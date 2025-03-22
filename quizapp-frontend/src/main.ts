import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";

// createApp(App).mount("#app");
// App.use(router);
const app = createApp(App);
app.use(router); // ← この順番が重要！
app.mount("#app");
