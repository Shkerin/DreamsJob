<%--
  The page for editing a item.

  @author Vladimir Shkerin
  @since 01.06.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! String pageIndex = ""; %>
<%! String pageItemView = "selected"; %>
<%! String pageUserView = ""; %>

<html>
<head>
    <title>Dreams Job</title>
    <link type="text/css" rel="stylesheet" href="style/index.css">
    <link type="text/css" rel="stylesheet" href="style/styleform.css">
</head>
<body>

<div id="all_content">

    <%@ include file="/WEB-INF/views/include/Head.jspf" %>
    <%@ include file="/WEB-INF/views/include/Links.jspf" %>
    <%@ include file="/WEB-INF/views/include/Initialization.jspf" %>

    <div id="main">

        <h1>Edit item:</h1>
        <h2>Modify the form below and click "save change" to save</h2>

        <%
            obj = session.getAttribute("id");
            String id = obj != null ? (String) obj : "";
            obj = session.getAttribute("parentId");
            String parentId = obj != null ? (String) obj : "";
            obj = session.getAttribute("user");
            String user = obj != null ? (String) obj : "";
            obj = session.getAttribute("name");
            String name = obj != null ? (String) obj : "";
            obj = session.getAttribute("desc");
            String desc = obj != null ? (String) obj : "";
            obj = session.getAttribute("date");
            String date = obj != null ? (String) obj : "";
        %>

        <form class="body_form" action="item_edit"
              onsubmit="return validateFormItem();" method="post">
            <input type="hidden" name="id" value="<%= id %>">
            <div class="tableRow">
                <p> Parent id: </p>
                <p><input type="number" name="parentId" min="0" max="1000000"
                          value="<%= parentId %>" placeholder="5"></p>
            </div>
            <div class="tableRow">
                <p> User: </p>
                <p><input type="text" name="user"
                          value="<%= user %>"></p>
            </div>
            <div class="tableRow">
                <p> Name: </p>
                <p><input type="text" name="name"
                          value="<%= name %>" placeholder="name item"></p>
            </div>
            <div class="tableRow">
                <p> Desc: </p>
                <p><input type="text" name="desc"
                          value="<%= desc %>" placeholder="desc item"></p>
            </div>
            <div class="tableRow">
                <p> Date: </p>
                <p><input type="datetime" name="date"
                          value="<%= date %>"></p>
            </div>
            <div class="tableRow">
                <p></p>
                <p>
                    <%
                        if (role.isRoleAdmin()) {
                    %>
                    <input id="buttonSave" type="submit" value="Save change">
                    <%
                        }
                    %>
                    <input type="button" value="Back"
                           onclick="document.location.href='navigation?page=items'">
                </p>
            </div>
            <div class="tableRow">
                <p></p>
                <div id="message">
                    <%@ include file="/WEB-INF/views/include/DisplayMessage.jspf" %>
                </div>
            </div>
        </form>

    </div>
    <%--main--%>

    <%@ include file="/WEB-INF/views/include/Footer.jspf" %>

</div>
<%--all_content--%>

<script src="<%=request.getContextPath()%>/scripts/handlerButton.js"></script>

</body>
</html>
