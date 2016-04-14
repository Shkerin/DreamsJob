<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 22.03.16
  Time: 19:21
  To change this template use File | Settings | File Templates.
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
    } else if (login != null) {
        %>Welcome back, <%=session.getAttribute("login")%>.
            <a href="<%=request.getContextPath()%>/logout">logout</a><%
    } else {
        %>Unregistered user!<%
    }
%>

<div>
    <a href="<%=request.getContextPath()%>/views/UserView.jsp">List users</a>
</div>

</body>
</html>
