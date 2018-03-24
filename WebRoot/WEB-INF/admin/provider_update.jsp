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
				<strong>你现在所在的位置是:</strong> <span>供应商管理页面 >> 供应商修改页</span>
			</div>
			<div class="providerAdd">
				<form action="#" id="proUpdateFrom">
					<!--div的class 为error是验证错误，ok是验证成功-->
					<input type="hidden" name="id" value="${pro.id }">
					<div class="">
						<label for="providerId">供应商编码：</label> <input type="text"
							name="providerId" id="providerId" value=${pro.proCode } /> <span
							id="span1">*</span>
					</div>
					<div>
						<label for="providerName">供应商名称：</label> <input type="text"
							name="providerName" id="providerName" value="${pro.proName }" />
						<span id="span2">*</span>
					</div>
					<div>
						<label for="people">联系人：</label> <input type="text" name="people"
							id="people" value="${pro.proContact }" /> <span id="span3">*</span>

					</div>
					<div>
						<label for="phone">联系电话：</label> <input type="text" name="phone"
							id="phone" value="${pro.proPhone }" /> <span id="span4">*</span>
					</div>
					<div>
						<label for="address">联系地址：</label> <input type="text"
							name="address" id="address" value="${pro.proAddress }" /> <span></span>

					</div>
					<div>
						<label for="fax">传真：</label> <input type="text" name="fax"
							id="fax" value="${pro.proFax }" /> <span></span>

					</div>
					<div>
						<label for="describe">描述：</label> <input type="text"
							name="describe" id="describe" value="${pro.proDesc }" /> <span></span>

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
	<script src="${pageContext.request.contextPath}/js/Provider_update.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
</body>
</html>