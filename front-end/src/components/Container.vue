<template>
  <div class=" container" style="padding-top: 70px; padding-bottom: 50px;">
    <categories v-on:categoryChange="changeCategory"/>
    <browser v-bind:category="this.category"/>
    <el-backtop></el-backtop>
  </div>
</template>

<script>
  import Categories from "./Categories";
  import Browser from "./Browser";

    export default {
        name: "container",
        components: {Browser, Categories},
        data:function () {
          return {
            category:"hot"
          }
        },
        methods:{
            changeCategory:function (data) {
              console.log(this.category);
              this.category = data;
              console.log(this.category);
            }
        }

    }
</script>

<style scoped>

</style>
