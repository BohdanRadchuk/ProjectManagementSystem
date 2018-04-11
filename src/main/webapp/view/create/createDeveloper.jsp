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
    <input type="text" name="firstName" required placeholder="Developer first name"/>
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
        <select name="gender" required>
            <option value="male">Male</option>
            <option value="female">Female</option>
        </select>

    </div>
    <div class="col-sm-5">
        <h3>Developer salary</h3>
        <input type="text" name="salary" required placeholder="Developer salary"/>
    </div>
    <div>
        <input class="button" type="submit" value="Add developer"/>
    </div>
</form>
<div class="block_bottom">
    <a class="buttonBack" href="/view/create/createMenu.html"><span>Back</span></a>
</div>
</body>
</html>
