<!-- @format -->

<template>
  <div>
    <v-card class="">
      <v-btn
        color="orange darken-2 float-right mt-2 mr-2"
        dark
        to="/apart/index"
      >
        <b-icon icon="arrow-left-short" scale="2" variant=""></b-icon>
      </v-btn>

      <div class="float-right mr-5 mt-4">
        {{ selSidoCode }} {{ selGugunCode }} {{ selDongCode }}
        {{ target.aptName }}
      </div>
      <v-tabs color="deep-purple accent-4" left>
        <v-tab>지하철역</v-tab>
        <v-tab>음식점</v-tab>
        <v-tab>카페</v-tab>

        <v-tab-item>
          <v-card flat>
            <v-card-text><subway-info-vue></subway-info-vue></v-card-text>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card flat>
            <v-card-text
              ><restaurant-info-vue></restaurant-info-vue
            ></v-card-text>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card flat>
            <v-card-text><cafe-info-vue></cafe-info-vue></v-card-text>
          </v-card>
        </v-tab-item>
      </v-tabs>
    </v-card>
  </div>
</template>

<script>
import RestaurantInfoVue from "./NearbyInfo/RestaurantInfo.vue";
import CafeInfoVue from "./NearbyInfo/CafeInfo.vue";
import SubwayInfoVue from "./NearbyInfo/SubwayInfo.vue";
import { mapState } from "vuex";

export default {
  name: "ApartNearbyInfo",
  components: {
    SubwayInfoVue,
    RestaurantInfoVue,
    CafeInfoVue,
  },
  data() {
    return {
      model: "subway",
    };
  },
  computed: {
    ...mapState(["selSidoCode", "selGugunCode", "selDongCode", "target"]),
  },
};
</script>

<style scoped>
.v-application ul {
  padding-left: 0;
}
.infoboxes {
  background-color: white;
}
</style>
