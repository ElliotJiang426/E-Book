<template>
  <nav class="navbar navbar-fixed-top navbar-inverse" style="width: 100%">
    <ul class="nav navbar-nav" id="navi" style="width: 100%">
      <li role="presentation" style=" padding: 15px;"><img alt="" src="../assets/image/bookicon.png" style="width: 25px;"/></li>
      <li role="presentation" style=" padding: 15px; color: white; font-size: 18px">E-BOOK</li>
      <li id="home" role="presentation" style="float: right" class="active"><router-link to="/home">HOME</router-link></li>
      <li id="cart" role="presentation" style="float: right"><router-link to="/counter/cart">CART</router-link></li>
      <li id="account" role="presentation" class="dropdown" style="float: right">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">ACCOUNT<span class="caret"></span></a>
        <ul class="dropdown-menu" id="actlist">
          <li>
            <router-link to="/account/information">
              <el-avatar :size="25" :src="this.$client.avatar" fit="fill"></el-avatar>{{this.$client.nickname}}
              <i class="el-icon-s-check"></i>
            </router-link>
          </li>
          <li role="separator" class="divider"></li>
          <li><router-link to="/account/orderlist">My Orders</router-link></li>
          <li><router-link to="/account/balance">My Balance: ￥{{this.$client.balance.toFixed(2)}}</router-link></li>
          <li><router-link to="/account/likelist">My Favorite</router-link></li>
          <li v-show="this.isAdmin" role="separator" class="divider"></li>
          <li v-show="this.isAdmin"><router-link to="/account/userManagement">Admin Settings</router-link></li>
          <li role="separator" class="divider"></li>
          <li><a href="#" style="color: red" @click="logout">Log Out</a></li>
        </ul>
      </li>
    </ul>
<!--    <div class="col-lg-3" style="float: right; margin: 10px">-->
<!--      <div class="input-group">-->
<!--        <input type="text" class="form-control" placeholder="Search for...">-->
<!--        <span class="input-group-btn">-->
<!--        <button class="btn btn-default" type="button">Search</button>-->
<!--      </span>-->
<!--      </div>&lt;!&ndash; /input-group &ndash;&gt;-->
<!--    </div>-->
  </nav>
</template>

<script>
    import $ from "jquery";

    export default {
        name: "navigation",
        data:function () {
          return{
            user:this.$client,
            circleUrl:this.$client.avatar,
            isAdmin: false,
          }
        },
        methods: {
          logout: function () {
            this.$client.user_id = 0;
            this.$router.push("/login");
          }
        },
        mounted() {
          if(this.$client.user_type === 0) this.isAdmin = true;
          $(document).ready(function () {
            $('#navi > li').click(function (e) {
              e.preventDefault();
              $('#navi > li').removeClass('active');
              $(this).addClass('active');
            });
            $('#actlist > li').click(function (e) {
              e.preventDefault();
              $('#navi > li').removeClass('active');
              $('#account').addClass('active');
            })
          });
        },
    }
</script>

<style>
  .dropdown:hover>.dropdown-menu {
    display: block;
  }

  .dropdown>.dropdown-toggle:active {
    pointer-events: none;
  }
</style>
