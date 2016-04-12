<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
	<%@include file="Menu.jsp" %>
    <h1>User Profile:</h1>

    <c:set var="id" scope="session" value='<%= request.getAttribute("data") %>'/>
    <h4 style="color:red"><c:out value="${id}" /></h4>

</body>
</html>
