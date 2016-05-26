<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,com.andrewdacenko.*" %>

<%
    Student student = new Student();
    student.setLast_name((String)request.getParameter("last_name"));
    student.setFirst_name((String)request.getParameter("first_name"));
    student.setMiddle_name((String)request.getParameter("middle_name"));
    student.setId((String)request.getParameter("id"));
    student.setCourse((String)request.getParameter("course"));
    student.setCountry((String)request.getParameter("country"));
    student.setGender((String)request.getParameter("gender"));
    student.setMark(Double.valueOf((String)request.getParameter("mark")));

    StoreDatabase db = new StoreDatabase();
    db.add(student);

    String site = new String("index.jsp");
    response.sendRedirect(site);
%>
