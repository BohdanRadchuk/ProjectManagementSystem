<%--
  Created by IntelliJ IDEA.
  User: я
  Date: 22.03.2018
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Middle</title>
</head>
<body>
<h1>Middle Developers</h1>
<c:forEach items="${middleDevelopers}" var="element">
    <tr>
        <td>${element.firstName}</td>
        <td>${element.lastName}</td>
    </tr>
</c:forEach>

</body>
</html>
