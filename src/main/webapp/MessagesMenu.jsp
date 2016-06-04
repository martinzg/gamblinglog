<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="count" scope="session" value='<%= request.getAttribute("count") %>'/>

<table>
    <tr>
        <td>
            <a href="/messages/inbox">Inbox (${count})</a> |
            <a href="/messages/newmessage">New Message</a>
        </td>
    </tr>
</table>
<br>

