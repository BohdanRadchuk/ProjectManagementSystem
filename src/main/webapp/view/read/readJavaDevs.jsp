<%--
  Created by IntelliJ IDEA.
  User: я
  Date: 22.03.2018
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>JavaDevelopers</title>
</head>
<body>
<h1>Java Developers</h1>
<c:forEach items="${javadevs}" var="element">
    <tr>
        <td>${element.field1}</td>
        <td>${element.field2}</td>
    </tr>
</c:forEach>
</body>
</html>
