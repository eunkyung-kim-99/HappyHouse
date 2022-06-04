<!-- @format -->

<template>
  <div>
    <div class="apartdetaillist wrapper scroll">
      <template v-if="apartdetaillist.length">
        <div v-for="(apart, index) in apartdetaillist" :key="index">
          <v-simple-table>
            <template v-slot:default>
              <tbody>
                <tr>
                  <td width="40%">거래 날짜</td>
                  <td>
                    {{ apart.dealYear }}년 {{ apart.dealMonth }}월
                    {{ apart.dealDay }}일
                  </td>
                </tr>
                <tr>
                  <td>거래 금액</td>
                  <td>{{ apart.dealAmount }}만원</td>
                </tr>
                <tr>
                  <td>면적</td>
                  <td>{{ apart.area }}</td>
                </tr>
                <tr>
                  <td>층</td>
                  <td>{{ apart.floor }}층</td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
          <hr />
        </div>
      </template>
      <template v-else>거래 내역이 없습니다</template>
    </div>
  </div>
</template>

<script>
import http from "@/api/http.js";

export default {
  computed: {},
  components: {},
  props: {
    apartdetail: Object,
    sortValue: String,
  },
  data() {
    return {
      apartdetaillist: [],
    };
  },

  created() {
    http.get(`/apt/${this.apartdetail.aptCode}`).then(({ data }) => {
      console.log(data);
      this.apartdetaillist = data;
    });
  },
  watch: {
    sortValue: function () {
      const list = JSON.stringify(this.apartdetaillist);
      http
        .post(`/apt/sort/${this.sortValue}`, list)
        .then((data) => {
          console.log(data.data);
          this.apartdetaillist = data.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style>
.apartdetaillist hr {
  height: 0.7px;
  background-color: rgb(223, 223, 223);
}
</style>
