<!-- @format -->

<template>
  <div class="bg" align="center">
    <header-nav></header-nav>
    <v-card class="mb-7 board_bg mt-8" max-width="70%">
      <v-card-text>
        <!-- <v-row align="center">
          <h4 class="userinter-title text-left m-3">즐겨찾기</h4>
        </v-row>
        <v-divider></v-divider> -->
        <v-container v-if="userinter.length">
          <v-row>
            <v-col cols="5" class="wrapper scroll">
              <user-inter-list-item
                v-for="inter in userinter"
                :key="inter.no"
                v-bind="inter"
              ></user-inter-list-item>
            </v-col>

            <v-col fluid v-if="userinterdetail.length" class="wrapper scroll">
              <v-row>
                <user-inter-detail-list-item
                  v-for="inter in userinterdetail"
                  :key="inter.no"
                  v-bind="inter"
                  cols="12"
                  md="6"
                ></user-inter-detail-list-item>
              </v-row>
            </v-col>
            <v-col v-else>즐겨찾기에 추가된 아파트목록이 없습니다.</v-col>
          </v-row>
        </v-container>
        <div v-else>즐겨찾기에 추가된 항목이 없습니다</div>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import UserInterListItem from "@/components/userinter/UserInterListItem.vue";
import UserInterDetailListItem from "@/components/userinter/UserInterDetailListItem.vue";
import HeaderNav from "../components/layout/HeaderNav.vue";

export default {
  name: "UserInterView",
  components: {
    UserInterListItem,
    UserInterDetailListItem,
    HeaderNav,
  },
  computed: {
    ...mapState(["loginUser", "userinter", "userinterdetail"]),
  },
  watch: {},
  created() {
    if (!this.loginUser) {
      alert("로그인이 필요한 서비스입니다.");
      this.$router.push({ name: "user" });
    }
    this.CLEAR_USER_INTER_DETAIL();
    this.get_userinter();
    console.log(this.userinter);
  },
  methods: {
    ...mapMutations(["CLEAR_USER_INTER_DETAIL"]),
    ...mapActions(["get_userinter"]),
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
.userinter-bg {
  height: 100%;
  width: 80%;
}
.wrapper {
  height: 500px;
}
.board_bg {
  width: 80%;
  background: rgba(256, 256, 256, 0.93) 60%;
}
</style>
