import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default new Vuex.Store({
  state: {
    accessToken: null,
    userId: "",
    userName: ""
  },
  getters: { // computed역할
    getAccessToken(state) {
      return state.accessToken;
    },
    getUserId(state) {
      return state.userId;
    },
    getUserName(state) {
      return state.userName;
    }
  },
  mutations: {
    LOGIN(state, payload) {
      state.accessToken = payload["auth-token"];
      state.userId = payload["user-id"];
      state.userName = payload["user-name"];
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.userId = "";
      state.userName = "";
    }
  },
  actions: {
    LOGIN(context, user) {
      return axios
        .post(`${SERVER_URL}/member/confirm/login`, user)
        .then((response) => {
          context.commit("LOGIN", response.data);
          let tmpObj = {
            id: "id",
            items: []
          }

          tmpObj.items.push(response.data["user-id"])
          tmpObj.items.push(response.data["user-pw"])
          tmpObj.items.push(response.data["user-name"])
          tmpObj.items.push(response.data["user-email"])
          tmpObj.items.push(response.data["user-joindate"])
          tmpObj.items.push(response.data["user-phone"])
          tmpObj.items.push(response.data["user-interLoc"])
          sessionStorage.setItem('id', JSON.stringify(tmpObj));
          axios.defaults.headers.common[ // auth-token을 이름으로 headers에 ${response.data["auth-token"]}이 정보를 담아라
            "auth-token"
          ] = `${response.data["auth-token"]}`;
        });
    },
    LOGOUT(context) {
      context.commit("LOGOUT");
      sessionStorage.clear();
      axios.defaults.headers.common["auth-token"] = undefined;
    }
  },
  modules: {}
});