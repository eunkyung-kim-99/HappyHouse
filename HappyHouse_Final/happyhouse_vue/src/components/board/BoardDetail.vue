<!-- @format -->

<template>
  <b-container class="bv-example-row mt-3 mb-10">
    <h3>{{ article.subject }}</h3>
    <b-row>
      <b-col cols="9"><v-divider></v-divider></b-col>
      <b-col
        ><h6 style="float: right; color: grey">
          {{ article.regtime }}
          <template v-if="article.updatetime == 1">(수정)</template> | 조회수
          {{ article.hit }}
        </h6></b-col
      ></b-row
    >

    <v-simple-table class="p-3 mb-10">
      <template>
        <tbody>
          <tr>
            <td width="25%"><strong> 작성자 </strong></td>
            <td>{{ loginUser.id }}</td>
          </tr>
          <tr>
            <td><strong>제목</strong></td>
            <td>{{ article.subject }}</td>
          </tr>
          <tr>
            <td><strong>내용 </strong></td>
            <td class="pt-5 pb-5 pr-5 wrapper scroll">
              {{ article.contents }}
            </td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>

    <template v-if="loginUser.id === `${article.id}`">
      <div style="float: right" class="mt-2 mb-5">
        <b-button variant="info" class="mr-2" @click="moveModifyArticle"
          >수정</b-button
        >

        <b-button variant="danger" class="mr-2" @click="deleteArticle"
          >삭제</b-button
        >
        <b-button type="reset" variant="secondary" @click="movelist"
          >목록</b-button
        >
      </div>
    </template>
    <div v-else style="float: right" class="mt-2">
      <b-button type="reset" variant="secondary" @click="movelist"
        >목록</b-button
      >
    </div>
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
    http.put(`/board/${this.$route.params.articleno}`).then(({ data }) => {
      console.log(data);
    });

    http.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
      console.log("detail" + data.id);
      this.article = data;
    });
  },
  methods: {
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
    movelist() {
      this.$router.push({ name: "boardList" });
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style scoped>
h3 {
  color: rgb(120, 120, 120);
  text-align: center;
  padding: 60px;
}
</style>
