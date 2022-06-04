<!-- @format -->

<template>
  <b-container class="bv-example-row mt-3">
    <h3>글수정</h3>
    <v-divider></v-divider>
    <community-input-item type="modify" />
  </b-container>
</template>

<script>
import CommunityInputItem from "@/components/community/item/CommunityInputItem.vue";

export default {
  name: "BoardModify",
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
