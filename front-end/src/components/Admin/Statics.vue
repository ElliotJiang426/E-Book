<template>
  <div class="col-md-9" style="height: auto">
    <div class="container">
      <el-date-picker
        v-model="startDate"
        type="date"
        placeholder="Start Date"
        class="datebox"
        style="margin-top: 5px; margin-left: 10px; margin-bottom: 10px"
        v-on:input="startDateSelect()"
      >
      </el-date-picker>
      <el-date-picker
        v-model="endDate"
        type="date"
        placeholder="End Date"
        class="datebox"
        style="margin-top: 5px; margin-bottom: 10px"
        v-on:input="endDateSelect()"
      >
      </el-date-picker>
      <el-button type="primary" style="margin-top: 5px; margin-left: 5px" icon="el-icon-s-data" @click="rankUser">RankUser</el-button>
      <el-button type="success" class="addBtn" icon="el-icon-s-data" @click="rankBook">RankBook</el-button>
      <el-table
        v-if="!userStatics"
        :data="bookList"
        border
        height="630"
        style="width: 96%; margin-left: 15px">
        <el-table-column
          type="index"
          width="80">
        </el-table-column>
        <el-table-column
          label="Cover"
          width="90">
          <template slot-scope="scope"><img alt="" :src="scope.row.cover" style="width: 100%; height: auto"/></template>
        </el-table-column>
        <el-table-column
          prop="bookName"
          label="BookName"
          width="141">
        </el-table-column>
        <el-table-column
          prop="author"
          label="Author"
          width="140">
        </el-table-column>
        <el-table-column
          prop="press"
          label="Press"
          width="140">
        </el-table-column>
        <el-table-column
          prop="sale"
          label="Sale"
          width="109">
        </el-table-column>
      </el-table>
      <el-table
        v-if="userStatics"
        :data="userList"
        border
        height="630"
        style="width: 96%; margin-left: 15px">
        <el-table-column
          type="index"
          width="80">
        </el-table-column>
        <el-table-column
          label="Avatar"
          width="90">
          <template slot-scope="scope"><img alt="" :src="scope.row.avatar" style="width: 100%; height: auto"/></template>
        </el-table-column>
        <el-table-column
          prop="userId"
          label="UserID"
          width="141">
        </el-table-column>
        <el-table-column
          prop="username"
          label="Username"
          width="140">
        </el-table-column>
        <el-table-column
          prop="amount"
          label="Amount"
          width="140">
        </el-table-column>
        <el-table-column
          label="Bills"
          width="109">
          <template slot-scope="scope">{{scope.row.bills.toFixed(2)}}</template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
    export default {
        name: "statics",
        data:function () {
            return{
              bookList:[],
              preSearchList:[],
              allOrders:[],
              userList:[],
              allUsers:[],
              startDate:null,
              endDate:null,
              userStatics:0,
            }
        },
        methods:{
          dateFtt: function(fmt, date) {
            if(!date) date = new Date();
            if(!(date instanceof Date)) date = new Date(date);
            let o = {
              "M+" : date.getMonth()+1,                 //月份
              "d+" : date.getDate(),                    //日
              "h+" : date.getHours(),                   //小时
              "m+" : date.getMinutes(),                 //分
              "s+" : date.getSeconds(),                 //秒
              "q+" : Math.floor((date.getMonth()+3)/3), //季度
              "S"  : date.getMilliseconds()             //毫秒
            };
            if(/(y+)/.test(fmt))
              fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
            for(let k in o)
              if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length===1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
            return fmt;
          },
          startDateSelect:function () {
            if(!this.startDate) this.startDate = new Date("2020-01-01");
            // else this.startDate = new Date(this.startDate).toLocaleString('chinese', { hour12: false});
            console.log(this.startDate);
          },
          endDateSelect:function () {
            if(!this.endDate) this.endDate = new Date("2020-12-31");
            // else this.endDate = new Date(this.endDate).toLocaleString('chinese', { hour12: false});
            console.log(this.endDate);
            console.log(this.endDate > this.startDate);
          },
          rankBook:function () {
            this.userStatics = 0;
            if(!this.startDate) this.startDate = new Date("2020-01-01");
            if(!this.endDate) this.endDate = new Date("2020-12-31");
            let selectSpan = this.allOrders.filter(item => {
                let date = new Date(item.orderTime);

                if(date >= this.startDate && date <= this.endDate) return item;
            });
            this.bookList = this.preSearchList;
            this.bookList.forEach(book => {
              book.sale = 0;
            });
            selectSpan.forEach(order => {
              order.orderItemList.forEach(item => {
                this.bookList.forEach(book => {
                  if(book.bookId === item.bookId){
                    book.sale = book.sale + item.quantity;
                  }
                })
              })
            });

            this.bookList.sort((a, b) =>
                a.sale < b.sale ? 1 : -1
            );
          },
          rankUser:function() {
            this.userStatics = 1;

            if(!this.startDate) this.startDate = new Date("2020-01-01");
            if(!this.endDate) this.endDate = new Date("2020-12-31");

            this.userList = this.allUsers;
            this.userList.forEach(user => {
              user.amount = 0;
              user.bills = 0.00;

              fetch("http://localhost:8080/getOrders/"+user.userId
              ).then(response => response.json()
              ).then(data => {
                let filteredData = data.filter(item => {
                  let date = new Date(item.orderTime);

                  if(date >= this.startDate && date <= this.endDate) return item;
                });
                filteredData.forEach(item => {
                  item.orderItemList.forEach(item => {
                    user.amount = user.amount + item.quantity;
                    user.bills = user.bills + item.price*item.quantity;
                  });
                });
                console.log(user.bills.toFixed(2));
              }).then(() => {
                  this.userList.sort((a, b) =>
                    a.bills < b.bills ? 1 : -1
                  )}
              );
            });

          }
        },

        mounted() {
          fetch("http://localhost:8080/getAllUsers"
          ).then(response => response.json()
          ).then(data => {
            console.log(data);
            data.forEach(item => {
              let tmpUser = {
                avatar: item.user.avatar.avatar,
                username: item.username,
                userId: item.userId,
                amount: 0,
                bills: 0.0,
              };
              this.allUsers.push(tmpUser);
            });
          });
          fetch("http://localhost:8080/getBriefBooks")
            .then(response => response.json())
            .then(data => {
              //alert("data:" + data);
              console.log(data);

              data.forEach(item => {
                  let tmpbook = {
                    cover:item.cover.cover,
                    bookId:item.bookId,
                    bookName:item.bookName,
                    author:item.author,
                    press:item.press,
                    sale:0,
                  };
                  this.preSearchList.push(tmpbook);
                  //this.bookList.push(tmpbook);
              });
            }).catch(function (ex) {
            console.log('parsing failed', ex)
          });

          fetch("http://localhost:8080/getAllOrders"
          ).then(response => response.json()
          ).then(data => {
            console.log(data);
            this.allOrders = data;
          });
        }
    }
</script>

<style scoped>
  .container{
    width: 90%;
    height: auto;
    float: left;
    position: relative;
    padding: 5px;
    border: 1px solid #e6e6e6;
    margin: 5px 5px 5px 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .addBtn{
    float: right;
    margin-top: 5px;
    margin-right: 7px;
  }
</style>
