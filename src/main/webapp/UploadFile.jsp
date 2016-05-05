<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form action="userprofile" method="post" enctype="multipart/form-data">
    <input type="file" name="file" size="50" />
    <br>
    <input type="submit" name="upload" value="Upload" style="width:128px"/>
</form>