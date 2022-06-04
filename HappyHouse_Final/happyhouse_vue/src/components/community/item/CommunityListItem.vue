<!-- @format -->

<template>
  <b-tr>
    <b-td>{{ no }}</b-td>
    <!-- <b-td>{{ hit }}</b-td> -->
    <b-td>{{ id }}</b-td>
    <b-th class="text-left">
      <router-link
        :to="{ name: 'CommunityDetail', params: { articleno: no } }"
        >{{ subject }}</router-link
      >
    </b-th>
    <b-td>{{ sidoname }} {{ gugunname }} {{ dongname }}</b-td>
    <b-td>{{ keyword }}</b-td>
    <!-- <b-td>{{ regtime }}</b-td> -->

    <b-td>{{ regtime | dateFormat }}</b-td>
    <b-td>{{ hit }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "CommunityListItem",
  props: {
    no: String,
    id: String,
    subject: String,
    content: String,
    sidoname: String,
    gugunname: String,
    dongname: String,
    keyword: String,
    hit: String,
    regtime: String,
  },
  created() {
    if (this.id.length > 10) {
      this.id = this.content.substr(0, 10) + "...";
    }
    if (this.subject.length > 10) {
      this.subject = this.subject.substr(0, 10) + "...";
    }
    if (this.content.length > 10) {
      this.content = this.content.substr(0, 10) + "...";
    }
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
