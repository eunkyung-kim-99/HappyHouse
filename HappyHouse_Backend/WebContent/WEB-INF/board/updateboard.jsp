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

<title>게시판 수정</title>
</head>
<body>

	<!-- HEADER 시작 -->
	<jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>
	<!-- HEADER 끝 -->

	<!-- MAIN 시작 -->
	<div id="main" class="container">
		<!-- 게시판 시작 -->
		<div class="container text-center mt-3" style="height: 600px">

			<div class="col-lg-4 mx-auto">
				<form action="updateboard.user" class="text-left mb-3" method="post" id="rform">
					<h2 class="p-3 mb-3 text-center">
						게시글 수정
					</h2>
					<div class="form-group">
						<label for="no">글 번호</label> 
						<input type="text" class="form-control" name="no" id="no" value="${no }" readonly/>
					</div>
					<div class="form-group">
						<label for="id">아이디</label> 
						<input type="text" class="form-control" name="id" id="id" value="${id }" readonly/>
					</div>
					<div class="form-group">
						<label for="subject">제목</label> 
						<input type="text" class="form-control" name="subject" id="subject" value="${subject}"/>
					</div>
					<div class="form-group">
						<label for="contents">내용</label>
						<textarea rows="10" cols="50" name="contents" id="contents">${contents }</textarea>
					</div>
					
					<div class="form-group">
						<input type="submit" class="btn btn-outline-primary" value="글 작성">
					</div>

				</form>
			</div>
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
