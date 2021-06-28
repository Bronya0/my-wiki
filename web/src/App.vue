<template>
  <a-layout  >
    <Header></Header>
    <router-view/>
    <Footer></Footer>
  </a-layout>
</template>

<script lang="ts">
  import { defineComponent } from 'vue';
  import Header from "@/components/Header.vue";
  import Footer from "@/components/Footer.vue";

  export default defineComponent({
    name: 'app',
    components:{
      Header,
      Footer,
    },
  });
</script>

<style>


</style>
