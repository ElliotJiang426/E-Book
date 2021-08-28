<template>
  <div>
    <navigation></navigation>
    <div class=" container" style="padding-top: 70px; padding-bottom: 50px;">
      <sidebar v-bind:avatar="this.avatar" style="margin-top: 5px"/>
      <router-view v-on:userUpdated="userUpdated"></router-view>
    </div>
  </div>
</template>

<script>
  import Sidebar from './Account/Sidebar';
  import Navigation from "./Navigation";

    export default {
        name: "account",
        data:function(){
          return{
            avatar:this.$client.avatar,
          }
        },
        components: {Navigation, Sidebar},
        methods:{
          userUpdated:function () {
            this.avatar = this.$client.avatar;
          }
        },
    }
</script>

<style scoped>

</style>
