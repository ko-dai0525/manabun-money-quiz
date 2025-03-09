<template>
  <div id="app">
    <QuizList />
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import QuizList from "./components/QuizList.vue";

export default defineComponent({
  name: "App",
  components: {
    QuizList,
  },
});
</script>

<style>
/* グローバルなスタイルを記述 */
</style>
