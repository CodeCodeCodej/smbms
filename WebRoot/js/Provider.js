//删除
$(function() {
	var proCode;
	var providerId;
	$('.removeProvider').click(function() {
		proCode = jQuery(this).attr("delete");
		providerId = jQuery(this).attr("value");
		$('.zhezhao').css('display', 'block');
		$('#removeProv').fadeIn();
	});
	$('#no').click(function() {
		$('.zhezhao').css('display', 'none');
		$('#removeProv').fadeOut();
	});
	$("#yes").click(function() {
		$('.zhezhao').css('display', 'none');
		$('#removeProv').fadeOut();
		
		$.post("${pageContext.request.contextPath}/smbms/ProviderServlet/zhifu",{"providerId" : providerId},function(Re) {
			if(Re=="0"){
				alert("该供应商有未付款的订单");
			}else{
				$.get("${pageContext.request.contextPath}/smbms/ProviderServlet/Delete",{"proCode" : proCode},function(Return) {
					alert(Return);
						window.location.href = "${pageContext.request.contextPath}/smbms/ProviderServlet/Provider";
					});
			}
			});
	/*	$.get("${pageContext.request.contextPath}/smbms/ProviderServlet/Delete",{"proCode" : proCode},function(Return) {
			alert(Return);
				window.location.href = "${pageContext.request.contextPath}/smbms/ProviderServlet/Provider";
			});*/
		});
});

//模糊查询
$(function(){
	$("#button").click(function(){
		var proName=document.getElementById("proName").value;
		window.location.href = "${pageContext.request.contextPath}/smbms/ProviderServlet/Provider?proName=" + proName;
	});
});
//跳页
$(function() {
	$("#jump").click(function() {
		var totalPage = document.getElementById("totalPage").value;
		var proName = document.getElementById("Name").value;
		var yeshu = parseInt(document.getElementById("inputPage").value);
		if(isNaN(yeshu)){
			alert("请输入数字");
			return;
		}
		
		if(yeshu<1 || yeshu>totalPage){
				alert("输入的长度不符合当前页数");
				return;
			}	
		window.location.href="${pageContext.request.contextPath}/smbms/ProviderServlet/Provider?currentPage="+ yeshu+"&proName="+proName;
	});
});
