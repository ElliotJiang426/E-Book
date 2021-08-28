<template>
  <div id="app">
<!--    <img src="./assets/logo.png">-->
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
}
</script>

<style>
  @import "./assets/css/bootstrap.min.css";
  .loginbox .el-form-item__label{
    color:white;
  }

</style>
