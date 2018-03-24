<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>超市账单管理系统</title>

</head>
<body>
<a>你好${bill.providerId }</a>
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
				<form action="#" id="billFrom">
					<!--div的class 为error是验证错误，ok是验证成功-->
					<input type="hidden" value="${bill.id }" name="id">
					<div class="">
						<label for="providerId">订单编码：</label> <input type="text"
							name="billCode" id="billCode" value="${bill.billCode }"
							readonly="readonly" /> <span>*</span>
					</div>
					<div>
						<label for="providerName">商品名称：</label> <input type="text"
							name="providerName" id="providerName"
							value="${bill.productName }" /> <span id="span1">*</span>
					</div>
					<div>
						<label for="people">商品单位：</label> <input type="text"
							name="productUnit" id="productUnit" value="${bill.productUnit }" />
						<span id="span2">*</span>

					</div>
					<div>
						<label for="phone">商品数量：</label> <input type="text"
							name="productCount" id="productCount"
							value="${bill.productCount }" /> <span id="span3">*</span>
					</div>
					<div>
						<label for="address">总金额：</label> <input type="text"
							name="totalPrice" id="totalPrice" value="${bill.totalPrice }" />
						<span id="span4">*</span>
					</div>
					<div>
						<label for="fax">供应商：
						</label> 
						<select name="proName" id="proName">
							 <option value="${bill.providerId }">${bill.proName }</option> 
							<c:forEach var="pro" items="${proName }">
							<c:if test="${pro.id!=bill.providerId }"><option value="${pro.id }">${pro.proName }</option></c:if>
							</c:forEach>
						</select>
						<span id="span5">*</span>
					</div>
					<div>
						<label>是否付款：</label> <input type="radio" name="zhifu"
							${bill.isPayment==2? "":"checked" } value="1" />未付款 <input
							type="radio" name="zhifu" ${bill.isPayment==2?
							"checked":"" } value="2" />已付款
					</div>
					<div class="providerAddBtn">
						<!--<a href="#">保存</a>-->
						<!--<a href="billList.html">返回</a>-->
						<input type="button" value="保存" id="subm" /> <input type="button"
							value="返回" onclick="history.back(-1)" />
					</div>
				</form>
			</div>

		</div>
	</section>
	<c:import url="footer.jsp"></c:import>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/Bill_Update.js"></script>
	<!-- 尾部 -->
</body>
</html>