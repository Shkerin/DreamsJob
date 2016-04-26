<%@ page import="com.vladshkerin.models.User" %>
<%@ page import="com.vladshkerin.services.UserService" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 06.04.16
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dreams Job</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/index.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/styletable.css">
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

<h1>List users:</h1>

<div>
    <%=request.getContextPath()%>
</div>

<table>
    <tr>
        <th class="center">Id</th>
        <th class="right">Name</th>
        <th class="right">Growth</th>
        <th class="center">Birth day</th>
        <th class="center">Email</th>
        <th class="center">Children</th>
        <th class="center">Del</th>
    </tr>
    <% for (User user : UserService.getInstance().getAll()) { %>
    <tr>
        <td class="center">
            <a href="<%=request.getContextPath()%>/useredit?id=<%= user.getId() %>"><%= user.getId() %></a>
        </td>
        <td class="right">
            <%= user.getName() %>
        </td>
        <td class="right">
            <%= user.getGrowth() %>
        </td>
        <td class="center">
            <%= user.getBirthDayStr() %>
        </td>
        <td class="center">
            <%= user.getEmail() %>
        </td>
        <td class="center">
            <%= user.getChildrenStr() %>
        </td>
        <td class="center">
            <a id="imageLink" href="<%=request.getContextPath()%>/userdelete?id=<%= user.getId() %>">
                <img src="../img/trash.png" width="20" height="20">
            </a>
        </td>
    </tr>
    <% } %>
</table>

<p>
    <div id="button" class="tableRow">
        <form action="<%=request.getContextPath()%>/useradd" method="post">
            <input type="submit" value="Add user">
        </form>
        <form action="<%=request.getContextPath()%>/index.jsp">
            <input type="submit" value="Back">
        </form>
    </div>
</p>

</body>
</html>
