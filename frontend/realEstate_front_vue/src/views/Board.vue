<template>
     <div class="container"><!-- 템플릿에 ROOT엘리먼트가 필요 -->
     <br><br>
      <h2>QnA</h2>
      <div v-if="items.length">
      <br>
        <table class="table table-bordered table-condensed">
          <colgroup>
            <col width="10%">
            <col width="50%">
            <col width="15%">
            <col width="25%">
          </colgroup>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>날짜</th>
          </tr>
          <tr v-for="(board, index) in items" :key="index + '_items'">
            <td >{{board.no}}</td>
            <td v-if="checkLogin"><span class="fontColor" @click="checkID(board.writer, board.no)">{{board.title}}</span></td>
            <td v-else>{{board.title}}</td>
            <td>{{board.writer}}</td>
            <td>{{getFormatDate(board.regtime)}}</td>
          </tr>
        </table>
      </div>
      <div v-else class="text-center">
        게시글이 없습니다.
      </div>
      <div class="text-right">
        <button class="btn  btn1" @click="createPage">등록</button>
      </div>
    </div>
</template>

<script>
import moment from 'moment';
import axios from 'axios';
export default {
  data(){
    return {
      items: [],
      checkLogin: {},  // 추가
    }
  },
  created() {
    this.checkLogin = JSON.parse(sessionStorage.getItem('id'));  // 추가
    console.log(this.checkLogin);
    axios.get(`http://localhost:8000/happyhouse/api/board`).then(({data }) => {  
      this.items = data;
    });
  },
  methods: {
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format("YYYY.MM.DD");
    },
    createPage() {
      let checkSession = JSON.parse(sessionStorage.getItem('id'));
      console.log(checkSession);
      if(checkSession == null){
        alert("로그인후 이용해 주세요.")
      }
      else{
        this.$router.push("/board/create")
      }
    },
    checkID(myId, myNo){  // 추가
      console.log(myId, myNo);
      if(this.checkLogin.items[0] == myId){
        this.$router.push(`/board/detail/${myNo}`);
      }
      else{
        alert("본인이 등록한 게시물만 수정할 수 있습니다.")
      }
    }
  },
}
</script>

<style>
  .fw{
      font-weight: 800;
  }
  .btn1{
    background-color:  #ffc107;
    color:black;
  }
  .fontColor{
      color: #cccc00;
  }
</style>