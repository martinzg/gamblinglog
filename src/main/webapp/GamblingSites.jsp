<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gambling Sites</title>
    <script>
    	function deleteSelectedSites() {
    		var selectAllChecked = document.getElementById("select-all").checked
    		var selectedSiteIds = ""
    		var selectOnes = document.getElementsByClassName("select-one")
    		for (var i = 0; i < selectOnes.length; i++) {
    			if (selectOnes[i].checked || selectAllChecked) {
    				if (selectedSiteIds.length > 0) {
    					selectedSiteIds += ","
    				}
    				selectedSiteIds += selectOnes[i].value
    			}
    		}
    		post("/gamblingsites", {siteIds: selectedSiteIds})
    	}
    	
    	function post(path, params, method) {
    	    method = method || "post"; // Set method to post by default if not specified.

    	    // The rest of this code assumes you are not using a library.
    	    // It can be made less wordy if you use one.
    	    var form = document.createElement("form");
    	    form.setAttribute("method", method);
    	    form.setAttribute("action", path);

    	    for(var key in params) {
    	        if(params.hasOwnProperty(key)) {
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
</head>
<body>
	<%@include file="Menu.jsp" %>
    <h1>Gambling Sites:</h1>
    <table>
        <tr>
            <td>
                <form method="post">
                    <input type="submit" name="add site" value="Add New Site">
                </form>
            </td>
            <td>
                <button onclick="deleteSelectedSites()">Delete</button>
            </td>
        </tr>
    </table>

    <table border="1">
        <tr>
        	<th><input type="checkbox" id="select-all"></th>
            <th>Site Name</th>
            <th>Site URL</th>
            <th>Site Description</th>
        </tr>
        <c:set var="gamblingSiteList" scope="session" value='<%= request.getAttribute("data") %>'/>
        <c:if test="${not empty gamblingSiteList}">
            <c:forEach var="site" items="${gamblingSiteList}">
                <tr>
                	<td><input type="checkbox" class="select-one" value="${site.id}"></td>
                    <td width="150px">${site.name}</td>
                    <td width="150px">${site.URL}</td>
                    <td width="150px">${site.description}</td>
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
