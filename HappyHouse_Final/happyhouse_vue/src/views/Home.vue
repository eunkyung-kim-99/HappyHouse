<!-- @format -->

<template>
  <div class="bg">
    <header-nav></header-nav>

    <v-row style="height: 70%" no-gutters align="center" justify="center">
      <v-col cols="12" xl="6" lg="6"> </v-col>
      <v-col cols="12" xl="6" lg="6">
        <p
          class="font-1 text-align white--text"
          style="line-height: 1; font-weight: 750"
        >
          FullHouse
        </p>
        <p class="font-2 text-align white--text" style="font-weight: 400">
          FullHouse 홈페이지에 오신 걸 환영합니다. <br />원하는 아파트 정보를
          조회하실 수 있습니다.
        </p>
        <p class="text-align">
          <router-link to="/apart">
            <v-btn
              rounded
              class="font-3 btn"
              color="red"
              elevation="5"
              dark
              x-large
              style="text-transform: none; font-weight: 850; width: 340px"
              >아파트 실거래가 검색 바로가기</v-btn
            >
          </router-link>
        </p>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import HeaderNav from "../components/layout/HeaderNav.vue";

export default {
  components: { HeaderNav },
  name: "Home",
};
</script>
<style>
.bg {
  background: linear-gradient(
      180deg,
      rgba(0, 0, 0, 0.5) 1%,
      rgba(0, 0, 0, 0.273638) 51.03%,
      rgba(0, 0, 0, 0.5) 100%
    ),
    url("../assets/images/house3.jpg");
  height: 110vh !important;
  background-size: cover;
}
/* .text-align {
  text-align: right;
} */
/* @media screen and (max-width: 600px) {
  .text-align {
    text-align: left;
  }
} */
</style>
