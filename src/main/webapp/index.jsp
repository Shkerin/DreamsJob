<%--
  The main project page DreamsJob.

  @author Vladimir Shkerin
  @since 22.03.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dreams Job</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/index.css">
</head>
<body>

<%
    Object login = session.getAttribute("login");
    if (session.isNew() || login == null) {
        %><h1>User registration</h1>
        <p>
            <form action="<%=request.getContextPath()%>/login" method="post">
                login : <input type="text" name="login"><br>
                password : <input type="password" name="password"><br>
                <input type="submit">
            </form>
        </p>
        <%
    } else {
        %>
        <%@ include file="views/include/UserRegistrationInfo.jsp"%>
        <%
    }
%>

<div>
    <a href="<%=request.getContextPath()%>/views/UserView.jsp">List users</a>
</div>
<br>
<div>
    <a href="<%=request.getContextPath()%>/views/ItemView.jsp">List items</a>
</div>

</body>
</html>
