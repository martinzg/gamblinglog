<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>GamblingSiteReview</title>
</head>
<body>
    <%@include file="Menu.jsp" %>
    <h1>Add review:</h1>
    <form method="post">
        Title:<br>
        <input type="text" name="reviewTitle" required>
        <br>
        Review:<br>
        <input type="text" name="reviewText" required>
        <br>
        Your rating:<br>
        <input type="text" name="reviewRating" required>
        <br><br>
        <input type="submit" name="submit" value="Add">
        Your rating:<br>
        <br>
        <input type="radio" name="rating-${loopCounter2.index}" value="1" class="rating-star${loopCounter2.index}"/>
                          <input type="radio" name="rating-${loopCounter2.index}" value="2" class="rating-star${loopCounter2.index}"/>
                          <input type="radio" name="rating-${loopCounter2.index}" value="3" class="rating-star${loopCounter2.index}"/>
                          <input type="radio" name="rating-${loopCounter2.index}" value="4" class="rating-star${loopCounter2.index}"/>
                          <input type="radio" name="rating-${loopCounter2.index}" value="5" class="rating-star${loopCounter2.index}"/>
    </form>
    <br>
    <form method="post">
        <input type="submit" name="back" value="Back">
    </form>
    <c:set var="message" scope="session" value='<%= request.getParameter("param") %>'/>
    <c:if test="${message != null}">
        <h4 style="color:red"><c:out value="${message}" /></h4>
    </c:if>

</body>
</html>
