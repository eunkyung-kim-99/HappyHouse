<!-- @format -->

<template>
  <!-- <select
    class="border m-2"
    style="height: 40px"
    name="sortValue"
    id="sortValue"
    @change="ordering"
    v-model="sortValue"
  >

    <option value="sortValuePrice">선택해주세요</option>
    <option value="sortValuePrice">거래금액순</option>
    <option value="sortValueArea">면적순</option>
  </select> -->
  <v-select :items="items" @change="ordering" v-model="sortValue"> </v-select>
</template>

<script>
export default {
  name: "ApartOrder",
  data() {
    return {
      sortValue: "",
      items: [
        { value: "", text: "선택해주세요" },
        { value: "sortValuePrice", text: "거래금액순" },
        { value: "sortValueArea", text: "면적순" },
      ],
    };
  },
  methods: {
    ordering() {
      this.$emit("orderby", this.sortValue);
    },
  },
};
</script>

<style></style>
