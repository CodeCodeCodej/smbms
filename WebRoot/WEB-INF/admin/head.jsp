<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>头部</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/smbms/css/public.css" />
<link rel="stylesheet" href="/smbms/css/style.css" />
</head>
<body>
	<!--头部-->
	<header class="publicHeader">
		<h1>超市账单管理系统</h1>

		<div class="publicHeaderR">
			<p>
				<span>下午好！</span><span style="color: #fff21b"> ${sessionScope.user.userCode}</span> , 欢迎你！
			</p>
			<a href="${pageContext.request.contextPath}/LoginServlet/zhuxiao">退出</a>
		</div>
	</header>
	<!--时间-->
	<section class="publicTime">
		<span id="time">2015年1月1日 11:11 星期一</span> <a href="#">前方高能</a>
	</section>
	<script src="/smbms/js/time.js"></script>
</body>
</html>
