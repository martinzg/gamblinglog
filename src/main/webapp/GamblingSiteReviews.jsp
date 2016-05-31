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
    <c:set var="message" scope="session" value='<%= request.getParameter("param") %>'/>
    <c:if test="${message != null}">
        <h4 style="color:red"><c:out value="${message}" /></h4>
    </c:if>

</body>
</html>
