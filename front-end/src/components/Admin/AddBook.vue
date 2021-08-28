<template>
  <div class="col-md-9" style="min-height: 1000px">
    <div class="infoBox">
      <el-form style="margin-left: 50px">
        <el-form-item label="Cover" label-width="100px" style="margin-top: 20px" required>
          <el-upload
            class="avatar-uploader"
            ref="upload"
            action="fakeaction"
            :show-file-list="false"
            :before-upload="beforeUpload">
            <img alt="" v-if="form.cover" :src="form.cover" style="width: 160px; height: auto" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="BookName" label-width="100px" required>
          <el-input v-model="form.bookName" style="width: 50%; margin-right: 5px" ></el-input>
        </el-form-item>
        <el-form-item label="Author" label-width="100px" required>
          <el-input v-model="form.author" style="width: 50%; margin-right: 5px"></el-input>
        </el-form-item>
        <el-form-item label="Press" label-width="100px" required>
          <el-input v-model="form.press" style="width: 50%; margin-right: 5px" ></el-input>
        </el-form-item>
        <el-form-item label="ISBN" label-width="100px" required>
          <el-input v-model="form.isbn" style="width: 50%; margin-right: 5px" ></el-input>
        </el-form-item>
        <el-form-item label="Price" label-width="100px" required>
          <el-input-number :precision="2" :step="0.1" :min="0" controls-position="right" v-model="form.price" style="width: 50%; margin-right: 5px" ></el-input-number>
        </el-form-item>
        <el-form-item label="Rate" label-width="100px" required>
          <el-input-number :precision="1" :step="0.1" :min="0" :max="5" controls-position="right" v-model="form.rate" style="width: 50%; margin-right: 5px" ></el-input-number>
        </el-form-item>
        <el-form-item label="Stock" label-width="100px" required>
          <el-input-number v-model="form.stock" controls-position="right" style="width: 50%; margin-right: 5px" :min="0" :max="10000"></el-input-number>
        </el-form-item>
        <el-form-item label="Categories" label-width="100px" required>
          <el-checkbox-group v-model="form.categories">
            <el-checkbox label="hot">Hot</el-checkbox>
            <el-checkbox label="onsale">On Sale</el-checkbox>
            <el-checkbox label="novel">Novel</el-checkbox>
            <el-checkbox label="economy">Economy</el-checkbox>
            <el-checkbox label="literature">Literature</el-checkbox>
            <el-checkbox label="teenage">Teenage</el-checkbox>
            <el-checkbox label="science">Science</el-checkbox>
            <el-checkbox label="history">History</el-checkbox>
            <el-checkbox label="biography">Biography</el-checkbox>
            <el-checkbox label="other">Other</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="Description" label-width="100px" required>
          <el-input v-model="form.description" type="textarea" :rows="5" style="width: 50%; margin-right: 5px" ></el-input>
        </el-form-item>
      </el-form>
      <div style="margin-left: 150px; margin-top: 40px;">
        <el-button type="primary" @click="saveBook" >Save</el-button>
        <el-button type="danger" @click="ditch" style="margin-left: 50px">Ditch</el-button>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "AddBook",
        data:function () {
          return{
            form:{
              cover:"",
              bookName:"",
              isbn:"",
              author:"",
              press:"",
              price:0,
              rate:0,
              stock:0,
              description:"",
              categories:[],
            },
          }
        },
        methods:{
          beforeUpload:function (file) {
            console.log(file);

            let base64 = this.getBase64(file).then(res => {
              this.form.cover = res;
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
          ditch:function () {
             this.$router.go(-1);
          },
          saveBook:function () {
            let that = this;
            if(this.form.bookName === "" || this.form.author === "" || this.form.press === "" ||
              this.form.isbn === "" || this.form.rate === 0 || this.form.price === 0 || this.form.categories.length === 0 ||
              this.form.description === "" || this.form.cover === "" || this.form.stock === 0){
              this.$message("Form Unfilled");
              return;
            }
            let categories = "";
            this.form.categories.forEach(cate => {
              categories = categories.concat(cate, " ");
            });
            let data = this.$qs.stringify({
              cover: this.form.cover,
              bookName: this.form.bookName,
              author: this.form.author,
              press: this.form.press,
              description: this.form.description,
              isbn: parseInt(this.form.isbn),
              rate: this.form.rate,
              price: this.form.price,
              stock: this.form.stock,
              categories: categories,
            });
            // console.log(data);
            this.$axios.post("http://localhost:8080/addBook",data
            ).then(response => {
              that.$message("Success!");
              that.$router.go(-1);
            }).catch(function (ex) {
              console.log(ex)
            });
          }
        }
    }
</script>

<style scoped>
  .infoBox{
    width: 90%;
    height: 1070px;
    float: left;
    position: relative;
    padding: 5px;
    border: 1px solid #e6e6e6;
    margin: 5px 5px 5px 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
  }
</style>
