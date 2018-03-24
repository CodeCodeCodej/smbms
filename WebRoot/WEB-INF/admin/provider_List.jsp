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
				<strong>你现在所在的位置是:</strong> <span>供应商管理页面</span>
			</div>
			<div class="search">
				<span>供应商名称：</span> <input type="text" placeholder="请输入供应商的名称" name="proName" id="proName" value=""/>  
				<input type="button" value="查询" id="button"/> 
				<c:if test="${sessionScope.user.userRole==1}">
				<a href="${pageContext.request.contextPath}/smbms/ProviderServlet/add">添加供应商</a>
				</c:if>
			</div>
			<!--供应商操作表格-->
			<table class="providerTable" cellpadding="0" cellspacing="0">
				<tr class="firstTr">
					<th width="10%">供应商编码</th>
					<th width="20%">供应商名称</th>
					<th width="10%">联系人</th>
					<th width="10%">联系电话</th>
					<th width="10%">传真</th>
					<th width="10%">创建时间</th>
					<th width="30%">操作</th>
				</tr>
				<c:forEach var="pro" items="${list }">
					<tr>
						<td>${pro.proCode }</td>
						<td>${pro.proName }</td>
						<td>${pro.proContact}</td>
						<td>${pro.proPhone }</td>
						<td>${pro.proFax }</td>
						<td>${pro.creationDate }</td>
						<td>
						<c:if test="${sessionScope.user.userRole==1}">
						<a href="${pageContext.request.contextPath}/smbms/ProviderServlet/View?proCode=${pro.proCode }"><img src="${pageContext.request.contextPath}/img/read.png" alt="查看"title="查看" /> </a> 
						<a href="${pageContext.request.contextPath}/smbms/ProviderServlet/IdUpdate?proCode=${pro.proCode }"><img src="${pageContext.request.contextPath}/img/xiugai.png" alt="修改"title="修改" /> </a> 
						<a href="#" class="removeProvider" delete="${pro.proCode }" value="${pro.id }" ><img src="${pageContext.request.contextPath}/img/schu.png" alt="删除"title="删除" /> </a>
						</c:if>
						</td>
					</tr>
				</c:forEach>
						<tr bgcolor="#CCCCFE">
					<td colspan="7"><span>共${pageBean.totalCount}条数据&nbsp;&nbsp;
					</span>&nbsp;&nbsp;
					<c:if test="${pageBean.totalCount!=0}">
					 <!-- 判断是不是第一页，如果是第一页就不能跳转 --> 
					<c:if test="${pageBean.currentPage!=1 }">
							<span><a
								href="${pageContext.request.contextPath}/smbms/ProviderServlet/Provider?currentPage=${pageBean.currentPage-1}&proName=${param.proName}">上一页</a>
							</span>
						</c:if> <c:forEach begin="1" end="${pageBean.totalPage }" var="page">
							<c:if test="${pageBean.currentPage==page }">
								<a href="javascript:void(0)">${page}</a>
							</c:if>
							<c:if test="${pageBean.currentPage!=page }">
								<a
									href="${pageContext.request.contextPath}/smbms/ProviderServlet/Provider?currentPage=${page}&proName=${param.proName}">${page}</a>
							</c:if>
						</c:forEach> 
						<c:if test="${pageBean.currentPage!=pageBean.totalPage }">
							<span><a
								href="${pageContext.request.contextPath}/smbms/ProviderServlet/Provider?currentPage=${pageBean.currentPage+1}&proName=${param.proName}">下一页</a>
							</span>
						</c:if>
						<span class="page-go-form"><label>跳转至</label> 
						<input type="text" name="inputPage" id="inputPage" value="" />页
							<input type="hidden" id="totalPage" value="${pageBean.totalPage}">
							<input type="hidden" id="Name" value="${param.proName}">
							<button type="button" class="page-btn" id="jump">GO</button> </span> 
							<c:if test="${pageBean.currentPage!=pageBean.totalPage}">
							<span><a
								href="${pageContext.request.contextPath}/smbms/ProviderServlet/Provider?currentPage=${pageBean.totalPage}&proName=${param.proName} ">最后一页</a>
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
	<div class="remove" id="removeProv">
		<div class="removerChid">
			<h2>提示</h2>
			<div class="removeMain">
				<p>你确定要删除该供应商吗？</p>
				<a href="#" id="yes">确定</a> <a href="#" id="no">取消</a>
			</div>
		</div>
	</div>
		<c:import url="footer.jsp"></c:import>

	<!-- 尾部 -->
	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/js/Provider.js"></script>
	<script type="text/javascript">
	$ (function(){
	$("#proName").val("${proName }");
		});
	</script>
</body>
</html>