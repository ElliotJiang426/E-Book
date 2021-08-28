<template>
  <div class="col-md-9" style="min-height: 640px">
    <div class="infoBox">
      <el-form style="margin-left: 50px">
        <el-form-item label="Avatar" label-width="100px" style="margin-top: 20px">
            <el-upload
              class="avatar-uploader"
              ref="upload"
              action="fakeaction"
              :show-file-list="false"
              :before-upload="beforeUpload"
              v-bind:disabled="!changing">
              <img alt="" v-if="form.avatar" :src="form.avatar" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
        </el-form-item>
        <el-form-item label="Nickname" label-width="100px" required="true">
          <el-input v-model="form.nickname" style="width: 50%; margin-right: 5px" :disabled="!changing"></el-input>
        </el-form-item>
        <el-form-item label="Email" label-width="100px" required="true">
          <el-input v-model="form.email" style="width: 50%; margin-right: 5px" :disabled="!changing"></el-input>
        </el-form-item>
        <el-form-item label="Tel" label-width="100px">
          <el-input v-model="form.tel" style="width: 50%; margin-right: 5px" :disabled="!changing"></el-input>
        </el-form-item>
        <el-form-item label="Address" label-width="100px">
          <el-input v-model="form.address" style="width: 50%; margin-right: 5px" :disabled="!changing"></el-input>
        </el-form-item>
        <el-form-item label="Password" label-width="100px" required="true">
          <el-input v-model="form.password" v-bind:type="type" style="width: 50%; margin-right: 5px" :disabled="!changing">
            <el-button slot="append" icon="el-icon-view" @click="visible"></el-button>
          </el-input>
        </el-form-item>
      </el-form>
      <div style="margin-left: 150px; margin-top: 40px;">
        <el-button type="primary" @click="update" v-show="!changing">Update</el-button>
        <el-button type="danger" @click="ditch" v-show="changing">Ditch</el-button>
        <el-button @click="save" v-show="changing">Save</el-button>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "information",
        data:function () {
            return{
              form:{
                nickname:this.$client.nickname,
                tel:this.$client.tel,
                email:this.$client.email,
                address:this.$client.address,
                password:this.$client.password,
                avatar:this.$client.avatar,
              },
              changing:false,
              type:"password",
            }
        },
        methods:{
          visible:function () {
            if(this.type === "text")
              this.type = "password";
            else
              this.type = "text";
          },
          update:function () {
            this.changing = true;
          },
          ditch:function () {
            this.changing = false;
            this.form.nickname = this.$client.nickname;
            this.form.email = this.$client.email;
            this.form.tel = this.$client.tel;
            this.form.address = this.$client.address;
            this.form.password = this.$client.password;
            this.form.avatar = this.$client.avatar;
          },
          save:function (param) {
            let data = this.$qs.stringify({
              user_id:this.$client.user_id,
              nickname:this.form.nickname,
              email:this.form.email,
              tel:this.form.tel,
              address:this.form.address,
              password:this.form.password,
              avatar:this.form.avatar,
            });
            this.$axios.post("http://localhost:8080/updateUser", data
            ).then(result => {
              console.log(result);
              this.$message.success("You've updated your information!");
              this.changing = false;

              this.$client.nickname = result.data.nickname;
              this.$client.password = this.form.password;
              this.$client.avatar = result.data.avatar.avatar;
              this.$client.email = result.data.email;
              this.$client.tel = result.data.tel;
              this.$client.address = result.data.address;

              this.$emit("userUpdated");

            }).catch(error => {
              console.log(error);
              this.$message({
                type: 'error',
                message: 'Your input is wrong!'
              });
            })
          },
          beforeUpload:function (file) {
            console.log(file);

            let base64 = this.getBase64(file).then(res => {
              this.form.avatar = res;
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
        }
    }
</script>

<style>
  @import "../../../src/assets/css/upload.css";

  .infoBox{
    width: 90%;
    height: 650px;
    float: left;
    position: relative;
    padding: 5px;
    border: 1px solid #e6e6e6;
    margin: 5px 5px 5px 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
  }

</style>
