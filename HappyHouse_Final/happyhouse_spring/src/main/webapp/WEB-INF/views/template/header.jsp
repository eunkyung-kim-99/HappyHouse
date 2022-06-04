<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header class="navbar navbar-light bg-light fixed-top container">
		<nav class="navbar navbar-expand-sm">
			<a class="navbar-brand" href="/"
				style="font-size: 30px; font-weight: bold; color: orange"> <i
				class="fa-solid fa-house-chimney-window"></i> HAPPY HOUSE
			</a>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="${root }/user/listboard">공지사항</a></li>
				<li class="nav-item"><a class="nav-link" href="#">오늘의 뉴스</a></li>
				<li class="nav-item"><a class="nav-link" href="#">주변 탐방</a></li>
			</ul>
		</nav>

		<button class="navbar-toggler" type="button"
			data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
			aria-controls="offcanvasNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="offcanvas offcanvas-end" tabindex="-1"
			id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
			<div class="offcanvas-header">
				<h5 class="offcanvas-title" id="offcanvasNavbarLabel">MENU</h5>
				<button type="button" class="btn-close text-reset"
					data-bs-dismiss="offcanvas" aria-label="Close"></button>
			</div>
			<div class="offcanvas-body">
				<ul class="navbar-nav justify-content-end flex-grow-1">

					<c:choose>
						<c:when test="${empty userInfo }">
							<li class="nav-item">
								<button type="button" class="btn menu-btn"
									data-bs-toggle="modal" data-bs-target="#exampleModalregist"
									data-bs-whatever="@mdo">회원가입</button>
							</li>
							<hr>
							<li class="nav-item">
								<button type="button" class="btn menu-btn"
									data-bs-toggle="modal" data-bs-target="#exampleModallogin"
									data-bs-whatever="@mdo">로그인</button>
							</li>
							<hr>

						</c:when>
						<c:otherwise>
							<h5 class="text-center mb-3">${userInfo.id }님반갑습니다.</h5>
							<li class="nav-item">
								<form action="/user/logout">
									<button type="submit" class="btn menu-btn">로그아웃</button>
								</form>
							</li>
							<hr>

							<li class="nav-item">
								<button type="button" class="btn menu-btn"
									data-bs-toggle="modal" data-bs-target="#exampleModalconfirm"
									data-bs-whatever="@mdo">회원정보확인</button>
							</li>
							<hr>

						</c:otherwise>
					</c:choose>
					<li class="nav-item">
						<button type="button" class="btn menu-btn">관심 지역 설정</button>
					</li>
					<hr>

					<li class="nav-item">
						<button type="button" class="btn menu-btn">관심 지역 둘러보기</button>
					</li>
				</ul>
			</div>
		</div>
	</header>
</body>
</html>