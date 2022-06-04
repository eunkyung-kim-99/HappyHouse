<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- EmailJS-->
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/@emailjs/browser@3/dist/email.min.js"></script>

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


<link href="index.css" rel="stylesheet" type="text/css">

<!-- icon -->
<script src="https://kit.fontawesome.com/437ed9bce7.js"
	crossorigin="anonymous"></script>

<c:if test="${not empty msg}">
	<script type="text/javascript">
		alert("${msg}");
	</script>
</c:if>

<title>게시판</title>
</head>
<body>

	<!-- HEADER 시작 -->
	<jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>
	<!-- HEADER 끝 -->

	<!-- MAIN 시작 -->
	<div id="main" class="container">
		<!-- 게시판 시작 -->
		<div class="map-contents row">

			<!-- 전체게시판 시작 -->
			<div id="map" class="col-md-8">
				<h2 style="text-align: center">게시판</h2>

				<c:if test="${empty userInfo }">
					<div>
						<a class="boarda" href="#">
							<p onclick="alert('로그인해주세요')">글 작성</p>
						</a>
					</div>
				</c:if>

				<c:if test="${not empty userInfo }">
					<div>
						<a class="boarda" href="registboardform.user">글 작성</a>
					</div>
				</c:if>

				<table class="table table-hover">
					<thead>
						<tr>
							<th>글 번호</th>
							<th>작성자</th>
							<th>제목</th>
							<th style="width: 400px">내용</th>
							<th>상세보기</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty list }">

							<c:forEach items="${list }" var="item">
								<tr>
									<th>${item.no }</th>
									<th>${item.id }</th>
									<th>${item.subject }</th>
									<th style="width: 400px">${item.contents }</th>
									<th><a class="boarda"
										href="boardinfo.user?no=${item.no }&id=${item.id }&subject=${item.subject }&contents=${item.contents }">글
											보기</a></th>
								</tr>
							</c:forEach>

						</c:if>
					</tbody>
				</table>
			</div>


			<!-- 전체게시판 끝 -->

			<!-- 세부게시판 시작-->
			<div id="boardinfolist" class="col-md-4 row">

				<c:if test="${not empty infolist }">
					<c:forEach items="${infolist }" var="item">

						<div class="col-md-3">
							<div class="boardex">글 번호</div>
							<hr>
							<div class="boardex">작성자</div>
							<hr>
							<div class="boardex">제목</div>
							<hr>
							<div class="boardex">내용</div>

						</div>
						<div class="col-md-9">
							<div id="boardno">
								<span>${item.no }</span> 
								<div> <c:if
										test="${empty userInfo || userInfo.id != item.id }">
										<a href="#" class="updatebtn">
											<p onclick="alert('글쓴이만 수정 가능합니다')" style="display: inline;">수정</p>
										</a>
									</c:if> <c:if test="${userInfo.id == item.id }">
										<a class="updatebtn"
											href="updateboardform.user?no=${item.no }&id=${item.id}&subject=${item.subject}&contents=${item.contents}">수정</a>
									</c:if> <c:if test="${empty userInfo || userInfo.id != item.id }">
										<a href="#" class="updatebtn">
											<p onclick="alert('글쓴이만 삭제 가능합니다')" style="display: inline;">삭제</p>
										</a>
									</c:if> <c:if test="${userInfo.id == item.id }">
										<a class="updatebtn"
											href="deleteboard.user?no=${item.no }&id=${item.id}&subject=${item.subject}&contents=${item.contents}">삭제</a>
									</c:if>
								</div>
							</div>
							<hr>
							<div id="boardid">${item.id }</div>
							<hr>
							<div id="boardsubject">${item.subject }</div>
							<hr>
							<div id="boardcontents">${item.contents }</div>
						</div>



					</c:forEach>
				</c:if>

			</div>
			<!-- 세부게시판 끝-->
		</div>
		<!-- 게시판 끝 -->
	</div>
	<!-- MAIN 끝 -->

	<jsp:include page="/WEB-INF/template/modal.jsp"></jsp:include>

	<!-- FOOTER 시작 -->
	<footer class="p-3 bg-dark text-white container">
		<img src="img/SSAFY.jpg" />
		<p>Find Us</p>
		<hr />
		<p>(SSAFY)서울시 강남구 테헤란로 멀티스퀘어</p>
		<p>1544-9001</p>
	</footer>
	<!-- FOOTER 끝 -->
</body>
</body>
</html>
