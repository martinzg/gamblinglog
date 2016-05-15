<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Forgot Password</title>
</head>
<body>

    <h1>Forgot password:</h1>
    <form method="post">
        <h4>To reset your password enter your email and press Reset.</h4>
        Email:<br>
        <input type="email" name="email" required>
        <br><br>
        <input type="submit" name="submit" value="Reset">
    </form>
    <c:set var="message" scope="session" value='<%= request.getAttribute("model") %>'/>
    <c:if test="${message != null}">
        <h4 style="color:red"><c:out value="${message}" /></h4>
    </c:if>
    <a href="/login">Back to Login</a>

</body>
</html>
