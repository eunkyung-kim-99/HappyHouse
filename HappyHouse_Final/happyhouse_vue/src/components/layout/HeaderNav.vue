<!-- @format -->

<template>
  <v-app-bar class="pa-2 mb-4" color="transparent" flat>
    <div class="align-center">
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
        <router-link to="/apart">
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
        <router-link to="/userinter">
          <v-btn
            small
            plain
            text
            style="text-transform: none"
            class="font-head weight-500 white--text"
            ><v-icon size="18">mdi-star</v-icon>
            <span class="mb-n1">즐겨찾기</span></v-btn
          >
        </router-link>
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
        <router-link to="/community" class="link">
          <v-btn
            small
            plain
            text
            style="text-transform: none"
            class="font-head weight-500 white--text"
            ><v-icon size="18">mdi-plus</v-icon>
            <span class="mb-n1">커뮤니티</span></v-btn
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
          @click="onClickLogout"
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
            <span class="mb-n1"> {{ loginUser.id }}님 </span>
          </v-btn>
        </router-link>
        <div>
          <v-avatar size="30" v-if="profile">
            <img :src="profile" alt="프로필 사진" />
          </v-avatar>
          <v-avatar size="30" v-else>
            <img src="@/assets/images/notloginimg.png" alt="비로그인 이미지" />
          </v-avatar>
        </div>
      </div>
    </div>

    <v-app-bar-nav-icon class="white--text d-flex d-sm-none" />
  </v-app-bar>
</template>

<script>
import { mapState, mapMutations } from "vuex";
export default {
  methods: {
    ...mapMutations([
      "SET_IS_LOGIN",
      "LOGIN_USER",
      "SET_GUGUN_CODE",
      "SET_SIDO_CODE",
      "SET_DONG_CODE",
      "SET_APART_LIST",
    ]),

    resetapt() {
      this.SET_GUGUN_CODE(null);
      this.SET_SIDO_CODE(null);
      this.SET_DONG_CODE(null);
      this.SET_APART_LIST(null);
    },
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.LOGIN_USER(null);
      sessionStorage.removeItem("accesstoken");
      alert("로그아웃 되었습니다");
      if (this.$route.path != "/") this.$router.push({ name: "Home" });
    },
  },
  computed: {
    ...mapState(["loginUser", "isLogin", "userInfo", "profile"]),
  },
  created() {
    console.log("유저이미지" + this.profile);
  },
};
</script>

<style></style>
