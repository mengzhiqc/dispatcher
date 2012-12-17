$('document').ready(function() {
	// 提交按钮动作

	$('#login').submit(function() {
		if ($('#login_username').val() == '') {
			alert("用户名不能为空");
			return false;
		}
		if ($('#login_password').val() == '') {
			alert('密码不能为空');
			return false;
		}

		var loginUrl = '/dispatcher/user/login.action';
		var indexUrl = '/dispatcher/user/userHomeIndex.action';

		var data = {};
		data.username = $('#login_username').val();
		data.password = $('#login_password').val();
		$.post(loginUrl, data, function(rs) {
			if (rs == 'loginSuccess') {
				alert("登录成功");
				window.location.href = indexUrl;
			} else {
				alert("登录失败");
			}
		});

		return false;
	});

});