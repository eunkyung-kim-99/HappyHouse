<!-- @format -->

<template>
  <b-container class="bv-example-row" style="width: 70%">
    <h4 class="white--text">회원가입</h4>
    <b-row>
      <b-col>
        <b-card id="registerviewfirstcard">
          <b-form enctype="multipart/form-data">
            <div style="text-align: center">
              <div>
                <v-avatar color="grey lighten-2" size="130">
                  <img id="preview" src="@/assets/images/notloginimg.png" />
                </v-avatar>
                <!-- <v-avatar class="registerViewfileinput" size="40"> -->
                <b-icon
                  class="registerViewfileinput"
                  onclick="document.getElementById('profileimg').click();"
                  icon="camera-fill"
                  font-scale="3"
                  variant="secondary"
                ></b-icon>

                <!-- <img src="@/assets/images/cameraimg.png" /> -->
                <!-- </v-avatar> -->
              </div>
            </div>

            <b-form-group>
              <b-form-file
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
            <!-- ㅇㄹㅇㄹㅇㄹ -->
            <v-simple-table class="p-3">
              <tbody>
                <tr>
                  <td width="35%"><strong>아이디</strong></td>

                  <td>
                    <b-form-group
                      :invalid-feedback="invalidFeedback"
                      :state="state"
                    >
                      <b-row>
                        <b-col cols="8">
                          <b-form-input
                            id="id"
                            v-model="id"
                            type="text"
                            :state="state"
                            required
                            @change="changeid"
                          ></b-form-input>
                        </b-col>
                        <b-col cols="4">
                          <b-button
                            variant="primary"
                            @click="checkid"
                            id="idcheckbtn"
                            >중복체크</b-button
                          >
                        </b-col>
                      </b-row>
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
                        class="mt-3"
                        id="password"
                        v-model="password"
                        type="password"
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
                        class="mt-3"
                        id="passwordconfirm"
                        v-model="passwordconfirm"
                        type="password"
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
                        class="mt-3"
                        id="name"
                        v-model="name"
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
                        class="mt-3"
                        id="email"
                        v-model="email"
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
                        class="mt-3"
                        id="phonenumber"
                        v-model="phonenumber"
                        type="text"
                        required
                      ></b-form-input>
                    </b-form-group>
                  </td>
                </tr>
              </tbody>
            </v-simple-table>

            <!-- ㄴㅇㄴㅇ -->

            <div style="text">
              <b-button
                style="float: left"
                variant="primary"
                @click="regist"
                class="m-1"
                id="registersubmitbtn"
                >등록</b-button
              >
              <b-button
                style="float: right"
                @click="movelogin"
                variant="danger"
                class="m-1"
                id="registerloginbtn"
                >로그인</b-button
              >
            </div>
          </b-form>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapGetters } from "vuex";
import http from "../../api/http";

export default {
  name: "RegisterView",
  data() {
    return {
      id: "",
      password: "",
      name: "",
      email: "",
      phonenumber: "",
      passwordconfirm: "",
      profileimg: File,
      idchecked: false,
    };
  },
  created() {
    this.id = "";
    this.password = "";
    this.name = "";
    this.email = "";
    this.phonenumber = "";
    this.passwordconfirm = "";
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
    changeid() {
      this.idchecked = false;
    },
    checkid() {
      http
        .get(`/user/checkid/${this.id}`)
        .then((response) => {
          console.log(response);
          alert(response.data);
          if (response.data === "사용 가능한 아이디입니다.") {
            this.idchecked = true;
          } else {
            this.idchecked = false;
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
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
      } else if (this.password !== this.passwordconfirm) {
        alert("비밀번호가 일치하지 않습니다");
        return;
      }
      if (!this.idchecked) {
        alert("아이디 중복검사를 해주세요.");
        return;
      }
      let user = {
        id: this.id,
        password: this.password,
        name: this.name,
        email: this.email,
        phonenumber: this.phonenumber,
        profileimg: this.profileimg,
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
    ...mapGetters(["goodpassword"]),
    state() {
      return this.id.length >= 6 && this.id.length <= 12 && this.idchecked;
    },
    invalidFeedback() {
      if (this.id.length < 6 || this.id.length > 12) {
        return "아이디는 6자 ~ 12자 사이로 입력해주세요.";
      } else {
        return "아이디 중복체크를 해주세요.";
      }
    },
    pwstate() {
      return this.password == this.passwordconfirm;
    },
    pwinvalidFeedback() {
      return "비밀번호가 일치하지 않습니다.";
    },
    notallowedid() {
      for (var i = 0; i < this.id.length; i++) {
        if (
          (this.id[i] >= "a" && this.id[i] <= "z") ||
          (this.id[i] >= "A" && this.id[i] <= "Z") ||
          (this.id[i] >= "0" && this.id[i] <= "9")
        ) {
          continue;
        } else {
          return false;
        }
      }
      return true;
    },
    pstate() {
      for (var i = 0; i < this.password.length; i++) {
        if (
          (this.password[i] >= "a" && this.password[i] <= "z") ||
          (this.password[i] >= "A" && this.password[i] <= "Z") ||
          (this.password[i] >= "0" && this.password[i] <= "9")
        ) {
          continue;
        } else if (this.goodpwcheck(this.password[i])) {
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
};
</script>

<style scoped>
#idcheckbtn {
  width: 100%;
  margin-top: 0;
}
#preview {
  width: 100%;
  height: 100%;
}
#registersubmitbtn {
  width: 45%;
}
#registerloginbtn {
  width: 45%;
}
.registerViewfileinput {
  position: absolute;
  right: 30%;
  left: 53%;
  top: 14%;
}
</style>
