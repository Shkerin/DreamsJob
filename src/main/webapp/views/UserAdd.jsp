<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 12.04.16
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dreams Job</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/index.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/styleform.css">
</head>
<body>
<h1>Add user:</h1>
<h2>Fill out the form below and click "add user" to add</h2>

<form action="<%=request.getContextPath()%>/useradd" method="post">
    <div class="tableRow">
        <p> Id </p>
        <p><input type="text" name="id" value="" placeholder="1"></p>
    </div>
    <div class="tableRow">
        <p> Name </p>
        <p><input type="text" name="name" value="" placeholder="Ivan"></p>
    </div>
    <div class="tableRow">
        <p> Growth </p>
        <p><input type="number" name="growth" min="1.0" max="200.0" placeholder="176.5"></p>
    </div>
    <div class="tableRow">
        <p> Date </p>
        <p><input type="date" name="birthDay" value="date"></p>
    </div>
    <div class="tableRow">
        <p> Children </p>
        <p><textarea name="children"></textarea></p>
    </div>
    <div class="tableRow">
        <p></p>
        <p><input type="submit" value="Add user"></p>
    </div>
</form>
</body>
</html>
