<template>
  <div class="col-md-9" style="height: auto">
    <div class="container">
      <el-input class="searchbox" placeholder="please input..." suffix-icon="el-icon-search" v-model="input" v-on:input="search()"></el-input>
      <el-button type="success" class="addBtn" icon="el-icon-circle-plus-outline" @click="addBook">Add New Book</el-button>
      <el-table
        :data="bookList"
        border
        height="630"
        style="width: 96%; margin-left: 15px">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" class="demo-table-expand">
              <el-form-item label="Cover" label-width="100px" size="mini">
                <el-upload
                  class="avatar-uploader"
                  ref="upload"
                  action="fakeaction"
                  :show-file-list="false"
                  :http-request="beforeUpload"
                  :data="props"
                  v-bind:disabled="!props.row.changing">
                  <img alt="" v-if="props.row.book.cover.cover" :src="props.row.book.cover.cover" style="width: 100px; height: auto" />
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
              </el-form-item>
              <el-form-item label="Bookname" label-width="100px" size="mini">
                <el-input v-if="props.row.changing" v-model="props.row.book.bookName"></el-input>
                <span v-if="!props.row.changing">{{ props.row.book.bookName }}</span>
              </el-form-item>
              <el-form-item label="ISBN" label-width="100px" size="mini">
                <el-input v-if="props.row.changing" v-model="props.row.book.isbn"></el-input>
                <span v-if="!props.row.changing">{{ props.row.book.isbn }}</span>
              </el-form-item>
              <el-form-item label="Author" label-width="100px" size="mini">
                <el-input v-if="props.row.changing" v-model="props.row.book.author"></el-input>
                <span v-if="!props.row.changing">{{ props.row.book.author }}</span>
              </el-form-item>
              <el-form-item label="Press" label-width="100px" size="mini">
                <el-input v-if="props.row.changing" v-model="props.row.book.press"></el-input>
                <span v-if="!props.row.changing">{{ props.row.book.press }}</span>
              </el-form-item>
              <el-form-item label="Rate" label-width="100px" size="mini">
                <el-input v-if="props.row.changing" v-model="props.row.book.rate"></el-input>
                <span v-if="!props.row.changing">{{ props.row.book.rate }}</span>
              </el-form-item>
              <el-form-item label="Price" label-width="100px" size="mini">
                <el-input v-if="props.row.changing" v-model="props.row.book.price"></el-input>
                <span v-if="!props.row.changing">￥ {{ props.row.book.price }}</span>
              </el-form-item>
              <el-form-item label="Stock" label-width="100px" size="mini">
                <el-input v-if="props.row.changing" v-model="props.row.book.stock"></el-input>
                <span v-if="!props.row.changing">{{ props.row.book.stock }}</span>
              </el-form-item>
              <el-form-item label="Category" label-width="100px" size="mini">
                <el-input v-if="props.row.changing" v-model="props.row.book.categories"></el-input>
                <span v-if="!props.row.changing">{{ props.row.book.categories }}</span>
              </el-form-item>
              <el-form-item label="Description" label-width="100px" size="mini">
                <el-input type="textarea" v-if="props.row.changing" v-model="props.row.book.description" autosize></el-input>
                <span class="intro" v-if="!props.row.changing">{{ props.row.book.description }}</span>
              </el-form-item>
              <el-form-item style="margin-left: 250px; margin-top: 20px">
                <template>
                  <el-button type="primary" v-if="!props.row.changing" @click="editBook(props.row)">Edit</el-button>
                  <el-button v-if="props.row.changing" @click="updateBook(props.row)">Save</el-button>
                  <el-button type="danger" v-if="props.row.changing" @click="ditchEdition(props.$index)">Cancel</el-button>
                </template>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          prop="book.cover"
          label="Cover"
          width="90">
          <template slot-scope="scope"><img alt="" :src="scope.row.book.cover.cover" style="width: 100%; height: auto"/></template>
        </el-table-column>
        <el-table-column
          prop="book.bookId"
          label="BookId"
          width="80">
        </el-table-column>
        <el-table-column
          prop="book.bookName"
          label="BookName"
          width="141">
        </el-table-column>
        <el-table-column
          prop="book.author"
          label="Author"
          width="140">
        </el-table-column>
        <el-table-column
          prop="book.stock"
          label="Stock"
          width="80">
        </el-table-column>
        <el-table-column
          label="Options"
          width="121">
          <template slot-scope="scope">
            <el-button @click="deleteBook(scope.row)" type="danger" size="small" icon="el-icon-circle-close">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
    export default {
        name: "BookManagement",
        data:function () {
            return{
              bookList:[],
              preSearchList:[],
              input:"",
            }
        },
        mounted() {
            fetch("http://localhost:8080/getBooks"
            ).then(response => response.json()
            ).then(data => {
              data.forEach(item => {
                let tmpBook = {
                  book: item,
                  changing: false,
                };
                this.bookList.push(tmpBook);
                this.preSearchList.push(tmpBook);
              });
            })
        },
        methods:{
            editBook:function (item) {
                item.changing = !item.changing;
            },
            updateBook:function (item) {
                console.log(item);
                if(item.book.bookId === "" || item.book.bookName === "" || item.book.author === "" || item.book.press === "" ||
                  item.book.isbn === "" || item.book.rate === "" || item.book.price === "" || item.book.categories === "" ||
                  item.book.description === "" || item.book.cover.cover === "" || item.book.stock === ""){
                  this.$message("Form Unfilled");
                  return;
                }
                let data = this.$qs.stringify({
                  cover: item.book.cover.cover,
                  bookId: item.book.bookId,
                  bookName: item.book.bookName,
                  author: item.book.author,
                  press: item.book.press,
                  description: item.book.description,
                  isbn: item.book.isbn,
                  rate: item.book.rate,
                  price: item.book.price,
                  stock: item.book.stock,
                  categories: item.book.categories,
                });
                this.$axios.post("http://localhost:8080/updateBook", data
                ).then(result => {
                  console.log(result);
                }).catch(function (ex) {
                  console.log(ex)
                });
                item.changing = !item.changing;
            },
            ditchEdition:function(index){
                let bookId = this.bookList[index].book.bookId;
                fetch("http://localhost:8080/getBook/"+bookId
                ).then(response => response.json()
                ).then(data => {
                  this.bookList[index].book = data;
                  this.bookList[index].changing = false;
                })
            },
            deleteBook:function(item){
                this.$confirm("You're going to delete this book eternally, are you sure?",  'Warning', {
                  confirmButtonText: 'Confirm',
                  cancelButtonText: 'Cancel',
                  type: 'warning'
                }).then(() => {
                  this.$axios.get("http://localhost:8080/deleteBook/"+item.book.bookId
                  ).then(result => {
                    console.log(result);
                  });
                  this.$message({
                    type: 'success',
                    message: 'Success!'
                  });
                  this.$forceUpdate();
                }).catch(() => {
                  this.$message({
                    type: 'info',
                    message: 'Operation Canceled'
                  });
                });
            },
            beforeUpload:function (param) {
                let index = param.data.$index;
                // console.log(index);

                let base64 = this.getBase64(param.file).then(res => {
                  this.bookList[index].book.cover.cover = res;
                });
                console.log(base64);
                return false;
            },
            getBase64(file) {
                return new Promise(function(resolve, reject) {
                    let reader = new FileReader();
                    let imgResult = "";
                    reader.readAsDataURL(file);
                    reader.onload = function() {
                      imgResult = reader.result;
                    };
                    reader.onerror = function(error) {
                      reject(error);
                    };
                    reader.onloadend = function() {
                      resolve(imgResult);
                    };
                });
            },
            search:function(){
              console.log(this.input);
              let needle = this.input.toLowerCase();
              if (!needle) {
                this.bookList = this.preSearchList;
                return;
              }
              this.bookList = this.preSearchList.filter((item) => {
                return (item.book.bookName.toLowerCase().indexOf(needle) > -1) || (item.book.author.toLowerCase().indexOf(needle) > -1) || (item.book.press.toLowerCase().indexOf(needle) > -1);
              })
            },
            addBook:function () {
              this.$router.push("addBook");
            }
        }
    }
</script>

<style scoped>
  @import "../../../src/assets/css/upload.css";
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
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand > .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 100%;
  }
  .intro{
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 3;
    overflow: hidden;
  }
  .searchbox{
    float: left;
    width: 400px;
    margin: 5px 15px 10px 15px;
  }
  .addBtn{
    float: right;
    margin-top: 5px;
    margin-right: 15px;
  }
</style>
