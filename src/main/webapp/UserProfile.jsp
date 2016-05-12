<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
	<%@include file="Menu.jsp" %>
    <h1>User Profile:</h1>


    <div style="width:128px;height:128px;overflow:hidden;border:1px solid black;align-content:center" >
        <img src="/images/avatar.jpg" alt="HTML5 Icon" style="max-width:100%;height:128px">
    </div>
    <br>
    <%@include file="UploadFile.jsp" %>


    <table>
        <tr>
            <td valign="top">
                <form method="post">
                    <input type="submit" name="edit" value="Edit Profile">
                </form>
            </td>
            <td valign="top">
                <button onclick="deleteUserFunction()">Delete User</button>
            </td>
        </tr>
    </table>

    <%@include file="EditUserProfile.jsp" %>

    <script>
        function deleteUserFunction() {
            if (confirm("Are you sure you want to delete your Profile?") == true) {
                location.href = "/deleteuser";
            }
        }
    </script>

    <c:set var="message" scope="session" value='<%= request.getAttribute("model") %>'/>
    <c:if test="${message != null}">
        <h4 style="color:red"><c:out value="${message}" /></h4>
    </c:if>

</body>
</html>
