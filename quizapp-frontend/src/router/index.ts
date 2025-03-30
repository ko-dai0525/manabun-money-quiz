import {
  createRouter,
  createWebHistory,
  type NavigationGuardNext,
  type RouteLocationNormalized,
} from "vue-router";
import QuizPlayer from "../components/QuizPlayer.vue";
import ResultView from "../components/ResultView.vue";
import Home from "../components/Home.vue";
import QuizRegister from "../components/QuizRegister.vue";
import AdminLogin from "../components/AdminLogin.vue";
import QuizManage from "../components/QuizManage.vue";

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/quiz",
    name: "QuizPlayer",
    component: QuizPlayer,
  },
  {
    path: "/result",
    name: "ResultView",
    component: ResultView,
  },
  {
    path: "/register",
    name: "QuizRegister",
    component: QuizRegister,
    beforeEnter: (
      _to: RouteLocationNormalized,
      _from: RouteLocationNormalized,
      next: NavigationGuardNext
    ) => {
      const storedPin = localStorage.getItem("adminPin");
      if (storedPin === "1234") {
        next();
      } else {
        next({
          path: "/admin",
          query: { redirect: _to.fullPath },
        });
      }
    },
  },
  {
    path: "/admin",
    name: "AdminLogin",
    component: AdminLogin,
  },
  {
    path: "/manage",
    name: "QuizManage",
    component: QuizManage,
    beforeEnter: (
      _to: RouteLocationNormalized,
      _from: RouteLocationNormalized,
      next: NavigationGuardNext
    ) => {
      const storedPin = localStorage.getItem("adminPin");
      if (storedPin === "1234") {
        next();
      } else {
        next({
          path: "/admin",
          query: { redirect: _to.fullPath },
        });
      }
    },
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
