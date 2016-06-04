<%@ page import="com.vladshkerin.services.ItemService" %>
<%--
  The page to display items.

  @author Vladimir Shkerin
  @since 09.05.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageIndex" value=""/>
<c:set var="pageItemView" value="selected"/>
<c:set var="pageUserView" value=""/>

<html>
<head>
    <title>Dreams Job</title>
    <link type="text/css" rel="stylesheet" href="styles/index.css">
    <link type="text/css" rel="stylesheet" href="styles/style_table.css">
</head>
<body>

<div id="all_content">

    <%@ include file="/WEB-INF/views/includes/Head.jspf" %>
    <c:if test="${sessionScope.CURRENT_USER != null}">
        <%@ include file="/WEB-INF/views/includes/Links.jspf" %>
    </c:if>

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
            <c:forEach var="item" items="${sessionScope.items}">
                <tr>
                    <td class="right">
                        <c:out value="${item.user.name}"/>
                    </td>
                    <td class="center">
                        <c:out value="${item.getDateStr('dd.MM.yyyy HH:mm')}"/>
                    </td>
                    <td class="center">
                        <c:out value="${item.parentId}"/>
                    </td>
                    <td class="center">
                        <c:out value="${item.name}"/>
                    </td>
                    <td class="center">
                        <c:out value="${item.desc}"/>
                    </td>
                    <td class="center">
                        <a id="imageLinkEdit" href="item_edit?id=<c:out value="${item.id}"/>">
                            <img src="img/edit_icon.png" width="20" height="20">
                        </a>
                    </td>
                    <td class="center">
                        <a id="imageLinkDelete" href="item_delete?id=<c:out value="${item.id}"/>">
                            <img src="img/trash_icon.png" width="20" height="20">
                        </a>
                    </td>
                </tr>
            </c:forEach>
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

        <%--////////////////// Tree items ///////////////////////--%>

        <h1>Tree items:</h1>

        <form method="post">
            <ul class="treeItem">
                <li><input type="checkbox" name="tree" value="0">Root</li>
                <%--TODO question 03.06.2016--%>
                <%--<c:out value="${tree_items}"/>--%>
                <%= ItemService.getInstance().getTreeItems(0L) %>
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
                <c:forEach var="sheet" items="${sessionScope.sheets_tree_items}">
                    <li><c:out value="${sheet}"/></li>
                </c:forEach>
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

    <%@ include file="/WEB-INF/views/includes/Footer.jspf" %>

</div>
<%--all_content--%>

</body>
</html>
