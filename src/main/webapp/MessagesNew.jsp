<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Messages New</title>
</head>
<body>
	<%@include file="Menu.jsp" %>
    <h1>Messages:</h1>

    <%@include file="MessagesMenu.jsp" %>

    <form method="post" id="msgform">
        Name:
        <input type="email" name="recipient" required>
        <input type="submit" name="send" value="Send" size="255">
    </form>
    <textarea rows="4" cols="50" name="comment" placeholder="Enter your message here..." form="msgform" required></textarea>

    <c:set var="message" scope="session" value='<%= request.getAttribute("model") %>'/>
    <c:if test="${message != null}">
        <h4 style="color:red"><c:out value="${message}" /></h4>
    </c:if>

</body>
</html>
