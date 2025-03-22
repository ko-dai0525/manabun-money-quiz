import { createRouter, createWebHistory } from "vue-router";
import QuizPlayer from "../components/QuizPlayer.vue";
import ResultView from "../components/ResultView.vue";

const routes = [
  {
    path: "/",
    name: "QuizPlayer",
    component: QuizPlayer,
  },
  {
    path: "/result",
    name: "ResultView",
    component: ResultView,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
