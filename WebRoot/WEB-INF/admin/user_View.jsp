<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
</head>
<body>
<!--头部-->	<c:import url="head.jsp"></c:import>
<!--主体内容-->
<section class="publicMian ">
		<c:import url="left.jsp"></c:import>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户信息查看页面</span>
        </div>
        <div class="providerView">
            <p><strong>用户编号：</strong><span>${user.userCode}</span></p>
            <p><strong>用户名称：</strong><span>${user.userName }</span></p>
            <p><strong>用户性别：</strong><span>${user.gender }</span></p>
            <p><strong>出生日期：</strong><span>${user.birthday }</span></p>
            <p><strong>用户电话：</strong><span>${user.phone }</span></p>
            <p><strong>用户地址：</strong><span>${user.address }</span></p>
            <p><strong>用户类别：</strong><span>${user.userRole }</span></p>
            <a   onclick="history.back()">返回</a>
        </div>
    </div>
</section>
	<c:import url="footer.jsp"></c:import>
<!-- 尾部 -->
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/js/User.js"></script>
</body>
</html>