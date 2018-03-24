<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML >
<html>
<head>
<title>主题</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
<!--    <script type="text/javascript">
	$(document).ready(function() {
	$(".click").click(function(){
				var password = "${pageContext.request.contextPath}"+jQuery(this).attr("tz");
				$(".right").load(password);
			});
			})
</script>  -->
</head>

<body>
	<div class="left">
		<h2 class="leftH2">
			<span class="span1"></span>功能列表 <span></span>
		</h2>
		<nav>
		 	<ul class="list">
		 	<c:choose>
		 	<c:when test="${sessionScope.user.userRole==3}">
		 	<li><a href="${pageContext.request.contextPath}/smbms/ProviderServlet/Provider" >供应商管理</a></li>
				<li ><a href="${pageContext.request.contextPath}/smbms/UserServlet/User" >用户管理</a></li>
				<li><a href="${pageContext.request.contextPath}/smbms/PasswordServlet/password" >密码修改</a></li>
				<li><a href="${pageContext.request.contextPath}/LoginServlet/zhuxiao" tz="/login.html">退出系统</a></li>
		 	</c:when>
		 	<c:otherwise>
		 	<li><a href="${pageContext.request.contextPath}/smbms/BillServlet/Bill" >账单管理</a></li>
				<li><a href="${pageContext.request.contextPath}/smbms/ProviderServlet/Provider" >供应商管理</a></li>
				<li ><a href="${pageContext.request.contextPath}/smbms/UserServlet/User" >用户管理</a></li>
				<li><a href="${pageContext.request.contextPath}/smbms/PasswordServlet/password" >密码修改</a></li>
				<li><a href="${pageContext.request.contextPath}/LoginServlet/zhuxiao" tz="/login.html">退出系统</a></li>
		 	</c:otherwise>
		 	</c:choose>
			</ul>
	<!-- 	 <ul class="list">
				<li><a href="javascript:void(0)" tz="/smbms/BillServlet/Bill" class="click">账单管理</a></li>
				<li><a href="javascript:void(0)"tz="/smbms/ProviderServlet/Provider" class="click">供应商管理</a></li>
				<li ><a href="javascript:void(0)"tz="/smbms/UserServlet/User"  class="click">用户管理</a></li>
				<li><a href="javascript:void(0)"tz="/smbms/PasswordServlet/password" class="click" >密码修改</a></li>
				<li><a href="javascript:void(0)"tz="/login.html" tz="/login.html" class="click">退出系统</a></li>
			</ul>  -->
		</nav>
	</div>
</body>
</html>
