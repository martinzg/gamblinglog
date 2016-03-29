<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Redirect</title>
</head>
<body>

        <% response.sendRedirect(request.getAttribute("data").toString());%>

</body>
</html>
