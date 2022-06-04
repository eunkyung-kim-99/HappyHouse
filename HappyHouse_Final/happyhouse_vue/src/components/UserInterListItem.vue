<!-- @format -->

<template>
  <b-col cols="4">
    <v-card class="mx-auto" max-width="344" outlined>
      <v-list-item three-line>
        <v-list-item-content>
          <div class="text-overline mb-4">{{ id }}</div>
          <v-list-item-title class="text-h6 mb-1">
            {{ selsidocode }} {{ selguguncode }} {{ seldongcode }}
          </v-list-item-title>
          <v-list-item-subtitle
            >Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle
          >
        </v-list-item-content>
      </v-list-item>

      <v-card-actions>
        <v-btn outlined rounded text @click="userintermapsearch">
          지도 검색 바로가기
        </v-btn>
        <v-btn outlined rounded text @click="deleteinter"> 삭제 </v-btn>
      </v-card-actions>
    </v-card>
  </b-col>
</template>

<script>
import { mapActions, mapMutations } from "vuex";
import http from "@/api/http.js";
export default {
  name: "UserInterListItem",
  props: {
    no: Number,
    id: String,
    selsidocode: String,
    selguguncode: String,
    seldongcode: String,
  },
  methods: {
    ...mapActions(["get_userinter", "getApartList"]),
    ...mapMutations(["SET_GUGUN_CODE", "SET_SIDO_CODE", "SET_DONG_CODE"]),
    deleteinter() {
      if (confirm("즐겨찾기를 삭제하시겠습니까?")) {
        http.delete(`/userinter/${this.no}`).then((data) => {
          if (data) {
            this.get_userinter();
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
  },
};
</script>

<style></style>
