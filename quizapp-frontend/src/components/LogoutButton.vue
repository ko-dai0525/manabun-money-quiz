<template>
  <button @click="logout" class="logout-button">🔓 ログアウト</button>
</template>

<script lang="ts" setup>
import { useRouter } from "vue-router";
import axios from "axios";

const router = useRouter();

const logout = () => {
  sessionStorage.removeItem("adminPin");
  delete axios.defaults.headers.common["Authorization"];
  router.push("/");
};
</script>

<style scoped>
.logout-button {
  background-color: #ccc;
  border: none;
  padding: 0.5rem 1rem;
  margin-left: auto;
  font-size: 1rem;
  border-radius: 6px;
  cursor: pointer;
}
</style>
