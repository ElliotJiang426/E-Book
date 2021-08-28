<template>
  <div class="col-md-9" style="min-height: 640px">
    <div class="container">
      <el-table
        :data="userList"
        border
        style="width: 96%; margin-left: 15px">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" class="demo-table-expand" >
              <el-form-item label="UserId" label-width="100px">
                <span>{{ props.row.user_id }}</span>
              </el-form-item>
              <el-form-item label="Username" label-width="100px">
                <span>{{ props.row.username }}</span>
              </el-form-item>
              <el-form-item label="Nickname" label-width="100px">
                <span>{{ props.row.user.nickname }}</span>
              </el-form-item>
              <el-form-item label="Tel" label-width="100px">
                <span>{{ props.row.user.tel }}</span>
              </el-form-item>
              <el-form-item label="Address" label-width="100px">
                <span>{{ props.row.user.address }}</span>
              </el-form-item>
              <el-form-item label="Email" label-width="100px">
                <span>{{ props.row.user.email }}</span>
              </el-form-item>
              <el-form-item label="Balance" label-width="100px">
                <span>￥ {{ props.row.user.balance }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          prop="user.avatar"
          label="Avatar"
          width="70">
          <template slot-scope="scope"><img alt="" :src="scope.row.user.avatar.avatar" style="width: 100%; height: auto"/></template>
        </el-table-column>
        <el-table-column
          prop="user_id"
          label="UserId"
          width="80">
        </el-table-column>
        <el-table-column
          prop="username"
          label="Username"
          width="141">
        </el-table-column>
        <el-table-column
          prop="user.nickname"
          label="Nickname"
          width="140">
        </el-table-column>
        <el-table-column
          prop="state"
          label="State"
          width="100">
        </el-table-column>
        <el-table-column
          label="Options"
          width="120">
          <template slot-scope="scope">
            <el-button v-if="scope.row.isNormal" @click="blockAccount(scope.row)" type="danger" size="small" icon="el-icon-lock">Block</el-button>
            <el-button v-if="!scope.row.isNormal" @click="unBlockAccount(scope.row)" type="success" size="small" icon="el-icon-unlock">Unblock</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
    export default {
        name: "UserManagement",
        data: function () {
          return{
            userList:[],
          }
        },
        mounted() {
          fetch("http://localhost:8080/getAllUsers"
          ).then(response => response.json()
          ).then(data => {
            console.log(data);
            let tmpUserList = [];
            let normalUsers = data.filter(item => item.userType !== 0);
            normalUsers.forEach(item => {
              let tmpUser = {
                user: item.user,
                username: item.username,
                user_id: item.userId,
                state: "normal",
                isNormal: true,
              };
              if(item.userType === 2) {
                tmpUser.state = "blocked";
                tmpUser.isNormal = false;
              }
              tmpUserList.push(tmpUser);
            });
            this.userList = tmpUserList;
          })
        },
        methods:{
          blockAccount:function (item) {
            item.state = "blocked";
            item.isNormal = false;
            this.$axios.get(
              "http://localhost:8080/blockUser/"+item.user_id,
            ).then(result => {
              console.log(result);
            })
          },
          unBlockAccount:function (item) {
            item.state = "normal";
            item.isNormal = true;
            this.$axios.get(
              "http://localhost:8080/unBlockUser/"+item.user_id,
            ).then(result => {
              console.log(result);
            })
          }
        }
    }
</script>

<style scoped>
  .container{
    width: 90%;
    height: 650px;
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
    width: 50%;
  }
</style>
