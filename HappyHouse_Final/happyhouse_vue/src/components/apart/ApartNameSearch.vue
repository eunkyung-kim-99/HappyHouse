<!-- @format -->

<template>
  <div id="apt-search">
    <b-input-group class="mt-1 mb-1">
      <b-form-input
        type="search"
        placeholder="아파트 이름으로 검색해주세요."
        v-model="modelword"
        name="word"
        id="word"
        @change="changeword"
      ></b-form-input>
      <b-input-group-prepend>
        <b-button variant="info" @click="searchApt">
          <b-icon icon="search"></b-icon>
        </b-button>
      </b-input-group-prepend>
    </b-input-group>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
export default {
  name: "ApartNameSearch",
  data() {
    return {
      modelword: "",
      modelkey: "aptName",
    };
  },
  computed: {
    ...mapState(["selSidoCode", "selGugunCode", "selDongCode", "word", "key"]),
  },
  methods: {
    ...mapActions(["getApartList"]),
    ...mapMutations(["SET_WORD"]),
    searchApt() {
      console.log(this.word + " : " + this.key);
      const codes = {
        sido: this.selSidoCode,
        gugun: this.selGugunCode,
        dong: this.selDongCode,
        key: this.modelkey,
        word: this.modelword,
      };

      this.getApartList(codes);

      this.SET_WORD("");
    },
    changeword() {
      this.SET_WORD(this.modelword);
    },
  },

  watch: {
    word: function () {
      console.log("???");
      this.modelword = this.word;
    },
  },
};
</script>

<style></style>
