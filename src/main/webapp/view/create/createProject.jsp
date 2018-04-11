<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../css/styles.css" rel="stylesheet" type="text/css">
    <title>CreateProject</title>
</head>
<body bgcolor="#acffb5">

<h2 class="text-center">Add new Project</h2>
<form action="/createProject" method="POST">
    <h3>Project name</h3>
    <input type="hidden" name="projectId">
    <input type="text" name="projectName" required placeholder="Project name"/>
    <div class="col-sm-5">
        <h3>Project description</h3>
        <input type="text" name="projectDescr" required placeholder="Project description"/>
    </div>
    <div class="col-sm-5">
        <h3>Project price</h3>
        <input type="text" name="projectPrice" required placeholder="Project price"/>
    </div>

    <div>
        <input class="button" type="submit" value="Add project"/>
    </div>
</form>
<div class="block_bottom">
    <a class="buttonBack" href="/view/create/createMenu.html"><span>Back</span></a>
</div>
</body>
</html>
