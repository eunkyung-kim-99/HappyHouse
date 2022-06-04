<!-- @format -->

<template>
  <b-container>
    <b-row class="mb-1">
      <b-col cols="10" class="text-center">
        <v-chip close-icon="" color="" label class="mt-2">
          <div
            v-if="selDongCode"
            class="text-center"
            id="searcharea"
            style="font-size: 20px"
          >
            검색지역 : {{ selSidoCode }} {{ selGugunCode }} {{ selDongCode }}
          </div>
          <div class="text-center" id="searcharea" v-else>
            검색지역 : 없음
          </div></v-chip
        >
      </b-col>

      <b-col>
        <div class="text-center mt-1" id="interloc">
          <template v-if="selDongCode">
            <b-icon
              icon="star-fill"
              color="orange"
              font-scale="2"
              v-if="loginUser"
              @click="addinter"
            ></b-icon>
            <b-icon
              v-else
              @click="addcheck"
              icon="star-fill"
              color="grey"
              font-scale="2"
            ></b-icon>
          </template>

          <b-icon
            v-else
            @click="addcheck"
            icon="star-fill"
            color="grey"
            font-scale="2"
          ></b-icon>
        </div>
      </b-col>
    </b-row>

    <ApartListVue></ApartListVue>
  </b-container>
</template>

<script>
import ApartListVue from "./ApartList.vue";
import { mapState } from "vuex";
import http from "@/api/http";

export default {
  name: "ApartNameSearch",
  computed: {
    ...mapState([
      "sidos",
      "guguns",
      "houses",
      "dongs",
      "selSidoCode",
      "selGugunCode",
      "selDongCode",
      "loginUser",
    ]),
  },
  components: {
    ApartListVue,
  },
  methods: {
    addcheck() {
      if (!this.loginUser) {
        alert("로그인이 필요한 서비스입니다");
        this.$router.push({ name: "user" });
        return;
      }
      if (!this.selDongCode) {
        alert("추가할 지역을 선택하세요");
        return;
      }
    },

    addinter() {
      if (confirm("즐겨찾기에 추가하시겠습니까?")) {
        http
          .post("/userinter", {
            id: this.loginUser.id,
            selsidocode: this.selSidoCode,
            selguguncode: this.selGugunCode,
            seldongcode: this.selDongCode,
            aptName: "null",
            img: "null",
          })
          .then((data) => {
            let msg = "추가 도중 문제가 발생했습니다.";
            if (data.data == "success") {
              console.log(data.data);
              msg = "즐겨찾기에 추가되었습니다.";
            } else {
              console.log(data);
              msg = "이미 추가된 항목입니다.";
            }
            alert(msg);
          });
      }
    },
  },
};
</script>

<style></style>
