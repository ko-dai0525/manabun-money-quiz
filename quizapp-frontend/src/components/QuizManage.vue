<template>
  <div class="quiz-list">
    <h2>登録済みのクイズ一覧</h2>
    <div class="action-button">
      <RouterLink to="/">🏠 ホームに戻る</RouterLink>
      <button @click="deleteSelectedQuizzes">🗑 選択したクイズを削除</button>
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
            <tr
              v-for="quiz in quizzes"
              :key="quiz.id"
              :class="{ selected: selectedIds.includes(quiz.id) }"
              @click="toggleSelection(quiz.id)"
            >
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
import { deleteQuiz } from "../api";

const quizzes = ref<Quiz[]>([]);
const selectedIds = ref<number[]>([]);
const toggleSelection = (id: number) => {
  if (selectedIds.value.includes(id)) {
    selectedIds.value = selectedIds.value.filter(
      (selectedId) => selectedId !== id
    );
  } else {
    selectedIds.value.push(id);
  }
};

const deleteSelectedQuizzes = async () => {
  if (selectedIds.value.length === 0) {
    alert("削除するクイズを選択してください。");
    return;
  }

  const confirmed = confirm(
    `${selectedIds.value.length}件のクイズを削除しますか？`
  );
  if (!confirmed) return;

  try {
    await Promise.all(selectedIds.value.map((id) => deleteQuiz(id)));
    quizzes.value = quizzes.value.filter(
      (quiz) => !selectedIds.value.includes(quiz.id)
    );
    selectedIds.value = [];
    alert("クイズを削除しました。");
  } catch (error) {
    console.error("Error deleting quizzes:", error);
    alert("クイズの削除に失敗しました。");
  }
};

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

.action-buttons {
  display: flex;
  justify-content: space-between;
  margin-bottom: 1rem;
}

.action-buttons button {
  background-color: #f44336;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 5px;
  cursor: pointer;
}

.table-wrapper {
  background-color: rgba(255, 255, 255, 0.9); /* 半透明の白背景 */
  padding: 1rem;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

tr.selected {
  background-color: #fff176;
  cursor: pointer;
  color: #333; /* 文字を濃く */
  border-left: 4px solid #ffeb3b; /* 強調線などもOK */
}

tr:hover {
  background-color: #f5f5f5;
}
</style>
