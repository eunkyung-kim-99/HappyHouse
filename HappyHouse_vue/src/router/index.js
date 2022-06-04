/** @format */

import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import ApartView from "@/views/ApartView";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/apartindex",
    name: "Apart",
    component: ApartView,
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
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
