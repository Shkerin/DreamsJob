<%--
  The main page DreamsJob.

  @author Vladimir Shkerin
  @since 22.03.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! String pageIndex = "selected"; %>
<%! String pageItemView = ""; %>
<%! String pageUserView = ""; %>
<html>
<head>
    <title>Dreams Job</title>
    <link rel="stylesheet" type="text/css" href="style/index.css">
</head>
<body>

<div id="all_content">

    <%@ include file="/WEB-INF/views/include/Head.jspf" %>

    <%
        obj = session.getAttribute("user");
        if (!session.isNew() && obj != null) {
    %>
    <%@ include file="/WEB-INF/views/include/Links.jspf" %>
    <%
        }
    %>

    <div id="main">
        <%
            if (session.isNew() || obj == null) {
        %>
        <div id="form_registration">
            <h1>User registration</h1>
            <%
                Object message = session.getAttribute("message");
                if (message != null) {
            %>
            <p id="message">
                <%=(String) message%>
            </p>
            <%
                }
            %>
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
        <%
        } else {
        %>
        <div id="image_registration">
            <img src="img/love_job.jpg">
        </div>
        <%
            }
        %>
    </div>

    <%@ include file="/WEB-INF/views/include/Footer.jspf" %>

</div>

</body>
</html>
