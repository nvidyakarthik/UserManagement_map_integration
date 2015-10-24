<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Application Users</title>
</head>
<body>
<h5 align="right"><a href="${pageContext.request.contextPath}/logoutPage">Log Out</a></h5><br>
<%@ include file="headerInclude.jsp"%>
The List of Application User with Status. <br>
<%@ include file="mainGrid.jsp"%>
<h5 align="right"><a href="${pageContext.request.contextPath}/secured/addUser"><input type="button" value="Add User"/></a></h5>
	<h1>Application Users</h1>
        <table style="border: 1px solid; width: 100%; text-align:center">
			<thead style="background:#d3dce3">
        	<tr>
        		<th>User Name</th>
        		<th>First Name</th>
        		<th>Last Name</th>
        		<th>Email Id</th>
        		<th>Active</th>
        		<th>User Roles</th>
        		<th>Last Logged In</th>
        		
        	</tr>
        	</thead>
    	<tbody style="background:#ccc">
        	<c:forEach var="users" items="${ApplicationUsers}">
        	<tr>
        		<td><c:out value="${users.username}"/></td>
        		<td><c:out value="${users.firstname}"/></td>
        		<td><c:out value="${users.lastname}"/></td>
        		<td><c:out value="${users.emailid}"/></td>
        		<td><c:out value="${users.isactive}"/></td>
        		<td><c:forEach var="rolenameslist" items="${users.roles}"><c:out value="${rolenameslist.roleName}"/><samp></samp></c:forEach></td>
        		<td><c:out value="${users.lastlogin}"/></td>
        		
        	</tr>
        	</c:forEach>
        	</tbody>
        </table>
</body>
</html>