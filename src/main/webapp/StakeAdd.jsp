<%--
  Created by IntelliJ IDEA.
  User: tyoma17
  Date: 03.04.2016
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>StakeAdd</title>
</head>
<body>
	<%@include file="Menu.jsp" %>
  <h1> Add stake:</h1>
  <form method="post">
    Date:<br>
    <input type="date" name="date" required>
    <br>
    Site URL:<br>
    <input type="text" name="siteURL" required>
    <br>
    Event:<br>
    <input type="text" name="event" required>
    <br>
    Bet Type:<br>
    <input type="text" name="betType" required>
    <br>
    Bet amount:<br>
    <input type="number" name="betAmount" required>
    <br>
    Coefficient:<br>
    <input type="number" name="coefficient" required>
    <br>
    Result:<br>
    <input type="text" name="result" required>
    <br>
    Comment:<br>
    <input type="text" name="comment">
    <br><br>
    <input type="submit" name="submit" value="Submit!">
  </form>
  <c:set var="message" scope="session" value='<%= request.getParameter("param") %>'/>
  <c:if test="${message != null}">
    <h4 style="color:red"><c:out value="${message}" /></h4>
  </c:if>
</body>
</html>
