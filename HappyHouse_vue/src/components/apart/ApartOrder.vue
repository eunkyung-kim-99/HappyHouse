<template>
  <div v-if="aparts">
    <select name="ordercategory" id="ordercategory">
      <option value="">선택</option>
      <option value="aptname">아파트 이름순</option>
      <option value="dealdate">거래날짜 순</option>
      <option value="area">면적 순</option>
    </select>
    <select
      class="select-order"
      name="ordermethod"
      id="ordermethod"
      @change="ordering"
    >
      <option value="">선택</option>
      <option value="asc">오름차순</option>
      <option value="desc">내림차순</option>
      <option></option>
    </select>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "ApartOrder",
  computed: {
    ...mapState(["aparts"]),
  },
  methods: {
    ordering() {},
  },
};
</script>

<style></style>
