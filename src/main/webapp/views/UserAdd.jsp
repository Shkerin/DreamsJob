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

<p>
    <%
        if (session.getAttribute("login") != null) {
    %>Welcome back, <%=session.getAttribute("login")%>.
    <a href="<%=request.getContextPath()%>/logout">logout</a><%
    } else {
    %>Unregistered user!<%
        }
%>
</p>

<h1>Add user:</h1>
<h2>Fill out the form below and click "add user" to add</h2>

<form name="myForm" action="<%=request.getContextPath()%>/useradd"
      onsubmit="validateForm()" method="post">
    <div class="tableRow">
        <p> Id </p>
        <p><input type="text" name="id" value="" placeholder="00001"></p>
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
        <p> Email </p>
        <p><input type="email" name="email" value="" placeholder="user@mail.ru"></p>
    </div>
    <div class="tableRow">
        <p> Children </p>
        <p><textarea name="children"></textarea></p>
    </div>
    <div class="tableRow">
        <p></p>
        <p>
            <input type="submit" name="fname" value="Save change">
            <input type="submit" value="Back"
                   formaction="<%=request.getContextPath()%>/views/UserView.jsp">
        </p>
    </div>
    <div class="tableRow">
        <p></p>
        <div id="message">
        </div>
    </div>
</form>

<script>
    function validateForm() {
        var divMessage = document.getElementById("message");
        divMessage.innerHTML = "TEST!";
    }
</script>

<%--<script src="<%=request.getContextPath()%>/scripts/handlerButton.js"></script>--%>

</body>
</html>
