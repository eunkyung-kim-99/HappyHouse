<!-- @format -->

<template>
  <v-app>
    <router-view />
    <footer-nav></footer-nav>
  </v-app>
</template>

<script>
import FooterNav from "./components/layout/FooterNav.vue";
import "./css/fonts.css";

export default {
  components: { FooterNav },
  name: "App",
};
</script>
<style>
.weight-500 {
  font-weight: 500 !important;
}
.wrapper {
  height: 620px;
  width: auto;
  overflow: hidden; /* .scroll 의 넘치는 내용을 감춤 */
}

.scroll {
  width: 100%;
  overflow-y: scroll;
  padding-right: 0px; /* 스크롤바 너비 */
  box-sizing: content-box;
}
</style>
