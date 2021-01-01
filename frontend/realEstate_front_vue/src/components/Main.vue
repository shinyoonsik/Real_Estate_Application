<template>
  <div class="hello">
    <p style="padding: 50px;">
      <marquee scrolldelay="20" scrollamount="20"><img src="@/assets/logo.png"><br><span> 누구나 쉽고 빠르게 찾아볼 수 있는 <span class="fc">HappyHouse</span>에 오신걸 환영합니다!</span></marquee>
    </p>
  </div>
</template>

<script>
export default {
  name: "Main",
  props: {
    msg: String
  }
};
</script>

<style scoped>
p {
  font-size: 30pt;
  color: white;
}
.fc{
  color: #ffc107;
}
</style>
