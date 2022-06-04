<!-- @format -->

<template>
  <b-container class="bv-example-row mt-3">
    <h3>게시글 작성</h3>
    <v-divider></v-divider>
    <community-input-item type="register" />
  </b-container>
</template>

<script>
import CommunityInputItem from "@/components/community/item/CommunityInputItem.vue";

export default {
  name: "CommunityRegister",
  components: {
    CommunityInputItem,
  },
};
</script>

<style scoped>
h3 {
  color: rgb(120, 120, 120);
  text-align: center;
  padding: 10px;
}
</style>
