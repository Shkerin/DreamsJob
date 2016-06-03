<%@ page import="com.vladshkerin.models.Item" %>
<%@ page import="com.vladshkerin.services.ItemService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.vladshkerin.services.FilterService" %>
<%--
  The page to display items.

  @author Vladimir Shkerin
  @since 09.05.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! String pageIndex = ""; %>
<%! String pageItemView = "selected"; %>
<%! String pageUserView = ""; %>

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

        <h1>List items:</h1>

        <table>
            <tr>
                <th class="center">User</th>
                <th class="center">Date</th>
                <th class="right">Parent id</th>
                <th class="right">Name</th>
                <th class="center">Description</th>
                <th class="center">Edit</th>
                <th class="center">Delete</th>
            </tr>
            <%
                for (Item item : ItemService.getInstance().getAll()) {
                    if (FilterService.getInstance().validationItem(item)) {
            %>
            <tr>
                <td class="right">
                    <%= item.getUser().getName() %>
                </td>
                <td class="center">
                    <%= item.getDateStr() %>
                </td>
                <td class="center">
                <%= item.getParentId() %>
                </td>
                <td class="center">
                <%= item.getName() %>
                </td>
                <td class="center">
                <%= item.getDesc() %>
                </td>
                <td class="center">
                    <a id="imageLinkEdit" href="item_edit?id=<%= item.getId() %>">
                        <img src="img/edit_icon.png" width="20" height="20">
                    </a>
                </td>
                <td class="center">
                    <a id="imageLinkDelete" href="item_delete?id=<%= item.getId() %>">
                        <img src="img/trash_icon.png" width="20" height="20">
                    </a>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>

        <p>
        <div class="button_action">
            <form action="navigation?page=item_add" method="post">
                <input type="submit" value="Add item">
            </form>
            <form action="index.jsp" method="post">
                <input type="submit" value="Back">
            </form>
        </div>
        </p>

        <hr>

        <h1>Tree items:</h1>

        <form method="post">
            <ul class="treeItem">
                <li><input type="checkbox" name="tree" value="0">Root</li>
                <%= ItemService.getInstance().getTreeItems(CURRENT_USER, 0L) %>
            </ul>

            <p>
            <div class="button_action">
                <input type="submit" value="Cut"
                       formaction="item_cut">
                <input type="submit" value="Paste"
                       formaction="item_paste">
            </div>
            </p>

        </form>

        <form method="post">
            <h2>Choice item</h2>

            <ul class="itemCut">
                <%
                    obj = session.getAttribute("tree");
                    if (obj != null && obj instanceof List) {
                        List<String> list = (List) obj;
                        for (String str : list) {
                %>
                <li><%= str %>
                </li>
                <%
                        }
                    }
                %>
            </ul>

            <p>
            <div class="button_action">
                <input type="submit" value="Cancel"
                       formaction="item_cancel_cut">
            </div>
            </p>
        </form>

    </div>
    <%--main--%>

    <%@ include file="/WEB-INF/views/include/Footer.jspf" %>

</div>
<%--all_content--%>

</body>
</html>
