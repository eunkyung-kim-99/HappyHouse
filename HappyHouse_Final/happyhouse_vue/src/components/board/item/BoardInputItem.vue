<!-- @format -->

<template>
  <div>
    <v-simple-table class="p-3">
      <template>
        <tbody>
          <tr>
            <td width="25%"><strong> 작성자 </strong></td>
            <td>{{ loginUser.id }}</td>
          </tr>
          <tr>
            <td><strong>제목</strong></td>
            <td>
              <b-form-input
                id="subject"
                v-model="article.subject"
                type="text"
                required
                placeholder="제목을 입력해주세요."
              ></b-form-input>
            </td>
          </tr>
          <tr>
            <td><strong>내용 </strong></td>
            <td>
              <b-form-textarea
                id="content"
                v-model="article.contents"
                placeholder="내용을 입력해주세요."
                rows="6"
                max-rows="7"
              ></b-form-textarea>
            </td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
    <div style="float: right" class="mt-2 mb-5">
      <b-button
        type="submit"
        variant="info"
        class="m-1"
        v-if="this.type === 'register'"
        @click="registArticle"
        >작성</b-button
      >
      <b-button
        type="submit"
        variant="info"
        class="m-1"
        @click="modifyArticle"
        v-else
        >수정</b-button
      >
      <b-button type="reset" variant="secondary" class="m-1" @click="movelist"
        >목록</b-button
      >
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";
export default {
  name: "BoardInputItem",
  data() {
    return {
      article: {
        no: 0,
        id: "",
        subject: "",
        contents: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      http.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
        // this.article.articleno = data.article.articleno;
        // this.article.userid = data.article.userid;
        // this.article.subject = data.article.subject;
        // this.article.content = data.article.content;
        console.log("modify" + data.id);
        this.article = data;
      });
      this.isUserid = true;
    }
  },
  methods: {
    movelist() {
      this.$router.push({ name: "boardList" });
    },
    registArticle() {
      if (!this.article.subject) {
        alert("제목을 입력하세요!");
        return;
      }
      if (!this.article.contents) {
        alert("내용을 입력하세요!");
        return;
      }
      http
        .post(`/board`, {
          no: 0,
          id: this.loginUser.id,
          subject: this.article.subject,
          contents: this.article.contents,
        })
        .then(({ data }) => {
          let msg = "등록이 완료되었습니다.";
          if (data === "success") {
            console.log("register" + data);
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    modifyArticle() {
      if (!this.article.subject) {
        alert("제목을 입력하세요!");
        return;
      }
      if (!this.article.contents) {
        alert("내용을 입력하세요!");
        return;
      }
      http
        .put(`/board`, {
          no: this.article.no,
          id: this.loginUser.id,
          subject: this.article.subject,
          contents: this.article.contents,
        })
        .then(({ data }) => {
          let msg = "수정이 완료되었습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "boardList" });
        });
    },
    moveList() {
      this.$router.push({ name: "boardList" });
    },
  },
  computed: {
    ...mapState(["loginUser"]),
  },
};
</script>

<style></style>
