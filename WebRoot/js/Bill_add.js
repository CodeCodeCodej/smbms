$(function() {
	$("#billId").blur(function() {
		var billId = document.getElementById("billId").value;
		if (billId == "") {
			num1 = 0;
			$("#span1").text("订单编码不能为空");
			$("#span1").css("color", "red");
		} else {
			num1 = 1;
			$("#span1").text("√");
			$("#span1").css("color", "green");
		}
	});
	$("#billName").blur(function() {
		var billName = document.getElementById("billName").value;
		if (billName == "") {
			num2 = 0;
			$("#span2").text("商品名称不能为空");
			$("#span2").css("color", "red");
		} else {
			num2 = 1;
			$("#span2").text("√");
			$("#span2").css("color", "green");
		}
	});
	$("#billCom").blur(function() {
		var billCom = document.getElementById("billCom").value;
		if (billCom == "") {
			num3 = 0;
			$("#span3").text("商品单位不能为空");
			$("#span3").css("color", "red");
		} else {
			num3 = 1;
			$("#span3").text("√");
			$("#span3").css("color", "green");
		}
	});
	$("#billNum").blur(function() {
		var ret = /^((\d+\.\d*[1-9]\d{1})|(\d*[1-9]\d*\.\d{2}))$/;
		var billNum = document.getElementById("billNum").value;
		if (billNum == "") {
			num4 = 0;
			$("#span4").text("商品数量");
			$("#span4").css("color", "red");
		} else if (!ret.test(billNum)) {
			$("#span4").text("格式不正确，请保留两位小数");
			$("#span4").css("color", "red");
		} else {
			num4 = 1;
			$("#span4").text("√");
			$("#span4").css("color", "green");
		}
	});
	$("#money").blur(function() {
		var ret = /^((\d+\.\d*[1-9]\d{1})|(\d*[1-9]\d*\.\d{2}))$/;
		var money = document.getElementById("money").value;
		if (money == "") {
			num5 = 0;
			$("#span5").text("总金额不能为空");
			$("#span5").css("color", "red");
		} else if (!ret.test(money)) {
			$("#span5").text("格式不正确，请保留两位小数");
			$("#span5").css("color", "red");
		} else {
			num5 = 1;
			$("#span5").text("√");
			$("#span5").css("color", "green");
		}
	});
	$("#supplier").blur(function() {
		var supplier = document.getElementById("supplier").value;
		if (supplier == "") {
			num6 = 0;
			$("#span6").text("供应商不能为空");
			$("#span6").css("color", "red");
		} else {
			num6 = 1;
			$("#span6").text("√");
			$("#span6").css("color", "green");
		}
	});
	$("#submit")
			.click(
					function() {
						var num = num1 * num2 * num3 * num4 * num5 * num6;
						var from = $("#AddFrom").serialize();
						if (num == 1) {
							$
									.post(
											"${pageContext.request.contextPath}/smbms/BillServlet/ADD",
											from,
											function(Return) {
												if (Return == "1") {
													alert("增加成功");
													window.location.href = "${pageContext.request.contextPath}/smbms/BillServlet/Bill";
												} else {
													alert("增加失败");
													window.location.href = "${pageContext.request.contextPath}/smbms/BillServlet/add";
												}
											});
						} else {
							alert("请将资料补全");
						}
					});
});