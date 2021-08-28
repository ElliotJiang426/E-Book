<template>
  <div id="outer" class="col-md-3" style="box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)">
    <div class="panel panel-default" style="margin-bottom: 0; border: 0;">
      <ul id="cate" class="nav nav-pills nav-stacked panel-body" style="padding: 0" v-on:click="categoryChange($event)">
        <li role="presentation" class="active" ><a href="#" id="hot">HOT</a></li>
        <li role="presentation" ><a href="#" id="onsale">ON SALE</a></li>
        <li role="presentation"><a href="#" id="novel">NOVEL</a></li>
        <li role="presentation"><a href="#" id="literature">LITERATURE/ART</a></li>
        <li role="presentation"><a href="#" id="economy">FINANCIAL/ECONOMY</a></li>
        <li role="presentation"><a href="#" id="biography">BIOGRAPHY</a></li>
        <li role="presentation"><a href="#" id="history">HISTORY</a></li>
        <li role="presentation"><a href="#" id="science">SCIENCE</a></li>
        <li role="presentation"><a href="#" id="teenage">TEENAGE</a></li>
        <li role="presentation"><a href="#" id="other">OTHER</a></li>
      </ul>
    </div>
  </div>
</template>

<script>
  import $ from 'jquery'

    export default {
        name: "categories",
        mounted() {
          $(document).ready(function () {
            $('#cate > li').click(function (e) {
              e.preventDefault();
              $('#cate > li').removeClass('active');
              $(this).addClass('active');
              //this.allBooks.category = $(this).id;
            });
          });
        },
        methods:{
          categoryChange:function (e) {
            console.log(e.target.id);
            this.$emit("categoryChange", e.target.id);
          }
        }
    }
</script>

<style scoped>
</style>
