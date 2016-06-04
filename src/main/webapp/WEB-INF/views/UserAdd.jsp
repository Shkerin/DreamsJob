<%--
  The page for adding a user.

  @author Vladimir Shkerin
  @since 12.04.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageIndex" value=""/>
<c:set var="pageItemView" value=""/>
<c:set var="pageUserView" value="selected"/>

<html>
<head>
    <title>Dreams Job</title>
    <link type="text/css" rel="stylesheet" href="styles/index.css">
    <link type="text/css" rel="stylesheet" href="styles/style_form.css">
</head>
<body>

<div id="all_content">

    <%@ include file="/WEB-INF/views/includes/Head.jspf" %>
    <c:if test="${sessionScope.CURRENT_USER != null}">
        <%@ include file="/WEB-INF/views/includes/Links.jspf" %>
    </c:if>

    <div id="main">

        <h1>Add user:</h1>
        <h2>Fill out the form below and click "Add user" to add</h2>

        <form class="body_form" action="user_add"
              onsubmit="return validateFormUser()" method="post">
            <div class="tableRow">
                <p> Name: </p>
                <p><input type="text" name="name" value="" placeholder="Ivan"></p>
            </div>
            <div class="tableRow">
                <p> Growth: </p>
                <p><input type="number" name="growth" min="1.0" max="200.0" placeholder="176.5"></p>
            </div>
            <div class="tableRow">
                <p> Birth date: </p>
                <p><input type="date" name="birthDay" value="date"></p>
            </div>
            <div class="tableRow">
                <p> Email: </p>
                <p><input type="email" name="email" value="" placeholder="user@mail.ru"></p>
            </div>
            <div class="tableRow">
                <p> Children: </p>
                <p><textarea name="children"></textarea></p>
            </div>
            <div class="tableRow">
                <p></p>
                <p>
                    <c:if test="${sessionScope.CURRENT_USER.role.isRoleAdmin()}">
                        <input id="buttonSave" type="submit" value="Add user">
                    </c:if>
                    <input type="button" value="Back"
                           onclick="document.location.href='navigation?page=users'">

                </p>
            </div>
            <div class="tableRow">
                <p></p>
                <div id="message">
                    <c:out value="${sessionScope.message}"/>
                </div>
            </div>
        </form>

    </div>
    <%--main--%>

    <%@ include file="/WEB-INF/views/includes/Footer.jspf" %>

</div>
<%--all_content--%>

<script src="<c:out value="${pageContext.servletContext.contextPath}"/>/scripts/handlerButton.js"></script>

</body>
</html>
