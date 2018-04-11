<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../css/styles.css" rel="stylesheet" type="text/css">
    <title>CreateCustomer</title>
</head>

<body bgcolor="#acffb5">

<h2 class="text-center">Add new Customer</h2>
<form action="/createCust" method="POST">

    <div class="col-sm-5">
        <h3>Customer name</h3>
        <input type="hidden" name="customerId">
        <input type="text" name="custName" required placeholder="Customer name"/>
    </div>
    <div class="col-sm-5">
        <h3>Customer state or private</h3>
        <select name="stOrPr">
            <option value="1">State</option>
            <option value="0">Private</option>
        </select>
    </div>
    <div>
        <input class="button" type="submit" value="Add customer"/>
    </div>
</form>
<div class="block_bottom">
    <a class="buttonBack" href="/view/create/createMenu.html"><span>Back</span></a>
</div>
</body>
</html>
