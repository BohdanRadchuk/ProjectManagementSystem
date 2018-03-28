<%--
  Created by IntelliJ IDEA.
  User: я
  Date: 21.03.2018
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="../../css/styles.css" rel="stylesheet" type="text/css">
    <title>UpdateProject</title>
</head>
<body bgcolor="#E394FF">
<h2 class="text-center">Update Project</h2>
<form action="/updateProject?projectId=-1" method="POST">

    <input type="hidden" name="prId" value="${idProject}">
    <div class="col-sm-5">
        <h3>Project name</h3>
        <input type="text" name="projectName" required placeholder="Project name"/>
    </div>
    <div class="col-sm-5">
        <h3>Project description</h3>
        <input type="text" name="projectDescr" required placeholder="Project description"/>
    </div>
    <div class="col-sm-5">
        <h3>Project price</h3>
        <input type="text" name="projectPrice" required placeholder="Project price"/>
    </div>

    <div>
        <input class="button" type="submit" value="Update project"/>
    </div>
</form>
<div class="block_bottom">
    <a class="buttonBack" href="/updateProject?method=2"><span>Back</span></a>
</div>
</body>
</html>
