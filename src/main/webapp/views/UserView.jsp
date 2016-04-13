<%@ page import="com.vladshkerin.models.User" %>
<%@ page import="com.vladshkerin.services.UserService" %><%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 06.04.16
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User view</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/index.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/styletable.css">
</head>
<body>
<h1>List users:</h1>
<h2></h2>

<div>
    <%=request.getContextPath()%>
</div>

<table>
    <tr>
        <th class="center">Id</th>
        <th class="right">Name</th>
        <th class="right">Growth</th>
        <th class="center">Birth day</th>
        <th class="center">Children</th>
    </tr>
    <% for (User user : UserService.getInstance().getAll()) { %>
    <tr>
        <td class="center"><%= user.getId() %>
        </td>
        <td class="right"><%= user.getName() %>
        </td>
        <td class="right"><%= user.getGrowth() %>
        </td>
        <td class="center"><%= user.getBirthDayStr() %>
        </td>
        <td class="center"><%= user.getChildrenStr() %>
        </td>
    </tr>
    <% } %>
</table>

<p>
    <div>
        <form action="<%=request.getContextPath()%>/useradd" method="post">
            <input type="submit" value="Add user">
        </form>
        <form action="<%=request.getContextPath()%>/userdel" method="post">
            <input type="submit" value="Delete user">
        </form>
    </div>
</p>

</body>
</html>
