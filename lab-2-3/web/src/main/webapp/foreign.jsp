<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,com.andrewdacenko.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="db" class="com.andrewdacenko.StoreDatabase"/>

<% request.setAttribute("columns", Student.HEADER);%>
<% request.setAttribute("students", db.getGoodForeign());%>

<t:index>
    <jsp:body>
        <t:table tableTitle="${'Foreign 4+ students'}"
                 columns="${columns}"
                 students="${students}">
        </t:table>
    </jsp:body>
</t:index>