<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,com.andrewdacenko.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="db" class="com.andrewdacenko.StoreDatabase"/>

<% request.setAttribute("columns", Student.HEADER);%>
<% request.setAttribute("fields", Student.FIELDS);%>
<% request.setAttribute("countries", Student.COUNTRIES);%>
<% request.setAttribute("genders", Student.GENDERS);%>

<t:index>
    <jsp:body>
        <form method="POST" action="process.jsp" style="padding-bottom: 4rem;">
            <div class="form-group">
                <label for="form_control_0" class="from-label">First name</label>
                <input id="form_control_0" class="form-control" name="first_name" required>
            </div>

            <div class="form-group">
                <label for="form_control_1" class="from-label">Last name</label>
                <input id="form_control_1" class="form-control" name="last_name" required>
            </div>

            <div class="form-group">
                <label for="form_control_2" class="from-label">Middle name</label>
                <input id="form_control_2" class="form-control" name="middle_name" required>
            </div>

            <div class="form-group">
                <label for="form_control_3" class="from-label">ID</label>
                <input id="form_control_3" type="number" class="form-control" name="id" required min="1" max="99999">
            </div>

            <div class="form-group">
                <label for="form_control_4" class="from-label">Course</label>
                <input id="form_control_4" type="number" class="form-control" name="course" required min="1" max="5">
            </div>

            <div class="form-group">
                <label for="form_control_5" class="from-label">Country</label>
                <select id="form_control_5" class="form-control" name="country" required>
                    <c:forEach var="country" items="${countries}">
                        <option value="${country}">${country}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="form_control_6" class="from-label">Gender</label>
                <select id="form_control_6" class="form-control" name="gender" required>
                    <c:forEach var="gender" items="${genders}">
                        <option value="${gender}">${gender}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="form_control_7" class="from-label">Mark</label>
                <input id="form_control_7" type="number" class="form-control" name="mark" required min="1" max="5">
            </div>

        <button type="submit" class="btn btn-primary">Add</button>
    </form>
    </jsp:body>
</t:index>
