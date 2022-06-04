<!-- @format -->

<template>
  <v-app id="map">
    <b-row>
      <b-col cols="8 searchbar">
        <div class="searchbar-item mt-4 ml-4">
          <apart-search-bar-vue></apart-search-bar-vue>
        </div>
      </b-col>
      <b-col cols="4" class="apartright">
        <div class="apartright-box mt-4 mb-4">
          <apart-right></apart-right></div></b-col
    ></b-row>
  </v-app>
</template>
<script>
import ApartSearchBarVue from "@/components/apart/ApartSearchBar.vue";
import ApartRight from "@/components/apart/ApartRight.vue";

import markermapicon from "@/assets/images/marker1.png";
import axios from "axios";
import { mapState, mapActions, mapMutations } from "vuex";
import qs from "qs";
export default {
  name: "MapView",
  components: {
    ApartSearchBarVue,
    ApartRight,
  },
  data() {
    return {
      map: null,
      markers: [],
      SW8: [],
      timeplay: false,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      const SERVICEKEY = process.env.VUE_APP_KAKAOMAP_KEY;
      console.log(SERVICEKEY);
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" +
        SERVICEKEY +
        "&libraries=services,clusterer,drawing";
      document.head.appendChild(script);
    }
    for (var j = 0; j < this.markers.length; j++) {
      this.markers[j].setMap(null);
    }
    this.markers = [];
    var imageSrc = markermapicon;
    for (var i = 0; i < this.aparts.length; i++) {
      // 마커 이미지의 이미지 크기 입니다
      var imageSize = new kakao.maps.Size(35, 35);

      // 마커 이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
      // 마커를 생성합니다

      var marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(this.aparts[i].lat, this.aparts[i].lng), // 마커를 표시할 위치
        title: this.aparts[i].aptName, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImage, // 마커 이미지
      });
      // 이동할 위도 경도 위치를 생성합니다
      var moveLatLon = new kakao.maps.LatLng(
        this.aparts[this.aparts.length - 1].lat,
        this.aparts[this.aparts.length - 1].lng
      );
      var infowindow = new kakao.maps.InfoWindow({
        content:
          '<h6 class="text-center">' + `${this.aparts[i].aptName}` + "</h6>",

        // 인포윈도우 내부에 들어갈 컨텐츠 입니다.
      });

      kakao.maps.event.addListener(
        marker,
        "mouseover",
        this.makeOverListener(this.map, marker, infowindow)
      );
      window.kakao.maps.event.addListener(
        marker,
        "mouseout",
        this.makeOutListener(infowindow)
      );

      // 지도 중심좌표를 접속위치로 변경합니다
      this.markers.push(marker);
      this.map.setCenter(moveLatLon);
      this.SET_MAP_POINT({
        //c추가
        lat: this.aparts[this.aparts.length - 1].lat,
        lng: this.aparts[this.aparts.length - 1].lng,
      });
    }

    for (i = 0; i < this.markers.length; i++) {
      this.markers[i].setMap(this.map);
    }
  },
  methods: {
    ...mapMutations([
      "SET_SW8",
      "CLEAR_SW8",
      "CLEAR_MAP_POINT", //c추가
      "SET_MAP_POINT", //c추가
      "SET_FINISHSEARCH",
    ]),
    ...mapActions(["get_foods"]), //c추가
    binary_search(data, dist, start, end) {
      if (this.SW8.length == 0) {
        this.SW8.push({ data: data, dist: dist });
        return;
      }
      if (start >= end) {
        if (start + 1 > this.SW8.length) {
          this.SW8.push({ data: data, dist: dist });
          return;
        }
        if (this.SW8[start].dist < dist) {
          this.SW8.splice(start + 1, 0, { data: data, dist: dist });
        } else {
          this.SW8.splice(start, 0, { data: data, dist: dist });
        }
        return;
      }
      let mid = Math.floor((start + end) / 2);
      if (this.SW8[mid].dist > dist) {
        this.binary_search(data, dist, start, mid);
      } else {
        this.binary_search(data, dist, mid + 1, end);
      }
      return;
    },
    sleep(ms) {
      const wakeUpTime = Date.now() + ms;
      while (Date.now() < wakeUpTime) {
        this.timeplay = !this.timeplay;
      }
    },

    async placesSearchCB(data, status) {
      this.CLEAR_SW8();
      this.SW8 = [];
      this.SET_FINISHSEARCH(false);
      console.log("여기 안옴?");
      if (status === kakao.maps.services.Status.OK) {
        for (var i = 0; i < data.length; i++) {
          const tmapBody = qs.stringify({
            appKey: "l7xxbbb2aa7c829b43dc9d97383518c54051",
            startX: this.target.lng,
            startY: this.target.lat,
            endX: data[i].x,
            endY: data[i].y,
            reqCoordType: "WGS84GEO",
            resCoordType: "EPSG3857",
            startName: "출발지",
            endName: "도착지",
          });
          const tdata = data[i];
          await axios
            .post(
              "https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&format=json&callback=result",
              tmapBody,
              {
                "Accept-Language": "ko",
                "Content-Type": "application/x-www-form-urlencoded",
              }
            )
            .then((response) => {
              var resultData = response.data.features;

              //결과 출력
              var tDistance = (
                resultData[0].properties.totalDistance / 1000
              ).toFixed(1);
              var tTime = (resultData[0].properties.totalTime / 60).toFixed(0);
              const inputdata = {
                stationName: tdata.place_name,
                stationAddress: tdata.address_name,
                distance: tDistance,
                walktime: tTime,
              };
              var dist = parseFloat(tDistance);
              this.binary_search(inputdata, dist, 0, this.SW8.length);
              for (var j = 0; j < this.SW8.length; j++) {
                console.log(this.SW8[j].data);
              }
            })
            .catch((error) => {
              console.log(error);
            });
          this.sleep(500);
        }

        this.SET_SW8(this.SW8);
      }
      this.SET_FINISHSEARCH(true);
    },
    makeOverListener(map, marker, infowindow) {
      return function () {
        infowindow.open(map, marker);
        var infoTitle = document.querySelectorAll(".info-title");
        infoTitle.forEach(function (e) {
          e.parentElement.previousSibling.style.display = "center";
          e.parentElement.parentElement.style.border = "0px";
          e.parentElement.parentElement.style.background = "unset";
        });
      };
    },

    // 인포윈도우를 닫는 클로저를 만드는 함수입니다
    makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    },
    initMap() {
      var container = document.getElementById("map"); // 지도를 담을 영역의 DOM 레퍼런스
      var options = {
        // 지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.5012743, 127.039585), // 지도의 중심좌표.
        level: 5, // 지도의 레벨(확대, 축소 정도)
      };
      this.map = new kakao.maps.Map(container, options);
    },
  },
  computed: {
    ...mapState(["aparts", "target", "selDongCode", "mappoint"]), //c추가
  },
  watch: {
    selDongCode: function () {
      this.map.setLevel(5);
    },
    mappoint: function () {
      //c추가
      var moveLatLon = new kakao.maps.LatLng(
        this.mappoint.lat,
        this.mappoint.lng
      );
      this.map.setCenter(moveLatLon);
      this.map.setLevel(4);
    },
    target: function () {
      var moveLatLon = new kakao.maps.LatLng(this.target.lat, this.target.lng);
      this.map.setCenter(moveLatLon);
      this.map.setLevel(6);
      var ps = new kakao.maps.services.Places(this.map);
      ps.categorySearch("SW8", this.placesSearchCB, {
        useMapBounds: true,
      });
      this.get_foods(); //c추가
    },
    aparts: function () {
      for (var j = 0; j < this.markers.length; j++) {
        this.markers[j].setMap(null);
      }
      this.markers = [];
      var imageSrc = markermapicon;
      for (var i = 0; i < this.aparts.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 24);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        // 마커를 생성합니다

        var marker = new kakao.maps.Marker({
          position: new kakao.maps.LatLng(
            this.aparts[i].lat,
            this.aparts[i].lng
          ), // 마커를 표시할 위치
          title: this.aparts[i].aptName, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });
        // 이동할 위도 경도 위치를 생성합니다
        var moveLatLon = new kakao.maps.LatLng(
          this.aparts[this.aparts.length - 1].lat,
          this.aparts[this.aparts.length - 1].lng
        );
        var infowindow = new kakao.maps.InfoWindow({
          content: "아파트 이름: " + this.aparts[i].aptName, // 인포윈도우 내부에 들어갈 컨텐츠 입니다.
        });

        kakao.maps.event.addListener(
          marker,
          "mouseover",
          this.makeOverListener(this.map, marker, infowindow)
        );
        window.kakao.maps.event.addListener(
          marker,
          "mouseout",
          this.makeOutListener(infowindow)
        );

        // 지도 중심좌표를 접속위치로 변경합니다
        this.markers.push(marker);
        this.map.setCenter(moveLatLon);
        this.SET_MAP_POINT({
          //c추가
          lat: this.aparts[this.aparts.length - 1].lat,
          lng: this.aparts[this.aparts.length - 1].lng,
        });
      }

      for (i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(this.map);
      }
    },
  },
};
</script>
<style scoped>
#map {
  width: auto;
  height: 100%;
  z-index: 1;
}
.searchbar {
  z-index: 2;
  height: 100%;
}
.apartright {
  z-index: 2;
}
.apartright-box {
  height: 100%;
  background: rgba(255, 255, 255, 0.9) 51.03%;
}
</style>
