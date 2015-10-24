<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<h5 align="right">${fullName}  <a href="${pageContext.request.contextPath}/logoutPage">Log Out</a></h5><br>
Welcome ${fullName},
<%@ include file="headerInclude.jsp"%>
</body>
</html>