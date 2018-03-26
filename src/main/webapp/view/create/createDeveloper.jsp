<%--
  Created by IntelliJ IDEA.
  User: я
  Date: 22.03.2018
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateDeveloper</title>
    <link href="../../css/styles.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#acffb5">

<h2 class=" text-center">Add new Developer</h2>
<form action="/createDev" method="POST">
    <h3>Developer first name</h3>
    <input type="hidden" name="developerId">
    <input type="text" name="firstName" placeholder="Developer first name"/>
    <div class="col-sm-5">
        <h3>Developer secondary name</h3>
        <input type="text" name="secondaryName" placeholder="Developer secondary name"/>
    </div>
    <div class="col-sm-5">
        <h3>Developer age</h3>
        <input type="text" name="age" placeholder="Developer age"/>
    </div>
    <div class="col-sm-5">
        <h3>Developer gender</h3>
        <input type="text" name="gender" placeholder="Developer gender"/>
    </div>
    <div class="col-sm-5">
        <h3>Developer salary</h3>
        <input type="text" name="salary" placeholder="Developer salary"/>
    </div>
    <div>
        <input class="button" type="submit" value="Add developer"/>
    </div>

    <div class="block_bottom">
        <a class="buttonBack" href="/view/create/createMenu.html"><span>Back</span></a>
    </div>

</form>
</body>
</html>
