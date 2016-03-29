<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>

    <h1>User login:</h1>
    <form method="post">
        Email:<br>
        <input type="email" name="email" required>
        <br>
        Password:<br>
        <input type="password" name="password" required>
        <br><br>
        <input type="submit" name="submit" value="Log In">
    </form>
    <c:set var="message" scope="session" value='<%= request.getAttribute("message") %>'/>
    <c:if test="${message != null}">
        <h4 style="color:red"><c:out value="${message}" /></h4>
    </c:if>
    <table>
        <tr>
            <td><a href="/java2/registration">Register</a></td>
            <td><a href="/java2/forgotpassword">Forgot Password?</a></td>
        </tr>
    </table>

</body>
</html>
