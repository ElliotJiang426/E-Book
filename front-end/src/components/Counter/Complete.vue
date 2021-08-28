<template>
  <el-alert
    title="SUCCESS"
    type="success"
    show-icon
    :closable="false"
    center
    description="You have make a order successfully, the page will turn to home soon."
    style="width: 400px; margin:auto; height: 200px;">
  </el-alert>
</template>

<script>
    export default {
        name: "Complete",
        mounted() {
          setTimeout(this.goHome, 5000);
        },
        methods:{
          goHome:function () {
              this.$router.push('/home');
          }
        }
    }
</script>

<style scoped>

</style>
