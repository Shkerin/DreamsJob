<%@ page import="com.vladshkerin.models.User" %>
<%@ page import="com.vladshkerin.services.UserService" %><%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 13.04.16
  Time: 19:54
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

<h1>Edit user:</h1>
<h2>Modify the form below and click "save change" to save</h2>

<form action="<%=request.getContextPath()%>/useredit" method="post">
    <div class="tableRow">
        <p> Id </p>
        <p><input type="text" name="id"
                  value="<%=session.getAttribute("id")%>" placeholder="00001"></p>
    </div>
    <div class="tableRow">
        <p> Name </p>
        <p><input type="text" name="name"
                  value="<%=session.getAttribute("name")%>" placeholder="Ivan"></p>
    </div>
    <div class="tableRow">
        <p> Growth </p>
        <p><input type="number" name="growth" min="1.0" max="200.0"
                  value="<%=session.getAttribute("growth")%>" placeholder="176"></p>
    </div>
    <div class="tableRow">
        <p> Date </p>
        <p><input type="date" name="birthDay"
                  value="<%=session.getAttribute("birthDay")%>"></p>
    </div>
    <div class="tableRow">
        <p> Email </p>
        <p><input type="email" name="email"
                  value="<%=session.getAttribute("email")%>" placeholder="user@mail.ru"></p>
    </div>
    <div class="tableRow">
        <p> Children </p>
        <p><textarea name="children"><%=session.getAttribute("children")%></textarea></p>
    </div>
    <div class="tableRow">
        <p></p>
        <p>
            <input id="buttonSave" type="submit" value="Save change">
            <%--<input id="buttonDelete" type="button" value="Delete user">--%>
            <input id="buttonDelete" type="submit" value="Delete user"
                   formaction="<%=request.getContextPath()%>/views/UserView.jsp">
            <input type="submit" value="Back"
                   formaction="<%=request.getContextPath()%>/views/UserView.jsp">
        </p>
    </div>
    <div class="tableRow">
        <p></p>
        <div id="message">
            <%if (session.getAttribute("message") != null) {%>
                <%=session.getAttribute("message")%>
            <%}%>
        </div>
    </div>
</form>

<script src="<%=request.getContextPath()%>/scripts/handlerButton.js"></script>

</body>
</html>
