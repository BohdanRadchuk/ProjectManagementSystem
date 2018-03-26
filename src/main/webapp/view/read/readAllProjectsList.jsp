<%--
  Created by IntelliJ IDEA.
  User: я
  Date: 22.03.2018
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="../../css/styles.css" rel="stylesheet" type="text/css">
    <title>OneProjectDevelopers</title>
</head>
<body bgcolor="#94d9e7">
<h1 class="text-center">All Projects</h1>

<table border="1px">
    <tr class="th1">
        <th>Id</th>
        <th>ProjectName</th>
        <th>Description</th>
        <th>Cost</th>
        <th>Action</th>

    </tr>
    <c:forEach items="${allProjects}" var="element">

        <tr>
            <td>${element.id_project}</td>
            <td>${element.projectName}</td>
            <td>${element.description}</td>
            <td>${element.cost}</td>
            <td>
            <form action="/getOneProjectDevelopers?projectId=${element.id_project}" method="POST">
                <div >
                    <input class="button2" type="submit" value="get developers of this project"/>
                </div>
            </form>

        </tr>
    </c:forEach>
</table>
<div class="block_bottom">
    <a class="buttonBack" href="/view/read/readMenu.html"><span>Back</span></a>
</div>

</body>
</html>
