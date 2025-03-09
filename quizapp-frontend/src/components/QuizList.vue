<template>
  <div>
    <h1>Quiz List</h1>
    <ul>
      <li v-for="quiz in quizzes" :key="quiz.id">
        {{ quiz.question }} - {{ quiz.answer }}
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from "vue";
import { fetchQuizzes } from "../api";
import type { Quiz } from "../api";

export default defineComponent({
  name: "QuizList",
  setup() {
    const quizzes = ref<Quiz[]>([]);

    onMounted(async () => {
      try {
        quizzes.value = await fetchQuizzes();
      } catch (error) {
        console.error("Failed to fetch quizzes", error);
      }
    });

    return { quizzes };
  },
});
</script>

<style scoped>
/* 必要に応じてスタイルを記述 */
</style>
