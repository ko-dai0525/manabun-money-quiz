<template>
  <div id="app">
    <!-- <QuizList /> -->
    <QuizPlayer />
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
// import QuizList from "./components/QuizList.vue";
import QuizPlayer from "./components/QuizPlayer.vue";

export default defineComponent({
  name: "App",
  components: {
    QuizPlayer,
  },
});
</script>

<style>
/* グローバルなスタイルを記述 */
</style>
