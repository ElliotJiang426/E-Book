<template>
  <div class="container-fluid container" style="padding-top: 70px; padding-bottom: 50px; font: 2px solid black">
    <el-form :model="this.order" label-width="100px" >
      <el-form-item label="收件人">
        <el-input v-model="order.receiver" style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item label="电话号码">
        <el-input v-model="order.phone"  style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item label="收件地址">
        <el-input v-model="order.address.province"  style="width: 200px">
          <template slot="append">省/自治区</template>
        </el-input>
        <el-input v-model="order.address.city"  style="width: 200px">
          <template slot="append">市/自治州</template>
        </el-input>
        <el-input v-model="order.address.county"  style="width: 200px">
          <template slot="append">区/县</template>
        </el-input>
      </el-form-item>
      <el-form-item label="详细地址">
        <el-input type="textarea" v-model="order.details"></el-input>
      </el-form-item>
      <el-form-item label="订单信息">
        <el-table :data="orderData" stripe style="width: 100%">
          <el-table-column
            label="商品图片"
            width="120">
            <template slot-scope="scope"><img alt="" :src="scope.row.book.cover.cover" style="width: 90%; height: auto"/></template>
          </el-table-column>
          <el-table-column
            label="书名"
            prop="book.bookName"
            width="120">
          </el-table-column>
          <el-table-column
            label="作者"
            prop="book.author"
            width="120">
          </el-table-column>
          <el-table-column
            label="出版社"
            prop="book.press"
            width="120">
          </el-table-column>
          <el-table-column
            label="单价"
            prop="book.price"
            width="120">
          </el-table-column>
          <el-table-column
            label="数量"
            prop="quantity"
            width="120">
          </el-table-column>
        </el-table>
      </el-form-item>
    </el-form>
    <div style="margin-top: 20px">
      <el-button type="danger" style="float:left;" v-on:click="abandon()">Abandon Order</el-button>
      <el-button type="primary" style="float:right;" v-on:click="pay()">Checked! Pay now!</el-button>
      <span class="totalcost">Total: ￥{{total}}</span>
    </div>
  </div>
</template>

<script>
    export default {
        name: "Order",

        data:function () {
          return{
            order:{
              receiver:"",
              phone:"",
              address:{
                province:"",
                city:"",
                county:""
              },
              details:"",
            },
            orderData: this.$route.query.orderData,
            total: this.$route.query.total,
          }
        },
        methods:{
          abandon:function () {
              this.$emit("toCart");
              this.$router.go(-1);
          },
          pay: async function () {
              if(this.order.name === "" || this.order.address.province === "" ||
              this.order.address.city === "" || this.order.address.county === "" ||
              this.order.phone === "" || this.order.details === "") {
                this.$message.error("You've left some information undefined");
                return;
              }
              if(this.$client.balance < this.total){
                  this.$message.error("Your balance is not enough :(");
                  return;
                } else {
                  this.$client.balance -= this.total;
                  let data = {
                    user_id : this.$client.user_id,
                    bills : 0-this.total,
                  };
                  this.$axios.post("http://localhost:8080/updateBalance", this.$qs.stringify(data)).then(result => {
                    console.log(result);
                  });
              }
              let receiverInfo = this.$qs.stringify({
                user_id: this.$client.user_id,
                name: this.order.receiver,
                tel: this.order.phone,
                address: this.order.address.province+"省"
                  +this.order.address.city+"市"
                  +this.order.address.county+"区"
                  +this.order.details,
              });
              this.$axios.post("http://localhost:8080/addToReceivers",
              receiverInfo).then(result => {
                // console.log(this.dateFtt("yyyy-MM-dd hh:mm:ss"));
                let data = this.$qs.stringify({
                  user_id: this.$client.user_id,
                  receiver_id: result.data.receiverId,
                });
                this.$axios.post("http://localhost:8080/addToOrders", data).then(result => {
                  console.log(result.data);
                  this.orderData.forEach(item => {
                    let data = this.$qs.stringify({
                      order_id : result.data.orderId,
                      book_id : item.book.bookId,
                      quantity : item.quantity,
                      price : item.book.price,
                    });
                    this.$axios.post("http://localhost:8080/addOrderItem", data).catch(function (ex) {
                      console.log('parsing failed', ex)
                    });
                    this.$axios.get("http://localhost:8080/deleteFromCart/userId="+this.$client.user_id+"&bookId="+item.book.bookId);
                  });
                }).catch(function (ex) {
                  console.log('parsing failed', ex)
                });
              }).catch(function (ex) {
                console.log('parsing failed', ex)
              });




              this.$emit('toComplete');
              await this.$router.push('/counter/complete');
          }
        }
    }
</script>

<style scoped>
  .totalcost{
    font-size: 20px;
    margin: 10px 40px 20px 0;
    float: right;
  }
</style>
