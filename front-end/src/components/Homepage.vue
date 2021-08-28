<template>
  <div style="min-width: 1380px;">
    <navigation />
    <container />
    <footbar />
  </div>
</template>

<script>
    import Navigation from "./Navigation";
    import Container from "./Container";
    import Footbar from "./Footer"

    export default {
        name: "Homepage",
        components: {Navigation, Container, Footbar}
    }
</script>

<style>
  @import "../assets/css/bootstrap.min.css";
</style>
