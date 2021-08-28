<template>
    <Bookdetails :book="this.book"></Bookdetails>
</template>

<script>
  import Bookdetails from "../components/Bookdetails";
    export default {
        name: "Bookview",
        components: {Bookdetails},
        data:function () {
            return{
              book: null,
          }
        },
        created() {
          fetch("http://localhost:8080/getBook/"+this.$route.query.bookId)
            .then(response => response.json())
            .then(data => {
              this.book = data;
              console.log(this.book);
            }).catch(function (ex) {
            console.log('parsing failed', ex)
          })
        },
        mounted() {
          console.log(this.book.name);
        }
    }
</script>

<style scoped>

</style>
