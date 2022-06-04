<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- EmailJS-->
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/@emailjs/browser@3/dist/email.min.js"></script>

<link rel="shortcut icon" href="/img/home.png" />
<!-- jstl -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<!-- <script type="text/javascript" src="/js/index.js"></script> -->

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
    
    
    

    
	/* 여기부터 마커 찍는 코드 */
	<c:if test="${not empty list }">
	<c:forEach items="${list }" var="item">
  	  var moveLatLon = new kakao.maps.LatLng(${item.lat}, ${item.lng});
        var imageSrc = "/img/marker_map_icon.png", // 마커이미지의 주소입니다
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
	/* 마커 찍는 코드 끝*/
	var lat = "";
	var lng = "";
	
	
	/* 여기부터  주변 상권 검색*/
	
	 // 장소 검색 객체를 생성합니다
    var ps = new kakao.maps.services.Places(map); 
    // 카테고리로 은행을 검색합니다
    var dist;
    let SW8 = [];
    // 키워드 검색 완료 시 호출되는 콜백함수 입니다
    function sleep(ms) {
  		const wakeUpTime = Date.now() + ms;
  		while (Date.now() < wakeUpTime) {}
	}
    function placesSearchCB (data, status, pagination) {
    	SW8 = [];
        if (status === kakao.maps.services.Status.OK) {
            for (var i=0; i<data.length; i++) {
            	 $.ajax({
					method : "POST",
					url : "https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&format=json&callback=result",
					async : false,
					data : {
						"appKey" : "l7xxbbb2aa7c829b43dc9d97383518c54051",
						"startX" : lng,
						"startY" : lat,
						"endX" : data[i].x,
						"endY" : data[i].y,
						"reqCoordType" : "WGS84GEO",
						"resCoordType" : "EPSG3857",
						"startName" : "출발지",
						"endName" : "도착지"
					},
					success : function(response) {
						var resultData = response.features;
						//결과 출력
						var tDistance = ((resultData[0].properties.totalDistance) / 1000)
										.toFixed(1);
						var tTime = " 총 시간 : "
								+ ((resultData[0].properties.totalTime) / 60)
										.toFixed(0) + "분";

						dist = parseFloat(tDistance);
						binary_search(data[i], dist, 0, SW8.length);
					},
					error : function(request, status, error) {
						console.log("code:" + request.status + "\n"
								+ "message:" + request.responseText + "\n"
								+ "error:" + error);
					}
				});
            	sleep(500); 
            }       
        }
        console.log(`${SW8[0].data.place_name} ${SW8[0].dist}`); 
        let str = ``;
        $("#sbinfo").empty();
        for(let i=0;i<5 && i<SW8.length;i++){
        	
        	str+=`<div>`+
        		SW8[i].data.place_name+`은`+ SW8[i].dist+`km만큼 떨어져 있습니다.
        	</div>`;
        }
       $("#sbinfo").append(str);
        
        
        
        console.log("result"); 
        for (var i=0; i<SW8.length; i++) {
        	console.log(SW8[i]);
        } 
    }
    
        
    
	function binary_search(data, dist, start, end){
		if(SW8.length == 0){
        	SW8.push({"data":data, "dist":dist});
        	return;
		}
		if(start >= end){
			if(start+1 > SW8.length){
				SW8.push({"data":data, "dist":dist});
	        	return;
			}
			if(SW8[start].dist < dist){
				SW8.splice(start+1 ,0, {"data":data, "dist":dist});
			}else{
				SW8.splice(start ,0, {"data":data, "dist":dist});
			}
			return;
		}
		let mid = Math.floor((start + end)/2);
		if(SW8[mid].dist > dist){
			binary_search(data,dist,start,mid);
		}else{
			binary_search(data,dist,mid+1,end);
		}
		return;
	}

    
	/* 주변 상권 검색 끝*/

	
	
	
	/* 비동기 셀렉트 바 */
	$.ajax({
	    url: "${root}/address/sido",
	    type: "GET",
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
		 localStorage.removeItem("searchbyword");
		 sido = $(this).val();
		 console.log(sido);
			 $.ajax({
				    url: "${root}/address/gugun/"+sido,
				    type: "GET",
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
		 sido = $("#sido").val();
		gugun = $(this).val();
			 $.ajax({
				    url: "${root}/address/dong/"+sido+"/"+gugun,
				    type: "GET",
				    dataType: "xml",
				   
				    success: function (data) {
				    	 makedongXmlList(data)
				    },
				    error: function (xhr, status, msg) {
				      console.log("상태값 : " + status + " Http에러메시지 : " + msg);
				    },
		});
	 });
	 
	 
	 
	 $(document).on("change",".select-order",function(){
		 const address = JSON.parse(localStorage.getItem("address"));
		 const searchbyword = JSON.parse(localStorage.getItem("searchbyword"));
		 let ordercategory = $("#ordercategory").val();
		 let ordermethod = $("#ordermethod").val();
		 /* $.ajax({
			url : "${root }/apt/search" + "?sido="+ address.sido +"&gugun=" + address.gugun + "&dong="+address.dong + "&key=" + searchbyword.key + "&word=" + searchbyword.word +
					"&ordercategory=" + ordercategory + "&ordermethod=" +ordermethod; 
			type:"GET",
			datatype:"xml",
			success: function(date){
				
			},
			error: function (xhr, status, msg) {
			      console.log("상태값 : " + status + " Http에러메시지 : " + msg);
			    },
		 }); */
		
		 if(searchbyword != null){
			 location.href = "${root }/apt/search" + "?sido="+ address.sido +"&gugun=" + address.gugun + "&dong="+address.dong + "&key=" + searchbyword.key + "&word=" + searchbyword.word +
				"&ordercategory=" + ordercategory + "&ordermethod=" +ordermethod;
		 }
		 else{
			 location.href = "${root }/apt/search" + "?sido="+ address.sido +"&gugun=" + address.gugun + "&dong="+address.dong + 
				"&ordercategory=" + ordercategory + "&ordermethod=" +ordermethod;
		 }
		 
		
		
	 });
	 
	 
	 $(document).on("click","#word-btn",function(){
		// let pg = $(this).attr("data-pg");
		
		  const address = JSON.parse(localStorage.getItem("address"));
	 	  console.log(" 주소 정보 : " +  address);
		  let poll = `
			  <input type="hidden" name="sido" id="sido" value=` +address.sido + ` />
			  <input type="hidden" name="gugun" id="gugun" value=` + address.gugun+` />
			  <input type="hidden" name="dong" id="dong" value=` + address.dong+` />
		  `
		  let searchattr = $("#key").val();
		  let searchword = $("#word").val();
		  const search = {
				  key : searchattr,
				  word : searchword
		  };
		  localStorage.setItem("searchbyword",JSON.stringify(search));
	     	// $("#pg").val($(this).attr("data-pg"));
/* 		  $(document).getElementById("word-form").append(poll); */
		  	$("#word-form").append(poll);
	     	$("#word-form").submit(); 
	 })
	
	  $(document).on("click","#search-btn",function(){
		  let sido = $("#sido").val();
			 let gugun = $("#gugun").val();
			 let dong = $("#dong").val();
			 
			 const address = {
					 sido: sido,
					 gugun: gugun,
					 dong: dong,
			 };
			 localStorage.setItem("address", JSON.stringify(address));
		  
	  });
	 $(document).on("click","#searchsb",function(){
		let listnum = $(this).attr('listnum');
		console.log(listnum);
		
		lat = document.querySelector("#lat" + listnum).getAttribute('value');
		lng = document.querySelector("#lng" + listnum).getAttribute('value');
		console.log("lat : " + lat + "\t" + "lng : " + lng);
		$("#myModal").modal("show");
		ps.categorySearch('SW8', placesSearchCB, {useMapBounds:true}); 
	 });
	 
	 $(document).on("click",".modal-close",function(){
			
			$("#myModal").modal("hide");
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
    
	/* 비동기 셀렉트 바 끝 */

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

<link href="/css/index.css" rel="stylesheet" type="text/css">

<!-- icon -->
<script src="https://kit.fontawesome.com/437ed9bce7.js"
	crossorigin="anonymous"></script>