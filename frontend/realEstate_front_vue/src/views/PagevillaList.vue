<template>
  <div>
    <table v-if="showTb">
      <thead>
        <tr>
          <th>번호</th>
          <th>법정동</th>
          <th>주택이름</th>
          <th>건축년도</th>
          <th>주소</th>
          <th>전세가</th>
        </tr>
      </thead>
      <tbody id="searchResult3">
        <tr v-for="(villa, index) in paginatedData" :key="index + '_villas'">
          <td>{{villa.no}}</td>
          <td>{{villa.dong}}</td>
          <td>{{villa.aptName}}</td>
          <td>{{villa.buildYear}}</td>
          <td>{{villa.address}}</td>
          <td
            @mouseover="bgColorChange(true)"
            @mouseout="bgColorChange(false)"
            :class="{'mouse-over-color':isColor}"
          >click</td>
        </tr>
      </tbody>
    </table>
    <div class="btn-cover" v-if="showTb">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">이전</button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">다음</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "pagevilla-list",
  data() {
    return {
      selectedApt: "",
      pageNum: 0,
      tb1: this.showTb,
      tb3: false,
      isColor: false
    };
  },
  props: {
    listArray: {
      type: Array,
      required: true
    },
    pageSize: {
      type: Number,
      required: false,
      default: 10
    },
    showTb: {
      type: Boolean
    }
  },
  methods: {
    bgColorChange(flag) {
      this.isColor = flag;
    },
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    }
  },
  computed: {
    pageCount() {
      let listLeng = this.listArray.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;

      /*
      아니면 page = Math.floor((listLeng - 1) / listSize) + 1;
      이런식으로 if 문 없이 고칠 수도 있다!
      */
      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.listArray.slice(start, end);
    }
  }
};
</script>

<style>
table {
  width: 100%;
  border-collapse: collapse;
}
table th {
  font-size: 1.2rem;
}
table tr {
  height: 2rem;
  text-align: center;
  border-bottom: 1px solid #505050;
}
table tr:first-of-type {
  border-top: 2px solid #404040;
}
table tr td {
  padding: 1rem 0;
  font-size: 1.1rem;
}
.btn-cover {
  margin-top: 1.5rem;
  text-align: center;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}
.mouse-over-color {
  color: blue;
  font-size: 13pt;
}
</style>