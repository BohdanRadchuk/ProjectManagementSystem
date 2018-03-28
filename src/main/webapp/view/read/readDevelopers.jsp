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
    <title>Developers</title>
    <c:choose>
        <c:when test="${param.method eq 2}">
            <c:set var="bodyColor" scope="page">
                #E076FF
            </c:set>
            <c:set var="thClass" scope="page">
                th2
            </c:set>
            <c:set var="postHref" scope="page">
                /updateDeveloper?developerId=
            </c:set>
            <c:set var="button" scope="page">
                button3
            </c:set>
            <c:set var="buttonValue" scope="page">
                update developer
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
                /deleteDeveloper?developerId=
            </c:set>
            <c:set var="button" scope="page">
                button4
            </c:set>
            <c:set var="buttonValue" scope="page">
                delete developer
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
<h1 class="text-center">Developers</h1>

<table border="1px">
    <tr class="${thClass}">
        <th>Id</th>
        <th>FirstName</th>
        <th>SecondaryName</th>
        <th>Age</th>
        <th>Gender</th>
        <th>Salary</th>
        <c:if test="${param.method gt 0}">
            <th>Action</th>
        </c:if>

    </tr>
    <c:forEach items="${developers}" var="element">

        <tr>
            <td>${element.id}</td>
            <td>${element.firstName}</td>
            <td>${element.secondaryName}</td>
            <td>${element.age}</td>
            <td>${element.gender}</td>
            <td>${element.salary}</td>
            <c:if test="${param.method gt 0}">
                <td>
                    <form action="${postHref}${element.id}" method="POST">
                        <div>
                            <input class="${button}" type="submit" value="${buttonValue}"/>
                        </div>
                    </form>
                </td>
            </c:if>

        </tr>
    </c:forEach>
</table>

<div class="block_bottom">
    <a class="buttonBack" href="${buttonBackHref}"><span>Back</span></a>
</div>

</body>
</html>
