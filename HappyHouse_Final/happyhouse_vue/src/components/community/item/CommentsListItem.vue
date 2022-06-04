<!-- @format -->

<template>
  <b-row class="ml-3 mb-3">
    <b-col>
      <!-- 작성자 아이디/작성일 -->
      <b-row>
        <div class="m-2">
          <v-avatar size="20" v-if="profile" color="grey lighten-2">
            <img :src="profile" alt="프로필 사진" />
          </v-avatar>
          <v-avatar size="20" v-else color="grey lighten-2">
            <img src="@/assets/images/notloginimg.png" alt="비로그인 이미지" />
          </v-avatar>
        </div>
        <strong class="mr-3 mt-3">{{ id }}</strong>
        <div class="comments-regtime mt-3">
          {{ regtime }}
          <template v-if="this.updatetime == 1">(수정)</template>
        </div>
      </b-row>

      <b-row class="ml-4">
        <template v-if="!ismodify">{{ content }} </template>
      </b-row>

      <template v-if="ismodify"
        ><b-form-input
          v-model="updatecontent"
          type="text"
          required
          class="mt-3"
        ></b-form-input> </template
    ></b-col>

    <b-col v-if="loginUser.id === `${id}`">
      <template v-if="!ismodify">
        <div class="mr-5" style="float: right">
          <b-icon
            icon="wrench"
            variant="dark"
            @click="Modifycomments"
            class="mr-4"
          ></b-icon>
          <b-icon icon="trash" variant="dark" @click="deletecomments"></b-icon>
        </div>
      </template>
      <template v-else>
        <div class="mt-9">
          <b-icon
            icon="check-lg"
            variant="info"
            @click="updatecomments"
            class="mr-4"
          ></b-icon>

          <b-icon
            icon="x-lg"
            variant="danger"
            @click="cancelcommnets"
            class="mr-4"
          ></b-icon>
        </div>
      </template>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions } from "vuex";
// import axios from "axios";
import http from "@/api/http.js";
export default {
  name: "CommentsListItem",
  props: {
    no: Number,
    id: String,
    content: String,
    // hit: Number,
    communityno: Number,
    regtime: String,
    updatetime: Number,
  },
  data() {
    return {
      ismodify: false,
      updatecontent: this.content,
      profile: "",
    };
  },
  async created() {
    // axios.get(`http://localhost:9999/file/${this.id}`).then(({ data }) => {});

    await http.get(`/file/${this.id}`).then((data) => {
      // console.log("댓글프로필" + data.data);
      if (data.data) {
        this.profile = "data:image/png;base64," + data.data;
      }
    });
  },
  computed: {
    ...mapState(["loginUser"]),
  },
  methods: {
    ...mapActions(["delete_comments", "update_comments"]),

    Modifycomments() {
      this.ismodify = true;
    },
    updatecomments() {
      this.update_comments({
        no: this.no,
        communityno: this.communityno,
        content: this.updatecontent,
      });

      this.ismodify = false;
    },
    deletecomments() {
      this.delete_comments({ no: this.no, communityno: this.communityno });
    },
    cancelcommnets() {
      this.ismodify = false;
    },
  },
};
</script>

<style>
.width40 {
  width: 40%;
}
#cmodrembtn button {
  width: 40%;
}
.comments-regtime {
  color: rgb(138, 138, 138);
  font-size: 12px;
  padding-top: 5px;
}
</style>
