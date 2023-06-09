<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.karthik.web.jdbc.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Students</title>
<link type="text/css" rel="stylesheet" href="CSS/style.css">
</head>

<body>
	<div id="wrapper" >
		<div id ="header" >
			<h2>Academy of Intellectuals</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		
			<input type="button" value="Add Student" onclick="window.location.href='add-student-form.jsp';return false;"
			class="add-student-button">
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="student" items="${STUDENT_LIST}">
					
					<c:url var="tempLink" value="StudentControllerServlet">
						<c:param name="command" value="LOAD"/>
						<c:param name="studentId" value="${student.id}"/>
					</c:url>
					
					<c:url var="deleteLink" value="StudentControllerServlet">
						<c:param name="command" value="DELETE"/>
						<c:param name="studentId" value="${student.id}"/>
					</c:url>
					
					<tr>
						<td>${student.firstName}</td>
						<td>${student.lastName}</td>
						<td>${student.email}</td>
						<td>
							<a href="${tempLink}">Update</a> |
							<a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete the student?'))) return false">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>