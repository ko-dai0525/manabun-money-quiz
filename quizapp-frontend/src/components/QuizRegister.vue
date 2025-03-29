<template>
  <div class="register-container">
    <h2>クイズ登録フォーム</h2>
    <div class="home-back-button">
      <RouterLink to="/">🏠 ホームに戻る</RouterLink>
    </div>
    <form @submit.prevent="submitQuiz">
      <label>問題文</label>
      <textarea v-model="quiz.questionText" required></textarea>

      <label>選択肢1</label>
      <input v-model="quiz.option1" type="text" required />

      <label>選択肢2</label>
      <input v-model="quiz.option2" type="text" required />

      <label>選択肢3</label>
      <input v-model="quiz.option3" type="text" required />

      <label>選択肢4</label>
      <input v-model="quiz.option4" type="text" required />

      <label>正解番号（1〜4）</label>
      <input
        v-model.number="quiz.answer"
        type="number"
        min="1"
        max="4"
        required
      />

      <label>解説</label>
      <textarea v-model="quiz.explanation" required></textarea>

      <label>カテゴリ</label>
      <select v-model="quiz.category" required>
        <option value="">-- カテゴリを選択 --</option>
        <option value="おこづかい">おこづかい</option>
        <option value="買い物">買い物</option>
        <option value="貯金">貯金</option>
      </select>

      <div class="submit-wrapper">
        <button type="submit">登録する</button>
      </div>
    </form>
  </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import { createQuiz } from "../api";

const quiz = ref({
  questionText: "",
  option1: "",
  option2: "",
  option3: "",
  option4: "",
  answer: 1,
  explanation: "",
  category: "",
});

const submitQuiz = async () => {
  try {
    await createQuiz(quiz.value);
    alert("クイズを登録しました");
    // フォーム初期化
    quiz.value = {
      questionText: "",
      option1: "",
      option2: "",
      option3: "",
      option4: "",
      answer: 1,
      explanation: "",
      category: "",
    };
  } catch (error) {
    alert("登録に失敗しました");
    console.error(error);
  }
};
</script>

<style scoped>
.register-container {
  max-width: 600px;
  margin: 2rem auto;
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

label {
  font-weight: bold;
  margin-top: 0.5rem;
}

input,
select,
textarea {
  width: 100%;
  padding: 0.5rem;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

.submit-wrapper {
  display: flex;
  justify-content: center;
  margin-top: 1rem;
}

button {
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 0.75rem;
  font-size: 1rem;
  border-radius: 6px;
  cursor: pointer;
}

button:hover {
  background-color: #45a049;
}

.home-back-button {
  text-align: right;
  margin-bottom: 1rem;
  text-decoration: none;
  font-size: 1rem;
}
</style>
