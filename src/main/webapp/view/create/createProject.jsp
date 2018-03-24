<%--
  Created by IntelliJ IDEA.
  User: я
  Date: 21.03.2018
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../css/styles.css" rel="stylesheet" type="text/css">
    <title>CreateProject</title>
</head>
<body bgcolor="#acffb5">

<h2 class="text-center">Add new Project</h2>
<form action="/createProject" method="POST" class="container-unlim">
    <h3>Project name</h3>
    <input type="hidden" name="projectId">
    <input type="text" name="projectName" placeholder="Project name"/>
    <div class="col-sm-5">
        <h3>Project description</h3>
        <input type="text" name="projectDescr" placeholder="Project description"/>
    </div>
    <div class="col-sm-5">
        <h3>Project price</h3>
        <input type="text" name="projectPrice" placeholder="Project price"/>
    </div>

    <div>
        <input class="button button1" type="submit" value="Add project"/>
    </div>
    <div>
        <a class="buttonSmall button4" href="/view/create/createMenu.html">back</a>
    </div>
</form>
</body>
</html>
