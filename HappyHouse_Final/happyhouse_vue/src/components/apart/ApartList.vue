<!-- @format -->

<template>
  <div style="height: 100%">
    <apart-name-search-vue></apart-name-search-vue>
    <v-divider></v-divider>
    <template v-if="selDongCode">
      <template v-if="aparts.length" class="border">
        <div class="wrapper scroll">
          <apart-list-item-vue
            v-for="(apart, index) in aparts"
            :key="index"
            :apart="apart"
          ></apart-list-item-vue>
        </div>
      </template>
      <h3 v-else>
        <div>검색 결과가 없습니다.</div>
      </h3>
    </template>
    <h3 v-else>지역을 선택해주세요.</h3>
  </div>
</template>

<script>
import "@/css/fonts.css";

import ApartListItemVue from "./ApartListItem.vue";
import ApartNameSearchVue from "./ApartNameSearch.vue";
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState(["aparts", "selDongCode"]),
  },
  components: {
    ApartNameSearchVue,
    ApartListItemVue,
  },
};
</script>

<style scoped>
h3 {
  color: rgb(120, 120, 120);
  text-align: center;
  padding: 60px;
}
.wrapper {
  height: 700px;
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
