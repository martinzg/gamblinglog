<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

    <link rel="icon" href="data:;base64,=">
    <h1>User login:</h1>
    <form method="post" action="/j_security_check">
        Email:<br>
        <input type="email" name="j_username" required>
        <br>
        Password:<br>
        <input type="password" name="j_password" required>
        <br><br>
        <input type="submit" name="submit" value="Log In">
    </form>
    <h4 style="color:red">Login failure! Invalid Email or Password!</h4>
    <table>
        <tr>
            <td><a href="/registration">Register</a></td>
            <td><a href="/forgotpassword">Forgot Password?</a></td>
        </tr>
    </table>

</body>
</html>
