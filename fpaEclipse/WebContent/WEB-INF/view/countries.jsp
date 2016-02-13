<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Countries List</title>
</head>
<body>
	<h1>countries list</h1>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Language</th>
			<th colspan="2">Action</th>
		</tr>
		<jsp:useBean id="country" class="entity.Country" />
		<c:forEach var="country" items="${countries}">
			<tr>
				<td>${country.id}</td>
				<td>${country.name}</td>
				<td>${country.language}</td>
				<td><a href="FlowControl?id=${country.id}&logic=FetchCountry">Edit</a></td>
				<td><a href="FlowControl?id=${country.id}&logic=RemoveCountry">Delete</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5"><a href="FlowControl?logic=FetchCountry&id=">Insert New</a></td>
		</tr>
		<tr>
			<td colspan="5"><a href="index.jsp">Exit</a></td>
		</tr>
	</table>

</body>
</html>