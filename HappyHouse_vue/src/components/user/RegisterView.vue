<!-- @format -->

<template>
  <b-container class="bv-example-row mt-lg-5">
    <br /><br /><br />
    <h3 class="white--text">회원가입</h3>
    <b-row>
      <b-col>
        <b-card class="">
          <b-form>
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
            <b-form-group label="이름:">
              <b-form-input
                id="name"
                v-model="name"
                type="text"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일:">
              <b-form-input
                id="email"
                v-model="email"
                type="text"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group label="전화번호">
              <b-form-input
                id="phonenumber"
                v-model="phonenumber"
                type="text"
                required
              ></b-form-input>
            </b-form-group>
            <b-button variant="primary" @click="regist" class="m-1"
              >등록</b-button
            >
            <b-button @click="movelogin" variant="danger" class="m-1"
              >로그인</b-button
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
  name: "RegisterView",
  data() {
    return {
      id: "",
      password: "",
      name: "",
      email: "",
      phonenumber: "",
    };
  },
  methods: {
    regist() {
      if (
        this.id === "" ||
        this.password === "" ||
        this.name === "" ||
        this.email === "" ||
        this.phonenumber === ""
      ) {
        alert("모든 내용을 입력해주세요");
        return;
      }

      let user = {
        id: this.id,
        password: this.password,
        name: this.name,
        email: this.email,
        phonenumber: this.phonenumber,
      };

      this.$store.dispatch("createUser", user);
      this.$router.push({ name: "userlogin" });
    },
    movelogin() {
      this.$router.push({ name: "userlogin" });
    },
  },
  computed: {
    ...mapState(["randomUser"]),
  },
};
</script>

<style></style>
