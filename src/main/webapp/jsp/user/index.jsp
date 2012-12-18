<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎访问用户中心</title>
</head>
<body>
	<s:set name="username" value="username" />
	<s:if test="%{#username != null}">
		<h4>hi <s:property value="username"/>,欢迎访问用户中心</h4>
	</s:if>
	<s:else>
		<div class=''>现在就去<a href='/dispatcher/login.action'>登录</a>。</div>
	</s:else>
</body>
</html>