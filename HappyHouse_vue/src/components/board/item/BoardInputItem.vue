<!-- @format -->

<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="userid-group"
          label="작성자:"
          label-for="userid"
          v-model="article.id"
        >
          <b-form-input
            id="userid"
            readonly
            :disabled="isUserid"
            v-model="loginUser.id"
            type="text"
            required
            placeholder="작성자 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="subject-group" label="제목:" label-for="subject">
          <b-form-input
            id="subject"
            v-model="article.subject"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.contents"
            placeholder="내용 입력..."
            rows="5"
            max-rows="7"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">목록</b-button>
      </b-form>
    </b-col>
  </b-row>
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
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      // !this.article.id &&
      //   ((msg = "작성자 입력해주세요"), (err = false), this.$refs.id.focus());
      // err &&
      !this.article.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.article.contents &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.contents.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.no = 0;
      this.article.subject = "";
      this.article.contents = "";
      this.$router.push({ name: "boardList" });
    },
    registArticle() {
      //console.log(this.article.id);
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
