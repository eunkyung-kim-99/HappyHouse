<!-- @format -->

<template>
  <div class="wrapper scroll">
    <v-container fluid v-if="cafes.length">
      <v-row>
        <cafe-list-item v-if="cafes[0]" :cafeinfo="cafes[0]"></cafe-list-item>
        <v-col v-for="i in 8" :key="i" cols="12" md="4">
          <cafe-list-item v-if="cafes[i]" :cafeinfo="cafes[i]"></cafe-list-item>
        </v-col>
      </v-row>
    </v-container>
    <v-container fluid v-else> 카페 목록이 없습니다. </v-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
import CafeListItem from "./items/CafeListItem.vue";
export default {
  components: {
    CafeListItem,
  },
  mounted() {},
  computed: {
    ...mapState(["cafes"]),
  },
};
</script>

<style></style>
