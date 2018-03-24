
// 用户管理页面上点击删除按钮弹出删除框(userList.html)
$(function() {
	var userCode;
	$('.removeUser').click(function() {
		userCode = jQuery(this).attr("UserCode");
		$('.zhezhao').css('display', 'block');
		$('#removeUse').fadeIn();
	});

	$('#no').click(function() {
		$('.zhezhao').css('display', 'none');
		$('#removeUse').fadeOut();
	});

	$("#yes").click(function() {
		$('.zhezhao').css('display', 'none');
			$('#removeUse').fadeOut();
			
				$.get("${pageContext.request.contextPath}/smbms/UserServlet/Delete",{"userCode" : userCode},function(Return) {
					alert(Return);
						window.location.href = "${pageContext.request.contextPath}/smbms/UserServlet/User";
							});
		});
});


$(function() {
	$("#jump").click(function() {
		var totalPage = document.getElementById("totalPage").value;
		var userName = document.getElementById("Name").value;
		var yeshu = parseInt(document.getElementById("inputPage").value);
		if(isNaN(yeshu)){
			alert("请输入数字");
			return;
		}
		
		if(yeshu<1 || yeshu>totalPage){
				alert("输入的长度不符合当前页数");
				return;
			}			
		window.location.href="${pageContext.request.contextPath}/smbms/UserServlet/User?currentPage="+ yeshu+"&userName="+userName;
	});
});
//模糊查询
$(function(){
	$("#chaxun").click(function(){
		var userName=document.getElementById("userName").value;
		window.location.href="${pageContext.request.contextPath}/smbms/UserServlet/User?userName="+userName;
	});
});

