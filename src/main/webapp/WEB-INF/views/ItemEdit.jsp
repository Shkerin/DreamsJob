<%--
  The page for editing a item.

  @author Vladimir Shkerin
  @since 01.06.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageIndex" value=""/>
<c:set var="pageItemView" value="selected"/>
<c:set var="pageUserView" value=""/>

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

        <h1>Edit item:</h1>
        <h2>Modify the form below and click "Save change" to save</h2>

        <form class="body_form" action="item_edit"
              onsubmit="return validateFormItem();" method="post">
            <input type="hidden" name="id" value="<c:out value="${sessionScope.item.id}"/>">
            <div class="tableRow">
                <p> User: </p>
                <p><input type="text" name="user"
                <c:out value="${sessionScope.readonly}"/>
                          value="<c:out value="${sessionScope.item.user.name}"/>" title="User">
                </p>
            </div>
            <div class="tableRow">
                <p> Date: </p>
                <p><input type="datetime" name="date"
                <c:out value="${sessionScope.readonly}"/>
                          value="<c:out value="${sessionScope.item.getDateStr('dd.MM.yyyy HH:mm')}"/>" title="Date">
                </p>
            </div>
            <div class="tableRow">
                <p> Parent id: </p>
                <p><input type="number" name="parentId" min="0"
                          value="<c:out value="${sessionScope.item.parentId}"/>" placeholder="5"></p>
            </div>
            <div class="tableRow">
                <p> Name: </p>
                <p><input type="text" name="name"
                          value="<c:out value="${sessionScope.item.name}"/>" placeholder="name item"></p>
            </div>
            <div class="tableRow">
                <p> Description: </p>
                <p><textarea name="desc" title="Description"><c:out value="${sessionScope.item.desc}"/></textarea></p>
            </div>
            <div class="tableRow">
                <p></p>
                <p>
                    <input id="buttonSave" type="submit" value="Save change">
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
