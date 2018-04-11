<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="../../css/styles.css" rel="stylesheet" type="text/css">
    <title>UpdateProject</title>
</head>
<body bgcolor="#E394FF">
<h2 class="text-center">Update Developer</h2>
<form action="/updateDeveloper?developerId=-1" method="POST">
    <input type="hidden" name="devId" value="${idDeveloper}">
    <div class="col-sm-5">
        <h3>Developer first name</h3>
        <input type="text" name="firstName" required placeholder="Developer first name"/>
    </div>
    <div class="col-sm-5">
        <h3>Developer last name</h3>
        <input type="text" name="secondaryName" required placeholder="Developer last name"/>
    </div>
    <div class="col-sm-5">
        <h3>Developer age</h3>
        <input type="text" name="age" required placeholder="Developer age"/>
    </div>
    <div class="col-sm-5">
        <h3>Developer gender</h3>
        <select name="gender">
            <option value="male">Male</option>
            <option value="female">Female</option>
        </select>
    </div>
    <div class="col-sm-5">
        <h3>Developer salary</h3>
        <input type="text" name="salary" required placeholder="Developer salary"/>
    </div>
    <div>
        <input class="button" type="submit" value="Update developer"/>
    </div>
</form>
<div class="block_bottom">
    <a class="buttonBack" href="/updateDeveloper?method=2"><span>Back</span></a>
</div>
</body>
</html>
