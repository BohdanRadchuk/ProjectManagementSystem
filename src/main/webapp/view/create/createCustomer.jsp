<%--
  Created by IntelliJ IDEA.
  User: chemnen
  Date: 23.03.2018
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../css/styles.css" rel="stylesheet" type="text/css">
    <title>CreateCustomer</title>
</head>

<body bgcolor="#acffb5">

<h2 class=" text-center">Add new Customer</h2>
<form action="/createCust" method="POST" class="container-unlim">

    <div class="col-sm-5">
        <h3>Customer name</h3>
        <input type="hidden" name="customerId">
        <input type="text" name="custName" placeholder="Customer name"/>
    </div>
    <div class="col-sm-5">
        <h3>Customer state or private</h3>
        <input type="text" name="stOrPr" placeholder="State or private"/>
    </div>
    <div>
        <input class="button" type="submit" value="Add developer"/>
    </div>
    <div>
        <a class="buttonSmall button4" href="/view/create/createMenu.html">back</a>
    </div>


</form>


</body>
</html>
