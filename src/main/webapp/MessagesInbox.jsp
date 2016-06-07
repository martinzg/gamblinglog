<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <META HTTP-EQUIV="refresh" CONTENT="45">
    <title>Messages Inbox</title>
</head>
<body>
	<%@include file="Menu.jsp" %>
    <h1>Messages:</h1>

    <%@include file="MessagesMenu.jsp" %>

    <button onclick="getSelected()">Delete</button>

    <table border="1">
        <tr>
            <th><input type="checkbox" id="select-all" onClick="selectAll(this)"></th>
            <th>Time</th>
            <th>From</th>
            <th>Message</th>
        </tr>
        <c:set var="messageList" scope="session" value='<%= request.getAttribute("messageList") %>'/>
        <c:if test="${not empty messageList}">
            <c:forEach var="userMessage" items="${messageList}">

                <c:set var="backColor" scope="session" value="white"/>
                <c:if test="${not userMessage.readState}">
                    <c:set var="backColor" scope="session" value="90ee90"/>
                </c:if>

                <tr bgcolor="${backColor}">
                    <td><input type="checkbox" name="select-one" value="${userMessage.id}" onClick="selectOne()"></td>
                    <td width="200px" onclick="return messageLink('/messages/inbox/${userMessage.id}')" style="cursor: pointer;">${userMessage.dateTime}</td>
                    <td width="200px" onclick="return messageLink('/messages/inbox/${userMessage.id}')" style="cursor: pointer;">${userMessage.userFrom}</td>
                    <td width="200px" onclick="return messageLink('/messages/inbox/${userMessage.id}')" style="cursor: pointer;"><div style="width:100px; white-space:nowrap; overflow:hidden; text-overflow:ellipsis">${userMessage.message}</div></td>
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

        function selectAll(source) {
            checkboxes = document.getElementsByName('select-one');
            for (var i=0; i<checkboxes.length; i++){
                checkboxes[i].checked = source.checked;
            }
        }

        function selectOne(){
            checkboxes = document.getElementsByName("select-one");
            var count = 0;
            for (var i=0; i<checkboxes.length; i++){
                if (!checkboxes[i].checked){
                    document.getElementById("select-all").checked = false;
                    return;
                }
                else {
                    count++;
                    if (count == checkboxes.length){
                        document.getElementById("select-all").checked = true;
                    }
                }

            }
        }

        function getSelected(){
            checkboxes = document.getElementsByName("select-one");
            var ids = [];
            var count = 0;
            for (var i=0; i<checkboxes.length; i++){
                if (checkboxes[i].checked){
                    ids[count] = checkboxes[i].getAttribute("value");
                    count++;
                }
            }
            post("/messages/inbox", {msgIds: ids});
        }

        function post(path, params, method) {
            method = method || "post";
            var form = document.createElement("form");
            form.setAttribute("method", method);
            form.setAttribute("action", path);
            for (var key in params) {
                if (params.hasOwnProperty(key)) {
                    var hiddenField = document.createElement("input");
                    hiddenField.setAttribute("type", "hidden");
                    hiddenField.setAttribute("name", key);
                    hiddenField.setAttribute("value", params[key]);
                    form.appendChild(hiddenField);
                }
            }
            document.body.appendChild(form);
            form.submit();
        }

    </script>

</body>
</html>
