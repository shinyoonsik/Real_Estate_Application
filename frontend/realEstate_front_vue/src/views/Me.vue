<template>
<div v-if="user" class="container">
  <div id="header">
    <br>
		<h1 class="alignCenter">
      <!--
			<img alt="HAPPY HOUSE" src="@/assets/title_logo.png"
				style="height: 100px;">  style="margin-left: 130px;"-->
        My page
		</h1>
    <br>
	</div>
	<div id="content">
		
			<fieldset class="view_form">
				<div class="form-group">
          <span class="float-left">아이디</span>
					<input type="text" class="form-control form-control-lg" name="usr" v-model="user.items[0]" readonly>
				</div>
				<div class="form-group">
			    <span class="float-left">비밀번호</span>
          <input type="password" class="form-control form-control-lg" name="pwd" v-model="user.items[1]" readonly>
				</div>
				<div class="form-group">
          <span class="float-left">이름</span>
					<input type="text" class="form-control form-control-lg" name="name" v-model="user.items[2]" readonly>
				</div>
				<div class="form-group">
          <span class="float-left">이메일</span>
					<input type="email" class="form-control form-control-lg" name="email" v-model="user.items[3]" readonly>
				</div>
				<div class="form-group">
          <span class="float-left">생년월일</span>
					<input type="text" class="form-control form-control-lg" name="birth"  v-model="user.items[4]" readonly>
				</div>
				<br>
				<div class="form-group">
          <span class="float-left">휴대전화</span>
					<input type="tel" class="form-control form-control-lg" name="phone" v-model="user.items[5]" readonly>
				</div>
				<div class="form-group">
          <span class="float-left">관심지역</span>
					<input type="text" class="form-control form-control-lg" name="interestLocation" v-model="user.items[6]" readonly>
				</div>
				<br>

        <button class="btn btn-link"><router-link class="fs" to="/memberupdate" >정보 수정</router-link></button>
		    <button class="btn btn-link fs" @click="deleteInfo">회원 탈퇴</button>
			</fieldset>
		
	</div>
	<div id="footer" class="container">
		<div class="bg"></div>
    <br>
		<p class="align-center">
			<a class="text-info">HappyHouse Corp</a> &nbsp;
			<a class="text-info">고객센터</a>
		</p>
	</div>
	
</div>

</template>

<script>
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data() {
    return {
      user: null,
    };
  },
  created() {
    const tmpObj = JSON.parse(sessionStorage.getItem('id'));
   // console.log( tmpObj);
    if(tmpObj){
      this.user = tmpObj;
      console.log(this.user.items);
    }
    else{
      /*
      axios
        .get(`${SERVER_URL}/member/info`)
        .then((response) => {
          this.user = response.data.user;
          console.log(this.user);
        })
        .catch(() => {
          this.$store.dispatch("LOGOUT").then(() => this.$router.replace("/"));
        });*/
    }
  },
  methods: {
    deleteInfo(){
       let flag = confirm("탈퇴하시겠습니까?");
       console.log(flag);
       if(flag){
         console.log(this.user.items[0]);
         axios.delete(`${SERVER_URL}/member/delete/${this.user.items[0]}`).then(({ data }) => {
          let msg = "삭제 처리시 문제 발생"
          if (data === "success") {
            msg = "삭제 완료!";
            alert(msg);
            this.$store.dispatch("LOGOUT").then(() => this.$router.replace("/").catch(() => {}));
          }
        })
       }
    },
  },
};
</script>

<style>
</style>
