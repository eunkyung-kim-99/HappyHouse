<!-- @format -->

<template>
  <b-container class="bv-example-row">
    <!-- <v-list
      :to="{ name: 'boardDetail', params: { articleno: no } }"
      :content="arrayList"
      :titles="arrayOfTitles"
    /> -->

    <template v-if="loginUser">
      <b-row class="">
        <b-col class="text-right">
          <b-button variant="primary" @click="moveWrite()">글쓰기</b-button>
        </b-col>
      </b-row>
    </template>
    <template v-else>
      <b-row class="">
        <b-col class="text-right"> 글을 작성하려면 로그인하세요 </b-col>
      </b-row>
    </template>
    <b-row>
      <b-col>
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>번호</b-th>
              <b-th>작성자</b-th>
              <b-th>제목</b-th>
              <b-th>내용</b-th>
              <!-- <b-th>조회수</b-th> -->
              <!-- <b-th>작성일</b-th> -->
            </b-tr>
          </b-thead>
          <template v-if="articles.length">
            <tbody>
              <!-- 하위 component인 ListRow에 데이터 전달(props) -->
              <board-list-item
                v-for="article in articles"
                :key="article.no"
                v-bind="article"
              />
            </tbody>
          </template>
          <template v-else>
            <b-td aria-colspan="4">등록된 글이 없습니다</b-td>
          </template>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";
import BoardListItem from "@/components/board/item/BoardListItem";
///import VList from "vue-js-list";
import { mapState } from "vuex";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
    //VList,
  },
  data() {
    return {
      articles: [],
      // arrayOfContent: [
      //   ["content1", "content2"],
      //   ["content3", "content4"],
      // ],
      arrayOfTitles: ["번호", "제목", "내용", "작성자"],
    };
  },
  computed: {
    ...mapState(["loginUser"]),
  },
  created() {
    // this.$store.dispatch("getBoardList");
    http.get(`/board`).then(({ data }) => {
      console.log(data);
      this.articles = data;
      //   this.arrayOfContent = this.articles;
      //   articles.forEach((article) => {
      //     arrayOfContent.push([
      //       article.no,
      //       article.id,
      //       article.subject,
      //       article.contents,
      //     ]);
      //   });
    });
  },
  methods: {
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
</style>
