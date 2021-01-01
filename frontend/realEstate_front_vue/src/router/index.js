import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home.vue";
import Login from "@/views/Login.vue";
import Me from "@/views/Me.vue";
import Search from "@/views/Search.vue";
import Join from "@/views/Join.vue";
import Board from "@/views/Board.vue";
import Create from "@/views/Create.vue";
import Detail from "@/views/Detail.vue";
import Update from '@/views/Update.vue'
import Delete from '@/views/Delete.vue'
import MemberUpdate from '@/views/MemberUpdate.vue'
import store from "@/store";
import News from '@/views/News.vue'  // 추가********************************

Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const requireAuth = () => (to, from, next) => {
  const nextRoute = to.path;

  if (store.getters.getAccessToken || sessionStorage.getItem('id')) {
    return next();
  } else next("/login" + nextRoute);
};

const routes = [{
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/login",
    name: "Login",
    component: Login
  },
  {
    path: "/join",
    name: "Join",
    component: Join
  },
  {
    path: "/news",   
    name: "news",
    component: News
  },
  {
    path: "/memberupdate",
    name: "MemberUpdate",
    component: MemberUpdate
  },
  {
    path: "/board",
    name: "Board",
    component: Board
  },
  {
    path: "/board/delete/:no",
    name: "Delete",
    component: Delete
  },
  {
    path: "/board/update/:no",
    name: "Update",
    component: Update
  },
  {
    path: "/board/create",
    name: "create",
    component: Create
  },
  {
    path: "/board/detail/:no",
    name: "detail",
    component: Detail
  },
  {
    path: "/search",
    name: "Search",
    component: Search
  },
  {
    path: "/login/:nextRoute",
    name: "LoginNextRoute",
    component: Login
  },
  {
    path: "/me",
    name: "Me",
    component: Me,
    beforeEnter: requireAuth()
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;