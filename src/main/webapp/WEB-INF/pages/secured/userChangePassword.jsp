<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
</head>
<body>
<h5 align="right">${ModelMessage}  <a href="/logoutPage">Log Out</a></h5><br>
<%@ include file="headerInclude.jsp"%>
Welcome ${ModelMessage},


<h2 align="center">Change Password</h2>
<br>

<form action="${pageContext.request.contextPath}/secured/changePassword" method="post">
		<table align="center">
			<tr>
				<td>Current Password: </td><td><input type="password" name="currentPassword"/></td>
			</tr>
			<tr>
				<td>New Password : </td><td><input type="password" name="newPassword"/></td>
			</tr>
			<tr>
				<td>Confirm Password : </td><td><input type="password" name="confirmPassword"/></td>
			</tr>
			<tr>
				<td rowspan="2"><input type="submit" value="Change"/></td>
			</tr>	
		</table>
	</form>

</body>
</html>