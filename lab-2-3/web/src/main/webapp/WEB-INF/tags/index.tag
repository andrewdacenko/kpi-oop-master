<%@tag description="Index template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">
      <h1>Welcome</h1>
      <ol class="breadcrumb">
        <li><a href="index.jsp">Home</a></li>
        <li><a href="best.jsp">Best</a></li>
        <li><a href="foreign.jsp">Foreign</a></li>
        <li><a href="add.jsp">Add</a></li>
      </ol>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p id="copyright">Copyright 2016, OOP JSP Master.</p>
    </jsp:attribute>
    <jsp:body>
        <jsp:doBody/>
    </jsp:body>
</t:layout>