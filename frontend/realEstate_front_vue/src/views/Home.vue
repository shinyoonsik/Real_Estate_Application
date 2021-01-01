<template>
  <div class="home">
    <!-- <img alt="Vue logo" src="@/assets/logo.png" />-->

    <div class="bgImg bSize">
      <br /><br /><br/><br/><br/>
     /
      <Main></Main>
    </div>
  </div>
</template>

<script>
import Main from "@/components/Main.vue";  // 여기가 main화면

export default {
  name: 'Home',
  components: {
    Main
  },
};
</script>
<style scope>
.bSize {
  width: 100%;
  height: 950px;
}
.bgImg {
  background-image: url('../assets/img14.jpg');
  background-repeat: no-repeat;
  background-size: cover;
}
</style>
