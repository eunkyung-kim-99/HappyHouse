<!-- @format -->

<template>
  <b-container class="bv-example-row">
    <h3>{{ article.subject }}</h3>
    <b-row>
      <b-col cols="9"><v-divider></v-divider></b-col>
      <b-col
        ><h6 style="float: right; color: grey">
          {{ article.regtime }}
          <template v-if="article.updatetime == 1">(수정)</template> | 조회수
          {{ article.hit }}
        </h6></b-col
      ></b-row
    >

    <!-- 테이블 시작 -->
    <b-row style="height: 390px">
      <b-col cols="3">
        <v-carousel
          v-if="imglist.length"
          cycle
          height="370"
          hide-delimiter-background
          show-arrows-on-hover
        >
          <v-carousel-item v-for="(filedata, index) in imglist" :key="index">
            <v-sheet height="100%">
              <v-row
                class="fill-height"
                style="widyth: 100%"
                align="center"
                justify="center"
              >
                <div style="width: 100%">
                  <img
                    id="imgimg"
                    style="object-fit: cover"
                    :class="{ communityinputitemsampleimg: flag }"
                    :src="filedata.data"
                  />
                </div>
              </v-row>
            </v-sheet>
          </v-carousel-item>
        </v-carousel>
        <div v-else class="text-center" style="line-height: 320px">
          No Image
        </div>
      </b-col>
      <b-col>
        <v-simple-table class="p-3">
          <template>
            <tbody>
              <tr>
                <td width="25%"><strong> 작성자 </strong></td>
                <td>{{ article.id }}</td>
              </tr>
              <tr>
                <td><strong>제목</strong></td>
                <td>{{ article.subject }}</td>
              </tr>
              <tr>
                <td><strong>지역 </strong></td>
                <td>
                  {{ article.sidoname }} {{ article.gugunname }}
                  {{ article.dongname }}
                </td>
              </tr>
              <tr>
                <td><strong>분류 </strong></td>
                <td>{{ article.keyword }}</td>
              </tr>
              <tr>
                <td><strong>내용 </strong></td>
                <td class="pt-5 pb-5 pr-5 wrapper scroll" style="height: 100px">
                  {{ article.content }}
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </b-col>
      <!--  테이블 끝 -->
    </b-row>
    <template v-if="loginUser.id === `${article.id}`">
      <div style="float: right" class="mt-2">
        <b-button variant="info" class="mr-2" @click="moveModifyArticle"
          >수정</b-button
        >

        <b-button variant="danger" class="mr-2" @click="deleteArticle"
          >삭제</b-button
        >
        <b-button type="reset" variant="secondary" @click="movelist"
          >목록</b-button
        >
      </div>
    </template>

    <div v-else style="float: right" class="mt-2">
      <b-button type="reset" variant="secondary" @click="movelist"
        >목록</b-button
      >
    </div>
    <div class="m-2">
      <h4 class="mt-5 m-2">댓글</h4>
      <v-divider style="height: 1px; background-color: grey"></v-divider>
      <div v-if="comments.length" class="wrapper scroll pt-2">
        <!-- 하위 component인 ListRow에 데이터 전달(props) -->
        <comments-list-item
          v-for="comment in comments"
          :key="comment.no"
          v-bind="comment"
        />
      </div>
      <template v-else>
        <p>등록된 댓글이 없습니다</p>
      </template>
    </div>
    <b-row>
      <b-col cols="9">
        <b-form-input
          id="commentsform"
          v-model="content"
          type="text"
          required
          placeholder="댓글을 입력하세요"
        ></b-form-input
      ></b-col>
      <b-col>
        <b-button @click="insertcomments" variant="primary">
          <b-icon icon="chat-dots"></b-icon>
        </b-button> </b-col
    ></b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import http from "@/api/http";
import { mapState, mapActions, mapMutations } from "vuex";
import CommentsListItem from "@/components/community/item/CommentsListItem.vue";
export default {
  name: "CommunityDetail",
  components: {
    CommentsListItem,
  },
  data() {
    return {
      article: {},
      //comments: [],
      content: "",
      imglist: [],
      flag: true,
    };
  },
  computed: {
    ...mapState(["loginUser", "comments"]),
  },
  async created() {
    if (!this.checkLogin()) {
      return;
    }

    http.get(`/community/${this.$route.params.articleno}`).then(({ data }) => {
      console.log("detail" + data.content);
      this.article = data;
      console.log(this.article.no);
      http.get(`/comments/${this.article.no}`).then(({ data }) => {
        console.log(data);
        this.CLEAR_COMMENTS();
        this.SET_COMMENTS(data);
      });
    });

    await http
      .post(`/file/communityimg/files/${this.$route.params.articleno}`)
      .then((data) => {
        console.log(data);
        for (var itemp = 0; itemp < data.data.length; itemp++) {
          console.log(itemp);
          const item = {
            name: data.data[itemp][0],
            data: "data:image/png;base64," + data.data[itemp][1],
          };
          console.log(item);
          this.imglist.push(item);
        }
      });
  },
  mounted() {
    document.querySelector("img").classList.add("communityinputitemsampleimg");
  },
  methods: {
    ...mapActions(["insert_comments"]),
    ...mapMutations(["CLEAR_COMMENTS", "SET_COMMENTS"]),
    movelist() {
      this.$router.push({ name: "CommunityList" });
    },
    checkLogin() {
      if (!this.loginUser) {
        alert("로그인 후에 이용해주세요.");
        this.$router.push({ name: "userlogin" });
        return false;
      }
      return true;
    },
    listArticle() {
      this.$router.push({ name: "CommunityList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "CommunityModify",
        params: { articleno: this.article.no },
      });
    },
    deleteArticle() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        this.$router.replace(`/community/delete/${this.article.no}`);
      }
    },
    insertcomments() {
      if (!this.checkLogin()) {
        return;
      }
      if (this.content == "") {
        alert("댓글을 입력하세욘");
        return;
      }
      let comment = {
        no: 0,
        id: this.loginUser.id,
        communityno: this.article.no,
        content: this.content,
      };
      console.log(comment);
      http.post(`/comments`, comment).then(({ data }) => {
        let msg = "댓글 등록이 완료되었습니다.";
        if (data === "success") {
          console.log("register" + data);
          msg = "댓글 등록이 완료되었습니다.";
        }
        alert(msg);
        this.insert_comments(this.article.no);
        this.content = "";
      });
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style scoped>
.wrapper {
  height: 150px;
}
h3 {
  color: rgb(120, 120, 120);
  text-align: center;
  padding: 10px;
}
.communityinputitemsampleimg {
  object-fit: cover;
  background-size: cover;
  width: 100%;
}
</style>
