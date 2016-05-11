<%@ page import="com.vladshkerin.models.User" %>
<%@ page import="com.vladshkerin.services.UserService" %>
<%--
  Tne page to display users.

  @author Vladimir Shkerin
  @since 06.04.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dreams Job</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/index.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/styletable.css">
</head>
<body>

<jsp:include page="include/UserRegistrationInfo.jsp"></jsp:include>

<h1>List users:</h1>

<table>
    <tr>
        <th class="right">Name</th>
        <th class="right">Growth</th>
        <th class="center">Birth day</th>
        <th class="center">Email</th>
        <th class="center">Children</th>
        <th class="center">Del</th>
    </tr>
    <% for (User user : UserService.getInstance().getAll()) { %>
    <tr>
        <td class="right">
            <a href="<%=request.getContextPath()%>/useredit?id=<%= user.getId() %>">
                <%= user.getName() %>
            </a>
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
    <form action="<%=request.getContextPath()%>/views/UserAdd.jsp" method="post">
        <input type="submit" value="Add user">
    </form>
    <form action="<%=request.getContextPath()%>/index.jsp" method="post">
        <input type="submit" value="Back">
    </form>
</div>
</p>

</body>
</html>
