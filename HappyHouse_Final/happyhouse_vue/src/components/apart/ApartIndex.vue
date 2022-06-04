<!-- @format -->

<template>
  <div>
    <div class="header-map"></div>
    <apart-map-view></apart-map-view>
  </div>
</template>

<script>
// import ApartRight from "@/components/apart/ApartRight.vue";
import ApartMapView from "@/components/apart/ApartMapView.vue";
export default {
  data() {
    return {
      modalonoff: false,
    };
  },
  components: {
    ApartMapView,
    // ApartSearchBarVue,

    // ApartRight,
  },
  methods: {
    onmodal() {
      this.modalonoff = true;
    },
    offmodal() {
      this.modalonoff = false;
    },
  },
  mounted() {},
};
</script>

<style>
.v-application--wrap {
  background: linear-gradient(
      180deg,
      rgba(0, 0, 0, 0.5) 1%,
      rgba(0, 0, 0, 0.273638) 51.03%,
      rgba(0, 0, 0, 0.5) 100%
    ),
    url("../../assets/images/house3.jpg");
  height: 100% !important;
  background-size: cover;
}
.header-map {
  background-color: rgb(54, 54, 54);
}
</style>
