<template>
  <div class="border">
    <b-row>
      <div id="apt-search"></div>
      <apart-order-vue></apart-order-vue>
    </b-row>
    <apart-list-item-vue
      v-for="(apart, index) in aparts"
      :key="index"
      :apart="apart"
    ></apart-list-item-vue>
  </div>
</template>

<script>
import ApartOrderVue from "./ApartOrder.vue";
import ApartListItemVue from "./ApartListItem.vue";
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState(["aparts"]),
  },
  components: {
    ApartOrderVue,
    ApartListItemVue,
  },
};
</script>

<style></style>
