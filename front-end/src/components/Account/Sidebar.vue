<template>
  <div id="outer" class="col-md-3" style="box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)">
    <div class="panel panel-default" style="margin-bottom: 0; border: 0;">
      <ul id="account" class="nav nav-pills nav-stacked panel-body" style="padding: 0" >
        <li class="normalOption" role="presentation">
          <router-link to="/account/information">
            <div style="margin: 20px;text-align: center">
              <el-avatar shape="square" :size="100" fit="fill" :src="avatar"></el-avatar>
              <br/>
              <h3 class="title">{{ nickname }}</h3>
            </div>
          </router-link>
        </li>
        <li class="normalOption" role="presentation"><a @click="goOrders()">My Orders</a></li>
        <li class="normalOption" role="presentation"><router-link to="/account/likelist">My Favorites</router-link></li>
        <li class="normalOption" role="presentation"><router-link to="/account/balance">My Balance</router-link></li>
        <li class="normalOption" role="presentation"><router-link to="/account/myStatics">My Statics</router-link></li>
        <li role="presentation" v-show="isAdmin" id="dragDown">
          <a @click="viewOptions">Admin Options<i class="el-icon-arrow-down" style="float: right"></i></a>
          <el-collapse-transition>
            <ul v-show="showAdminOptions" class="nav nav-pills nav-stacked panel-body" id="subOptions">
              <li><router-link class="transition-box" to="/account/userManagement">Users Management</router-link></li>
              <li><router-link class="transition-box" to="/account/bookManagement">Books Management</router-link></li>
              <li><a class="transition-box" @click="goAdminOrders()">Orders Management</a></li>
              <li><router-link class="transition-box" to="/account/statics">Statics For Admin</router-link></li>
            </ul>
          </el-collapse-transition>
        </li>
        <li role="presentation"><a href="#" style="color: red" @click="logout">Log Out</a></li>
      </ul>
    </div>
  </div>
</template>

<script>
  import $ from 'jquery'
    export default {
        name: "sidebar",
        props:["avatar"],
        data: function(){
          return{
            nickname:this.$client.nickname,
            isAdmin:false,
            showAdminOptions: false,
          }
        },
        mounted() {
          if(this.$client.user_type === 0) this.isAdmin = true;
          $(document).ready(function () {
            $('#account > .normalOption').click(function (e) {
              e.preventDefault();
              $('#account > .normalOption').removeClass('active');
              $('#account > #dragDown').removeClass('active');
              $('#account > #dragDown > #subOptions > li').removeClass('active');
              $(this).addClass('active');
            });
            $('#account > #dragDown > #subOptions > li').click(function (e) {
              e.preventDefault();
              $('#account > li').removeClass('active');
              $('#account > #dragDown > #subOptions > li').removeClass('active');
              $(this).addClass('active');
            });
          });
          console.log(this.$client.avatar);
        },
        methods:{
          logout:function () {
            this.$client.user_id = 0;
            this.$router.push("/login");
          },
          viewOptions:function () {
            this.showAdminOptions = !this.showAdminOptions;
          },
          goOrders:function () {
            this.$router.push({
                path:"/account/orderlist",
                query: {isAdmin : false}
            });
          },
          goAdminOrders:function () {
            this.$router.push({
                path:"/account/orderlist",
                query: {isAdmin: true}
            })
          }
        }
    }
</script>

<style scoped>

</style>
