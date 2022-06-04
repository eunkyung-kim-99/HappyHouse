<template>
  <div>
    <div v-if="apart" class="aptinfolist">
      <div class="aptNamelist">
        {{ apart.aptName }}
      </div>
      <div>
        거래 날짜 : {{ apart.dealTear }}년 {{ apart.dealMonth }}월
        {{ apart.dealDay }}일
      </div>
      <div>거래 금액 : {{ apart.dealAmount }}만</div>
      <div>면적 : {{ apart.area }}</div>
    </div>
    <div v-else>
      <h4 v-if="aparts" class="text-center">검색 결과가 없습니다</h4>
      <h4 v-else class="text-center">검색 키워드로 검색해주세요.</h4>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "ApartListItem",
  props: {
    apart: Object,
  },
  computed: {
    ...mapState(["aparts"]),
  },
};
</script>

<style></style>
