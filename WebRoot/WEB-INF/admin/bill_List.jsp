<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
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
				<strong>你现在所在的位置是:</strong> <span>账单管理页面</span>
			</div>
			<div class="search">
				<span>商品名称：</span> <input type="text" placeholder="请输入商品的名称"
					id="productName" name="productName" value="" />
					
				<span>供应商：</span> <select name="providerId" id="providerId">
					<option value="">--请选择--</option>
					<c:forEach var="pro" items="${proName }">
						<option value="${pro.id }">${pro.proName }</option>
					</c:forEach>
				</select>
				 <span>是否付款：</span> 
				 <select name="isPayment" id="isPayment">
					<option value="">--请选择--</option>
					<option value="1">未付款</option>
					<option value="2">已付款</option>
				</select> 
				<input type="button" value="查询" id="button" />
				<!-- 管理员才可以增加 -->
				<c:if test="${sessionScope.user.userRole==1}">
					<a href="${pageContext.request.contextPath}/smbms/BillServlet/add">添加订单
					</a>
				</c:if>
			</div>
			<!--账单表格 样式和供应商公用-->
			<table class="providerTable" cellpadding="0" cellspacing="0">
				<tr class="firstTr">
					<th width="10%">账单编码</th>
					<th width="20%">商品名称</th>
					<th width="10%">供应商</th>
					<th width="10%">账单金额</th>
					<th width="10%">是否付款</th>
					<th width="10%">创建时间</th>
					<th width="30%">操作</th>
				</tr>
				<c:forEach var="bill" items="${list }">
					<tr>
						<td>${bill.billCode }</td>
						<td>${bill.productName }</td>
						<td>${bill.proName }</td>
						<td>${bill.totalPrice }</td>
						<td>${bill.isPayment==1?"未支付":"支付" }</td>
						<td>${bill.creationDate }</td>
						<td><c:if test="${sessionScope.user.userRole==1}">
								<a
									href="${pageContext.request.contextPath}/smbms/BillServlet/View?id=${bill.id}"><img
									src="${pageContext.request.contextPath}/img/read.png" alt="查看"
									title="查看" /> </a>
								<a
									href="${pageContext.request.contextPath}/smbms/BillServlet/Idupdate?id=${bill.id}"><img
									src="${pageContext.request.contextPath}/img/xiugai.png"
									alt="修改" title="修改" /> </a>
								<a href="#" class="removeBill" delete="${bill.id}"><img
									src="${pageContext.request.contextPath}/img/schu.png" alt="删除"
									title="删除" /> </a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				<tr bgcolor="#CCCCFE">
					<td colspan="7"><span>共${pageBaen.totalCount}条数据&nbsp;&nbsp;
					</span>&nbsp;&nbsp; <!-- 判断有没有数据 --> <c:if
							test="${pageBaen.totalCount!=0}">
							<!-- 判断是不是第一页，如果是第一页就不能跳转 -->
							<c:if test="${pageBaen.currentPage!=1 }">
								<span><a
									href="${pageContext.request.contextPath}/smbms/BillServlet/Bill?currentPage=${pageBaen.currentPage-1}&productName=${param.productName }&providerId=${param.providerId}&isPayment=${param.isPayment }">上一页</a>
								</span>
							</c:if>
							<!-- 循环每一页 -->
							<c:forEach begin="1" end="${pageBaen.totalPage }" var="page">
								<!-- 当前页不给点击 -->
								<c:if test="${pageBaen.currentPage==page }">
									<a href="javascript:void(0)">${page}</a>
								</c:if>
								<c:if test="${pageBaen.currentPage!=page }">
									<a
										href="${pageContext.request.contextPath}/smbms/BillServlet/Bill?currentPage=${page}&productName=${param.productName }&providerId=${param.providerId}&isPayment=${param.isPayment }">${page}</a>
								</c:if>
							</c:forEach>
							<!-- 下一页的判断 -->
							<c:if test="${pageBaen.currentPage!=pageBaen.totalPage }">
								<span><a
									href="${pageContext.request.contextPath}/smbms/BillServlet/Bill?currentPage=${pageBaen.currentPage+1}&productName=${param.productName }&providerId=${param.providerId}&isPayment=${param.isPayment }">下一页</a>
								</span>
							</c:if>
							<span class="page-go-form"><label>跳转至</label> <input
								type="text" name="inputPage" id="inputPage" value="" />页 <input
								type="hidden" id="totalPage" name="totalPage"
								value="${pageBaen.totalPage}"> <input type="hidden"
								id="Name" name="Name" value="${param.productName }"> <input
								type="hidden" id="providerID" name="providerID"
								value="${param.providerId}"> <input type="hidden"
								id="IsPayment" name="IsPayment" value="${param.isPayment }">
								<button type="button" class="page-btn" id="jump">GO</button> </span>
							<c:if test="${pageBaen.currentPage!=pageBaen.totalPage}">
								<span><a
									href="${pageContext.request.contextPath}/smbms/BillServlet/Bill?currentPage=${pageBaen.totalPage}&productName=${param.productName }&providerId=${param.providerId}&isPayment=${param.isPayment } ">最后一页</a>
								</span>
							</c:if>
						</c:if>
					</td>
				</tr>

			</table>
		</div>
	</section>

	<!--点击删除按钮后弹出的页面-->
	<div class="zhezhao"></div>
	<div class="remove" id="removeBi">
		<div class="removerChid">
			<h2>提示</h2>
			<div class="removeMain">
				<p>你确定要删除该订单吗？</p>
				<a href="#" id="yes">确定</a> <a href="#" id="no">取消</a>
			</div>
		</div>
	</div>
	<c:import url="footer.jsp"></c:import>
	<!-- 尾部 -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/Bill.js"></script>
	<script type="text/javascript">
	$ (function(){
	$("#productName").val("${productName }");
	$("#providerId option[value = ${providerId}]").attr("selected","selected");
	$("#isPayment option[value = ${isPayment}]").attr("selected","selected");
		});
	</script>
</body>
</html>