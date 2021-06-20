var E3MALL = {
	checkLogin : function(){
		var userTokenCookie = $.cookie("user_token");
		if(!userTokenCookie){
			return ;
		}
		var ssoAddress=$("#ssoAddress").val();
		console.log("ssoAddress: "+ssoAddress);
		$.ajax({
			url : ssoAddress+"/user/token/" + userTokenCookie,
			dataType : "jsonp",
			type : "GET",
			jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
			success : function(data){
				console.log(data);
				if(data.status === 200){
					console.log(data);
					var username = data.data.username;
					var html = username + "，欢迎来到宜立方购物网！<a href=\"http://www.e3mall.cn/user/logout.html\" class=\"link-logout\">[退出]</a>";
					$("#loginbar").html(html);
				}
			}
		});
	}
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	E3MALL.checkLogin();
});