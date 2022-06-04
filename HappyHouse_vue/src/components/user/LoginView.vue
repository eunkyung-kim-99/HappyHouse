<!-- @format -->

<template>
  <b-container class="bv-example-row">
    <h3 class="white--text">로그인</h3>
    <b-row>
      <b-col cols="">
        <b-card class="">
          <b-form class="">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group label="아이디:">
              <b-form-input
                id="id"
                v-model="id"
                type="text"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:">
              <b-form-input
                id="password"
                v-model="password"
                type="text"
                required
              ></b-form-input>
            </b-form-group>
            <b-button type="button" variant="primary" class="m-1" @click="login"
              >로그인</b-button
            >
            <b-button
              type="button"
              variant="success"
              class="m-1"
              @click="movePage"
              >회원가입</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "LoginView",
  data() {
    return {
      isLoginError: false,
      user: {
        id: "",
        password: "",
      },
    };
  },
  methods: {
    confirm() {
      alert("로그인!!!");
    },
    login() {
      if (this.id === "" || this.password === "") {
        alert("모든 내용을 입력해주세요");
        return;
      }
      let user = {
        id: this.id,
        password: this.password,
      };
      this.$store.dispatch("loginUser", user);
    },
    movePage() {
      this.$router.push({ name: "userregister" });
    },
  },
};
</script>

<style></style>
