<!-- @format -->

<template>
  <div class="bg">
    <v-container style="height: 100%">
      <v-row style="height: 80%" no-gutters align="center" justify="center">
        <div class="board_bg">
          <!-- <b-container class="bv-example-row mt-3 text-center"> -->
          <router-view></router-view>
          <!-- </b-container> -->
        </div>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
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
