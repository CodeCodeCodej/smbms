<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cn.entity.*"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>超市账单管理系统</title>
<style type="text/css">

</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
</head>
<body>
	<!--头部-->
	 <c:import url="head.jsp"></c:import> 
	<!--主体内容-->
	<section class="publicMian ">
	 <c:import url="left.jsp"></c:import> 
		<div class="right">
			<div class="location">
				<strong>你现在所在的位置是:</strong> <span>用户管理页面</span>
			</div>
			<div class="search">
			 <span>用户名：</span> 
			 <input type="text" placeholder="请输入用户名" name="userName" id="userName"  value=""/> 
			 <input type="button" value="查询" id="chaxun"/>
				<c:if test="${sessionScope.user.userRole==1}">
				
				<a href="${pageContext.request.contextPath}/smbms/UserServlet/add">添加用户</a>
				</c:if>
			</div>
			<!--用户-->
			<table class="providerTable" cellpadding="0" cellspacing="0">
				<tr class="firstTr">
					<th width="10%">用户编码</th>
					<th width="20%">用户名称</th>
					<th width="10%">性别</th>
					<th width="10%">年龄</th>
					<th width="10%">电话</th>
					<th width="10%">用户类型</th>
					<th width="30%">操作</th>
				</tr>
				<c:choose>
				<c:when test="${sessionScope.user.userRole==1}">
				<c:forEach var="Map" items="${list}">
					<tr>
						<td>${Map.userCode }</td>
						<td>${Map.userName }</td>
						<td>${Map. gender==2?"男":"女" }</td>
						<td>${Map.age }</td>
						<td>${Map.phone }</td>
						<td>${Map.roleName }</td>
						<td>
						<c:if test="${sessionScope.user.userRole==1}">
						<a href="${pageContext.request.contextPath}/smbms/UserServlet/view?userCode=${Map.userCode }"id="View""><img src="${pageContext.request.contextPath}/img/read.png" alt="查看"title="查看" /> </a>
						<a href="${pageContext.request.contextPath}/smbms/UserServlet/IdUpdate?userCode=${Map.userCode }"id="userCodeUpdate"><img src="${pageContext.request.contextPath}/img/xiugai.png" alt="修改"title="修改" /> </a> 
						
						<c:if test="${sessionScope.user.userCode!=Map.userCode}">
						<a href="#" class="removeUser" UserCode="${Map.userCode }"><img src="${pageContext.request.contextPath}/img/schu.png" alt="删除"title="删除" /> </a>
					</c:if>
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
								href="${pageContext.request.contextPath}/smbms/UserServlet/User?currentPage=${pageBean.currentPage-1}&userName=${param.userName}">上一页</a>
							</span>
						</c:if> <c:forEach begin="1" end="${pageBean.totalPage }" var="page">
							<c:if test="${pageBean.currentPage==page }">
								<a href="javascript:void(0)">${page}</a>
							</c:if>
							<c:if test="${pageBean.currentPage!=page }">
								<a
									href="${pageContext.request.contextPath}/smbms/UserServlet/User?currentPage=${page}&userName=${param.userName}">${page}</a>
							</c:if>
						</c:forEach> 
						<c:if test="${pageBean.currentPage!=pageBean.totalPage }">
							<span><a
								href="${pageContext.request.contextPath}/smbms/UserServlet/User?currentPage=${pageBean.currentPage+1}&userName=${param.userName}">下一页</a>
							</span>
						</c:if>
						<span class="page-go-form"><label>跳转至</label> 
						<input type="text" name="inputPage" id="inputPage" value="" />页
							<input type="hidden" id="totalPage" value="${pageBean.totalPage}">
							<input type="hidden" id="Name" value="${param.userName}">
							<button type="button" class="page-btn" id="jump">GO</button> </span> 
							<c:if test="${pageBean.currentPage!=pageBean.totalPage}">
							<span><a
								href="${pageContext.request.contextPath}/smbms/UserServlet/User?currentPage=${pageBean.totalPage}&userName=${param.userName} ">最后一页</a>
							</span>
						</c:if>
						</c:if>
						</td>
				</tr>
				</c:when>
				<c:otherwise>
						<tr>
						<td>${sessionScope.user.userCode }</td>
						<td>${sessionScope.user.userName }</td>
						<td>${sessionScope.user. gender==2?"男":"女"  }</td>
						<td>${sessionScope.user.age }</td>
						<td>${sessionScope.user.phone }</td>
						<td>${sessionScope.user.userRole==2?"经理":"普通用户" }</td>
						<td>
						<a href="${pageContext.request.contextPath}/smbms/UserServlet/view?userCode=${sessionScope.user.userCode }"id="View""><img src="${pageContext.request.contextPath}/img/read.png" alt="查看"title="查看" /> </a>
						<a href="${pageContext.request.contextPath}/smbms/UserServlet/IdUpdate?userCode=${sessionScope.user.userCode }"id="userCodeUpdate"><img src="${pageContext.request.contextPath}/img/xiugai.png" alt="修改"title="修改" /> </a> 
						
						</td>
					</tr>
				</c:otherwise>
				</c:choose>
			</table>
		</div>
	</section>
	<!-- 	<!--点击删除按钮后弹出的页面-->
	<div class="zhezhao"></div>
	<div class="remove" id="removeUse">
		<div class="removerChid">
			<h2>提示</h2>
			<div class="removeMain">
				<p>你确定要删除该用户吗？</p>
				<a href="#" id="yes">确定</a> <a href="#" id="no">取消</a>
			</div>
		</div>
	</div>
 <c:import url="footer.jsp"></c:import>
	<!-- 尾部 -->
	<script src="${pageContext.request.contextPath}/js/User.js"></script>
	<script type="text/javascript">
	$ (function(){
	$("#userName").val("${userName }");
		});
	</script>
</body>
</html>