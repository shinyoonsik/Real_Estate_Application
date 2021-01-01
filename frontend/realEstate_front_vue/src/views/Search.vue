<template>
  <div>
    <table class="table table-bordered">
      <tbody>
        <tr>
          <td class="mp">
            <select v-model="sel_sido" class="custom-select mb-1" @change="getGugun()">
              <option disabled value=" ">시도별 검색</option>
              <option
                :value="sido.sidoCode"
                v-for="(sido, index) in sidos"
                :key="index + '_sidos'"
              >{{ sido.sidoName }}</option>
            </select>
          </td>
          <td>
            <span class="font1">
              <select v-model="sel_gugun" class="custom-select mb-1" @change="getDong()">
                <option disabled value=" ">구군별 검색</option>
                <option
                  :value="gugun.gugunCode"
                  v-for="(gugun, index) in guguns"
                  :key="index + '_guguns'"
                >{{ gugun.gugunName }}</option>
              </select>
            </span>
          </td>
          <td>
            <span class="font1">
              <select v-model="sel_dong" class="custom-select mb-1" @change="getApt()">
                <option disabled value=" ">동별 검색</option>
                <option :value="dong.dong" v-for="(dong, index) in dongs" :key="index + '_dongs'">
                  {{
                  dong.dong
                  }}
                </option>
              </select>
            </span>
          </td>
          <td>
            <span class="font1">
              <select v-model="sel_apt" class="custom-select mb-1" @change="getSpecApt()">
                <option disabled value=" ">아파트별 검색</option>
                <option :value="apt.aptName" v-for="(apt, index) in apts" :key="index + '_apts'">
                  {{
                  apt.aptName
                  }}
                </option>
              </select>
            </span>
          </td>
          <td>
            <span class="font1">
              <select v-model="sel_dealAmount" class="custom-select mb-1" @change="getDeal()">
                <option disabled value=" ">아파트별 매매가별 검색</option>
                <option value="8000">8000미만</option>
                <option value="9000">9000미만</option>
                <option value="10000">10000미만</option>
                <option value="12000">12000미만</option>
              </select>
            </span>
          </td>
          <td>
            <span class="font1">
              <select v-model="sel_rentMoney" class="custom-select mb-1" @change="getRent()">
                <option disabled value=" ">아파트별 월세기준 검색</option>
                <option value="40">40미만</option>
                <option value="50">50미만</option>
                <option value="60">60미만</option>
                <option value="70">70미만</option>
                <option value="80">80미만</option>
              </select>
            </span>
          </td>
          <td>
            <span class="font1">
              <select v-model="sel_dealAmount2" class="custom-select mb-1" @change="getDeal2()">
                <option disabled value=" ">주택 매매가별 검색</option>
                <option value="8000">8000미만</option>
                <option value="9000">9000미만</option>
                <option value="10000">10000미만</option>
                <option value="12000">12000미만</option>
              </select>
            </span>
          </td>
          <td>
            <span class="font1">
              <select v-model="sel_rentMoney2" class="custom-select mb-1" @change="getRent2()">
                <option disabled value=" ">주택 월세가별 검색</option>
                <option value="40">40미만</option>
                <option value="50">50미만</option>
                <option value="60">60미만</option>
                <option value="70">70미만</option>
                <option value="80">80미만</option>
              </select>
            </span>
          </td>
        </tr>
      </tbody>
    </table>
    <pageapt-list :list-array="aptInfos" :showTb="tb1" :showD="detail" v-on:pass="ChangeApt" />
    <pagevilla-list :list-array="villas" :showTb="tb3" />

    <google-map class="googleMap" v-if="showMap">
      <div slot-scope="{ google, map }">
        <google-map-marker
          v-for="(marker, index) in markers"
          :google="google"
          :map="map"
          :key="index"
          :position="marker.position"
          @click="panTo($event, map)"
        ></google-map-marker>
      </div>
    </google-map>
  </div>
</template>

<script>
import axios from "axios";
import PageaptList from "./PageaptList";
import PagevillaList from "./PagevillaList";

export default {
  name: "Search",
  components: {
    PageaptList,
    PagevillaList
  },
  data() {
    return {
      selectedApt: "",
      markers: [
        {
          position: {
            lat: 0,
            lng: 0
          }
        }
      ],
      sidos: [],
      guguns: [],
      dongs: [],
      apts: [],
      aptInfos: [],
      villas: [],
      sel_sido: " ",
      sel_gugun: " ",
      sel_dong: " ",
      sel_apt: " ",
      sel_dealAmount: " ",
      sel_dealAmount2: " ",
      sel_rentMoney: " ",
      sel_rentMoney2: " ",
      tb1: false,
      tb3: false,
      detail: false,
      showMap: true
    };
  },
  created() {
    axios.get(`http://localhost:8000/happyhouse/map`).then(({ data }) => {
      this.sidos = data;
    });
  },
  methods: {
    ChangeApt() {
      this.detail = true;
      this.tb1 = false;
      this.showMap = false;
    },
    clearMarker() {
      this.markers = [];
    },
    addMarker(Lat, Lng) {
      console.log("현재마커" + Lat + " " + Lng);
      this.markers.push({
        position: {
          lat: Lat,
          lng: Lng
        }
      });
      for (var marker of this.markers) {
        console.log(marker.position.lat + " " + marker.position.lng);
      }
    },
    panTo(e, map) {
      map.panTo(e.latLng);
    },
    getGugun() {
      axios
        .get(`http://localhost:8000/happyhouse/map/gugun/${this.sel_sido}`)
        .then(({ data }) => {
          this.guguns = data;
        });
    },
    getDong() {
      axios
        .get(`http://localhost:8000/happyhouse/map/dong/${this.sel_gugun}`)
        .then(({ data }) => {
          this.dongs = data;
        });
    },
    getApt() {
      axios
        .get(`http://localhost:8000/happyhouse/map/apt/${this.sel_dong}`)
        .then(({ data }) => {
          this.apts = data;
          this.aptInfos = data;
          //this.clearMarker();
          for (var apt of this.apts) {
            this.addMarker(parseFloat(apt.lat), parseFloat(apt.lng));
          }
          this.tb1 = true;
          this.tb3 = false;
          this.detail = false;
          this.showMap = true;
        });
    },
    getSpecApt() {
      //this.clearMarker();

      this.aptInfos = [];
      for (var apt of this.apts) {
        if (apt.aptName == this.sel_apt) {
          this.addMarker(parseFloat(apt.lat), parseFloat(apt.lng));
          this.aptInfos.push(apt);
        }
      }
      this.tb1 = true;
      this.tb3 = false;
      this.detail = false;
      this.showMap = true;
    },
    getDeal() {
      axios
        .get(`http://localhost:8000/happyhouse/map/deal/${this.sel_dealAmount}`)
        .then(({ data }) => {
          this.aptInfos = data;
          this.tb1 = true;
          this.tb3 = false;
          this.detail = false;
          this.showMap = true;
        });
    },
    getRent() {
      axios
        .get(
          `http://localhost:8000/happyhouse/map/rent/${this.sel_rentMoney *
            200}`
        )
        .then(({ data }) => {
          this.aptInfos = data;
          for (var apt of this.apts) {
            console.log(apt.aptName);
          }
          this.tb1 = true;
          this.tb3 = false;
          this.detail = false;
          this.showMap = true;
        });
    },
    getDeal2() {
      axios
        .get(
          `http://localhost:8000/happyhouse/map/deal2/${this.sel_dealAmount2}`
        )
        .then(({ data }) => {
          this.villas = data;
          this.tb1 = false;
          this.tb3 = true;
          this.detail = false;
          this.showMap = true;
        });
    },
    getRent2() {
      axios
        .get(
          `http://localhost:8000/happyhouse/map/rent2/${this.sel_rentMoney2 *
            200}`
        )
        .then(({ data }) => {
          this.villas = data;
          this.tb1 = false;
          this.tb3 = true;
          this.detail = false;
          this.showMap = true;
        });
    }
  }
};
</script>
<style scope>
.googleMap {
  height: 500px;
}
.mp {
  /* padding: 0px 0px 0px 0px; */
  padding: 0;
}
.font1 {
  font-size: 18px;
}
</style>
