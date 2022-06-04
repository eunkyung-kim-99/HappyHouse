<!-- @format -->

<template>
  <v-card class="mx-auto" max-width="344">
    <img v-if="foodinfo.img" :src="foodinfo.img" />
    <img v-else src="@/assets/images/noimg.png" />
    <v-card-title> {{ foodinfo.title }}</v-card-title>

    <v-card-subtitle>
      <div>
        <div class="text-xs-center">
          <v-rating
            v-model="foodinfo.point"
            readonly
            background-color="orange lighten-3"
            color="orange"
          ></v-rating>
          <div>{{ foodinfo.point }}</div>
        </div>
      </div>
      <div>
        {{ foodinfo.etc }}
      </div>
      <div>리뷰 {{ foodinfo.review_count }}</div>
      <a :href="foodinfo.link">{{ foodinfo.link }}</a>
    </v-card-subtitle>
  </v-card>
</template>

<script>
// import { mapActions, mapMutations } from "vuex";
// import http from "@/api/http.js";
export default {
  name: "RestaurantListItem",
  props: {
    foodinfo: Object,
  },
};
</script>

<style scoped>
img {
  width: 100%;
}
</style>
