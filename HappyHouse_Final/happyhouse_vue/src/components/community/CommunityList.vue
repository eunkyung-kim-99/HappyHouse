<!-- @format -->

<template>
  <b-container class="mb-4">
    <b-row class="m-2">
      <b-col
        ><div>
          <b-row class="text-center">
            <b-col>
              <v-select
                :items="sidos"
                v-model="sidoname"
                @change="gugunList"
              ></v-select>
            </b-col>
            <b-col>
              <v-select
                :items="guguns"
                v-model="gugunname"
                @change="dongList"
              ></v-select>
            </b-col>
            <b-col>
              <v-select
                :items="dongs"
                v-model="dongname"
                @change="searchList"
              ></v-select>
            </b-col>
            <b-col>
              <v-btn
                fab
                dark
                small
                color="cyan"
                v-if="loginUser"
                @click="moveWrite()"
                style="float: right"
              >
                <v-icon dark> mdi-pencil </v-icon>
              </v-btn>
              <div v-else style="float: right">
                글을 작성하려면 로그인하세요
              </div></b-col
            >
          </b-row>
        </div>
      </b-col>
    </b-row>

    <b-row>
      <b-col>
        <div>
          <div>
            <b-card
              header="게시판"
              header-bg-variant="Secondary"
              align="center"
            >
              <b-form-group>
                <input
                  class="radiobtn"
                  v-bind:class="{ bold: allsel }"
                  type="button"
                  value="전체"
                  @click="searchListall"
                />
                <input
                  class="radiobtn"
                  v-bind:class="{ bold: edusel }"
                  type="button"
                  value="교육"
                  @click="searchListedu"
                />
                <input
                  class="radiobtn"
                  v-bind:class="{ bold: foodsel }"
                  type="button"
                  value="음식"
                  @click="searchListfood"
                />
                <input
                  class="radiobtn"
                  v-bind:class="{ bold: clubsel }"
                  type="button"
                  value="동호회"
                  @click="searchListclub"
                />
                <input
                  class="radiobtn"
                  v-bind:class="{ bold: studysel }"
                  type="button"
                  value="스터디"
                  @click="searchListstudy"
                />
                <input
                  class="radiobtn"
                  v-bind:class="{ bold: elsesel }"
                  type="button"
                  value="기타"
                  @click="searchListelse"
                />
              </b-form-group>
            </b-card>
          </div>
        </div>
      </b-col>
      <b-col cols="10">
        <b-table-simple
          hover
          responsive
          class="table tableordering text-center"
        >
          <b-thead head-variant="dark">
            <b-tr>
              <b-th width="5%">번호</b-th>
              <b-th width="10%">작성자</b-th>
              <b-th width="15%">제목</b-th>
              <b-th width="45%">지역</b-th>
              <b-th width="10%">분류</b-th>
              <b-th width="10%">작성일</b-th>
              <b-th width="5%">조회수</b-th>
            </b-tr>
          </b-thead>

          <template v-if="articles.length">
            <tbody>
              <!-- 하위 component인 ListRow에 데이터 전달(props) -->
              <community-list-item
                v-for="article in articles"
                :key="article.no"
                v-bind="article"
              />
            </tbody>
          </template>
          <template v-else>
            <b-td colspan="6">등록된 글이 없습니다</b-td>
          </template>
        </b-table-simple>
        <!--페이징-->
        <div v-show="false" class="text-center paging align-center">
          <div class="overflow-auto">
            <b-pagination
              class="pagingbtn"
              v-model="currentPage"
              :total-rows="comutotalcount"
              :per-page="perpage"
              :limit="limit"
              size="sm"
            ></b-pagination>
          </div>
        </div>

        <div class="text-center">
          <v-pagination
            v-model="currentPage"
            :length="length"
            circle
          ></v-pagination>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import CommunityListItem from "@/components/community/item/CommunityListItem.vue";
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "CommunityList",
  components: {
    CommunityListItem,
  },
  data() {
    return {
      //articles: [],
      sidoname: null,
      gugunname: null,
      dongname: null,
      keyword: "전체",
      currentPage: 1,
      perpage: 7,
      limit: 8,
      allsel: true,
      edusel: false,
      foodsel: false,
      clubsel: false,
      studysel: false,
      elsesel: false,
      length: "",
    };
  },
  computed: {
    ...mapState([
      "loginUser",
      "sidos",
      "guguns",
      "dongs",
      "articles",
      "comutotalcount",
    ]),
  },
  watch: {
    currentPage: function () {
      let area = {
        sidoname: this.sidoname,
        gugunname: this.gugunname,
        dongname: this.dongname,
        keyword: this.keyword,
        currentPage: this.currentPage,
        perpage: this.perpage,
      };
      this.searchDong(area);
    },
    articles: function () {
      if (this.comutotalcount % 7 == 0) {
        this.length = this.comutotalcount / 7;
      } else {
        this.length = this.comutotalcount / 7 + 1;
      }
    },
  },

  created() {
    // this.$store.dispatch("getBoardList");
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.getSido();
    let area = {
      sidoname: this.sidoname,
      gugunname: this.gugunname,
      dongname: this.dongname,
      keyword: this.keyword,
      currentPage: this.currentPage,
      perpage: this.perpage,
    };
    this.searchDong(area);
    if (this.comutotalcount % 7 == 0) {
      this.length = this.comutotalcount / 7;
    } else {
      this.length = this.comutotalcount / 7 + 1;
    }
  },
  methods: {
    ...mapActions([
      "getSido",
      "getGugun",
      "getDong",
      // "searchSido",
      // "searchGugun",
      "searchDong",
      //"searchKeyword",
    ]),
    ...mapMutations([
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "SET_ARTICLES",
    ]),
    moveWrite() {
      this.$router.push({ name: "CommunityRegister" });
    },
    dongList() {
      console.log(this.sidoname + ":" + this.gugunname);
      const codes = {
        scode: this.sidoname,
        gcode: this.gugunname,
      };
      this.CLEAR_DONG_LIST();
      this.dongname = null;
      if (this.gugunname) this.getDong(codes);
      console.log(this.sidoname + ":" + this.dongname);

      // let area = {
      //   sidoname: this.sidoname,
      //   gugunname: this.gugunname,
      //   keyword: this.keyword,
      // };
      // this.searchGugun(area);
      this.searchList();
    },

    gugunList() {
      console.log(this.sidoname);
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_LIST();
      this.gugunname = null;
      this.dongname = null;
      if (this.sidoname) this.getGugun(this.sidoname);

      // let area = {
      //   sidoname: this.sidoname,
      //   keyword: this.keyword,
      // };
      // this.searchSido(area);
      this.searchList();
    },
    searchListedu() {
      this.keyword = "교육";
      this.allsel = false;
      this.edusel = true;
      this.foodsel = false;
      this.clubsel = false;
      this.studysel = false;
      this.elsesel = false;
      this.searchList();
    },
    searchListall() {
      this.keyword = "전체";
      this.allsel = true;
      this.edusel = false;
      this.foodsel = false;
      this.clubsel = false;
      this.studysel = false;
      this.elsesel = false;
      this.searchList();
    },
    searchListfood() {
      this.keyword = "음식";
      this.allsel = false;
      this.edusel = false;
      this.foodsel = true;
      this.clubsel = false;
      this.studysel = false;
      this.elsesel = false;
      this.searchList();
    },
    searchListclub() {
      this.keyword = "동호회";
      this.allsel = false;
      this.edusel = false;
      this.foodsel = false;
      this.clubsel = true;
      this.studysel = false;
      this.elsesel = false;
      this.searchList();
    },
    searchListstudy() {
      this.keyword = "스터디";
      this.allsel = false;
      this.edusel = false;
      this.foodsel = false;
      this.clubsel = false;
      this.studysel = true;
      this.elsesel = false;
      this.searchList();
    },
    searchListelse() {
      this.keyword = "기타";
      this.allsel = false;
      this.edusel = false;
      this.foodsel = false;
      this.clubsel = false;
      this.studysel = false;
      this.elsesel = true;
      this.searchList();
    },
    searchList() {
      let area = {
        sidoname: this.sidoname,
        gugunname: this.gugunname,
        dongname: this.dongname,
        keyword: this.keyword,
        currentPage: this.currentPage,
        perpage: this.perpage,
      };
      this.searchDong(area);
    },
  },
};
</script>

<style src="vue2-perfect-scrollbar/dist/vue2-perfect-scrollbar.css"></style>
<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
.bold {
  font-weight: bold;
  text-decoration: underline;
  font-size: large;
}
#communitywritebtn {
  margin-top: 0;
}
td {
  width: auto;
  min-width: 100px;
  max-width: 120px;
}
th {
  min-width: 100px;
  max-width: 120px;
}

.ps {
  width: 100%;
}
.table {
  max-height: none;
  width: 100%;
}

.radiobtn {
  display: block;
}
</style>
