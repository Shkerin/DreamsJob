<%--
  Tne page to display users.

  @author Vladimir Shkerin
  @since 06.04.2016
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
    <link type="text/css" rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/style_table.css">
</head>
<body>

<div id="all_content">

    <%@ include file="/WEB-INF/views/includes/Head.jspf" %>
    <%@ include file="/WEB-INF/views/includes/Links.jspf" %>

    <div id="main">

        <h1>List users:</h1>

        <table>
            <tr>
                <th class="right">Name</th>
                <th class="right">Growth</th>
                <th class="center">Birth day</th>
                <th class="center">Email</th>
                <th class="center">Children</th>
                <th class="center">Edit</th>
                <th class="center">Delete</th>
            </tr>
            <c:forEach var="user" items="${sessionScope.users}">
                <tr>
                    <td class="right">
                        <c:out value="${user.name}"/>
                    </td>
                    <td class="center">
                        <c:out value="${user.growth}"/>
                    </td>
                    <td class="center">
                        <c:out value="${user.getBirthDayStr(\"dd.MM.yyyy\")}"/>
                    </td>
                    <td class="center">
                        <c:out value="${user.email}"/>
                    </td>
                    <td class="center">
                        <c:out value="${user.getChildrenStr()}"/>
                    </td>
                    <td class="center">
                        <a id="imageLinkEdit" href="user_edit?id=<c:out value="${user.id}"/>">
                            <img src="${pageContext.servletContext.contextPath}/img/edit_icon.png"
                                 width="20" height="20">
                        </a>
                    </td>
                    <td class="center">
                        <a id="imageLinkDelete" href="user_delete?id=<c:out value="${user.id}"/>">
                            <img src="${pageContext.servletContext.contextPath}/img/trash_icon.png"
                                 width="20" height="20">
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <p>
        <div class="button_action">
            <c:if test="${sessionScope.CURRENT_USER.role.isRoleAdmin()}">
                <form action="navigation?page=user_add" method="post">
                    <input type="submit" value="Add user">
                </form>
            </c:if>
            <form action="navigation?page=main" method="post">
                <input type="submit" value="Back">
            </form>
        </div>

    </div>
    <%--main--%>

    <%@ include file="/WEB-INF/views/includes/Footer.jspf" %>

</div>
<%--all_content--%>

</body>
</html>
