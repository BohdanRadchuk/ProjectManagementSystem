<%--
  Created by IntelliJ IDEA.
  User: chemnen
  Date: 24.03.2018
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="../../css/styles.css" rel="stylesheet" type="text/css">
    <title>AllProjects</title>
</head>
<body bgcolor="#94d9e7">
<h1 class="text-center">All Projects Info</h1>

<table border="1px">
    <tr class="th1">

        <th>ProjectName</th>
        <th>Cost</th>
        <th>Amount of developers</th>
    </tr>

    <c:forEach var="element" items="${projects}">
        <tr>
            <td>
                <c:out value="${element.projectName}"/>
            </td>
            <td>
                <c:out value="${element.cost}"/>
            </td>
            <td>
                <c:out value="${element.developersCount}"/>
            </td>
        </tr>
    </c:forEach>

</table>
<div class="block_bottom">
    <a class="buttonBack" href="/view/read/readMenu.html"><span>Back</span></a>
</div>

</body>
</html>
