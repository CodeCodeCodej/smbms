<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="cn.entity.*"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>超市账单管理系统</title>
</head>
<body>
	<!--头部-->
	<c:import url="head.jsp"></c:import>
	<!--主体内容-->
	<section class="publicMian ">
		<c:import url="left.jsp"></c:import>
		<div class="right">
			<div class="location">
				<strong>你现在所在的位置是:</strong> <span>用户管理页面 >> 用户修改页面</span>
			</div>
			<div class="providerAdd">
				<form action="#" id="Updatefrom">
					<!--div的class 为error是验证错误，ok是验证成功-->
					<input type="hidden" id="userCode" name="userCode"
						value="${user.userCode }">
					<div>
						<label for="userName">用户名称：</label> <input type="text"
							name="userName" id="userName" value="${user.userName }" /> <span
							id="span1">*</span>
					</div>

					<div>
						<label>用户性别：</label> <select name="sex">
							<option value="woman" ${user.gender==2? "":"selected" }>女</option>
							<option value="man" ${user.gender==1?"":"selected" }>男</option>
						</select>
					</div>
					<div>
						<label for="data">出生日期：</label> <input type="date" name="data"
							id="data" value="${user.birthday }" /> <span id="span2">*</span>
					</div>
					<div>
						<label for="userphone">用户电话：</label> <input type="text"
							name="userphone" id="userphone" value="${user.phone }" /> <span
							id="span3">*</span>
					</div>
					<div>
						<label for="userAddress">用户地址：</label> <input type="text"
							name="userAddress" id="userAddress" value="${user.address }" />
					</div>
					<div>
						<label>用户类别：</label>
				<c:choose>
				<c:when test="${sessionScope.user.userRole==2}">
				<input type="radio" name="userlei" value="1" ${user.userRole==1? "checked":"" } disabled/>管理员 
						<input type="radio" name="userlei" value="2" ${user.userRole==2? "checked":""} />经理
						 <input type="radio" name="userlei" value="3" ${user.userRole==3? "checked":"" } disabled/>普通用户
				</c:when>
				<c:when test="${sessionScope.user.userRole==3}">
				<input type="radio" name="userlei" value="1" ${user.userRole==1? "checked":"" } disabled/>管理员 
						<input type="radio" name="userlei" value="2" ${user.userRole==2? "checked":""} disabled/>经理
						 <input type="radio" name="userlei" value="3" ${user.userRole==3? "checked":""} />普通用户
				</c:when>
                <c:otherwise>
                <input type="radio" name="userlei" value="1" ${user.userRole==1? "checked":""}/>管理员 
						<input type="radio" name="userlei" value="2" ${user.userRole==2? "checked":""} />经理
						 <input type="radio" name="userlei" value="3" ${user.userRole==3? "checked":""}/>普通用户
                </c:otherwise>
				</c:choose>
                 
					</div>
					<div class="providerAddBtn">
						<input type="button" value="保存" id="update" /> <input
							type="button" value="返回" onclick="history.back(-1)" />
					</div>
				</form>
			</div>

		</div>
	</section>
	<c:import url="footer.jsp"></c:import>
	<!-- 尾部 -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/User_update.js"></script>
</body>
</html>