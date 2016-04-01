<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Change Password</title>
</head>
<body>

    <h1>Change password:</h1>
    <form method="post">
        New Password:<br>
        <input type="password" name="password" required>
        <br>
        Confirm New Password:<br>
        <input type="password" name="confirm password" required>
        <br><br>
        <input type="submit" name="submit" value="Change Password">
    </form>
    <c:set var="message" scope="session" value='<%= request.getAttribute("message") %>'/>
    <c:if test="${message != null}">
        <h4 style="color:red"><c:out value="${message}" /></h4>
    </c:if>

</body>
</html>
