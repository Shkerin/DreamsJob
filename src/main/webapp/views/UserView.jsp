<%@ page import="com.vladshkerin.enums.UserRole" %>
<%@ page import="com.vladshkerin.models.Role" %>
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

<div id="all_content">

    <jsp:include page="include/PageHead.jsp"></jsp:include>
    <%--<jsp:include page="include/PageLinks.jsp"></jsp:include>--%>
    <%--TODO remove if cancel comment up--%>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/index.jsp">HOME</a></li>
            <li class="selected"><a href="<%=request.getContextPath()%>/views/UserView.jsp">USERS</a></li>
            <li><a href="<%=request.getContextPath()%>/views/ItemView.jsp">ITEMS</a></li>
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

        <h1>List users:</h1>

        <table>
            <tr>
                <th class="right">Name</th>
                <th class="right">Growth</th>
                <th class="center">Birth day</th>
                <th class="center">Email</th>
                <th class="center">Children</th>
                <%
                    if (role.isRoleAdmin()) {
                %>
                <th class="center">Delete</th>
                <th class="center">Edit</th>
                <%
                    }
                %>
            </tr>
            <%
                for (User user : UserService.getInstance().getAll()) {
            %>
            <tr>
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
                <%
                    if (role.isRoleAdmin()) {
                %>
                <td class="center">
                    <a id="imageLinkDelete" href="<%=request.getContextPath()%>/user_delete?id=<%= user.getId() %>">
                        <img src="../img/trash_icon.png" width="20" height="20">
                    </a>
                </td>
                <td class="center">
                    <a id="imageLinkEdit" href="<%=request.getContextPath()%>/user_edit?id=<%= user.getId() %>">
                        <img src="../img/edit_icon.png" width="20" height="20">
                    </a>
                </td>
                <%
                    }
                %>
            </tr>
            <%
                }
            %>
        </table>

        <p>
        <div class="button_action">
            <%
                if (role.isRoleAdmin()) {
            %>
            <form action="<%=request.getContextPath()%>/views/UserAdd.jsp" method="post">
                <input type="submit" value="Add user">
            </form>
            <%
                }
            %>
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
