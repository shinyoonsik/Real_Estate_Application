<template>
  <b-container class="bv-example-row">
    <img alt="Vue logo" src="@/assets/logo2.png" class="move"/>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card
        class="mt-3"
        header="회원 가입"
        style="max-width: 40rem;"
        align="left"
        >
        <b-form>
        <b-form-group label="아이디:" label-for="uid">
            <b-form-input
            type="text"
            id="uid"
            v-model="userid"
            required
            placeholder="아이디 입력"
            ></b-form-input>
        </b-form-group>
        <b-form-group label="비밀번호:" label-for="pwd">
            <b-form-input
            type="password"
            id="pwd"
            v-model="userpwd"
            required
            placeholder="비밀번호 입력"
            ></b-form-input>
        </b-form-group>
        <b-form-group label="이름:" label-for="name">
            <b-form-input
            type="text"
            id="name"
            v-model="username"
            placeholder="이름"
            ></b-form-input>
        </b-form-group>
        <b-form-group label="이메일:" label-for="email">
            <b-form-input
            type="email"
            id="email"
            v-model="useremail"
            placeholder="이메일"
            ></b-form-input>
        </b-form-group>
        <b-form-group label="생년월일:" label-for="date">
            <b-form-input
            type="date"
            id="date"
            v-model="userdate"
            placeholder="생년월일"
            ></b-form-input>
        </b-form-group>
           <b-form-group label="휴대전화:" label-for="phone">
            <b-form-input
            type="tel"
            id="phone"
            v-model="userphone"
            placeholder="휴대전화 "
            ></b-form-input>
        </b-form-group>
        <b-form-group label="관심지역 입력:" label-for="interestLocation">
          <b-form-input
            type="text"
            id="interestLocation"
            v-model="interestLocation"
            placeholder="관심지역 입력"
          ></b-form-input>
        </b-form-group>
        <!-- <b-button type="button" variant="primary" class="m-1 btn-warning text-white " @click="login"
            >로그인</b-button
        > -->
        <b-button type="button" variant="success" class="m-1 btn-warning text-white " @click="joinMember">회원가입</b-button>
        </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import axios from 'axios';

export default {
  name: "Login",
  data: function() {
    return {
      userid: "",
      userpwd: "",
      username: "",
      useremail: "",
      userdate: "",
      userphone: "",
      interestLocation: "",
    };
  },
  computed: {
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : "";
    }
  },
  methods: {
    joinMember(){
       console.log(this.userid, this.username);
       axios.post(`http://localhost:8000/happyhouse/member/register`, {
        id: this.userid,
        pw: this.userpwd,
        name: this.username,
        email: this.useremail,
        birth: this.userdate,
        phone: this.userphone,
        interestLocation: this.interestLocation,
      }).then(({ data }) => {
        let msg = "등록 처리시 문제 발생"
        console.log(data.status);
        if (data.status == "SUCCESS") {
          msg = "등록 완료!";
          console.log(msg)
          this.moveList();
        }
      })
    },
    
    moveList(){
      this.$router.push("/");
    }
  }
}

</script>

<style scope>
#login-div {
  text-align: center;
}

.move{
   position: relative;
   right:43px; 
}
</style>
