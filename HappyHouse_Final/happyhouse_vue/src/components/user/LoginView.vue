<!-- @format -->

<template>
  <div class="loginviewcon">
    <h4 class="white--text">로그인</h4>
    <b-card
      class="mb-3 loginfirstcard"
      img-src="https://search.pstatic.net/common/?src=http%3A%2F%2Fcafefiles.naver.net%2FMjAxODA0MjRfMjgx%2FMDAxNTI0NTU1ODkzMjA4.eWK4q4EfMNXHi70YpDuETc5_0-gecw2rZGpRN-X1QDwg.Ji6w4wN8z8hy_3GnLtEpdybzA-sNgtj3TNQJj_eCz1Eg.JPEG.sukbae7%2FexternalFile.jpg&type=sc960_832"
      img-alt="Card image"
      img-left
      img-width="400px"
    >
      <b-card class="loginviewcard">
        <b-container>
          <b-form class="">
            <div style="text-align: center; margin-bottom: 20px">
              <router-link to="/">
                <img src="@/assets/images/loginlogo.gif" />
              </router-link>
            </div>
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <!--  -->

            <v-simple-table class="p-3">
              <tbody>
                <tr>
                  <td width="25%"><strong> 아이디 </strong></td>
                  <td>
                    <b-form-input
                      id="id"
                      v-model="id"
                      type="text"
                      required
                    ></b-form-input>
                  </td>
                </tr>
                <tr>
                  <td><strong>비밀번호</strong></td>
                  <td>
                    <b-form-input
                      id="password"
                      v-model="password"
                      type="text"
                      required
                    ></b-form-input>
                  </td>
                </tr>
              </tbody>
            </v-simple-table>
            <!--  -->
            <b-button
              type="button"
              variant="primary"
              class="loginformbtn"
              @click="confirm"
              >로그인</b-button
            >
            <v-divider></v-divider>
            <br />
            <div style="text-align: center">
              <span>로그인하시면 더 많은 기능을 이용하실 수 있습니다.</span>
            </div>
          </b-form>
        </b-container>
      </b-card>
      <b-card>
        <div class="text-center">
          계정이 없으신가요?
          <span @click="movePage" class="loginviewregbtn">회원가입</span>
        </div>
      </b-card>
    </b-card>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
  name: "LoginView",

  data() {
    return {
      user: {
        id: "",
        password: "",
      },
    };
  },
  computed: {
    ...mapState(["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(["userConfirm", "getUserInfo"]),

    movePage() {
      this.$router.push({ name: "userregister" });
    },
    async confirm() {
      const user = {
        id: this.id,
        password: this.password,
      };
      console.log(user);
      await this.userConfirm(user);
      let token = sessionStorage.getItem("accesstoken");
      if (this.isLogin) {
        console.log("~~~");
        alert("로그인 되었습니다.");
        await this.getUserInfo(token);
        this.$router.push({ name: "Home" });
      }
    },
  },
};
</script>

<style scoped>
.searchpw {
  text-decoration: underline;
  cursor: pointer;
}
.searchpw:hover {
  font-weight: bold;
}
.loginfirstcard {
  background-color: transparent;
  border: none;
}
.loginformbtn {
  width: 100%;
  margin: 0;
  margin-top: 5%;
  padding-left: 0;
  margin-bottom: 5%;
}
.loginviewcon {
  min-width: 600px;
}
.loginviewcard {
  margin-bottom: 5%;
}
.loginviewregbtn {
  color: deepskyblue;
  font-weight: bold;
  text-decoration: underline;
  cursor: pointer;
}

.loginviewbar {
  flex: auto;
  border: none;
  height: 1px;
  background-color: lightgray;
}
.loginviewtxt {
  padding: 0 10px;
}
.loginviewwrap {
  display: flex;
  align-items: center;
}
</style>
