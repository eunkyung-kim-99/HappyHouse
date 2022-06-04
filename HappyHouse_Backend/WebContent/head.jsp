<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- EmailJS-->
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/@emailjs/browser@3/dist/email.min.js"></script>

<link rel="shortcut icon" href="img/home.png" />

<!-- bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>

<!-- jquery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- index.js -->
 <script type="text/javascript" src="index.js"></script> 

<!-- map -->
<!-- <script
      async
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCFQh4CqixzCQ3GMECg9_9YxbIOijkW22w&callback=initMap"
    ></script> -->

<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=32d4bf5a63d1251f73331370dbb8f363&libraries=services"></script>
<script>

    var map, marker;
    var markers = [];
    // 카카오 맵 시작하기
    $(document).ready(function () {
    	
    	
    var container = document.getElementById('map'); // 지도를 담을 영역의 DOM 레퍼런스
    var options = { // 지도를 생성할 때 필요한 기본 옵션
      center: new kakao.maps.LatLng(37.5012743, 127.039585), // 지도의 중심좌표.
      level: 5 // 지도의 레벨(확대, 축소 정도)
    };
    map = new kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴

	<c:if test="${not empty list }">
	<c:forEach items="${list }" var="item">
  	  var moveLatLon = new kakao.maps.LatLng(${item.lat}, ${item.lng});
        var imageSrc = "img/marker_map_icon.png", // 마커이미지의 주소입니다
          imageSize = new kakao.maps.Size(50, 50), // 마커이미지의 크기입니다
          imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

        // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imageOption
        );
        // 마커를 생성합니다
       marker = new kakao.maps.Marker({
          map: map,
          position: moveLatLon,
          image: markerImage,
        }); 
        
      var infowindow = new kakao.maps.InfoWindow({
           content: `<div class="info-title"><span>아파트 이름: ${item.aptName}</span></div>` // 인포윈도우 내부에 들어갈 컨텐츠 입니다.
         });

         kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
         kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
     
        // 지도 중심좌표를 접속위치로 변경합니다
        markers.push(marker);
        map.setCenter(moveLatLon);
      
	</c:forEach>
	</c:if>
	
	$.ajax({
	    url: "${root}/address",
	    type: "GET",
	    data:{"act":"sidoxml"},
	    dataType: "xml",
	   
	    success: function (data) {
	    	 makesidoXmlList(data)
	    },
	    error: function (xhr, status, msg) {
	      console.log("상태값 : " + status + " Http에러메시지 : " + msg);
	    },
	  });
	
	function makesidoXmlList(data) {
		let poll=``;
		$(data).find("address").each(function() {
			
			poll +=	`
				 <option value="`+$(this).find("sidoname").text()+`">`+
				 $(this).find("sidoname").text()+`</option>
			`;
			
		})
		
		$("#sido").empty().append("<option>도/광역시</option>").append(poll);
	}
	
	
	var sido = "";
	 $(document).on("change", "#sido", function (){
		 sido = $(this).val();
		 console.log(sido);
			 $.ajax({
				    url: "${root}/address",
				    type: "GET",
				    data:{"act":"gugunxml", "sido" : sido},
				    dataType: "xml",
				   
				    success: function (data) {
				    	 makegugunXmlList(data)
				    },
				    error: function (xhr, status, msg) {
				      console.log("상태값 : " + status + " Http에러메시지 : " + msg);
				    },
		});
	 });
	 
	 function makegugunXmlList(data) {
			let poll=``;
			
			$(data).find("address").each(function() {
				
				poll +=	`
					 <option value="`+$(this).find("gugunName").text()+`">`+
					 $(this).find("gugunName").text()+`</option>
				`;
				
			})
			
			$("#gugun").empty().append("<option>시/군/구</option>").append(poll);
		}
	 
	 $(document).on("change", "#gugun", function (){
		 gugun = $(this).val();
			 $.ajax({
				    url: "${root}/address",
				    type: "GET",
				    data:{"act":"dongxml", "sido" : sido, "gugun" : gugun },
				    dataType: "xml",
				   
				    success: function (data) {
				    	 makedongXmlList(data)
				    },
				    error: function (xhr, status, msg) {
				      console.log("상태값 : " + status + " Http에러메시지 : " + msg);
				    },
		});
	 });
	 
	 function makedongXmlList(data) {
			let poll=``;
			
			$(data).find("address").each(function() {
				
				poll +=	`
					 <option value="`+$(this).find("dongName").text()+`">`+
					 $(this).find("dongName").text()+`</option>
				`;
				
			})
			
			$("#dong").empty().append("<option>동</option>").append(poll);
		}
	
    });
    
    
    function makeOverListener(map, marker, infowindow) {
        return function () {
          infowindow.open(map, marker);
          var infoTitle = document.querySelectorAll('.info-title');
          infoTitle.forEach(function (e) {
            var w = e.offsetWidth + 10;
            var ml = w / 2;

            e.parentElement.previousSibling.style.display = "center";
            e.parentElement.parentElement.style.border = "0px";
            e.parentElement.parentElement.style.background = "unset";
          });
        };
      }

      // 인포윈도우를 닫는 클로저를 만드는 함수입니다 
      function makeOutListener(infowindow) {
        return function () {
          infowindow.close();
        };
      }
      
      
    
</script>

<link href="index.css" rel="stylesheet" type="text/css">

<!-- icon -->
<script src="https://kit.fontawesome.com/437ed9bce7.js"
	crossorigin="anonymous"></script>