<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<strong>你现在所在的位置是:</strong> <span>用户管理页面 >> 用户添加页面</span>
			</div>
			<div class="providerAdd">
				<form action="${pageContext.request.contextPath}/smbms/UserServlet/Add" method="post" id="from">
					<!--div的class 为error是验证错误，ok是验证成功-->
					<div class="user">
						<label for="userId">用户编码：</label> <input type="text" name="userId"
							id="userId"  maxlength="10"/> <span id="span1">*请输入用户编码，且不能重复</span>
					</div>
					<div>
						<label for="userName">用户名称：</label> <input type="text"
							name="userName" id="userName" /> <span id="span">*请输入用户名称</span>
					</div>
					<div>
						<label for="userpassword">用户密码：</label> <input type="password"
							name="userpassword" id="userpassword" /> <span id="span2">*密码长度必须大于6位小于20位</span>

					</div>
					<div>
						<label for="userRemi">确认密码：</label> <input type="password"
							name="userRemi" id="userRemi" /> <span id="span3">*请输入确认密码</span>
					</div>
					<div>
						<label>用户性别：</label> <select name="sex">
							<option value="man">男</option>
							<option value="woman">女</option>
						</select> <span></span>
					</div>
					<div>
						<label for="data">出生日期：</label> <input type="Date" name="data"
							id="data" /> <span id="span5">*</span>
					</div>
					<div>
						<label for="userphone">用户电话：</label> <input type="text"
							name="userphone" id="userphone" /> <span id="span4">*</span>
					</div>
					<div>
						<label for="userAddress">用户地址：</label> <input type="text"
							name="userAddress" id="userAddress" />
					</div>
					<div>
						<label>用户类别：</label> <input type="radio" name="userlei" id="userlei" value="1"/>管理员 
						<input
							type="radio" name="userlei" value="2" id="userlei"/>经理 
						<input type="radio"
							name="userlei" value="3" id="userlei" checked/>普通用户 <span id="span6"></span>

					</div>
					<div class="providerAddBtn">
						<input type="button" value="保存" id="submit" /> <input
							type="button" value="返回" onclick="history.back(-1)" />
					</div>
				</form>
			</div>

		</div>
	</section>
	<!-- 尾部 -->
	<c:import url="footer.jsp"></c:import>
	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/js/User_add.js"></script>
</body>
</html>