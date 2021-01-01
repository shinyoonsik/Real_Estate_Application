<template>
  <div v-if="user" class="container">
  <div id="header">
    <br>
		<h1 class="alignCenter">
            개인정보 수정
		</h1>
    <br>
	</div>
	<div id="content">
        <fieldset class="view_form">
            <div class="form-group">
                <span class="float-left">아이디</span>
				<input type="text" class="form-control form-control-lg" name="usr" v-model="user.items[0]" readonly >
            </div>
            <div class="form-group">
			    <span class="float-left">비밀번호</span>
                <input type="password" class="form-control form-control-lg" name="pwd" v-model="user.items[1]" >
            </div>
			<div class="form-group">
                <span class="float-left">이름</span>
                <input type="text" class="form-control form-control-lg" name="name" v-model="user.items[2]" >
            </div>
            <div class="form-group">
                <span class="float-left">이메일</span>
                <input type="email" class="form-control form-control-lg" name="email" v-model="user.items[3]" >
            </div>
            <div class="form-group">
                <span class="float-left">생년월일</span>
                <input type="date" class="form-control form-control-lg" name="birth"  v-model="user.items[4]" >
            </div>
            <br>
            <div class="form-group">
                <span class="float-left">휴대전화</span>
                <input type="tel" class="form-control form-control-lg" name="phone" v-model="user.items[5]" >
            </div>
            <div class="form-group">
                <span class="float-left">관심지역</span>
                <input type="text" class="form-control form-control-lg" name="interestLocation" v-model="user.items[6]" >
            </div>
            <br>

            <button class="btn btn-link fs" @click="changeInfo">정보 수정</button>   
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
        this.$router.push("/");
    }
  },
  methods: {
      changeInfo(){
            axios.put(`${SERVER_URL}/member/update`, {
                id: this.user.items[0],
                pw: this.user.items[1],
                name: this.user.items[2],
                email: this.user.items[3],
                birth: this.user.items[4],
                phone: this.user.items[5],
                interestLocation: this.user.items[6],
            }).then(({ data }) => {
                let msg = "수정 처리시 문제 발생"
                console.log("업데이트 완료")
                if (data == "success") {
                    msg = "수정 완료!";
                    alert(msg);
                    this.$store.dispatch("LOGOUT").then(() => this.$router.replace("/").catch(() => {}));
                }
              })
      }
  },
};
</script>


<style>
.fs{
    color:#007bff;
    font-size: 17px;
}
</style>