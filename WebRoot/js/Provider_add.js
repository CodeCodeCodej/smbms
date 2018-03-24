// 增加页面的校验
$(function() {
	var num1;
	var num2;
	var num3;
	var num4;
	$("#providerId").blur(function() {
		var proCode = document.getElementById("providerId").value;
		if (proCode == "") {
			num1 = 0;
			$("#span1").text("供应商编码不能为空");
			$("#span1").css("color", "red");
		} else {
			num1 = 1;
			$("#span1").text("可以使用");
			$("#span1").css("color", "green");
		}
	});
	$("#providerName").blur(function() {
		var providerName = document.getElementById("providerName").value;
		if (providerName == "") {
			num2 = 0;
			$("#span2").text("供应商编码姓名不能为空");
			$("#span2").css("color", "red");
		} else {
			num2 = 1;
			$("#span2").text("可以使用");
			$("#span2").css("color", "green");
		}
	});
	$("#people").blur(function() {
		var people = document.getElementById("people").value;
		if (people == "") {
			num3 = 0;
			$("#span3").text("供应商编码联系人");
			$("#span3").css("color", "red");
		} else {
			num3 = 1;
			$("#span3").text("可以使用");
			$("#span3").css("color", "green");
		}
	});
	$("#phone").blur(function() {
		var phone = document.getElementById("phone").value;
		var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
		if (phone == "") {
			num4 = 0;
			$("#span4").text("电话不能为空");
			$("#span4").css("color", "red");
		} else if (!myreg.test(phone)) {
			num4 = 0;
			$("#span4").text("电话格式不正确");
			$("#span4").css("color", "red");
		} else {
			num4 = 1;
			$("#span4").text("格式正确");
			$("#span4").css("color", "green");
		}

	});
	$("#sub")
			.click(
					function() {
						var num = num1 * num2 * num3 * num4;
						var from = $("#providerfrom").serialize();
						if (num == 1) {
							$
									.post(
											"${pageContext.request.contextPath}/smbms/ProviderServlet/Add",
											from,
											function(Return) {
												if (Return == "1") {
													alert("增加成功");
													window.location.href = "${pageContext.request.contextPath}/smbms/ProviderServlet/Provider";
												} else {
													alert("增加失败");
													window.location.href = "${pageContext.request.contextPath}/smbms/ProviderServlet/add";
												}
											});
						} else {
							alert("请将资料补全");
							window.location.href = "${pageContext.request.contextPath}/smbms/ProviderServlet/add";
						}
					});
});