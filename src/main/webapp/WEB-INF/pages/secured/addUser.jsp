<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
     
	function userRolesValidation() {
		
		var varUserRole = document.addUser.roles;
		for (var i = 0; i < varUserRole.length; i++) {
			if (varUserRole[i].checked)				
			break;
		}
		if (i == varUserRole.length)
			return alert("Please Select the User Role");
		
		addUser.submit();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New User</title>
</head>
<body>
<%@ include file="headerInclude.jsp"%>
<form:form name="addUser" action="${pageContext.request.contextPath}/secured/addUser" method="post" commandName="newUser">
		<table align="center">
			<tr>
				
				<td>Email Id: </td><td><form:input path="emailid"/></td>
			</tr>
			<tr>
				<td>First Name: </td><td><form:input path="firstname"/></td>
			</tr>
			<tr>
				<td>Last Name: </td><td><form:input path="lastname"/></td>
			</tr>
			<tr>
				<td>User Name: </td><td><form:input path="username"/></td>
			</tr>
			<tr>
				<td>Roles : </td>
				<td>
				<c:forEach var="role" items="${roleList}">
    				<td><form:checkbox value="${role.id}" path="roles"/>${role.roleName}</td>  
        
   				</c:forEach>
				</td>
			</tr>
			<tr>
				<td rowspan="2"><input type="button" value="Add User" onclick="userRolesValidation()"/></td>
			</tr>	
		</table>
	</form:form>
</body>
</html>