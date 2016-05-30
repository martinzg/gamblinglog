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

    <table border="1">
        <tr>
            <th>Time</th>
            <th>From</th>
            <th>Message</th>
        </tr>
        <c:set var="messageList" scope="session" value='<%= request.getAttribute("messageList") %>'/>
        <c:if test="${not empty messageList}">
            <c:forEach var="userMessage" items="${messageList}">
                <tr onclick="return messageLink('/messages/inbox/${userMessage.id}')" style="cursor: pointer;">
                    <td width="200px">${userMessage.dateTime}</td>
                    <td width="200px">${userMessage.userFrom}</td>
                    <td width="200px"><div style="width:100px; white-space:nowrap; overflow:hidden; text-overflow:ellipsis">${userMessage.message}</div></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>

    <c:set var="message" scope="session" value='<%= request.getAttribute("model") %>'/>
    <c:if test="${message != null}">
        <h4 style="color:red"><c:out value="${message}" /></h4>
    </c:if>

    <script language="javascript" type="text/javascript">
        function messageLink(url) {
            location.href = url;
        }
    </script>

</body>
</html>
