//用户名的校验
var num1;
var num2;
var num3;
var num4;
var num5;
var num6;
$(function() {
	$("#userId").blur(function() {
		var uPattern = /^[a-zA-Z0-9_-]{4,16}$/;
		var userId = document.getElementById("userId").value;
			if (userId == "") {
				num1 = 0;
				$("#span1").text("用户名不能为空");
				$("#span1").css("color", "red");
					return;}
			$.post("${pageContext.request.contextPath}/smbms/PasswordServlet/userId",{"userId" : userId},
					function(Return) {
						if (Return == "用户名已存在") {
						num1 = 0;
							$("#span1").text(Return);
							$("#span1").css("color", "red");
							} else {
								if ((uPattern.test(userId))) {
									num1 = 1;
									$("#span1").text("用户名可以使用");
									$("#span1").css("color","green");
									} else {
										num1 = 0;
										$("#span1").text("请输入4到16位,可以包含字母、数字、下划线");
										$("#span1").css("color","red");
											}
									}
						});

					});
});
// 用户的名称不能为空
$(function() {
	$("#userName").blur(function() {
		var username = document.getElementById("userName").value;
		if (username == "") {
			num2 = 0;
			$("#span").text("用户姓名不能为空");
			$("#span").css("color", "red");

		} else {
			num2 = 1;
			$("#span").text("可以使用");
			$("#span").css("color", "green");
		}
	});
});

// 校验密码
$(function() {
	$("#userpassword").blur(function() {
		var password = document.getElementById("userpassword").value;
		var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/;
		if (password == "") {
			num3 = 0;
			$("#span2").text("密码不能为空");
			$("#span2").css("color", "red");
		} else if (!reg.test(password)) {
			num3 = 0;
			$("#span2").text("格式不正确，密码最少6位，不能为纯数字或者纯英文");
			$("#span2").css("color", "red");
		} else {
			num3 = 1;
			$("#span2").text("密码可以使用");
			$("#span2").css("color", "green");
		}
	});
});
// 对确认密码进行判断
$(function() {
	$("#userRemi").keyup(function() {
		var userpassword = document.getElementById("userpassword").value;
		var userRemi = document.getElementById("userRemi").value;
		if (userRemi == userpassword) {
			num4 = 1;
			$("#span3").text("两次密码相同");
			$("#span3").css("color", "green");
		} else {
			num4 = 0;
			$("#span3").text("两次密码不同");
			$("#span3").css("color", "red");
		}
	});
});

// 对电话的验证
$(function() {
	$("#userphone").blur(function() {
		var phone = document.getElementById("userphone").value;
		var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
		if (phone == "") {
			num5 = 0;
			$("#span4").text("电话不能为空");
			$("#span4").css("color", "red");
		} else if (!myreg.test(phone)) {
			num5 = 0;
			$("#span4").text("电话格式不正确");
			$("#span4").css("color", "red");
		} else {
			num5 = 1;
			$("#span4").text("格式正确");
			$("#span4").css("color", "green");
		}

	});
});
// 对出生日期判断
$(function() {
	$("#data").blur(function() {
		var date = document.getElementById("data").value;
		if (date == "") {
			num6 = 0;
			$("#span5").text("出生日期不能为空");
			$("#span5").css("color", "red");
		} else {
			num6 = 1;
			$("#span5").text("可以使用");
			$("#span5").css("color", "green");
		}
	});
});

$(function() {
$("#submit").click(function() {
	var num = num1 * num2 * num3 * num4 * num5 * num6;
		if (num == 1) {var from = $("#from").serialize();
							$
									.post(
											"${pageContext.request.contextPath}/smbms/UserServlet/Add",
											from,
											function(Return) {
												if (Return == "增加失败") {
													alert(Return);
													window.location.href = "${pageContext.request.contextPath}/smbms/UserServlet/Add";
												} else {
													alert(Return);
													window.location.href = "${pageContext.request.contextPath}/smbms/UserServlet/User";
													/*
													 * var password =
													 * "${pageContext.request.contextPath}/smbms/UserServlet/User";
													 * $(".right").load(password);
													 */
												}
											});
						} else {

							alert("请把资料补全");

						}
					});
});