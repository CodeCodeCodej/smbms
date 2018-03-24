//订单管理页面上点击删除按钮弹出删除框
$(function () {
	var id;
    $('.removeBill').click(function () {
    	id = jQuery(this).attr("delete");
        $('.zhezhao').css('display', 'block');
        $('#removeBi').fadeIn();
    });
    $('#no').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#removeBi').fadeOut();
    });
    $("#yes").click(function() {
		$('.zhezhao').css('display', 'none');
	    $('#removeBi').fadeOut();
	    $.post("${pageContext.request.contextPath}/smbms/BillServlet/Delete",{"id" : id},function(Return) {
			alert(Return);
		window.location.href = "${pageContext.request.contextPath}/smbms/BillServlet/Bill";
			});
	    });
});
//模糊查询
$(function(){
	$("#button").click(function(){
		var productName= document.getElementById("productName").value;
		var isPayment= document.getElementById("isPayment").value;
		var providerId= document.getElementById("providerId").value;
		window.location.href = "${pageContext.request.contextPath}/smbms/BillServlet/Bill?productName="+productName+"&isPayment="+isPayment+"&providerId="+providerId;
	});
	
	$("#jump").click(function() {
		var totalPage = document.getElementById("totalPage").value;
		var Name = document.getElementById("Name").value;
		var providerId = document.getElementById("providerID").value;
		var isPayment = document.getElementById("IsPayment").value;
		var yeshu = parseInt(document.getElementById("inputPage").value);
		if(isNaN(yeshu)){
			alert("请输入数字");
			return;
		}
		
		if(yeshu<1 || yeshu>totalPage){
				alert("输入的长度不符合当前页数");
				return;
			}	
		window.location.href="${pageContext.request.contextPath}/smbms/BillServlet/Bill?currentPage="+ yeshu+"&productName="+Name+"&isPayment="+isPayment+"&providerId="+providerId;
	});
});
