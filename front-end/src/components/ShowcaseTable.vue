<template>
  <div>
    <div class="toolbox clearfix">
      <el-button-group style="float: left;">
        <el-button type="primary" v-on:click="sort('price')">Price</el-button>
        <el-button type="primary" v-on:click="sort('rate')">Rate</el-button>
        <el-button type="primary" v-on:click="sort('name')">Name</el-button>
      </el-button-group>
      <el-input class="searchbox" placeholder="please input..." suffix-icon="el-icon-search" v-model="input" v-on:input="search()"></el-input>
      <div class="pricebox">
        <span style="font-size:15px; margin-left:10px">Price Interval:￥</span>
        <el-input size="mini" v-model="borderLow" style="width: 45px; margin-left: 10px; margin-right: 5px" v-on:input="setBorder()"></el-input>
        ~
        <el-input size="mini" v-model="borderHigh" style="width: 45px; margin-left: 10px" v-on:input="setBorder()"></el-input>
      </div>
    </div>
    <div>
      <div v-for="book in bookList" :key="book.bookName">
        <showcase v-bind:book="book"></showcase>
      </div>
    </div>
  </div>
</template>

<script>
  import Showcase from "./Showcase";

  export default {
        name: "showcaseTable",
        components: {Showcase},
        props:["category"],
        data: function () {
            return {
              bookList : null,
              input:"",
              borderLow:"",
              borderHigh:"",
              sortby: null,
              descending: false,
              preSearchList: null,
              // categories:this.allBooks.category,
            }
        },

        methods:{
          sort:function (e) {
            let sortby = e;
            let booklist = this.bookList;
            let descending = this.sortby === sortby && !this.descending;
            booklist.sort((a, b) => {
              return descending
                ? (a[sortby] < b[sortby] ? 1 : -1)
                : (a[sortby] > b[sortby] ? 1 : -1);
            });
            this.bookList = booklist;
            this.descending = descending;
            this.sortby = sortby;
          },
          search:function () {
            let needle = this.input.toLowerCase();
            if (!needle) {
              this.bookList = this.preSearchList;
              return;
            }
            this.bookList = this.preSearchList.filter((item) => {
              return (item.bookName.toLowerCase().indexOf(needle) > -1) || (item.author.toLowerCase().indexOf(needle) > -1) || (item.press.toLowerCase().indexOf(needle) > -1);
            })
          },
          setBorder(){
                let borderLow = parseFloat(this.borderLow) ? parseFloat(this.borderLow) : 0;
                let borderHigh = parseFloat(this.borderHigh)? parseFloat(this.borderHigh):1000;
                this.bookList = this.preSearchList.filter(item => item.price >= borderLow && item.price <= borderHigh);
          },
        },
        watch:{
          category(val){
            this.bookList = this.preSearchList.filter((item) => {
              return (item.categories.toLowerCase().indexOf(val) > -1);
            })
          }
        },
        mounted() {
              fetch("http://localhost:8080/getBriefBooks")
                .then(response => response.json())
                .then(data => {
                  //alert("data:" + data);
                  console.log(data);
                  this.preSearchList = data;
                  this.bookList = data.filter((item) => {
                    return (item.categories.toLowerCase().indexOf("hot") > -1);
                  });
                }).catch(function (ex) {
                console.log('parsing failed', ex)
              })
        }
  }
</script>

<style scoped>
  .clearfix{
    zoom: 1;
  }
  .clearfix:after {
    clear:both;
    display:block;
    content:"";
    height:0;
    overflow:hidden;
  }
  .toolbox{
    width: 100%;
    padding: 2px;
    border: 1px solid #dcdfe6;
    border-radius: 4px;
    margin-top: 20px;
    margin-bottom: 10px;
  }
  .searchbox{
    float: left;
    width: 300px;
    margin: 0 15px 0 15px;
  }
  .pricebox{
    float:right;
    height:100%;
    border-radius:4px;
    background-color:#20a0ff;
    color:white;
    padding:6px;
  }
</style>
