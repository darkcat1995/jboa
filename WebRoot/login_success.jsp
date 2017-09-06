<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>成功页</title>
	</head>
	<body>
		<s:if test="%{#session.employee!=null}">
		<h1>
			${sessionScope.employee.name } 登录成功
		</h1>
		</s:if>
		<s:else>
		<h1>
			你还没登录，请您先登录。
		</h1>
		</s:else>
		<br>
		<a href="login.jsp">继续操作</a>
	</body>
</html>
