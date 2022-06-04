<!-- @format -->

<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <!-- <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col> -->
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.no}.
          ${article.subject}</h3>
          <div><h6>${article.id}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="`<h5>${article.contents}</h5>`"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <template v-if="loginUser.id === `${article.id}`">
      <b-button variant="info" size="sm" @click="moveModifyArticle" class="mr-2"
        >수정</b-button
      >
      <b-button variant="danger" size="sm" @click="deleteArticle"
        >삭제</b-button
      >
    </template>
  </b-container>
</template>

<script>
// import moment from "moment";
import http from "@/api/http";
import { mapState } from "vuex";
export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    ...mapState(["loginUser"]),
  },
  created() {
    if (!this.loginUser) {
      alert("로그인 후에 이용해주세요.");
      this.$router.push({ name: "userlogin" });
      return;
    }

    http.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
      console.log("detail" + data.id);
      this.article = data;
    });
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "boardModify",
        params: { articleno: this.article.no },
      });
      //this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        this.$router.replace({
          name: "boardDelete",
          params: { articleno: this.article.no },
        });
      }
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
