<template>
  <div class="showcase panel panel-default" v-on:click="viewDetails()">
    <img class="bookcover" :src="book.cover.cover" alt=""/>
    <div class="bookinf">
      <p style="font-size: 18px;">{{book.bookName}}</p>
      <p style="color: #9d9d9d; margin-bottom: 5px">
        {{book.author}}<br>
        {{book.press}}
      </p>
      <b style="font-size: 18px; margin-top: 5px; color: red">￥{{book.price.toFixed(2)}}</b>
      <el-rate :value="book.rate" disabled show-score></el-rate>
    </div>
  </div>
</template>

<script>
    export default {
        name: "showcase",
        props: {
          book: null,
        },
        methods:{
          viewDetails(){
            this.$router.push({
              path:"/bookdetails",
              query:{bookId:this.book.bookId}
            })
          }
        }
    }
</script>

<style scoped>
  .showcase{
    width: 18.5%;
    height: 350px;
    float: left;
    position: relative;
    padding: 5px;
    border-bottom: 1px solid #e6e6e6;
    border-right: 1px solid #e6e6e6;
    margin: 5px 5px 5px 5px;
  }
  .showcase:hover{
    background-color: #ecf5ff;
  }
  .bookcover{
    width: 100%;
    max-height: 250px;
  }
  .bookinf{
    font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei",微软雅黑,Arial,sans-serif;
    padding-top: 5px;
    bottom: 5px;
    position: absolute;
    font-size: 13px;
  }
  .bookinf{
    margin: 0;
  }
  .buybtn{
    width: 20px;
    height: 8px;
    float: right;
    font-size: 10px;
    text-align: center;
  }
</style>
