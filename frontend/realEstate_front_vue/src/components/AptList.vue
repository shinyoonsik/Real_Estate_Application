<template>
  <div>
    <apt-list-item v-for="(apt, index) in aptlist" :key="index" :apt="apt" @select-apt="selectApt"/> <!--@select-apt 내가 만든 커스텀 이벤트에 대한 이벤트 리스너 -->
  </div>
</template>

<script>
import AptListItem from '@/components/AptListItem.vue';

export default {
  name: 'AptList',
  components: {
    AptListItem,
  },
  props: {
    aptlist: Array,  // Array는 aptlist의 타입을 의미함
  },
  methods: {
    selectApt(apt){ // js는 타입을 명시하지않기 때문에 보내준 이름을 그대로 사용하면 알아서 타입이 지정됨. 따라서 매개변수 사용시 이름 그대로 사용하면 된다
      this.$emit('select-apt', apt);
    }
  },
  
};
</script>

<style></style>
