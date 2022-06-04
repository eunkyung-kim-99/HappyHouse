<!-- @format -->

<template>
  <b-container class="bv-example-row" style="width: 70%">
    <h4 class="white--text">마이페이지</h4>
    <div class="detailviewframe">
      <b-row>
        <b-col>
          <b-card class="">
            <b-form enctype="multipart/form-data">
              <div style="text-align: center">
                <div>
                  <!-- <v-avatar color="grey lighten-2" size="130">
                    <img id="preview" src="@/assets/images/notloginimg.png" />
                  </v-avatar> -->

                  <!-- <v-avatar color="grey lighten-2" size="130" v-if="profile">
                    <img id="preview" :src="profile" alt="프로필 사진" />
                  </v-avatar> -->
                  <v-avatar color="grey lighten-2" size="130">
                    <img id="preview" src="@/assets/images/notloginimg.png" />
                  </v-avatar>

                  <!-- <v-avatar color="grey lighten-2" size="130">
                    <img id="preview" src="@/assets/images/notloginimg.png" />
                  </v-avatar> -->
                  <!-- <v-avatar
                    class="registerViewfileinput"
                    size="40"
                    onclick="document.getElementById('profileimg').click();"
                  >
                    <img src="@/assets/images/cameraimg.png" />
                  </v-avatar> -->

                  <b-icon
                    class="registerViewfileinput"
                    onclick="document.getElementById('profileimg').click();"
                    icon="camera-fill"
                    font-scale="3"
                    variant="secondary"
                  ></b-icon>
                </div>
              </div>

              <b-form-group>
                <b-form-file
                  :value="profile"
                  @change="readURL"
                  id="profileimg"
                  v-model="profileimg"
                  type="img"
                  accept=".jpg, .png, .gif"
                  class="mt-3"
                  plain
                  style="display: none"
                ></b-form-file>
              </b-form-group>
              <!--  -->
              <!-- ㅇㄹㅇㄹㅇㄹ -->
              <v-simple-table class="p-3">
                <tbody>
                  <tr>
                    <td width="35%"><strong>아이디</strong></td>
                    <td>
                      <b-form-group>
                        <b-form-input
                          readonly
                          id="id"
                          v-model="loginUser.id"
                          type="text"
                          required
                        ></b-form-input>
                      </b-form-group>
                    </td>
                  </tr>
                  <tr>
                    <td><strong>비밀번호</strong></td>
                    <td>
                      <b-form-group
                        :invalid-feedback="pinvalidFeedback"
                        :state="pstate"
                      >
                        <b-form-input
                          class="mt-4"
                          id="password"
                          v-model="loginUser.password"
                          type="text"
                          required
                          :state="pstate"
                        ></b-form-input>
                      </b-form-group>
                    </td>
                  </tr>
                  <tr>
                    <td><strong>비밀번호 확인 </strong></td>
                    <td>
                      <b-form-group
                        :invalid-feedback="pwinvalidFeedback"
                        :state="pwstate"
                      >
                        <b-form-input
                          class="mt-4"
                          id="password"
                          v-model="passwordconfirm"
                          type="text"
                          :state="pwstate"
                          required
                        ></b-form-input>
                      </b-form-group>
                    </td>
                  </tr>

                  <tr>
                    <td><strong>이름 </strong></td>
                    <td>
                      <b-form-group>
                        <b-form-input
                          class="mt-4"
                          id="name"
                          v-model="loginUser.name"
                          type="text"
                          required
                        ></b-form-input>
                      </b-form-group>
                    </td>
                  </tr>
                  <tr>
                    <td><strong>이메일 </strong></td>
                    <td>
                      <b-form-group>
                        <b-form-input
                          class="mt-4"
                          id="email"
                          v-model="loginUser.email"
                          type="text"
                          required
                        ></b-form-input>
                      </b-form-group>
                    </td>
                  </tr>

                  <tr>
                    <td><strong>전화번호</strong></td>
                    <td>
                      <b-form-group>
                        <b-form-input
                          class="mt-4"
                          id="phonenumber"
                          v-model="loginUser.phonenumber"
                          type="text"
                          required
                        ></b-form-input>
                      </b-form-group>
                    </td>
                  </tr>
                </tbody>
              </v-simple-table>
              <!--  -->

              <b-button
                variant="primary"
                @click="updateUser"
                class="m-1"
                id="detailview-update-btn"
                style="float: left"
                >수정</b-button
              >
              <b-button
                @click="deleteUser"
                variant="danger"
                class="m-1"
                id="detailview-delete-btn"
                style="float: right"
                >회원탈퇴</b-button
              >
            </b-form>
          </b-card>
        </b-col>
      </b-row>
    </div>
  </b-container>
</template>

<script>
import { mapState, mapGetters } from "vuex";
export default {
  name: "userdetail",
  data() {
    return {
      passwordconfirm: "",
      profileimg: File,
    };
  },

  // data() {
  //   return {
  //     user: null,
  //   };
  // },
  created() {},
  mounted() {
    this.passwordconfirm = this.loginUser.password;
    if (this.profile) {
      document.getElementById("preview").src = this.profile;
    }
    console.log(this.loginUser);
    console.log("로그인이미지" + this.loginUser.profileimg);
  },

  methods: {
    readURL(input) {
      var reader = new FileReader();
      reader.onload = function (e) {
        document.getElementById("preview").src = e.target.result;
      };
      reader.readAsDataURL(input.target.files[0]);
    },
    goodpwcheck(char) {
      for (var j = 0; j < this.goodpassword.length; j++) {
        if (char == this.goodpassword[j]) return true;
      }
      return false;
    },
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
      } else if (this.loginUser.password !== this.passwordconfirm) {
        alert("비밀번호가 일치하지 않습니다");
        return;
      }
      this.loginUser["profileimg"] = this.profileimg;
      this.$store.dispatch("updateUser", this.loginUser);
    },
    deleteUser() {
      alert("회원을 탈퇴합니다");
      this.$store.dispatch("deleteUser", this.loginUser.id);
      this.$store.commit("LOGOUT");
    },
  },
  computed: {
    ...mapState(["loginUser", "profile"]),
    ...mapGetters(["goodpassword"]),
    //...mapGetters(["loginUser"]),
    pwstate() {
      return this.loginUser.password == this.passwordconfirm;
    },
    pwinvalidFeedback() {
      return "비밀번호가 일치하지 않습니다.";
    },
    pstate() {
      for (var i = 0; i < this.loginUser.password.length; i++) {
        if (
          (this.loginUser.password[i] >= "a" &&
            this.loginUser.password[i] <= "z") ||
          (this.loginUser.password[i] >= "A" &&
            this.loginUser.password[i] <= "Z") ||
          (this.loginUser.password[i] >= "0" &&
            this.loginUser.password[i] <= "9")
        ) {
          continue;
        } else if (this.goodpwcheck(this.loginUser.password[i])) {
          continue;
        } else {
          return false;
        }
      }
      return true;
    },
    pinvalidFeedback() {
      return `비밀번호는 영문, 숫자, "!", "@", "#", "$", "%", "^", "&", "*"가 사용가능합니다.`;
    },
  },
  // created() {
  //   const pathName = new URL(document.location).pathname.split("/");
  //   const id = pathName[pathName.length - 1];

  //   this.$store.dispatch("setUser", id);
  // },
};
</script>
<style scoped>
.detailviewframe {
  min-width: 400px;
}
#preview {
  width: 100%;
  height: 100%;
}
.registerViewfileinput {
  position: absolute;
  /* right: 30%; */
  left: 55%;
  top: 14.5%;
}
#detailview-update-btn {
  width: 45%;
}
#detailview-delete-btn {
  width: 45%;
}
</style>
