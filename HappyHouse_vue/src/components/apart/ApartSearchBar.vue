<template>
  <b-row class="mt-4 mb-4 text-center">
    <b-col class="sm-3">
      <b-form-select
        v-model="sidoCode"
        :options="sidos"
        @change="gugunList"
      ></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select
        v-model="gugunCode"
        :options="guguns"
        @change="dongList"
      ></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select
        v-model="dongCode"
        :options="dongs"
        @change="searchApt"
      ></b-form-select>
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
    ...mapState(["sidos", "guguns", "houses", "dongs"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(["getSido", "getGugun", "getDong", "getApartList"]),
    ...mapMutations(["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_DONG_LIST"]),
    // sidoList() {
    //   this.getSido();
    // },
    dongList() {
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
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
      console.log(this.gugunCode);
    },
    searchApt() {
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

<style></style>
