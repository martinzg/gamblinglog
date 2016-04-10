<%@ page import="lv.javaguru.java2.domain.LandBasedCasino" %>
<%@ page import="lv.javaguru.java2.resources.OfflineGamblingEventData" %>
<%@ page import="lv.javaguru.java2.domain.GamblingType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add land based casino gambling event!</title>
</head>
<body>

    <h1>Add event:</h1>
    <form method="post">
        Date:<br>
        <input type="date" name="date" title="date" required><br>
        Casino<br>
        <select title="Landbased casinos">
            <% OfflineGamblingEventData data = (OfflineGamblingEventData) request.getAttribute("data"); %>
            <% for (LandBasedCasino c : data.getLandBasedCasinoList()) { %>
            <%= "<option value=\"" + c.getId() + "\" >" + c.getName() + " </option>" %>
            <% } %>
        </select><br>
        Gambling type:<br>
            <% for (GamblingType c : data.getGamblingTypeList()) { %>
            <%= "<input type=\"checkbox\" value=\"" + c.getId() + "\">" + c.getName() + " <br>"%>
            <% } %>
        Comment:<br>
        <input type="text" maxlength="256" name="comment" title="comment"><br>
        <input type="submit" name="submit" value="Submit" title="Submit">
    </form>

</body>
</html>
