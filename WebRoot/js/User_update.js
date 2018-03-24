$(function() {
	// 对用户的校验
	var num1 = 1;
	var num2 = 1;
	var num3 = 1;
	$("#userName").blur(function() {
		var username = $("#userName").val();
		if (username == "") {
			num1 = 0;
			$("#span1").text("用户姓名不能为空");
			$("#span1").css("color", "red");
		} else {
			num1 = 1;
			$("#span1").text("可以使用");
			$("#span1").css("color", "green");
		}
	});
	$("#data").blur(function() {
		var date = document.getElementById("data").value;
		if (date == "") {
			num2 = 0;
			$("#span2").text("出生日期不能为空");
			$("#span2").css("color", "red");
		} else {
			num2 = 1;
			$("#span2").text("可以使用");
			$("#span2").css("color", "green");
		}
	});
	$("#userphone").blur(function() {
		var phone = document.getElementById("userphone").value;
		var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
		if (phone == "") {
			num3 = 0;
			$("#span3").text("电话不能为空");
			$("#span3").css("color", "red");
		} else if (!myreg.test(phone)) {
			num3 = 0;
			$("#span3").text("电话格式不正确");
			$("#span3").css("color", "red");
		} else {
			num3 = 1;
			$("#span3").text("格式正确");
			$("#span3").css("color", "green");
		}
	});
	$("#update").click(function() {
		var num = num1 * num2 * num3;
		if (num == 1) {
			var from = $("#Updatefrom").serialize();
			$.post("${pageContext.request.contextPath}/smbms/UserServlet/Update",from,function(Return) {
			if (Return == "0") {
				alert("修改失败");
					window.location.href = "${pageContext.request.contextPath}/smbms/UserServlet/IdUpdate";
					} else if (Return == "1") {
					alert("修改成功");
						window.location.href = "${pageContext.request.contextPath}/smbms/UserServlet/User";
						}
						});
						} else {

							alert("请把资料补全");

						}

					});
});