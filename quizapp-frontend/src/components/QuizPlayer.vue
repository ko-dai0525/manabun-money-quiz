<template>
  <!-- クイズが存在する場合 -->
  <div v-if="quizList.length && currentQuiz">
    <h2>第{{ currentIndex + 1 }}問</h2>
    <p class="score-status">正解数：{{ score }} / {{ quizList.length }}</p>
    <div class="progress-bar">
      <div
        class="progress"
        :style="{ width: (currentIndex / quizList.length) * 100 + '%' }"
      ></div>
    </div>
    <h3>{{ currentQuiz.questionText }}</h3>
    <ul>
      <li
        v-for="(option, index) in options"
        :key="index"
        :class="{ selected: selected === index + 1 }"
        @click="selected = index + 1"
      >
        {{ option }}
      </li>
    </ul>
    <!-- 確定ボタンを追加-->
    <div v-if="selected && !showResult">
      <button @click="checkAnswer">確定</button>
    </div>

    <div v-if="showResult">
      <p v-if="isCorrect">⭕ 正解！</p>
      <p v-else>❌ 不正解... 正解は {{ correctOption }} です。</p>
      <button @click="nextQuestion">
        {{ isLastQuestion ? "結果を見る" : "次の問題へ" }}
      </button>
    </div>
  </div>

  <!-- クイズが存在しない場合 -->
  <div v-else-if="quizList.length === 0">
    <p>このカテゴリには問題がまだありません。</p>
  </div>

  <!-- ローディング中 or 例外 -->
  <div v-else>
    <p>読み込み中...</p>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, computed } from "vue";
import { fetchQuizzes, fetchQuizzesByCategory, type Quiz } from "../api";
import { useRouter } from "vue-router";
import { useRoute } from "vue-router";

const quizList = ref<Quiz[]>([]);
const currentIndex = ref(0);
const selected = ref<number | null>(null);
const isCorrect = ref(false);

const currentQuiz = computed(() => {
  return quizList.value[currentIndex.value] || null;
});

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

const score = ref(0);
const router = useRouter();

// const selectAnswer = (choice: number) => {
//   if (selected.value !== null) return; // すでに回答済み
//   selected.value = choice;
//   const correct = choice === currentQuiz.value?.answer;
//   isCorrect.value = correct;
//   if (correct) {
//     score.value += 1;
//   }
// };

const showResult = ref(false); // 結果表示フラグ

const checkAnswer = () => {
  if (!currentQuiz.value) return;
  const correct = selected.value === currentQuiz.value.answer;
  isCorrect.value = correct;
  if (correct) score.value++;
  showResult.value = true;
};

const nextQuestion = () => {
  selected.value = null;
  isCorrect.value = false;
  showResult.value = false;
  if (!isLastQuestion.value) {
    currentIndex.value++;
  } else {
    router.push({
      name: "ResultView",
      query: { score: score.value, total: quizList.value.length },
    });
  }
};

onMounted(async () => {
  const QUESTION_COUNT = 5;
  const route = useRoute();
  const category = route.query.category as string;

  let quizzes: Quiz[];

  if (category) {
    quizzes = await fetchQuizzesByCategory(category);
  } else {
    quizzes = await fetchQuizzes();
  }

  // シャッフルして5問に絞り、5問に満たない場合も表示する
  quizList.value = quizzes
    .sort(() => Math.random() - 0.5)
    .slice(0, Math.min(quizzes.length, QUESTION_COUNT));
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
.selected {
  background-color: #eef;
}
.score-status {
  font-weight: bold;
  font-size: 1rem;
  color: #555;
  margin: 0.5rem 0;
}
.progress-bar {
  width: 100%;
  height: 10px;
  background-color: #eee;
  border-radius: 5px;
  overflow: hidden;
  margin: 1rem 0;
}

.progress {
  height: 100%;
  background-color: #ffd700;
  transition: width 0.3s ease;
}
</style>
