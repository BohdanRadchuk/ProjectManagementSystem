<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="../../css/styles.css" rel="stylesheet" type="text/css">
    <title>Projects</title>
    <c:choose>
        <c:when test="${param.method eq 1}">
            <c:set var="bodyColor" scope="page">
                #94d9e7
            </c:set>
            <c:set var="thClass" scope="page">
                th1
            </c:set>
            <c:set var="postHref" scope="page">
                /getOneProjectDevelopers?projectId=
            </c:set>
            <c:set var="button" scope="page">
                button2
            </c:set>
            <c:set var="buttonValue" scope="page">
                get developers of this project
            </c:set>
            <c:set var="buttonBackHref" scope="page">
                /view/read/readMenu.html
            </c:set>
        </c:when>

        <c:when test="${param.method eq 2}">
            <c:set var="bodyColor" scope="page">
                #E076FF
            </c:set>
            <c:set var="thClass" scope="page">
                th2
            </c:set>
            <c:set var="postHref" scope="page">
                /updateProject?projectId=
            </c:set>
            <c:set var="button" scope="page">
                button3
            </c:set>
            <c:set var="buttonValue" scope="page">
                update project
            </c:set>
            <c:set var="buttonBackHref" scope="page">
                /view/update/updateMenu.html
            </c:set>
        </c:when>

        <c:when test="${param.method eq 3}">
            <c:set var="bodyColor" scope="page">
                #FFBBA4
            </c:set>
            <c:set var="thClass" scope="page">
                th3
            </c:set>
            <c:set var="postHref" scope="page">
                /deleteProject?projectId=
            </c:set>
            <c:set var="button" scope="page">
                button4
            </c:set>
            <c:set var="buttonValue" scope="page">
                delete project
            </c:set>
            <c:set var="buttonBackHref" scope="page">
                /view/delete/deleteMenu.html
            </c:set>
        </c:when>

        <c:otherwise>
            <c:set var="bodyColor" scope="page">
                #94d9e7
            </c:set>

            <c:set var="thClass" scope="page">
                th1
            </c:set>
            <c:set var="buttonBackHref" scope="page">
                /view/read/readMenu.html
            </c:set>
        </c:otherwise>

    </c:choose>
</head>
<body bgcolor="${bodyColor}">
<h1 class="text-center">Projects</h1>

<table border="1px">
    <tr class="${thClass}">
        <th>Id</th>
        <th>ProjectName</th>
        <th>Description</th>
        <th>Cost</th>
        <c:if test="${param.method gt 0}">
            <th>Action</th>
        </c:if>


    </tr>
    <c:forEach items="${allProjects}" var="element">

    <tr>
        <td>${element.id_project}</td>
        <td>${element.projectName}</td>
        <td>${element.description}</td>
        <td>${element.cost}</td>
        <c:if test="${param.method gt 0}">
        <td>
            <form action="${postHref}${element.id_project}" method="POST">
                <div>
                    <input class="${button}" type="submit" value="${buttonValue}"/>
                </div>
            </form>
        </td>
        </c:if>
        </c:forEach>
</table>
<div class="block_bottom">
    <a class="buttonBack" href="${buttonBackHref}"><span>Back</span></a>
</div>

</body>
</html>
