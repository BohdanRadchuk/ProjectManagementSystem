<%--
  Created by IntelliJ IDEA.
  User: я
  Date: 22.03.2018
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link href="../../css/styles.css" rel="stylesheet" type="text/css">
    <title>JavaDevelopers</title>
</head>
<body bgcolor="#94d9e7">
<h1 class="text-center">Developers</h1>

<table border="1px">
    <tr class="th1">
        <th>Id</th>
        <th>FirstName</th>
        <th>SecondaryName</th>
        <th>Age</th>
        <th>Gender</th>
        <th>Salary</th>
    </tr>
    <c:forEach items="${developers}" var="element">

        <tr>
            <td>${element.id}</td>
            <td>${element.firstName}</td>
            <td>${element.secondaryName}</td>
            <td>${element.age}</td>
            <td>${element.gender}</td>
            <td>${element.salary}</td>
        </tr>
    </c:forEach>
</table>

<div class="block_bottom">
    <a class="buttonBack" href="/view/read/readMenu.html"><span>Back</span></a>
</div>

</body>
</html>
