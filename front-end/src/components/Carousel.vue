<template>
  <div class="block">
    <el-carousel height="255px">
      <el-carousel-item v-for="(img,index) in imgList" :key="index">
        <h3 class="small"><img v-bind:src="img.url" alt="" style="width: 100%"></h3>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>


    export default {
        name: "carousel",
        data: function() {
          return {
            imgList: [
              {url: require('../assets/image/span2.jpg')},
              {url: require('../assets/image/span3.jpg')},
              {url: require('../assets/image/span4.jpg')},
              {url: require('../assets/image/span5.jpg')}
              ]
          }
        }
    }
</script>

<style scoped>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
