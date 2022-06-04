<!-- @format -->

<template>
  <div @click="userinterdetail" class="m-3">
    <v-simple-table>
      <template v-slot:default>
        <tbody>
          <tr>
            <td>{{ selsidocode }} {{ selguguncode }} {{ seldongcode }}</td>
            <td class="text-right">
              <v-btn flat rounded small @click="userintermapsearch" class="mr-4"
                >지도 검색</v-btn
              >
              <v-btn flat rounded small @click="deleteinter">삭제</v-btn>
            </td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
    <v-divider></v-divider>
    <!-- <v-card class="mx-auto" max-width="344" color="#E7E7E7">
      <v-list-item>
        <v-list-item-content>
          <div class="text-overline mb-4"></div>
          <v-list-item-title class="text-h6 mb-1">
            {{ selsidocode }} {{ selguguncode }} {{ seldongcode }}
          </v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-card-actions>
        <v-btn flat rounded small @click="userintermapsearch">지도 검색</v-btn>
        <v-btn flat rounded small @click="deleteinter">삭제</v-btn>
      </v-card-actions>
    </v-card> -->
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import http from "@/api/http.js";
export default {
  name: "UserInterListItem",
  props: {
    // no: Number,
    // id: String,
    selsidocode: String,
    selguguncode: String,
    seldongcode: String,
    aptName: String,
  },
  computed: {
    ...mapState(["loginUser"]),
  },
  created() {},
  methods: {
    ...mapActions(["get_userinter", "getApartList", "getuserintereatail"]),
    ...mapMutations([
      "SET_GUGUN_CODE",
      "SET_SIDO_CODE",
      "SET_DONG_CODE",
      "CLEAR_USER_INTER_DETAIL",
    ]),
    deleteinter() {
      if (confirm("즐겨찾기를 삭제하시겠습니까?")) {
        console.log("ddddd" + this.selsidocode);
        console.log("ddddd" + this.selguguncode);
        console.log("ddddd" + this.seldongcode);
        http
          .delete(
            `/userinter/${this.selsidocode}/${this.selguguncode}/${this.seldongcode}`
          )
          .then((data) => {
            if (data) {
              this.get_userinter();
              this.CLEAR_USER_INTER_DETAIL();
              alert("삭제되었습니다.");
            }
          });
      }
    },
    userintermapsearch() {
      this.SET_SIDO_CODE(this.selsidocode);
      this.SET_GUGUN_CODE(this.selguguncode);
      this.SET_DONG_CODE(this.seldongcode);
      const codes = {
        sido: this.selsidocode,
        gugun: this.selguguncode,
        dong: this.seldongcode,
        key: "",
        word: "",
      };
      this.getApartList(codes);
      this.$router.push({ name: "apart" });
    },
    userinterdetail() {
      this.getuserintereatail({
        selsidocode: this.selsidocode,
        selguguncode: this.selguguncode,
        seldongcode: this.seldongcode,
      });
    },
  },
};
</script>

<style></style>
