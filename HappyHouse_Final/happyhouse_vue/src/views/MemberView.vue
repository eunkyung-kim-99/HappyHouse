<!-- @format -->

<template>
  <div class="bg">
    <header-nav></header-nav>
    <v-container>
      <v-row no-gutters align="center" justify="center">
        <!-- <b-container class="bv-example-row mt-3 text-center"> -->
        <router-view></router-view>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import HeaderNav from "../components/layout/HeaderNav.vue";
export default {
  components: { HeaderNav },
  name: "MemberView",
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
