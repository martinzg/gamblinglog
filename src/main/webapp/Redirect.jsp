<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Redirect</title>
</head>
<body>

        <% response.sendRedirect(request.getAttribute("model").toString());%>

</body>
</html>
