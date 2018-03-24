<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
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
            <span>账单管理页面 >> 信息查看</span>
        </div>
        <div class="providerView">
            <p><strong>订单编号：</strong><span>${bill.billCode }</span></p>
            <p><strong>商品名称：</strong><span>${bill.productName }</span></p>
            <p><strong>商品单位：</strong><span>${bill.productUnit }</span></p>
            <p><strong>商品数量：</strong><span>${bill.productCount }</span></p>
            <p><strong>总金额：</strong><span>${bill.totalPrice }</span></p>
            <p><strong>供应商：</strong><span>${bill.proName }</span></p>
            <p><strong>是否付款：</strong><span>${bill.isPayment==2?"支付":"未支付" }</span></p>
            <a href="${pageContext.request.contextPath}/smbms/BillServlet/Bill">返回</a>
        </div>
    </div>
</section>
<!-- 尾部 -->
<c:import url="footer.jsp"></c:import>
</body>
</html>