<%--
  Created by IntelliJ IDEA.
  User: tyoma17
  Date: 18.04.2016
  Time: 6:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Stakes</title>
</head>
<body>
    <%@include file="Menu.jsp"%>
<h1>Stakes:</h1>

<table>
    <tr>
        <td>
            <form method="post">
                <input type="submit" name="add stake" value="Add new stake">
            </form>
        </td>
        <td>
            <form method="post">
                <input type="submit" name="delete stake" value="Delete stake">
            </form>
        </td>
    </tr>
</table>

<table border="1">
    <tr>
        <th>Date</th>
        <th>URL</th>
        <th>Event</th>
        <th>Bet type</th>
        <th>Bet amount</th>
        <th>Coefficient</th>
        <th>Result</th>
        <th>Comment</th>
    </tr>
    <c:set var="stakeList" scope="session" value='<%= request.getAttribute("data")%>'/>
    <c:if test="${not empty stakeList}">
        <c:forEach var="stake" items="${stakeList}">
            <tr>
                <td width="150px">${stake.date}</td>
                <td width="150px">${stake.url}</td>
                <td width="150px">${stake.event}</td>
                <td width="150px">${stake.betType}</td>
                <td width="150px">${stake.betAmount}</td>
                <td width="150px">${stake.coefficient}</td>
                <td width="150px">${stake.result}</td>
                <td width="150px">${stake.comment}</td>
            </tr>
        </c:forEach>
    </c:if>
</table>
<c:set var="message" scope="session" value='<%= request.getAttribute("message") %>'/>
<c:if test="${message != null}">
    <h4 style="color:red"><c:out value="${message}"/></h4>
</c:if>
</body>
</html>
