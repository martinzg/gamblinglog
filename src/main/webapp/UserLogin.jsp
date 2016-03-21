<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <h4 style="color:red">${message}</h4>
    <table>
        <tr>
            <td><a href="/java2/registration">Register</a></td>
            <td><a href="/java2/forgotpassword">Forgot Password?</a></td>
        </tr>
    </table>

</body>
</html>
