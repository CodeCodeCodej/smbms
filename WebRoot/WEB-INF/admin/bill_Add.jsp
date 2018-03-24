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
				<strong>你现在所在的位置是:</strong> <span>账单管理页面 >> 订单添加页面</span>
			</div>
			<div class="providerAdd">
				<form action="#" id="AddFrom">
					<!--div的class 为error是验证错误，ok是验证成功-->
					<div class="">
						<label for="billId">订单编码：</label> <input type="text" name="billId"
							id="billId" required /> <span id="span1">*请输入订单编码</span>
					</div>
					<div>
						<label for="billName">商品名称：</label> <input type="text"
							name="billName" id="billName" required /> <span id="span2">*请输入商品名称</span>
					</div>
					<div>
						<label for="billCom">商品单位：</label> <input type="text"
							name="billCom" id="billCom" required /> <span id="span3">*请输入商品单位</span>

					</div>
					<div>
						<label for="billNum">商品数量：</label> <input type="text"
							name="billNum" id="billNum" required /> <span id="span4">*请输入大于0的正自然数，小数点后保留2位</span>
					</div>
					<div>
						<label for="money">总金额：</label> <input type="text" name="money"
							id="money" required /> <span id="span5">*请输入大于0的正自然数，小数点后保留2位</span>
					</div>
					<div>
						<label>供应商：</label> <select name="supplier" id="supplier">
							<option value="">--请选择相应的提供商--</option>
							<c:forEach var="pro" items="${proName }">
								<option value="${pro.id }">${pro.proName }</option>
							</c:forEach>
						</select> <span id="span6">*请选择供应商</span>
					</div>
					<div>
						<label>是否付款：</label> <input type="radio" name="isPayment" checked value="1"/>未付款
						<input type="radio" name="isPayment" value="2"/>已付款
					</div>
					<div class="providerAddBtn">
						<!--<a href="#">保存</a>-->
						<!--<a href="billList.html">返回</a>-->
						<input type="button" value="保存" id="submit" /> <input
							type="button" value="返回" onclick="history.back(-1)" />
					</div>
				</form>
			</div>

		</div>
	</section>
	<!-- 尾部 -->
	<c:import url="footer.jsp"></c:import>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/Bill_add.js"></script>

</body>
</html>