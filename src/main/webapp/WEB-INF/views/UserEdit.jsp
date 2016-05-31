<%--
  The page for editing a user.

  @author Vladimir Shkerin
  @since 13.04.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! String pageIndex = ""; %>
<%! String pageItemView = ""; %>
<%! String pageUserView = "selected"; %>

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

        <h1>Edit user:</h1>
        <h2>Modify the form below and click "save change" to save</h2>

        <%
            obj = ApplicationService.getInstance().getSessionAttribute("id", session);
            String id = obj != null ? (String) obj : "";
            obj = ApplicationService.getInstance().getSessionAttribute("name", session);
            String name = obj != null ? (String) obj : "";
            obj = ApplicationService.getInstance().getSessionAttribute("growth", session);
            String growth = obj != null ? (String) obj : "";
            obj = ApplicationService.getInstance().getSessionAttribute("birthDay", session);
            String birthDay = obj != null ? (String) obj : "";
            obj = ApplicationService.getInstance().getSessionAttribute("email", session);
            String email = obj != null ? (String) obj : "";
            obj = ApplicationService.getInstance().getSessionAttribute("children", session);
            String children = obj != null ? (String) obj : "";
        %>

        <form class="body_form" action="user_edit"
              onsubmit="return validateFormUser();" method="post">
            <input type="hidden" name="id" value="<%= id %>">
            <div class="tableRow">
                <p> Name: </p>
                <p><input type="text" name="name"
                          value="<%= name %>" placeholder="Ivan"></p>
            </div>
            <div class="tableRow">
                <p> Growth: </p>
                <p><input type="number" name="growth" min="1.0" max="200.0"
                          value="<%= growth %>" placeholder="176"></p>
            </div>
            <div class="tableRow">
                <p> Birth date: </p>
                <p><input type="date" name="birthDay"
                          value="<%= birthDay %>"></p>
            </div>
            <div class="tableRow">
                <p> Email: </p>
                <p><input type="email" name="email"
                          value="<%= email %>" placeholder="user@mail.ru"></p>
            </div>
            <div class="tableRow">
                <p> Children </p>
                <p><textarea name="children"><%= children %></textarea></p>
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
                           onclick="document.location.href='navigation?page=users'">
                </p>
            </div>
            <div class="tableRow">
                <p></p>
                <div id="message">
                    <%
                        obj = ApplicationService.getInstance().getSessionAttribute("message", session);
                        if (obj != null) {
                            out.print((String) obj);
                            ApplicationService.getInstance().setSessionAttribute("message", "", session);
                        }
                    %>
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
