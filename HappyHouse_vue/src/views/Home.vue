<!-- @format -->

<template>
  <div>
    <div class="bg">
      <v-container style="height: 100%">
        <v-row style="height: 100%" no-gutters align="center" justify="center">
          <v-col cols="12" xl="6" lg="6"> </v-col>
          <v-col cols="12" xl="6" lg="6">
            <p
              class="font-1 text-align white--text"
              style="line-height: 1; font-weight: 750"
            >
              HappyHouse
            </p>
            <p class="font-2 text-align white--text" style="font-weight: 400">
              HappyHouse 홈페이지에 오신 걸 환영합니다. <br />원하는 아파트
              정보를 조회하실 수 있습니다.
            </p>
            <p class="text-align">
              <v-btn
                rounded
                class="font-3 btn"
                color="red"
                elevation="5"
                dark
                x-large
                style="text-transform: none; font-weight: 850; width: 340px"
                >아파트 실거래가 검색하러가기 -></v-btn
              >
            </p>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",

  components: {},
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
  height: 130vh !important;
  background-size: cover;
}
.btn {
  width: 340px;
}
.text-align {
  text-align: right;
}
@media screen and (max-width: 600px) {
  .text-align {
    text-align: left;
  }

  .btn {
    width: 200px !important ;
  }
}
</style>
