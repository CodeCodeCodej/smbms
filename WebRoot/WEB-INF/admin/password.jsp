<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>超市账单管理系统</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
</head>
<body>
<c:import url="head.jsp"></c:import>
	<!--主体内容-->
	<section class="publicMian ">
		<c:import url="left.jsp"></c:import>
		<div class="right">
			<div class="location">
				<strong>你现在所在的位置是:</strong> <span>密码修改页面</span>
			</div>
			<div class="providerAdd">
				<form action="#"
					method="post">
					<!--div的class 为error是验证错误，ok是验证成功-->
					<div class="">
						<label for="oldPassword">旧密码：</label> <input type="password"
							name="oldPassword" id="oldPassword" required " />
						<span id="span1">*请输入原密码</span>
					</div>
					<div>
						<label for="newPassword">新密码：</label> <input type="password"
							name="newPassword" id="newPassword" required /> <span id="span2">*请输入新密码</span>
					</div>
					<div>
						<label for="reNewPassword">确认新密码：</label> <input type="password"
							name="reNewPassword" id="reNewPassword" required /> <span id="span3">*请输入新确认密码，保证和新密码一致</span>
					</div>
					<div class="providerAddBtn">
						<!--<a href="#">保存</a>-->
						<input type="button" value="保存"  id="submit"/>
					</div>
				</form>
			</div>
		</div>
	</section>
		<c:import url="footer.jsp"></c:import>
	<script type="text/javascript">
	$(function(){
	var num;
	var num1;
	var num2;
	//对旧密码进行判断
	  $("#oldPassword").blur(function(){
	   var userPassword = document.getElementById("oldPassword").value;
	    if(userPassword==""){
	      $("#span1").text("不能为空");
	      $("#span1").css("color","red");
	      return;
	    }else{
	     $.post("${pageContext.request.contextPath}/smbms/PasswordServlet/verifyPassword", {"userPassword":userPassword}, function(te) {
				$("#span1").html(te);
			});
			}
	});
	//对新密码进行正则判断
	$("#newPassword").blur(function(){
	 var reg=/^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,22}$/; 
	 if(($("#newPassword").val())==""){
	      $("#span2").text("不能为空");
	      $("#span2").css("color","red");
	      return;
	 }
	if(!reg.test($("#newPassword").val())){
	num=0;
	 $("#span2").text("格式有误，请输入6~12位的数字，字母或特殊字符！");
	 $("#span2").css("color","red");
	}else{
	num=1;
	  $("#span2").text("密码可以使用");
	     $("#span2").css("color","green");
	}
	});
	//对确认密码进行判断
	    $("#reNewPassword").keyup(function(){
	    var newPassword= document.getElementById("newPassword").value;
	    var reNewPassword= document.getElementById("reNewPassword").value;
	    if(reNewPassword==""){
	         num1=0;
	         $("#span3").text("不能为空");
	         $("#span3").css("color","red");
	         return;
	    }
	     if(newPassword==reNewPassword){
	     num1=1;
	        $("#span3").text("两次密码相同");
	        $("#span3").css("color","green");
	        }else{
	        num1=0;
	         $("#span3").text("两次密码不同");
	         $("#span3").css("color","red");
	     }
	});
	  $("#submit").click(function(){
	  num2=num*num1;
	      var newPassword= document.getElementById("newPassword").value;
	      if(num2==1){
	       $.post("${pageContext.request.contextPath}/smbms/PasswordServlet/UpdatePassword",{"newPassword":newPassword},function(Return){
	        alert(Return);
	         });
	         //设置多少秒之后跳转到登陆页面
	         setTimeout("window.location.href = '${pageContext.request.contextPath}/index.jsp'",200);
	         }else{
	         alert("请将资料补全");}
	       });
	
	});
	

	</script>

</body>
</html>