<template>
  <div v-if="quizList.length && currentQuiz">
    <h2>{{ currentQuiz.questionText }}</h2>
    <ul>
      <li
        v-for="(option, index) in options"
        :key="index"
        :class="{
          correct: selected === index + 1 && isCorrect,
          wrong: selected === index + 1 && !isCorrect,
        }"
        @click="selectAnswer(index + 1)"
      >
        {{ option }}
      </li>
    </ul>

    <div v-if="selected">
      <p v-if="isCorrect">⭕ 正解！</p>
      <p v-else>❌ 不正解... 正解は {{ correctOption }} です。</p>
      <button @click="nextQuestion">
        {{ isLastQuestion ? "結果を見る" : "次の問題へ" }}
      </button>
    </div>
  </div>
  <div v-else>
    <p>読み込み中...</p>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, computed } from "vue";
import { fetchQuizzes, type Quiz } from "../api";

const quizList = ref<Quiz[]>([]);
const currentIndex = ref(0);
const selected = ref<number | null>(null);
const isCorrect = ref(false);

const currentQuiz = computed(() => quizList.value[currentIndex.value]);

const options = computed(() =>
  currentQuiz.value
    ? [
        currentQuiz.value.option1,
        currentQuiz.value.option2,
        currentQuiz.value.option3,
        currentQuiz.value.option4,
      ]
    : []
);

const correctOption = computed(() => {
  if (!currentQuiz.value) return "";
  return options.value[currentQuiz.value.answer - 1];
});

const isLastQuestion = computed(() => {
  return currentIndex.value === quizList.value.length - 1;
});

const selectAnswer = (choice: number) => {
  if (selected.value !== null) return; // すでに回答済み
  selected.value = choice;
  isCorrect.value = choice === currentQuiz.value?.answer;
};

const nextQuestion = () => {
  selected.value = null;
  isCorrect.value = false;
  if (!isLastQuestion.value) {
    currentIndex.value += 1;
  } else {
    alert("クイズ終了！"); // 今後、結果画面に遷移してもOK
  }
};

onMounted(async () => {
  quizList.value = await fetchQuizzes();
});
</script>

<style scoped>
li {
  cursor: pointer;
  margin-bottom: 0.5rem;
}
.correct {
  color: green;
}
.wrong {
  color: red;
}
</style>
