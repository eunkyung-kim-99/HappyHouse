<!-- @format -->

<template>
  <b-container class="bv-example-row mt-3">
    <h3>게시글 작성</h3>
    <v-divider></v-divider>
    <board-input-item type="modify" />
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem.vue";

export default {
  name: "BoardModify",
  components: {
    BoardInputItem,
  },
};
</script>

<style scoped>
h3 {
  color: rgb(120, 120, 120);
  text-align: center;
  padding: 60px;
}
</style>
