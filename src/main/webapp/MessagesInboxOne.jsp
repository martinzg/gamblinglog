<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Messages Inbox</title>
</head>
<body>
	<%@include file="Menu.jsp" %>
    <h1>Messages:</h1>

    <%@include file="MessagesMenu.jsp" %>

    <table border="1" width="602px">
        <c:set var="messageOne" scope="session" value='<%= request.getAttribute("messageOne") %>'/>
        <c:if test="${messageOne != null}">
        <tr>
            <th align="left">Message from: ${messageOne.userFrom}</th>
        </tr>
            <tr>
                <td><p>${messageOne.message}</p></td>
            </tr>
        </c:if>
    </table>

    <c:set var="message" scope="session" value='<%= request.getAttribute("model") %>'/>
    <c:if test="${message != null}">
        <h4 style="color:red"><c:out value="${message}" /></h4>
    </c:if>

</body>
</html>
