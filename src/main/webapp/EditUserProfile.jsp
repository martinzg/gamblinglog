<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="user" scope="session" value='<%= request.getAttribute("user") %>'/>
<c:if test="${user != null}">
    <table>
        <tr>
            <td>
                <form method="post">
                    First name:<br>
                    <input type="text" name="firstname" title="First name" value="${user.firstName}" required style="width:170px">
                    <br>
                    Last name:<br>
                    <input type="text" name="lastname" title="Last name" value="${user.lastName}" required style="width:170px">
                    <br>
                    Email:<br>
                    <input type="email" name="email" title="Email" value="${user.email}" readonly style="color:#808080;width:170px">
                    <br>
                    Password:<br>
                    <input type="password" name="password" title="Password" value="${user.password}" required style="width:170px">
                    <br><br>
                    <table>
                        <tr>
                            <td>
                                <input type="submit" name="update" value="Save" style="width:80px">
                            </td>
                            <td>
                                <input type="submit" name="cancel" value="Cancel" style="width:80px">
                            </td>
                        </tr>
                    </table>
                </form>
            </td>
        </tr>
    </table>
</c:if>
