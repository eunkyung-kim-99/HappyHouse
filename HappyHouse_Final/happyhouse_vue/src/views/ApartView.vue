<!-- @format -->

<template>
  <div class="bg">
    <header-nav></header-nav>
    <router-view></router-view>
  </div>
</template>

<script>
import HeaderNav from "../components/layout/HeaderNav.vue";

export default {
  components: { HeaderNav },
  name: "apartview",
};
</script>

<style scoped></style>
