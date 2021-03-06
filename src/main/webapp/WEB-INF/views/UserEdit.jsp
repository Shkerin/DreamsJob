<%--
  The page for editing a user.

  @author Vladimir Shkerin
  @since 13.04.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageIndex" value=""/>
<c:set var="pageItemView" value=""/>
<c:set var="pageUserView" value="selected"/>

<html>
<head>
    <title>Dreams Job</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/main.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/style_form.css">
</head>
<body>

<div id="all_content">

    <%@ include file="/WEB-INF/views/includes/Head.jspf" %>
    <%@ include file="/WEB-INF/views/includes/Links.jspf" %>

    <div id="main">

        <h1>Edit user:</h1>
        <h2>Modify the form below and click "Save change" to save</h2>

        <form class="body_form" action="user_edit"
              onsubmit="return validateFormUser();" method="post">
            <input type="hidden" name="id" value="<c:out value="${sessionScope.user.id}"/>">
            <div class="tableRow">
                <p> Name: </p>
                <p><input type="text" name="name"
                          value="<c:out value="${sessionScope.user.name}"/>" placeholder="Ivan"></p>
            </div>
            <div class="tableRow">
                <p> Growth: </p>
                <p><input type="number" name="growth" min="1.0" max="200.0"
                          value="<c:out value="${sessionScope.user.growth}"/>" placeholder="176"></p>
            </div>
            <div class="tableRow">
                <p> Birth date: </p>
                <p><input type="date" name="birthDay"
                          value="<c:out value="${sessionScope.user.getBirthDayStr()}"/>" title="Birth date"></p>
            </div>
            <div class="tableRow">
                <p> Email: </p>
                <p><input type="email" name="email"
                          value="<c:out value="${sessionScope.user.email}"/>" placeholder="user@mail.ru"></p>
            </div>
            <div class="tableRow">
                <p> Children: </p>
                <p><textarea name="children" title="Children"><c:out value="${sessionScope.user.getChildrenStr()}"/>
                </textarea></p>
            </div>
            <div class="tableRow">
                <p></p>
                <p>
                    <input id="buttonSave" type="submit" value="Save change">
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
