<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gambling Sites</title>
</head>
<body>

    <h1>Gambling Sites:</h1>
    <form method="post">
        <input type="submit" name="show sites" value="Gambling Sites">
    </form>

    <table>
        <tr>
            <td>
                <form method="post">
                    <input type="submit" name="add site" value="Add New Site">
                </form>
            </td>
            <td>
                <form method="post">
                    <input type="submit" name="delete site" value="Delete Site">
                </form>
            </td>
        </tr>
    </table>

    <table border="1">
        <tr>
            <th>Site Name</th>
            <th>Site URL</th>
            <th>Site Description</th>
        </tr>
        <c:set var="gamblingSiteList" scope="session" value='<%= request.getAttribute("data") %>'/>
        <c:if test="${not empty gamblingSiteList}">
            <c:forEach var="site" varStatus="status" items="${gamblingSiteList}">
                <tr>
                    <td width="150px"><c:out value="${site.name}"/></td>
                    <td width="150px"><c:out value="${site.URL}"/></td>
                    <td width="150px"><c:out value="${site.description}"/></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>

    <c:set var="message" scope="session" value='<%= request.getAttribute("message") %>'/>
    <c:if test="${message != null}">
        <h4 style="color:red"><c:out value="${message}" /></h4>
    </c:if>

</body>
</html>
