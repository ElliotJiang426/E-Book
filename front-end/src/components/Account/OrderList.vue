<template>
  <div class="col-md-9" style="min-height: 640px">
    <el-input class="searchbox" placeholder="please input..." suffix-icon="el-icon-search" v-model="input1" v-on:input="search()"></el-input>
    <el-date-picker
      v-model="input2"
      type="date"
      placeholder="Select Date"
      class="datebox"
      v-on:input="dateSelect()"
    >
    </el-date-picker>
    <div v-for="order in orderList" :key="order.orderId" v-show="!empty">
      <div class="ordercase">
        <el-table
          :data="order.orderItemList"
          stripe
          height="225"
          style="width: 50%; float: left">
          <el-table-column
            width="100">
            <template slot-scope="scope"><img alt="" :src="scope.row.book.cover.cover" style="width: 30px; height: auto"/></template>
          </el-table-column>
          <el-table-column
            prop="book.bookName"
            label="书名"
            width="150">
          </el-table-column>
          <el-table-column
            prop="price"
            label="单价"
            width="60">
          </el-table-column>
          <el-table-column
            prop="quantity"
            label="数量"
            width="55">
          </el-table-column>
        </el-table>
        <div class="orderInfo">
          <p>Order Id: {{order.orderId}}</p>
          <p>Total Cost: ￥{{order.total.toFixed(2)}}</p>
          <p>Receiver Name: {{order.receiver.name}}</p>
          <p>Receiver Tel: {{order.receiver.tel}}</p>
          <p class="address">Receiver Address: {{order.receiver.address}}</p>
          <p class="address">Time: {{order.date}}</p>
        </div>
      </div>
    </div>
    <div v-show="empty">
      <el-alert
        title="EMPTY!"
        description="Your orderList is empty! Go and buy some book into it!"
        type="info"
        :closable="false"
        show-icon>
      </el-alert>
    </div>
  </div>
</template>

<script>
    export default {
        name: "orderlist",
        data:function () {
          return{
            orderList:[],
            preSearchList:[],
            input1: "",
            input2: null,
            empty: true,
          }
        },
        beforeMount() {
          if(this.$route.query.isAdmin){
            fetch("http://localhost:8080/getAllOrders"
            ).then(response => response.json()
            ).then(data => {
              data.forEach(item => {
                let orderInfo = {
                  orderItemList:null,
                  orderId:0,
                  receiver:null,
                  total:0,
                  date:"",
                };
                orderInfo.orderItemList = item.orderItemList;
                orderInfo.orderId = item.orderId;
                orderInfo.receiver = item.receiver;
                orderInfo.date = new Date(item.orderTime).toLocaleString('chinese', { hour12: false});
                item.orderItemList.forEach(item => {
                  orderInfo.total = orderInfo.total+item.price*item.quantity;
                });
                this.orderList.push(orderInfo);
                this.preSearchList.push(orderInfo);
                this.empty = false;
              })
            });
            return;
          }

          fetch("http://localhost:8080/getOrders/"+this.$client.user_id
          ).then(response => response.json()
          ).then(data => {
            data.forEach(item => {
              let orderInfo = {
                orderItemList:null,
                orderId:0,
                receiver:null,
                total:0,
                date:"",
              };
              orderInfo.orderItemList = item.orderItemList;
              orderInfo.orderId = item.orderId;
              orderInfo.receiver = item.receiver;
              orderInfo.date = new Date(item.orderTime).toLocaleString('chinese', { hour12: false});
              item.orderItemList.forEach(item => {
                orderInfo.total = orderInfo.total+item.price*item.quantity;
              });
              this.orderList.push(orderInfo);
              this.preSearchList.push(orderInfo);
              this.empty = false;
            })
          })
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
          search:function () {
            let needle = this.input1.toLowerCase();
            if (!needle) {
              this.orderList = this.preSearchList;
              return;
            }
            this.orderList = this.preSearchList.filter((item) => {
              let orderItems = item.orderItemList.filter((item) => {
                return (item.book.bookName.toLowerCase().indexOf(needle) > -1);
              });

              return (item.orderId.toString().indexOf(needle.toString()) > -1) ||
                (item.receiver.name.toLowerCase().indexOf(needle) > -1) || (item.receiver.tel.toLowerCase().indexOf(needle) > -1) || (item.receiver.address.toLowerCase().indexOf(needle) > -1) || orderItems.length !== 0;
            })
          },
          dateSelect:function () {
            if(!this.input2){
              this.orderList = this.preSearchList;
              return;
            }
            let date = this.dateFtt("yyyy-MM-dd",this.input2);
            // console.log(date);
            this.orderList = this.preSearchList.filter(item => {
              return this.dateFtt("yyyy-MM-dd",item.date) === date;
            })
          },
        }
    }
</script>

<style scoped>
  .ordercase{
    width: 90%;
    height: 240px;
    float: left;
    position: relative;
    padding: 5px;
    border: 1px solid #e6e6e6;
    margin: 5px 5px 5px 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
  }
  .orderInfo{
    width: 300px;
    margin-left: 20px;
    font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei",微软雅黑,Arial,sans-serif;
    padding-top: 10px;
    bottom: 5px;
    float: left;
    font-size: 15px;
  }
  .address{
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    overflow: hidden;
  }
  .searchbox{
    float: left;
    width: 400px;
    margin: 5px 15px 10px 5px;
  }
  .datebox{
    width: 200px;
    margin-top: 5px;
    margin-left: 125px;
  }
</style>

