<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="JavaScript" type="text/javascript" src="/dispatcher/js/login.js"></script>
<title>用户登录</title>
</head>
<body>
	<h4>hi <s:property value="username"/>,欢迎访问用户中心</h4> 
	<div class="row">
		<hr/>
		<div class="span6">
			<table class='table'>
				<tbody>
					<tr>
					<td>用户名:</td><td><input id='username' name='username' type='input' placeholder='请输入用户名'/></td>
					</tr>
					<tr>
					<td>密码:</td><td><input id='password' name='password' type='input' placeholder='请输入密码'/></td>
					</tr>
					<tr>
					<td></td><td><a class='btn btn-primary' id='loginSubmit' href='javascript:;'>登 录</a> &nbsp;&nbsp;<a href='/'>注  册</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="span3"></div>
	</div>
</body>
</html>