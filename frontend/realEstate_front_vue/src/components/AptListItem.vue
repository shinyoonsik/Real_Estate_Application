<template>
  <div
    style="margin: 10px;"
    @click="selectApt"
    @mouseover="bgColorChange(true)"
    @mouseout="bgColorChange(false)"
    :class="{'mouse-over-bcolor':isColor}"
  >
    <!-- 'mouse-over-bcolor' ; 홑따옴표를 해야 에러가 나지 않는다 -->
    <img src="@/assets/apt2.png" class="img-list" alt />
    <!-- <img :src="'@/assets/apt'+apt.층%10+'.png'" class="img-list" alt /> -->
    {{apt.아파트}}
  </div>
</template>

<script>
export default {
  name: "AptListItem",
  data() {
    return {
      isColor: false,
      num: "apt" + this.apt.층 + ".png"
    };
  },
  props: {
    apt: Object
  },
  methods: {
    selectApt() {
      this.$emit("select-apt", this.apt);
    },
    bgColorChange(flag) {
      this.isColor = flag;
    }
  }
};
</script>

<style>
.img-list {
  width: 50px;
}

.mouse-over-bcolor {
  background-color: white;
  font-size: 13pt;
}
</style>
