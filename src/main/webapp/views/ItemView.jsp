<%@ page import="com.vladshkerin.models.Item" %>
<%@ page import="com.vladshkerin.services.ItemService" %>
<%--
  The page to display items.

  @author Vladimir Shkerin
  @since 09.05.2016
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

<h1>List items:</h1>

<table>
    <tr>
        <th class="right">Name</th>
        <th class="center">Description</th>
    </tr>
    <% for (Item item : ItemService.getInstance().getAll()) { %>
    <tr>
        <td class="right">
            <%--<a href="<%=request.getContextPath()%>/itemedit?id=<%=item.getId()%>">--%>
                <%--<%= item.getName() %>--%>
            <%--</a>--%>
            <%= item.getName() %>
        </td>
        <td class="center">
            <%= item.getDesc() %>
        </td>
    </tr>
    <% } %>
</table>

<p>
<div id="button" class="tableRow">
    <form action="<%=request.getContextPath()%>/views/ItemAdd.jsp" method="post">
        <input type="submit" value="Add item">
    </form>
    <form action="<%=request.getContextPath()%>/index.jsp" method="post">
        <input type="submit" value="Back">
    </form>
</div>
</p>

</body>
</html>
