<!-- @format -->

<template>
  <div class="bg">
    <header-nav></header-nav>
    <div>
      <v-row no-gutters align="center" justify="center">
        <div class="board_bg">
          <router-view></router-view></div
      ></v-row>
    </div>
  </div>
</template>
<script>
import HeaderNav from "../components/layout/HeaderNav.vue";

export default {
  components: { HeaderNav },
  name: "CommunityView",
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
.board_bg {
  width: 80%;
  background: rgba(256, 256, 256, 0.93) 60%;
}
</style>
