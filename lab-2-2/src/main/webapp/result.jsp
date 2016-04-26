<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<html>
<a href="./">Back</a>

<p>Here goes the result:</p>
<ul>
    <c:forEach var="number" items="${numbers}">
	    <li>${number}</li>
	</c:forEach>
</ul>

</html>