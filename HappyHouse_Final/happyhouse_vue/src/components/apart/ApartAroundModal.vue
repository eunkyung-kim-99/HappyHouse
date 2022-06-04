<template>
  <div>
    <b-modal refs="Mymodal" id="bv-modal-example" hide-footer>
      <template #modal-title> Using <code>$bvModal</code> Methods </template>
      <div
        class="d-block text-center"
        v-for="(item, index) in str"
        :key="index"
      >
        <h3>{{ item }}</h3>
      </div>
      <b-button class="mt-3" block @click="$bvModal.hide('bv-modal-example')"
        >Close Me</b-button
      >
    </b-modal>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      map: null,
      SW8: [],
      str: [],
      modalonoff: false,
    };
  },
  props: {
    apart: Object,
  },
  watch: {
    modalonoff: function () {
      if (this.modalonoff) {
        this.$refs["Mymodal"].show();
      } else {
        this.$refs["Mymodal"].hide();
      }
    },
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
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" + SERVICEKEY;
      document.head.appendChild(script);
    }
    var ps = new window.kakao.maps.services.Places(this.map);

    ps.categorySearch("this.SW8", this.placesSearchCB);
  },
  methods: {
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
    placesSearchCB(data, status) {
      var dist;
      if (status === kakao.maps.services.Status.OK) {
        for (var i = 0; i < data.length; i++) {
          axios.post(
            {
              url: "https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&format=json&callback=result",
              async: false,
              data: {
                appKey: "l7xxbbb2aa7c829b43dc9d97383518c54051",
                startX: this.apart.lng,
                startY: this.apart.lat,
                endX: data[i].x,
                endY: data[i].y,
                reqCoordType: "WGS84GEO",
                resCoordType: "EPSG3857",
                startName: "출발지",
                endName: "도착지",
              },
              success: function (response) {
                var resultData = response.features;
                //결과 출력
                var tDistance = (
                  resultData[0].properties.totalDistance / 1000
                ).toFixed(1);

                dist = parseFloat(tDistance);
                this.binary_search(data[i], dist, 0, this.SW8.length);
              },
              error: function (request, status, error) {
                console.log(
                  "code:" +
                    request.status +
                    "\n" +
                    "message:" +
                    request.responseText +
                    "\n" +
                    "error:" +
                    error
                );
              },
            },
            500
          );
        }
      }
      console.log(`${this.SW8[0].data.place_name} ${this.SW8[0].dist}`);

      for (let i = 0; i < 5 && i < this.SW8.length; i++) {
        this.str[i] =
          this.SW8[i].data.place_name +
          `은` +
          this.SW8[i].dist +
          `km만큼 떨어져 있습니다.`;
      }

      console.log("result");
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
};
</script>

<style></style>
