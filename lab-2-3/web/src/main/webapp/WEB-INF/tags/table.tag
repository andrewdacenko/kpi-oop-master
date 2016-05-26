<%@tag description="Table fragment template" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="tableTitle" required="true"%>
<%@attribute name="columns" required="true" type="String[]"%>
<%@attribute name="students" required="true" type="java.util.ArrayList<com.andrewdacenko.Student>"%>

<h2 class="header">${tableTitle}</h2>
<table class="table">
    <thead>
        <tr>
            <c:forEach var="col" items="${columns}">
                <th>${col}</th>
            </c:forEach>
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <c:when test="${students.size() > 0}">
                <c:forEach var="student" items="${students}">
                    <tr>
                        <c:forEach var="col" items="${columns}" varStatus="loop">
                            <td>${student.getElementByIndex(loop.index)}</td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="100">No data</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </tbody>
</table>