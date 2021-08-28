<template>
  <div class="col-md-9" style="min-height: 640px">
    <carousel/>
    <showcase-table v-bind:category="this.category"/>
  </div>
</template>

<script>
    import Carousel from "./Carousel"
    import ShowcaseTable from "./ShowcaseTable";

    export default {
        name: "browser",
        components: {Carousel, ShowcaseTable},
        props:["category"],
        // mounted() {
        //   console.log(this.category);
        // }
    }
</script>

<style scoped>

</style>
