<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
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
