$('document').ready(function(){
	//用户登录操作
	$('#loginSubmit').bind('click',function(){
		if($('#username').val()==''){
			alert("用户名不能为空");
			return;
		}
		if($('#password').val() == ''){
			alert('密码不能为空');
			return;
		}
		var loginUrl = '/dispatcher/user/login.action';
		var indexUrl = '/dispatcher/user/userHomeIndex.action';
		var data = {};
		data.username = $('#username').val();
		data.password = $('#password').val();
		$.post(loginUrl,data,function(response){
			console.log(response);
			if (response == 'loginSuccess'){
				alert("登录成功");
				window.location.href = indexUrl;
			}
		});
	})
});