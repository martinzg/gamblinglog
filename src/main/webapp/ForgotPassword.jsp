<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Reset Password page</title>
</head>
<body>

    <h1>Reset password:</h1>
    <form method="post">
        <h4>To reset your password enter your email and press Reset.</h4>
        Email:<br>
        <input type="email" name="email" required>
        <br><br>
        <input type="submit" name="submit" value="Reset">
    </form>
    <c:set var="message" scope="session" value='<%= request.getParameter("param") %>'/>
    <c:if test="${message != null}">
        <h4 style="color:red"><c:out value="${message}" /></h4>
    </c:if>

</body>
</html>
