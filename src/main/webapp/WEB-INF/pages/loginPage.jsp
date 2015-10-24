<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
<title>Login Page</title>
</head>
<body>
	<h2>Welcome to the Login Page EPTBooking Software Application</h2>
	<c:if test="${'fail' eq param.auth}">
		<div style="color:red">
                Login Failed!!!<br />
                Reason : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
         </div>
	</c:if>
	 <form action="j_spring_security_check" method="POST">
	
		<table>
			<tr>
				<td>Username:</td>
				<td><input type='text' name='j_username' /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password'></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
</body>
</html>
