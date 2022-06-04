/** @format */

import Vue from "vue";
import Vuex from "vuex";
import http from "@/api/http";
import router from "@/router";
import createPersistedState from "vuex-persistedstate";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    aparts: [],
    house: null,
    loginUser: null,
    todos: [
      // { title: '할 일1', completed: false },
      // { title: '할 일2', completed: false },
    ],
    articles: [],
    arrayList: [],
  },
  getters: {
    loginUser: function (state) {
      return state.loginUser;
    },
  },
  mutations: {
    SET_BOARD_LIST(state, articles) {
      state.articles = articles;
    },
    SET_ARRAYLIST_LIST(state, articles) {
      articles.forEach((article) => {
        state.arrayList.push([article.no, article.id, article.subject]);
      });
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
    LOGIN_USER: function (state, user) {
      state.loginUser = user;
    },
    LOGOUT: function (state) {
      state.loginUser = null;
    },
  },
  actions: {
    getBoardList({ commit }) {
      http.get(`/board`).then(({ data }) => {
        console.log(data);
        commit("CLEAR_BOARD_LIST", data);
        commit("SET_ARRAYLIST_LIST", data);
      });
    },
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
    getApartList({ commit }, params) {
      http
        .get(
          `/apt?sido=${params.sido}&gugun=${params.gugun}&dong=${params.dong}`
        )
        .then(({ data }) => {
          console.log(data);
          commit("SET_APART_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    createUser: function (context, user) {
      console.log("user" + user);
      http
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
    },
    loginUser: function ({ commit }, user) {
      console.log("user" + user.id + " :  " + user.password);
      http.get(`/user/${user.id}/${user.password}`).then(({ data }) => {
        let msg = "로그인 도중 문제가 발생했습니다.";
        if (data) {
          console.log("login" + data.email);
          msg = "로그인 성공";
        }
        alert(msg);
        commit("LOGIN_USER", data);
        router.push({ name: "Home" });
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

    updateUser: function (context, user) {
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
