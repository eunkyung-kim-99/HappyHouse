<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	
</script>
<%@include file="/WEB-INF/views/head.jsp"%>

<title>happyhouse</title>


</head>
<body>

	<c:if test="${not empty msg }">
		<script>
			alert("${msg}");
		</script>
	</c:if>
	<%-- 	<form name="pageForm" id="pageForm">
		<input type="hidden" name="key" id="key" value="${key}" /> <input
			type="hidden" name="word" id="word" value="${word}" />
	</form> --%>


	<!-- HEADER 시작 -->
	<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
	<!-- HEADER 끝 -->

	<!-- MAIN 시작 -->
	<div id="main" class="container">
		<!-- BANNER 시작 -->
		<jsp:include page="/WEB-INF/views/template/banner.jsp"></jsp:include>
		<!-- BANNER 끝 -->

		<div class="pt-3 row">
			<div class="col-8">
				<div id="location-search" class="mb-2">
					<div class="select-box">
						<form action="${root }/apt/search" method="get">
							<!-- 도/광력시 dropdown -->
							<div class="row">
								<select class="col border m-2" id="sido" name="sido">
									<option value="">도/광역시</option>
								</select>
								<!-- 시/구/군 dropdown -->
								<select class="col border m-2" id="gugun" name="gugun">
									<option value="">시/군/구</option>
								</select>

								<!-- 동 dropdown -->
								<select class="col border m-2" id="dong" name="dong">
									<option value="">동</option>
								</select>
								<!-- search-btn 시작 -->
								<button type="submit" class="col btn btn-secondary"
									id="search-btn">검색</button>
								<!-- search-btn 끝 -->
							</div>
						</form>
					</div>
				</div>
				<!-- MAP 시작 -->
				<div id="map"></div>
				<!-- MAP 끝 -->
			</div>

			<!-- 주택 list 시작-->
			<div id="location-list " class="col-4">
				<div class="row searchaddress">
					<c:choose>
						<c:when test="${not empty searchaddress}">
							<h5 class="text-center pt-2">검색 지역 : ${searchaddress }</h5>
						</c:when>
						<c:otherwise>
							<h5 class="text-center pt-2">검색 지역 : 없음</h5>
						</c:otherwise>
					</c:choose>
					<div id="result123"></div>
				</div>
				<div class="border p-2">
					<div class="row">
						<div id="apt-search">
							<form class="form-inline" id="word-form"
								action="${root }/apt/search" method="GET">
								<input type="hidden" name="pg" value="1"> <select
									class="col border m-2" style="height: 40px;" name="key"
									id="key">
									<option value="aptName">아파트이름
								</select> <input type="text" class="col ml-1 form-control" name="word"
									id="word">
								<button type="button" class="col btn btn-outline-primary"
									id="word-btn">검색</button>
							</form>
							<c:if test="${not empty list }">
								<select name="ordercategory" id="ordercategory">
									<option value="">선택</option>
									<option value="aptname">아파트 이름순</option>
									<option value="dealdate">거래날짜 순</option>
									<option value="area">면적 순</option>
								</select>
								<select class="select-order" name="ordermethod" id="ordermethod">
									<option value="">선택</option>
									<option value="asc">오름차순</option>
									<option value="desc">내림차순</option>
									<option></option>
								</select>
							</c:if>
						</div>
					</div>

					<div id="apt-list" style="overflow: scroll; height: 500px;">
						<c:choose>
							<c:when test="${not empty list }">
								<c:forEach items="${list }" varStatus="status" var="item">

									<div class="aptinfolist">
										<div class="aptNamelist">${item.aptName }
											<button type="button" id="searchsb" listnum="${status.index}"
												class="btn btn-primary" style="font-size: 9px"
												data-toggle="modal" data-target="#myModal">가까운 지하철
												검색</button>
										</div>
										<div>거래 날짜 : ${item.dealYear }년 ${item.dealMonth }월
											${item.dealDay }일</div>
										<div>
											거래 금액 : ${item.dealAmount } <span class="won">(만)</span>
										</div>

										<div>면적 : ${item.area }</div>
										<div style="display: none;" id="lat${status.index}" class = "latclass"
											value="${item.lat}">${item.lat}</div>
										<div style="display: none;" id="lng${status.index}"
											value="${item.lng}">${item.lng}</div>
									</div>
									<hr>

								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${empty searchaddress}">
										<h4 class="text-center mt-5">검색 키워드로 검색해주세요</h4>
									</c:when>
									<c:otherwise>
										<h4 class="text-center mt-5">검색 결과가 없습니다</h4>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</div>
					<div id="apt-page">
						<div>${navi.navigator}</div>
					</div>
				</div>
				<!-- 주택 list 끝-->
			</div>
			<!-- map-contetns 끝 -->

			<!-- info-contents 시작 -->
			<div class="row mt-2 infocontents">
				<div class="col m-2 p-3 border">
					<img src="/img/adimg.jpg" class="d-block w-100" alt="광고입니다" />
				</div>
				<div class="col m-2 p-3 border text-center">
					<a href="${root }/user/listboard">공지사항</a>
				</div>
				<div class="col m-2 p-3 border text-center">오늘의 뉴스</div>
			</div>
			<!-- info-contens 끝 -->
		</div>
	</div>
	<!-- MAIN 끝 -->

	<jsp:include page="/WEB-INF/views/template/modal.jsp"></jsp:include>

	<!-- FOOTER 시작 -->
	<footer class="p-3 bg-dark text-white container">
		<img src="/img/SSAFY.jpg" />
		<p>Find Us</p>
		<hr />
		<p>(SSAFY)서울시 강남구 테헤란로 멀티스퀘어</p>
		<p>1544-9001</p>
	</footer>
	<!-- FOOTER 끝 -->



	<!-- The Modal -->
	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">가까운 지하철 역 정보</h4>
					<button type="button" class="close modal-close"
						data-dismiss="modal">x</button>
				</div>

				<!-- Modal body -->
				<div id="sbinfo" class="modal-body"></div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger modal-close"
						data-dismiss="modal">닫기</button>
				</div>

			</div>
		</div>
	</div>
</body>
</body>
</html>
