import { createRouter, createWebHistory } from "vue-router";
import QuizPlayer from "../components/QuizPlayer.vue";
import ResultView from "../components/ResultView.vue";
import Home from "../components/Home.vue";
import QuizRegister from "../components/QuizRegister.vue";

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
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
