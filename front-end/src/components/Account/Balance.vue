<template>
  <div class="col-md-9" style="min-height: 640px">
    <div class="balanceCase">
      <div >
        <h1 style="margin-left: 20px">Your Balance: </h1>
        <div style="text-align: center">
        <img class="icon" alt="" :src="logo"/>
        <el-input class="balanceinput" v-bind:value="balance" v-bind:type="type" :disabled="true">
          <el-button slot="append" icon="el-icon-view" @click="visible"></el-button>
        </el-input>
        </div>
        <div style="text-align: center">
          <el-button type="success" style="margin-top: 60px" @click="recharge">Recharge</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "balance",
        data:function () {
          return{
            logo:require('../../assets/image/icons/money.png'),
            balance: this.$client.balance.toFixed(2),
            type:"password"
          }
        },
        methods:{
          visible:function () {
            if(this.type === "text")
              this.type = "password";
            else
              this.type = "text";
          },
          recharge() {
            this.$prompt('Please input your password', 'Hint', {
              confirmButtonText: 'Confirm',
              cancelButtonText: 'Cancel',
              inputType:'password'
            }).then(({ value }) => {
              if(value !== this.$client.password){
                this.$message({
                  type: 'error',
                  message: 'Your input is wrong!'
                });
              } else {
                this.$prompt('How much would you like to recharge?', 'Hint', {
                  confirmButtonText: 'Confirm',
                  cancelButtonText: 'Cancel',
                  inputType: 'number',
                  inputErrorMessage: 'You can only input numbers'
                }).then(({ value }) => {
                  let data = this.$qs.stringify({
                    user_id: this.$client.user_id,
                    bills: value,
                  });
                  console.log(value);
                  console.log(data);
                  this.$axios.post("http://localhost:8080/recharge", data
                  ).then(result => {
                    console.log(result);
                    this.$client.balance = result.data.balance;
                    this.balance = result.data.balance;
                    this.$message({
                      type: 'success',
                      message: "You've successfully recharged! Thank you for your support!"
                    });
                  })
                })
              }
            }).catch(() => {
              this.$message({
                type: 'info',
                message: 'canceled'
              });
            });
          }
        }
    }
</script>

<style scoped>
  .balanceCase{
    width: 90%;
    height: 400px;
    float: left;
    position: relative;
    padding: 5px;
    border: 1px solid #e6e6e6;
    margin: 5px 5px 5px 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
  }
  .icon{
    width: 100px;
    height: auto;
  }
  .balanceinput{
    width: 200px;
    margin-top: 100px;
    margin-left: 20px;
  }
</style>
