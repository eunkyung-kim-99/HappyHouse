<!-- @format -->

<template>
  <b-tr>
    <b-td>{{ no }}</b-td>
    <!-- <b-td>{{ hit }}</b-td> -->
    <b-td>{{ id }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'boardDetail', params: { articleno: no } }">{{
        subject
      }}</router-link>
    </b-th>
    <b-td>{{ contents }}</b-td>
    <!-- <b-td>{{ regtime | dateFormat }}</b-td> -->
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    no: Number,
    id: String,
    subject: String,
    contents: String,
    // hit: Number,
    // regtime: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
