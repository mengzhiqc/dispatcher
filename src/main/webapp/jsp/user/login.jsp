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
					<s:form action='login' method='post' theme='xhtml'>
					<table>
						<tr>
							<td><s:textfield name="username" label="用户名" /></td>
						</tr>
						<tr>
							<td><s:password name="password" label="密码" /></td>
						</tr>
						<tr>
							<td><s:submit align="middle" value="登录" cssClass="btn btn-primary" id="loginSubmit" action="login" />
								
							</td>
						</tr>
					</table>
					
					<a href='/'>注  册</a>
					</s:form>
		</div>
		<div class="span3"></div>
	</div>
</body>
</html>
