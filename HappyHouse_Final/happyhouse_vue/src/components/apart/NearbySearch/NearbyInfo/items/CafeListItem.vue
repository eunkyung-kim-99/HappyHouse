<!-- @format -->

<template>
  <v-card class="mx-auto" max-width="344">
    <img v-if="cafeinfo.img" :src="cafeinfo.img" />
    <img v-else src="@/assets/images/noimg.png" />
    <v-card-title> {{ cafeinfo.title }}</v-card-title>

    <v-card-subtitle>
      <div>
        <div class="text-xs-center">
          <v-rating
            v-model="cafeinfo.point"
            readonly
            background-color="orange lighten-3"
            color="orange"
          ></v-rating>
          <div>{{ cafeinfo.point }}</div>
        </div>
      </div>
      <div>
        {{ cafeinfo.etc }}
      </div>
      <div>리뷰 {{ cafeinfo.review_count }}</div>
      <a :href="cafeinfo.link">{{ cafeinfo.link }}</a>
    </v-card-subtitle>
  </v-card>
</template>

<script>
export default {
  name: "CafeListItem",
  props: {
    cafeinfo: Object,
  },
};
</script>

<style scoped>
img {
  width: 100%;
}
</style>
