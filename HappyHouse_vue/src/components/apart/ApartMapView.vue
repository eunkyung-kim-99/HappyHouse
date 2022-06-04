<template>
  <div><div id="map"></div></div>
</template>
<script>
import { mapState } from "vuex";
export default {
  name: "MapView",
  data() {
    return {
      map: null,
      markers: [
        {
          title: "ssafy",
          position: {
            lat: 37.5012743,
            lng: 127.039585,
          },
        },
      ],
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
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" + SERVICEKEY;
      document.head.appendChild(script);
    }
    var imageSrc =
      "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

    for (var i = 0; i < this.aparts.length; i++) {
      // 마커 이미지의 이미지 크기 입니다
      var imageSize = new window.kakao.maps.Size(24, 35);

      // 마커 이미지를 생성합니다
      var markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize);

      // 마커를 생성합니다
      var marker = new window.kakao.maps.Marker({
        map: this.map, // 마커를 표시할 지도
        position: this.aparts[i].latlng, // 마커를 표시할 위치
        title: this.aparts[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImage, // 마커 이미지
      });
    }
    marker.setMap(this.map);
  },
  methods: {
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
    ...mapState(["aparts"]),
  },
  created() {},
};
</script>
<style scoped>
#map {
  width: auto;
  height: 600px;
}
</style>
