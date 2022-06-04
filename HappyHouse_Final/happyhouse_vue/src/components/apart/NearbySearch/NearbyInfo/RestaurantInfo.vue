<!-- @format -->

<template>
  <div class="wrapper scroll">
    <v-container fluid v-if="foods.length">
      <v-row>
        <v-col v-for="i in 9" :key="i" cols="12" md="4">
          <restaurant-list-item
            v-if="foods[i]"
            :foodinfo="foods[i]"
          ></restaurant-list-item>
        </v-col>
      </v-row>
    </v-container>
    <v-container fluid v-else>음식점 목록이 없습니다. </v-container>
  </div>
  <!-- 
  <div class="infoboxes" v-if="foods">
    <b-card-group deck>
      <restaurant-list-item
        v-if="foods[0]"
        :foodinfo="foods[0]"
      ></restaurant-list-item>
      <restaurant-list-item
        v-if="foods[1]"
        :foodinfo="foods[1]"
      ></restaurant-list-item>
      <restaurant-list-item
        v-if="foods[2]"
        :foodinfo="foods[2]"
      ></restaurant-list-item>
    </b-card-group>
    <b-card-group deck>
      <restaurant-list-item
        v-if="foods[3]"
        :foodinfo="foods[3]"
      ></restaurant-list-item>
      <restaurant-list-item
        v-if="foods[4]"
        :foodinfo="foods[4]"
      ></restaurant-list-item>
      <restaurant-list-item
        v-if="foods[5]"
        :foodinfo="foods[5]"
      ></restaurant-list-item>
    </b-card-group>
    <br /> 
  </div>-->
</template>

<script>
import { mapState } from "vuex";
// import RestaurantListItem from "@/components/apart/NearbySearch/NearbyInfo/items/RestaurantListItem.vue";
import RestaurantListItem from "./items/RestaurantListItem.vue";
export default {
  components: {
    RestaurantListItem,
  },
  mounted() {},
  computed: {
    ...mapState(["foods"]),
  },
};
</script>

<style></style>
