<template>
   <div class="container">
      <br><br>
      <h2>QnA</h2>
      <br>
      <table class="table table-bordered table-condensed">
        <tr>
          <th>번호</th>
          <td>{{item.no}}</td>
        </tr>
        <tr>
          <th>글쓴이</th>
          <td>{{item.writer}}</td>
        </tr>
        <tr>
          <th>제목</th>
          <td>{{item.title}}</td>
        </tr>
        <tr>
          <th>날짜</th>
          <td>{{getFormatDate(item.regtime)}}</td>
        </tr>
        <tr>
          <th>내용</th>
          <td colspan="2">
            {{item.content}}
          </td>
        </tr>
      </table>
      <br />
      <div class="text-center">
        <router-link to="/board" class="btn  btn1">목록</router-link>&nbsp;
        <router-link :to="'/board/update/' + item.no" class="btn  btn1">수정</router-link>&nbsp;
        <router-link :to="'/board/delete/' + item.no" class="btn  btn1" >삭제</router-link>
      </div>
    </div>
</template>
<script>
import axios from 'axios';
import moment from 'moment';

export default {
   data(){
    return {
      item: {}
    }
  },
  created() {
    
    axios.get(`http://localhost:8000/happyhouse/api/board/${this.$route.params.no}`).then(({data }) => {  
      this.item = data;
    });

    
  },
  methods: {
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format("YYYY.MM.DD HH:mm:ss");
    },
   
  },
}
</script>
<style>
 .btn1{
    background-color:  #f7e600;
    color:black;
  }
</style>