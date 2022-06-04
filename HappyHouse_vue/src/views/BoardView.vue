<!-- @format -->

<template>
  <div>
    <div class="bg">
      <v-container style="height: 100%">
        <v-row style="height: 70%" no-gutters align="center" justify="center">
          <div class="board_bg">
            <router-view></router-view></div
        ></v-row>
      </v-container>
    </div>
  </div>
</template>
<script>
export default {
  name: "BoardView",
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
  background: rgba(256, 256, 256, 0.8) 30%;
}
</style>
