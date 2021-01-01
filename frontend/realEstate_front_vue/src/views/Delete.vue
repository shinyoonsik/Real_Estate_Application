<template>
  <div>
      삭제중...
  </div>
</template>
<script>
import axios from 'axios';
export default {
   created() {
    axios.delete(`http://localhost:8000/happyhouse/api/board/${this.$route.params.no}`).then(({ data }) => {
      let msg = "삭제 처리시 문제 발생";
      if (data === "success") {
        msg = "삭제 완료!";
        alert(msg);
        this.$router.push('/board');
      }
    })
  },
}
</script>
<style>

</style>
