import Vue from "vue";
import Empty from "./Empty.vue";
// import App from './App.vue'
import router from "./router";

Vue.config.productionTip = false;

new Vue({
  router,
  render: (h) => h(Empty),
}).$mount("#app");
