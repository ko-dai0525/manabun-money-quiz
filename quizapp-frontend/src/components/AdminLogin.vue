<template>
  <div class="admin-login">
    <h2>管理者ログイン</h2>
    <p>PINコードを入力してください</p>

    <input
      v-model="pin"
      type="password"
      maxlength="10"
      placeholder="4桁のPINコード"
    />
    <button @click="submitPin">ログイン</button>

    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
  </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import { useRouter, useRoute } from "vue-router";
import axios from "axios";

const router = useRouter();
const route = useRoute();
const pin = ref("");
const errorMessage = ref("");

const submitPin = () => {
  if (pin.value.trim() === "") {
    errorMessage.value = "PINコードを入力してください。";
    return;
  }

  // 仮の正解PIN（バックエンドと同期が必要）
  const correctPin = "1234";

  if (pin.value === correctPin) {
    sessionStorage.setItem("adminPin", pin.value);
    axios.defaults.headers.common["Authorization"] = `Bearer ${pin.value}`;

    // 遷移元の画面が query に入っている場合はそこに戻す
    const redirectPath = route.query.redirect || "/";
    router.push(String(redirectPath));
  } else {
    errorMessage.value = "PINコードが間違っています。";
  }
};
</script>

<style scoped>
.admin-login {
  max-width: 400px;
  margin: 5rem auto;
  padding: 2rem;
  background-color: #ffffffcc;
  border-radius: 10px;
  box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

input {
  padding: 0.5rem;
  width: 80%;
  margin-bottom: 1rem;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  background-color: #ffd700;
  border: none;
  padding: 0.5rem 1.5rem;
  font-size: 1rem;
  cursor: pointer;
  border-radius: 5px;
}

.error {
  color: red;
  margin-top: 1rem;
}
</style>
