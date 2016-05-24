<%--
  The main project page DreamsJob.

  @author Vladimir Shkerin
  @since 22.03.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dreams Job</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/index.css">
</head>
<body>

<div id="all_content">

    <%@ include file="views/include/PageHead.jsp"%>
    <%--<%@ include file="views/include/PageLinks.jsp"%>--%>
    <%--TODO remove if cancel comment up--%>
    <nav>
        <ul>
            <li class="selected"><a href="<%=request.getContextPath()%>/index.jsp">HOME</a></li>
            <li><a href="<%=request.getContextPath()%>/views/UserView.jsp">USERS</a></li>
            <li><a href="<%=request.getContextPath()%>/views/ItemView.jsp">ITEMS</a></li>
        </ul>
    </nav>

    <div id="main">
        <div id="page_registration">
            <%
            Object login = session.getAttribute("login");
            if (session.isNew() || login == null) {
            %>
                <h1>User registration</h1>
                <form action="<%=request.getContextPath()%>/login" method="post">
                    login : <input type="text" name="login"><br>
                    password : <input type="password" name="password"><br>
                    <input type="submit">
                </form>
            <%
            } else {
            %>
                <img src="img/love_job.jpg">
            <%
            }
            %>
        </div>
    </div>

    <%@ include file="views/include/PageFooter.jsp"%>

</div>

</body>
</html>
