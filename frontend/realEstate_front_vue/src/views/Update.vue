<template>
  <div class="container">
      <br><br>
      <h2>개인 정보 수정</h2>
      <br>

       <table class="table table-bordered table-condensed">
        <tr>
          <th>작성자</th>
          <td> <input type="text" class="form-control" id="writer" ref="writer" placeholder="작성자를 입력하세요" v-model="writer"></td>
        </tr>
        <tr>
          <th>제목</th>
          <td><input type="text" class="form-control" id="title" ref="title" placeholder="제목을 입력하세요" v-model="title"></td>
        </tr>
        <tr>
          <th>내용</th>
          <td> <textarea type="text" class="form-control" id="contnet" ref="content" placeholder="내용을 입력하세요" v-model="content"></textarea></td>
        </tr>
      </table>

      <div class="text-right">
        <button class="btn btn1 " @click="checkHandler">수정</button>
      </div>
    </div>
</template>
<script>
import axios from 'axios'
export default {
  data() {
      return {
          no: '',
          regtime: '',
          writer: '',
          title: '',
          content: ''
      }
  },
  created() {
    axios.get(`http://localhost:8000/happyhouse/api/board/${this.$route.params.no}`).then(({ data }) => {  
        this.no = data.no;
        this.regtime = data.regtime;
        this.writer = data.writer;
        this.title = data.title;
        this.content = data.content;
      
    });
  },
  methods: {
    checkHandler() {
      let err = true;
      let msg = '';
      !this.writer && (msg = '작성자를 입력해주세요', err = false, this.$refs.writer.focus());
      err && !this.title && (msg = '제목 입력해주세요', err = false, this.$refs.title.focus());
      err && !this.content && (msg = '내용 입력해주세요', err = false, this.$refs.content.focus());
      if (!err) alert(msg);
      else this.updateHandler();
    },
    updateHandler() {
      //const params = (new URL(document.location)).searchParams;
      axios.put(`http://localhost:8000/happyhouse/api/board/${this.$route.params.no}`, {
        no : this.no,
        regtime : this.regtime,
        writer : this.writer,
        title : this.title,
        content : this.content,
       
      }).then(({ data }) => {
        let msg = "수정 처리시 문제 발생"
        if (data == "success") {
          msg = "수정 완료!";
        }
        alert(msg)
        this.$router.push("/board");
      })
    }
  },
}
</script>
<style>
</style>