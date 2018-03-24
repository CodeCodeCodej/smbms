<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>超市账单管理系统</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
</head>
<body>
	<!--头部-->
	<c:import url="head.jsp"></c:import>

	<!--主体内容-->
	<section class="publicMian">
		<c:import url="left.jsp"></c:import>
		<div class="right">
			<img class="wColck" src="${pageContext.request.contextPath}/img/clock.jpg" alt=""
				 />
			<div class="wFont">
				<h2>${sessionScope.user.userName}</h2>
				<p>欢迎来到超市账单管理系统!</p>
			</div>
		</div>
	</section>
	<!-- 尾部 -->
	<c:import url="footer.jsp"></c:import>
</body>
</html>