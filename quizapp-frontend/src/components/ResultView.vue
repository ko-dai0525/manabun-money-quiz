<template>
  <div>
    <h2>クイズ結果</h2>
    <p>あなたのスコア: {{ score }} / {{ total }}</p>
    <button @click="goHome">もう一度チャレンジ</button>
  </div>
</template>

<script lang="ts" setup>
import { useRoute, useRouter } from "vue-router";

const route = useRoute();
const router = useRouter();

const score = Number(route.query.score);
const total = Number(route.query.total);

const goHome = () => {
  router.push("/");
};
</script>
