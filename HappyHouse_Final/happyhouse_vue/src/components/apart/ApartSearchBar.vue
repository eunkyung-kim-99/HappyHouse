<!-- @format -->

<template>
  <b-row class="text-center">
    <b-col class="sm-3">
      <v-select
        :items="sidos"
        label="Solo field"
        solo
        v-model="sidoCode"
        @change="gugunList"
      ></v-select>
    </b-col>
    <b-col class="sm-3">
      <v-select
        :items="guguns"
        ref="gugunsel"
        v-model="gugunCode"
        label="Solo field"
        solo
        @change="dongList"
      ></v-select>
    </b-col>
    <b-col class="sm-3">
      <v-select
        :items="dongs"
        ref="dongsel"
        v-model="dongCode"
        label="Solo field"
        solo
        @change="searchApt"
      ></v-select>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  computed: {
    ...mapState([
      "sidos",
      "guguns",
      "houses",
      "dongs",
      "selSidoCode",
      "selGugunCode",
      "selDongCode",
    ]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
  },
  async mounted() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();

    await this.getSido();
  },
  methods: {
    ...mapActions([
      "getSido",
      "getGugun",
      "getDong",
      "getApartList",
      "selSidoCode",
    ]),
    ...mapMutations([
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "SET_WORD",
      "SET_SIDO_CODE",
      "SET_DONG_CODE",
      "SET_GUGUN_CODE",
      "SET_APART_LIST",
      "SET_WORD",
    ]),
    // sidoList() {
    //   this.getSido();
    // },

    dongList() {
      this.SET_WORD("");
      console.log(this.sidoCode + ":" + this.gugunCode);
      const codes = {
        scode: this.sidoCode,
        gcode: this.gugunCode,
      };
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(codes);
      console.log(this.sidoCode + ":" + this.gugunCode);
    },

    gugunList() {
      this.SET_WORD("");
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_LIST();
      this.SET_SIDO_CODE();
      this.SET_DONG_CODE();
      this.SET_GUGUN_CODE();
      this.SET_APART_LIST(null);
      this.SET_WORD(null);
      this.gugunCode = null;
      this.dongCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
      console.log(this.gugunCode);
    },
    searchApt() {
      this.SET_WORD("");
      const codes = {
        sido: this.sidoCode,
        gugun: this.gugunCode,
        dong: this.dongCode,
        key: "",
        word: "",
      };
      if (this.dongCode) this.getApartList(codes);
    },
  },
};
</script>

<style>
.font-white h4 {
  color: white;
}
.border {
  background-color: white;
}
</style>
