/** @format */
import jwt_decode from "jwt-decode";

import Vue from "vue";
import Vuex from "vuex";
import http from "@/api/http";
import router from "@/router";
import createPersistedState from "vuex-persistedstate";
import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    aparts: [],
    SW8: [],
    key: "aptName",
    word: "",
    house: null,
    finishsearch: false,

    profile: "",
    loginUser: null,
    isLogin: false,
    isLoginError: false,
    pwallowedchar: ["!", "@", "#", "$", "%", "^", "&", "*"],

    selSidoCode: "",
    selGugunCode: "",
    selDongCode: "",

    target: Object,
    map: null,
    articles: [],
    boards: [],
    totalcount: 0,
    boardselect: "전체",
    boardcontent: "",
    comutotalcount: 0,
    sidoName: [{ value: null, text: "선택하세요" }],
    gugunName: [{ value: null, text: "선택하세요" }],
    dongName: [{ value: null, text: "선택하세요" }],
    comments: [],
    //유저 즐겨찾기
    userinter: [],
    userinterdetail: [],
    //맛집
    foods: [],
    food: Object,
    cafes: [],
    //지도
    mappoint: Object,
  },
  getters: {
    goodpassword: function (state) {
      return state.pwallowedchar;
    },
    loginUser: function (state) {
      return state.loginUser;
    },
    get_key(state) {
      return state.key;
    },
    get_word(state) {
      return state.word;
    },
  },
  mutations: {
    //아파트 관리 mutations
    SET_FINISHSEARCH(state, finishsearch) {
      state.finishsearch = finishsearch;
    },
    SET_KEY(state, key) {
      state.key = key;
    },
    SET_WORD(state, word) {
      state.word = word;
    },
    SET_SW8(state, SW8) {
      state.SW8 = SW8;
    },
    CLEAR_SW8(state) {
      state.SW8 = null;
    },
    SET_TARGET(state, target) {
      state.target = target;
    },
    SET_GUGUN_CODE(state, guguncode) {
      state.selGugunCode = guguncode;
    },
    SET_SIDO_CODE(state, sidocode) {
      state.selSidoCode = sidocode;
    },
    SET_DONG_CODE(state, dongcode) {
      state.selDongCode = dongcode;
    },
    CLEAR_APART_LIST(state) {
      state.aparts = [];
      console.log("클리어 후" + state.aparts);
    },
    SET_APART_LIST(state, aparts) {
      state.aparts = aparts;
    },

    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoName, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunName, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongName, text: dong.dongName });
      });
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },

    //로그인
    LOGIN_USER: function (state, user) {
      state.loginUser = user;
      state.isLogin = true;
    },
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    LOGOUT: function (state) {
      state.loginUser = null;
    },
    RESET_PROFILE: function (state) {
      state.profile = "";
    },
    SET_PROFILE: function (state, file) {
      state.profile = file;
    },

    // 커뮤니티 검색 바
    SET_SIDO_NAME: function (state, sidoName) {
      state.sidoName = sidoName;
    },
    SET_GUGUN_NAME: function (state, gugunName) {
      state.gugunName = gugunName;
    },
    SET_DONG_NAME: function (state, dongName) {
      state.dongName = dongName;
    },

    //커뮤니티
    SET_COMUTOTALCOUNT: function (state, comutotalcount) {
      state.comutotalcount = comutotalcount;
    },
    CLEAR_ARTICLES: function (state) {
      state.articles = [];
    },
    SET_ARTICLES: function (state, articles) {
      state.articles = articles;
    },
    CLEAR_COMMENTS: function (state) {
      state.comments = [];
    },
    SET_COMMENTS: function (state, comments) {
      state.comments = comments;
    },
    //유저 즐겨찾기
    CLEAR_USER_INTER: function (state) {
      state.userinter = [];
    },
    SET_USER_INTER: function (state, userinter) {
      state.userinter = userinter;
    },
    CLEAR_USER_INTER_DETAIL: function (state) {
      state.userinterdetail = [];
    },
    SET_USER_INTER_DETAIL: function (state, userinterdetail) {
      state.userinterdetail = userinterdetail;
    },
    //게시판 검색
    CLEAR_BOARDS: function (state) {
      state.boards = [];
    },
    SET_BOARDS: function (state, boards) {
      state.boards = boards;
    },
    SET_BOARDSELECT: function (state, boardselect) {
      state.boardselect = boardselect;
    },
    SET_BOARDCONTENT: function (state, boardcontent) {
      state.boardcontent = boardcontent;
    },
    SET_TOTALCOUNT: function (state, totalcount) {
      console.log(totalcount);
      state.totalcount = totalcount;
    },

    //음식점 검색
    CLEAR_FOODS: function (state) {
      state.foods = [];
    },
    SET_FOODS: function (state, foods) {
      state.foods = foods;
    },

    CLEAR_CAFES: function (state) {
      state.cafes = [];
    },
    SET_CAFES: function (state, cafes) {
      state.cafes = cafes;
    },
    //지도좌표셋
    CLEAR_MAP_POINT: function (state) {
      state.mappoint = null;
    },
    SET_MAP_POINT: function (state, mappoint) {
      state.mappoint = mappoint;
    },
  },
  actions: {
    // getBoardList({ commit }) {
    //   http.get(`/board`).then(({ data }) => {
    //     console.log(data);
    //     commit("CLEAR_BOARD_LIST", data);
    //     commit("SET_ARRAYLIST_LIST", data);
    //   });
    // },
    getSido({ commit }) {
      http
        .get(`/address/sido`)
        .then(({ data }) => {
          // console.log(data);
          console.log(data);
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      commit("SET_SIDO_CODE", sidoCode);
      http
        .get(`/address/gugun/${sidoCode}`)
        .then(({ data }) => {
          // console.log(commit, response);

          commit("SET_GUGUN_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getDong({ commit }, Codes) {
      commit("SET_GUGUN_CODE", Codes.gcode);
      http
        .get(`/address/dong/${Codes.scode}/${Codes.gcode}`)
        .then(({ data }) => {
          console.log(data);

          commit("SET_DONG_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    orderApartList({ commit }, params) {
      http
        .post("/apt/sort", {
          sortValue: params.sortValue,
          list: params.list,
        })
        .then((data) => {
          console.log(data);
          console.log(data.data);
          commit("SET_APART_LIST", data.data.list);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getApartList({ commit }, params) {
      commit("SET_DONG_CODE", params.dong);
      http
        .get(
          `/apt?sido=${params.sido}&gugun=${params.gugun}&dong=${params.dong}&key=${params.key}&word=${params.word}`
        )
        .then(({ data }) => {
          console.log(data);
          commit("SET_APART_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    //추가

    getApartDetailList({ commit }, data) {
      commit("CLEAR_APART_LIST");
      console.log("getApartDetailList" + data.aparts[0].aptName);
      // for (let i = data.aparts.length - 1; i >= 0; i--) {
      //   if (data.aparts[i].aptName != data.aptname) {
      //     data.aparts.splice(i, 1);
      //     console.log(data.aparts[i].aptName + "999999999999");
      //     //let arr = [];
      //     // arr.push(data.aparts[i]);
      //     // console.log("arr==============" + arr);
      //     commit("SET_APART_LIST", data.aparts);
      //     return;
      //   }
      // }

      for (var apart of data.aparts) {
        if (apart.aptName == data.aptname) {
          let arr = [
            {
              aptCode: apart.aptCode,
              buildYear: apart.buildYear,
              roadName: apart.roadName,
              dongName: apart.dongName,
              dongCode: apart.dongCode,
              aptName: apart.aptName,
              jibun: apart.jibun,
              lng: apart.lng,
              lat: apart.lat,
            },
          ];
          console.log("arr==============" + arr);
          commit("SET_APART_LIST", arr);
        }
      }
      commit("CLEAR_APART_LIST");
    },
    //로그인
    createUser: async function (context, user) {
      console.log("user" + user);
      console.log(user.id);
      console.log(user.password);
      console.log(user.name);
      console.log(user.email);
      console.log(user.phonenumber);

      console.log("--------------");
      console.log(user.profileimg);
      await http
        .post(`/user`, {
          id: user.id,
          password: user.password,
          name: user.name,
          email: user.email,
          phonenumber: user.phonenumber,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            console.log("register!!" + data);
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          router.push({ name: "userlogin" });
        });
      let formData = new FormData();
      formData.append("profileimg", user.profileimg);
      await axios
        .post(`http://localhost:9999/file/${user.id}`, formData, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then(({ data }) => {
          console.log("여기옴?");
          console.log(data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    loginUser: async function ({ commit }, user) {
      console.log("user" + user.id + " :  " + user.password);
      await http.get(`/user/${user.id}/${user.password}`).then(({ data }) => {
        let msg = "로그인 도중 문제가 발생했습니다.";
        if (data) {
          console.log("login" + data.email);
          msg = "로그인 성공";
        }
        alert(msg);
        commit("LOGIN_USER", data);
        router.push({ name: "Home" });
      });
      await http
        .get(`/file/${user.id}`)
        .then((data) => {
          console.log("여기안옴?");
          console.log(data);
          if (data.data) {
            commit("SET_PROFILE", data.data);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },

    deleteUser: function (context, id) {
      console.log("delete" + id);
      http.delete(`/user/${id}`).then(({ data }) => {
        let msg = "삭제 도중 문제가 발생했습니다.";
        if (data) {
          console.log("login" + data);
          msg = "탈퇴 성공";
        }
        alert(msg);
        router.push({ name: "Home" });
      });
    },

    async updateUser({ commit }, user) {
      console.log("user" + user);
      http
        .put(`/user`, {
          id: user.id,
          password: user.password,
          name: user.name,
          email: user.email,
          phonenumber: user.phonenumber,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            console.log("register" + data);
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          router.push({ name: "Home" });
        });

      let formData = new FormData();
      formData.append("profileimg", user.profileimg);
      console.log("이미지 데이터", user.profileimg);
      await axios
        .put(`http://localhost:9999/file/${user.id}`, formData, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then(({ data }) => {
          console.log(data);
        });
      await http
        .get(`/file/${user.id}`)
        .then((data) => {
          console.log("여기안옴?");
          console.log(data.data);
          var imgsrc = "";
          if (data.data) {
            imgsrc = "data:image/png;base64," + data.data;
          }
          commit("SET_PROFILE", imgsrc);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async userConfirm({ commit }, user) {
      await http
        .get(`/user/${user.id}/${user.password}`)
        .then((response) => {
          if (response.data.message === "success") {
            console.log("success");
            console.log(response);
            let token = response.data["accesstoken"];
            console.log(token);
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("accesstoken", token);
          } else {
            console.log("fail");
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        })
        .catch(() => {});
    },
    async getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      const instance = http;
      instance.defaults.headers["access-token"] =
        sessionStorage.getItem("accesstoken");
      console.log(decode_token);
      await instance
        .post(`/user/info/${decode_token.userid}`)
        .then((response) => {
          console.log(response);
          if (response.data.message === "success") {
            commit("LOGIN_USER", response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        })
        .catch((error) => {
          console.log(error);
        });
      commit("RESET_PROFILE");
      await http
        .get(`/file/${decode_token.userid}`)
        .then((data) => {
          console.log("여기안옴?");
          console.log(data.data);
          if (data.data) {
            var imgsrc = "data:image/png;base64," + data.data;

            commit("SET_PROFILE", imgsrc);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    //아파트
    searchDong({ commit }, area) {
      console.log("searchSido" + area.sidoname);
      http
        .get(
          `/communitysearch/${area.sidoname}/${area.gugunname}/${area.dongname}/${area.keyword}
?pg=${area.currentPage}&perpage=${area.perpage}`
        )
        .then(({ data }) => {
          console.log(data);
          commit("CLEAR_ARTICLES");
          commit("SET_ARTICLES", data.communitylist);
          console.log(data.totalcount);
          commit("SET_COMUTOTALCOUNT", data.totalcount);
        });
    },

    //커뮤니티
    insert_comments({ commit }, no) {
      http.get(`/comments/${no}`).then(({ data }) => {
        console.log(data);
        commit("SET_COMMENTS", data);
      });
    },
    get_comments({ commit }, no) {
      http.get(`/comments/${no}`).then(({ data }) => {
        console.log("get_comments" + data);
        commit("CLEAR_COMMENTS");
        commit("SET_COMMENTS", data);
      });
    },
    delete_comments({ dispatch }, comment) {
      console.log(comment);

      if (confirm("댓글을 삭제하시겠습니까?")) {
        http.delete(`/comments/${comment.no}`).then(({ data }) => {
          console.log(data);
          dispatch("get_comments", comment.communityno);
        });
      }
    },
    update_comments({ dispatch }, comment) {
      console.log(comment);
      http
        .put(`/comments/${comment.no}/${comment.content}`)
        .then(({ data }) => {
          console.log(data);
          alert("댓글 수정 완료");
          dispatch("get_comments", comment.communityno);
        });
    },

    //유저즐겨찾기 추가
    get_userinter({ commit }) {
      http.get(`/userinter/${this.state.loginUser.id}`).then(({ data }) => {
        console.log(data);
        commit("CLEAR_USER_INTER");
        commit("SET_USER_INTER", data);
      });
    },

    getuserintereatail({ commit }, userinter) {
      // http.get(`/userinter/${this.state.loginUser.id}`).then(({ data }) => {
      console.log(
        userinter.selsidocode +
          "/" +
          userinter.selguguncode +
          "/" +
          userinter.seldongcode
      );
      http
        .get(
          `/userinter/${this.state.loginUser.id}/${userinter.selsidocode}/${userinter.selguguncode}/${userinter.seldongcode}`
        )
        .then(({ data }) => {
          console.log(data);

          commit("CLEAR_USER_INTER_DETAIL");
          commit("SET_USER_INTER_DETAIL", data);
        });
    },
    //게시판 검색 기능
    get_boardlist({ commit }, params) {
      http
        .get(`/board?pg=${params.currentPage}&perpage=${params.perpage}`)
        .then(({ data }) => {
          console.log(data);
          commit("CLEAR_BOARDS");
          commit("SET_BOARDS", data.boardlist);
          console.log(data.totalcount);
          commit("SET_TOTALCOUNT", data.totalcount);
        });
    },
    board_search({ commit }, params) {
      http
        .get(
          `/board?pg=${params.currentPage}&perpage=${params.perpage}&select=${params.select}&content=${params.content}`
        )
        .then(({ data }) => {
          console.log(data);
          commit("CLEAR_BOARDS");
          commit("SET_BOARDS", data.boardlist);
          console.log(data.totalcount);
          commit("SET_TOTALCOUNT", data.totalcount);
        });
    },

    //음식점 찾기
    get_foods({ commit }) {
      http
        .get(
          `/crawling/${this.state.selSidoCode}/${this.state.selGugunCode}/${this.state.selDongCode}`
        )
        .then(({ data }) => {
          console.log(data);
          commit("CLEAR_FOODS");
          commit("SET_FOODS", data.foods);
          commit("CLEAR_CAFES");
          commit("SET_CAFES", data.cafes);
        });
    },
  },
  modules: {},
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
