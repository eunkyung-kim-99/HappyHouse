<!-- @format -->

<template>
  <b-container class="mt-10 mb-7">
    <b-row class="mb-1">
      <b-col cols="9"> <board-search></board-search> </b-col>
      <b-col>
        <v-btn
          class="mx-2"
          fab
          dark
          small
          color="cyan"
          v-if="admincheck"
          @click="moveWrite()"
          style="float: right"
        >
          <v-icon dark> mdi-pencil </v-icon>
        </v-btn>
        <!-- <div v-else style="float: right">
          글을 작성하려면 로그인하세요
        </div> -->
      </b-col>
    </b-row>
    <b-table-simple hover responsive class="text-center mt-2">
      <b-thead head-variant="dark">
        <b-tr>
          <b-th width="5%">번호</b-th>
          <b-th width="25%">작성자</b-th>
          <b-th>제목</b-th>
          <b-th width="25%">작성일</b-th>
          <b-th width="10%">조회수</b-th>
        </b-tr>
      </b-thead>
      <template v-if="boards.length">
        <tbody>
          <!-- 하위 component인 ListRow에 데이터 전달(props) -->
          <board-list-item
            v-for="article in boards"
            :key="article.no"
            v-bind="article"
          />
        </tbody>
      </template>
      <template v-else>
        <td colspan="5">등록된 글이 없습니다</td>
      </template>
    </b-table-simple>
    <!--  페이징 -->
    <!-- <div class="overflow-auto">
      <b-pagination
        v-model="currentPage"
        :total-rows="totalcount"
        :per-page="perpage"
        :limit="limit"
        size="sm"
      ></b-pagination>
    </div> -->

    <div class="text-center">
      <v-pagination
        v-model="currentPage"
        :length="length"
        circle
      ></v-pagination>
    </div>
  </b-container>
</template>

<script>
// import http from "@/api/http";
import BoardListItem from "@/components/board/item/BoardListItem";
import BoardSearch from "@/components/board/item/BoardSearch.vue";
///import VList from "vue-js-list";
import { mapActions, mapState } from "vuex";

export default {
  name: "BoardList",
  data() {
    return {
      currentPage: 1,
      perpage: 7,
      limit: 8,
      length: 1,
      admincheck: false,
    };
  },
  components: {
    BoardListItem,
    BoardSearch,
    //VList,
  },
  // data() {
  //   return {
  //     arrayOfTitles: ["번호", "제목", "내용", "작성자"],
  //   };
  // },
  computed: {
    ...mapState([
      "loginUser",
      "boards",
      "totalcount",
      "boardselect",
      "boardcontent",
    ]),
  },

  watch: {
    currentPage: function () {
      const params = {
        currentPage: this.currentPage,
        perpage: this.perpage,
        select: this.boardselect,
        content: this.boardcontent,
      };
      this.board_search(params);
    },
    boards: function () {
      if (this.totalcount % 7 == 0) {
        this.length = this.totalcount / 7;
      } else {
        this.length = this.totalcount / 7 + 1;
      }
    },
  },
  created() {
    const params = {
      currentPage: this.currentPage,
      perpage: this.perpage,
      select: this.boardselect,
      content: this.boardcontent,
    };
    this.board_search(params);
    if (this.totalcount % 7 == 0) {
      this.length = this.totalcount / 7;
    } else {
      this.length = this.totalcount / 7 + 1;
    }

    if (this.loginUser.id == "admin") {
      this.admincheck = true;
    }
  },
  methods: {
    ...mapActions(["board_search"]),
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
#writeboard {
  width: 100%;
}
.board-footer {
  width: 100%;
}
</style>
