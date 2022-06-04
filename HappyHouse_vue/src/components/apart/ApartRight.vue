<template>
  <div>
    <b-row class="searchaddress">
      <div>
        <h5 v-if="guguns === 'undefined'" class="text-center">
          검색지역 : {{ sidos.text }} {{ gugun.text }} {{ dongs.text }}
        </h5>
        <h5 v-else>검색지역 : 없음</h5>
      </div>
    </b-row>
    <ApartListVue></ApartListVue>
  </div>

  <!-- <div class="row searchaddress">
    


    <c:choose>
      <c:when test="${not empty searchaddress}">
        <h5 class="text-center pt-2">검색 지역 : ${searchaddress }</h5>
      </c:when>
      <c:otherwise>
        <h5 class="text-center pt-2">검색 지역 : 없음</h5>
      </c:otherwise>
    </c:choose>
    <div id="result123"></div>
  </div> -->
</template>

<script>
import ApartListVue from "./ApartList.vue";
import { mapState } from "vuex";
export default {
  name: "ApartNameSearch",
  computed: {
    ...mapState(["sidos", "guguns", "houses", "dongs"]),
  },
  components: {
    ApartListVue,
  },
};
</script>

<style></style>
