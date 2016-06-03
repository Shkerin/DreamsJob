<%@ page import="com.vladshkerin.services.UserService" %>
<%--
  Tne page to display users.

  @author Vladimir Shkerin
  @since 06.04.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! String pageIndex = ""; %>
<%! String pageItemView = ""; %>
<%! String pageUserView = "selected"; %>

<html>
<head>
    <title>Dreams Job</title>
    <link type="text/css" rel="stylesheet" href="style/index.css">
    <link type="text/css" rel="stylesheet" href="style/styletable.css">
</head>
<body>

<div id="all_content">

    <%@ include file="/WEB-INF/views/include/Initialization.jspf" %>
    <%@ include file="/WEB-INF/views/include/Head.jspf" %>
    <%@ include file="/WEB-INF/views/include/Links.jspf" %>

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
                    if (CURRENT_ROLE.isRoleAdmin()) {
                %>
                <th class="center">Edit</th>
                <th class="center">Delete</th>
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
                <td class="center">
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
                    if (CURRENT_ROLE.isRoleAdmin()) {
                %>
                <td class="center">
                    <a id="imageLinkEdit" href="user_edit?id=<%= user.getId() %>">
                        <img src="img/edit_icon.png" width="20" height="20">
                    </a>
                </td>
                <td class="center">
                    <a id="imageLinkDelete" href="user_delete?id=<%= user.getId() %>">
                        <img src="img/trash_icon.png" width="20" height="20">
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
                if (CURRENT_ROLE.isRoleAdmin()) {
            %>
            <form action="navigation?page=user_add" method="post">
                <input type="submit" value="Add user">
            </form>
            <%
                }
            %>
            <form action="index.jsp" method="post">
                <input type="submit" value="Back">
            </form>
        </div>
        </p>

    </div>
    <%--main--%>

    <%@ include file="/WEB-INF/views/include/Footer.jspf" %>

</div>
<%--all_content--%>

</body>
</html>
