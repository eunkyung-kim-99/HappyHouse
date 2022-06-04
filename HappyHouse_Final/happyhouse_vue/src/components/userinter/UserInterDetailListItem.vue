<!-- @format -->

<template>
  <v-layout justify-center>
    <v-card class="mx-auto m-2" max-width="400">
      <!-- <v-img
        class="white--text align-end"
        height="200px"
        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
      > -->
      <v-img
        class="white--text align-end"
        :src="img"
        width="250px"
        height="180px"
      >
        <v-card-title
          ><div class="apttitle">{{ aptName }}</div></v-card-title
        >
      </v-img>

      <v-card-subtitle class="pb-0"
        >{{ jibun }}<br />{{ buildyear }}년 건축</v-card-subtitle
      >

      <!-- <v-card-text class="text--primary">
        <div>Whitehaven Beach</div>

        <div>Whitsunday Island, Whitsunday Islands</div>
      </v-card-text> -->

      <v-card-actions class="text-right">
        <v-btn color="orange" @click="searchapart" text> 아파트검색 </v-btn>

        <v-btn color="orange" @click="deleteinter" text> 삭제 </v-btn>
      </v-card-actions>
    </v-card>
    <!-- <v-card class="m-2">
      <v-container>
        <v-layout row wrap>
          <v-card>
            <v-img :src="img" width="200px" height="200px">
              <v-container>
                <v-layout>
                  <v-flex flexbox>
                    <span class="headline white--text"></span>
                  </v-flex>
                </v-layout>
              </v-container>
            </v-img>

            <v-card-actions>
              <div class="mr-3">{{ aptName }}</div>
              <b-icon
                outlined
                rounded
                text
                @click="deleteinter"
                icon="star-fill"
                color="orange"
                font-scale="2"
              ></b-icon>
            </v-card-actions>
          </v-card>
        </v-layout>
      </v-container>
    </v-card> -->
  </v-layout>
  <!-- 
  <b-button @click="userinterdetail">
    <v-card class="mx-auto" max-width="344" outlined>
      <v-list-item three-line>
        <v-list-item-content>
          <div class="text-overline mb-4"></div>
          <img :src="img" />
          <v-list-item-title class="text-h6 mb-1">
            {{ selsidocode }} {{ selguguncode }} {{ seldongcode }} {{ aptName }}
          </v-list-item-title>
          <v-list-item-subtitle
            >Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle
          >
        </v-list-item-content>
      </v-list-item>

      <v-card-actions>
        <v-btn outlined rounded text @click="deleteinter"> 삭제 </v-btn>
      </v-card-actions>
    </v-card>
  </b-button> -->
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import http from "@/api/http.js";
export default {
  name: "UserInterListItem",
  props: {
    no: Number,
    // id: String,
    selsidocode: String,
    selguguncode: String,
    seldongcode: String,
    aptName: String,
    img: String,
    roadname: String,
    jibun: String,
    buildyear: String,
  },
  computed: {
    ...mapState(["aparts"]),
  },
  methods: {
    ...mapActions([
      "get_userinter",
      "getApartList",
      "getuserintereatail",
      "getApartDetailList",
    ]),
    ...mapMutations([
      "SET_GUGUN_CODE",
      "SET_SIDO_CODE",
      "SET_DONG_CODE",
      "CLEAR_APART_LIST",
    ]),
    deleteinter() {
      if (confirm("즐겨찾기를 삭제하시겠습니까?")) {
        console.log("ddddd" + this.selsidocode);
        console.log("ddddd" + this.selguguncode);
        console.log("ddddd" + this.seldongcode);
        http.delete(`/userinter/${this.no}`).then((data) => {
          if (data) {
            //this.get_userinter();
            this.getuserintereatail({
              selsidocode: this.selsidocode,
              selguguncode: this.selguguncode,
              seldongcode: this.seldongcode,
            });
            alert("삭제되었습니다.");
          }
        });
      }
    },
    searchapart() {
      this.SET_SIDO_CODE(this.selsidocode);
      this.SET_GUGUN_CODE(this.selguguncode);
      this.SET_DONG_CODE(this.seldongcode);
      const codes = {
        sido: this.selsidocode,
        gugun: this.selguguncode,
        dong: this.seldongcode,
        key: "null",
        word: this.aptName,
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

<style>
.apttitle {
  background-color: rgba(90, 90, 90, 0.484);
  padding: 5px;
}
</style>
