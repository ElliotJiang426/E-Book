<template>
  <button v-on:click="getBooks()">getBook</button>
</template>

<script>
    export default {
        name: "tmp",
        data:function () {
            return{
              book:null,
            }
        },
        methods:{
          getBooks : function() {
            return (
              fetch("http://localhost:8080/getBooks")
                .then(response => response.json())
                .then(data => {
                  // alert("data:" + data);
                  this.books = data;
                  console.log(data);
                }).catch(function (ex) {
                console.log('parsing failed', ex)
              })
            )}
        }
    }
</script>

<style scoped>

</style>
