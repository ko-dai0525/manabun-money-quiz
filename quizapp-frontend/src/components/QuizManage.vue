<template>
  <div class="quiz-list">
    <h2>登録済みのクイズ一覧</h2>
    <div class="home-back-button">
      <RouterLink to="/">🏠 ホームに戻る</RouterLink>
    </div>

    <div class="table-wrapper">
      <div v-if="quizzes.length">
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>カテゴリ</th>
              <th>問題文</th>
              <th>正解</th>
              <th>解説</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="quiz in quizzes" :key="quiz.id">
              <td>{{ quiz.id }}</td>
              <td>{{ quiz.category }}</td>
              <td>{{ quiz.questionText }}</td>
              <td>{{ getCorrectOption(quiz) }}</td>
              <td>{{ quiz.explanation }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <p v-else>現在登録されているクイズはありません。</p>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, ref } from "vue";
import { fetchQuizzes, type Quiz } from "../api";

const quizzes = ref<Quiz[]>([]);

onMounted(async () => {
  quizzes.value = await fetchQuizzes();
});

/**
 * クイズの正解テキストを取得する(型安全)
 */
function getCorrectOption(quiz: Quiz): string {
  const options = [quiz.option1, quiz.option2, quiz.option3, quiz.option4];
  const index = quiz.answer - 1;

  if (index >= 0 || index < options.length) {
    return options[index];
  } else {
    return "不明";
  }
}
</script>

<style scoped>
.quiz-list {
  padding: 1rem;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 1rem;
}

th,
td {
  padding: 0.75rem;
  border: 1px solid #ddd;
  text-align: left;
  vertical-align: top;
}

th {
  background-color: #f5f5f5;
}

td {
  font-size: 0.95rem;
}

.home-back-button {
  text-align: right;
  margin-bottom: 1rem;
  text-decoration: none;
  font-size: 1rem;
}

.table-wrapper {
  background-color: rgba(255, 255, 255, 0.9); /* 半透明の白背景 */
  padding: 1rem;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}
</style>
