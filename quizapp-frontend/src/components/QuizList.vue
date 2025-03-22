<!-- src/components/QuizList.vue -->
<template>
  <div v-if="quizzes.length">
    <div v-for="quiz in quizzes" :key="quiz.id" class="quiz-card">
      <h2>{{ quiz.questionText }}</h2>
      <ul>
        <li>{{ quiz.option1 }}</li>
        <li>{{ quiz.option2 }}</li>
        <li>{{ quiz.option3 }}</li>
        <li>{{ quiz.option4 }}</li>
      </ul>
    </div>
  </div>
  <div v-else>
    <p>Loading...</p>
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
.quiz-card {
  margin-bottom: 1rem;
  padding: 1rem;
  border: 1px solid #ccc;
}
</style>
