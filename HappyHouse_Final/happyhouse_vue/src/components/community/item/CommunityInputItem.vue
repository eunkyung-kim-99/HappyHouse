<!-- @format -->

<template>
  <div class="mb-1">
    <v-simple-table class="p-3">
      <template>
        <tbody>
          <tr>
            <td width="25%"><strong> 작성자 </strong></td>
            <td>{{ loginUser.id }}</td>
          </tr>
          <tr>
            <td><strong>제목</strong></td>
            <td>
              <b-form-input
                id="subject"
                v-model="article.subject"
                type="text"
                required
                placeholder="제목을 입력해주세요."
              ></b-form-input>
            </td>
          </tr>
          <tr>
            <td><strong>내용 </strong></td>
            <td>
              <b-form-textarea
                id="content"
                v-model="article.content"
                placeholder="내용을 입력해주세요."
                rows="4"
                max-rows="7"
              ></b-form-textarea>
            </td>
          </tr>
          <tr>
            <td>
              <strong>첨부파일</strong>
            </td>
            <td>
              <form enctype="multipart/form-data">
                <b-form-file
                  @change="readURL"
                  class="mt-3"
                  multiple
                  plain
                  accept=".jpg, .png, .gif"
                  type="img"
                  v-model="communityimage"
                  id="communityimage"
                ></b-form-file>
              </form>
            </td>
          </tr>
          <tr>
            <td>
              <strong>첨부파일 관리</strong>
            </td>
            <td>
              <div v-for="(filedata, index) in communityfilelist" :key="index">
                {{ filedata.name }}
                <span @click="deletefile($event)" :indexval="index">x</span>
              </div>
            </td>
          </tr>
          <tr>
            <td>
              <strong>첨부파일 사진</strong>
            </td>
            <td>
              <img
                class="communityinputitemsampleimg"
                v-for="(filedata, index) in communityfilelist"
                :key="index"
                :src="filedata.data"
              />
            </td>
          </tr>
          <tr>
            <td><strong>지역</strong></td>
            <td>
              <template>
                <b-row class="text-center">
                  <b-col class="sm-3">
                    <v-select
                      :items="sidos"
                      v-model="article.sidoname"
                      @change="gugunList"
                    ></v-select>
                  </b-col>
                  <b-col class="sm-3">
                    <v-select
                      :items="guguns"
                      v-model="article.gugunname"
                      @change="dongList"
                    ></v-select>
                  </b-col>
                  <b-col class="sm-3">
                    <v-select
                      :items="dongs"
                      v-model="article.dongname"
                    ></v-select>
                  </b-col>
                </b-row>
              </template>
            </td>
          </tr>

          <tr>
            <td><strong>분류 </strong></td>
            <td>
              <b-form-group class="mt-5">
                <b-form-radio
                  class="keyword-radio"
                  v-model="article.keyword"
                  value="전체"
                  >전체</b-form-radio
                >
                <b-form-radio
                  class="keyword-radio"
                  v-model="article.keyword"
                  value="교육"
                  >교육</b-form-radio
                >
                <b-form-radio
                  class="keyword-radio"
                  v-model="article.keyword"
                  value="음식"
                  >음식</b-form-radio
                >
                <b-form-radio
                  class="keyword-radio"
                  v-model="article.keyword"
                  value="동호회"
                  >동호회</b-form-radio
                >
                <b-form-radio
                  class="keyword-radio"
                  v-model="article.keyword"
                  value="스터디"
                  >스터디</b-form-radio
                >
                <b-form-radio
                  class="keyword-radio"
                  v-model="article.keyword"
                  value="기타"
                  >기타</b-form-radio
                >
              </b-form-group>
            </td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
    <div style="float: right" class="mt-2 mb-5">
      <b-button
        type="submit"
        variant="info"
        class="m-1"
        v-if="this.type === 'register'"
        @click="registArticle"
        >작성</b-button
      >
      <b-button
        type="submit"
        variant="info"
        class="m-1"
        v-else
        @click="modifyArticle"
        >수정</b-button
      >
      <b-button type="reset" variant="secondary" class="m-1" @click="movelist"
        >목록</b-button
      >
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapState, mapActions, mapMutations } from "vuex";
import axios from "axios";

export default {
  name: "CommunityInputItem",
  data() {
    return {
      article: {
        no: 0,
        id: "",
        subject: "",
        content: "",
        sidoname: null,
        gugunname: null,
        dongname: null,
        keyword: "전체",
      },
      communityimage: File,
      communityfilelist: [],
      tosenddata: [],
      lastboardno: 0,
    };
  },
  props: {
    type: { type: String },
  },
  watch: {
    communityimage: (val) => {
      // console.log(val.target.attributes.value);
      console.log(val);
    },
  },
  async created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
    if (this.type === "modify") {
      http
        .get(`/community/${this.$route.params.articleno}`)
        .then(({ data }) => {
          this.article.sidoname = data.sidoname;
          this.gugunList();
          this.article.gugunname = data.gugunname;
          this.dongList();
          this.article = data;
        });
    }
  },
  methods: {
    ...mapActions(["getSido", "getGugun", "getDong"]),
    ...mapMutations(["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_DONG_LIST"]),

    deletefile(event) {
      console.log(event.target.attributes[0].value);
      let removeindex = event.target.attributes[0].value;
      this.tosenddata.splice(removeindex, 1);
      this.communityfilelist.splice(removeindex, 1);
      console.log(this.tosenddata);
      console.log(this.communityfilelist);
    },
    async readURL(input) {
      console.log(input);
      for (let t = 0; t < input.target.files.length; t++) {
        var reader = new FileReader();
        reader.onload = (e) => {
          console.log(e);
          console.log(this.communityimage);
          console.log(t);

          const data = {
            name: this.communityimage[t].name,
            data: e.target.result,
          };
          this.communityfilelist.push(data);
          console.log(this.communityimage[t]);
          this.tosenddata.push(this.communityimage[t]);
        };
        await reader.readAsDataURL(input.target.files[t]);
      }
    },
    movelist() {
      this.$router.push({ name: "CommunityList" });
    },
    async registArticle() {
      if (!this.article.subject) {
        alert("제목을 입력하세요!");
        return;
      }
      if (!this.article.content) {
        alert("내용을 입력하세요!");
        return;
      }
      if (
        !this.article.sidoname ||
        !this.article.gugunname ||
        !this.article.dongname
      ) {
        alert("지역을 선택해주세요");
        return;
      }
      await http
        .post(`/community`, {
          no: 0,
          id: this.loginUser.id,
          subject: this.article.subject,
          content: this.article.content,
          sidoname: this.article.sidoname,
          gugunname: this.article.gugunname,
          dongname: this.article.dongname,
          keyword: this.article.keyword,
        })
        .then(({ data }) => {
          let msg = "등록이 완료되었습니다.";
          if (data === "success") {
            console.log("register" + data);
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });

      await http.get(`/community/last`).then(({ data }) => {
        console.log(data);
        this.lastboardno = data.no;
      });
      console.log("마지막 글 번호 : " + this.lastboardno);
      let formData = new FormData();
      for (var itemp = 0; itemp < this.tosenddata.length; itemp++) {
        formData.append("communityimg", this.tosenddata[itemp]);
      }

      console.log("이미지 데이터", this.tosenddata);
      await axios
        .post(
          `http://localhost:9999/file/communityimg/${this.lastboardno}`,
          formData,
          {
            headers: { "Content-Type": "multipart/form-data" },
          }
        )
        .then(({ data }) => {
          console.log(data);
        });

      // await http.post(`/communityimg/${}`)
    },
    modifyArticle() {
      if (!this.article.subject) {
        alert("제목을 입력하세요!");
        return;
      }
      if (!this.article.content) {
        alert("내용을 입력하세요!");
        return;
      }
      if (
        !this.article.sidoname ||
        !this.article.gugunname ||
        !this.article.dongname
      ) {
        alert("지역을 선택해주세요");
        return;
      }
      http
        .put(`/community`, {
          no: this.article.no,
          id: this.loginUser.id,
          subject: this.article.subject,
          content: this.article.content,
          sidoname: this.article.sidoname,
          gugunname: this.article.gugunname,
          dongname: this.article.dongname,
          keyword: this.article.keyword,
        })
        .then(({ data }) => {
          let msg = "수정이 완료되었습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "CommunityList" });
        });

      let formData = new FormData();
      for (var itemp = 0; itemp < this.tosenddata.length; itemp++) {
        formData.append("communityimg", this.tosenddata[itemp]);
      }

      console.log("이미지 데이터", this.tosenddata);
      http
        .put(`/file/communityimg/${this.article.no}`, formData, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then(({ data }) => {
          console.log(data);
        });
    },
    moveList() {
      this.$router.push({ name: "CommunityList" });
    },
    dongList() {
      console.log(this.article.sidoname + ":" + this.article.gugunname);
      const codes = {
        scode: this.article.sidoname,
        gcode: this.article.gugunname,
      };
      this.CLEAR_DONG_LIST();
      this.article.dongname = null;
      if (this.article.gugunname) this.getDong(codes);
      console.log(this.article.sidoname + ":" + this.article.dongname);
    },

    gugunList() {
      console.log(this.article.sidoname);
      console.log("히힣히");

      this.CLEAR_GUGUN_LIST();
      this.article.gugunname = null;
      this.article.dongname = null;
      if (this.article.sidoname) this.getGugun(this.article.sidoname);
      //console.log(this.article.gugunname);
    },
  },
  computed: {
    ...mapState(["loginUser", "sidos", "guguns", "dongs"]),
  },
};
</script>

<style>
.keyword-radio {
  float: left;
  margin: 5px;
}
.communityinputitemsampleimg {
  width: 20%;
  height: 200%;
}
</style>
