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

<div id="all_content">

    <jsp:include page="include/PageHead.jsp"></jsp:include>
    <%--<jsp:include page="include/PageLinks.jsp"></jsp:include>--%>

    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/index.jsp">HOME</a></li>
            <li><a href="<%=request.getContextPath()%>/views/UserView.jsp">USERS</a></li>
            <li class="selected"><a href="<%=request.getContextPath()%>/views/ItemView.jsp">ITEMS</a></li>
        </ul>
    </nav>

    <div id="main">

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

    </div> <%--main--%>

    <jsp:include page="include/PageFooter.jsp"></jsp:include>

</div>  <%--all_content--%>

</body>
</html>
