<%--
  The login page DreamsJob.
  
  @author Vladimir Shkerin
  @since 06.06.2016
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Dreams Job</title>
    <link rel="stylesheet" type="text/css" href="styles/index.css">
</head>
<body>

<div id="all_content">

    <%@ include file="/WEB-INF/views/includes/Head.jspf" %>

    <div id="main">

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

    </div>
    <%--main--%>

    <%@ include file="/WEB-INF/views/includes/Footer.jspf" %>

</body>
</html>
