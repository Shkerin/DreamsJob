<%--
  The page for adding a item.
  
  @author Vladimir Shkerin
  @since 09.05.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dreams Job</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/index.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/styleform.css">
</head>
<body>

<div id="all_content">

    <jsp:include page="include/PageHead.jsp"></jsp:include>
    <%--<jsp:include page="include/PageLinks.jsp"></jsp:include>--%>
    <%--TODO remove if cancel comment up--%>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/index.jsp">HOME</a></li>
            <li><a href="<%=request.getContextPath()%>/views/UserView.jsp">USERS</a></li>
            <li class="selected"><a href="<%=request.getContextPath()%>/views/ItemView.jsp">ITEMS</a></li>
        </ul>
    </nav>

    <div id="main">

        <h1>Add item:</h1>
        <h2>Fill out the form below and click "add item" to add</h2>

        <form class="body_form" action="<%=request.getContextPath()%>/item_add"
              onsubmit="return validateFormItem()" method="post">
            <div class="tableRow">
                <p> Name </p>
                <p><input type="text" name="name" placeholder="Distribution of orange"></p>
            </div>
            <div class="tableRow">
                <p> Description </p>
                <p><textarea name="desc"></textarea></p>
            </div>
            <div class="tableRow">
                <p></p>
                <p>
                    <input id="buttonSave" type="submit" value="Save change">
                    <%--<input type="submit" value="Back"--%>
                           <%--formaction="<%=request.getContextPath()%>/views/ItemView.jsp">--%>
                    <input type="button" value="Back"
                           onclick="document.location.href=
                                   '<%=request.getContextPath()%>/views/ItemView.jsp'">
                </p>
            </div>
            <div class="tableRow">
                <p></p>
                <div id="message">
                    <%if (session.getAttribute("message") != null) {%>
                        <%=session.getAttribute("message")%>
                        <%session.setAttribute("message", "");%>
                    <%}%>
                </div>
            </div>
        </form>

    </div> <%--main--%>

    <jsp:include page="include/PageFooter.jsp"></jsp:include>

</div>  <%--all_content--%>

<script src="<%=request.getContextPath()%>/scripts/handlerButton.js"></script>

</body>
</html>
