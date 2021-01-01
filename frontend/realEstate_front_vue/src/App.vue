<template>
  <div id="app">
    <div>
      <b-navbar toggleable="lg" type="dark" variant="warning">
        <b-navbar-brand>
          <router-link to="/">
            <img
              src="@/assets/title_logo.png"
              class="d-inline-block align-middle"
              width="60px"
              alt="Kitten"
            />
          </router-link>
        </b-navbar-brand>
        <b-button variant="outline-warning">
          <router-link class="myColor" to="/search">방 찾기</router-link>
        </b-button>
        <b-button variant="outline-warning">
          <router-link class="myColor" to="/News">부동산 뉴스</router-link>
        </b-button>
        <b-button variant="outline-warning" class="kk" @click="checkLogin">쉽게 방 찾기</b-button>
        <b-button variant="outline-warning">
          <router-link class="myColor" to="/board">QnA</router-link>
        </b-button>
        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto" v-if="getAccessToken || storageDate">
            <b-nav-item class="myColor" v-if="getAccessToken">{{ getUserId }}님 환영합니다.</b-nav-item>
            <b-nav-item class="myColor" v-else-if="storageDate">{{ storageDate }}님 환영합니다.</b-nav-item>
            <b-nav-item>
              <router-link to="/me">내정보보기</router-link>
            </b-nav-item>
            <b-nav-item @click.prevent="onClickLogout">로그아웃</b-nav-item>
          </b-navbar-nav>
          <b-navbar-nav class="ml-auto" v-else>
            <b-nav-item>
              <router-link to="/login">로그인</router-link>
            </b-nav-item>
            <b-nav-item>
              <router-link to="/join">회원가입</router-link>
            </b-nav-item>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>
    <router-view />
    <Footer></Footer> <!-- 추가 ****************************************************  -->
  </div>
</template>

<script>
import Vue from "vue";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

//mapGetters 등록
import { mapGetters } from "vuex";
import Footer from "@/views/Footer.vue" 
export default {
  data() {
    return {
      isLogin: false,
      storageDate: ""
    };
  },
  components:{  
    Footer
  },
  created() {
    const memberInfo = JSON.parse(sessionStorage.getItem("id"));
    console.log(memberInfo);
    if (memberInfo) {
      this.storageDate = memberInfo.items[0];
    }
  },
  computed: {
    ...mapGetters(["getAccessToken", "getUserId", "getUserName"])
  },
  methods: {
    onClickLogout() {
      this.storageDate = "";
      this.$store
        .dispatch("LOGOUT")
        .then(() => this.$router.replace("/").catch(() => {}));
    },
    checkLogin() {
      let checkSession = JSON.parse(sessionStorage.getItem("id"));
      console.log(checkSession);
      if (checkSession == null) {
        alert("로그인후 이용해 주세요.");
      } else {
        location.href = "/kakao.html";
      }
    },
  }
};
</script>
<style>
.kk {
  color: black;
  font-size: 19px;
}
.myColor {
  color: black;
  font-size: 19px;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

a {
  color: rgb(219, 250, 250);
}

a:hover {
  color: rgb(219, 250, 250);
  text-decoration: none;
}
</style>
