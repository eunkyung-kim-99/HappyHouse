/** @format */

import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import ApartIndex from "@/components/apart/ApartIndex.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },

  {
    path: "/apart",
    name: "apart",
    component: () => import("@/views/ApartView.vue"),
    redirect: "/apart/index",
    children: [
      {
        path: "index",
        name: "index",
        component: ApartIndex,
      },
      {
        path: "nearbysearch",
        name: "nearbysearch",
        component: () =>
          import("@/components/apart/NearbySearch/ApartNearbySearch.vue"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/BoardView.vue"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "write",
        name: "boardRegister",
        component: () => import("@/components/board/BoardRegister.vue"),
      },
      {
        path: "detail/:articleno",
        name: "boardDetail",
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:articleno",
        name: "boardModify",
        component: () => import("@/components/board/BoardModify.vue"),
      },
      {
        path: "delete/:articleno",
        name: "boardDelete",
        component: () => import("@/components/board/BoardDelete.vue"),
      },
    ],
  },
  {
    path: "/community",
    name: "community",
    component: () => import("@/views/CommunityView.vue"),
    redirect: "/community/list",
    children: [
      {
        path: "list",
        name: "CommunityList",
        component: () => import("@/components/community/CommunityList.vue"),
      },
      {
        path: "write",
        name: "CommunityRegister",
        component: () => import("@/components/community/CommunityRegister.vue"),
      },
      {
        path: "detail/:articleno",
        name: "CommunityDetail",
        component: () => import("@/components/community/CommunityDetail.vue"),
      },
      {
        path: "modify/:articleno",
        name: "CommunityModify",
        component: () => import("@/components/community/CommunityModify.vue"),
      },
      {
        path: "delete/:articleno",
        name: "CommunityDelete",
        component: () => import("@/components/community/CommunityDelete.vue"),
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/MemberView.vue"),
    redirect: "/user/login",
    children: [
      {
        path: "login",
        name: "userlogin",
        component: () => import("@/components/user/LoginView.vue"),
      },
      {
        path: "register",
        name: "userregister",
        component: () => import("@/components/user/RegisterView.vue"),
      },
      {
        path: "detail",
        name: "userdetail",
        component: () => import("@/components/user/DetailView.vue"),
      },
    ],
  },
  {
    path: "/userinter",
    name: "userinter",
    component: () => import("@/views/UserInterView.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
