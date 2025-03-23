<template>
  <div>
    <router-view />
    <img src="/src/assets/mascot.png" alt="マスコット" class="mascot-right" />
    <img src="/src/assets/mascot-bg.png" class="background-image" />
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
.background-image {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  object-fit: cover;
  opacity: 0.1; /* ← 背景っぽくなる！ */
  z-index: -1;
  pointer-events: none; /* クリックブロック防止 */
}

.mascot-right {
  position: fixed;
  bottom: 0;
  right: 0;
  width: 200px;
  opacity: 0.95;
  z-index: 10;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  border-radius: 12px;
  filter: brightness(1.05) contrast(0.95) saturate(0.9);
  opacity: 0.9;
}
</style>
