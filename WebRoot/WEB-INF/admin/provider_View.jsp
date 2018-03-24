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
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面 >> 信息查看</span>
        </div>
        <div class="providerView">
            <p><strong>供应商编码：</strong><span>${pro.proCode }</span></p>
            <p><strong>供应商名称：</strong><span>${pro.proName }</span></p>
            <p><strong>联系人：</strong><span>${pro.proContact }</span></p>
            <p><strong>联系电话：</strong><span>${pro.proPhone }</span></p>
            <p><strong>传真：</strong><span>${pro.proFax}</span></p>
            <p><strong>描述：</strong><span>${pro.proDesc }</span></p>

            <a href="${pageContext.request.contextPath}/smbms/ProviderServlet/Provider">返回</a>
        </div>
    </div>
</section>
<!-- 尾部 -->
	<c:import url="footer.jsp"></c:import>

</body>
</html>