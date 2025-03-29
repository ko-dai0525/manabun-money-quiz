<template>
  <div class="home-container">
    <h1 class="title">まなぶん！おかねクイズ</h1>
    <p class="subtitle">お金のこと、楽しく学ぼう！</p>
    <p class="category-button">
      <button @click="startQuiz('買い物')">買い物</button>
      <button @click="startQuiz('貯金')">貯金</button>
    </p>
    <button @click="startQuiz('未分類')" class="start-button">
      クイズをはじめる
    </button>
  </div>
  <!-- クイズ登録ボタンを追加 -->
  <router-link to="/register" class="nav-button">クイズを登録する</router-link>
  <!-- クイズ管理画面 -->
  <router-link to="/manage" class="nav-button">クイズ管理画面</router-link>
</template>

<script lang="ts" setup>
import { useRouter } from "vue-router";
const router = useRouter();

const startQuiz = (category: string) => {
  router.push({ path: "/quiz", query: { category } });
};
</script>

<style scoped>
.home-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  /* background: linear-gradient(to bottom right, #fff9e6, #e6f7ff); */
  text-align: center;
  padding: 2rem;
}

.title {
  font-size: 2.5rem;
  font-weight: bold;
  margin-bottom: 0.5rem;
  color: #333;
}

.subtitle {
  font-size: 1.2rem;
  margin-bottom: 2rem;
  color: #666;
}

.mascot {
  width: 200px;
  height: auto;
  margin-bottom: 2rem;
}

.start-button {
  background-color: #ffd700;
  color: #333;
  border: none;
  padding: 1rem 2rem;
  font-size: 1.2rem;
  border-radius: 10px;
  cursor: pointer;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s ease;
}

.start-button:hover {
  background-color: #ffcc00;
}

.nav-button {
  display: inline-block;
  margin: 1rem;
  padding: 0.75rem 1.5rem;
  background-color: #f0f0f0;
  color: #333;
  text-decoration: none;
  border-radius: 8px;
  font-weight: bold;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s;
}

.nav-button:hover {
  background-color: #e0e0e0;
}
</style>
