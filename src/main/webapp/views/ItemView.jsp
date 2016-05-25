<%@ page import="com.vladshkerin.enums.UserRole" %>
<%@ page import="com.vladshkerin.models.Item" %>
<%@ page import="com.vladshkerin.models.Role" %>
<%@ page import="com.vladshkerin.services.ItemService" %>
<%@ page import="java.util.List" %>
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
    <%--<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/style_tree.css">--%>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/styletable.css">
</head>
<body>

<div id="all_content">

    <jsp:include page="include/PageHead.jsp"></jsp:include>
    <%--<jsp:include page="include/PageLinks.jsp"></jsp:include>--%>
    <%--TODO remove if cancel comment up--%>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/index.jsp">HOME</a></li>
            <li><a href="<%=request.getContextPath()%>/views/UserView.jsp">USERS</a></li>
            <li class="selected"><a href="<%=request.getContextPath()%>/views/ItemView.jsp">ITEMS</a></li>
        </ul>
    </nav>

    <%
        Role role = new Role(UserRole.USER);
        synchronized (session) {
            Object obj = session.getAttribute("role");
            if (obj != null && obj instanceof Role) {
                role = (Role) obj;
            }
        }
    %>

    <div id="main">

        <h1>Tree items:</h1>

        <form method="post">
            <ul class="treeCSS">
                <li><input type="checkbox" name="tree" value="0">Root</li>
                <%=ItemService.getInstance().getTreeItems(role, 0L)%>
            </ul>

            <p>
            <div class="button_action">
                <input type="submit" value="Cut"
                       formaction="<%=request.getContextPath()%>/item_cut">
                <input type="submit" value="Paste"
                       formaction="<%=request.getContextPath()%>/item_paste">
            </div>
            </p>
        </form>

        <ul class="itemCut">
            <%
                Object obj = session.getAttribute("tree");
                if (obj != null && obj instanceof List) {
                    List<String> list = (List) obj;
                    for (String str : list) {
            %>
            <li><%= str%>
            </li>
            <%
                    }
                }
            %>
        </ul>

        <hr>

        <h1>List items:</h1>

        <table>
            <tr>
                <th class="right">Parent id</th>
                <th class="right">Name</th>
                <th class="center">Description</th>
            </tr>
            <%
                for (Item item : ItemService.getInstance().getAll()) {
                    if (role.isRoleAdmin() ||
                            item.getUser().getRole().isRoleUser()) {
            %>
            <tr>
                <td class="center">
                    <%= item.getParentId()%>
                </td>
                <td class="right">
                    <%= item.getName() %>
                </td>
                <td class="center">
                    <%= item.getDesc() %>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>

        <p>
        <div class="button_action">
            <form action="<%=request.getContextPath()%>/views/ItemAdd.jsp" method="post">
                <input type="submit" value="Add item">
            </form>
            <form action="<%=request.getContextPath()%>/index.jsp" method="post">
                <input type="submit" value="Back">
            </form>
        </div>
        </p>

    </div>
    <%--main--%>

    <jsp:include page="include/PageFooter.jsp"></jsp:include>

</div>
<%--all_content--%>

</body>
</html>
