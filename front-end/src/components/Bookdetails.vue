<template>
  <div>
    <Navigation></Navigation>
    <div class="container-fluid container" style="padding-top: 70px; padding-bottom: 50px;">
      <categories/>
      <div class="col-md-9">
        <div class="bookdetail" style="width: 100%">
          <div class="col-md-5"><img alt="" :src="book.cover.cover" style="width: 100%;"></div>
          <div class="col-md-7">
            <h1 class="booktitle">{{book.bookName}}<el-button style="float: right;" type="primary" icon="el-icon-back" v-on:click="goBack()" circle></el-button></h1>
            <h5>Author: </h5><h4>{{book.author}}</h4>
            <h5>Press: </h5><h4>{{book.press}}</h4>
            <h5>Price: </h5><h1 class="bookprice">￥{{book.price.toFixed(2)}}</h1>
            <h5>Stock: </h5>
            <div @dblclick="editableS = !editableS">
            <h4 v-show="!editableS">{{book.stock}}</h4>
            <el-input type="text" autosize v-model="book.stock" v-show="editableS" @blur="editableS = !editableS"></el-input>
            </div>
            <h5>Introduction: </h5>
            <div @dblclick="editable = !editable">
            <h4 v-show="!editable">{{book.description}}</h4>
            <el-input type="textarea" autosize v-model="book.description" v-show="editable" @blur="editable = !editable"></el-input>
            </div>
          </div>
        </div>
        <div style="clear: both"></div>
        <div style="width: 100%; margin-left: 220px; margin-top: 50px; margin-bottom: 20px">
          <el-button type="danger" icon="el-icon-coin">Buy Now</el-button>
          <el-button type="danger" plain icon="el-icon-shopping-cart-full" v-on:click="addToCart(book)">Add to Cart</el-button>
          <el-button type="warning" icon="el-icon-star-on" v-on:click="addToLike(book)">Like</el-button>
        </div>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
  import Navigation from "./Navigation";
  import Categories from "./Categories";
  import Footer from "./Footer";
    export default {
        name: "Bookdetails",
        components:{
          Navigation,
          Categories,
          Footer,
        },
        props:{
          book: null,
          editable: false,
          editableS: false,
        },
      created() {
          console.log(this.props.book.bookName);

      },
      methods:{
          goBack:function () {
            this.$router.go(-1);
          },
          addToCart:function (book) {
              console.log(book);
              console.log(this.$client);
              let data = this.$qs.stringify({
                user_id: this.$client.user_id,
                book_id: book.bookId,
                quantity: 1
              });
              this.$axios.post(
                "http://localhost:8080/addToCart",
                data
              ).then(result => {
                console.log(result);
              }).catch(function (ex) {
                console.log('parsing failed', ex)
              });


              // console.log(book);
              // let bk = {
              //   id:book.bookId,
              //   cover:book.cover,
              //   price:book.price,
              //   name:book.bookName,
              //   author:book.author,
              //   press:book.press,
              //   quantity:1,
              //   chosen:false,
              // };
              // console.log(bk);
              // let exist = this.$client.cart.find((item) => item.name === bk.name);
              //
              this.$message({
                message: "You've add a book into cart!",
                type: 'success'
              });
              //
              // if(exist){
              //   exist.quantity ++ ;
              //
              //   return;
              // }
              // console.log(book.bookName);
              // this.$client.cart.push(bk);
          },
        addToLike:function (book) {
          let data = this.$qs.stringify({
            user_id: this.$client.user_id,
            book_id: book.bookId,
          });
          this.$axios.post("http://localhost:8080/addToLikeList", data
          ).then(result => {
            console.log(result.data);
          });
          this.$message({
            message: "You've add a book into your favorite list!",
            type: 'success'
          });
        }
      }
    }
</script>

<style scoped>
  .bookprice{
    color: red;
    margin-top: 10px;
  }
</style>
