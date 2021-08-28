<template>
  <div :style="background">
    <el-form ref="form" :model="form" label-width="80px" class="loginbox" v-show="!showRegisterForm">
      <el-form-item style="margin-bottom: 0;" >
        <span style="font-size: 30px; color: white">Welcome!</span>
      </el-form-item>
      <el-form-item label="Username" style="width: 100%; margin-top: 5px">
        <el-input v-model="form.username" prefix-icon="el-icon-user" style="width: 80%; margin-right: 5px" ></el-input>
        <el-link type="info" :underline="false" v-on:click="register()">Register</el-link>
      </el-form-item>
      <el-form-item label="Password" style="width: 100%">
        <el-input type="password" v-model="form.password" prefix-icon="el-icon-lock" style="width: 80%; margin-right: 5px"></el-input>
        <el-link type="info" :underline="false">Forget?</el-link>
      </el-form-item>
      <el-form-item style="margin-bottom: 5px">
        <el-checkbox label="Remember Me" v-model="form.rememberPwd" style="margin-right: 60px"></el-checkbox>
        <el-button @click="submit()">Login</el-button>
      </el-form-item>
    </el-form>
    <el-form ref="registerForm" status-icon :model="registerForm" :rules="rules" label-width="100px" class="registerbox" v-show="showRegisterForm">
      <el-form-item style="margin-bottom: 0;" >
        <span style="font-size: 15px;">Please Fill The Form</span>
      </el-form-item>
      <el-form-item label="Username" prop="username" style="width: 100%; margin-top: 5px">
        <el-input v-model="registerForm.username" prefix-icon="el-icon-user" style="width: 80%; margin-right: 5px" ></el-input>
      </el-form-item>
      <el-form-item label="Password" prop="password" style="width: 100%">
        <el-input type="password" v-model="registerForm.password" prefix-icon="el-icon-lock" style="width: 80%; margin-right: 5px"></el-input>
      </el-form-item>
      <el-form-item label="Confirm" prop="confirm_pwd" style="width: 100%">
        <el-input type="password" v-model="registerForm.confirm_pwd" prefix-icon="el-icon-lock" style="width: 80%; margin-right: 5px"></el-input>
      </el-form-item>
      <el-form-item label="Nickname" style="width: 100%; margin-top: 5px">
        <el-input v-model="registerForm.nickname" prefix-icon="el-icon-s-custom" style="width: 80%; margin-right: 5px" ></el-input>
      </el-form-item>
      <el-form-item label="Name" style="width: 100%; margin-top: 5px">
        <el-input v-model="registerForm.name" prefix-icon="el-icon-s-custom" style="width: 80%; margin-right: 5px" ></el-input>
      </el-form-item>
      <el-form-item label="Email" prop="email" style="width: 100%; margin-top: 5px">
        <el-input v-model="registerForm.email" prefix-icon="el-icon-message" style="width: 80%; margin-right: 5px" ></el-input>
      </el-form-item>
      <el-form-item label="Tel" style="width: 100%; margin-top: 5px">
        <el-input v-model="registerForm.tel" prefix-icon="el-icon-phone" style="width: 80%; margin-right: 5px" ></el-input>
      </el-form-item>
      <el-form-item label="Address" style="width: 100%; margin-top: 5px">
        <el-input v-model="registerForm.address" prefix-icon="el-icon-location" style="width: 80%; margin-right: 5px" ></el-input>
      </el-form-item>
      <el-form-item style="margin-bottom: 5px">
        <el-button type="danger" @click="register()">Back</el-button>
        <el-button @click="submitRegisterForm('registerForm')">Register</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
        name: "Loginform",
        data: function () {
          let avoidDuplicate = (rule, value, callback) => {
            console.log(value);
            fetch("http://localhost:8080/getUser/"+value
            ).then(response => response.json()
            ).then((data) => {
              console.log(data);
              if(value === ''){
                callback(new Error('plz input your username'));
              }
              else {
                callback(new Error('username is already taken'));
              }
            }).catch(function (ex) {
              console.log('parsing failed', ex);
              if(value === ''){
                callback(new Error('plz input your username'));
              } else {
                callback();
              }
            });
          };
          let validatePass = (rule, value, callback) => {
            if (value === '') {
              callback(new Error('plz input you password'));
            } else if(value.length < 6){
              callback(new Error('your password is too short'))
            } else {
              if (this.registerForm.confirm_pwd !== '') {
                this.$refs.registerForm.validateField('confirm_pwd');
              }
              callback();
            }
          };
          let validatePass2 = (rule, value, callback) => {
            if(value !== this.registerForm.password){
              callback(new Error('two inputs differ'))
            } else {
              callback();
            }
          };
          let validateEmail = (rule, value, callback) => {
            const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
            if(value === ''){
              callback(new Error('plz input your email address'));
            } else if(mailReg.test(value)){
              callback();
            } else {
              callback(new Error('plz input correct email address'));
            }
          };
          return{
            form:{
              username:"",
              password:"",
              //rememberPwd:false,
            },
            background: {
              backgroundImage: "url("+require("../assets/image/background.jpg")+")",
              backgroundRepeat: "no-repeat",
              backgroundSize: "1600px",
              height: "720px"
            },
            showRegisterForm:false,
            registerForm:{
              username:"",
              password:"",
              confirm_pwd :"",
              nickname:"Anonymous",
              name:"",
              tel:"",
              address:"",
              email:"",
            },
            rules:{
              username:[
                { required: true, validator: avoidDuplicate, trigger: 'blur' },
              ],
              password:[
                { required: true, validator: validatePass, trigger: 'blur'} ,
              ],
              confirm_pwd:[
                { required: true, validator: validatePass2, trigger: 'blur'},
              ],
              email:[
                { required: true, validator: validateEmail, trigger: 'blur'},
              ]
            }
          }
        },
        methods:{
          submit:function(){
            let that = this;
            let userMsg  = this.$qs.stringify({
              username:this.form.username,
              password:this.form.password,
            });
            this.$axios.post(
              "http://localhost:8080/checkUser",
              userMsg
            ).then(result => {
              console.log(result);
              if(result.data !== "") {
                if(result.data.userType === 2){
                  this.$message.error("Your Account is Blocked!");
                } else {
                  console.log(result.data);
                  this.$router.push("/home");
                  this.$client.user_id = result.data.user.userId;
                  this.$client.username = result.data.username;
                  this.$client.password = result.data.password;
                  this.$client.nickname = result.data.user.nickname;
                  this.$client.balance = result.data.user.balance;
                  this.$client.avatar = result.data.user.avatar.avatar;
                  this.$client.email = result.data.user.email;
                  this.$client.tel = result.data.user.tel;
                  this.$client.address = result.data.user.address;
                  this.$client.user_type = result.data.userType;
                }
              }
              else{
                this.$message.error("You have a wrong username or password");
              }
            }).catch(function (error) {
              console.log(error);
              that.$message.error("You have a wrong username or password");
            });

            //this.$router.push("/home");
          },
          register:function() {
              this.showRegisterForm = !this.showRegisterForm;
          },
          submitRegisterForm:function(formName) {
            this.$refs[formName].validate((valid) => {
              if (valid) {
                let data = this.$qs.stringify({
                  username: this.registerForm.username,
                  password: this.registerForm.password,
                  nickname: this.registerForm.nickname,
                  name: this.registerForm.name,
                  email: this.registerForm.email,
                  tel: this.registerForm.tel,
                  address: this.registerForm.address,
                });
                this.$axios.post("http://localhost:8080/addUser", data
                ).then(result => {
                  console.log(result.data);
                  alert('submit!');
                  setTimeout(this.register, 3000);
                });

              } else {
                console.log('error submit!!');
                return false;
              }
            });
          }
        }
    }
</script>

<style scoped>
 .loginbox {
   border: 1px solid #bce8f1;
   border-radius: 4px;
   padding: 15px;
   margin: auto;
   width: 440px;
   box-shadow: 8px 8px 8px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
   position: absolute;
   top: 50%;
   left: 50%;
   transform: translate(-50%,-50%);
   color: white;
 }
  .registerbox{
    border: 1px solid #bce8f1;
    border-radius: 4px;
    padding: 15px;
    margin: auto;
    width: 400px;
    box-shadow: 8px 8px 8px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    position: absolute;;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    color: #000000;
    background-color: white;
  }
</style>
