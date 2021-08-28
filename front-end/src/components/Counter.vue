<template>
  <div class="base">
    <navigation></navigation>
    <div style="border-bottom: 2px solid #2b669a">
      <div class=" container" style="padding-top: 70px; padding-bottom: 20px;" align="center">
        <img alt="" src="../assets/image/ad.jpg" /><br/>
        <img class="icon" alt="" :src="icons[active]" />
        <el-steps class="steps" :active="active" finish-status="success" simple>
          <el-step title="My Cart"></el-step>
          <el-step title="Fill the Order"></el-step>
          <el-step title="Complete "></el-step>
        </el-steps>
      </div>
    </div>
    <router-view v-on:toOrder="goOrder()" v-on:toComplete="goComplete()" v-on:toCart="goCart()"></router-view>
  </div>
</template>

<script>
  import Navigation from "./Navigation";
  import $ from "jquery";
    export default {
        name: "Counter",
        components:{Navigation},
        data:function(){
          return{
            active:0,
            icons:[
              require('../assets/image/icons/cart.png'),
              require('../assets/image/icons/order.png'),
              require('../assets/image/icons/complete.png'),
            ],

          }
        },
        mounted() {
          $(document).ready(function () {
              $('ul > #home').removeClass('active');
              $('ul > #cart').addClass('active');
          });
        },
        methods: {
          goOrder() {
            this.active = 1;
          },
          goCart() {
            this.active = 0;
          },
          goComplete(){
            this.active = 2;
          }
        }
    }
</script>

<style scoped>
  .steps{
    width: 60%;
    margin: 20px 90px 0 0;
    float: right;
    padding-right: 10px;
    padding-left: 10px;
  }
  .icon{
    width: 50px;
    height: auto;
    margin-top: 20px;
  }
</style>
