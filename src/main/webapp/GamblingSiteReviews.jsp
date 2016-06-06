<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gambling Site Reviews</title>
</head>
<body>
    <%@include file="Menu.jsp" %>
    <br>
    <form method="post">
        <input type="submit" name="back" value="Back">
    </form>
    <h1>Gambling Site ${model.site.name} </h1>
     <h2>Site URL:</h2>
    <h3>${model.site.URL}</h2>
    <h2>Site Description:</h2>
    <h3>${model.site.description}</h2>
    <br>
      <table border="1">
        <c:set var="gamblingSiteReviews" scope="session" value='<%= request.getAttribute("data") %>'/>
        <c:if test="${not empty model.gamblingSiteReviews}">
            <c:forEach var="review" items="${model.gamblingSiteReviews}">
                <tr>
                    <td width="150px">${review.reviewTitle}</td>
                    <td width="150px">${review.reviewText}</td>
                    <td width="150px">${review.reviewRating}</td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
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
    </form>
    <c:if test="${model.message != null}">
        <h4 style="color:red"><c:out value="${model.message}" /></h4>
    </c:if>

</body>
</html>
