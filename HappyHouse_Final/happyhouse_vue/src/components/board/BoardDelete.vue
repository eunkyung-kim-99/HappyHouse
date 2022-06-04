<!-- @format -->

<template>
  <b-container class="bv-example-row mt-3">
    <h3>
      삭제 중
      <b-icon
        icon="arrow-counterclockwise"
        animation="spin-reverse"
        font-scale="4"
      ></b-icon>
    </h3>
  </b-container>
</template>

<script>
import http from "@/api/http";

export default {
  name: "BoardDelete",
  created() {
    http.delete(`/board/${this.$route.params.articleno}`).then(({ data }) => {
      let msg = "삭제가 완료되었습니다.";
      if (data === "SUCCESS") {
        msg = "삭제가 완료되었습니다.";
      }
      alert(msg);
      // 현재 route를 /list로 변경.
      this.$router.push({ name: "boardList" });
    });
  },
};
</script>

<style></style>
