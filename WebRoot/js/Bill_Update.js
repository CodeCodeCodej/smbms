
$(function(){
	var num1=1;
	var num2=1;
	var num3=1;
	var num4=1;
	var num5=1;
	$("#providerName").blur(function(){
		var providerName = document.getElementById("providerName").value;
		if(providerName==""){
				num1 = 0;
				$("#span1").text("商品名称不能为空");
				$("#span1").css("color", "red");
			} else {
				num1 = 1;
				$("#span1").text("√");
				$("#span1").css("color", "green");
			}
	});
	$("#productUnit").blur(function(){
		var productUnit = document.getElementById("productUnit").value;
		if(productUnit==""){
				num2 = 0;
				$("#span2").text("商品单位不能为空");
				$("#span2").css("color", "red");
			} else {
				num2 = 1;
				$("#span2").text("√");
				$("#span2").css("color", "green");
			}
	});	$("#productCount").blur(function(){
		var productCount = document.getElementById("productCount").value;
		if(productCount==""){
				num3 = 0;
				$("#span3").text("商品数量不能为空");
				$("#span3").css("color", "red");
			} else {
				num3 = 1;
				$("#span3").text("√");
				$("#span3").css("color", "green");
			}
	});	$("#totalPrice").blur(function(){
		var providerName = document.getElementById("totalPrice").value;
		if(totalPrice==""){
				num4 = 0;
				$("#span4").text("总金额不能为空");
				$("#span4").css("color", "red");
			} else {
				num4 = 1;
				$("#span4").text("√");
				$("#span4").css("color", "green");
			}
	});	$("#proName").blur(function(){
		var proName = document.getElementById("proName").value;
		if(proName==""){
				num5 = 0;
				$("#span5").text("供应商不能为空");
				$("#span5").css("color", "red");
			} else {
				num5 = 1;
				$("#span5").text("√");
				$("#span5").css("color", "green");
			}
	});
	$("#subm").click(function(){
		var num=num1*num2*num3*num4*num5;
		var from=$("#billFrom").serialize();
		if(num==1){
			$.post("${pageContext.request.contextPath}/smbms/BillServlet/Update",from,function(Return){
				
				if(Return=="1"){
					alert("修改成功");
					window.location.href ="${pageContext.request.contextPath}/smbms/BillServlet/Bill";
				}else{
					alert("修改失败");
					window.location.href ="${pageContext.request.contextPath}/smbms/BillServlet/Idupdate";
				}
			});
		}else{
			alert("请将资料补全");
		}
	});
});