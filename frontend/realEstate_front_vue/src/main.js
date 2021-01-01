import Vue from "vue";
import "./plugins/axios";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vueGoogleMapWrapper from '@/plugins/vue-google-map-wrapper'

Vue.config.productionTip = false;

Vue.use(vueGoogleMapWrapper, {
  apiKey: 'AIzaSyBCOwfIFyVnOJlh7WWvHmjCaTrk0rvbDJA'
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");