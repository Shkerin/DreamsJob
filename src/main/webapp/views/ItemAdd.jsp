<%@ page import="com.vladshkerin.models.Item" %>
<%--
  Page for add item.
  
  @author vlad
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

<p>
    <%
    if (session.getAttribute("login") != null) {
        %>Welcome back, <%=session.getAttribute("login")%>.
        <a href="<%=request.getContextPath()%>/logout">logout</a><%
    } else {
        %>Unregistered user!<%
    }
    %>
</p>

<h1>Add item:</h1>
<h2>Fill out the form below and click "add item" to add</h2>

<form action="<%=request.getContextPath()%>/itemadd"
      onsubmit="return validateFormItem()" method="post">
    <input type="hidden" name="id" value="<%=Item.getNextId()%>">
    <div class="tableRow">
        <p> Name </p>
        <p><input type="text" name="name" value="" placeholder="Distribution of orange"></p>
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

<script src="<%=request.getContextPath()%>/scripts/handlerButton.js"></script>

</body>
</html>
