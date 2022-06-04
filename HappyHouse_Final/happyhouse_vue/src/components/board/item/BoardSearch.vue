<!-- @format -->

<template>
  <div class="mt-1">
    <b-row>
      <b-input-group>
        <b-col cols="2">
          <template>
            <!-- <v-select :items="words" solo v-model="select"></v-select> -->
            <b-form-select v-model="select" :options="words"></b-form-select>
          </template>
        </b-col>
        <b-col cols="7">
          <b-form-input v-model="content"></b-form-input>
        </b-col>

        <b-col>
          <b-input-group-prepend>
            <b-button id="boardsearchbtn" variant="info" @click="selected"
              >검색</b-button
            >
          </b-input-group-prepend>
        </b-col>
      </b-input-group>
    </b-row>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
export default {
  data() {
    return {
      select: "전체",
      words: ["전체", "작성자", "제목"],
      content: "",
    };
  },
  computed: {
    ...mapState(["boardselect", "boardcontent"]),
  },
  mounted() {
    this.SET_BOARDSELECT("전체");
    this.SET_BOARDCONTENT("");
  },
  methods: {
    ...mapActions(["board_search"]),
    ...mapMutations(["SET_BOARDSELECT", "SET_BOARDCONTENT"]),
    selected() {
      const params = {
        currentPage: 1,
        perpage: 5,
        select: this.select,
        content: this.content,
      };
      this.board_search(params);
    },
  },
  watch: {
    select: function () {
      this.SET_BOARDSELECT(this.select);
    },
    content: function () {
      this.SET_BOARDCONTENT(this.content);
    },
  },
};
</script>

<style>
#boardsearchbtn {
  width: 100%;
  margin-top: 0;
}
</style>
