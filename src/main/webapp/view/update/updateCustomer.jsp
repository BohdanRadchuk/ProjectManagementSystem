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
    <title>UpdateCustomer</title>
</head>
<body bgcolor="#E394FF">
<h2 class="text-center">Update customer</h2>
<form action="/updateCustomer?customerId=-1" method="POST">
    <input type="hidden" name="custId" value="${idCustomer}">
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
        <input class="button" type="submit" value="Update customer"/>
    </div>

</form>
<div class="block_bottom">
    <a class="buttonBack" href="/updateProject?method=2"><span>Back</span></a>
</div>
</body>
</html>
