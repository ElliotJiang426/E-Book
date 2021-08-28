<template>
   <div class="col-md-9">
      <div v-for="book in likeBookList" :key="book.bookId" v-show="!empty">
        <showcase v-bind:book="book"></showcase>
      </div>
      <div v-show="empty">
        <el-alert
          title="EMPTY!"
          description="Your favorite bookList is empty! Go and add some book into it!"
          type="info"
          :closable="false"
          show-icon>
        </el-alert>
      </div>
   </div>
</template>
<script>
  import Showcase from '../Showcase'
    export default {
        name: "likelist",
        components:{Showcase},
        data:function () {
          return{
            likeBookList:[],
            empty:null
          }
        },
        beforeMount() {
          fetch("http://localhost:8080/getFavorites/"+this.$client.user_id
          ).then(response => response.json()
          ).then(data => {
            console.log(data);
            this.empty = data.length === 0;
            this.likeBookList = data;
          })
        }
    }
</script>

<style scoped>

</style>
