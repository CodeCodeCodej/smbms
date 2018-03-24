<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>系统登录 - 超市账单管理系统</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />
<style type="text/css">
#button {
	width: 70px;
	height: 42px;
	background: #436EEE
}
</style>
</head>
<body class="login_bg">
	<section class="loginBox">
		<header class="loginHeader">
			<h1>超市账单管理系统</h1>
		</header>
		<section class="loginCont">
			<form class="loginForm"
				action="${pageContext.request.contextPath}/LoginServlet/login"
				method="post" id="from">
				<div class="inputbox">
					<%-- <div id="div">${tc==null?"":tc}</div> --%>
					<label for="user">用户名：</label> <input id="user" type="text"
						name="usercode"  placeholder="请输入用户名" required />
				</div>
				<div class="inputbox">
					<label for="mima">密码：</label> <input id="mima" type="password"
						name="userpassword"  placeholder="请输入密码" required />
				</div>
				<div class="subBtn">
					<input type="button" value="登录" id="button" /> <input type="reset"
						value="重置" id="reset" />
				</div>
			</form>
		</section>
	</section>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#button").click(function() {
			var from = $("#from").serialize();
			$.post("${pageContext.request.contextPath}/LoginServlet/login",from,function(Return){
                 if(Return=="1"){
                 window.location.href = "${pageContext.request.contextPath}/smbms/PasswordServlet/welcome";
                 }else {
                 alert("登陆失败，账号错误");
                 	window.location.href = "${pageContext.request.contextPath}/index.jsp";
                 }
			});
			});
			

		if ("${sessionScope.user.userCode}" != "") {
				window.location.href = "${pageContext.request.contextPath}/smbms/PasswordServlet/welcome";
			}
		});
	</script>

</body>
</html>