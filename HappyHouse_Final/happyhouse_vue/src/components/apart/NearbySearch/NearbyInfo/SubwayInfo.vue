<!-- @format -->

<template>
  <div>
    <v-container fluid v-if="SW8" class="wrapper scroll">
      <v-row>
        <v-col v-for="i in 9" :key="i" cols="12" md="4">
          <subway-item-vue
            v-if="SW8[i]"
            :stationinfo="SW8[i]"
          ></subway-item-vue>
        </v-col>
      </v-row>
    </v-container>

    <div id="loadingdata" v-else-if="finishsearch">
      <div id="loadingdatatext">가까운 지하철역을 찾을 수 없습니다...</div>
    </div>
    <div id="loadingdata" v-else>
      <div class="text-center">
        <b-spinner label="Spinning"></b-spinner>
        <b-spinner type="grow" label="Spinning"></b-spinner>
        <b-spinner variant="primary" label="Spinning"></b-spinner>
        <b-spinner variant="primary" type="grow" label="Spinning"></b-spinner>
        <b-spinner variant="success" label="Spinning"></b-spinner>
        <b-spinner variant="success" type="grow" label="Spinning"></b-spinner>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import SubwayItemVue from "./items/SubwayItem.vue";
export default {
  computed: {
    ...mapState(["SW8", "finishsearch"]),
  },
  components: {
    SubwayItemVue,
  },
};
</script>

<style scoped>
#loadingdata {
  width: auto;
  height: 600px;
  text-align: center;
  line-height: 50px;
  margin: 0 auto;
}
.wrapper {
  height: 500px;
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
