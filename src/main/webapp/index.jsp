<%--
  The main page DreamsJob.

  @author Vladimir Shkerin
  @since 22.03.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageIndex" value="selected"/>
<c:set var="pageItemView" value=""/>
<c:set var="pageUserView" value=""/>

<html>
<head>
    <title>Dreams Job</title>
    <link rel="stylesheet" type="text/css" href="styles/index.css">
</head>
<body>

<div id="all_content">

    <%--TODO question 03.06.2016--%>
    <%--<c:import url="/WEB-INF/views/include/Head.jspf"/>--%>
    <%@ include file="/WEB-INF/views/includes/Head.jspf" %>
    <c:if test="${sessionScope.CURRENT_USER != null}">
        <%@ include file="/WEB-INF/views/includes/Links.jspf" %>
    </c:if>

    <div id="main">
        <c:choose>
            <c:when test="${sessionScope.CURRENT_USER != null}">
                <div id="image_registration">
                    <img src="img/love_job.jpg">
                </div>
            </c:when>
            <c:otherwise>
                <div id="form_registration">
                    <h1>User registration</h1>
                    <c:if test="${sessionScope.message != null}">
                        <p id="message">
                            <c:out value="${sessionScope.message}"/>
                        </p>
                    </c:if>
                    <form action="login" method="post">
                        <table>
                            <tr>
                                <td>login :</td>
                                <td><input type="text" name="login"></td>
                            </tr>
                            <tr>
                                <td>password :</td>
                                <td><input type="password" name="password"></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="submit"></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <%--main--%>

    <%@ include file="/WEB-INF/views/includes/Footer.jspf" %>

</div
<%--all_content--%>

</body>
</html>
