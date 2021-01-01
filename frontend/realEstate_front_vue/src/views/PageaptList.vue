<template>
  <div>
    <table v-if="showTb">
      <thead>
        <tr>
          <th>번호</th>
          <th>법정동</th>
          <th>아파트이름</th>
          <th>지번</th>
          <th>지역코드</th>
          <th>실거래가</th>
        </tr>
      </thead>
      <tbody id="searchResult1">
        <tr v-for="(aptInfo, index) in paginatedData" :key="index + '_aptInfos'">
          <td>{{aptInfo.no}}</td>
          <td>{{aptInfo.dong}}</td>
          <td>{{aptInfo.aptName}}</td>
          <td>{{aptInfo.jibun}}</td>
          <td>{{aptInfo.code}}</td>
          <td
            @click="sendDongCode(aptInfo.code)"
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

    <b-container class="bv-example-row" v-if="showD">
      <b-row>
        <b-col>
          <h2>아파트 매매 정보</h2>
        </b-col>
      </b-row>
      <b-row>
        <b-col cols="5" align="left">
          <apt-list :aptlist="aptsL" @select-apt="selectApt" />
        </b-col>
        <b-col cols="7">
          <apt-detail :apt="selectedApt" />
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";
import AptDetail from "@/components/AptDetail.vue";
import AptList from "@/components/AptList.vue";

const API_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
const API_URL =
  "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";

export default {
  name: "pageapt-list",
  components: {
    AptDetail,
    AptList
  },
  data() {
    return {
      selectedApt: "",
      pageNum: 0,
      dongCode: "",
      aptsL: [],
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
    },
    showD: {
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
    },
    selectApt(apt) {
      this.selectedApt = apt;
    },
    sendDongCode(dongCode) {
      this.$emit("pass");
      console.log(this.tb1);
      this.tb1 = false;
      this.tb3 = false;
      this.detail = true;
      this.showMap = false;
      console.log(dongCode);
      this.dongCode = dongCode;

      const params = {
        LAWD_CD: this.dongCode,
        DEAL_YMD: "202009", // 이미 API_KEYs는 공공데이터 기관에서 인코딩된 상태로 주어졌다.
        serviceKey: decodeURIComponent(API_KEY) // axios가 인코딩해서 보내기 때문에 이미 인코딩된 key를 디코딩시켜야 한다. 안 그러면 두 번 인코딩되므로
      };
      axios
        .get(API_URL, {
          params // params: params와 같이 key와 value가 같으면 잛게 하나만 적어도 된다
        })
        .then(response => {
          console.log(response);
          this.aptsL = response.data.response.body.items.item;
        })
        .catch(err => {
          console.log(err);
        });
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