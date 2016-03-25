<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>GamblingSiteAdd</title>
</head>
<body>

    <h1>Add gambling site:</h1>
    <form method="post">
        Site name:<br>
        <input type="text" name="siteName" required>
        <br>
        Site URL:<br>
        <input type="text" name="siteURL" required>
        <br>
        Site Description:<br>
        <input type="text" name="siteDescription" required>
        <br><br>
        <input type="submit" name="submit" value="Add">
    </form>
    <c:set var="message" scope="session" value='<%= request.getParameter("param") %>'/>
    <c:if test="${message != null}">
        <h4 style="color:red"><c:out value="${message}" /></h4>
    </c:if>

</body>
</html>
