<template>
    <Loginform></Loginform>
</template>

<script>
  import Loginform from "../components/Loginform";
    export default {
        name: "Loginview",
        components: {Loginform},
        beforeDestroy() {
          if(this.$client.user_id === 0){
            this.$message.error("You've not login!")
            this.$router.push("/login");
          }
        }
    }
</script>

<style scoped>

</style>
