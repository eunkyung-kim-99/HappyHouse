<template>
  <div>
    <b-container>
      <div>안녕하세요?</div>
      <apart-banner-view></apart-banner-view>
      <b-row>
        <b-col cols="8">
          <apart-search-bar-vue></apart-search-bar-vue>
          <apart-map-view></apart-map-view>
        </b-col>
        <b-col cols="4">
          <apart-right></apart-right>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import ApartRight from "@/components/apart/ApartRight.vue";
import ApartMapView from "@/components/apart/ApartMapView.vue";
import ApartBannerView from "../components/apart/ApartBannerView.vue";
import ApartSearchBarVue from "../components/apart/ApartSearchBar.vue";
export default {
  components: {
    ApartMapView,
    ApartSearchBarVue,
    ApartBannerView,
    ApartRight,
  },
};
</script>

<style></style>
