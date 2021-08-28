<template>
  <div class="container-fluid container" style="padding-top: 70px; padding-bottom: 50px;">
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column
        prop="chosen"
        width="50">
        <template slot-scope="scope"><el-checkbox v-model="scope.row.chosen" ></el-checkbox></template>
      </el-table-column>
      <el-table-column
        label="商品信息"
        width="180">
        <template slot-scope="scope"><img alt="" :src="scope.row.book.cover.cover" style="width: 90%; height: auto"/></template>
      </el-table-column>
      <el-table-column
        prop="book.bookName"
        label="书名"
        width="160">
      </el-table-column>
      <el-table-column
        prop="book.author"
        label="作者"
        width="160">
      </el-table-column>
      <el-table-column
        prop="book.press"
        label="出版社"
        width="160">
      </el-table-column>
      <el-table-column
        prop="book.price"
        label="单价(￥)"
        width="130">
      </el-table-column>
      <el-table-column
        prop="quantity"
        label="数量"
        width="200">
        <template slot-scope="scope"><el-input-number v-model="scope.row.quantity" :min="1" v-on:change="addToCart(scope.row)"></el-input-number></template>
      </el-table-column>
      <el-table-column
        width="100">
        <template slot-scope="scope"><el-button size="mini" type="danger" v-on:click="deleteFromCart(scope.row)">DELETE</el-button></template>
      </el-table-column>
    </el-table>
    <div class="gopay">
      <el-button type="primary" style="float:right;" v-on:click="toFillOrder()">Buy Now!</el-button>
      <span class="totalcost">Total: ￥{{totalCost}}</span>

    </div>
  </div>
</template>

<script>
    export default {
        name: "Cart",
        data:function () {
          return{
            tableData:[],
          }
        },
        mounted() {
          fetch("http://localhost:8080/getCart/"+this.$client.user_id
          ).then(response => response.json()
          ).then(data => {
              data.forEach(item => {
                let book = {
                  chosen:false,
                  book:null,
                  quantity:1,
                };
                fetch("http://localhost:8080/getBook/"+item.bookId)
                  .then(response => response.json())
                  .then(data => {
                    book.book = data;
                  }).catch(function (ex) {
                  console.log('parsing failed', ex)
                });
                book.quantity = item.quantity;
                this.tableData.push(book);
              });
              console.log("tableData");
              console.log(this.tableData);
            }
          )
        },
        methods:{
          deleteFromCart:function (item) {
              this.$axios.get("http://localhost:8080/deleteFromCart/userId="+this.$client.user_id+"&bookId="+item.book.bookId);
              this.tableData.splice(item.index, 1);
          },
          toFillOrder:function () {
              this.$emit("toOrder");
              let orderData = this.tableData.filter((book) => book.chosen === true);
              if(orderData.length === 0) {
                this.$message.error("You've not select any book!");
                return;
              }
              this.$router.push({path:"/counter/order", query:{orderData : orderData, total: this.totalCost}});
          },
          addToCart:function (item) {
            let data = this.$qs.stringify({
              user_id: this.$client.user_id,
              book_id: item.book.bookId,
              quantity: item.quantity
            });
            this.$axios.post(
              "http://localhost:8080/addToCart",
              data
            ).then(result => {
              console.log(result);
            })
          }
        },
        computed:{
          totalCost:function () {
            let chosenBooks = this.tableData.filter((book) => book.chosen === true);
            console.log(chosenBooks);
            let total = 0;
            chosenBooks.forEach((book) => total = total+book.book.price*book.quantity);
            return total.toFixed(2);
          }
        },
    }
</script>

<style scoped>
  .totalcost{
    font-size: 20px;
    margin: 10px 40px 20px 0;
    float: right;
  }
  .gopay{
    margin-top: 20px;
  }
</style>
