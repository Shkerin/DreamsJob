<%--
  The page for adding a item.
  
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
    <link type="text/css" rel="stylesheet" href="styles/style_form.css">
</head>
<body>

<div id="all_content">

    <%@ include file="/WEB-INF/views/includes/Head.jspf" %>
    <%@ include file="/WEB-INF/views/includes/Links.jspf" %>

    <div id="main">

        <h1>Add item:</h1>
        <h2>Fill out the form below and click "Add item" to add</h2>

        <form class="body_form" action="item_add"
              onsubmit="return validateFormItem()" method="post">
            <div class="tableRow">
                <p> User: </p>
                <p><input type="text" name="user"
                <c:out value="${sessionScope.readonly}"/>
                          value="<c:out value="${sessionScope.user.name}"/>"
                          placeholder="user">
                </p>
            </div>
            <div class="tableRow">
                <p> Parent id: </p>
                <p><input type="number" name="parentId" min="0" placeholder="1"></p>
            </div>
            <div class="tableRow">
                <p> Name: </p>
                <p><input type="text" name="name" placeholder="Distribution of orange"></p>
            </div>
            <div class="tableRow">
                <p> Description: </p>
                <p><textarea name="desc"></textarea></p>
            </div>
            <div class="tableRow">
                <p></p>
                <p>
                    <input id="buttonSave" type="submit" value="Add item">
                    <input type="button" value="Back"
                           onclick="document.location.href='navigation?page=items'">
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
