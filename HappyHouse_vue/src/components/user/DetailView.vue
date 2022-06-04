<!-- @format -->

<template>
  <b-container class="bv-example-row mt-lg-5">
    <br /><br /><br />
    <h3 class="white--text">마이페이지</h3>
    <b-row>
      <b-col>
        <b-card class="">
          <b-form>
            <b-form-group label="아이디:">
              <b-form-input
                readonly
                id="id"
                v-model="loginUser.id"
                type="text"
                required
              ></b-form-input>
            </b-form-group>

            <b-form-group label="비밀번호:">
              <b-form-input
                id="password"
                v-model="loginUser.password"
                type="text"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:">
              <b-form-input
                id="name"
                v-model="loginUser.name"
                type="text"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일:">
              <b-form-input
                id="email"
                v-model="loginUser.email"
                type="text"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group label="전화번호">
              <b-form-input
                id="phonenumber"
                v-model="loginUser.phonenumber"
                type="text"
                required
              ></b-form-input>
            </b-form-group>
            <b-button variant="primary" @click="updateUser" class="m-1"
              >수정</b-button
            >
            <b-button @click="deleteUser" variant="danger" class="m-1"
              >삭제</b-button
            >
          </b-form>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "userdetail",
  // data() {
  //   return {
  //     user: null,
  //   };
  // },
  methods: {
    updateUser() {
      if (
        this.loginUser.id === "" ||
        this.loginUser.password === "" ||
        this.loginUser.name === "" ||
        this.loginUser.email === "" ||
        this.loginUser.phonenumber === ""
      ) {
        alert("모든 내용을 입력해주세요");
        return;
      }
      this.$store.dispatch("updateUser", this.loginUser);
    },
    deleteUser() {
      alert("회원을 탈퇴합니다");
      this.$store.dispatch("deleteUser", this.loginUser.id);
      this.$store.commit("LOGOUT");
    },
  },
  computed: {
    ...mapState(["loginUser"]),
    //...mapGetters(["loginUser"]),
  },
  // created() {
  //   this.user = loginUser();
  // },
  // created() {
  //   const pathName = new URL(document.location).pathname.split("/");
  //   const id = pathName[pathName.length - 1];

  //   this.$store.dispatch("setUser", id);
  // },
};
</script>
