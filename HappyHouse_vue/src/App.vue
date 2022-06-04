<!-- @format -->

<template>
  <v-app>
    <v-app-bar class="pa-2" fixed color="transparent" flat>
      <div class="d-flex align-center">
        <!-- <p><v-img src="./assets/images/Logo.png"></v-img></p> -->
      </div>

      <div class="d-none d-sm-flex">
        <div style="margin-left: 10%" class="d-flex align-center">
          <router-link to="/" class="link">
            <v-btn
              small
              plain
              text
              style="text-transform: none"
              class="font-head weight-500 white--text"
              ><v-icon size="18">mdi-home</v-icon>
              <span class="mb-n1">홈</span></v-btn
            >
          </router-link>
          <router-link to="/apartindex">
            <v-btn
              small
              plain
              text
              style="text-transform: none"
              class="font-head weight-500 white--text"
              ><v-icon size="18">mdi-magnify</v-icon>
              <span class="mb-n1">아파트 실거래가 검색</span></v-btn
            >
          </router-link>
          <v-btn
            small
            plain
            text
            style="text-transform: none"
            class="font-head weight-500 white--text"
            ><v-icon size="18">mdi-plus</v-icon>
            <span class="mb-n1">주변상권</span></v-btn
          >
          <v-btn
            small
            plain
            text
            style="text-transform: none"
            class="font-head weight-500 white--text"
            ><v-icon size="18">mdi-star</v-icon>
            <span class="mb-n1">즐겨찾기</span></v-btn
          >

          <router-link to="/board" class="link">
            <v-btn
              small
              plain
              text
              style="text-transform: none"
              class="font-head weight-500 white--text"
              ><v-icon size="18">mdi-wallet-giftcard</v-icon>
              <span class="mb-n1">게시판</span></v-btn
            >
          </router-link>
        </div>
      </div>
      <v-spacer></v-spacer>
      <div v-if="!loginUser">
        <div class="d-none d-sm-flex">
          <router-link to="/user/register" class="link">
            <v-btn
              small
              plain
              text
              style="text-transform: none"
              class="font-head weight-500 white--text"
            >
              <span class="mb-n1"> 회원가입 </span>
              <v-avatar size="10" class=""></v-avatar>
            </v-btn>
          </router-link>

          <router-link to="/user" class="link">
            <v-btn
              small
              plain
              text
              style="text-transform: none"
              class="font-head weight-500 white--text"
            >
              <span class="mb-n1"> 로그인 </span>
              <v-avatar size="10" class=""></v-avatar>
            </v-btn>
          </router-link>
        </div>
      </div>
      <div v-if="loginUser">
        <div class="d-none d-sm-flex">
          <v-btn
            small
            plain
            text
            style="text-transform: none"
            class="font-head weight-500 white--text"
            @click="logout"
          >
            <span class="mb-n1"> 로그아웃 </span>
            <v-avatar size="10" class=""></v-avatar>
          </v-btn>

          <router-link to="/user/detail" class="link">
            <v-btn
              small
              plain
              text
              style="text-transform: none"
              class="font-head weight-500 white--text"
            >
              <span class="mb-n1"> 마이페이지 </span>
              <v-avatar size="10" class=""></v-avatar>
            </v-btn>
          </router-link>
        </div>
      </div>

      <v-app-bar-nav-icon class="white--text d-flex d-sm-none" />
    </v-app-bar>

    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import "./css/fonts.css";
import { mapState } from "vuex";
import router from "@/router";

export default {
  name: "App",

  data() {
    return {
      isloginview: false,
    };
  },
  methods: {
    logout() {
      alert("로그아웃되었습니다");
      this.$store.commit("LOGOUT");
      router.push({ name: "Home" });
    },
  },
  computed: {
    ...mapState(["loginUser"]),
  },
};
</script>
<style>
.weight-500 {
  font-weight: 500 !important;
}
</style>
