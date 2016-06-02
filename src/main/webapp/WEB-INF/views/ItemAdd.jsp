<%--
  The page for adding a item.
  
  @author Vladimir Shkerin
  @since 09.05.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! String pageIndex = ""; %>
<%! String pageItemView = "selected"; %>
<%! String pageUserView = ""; %>

<html>
<head>
    <title>Dreams Job</title>
    <link type="text/css" rel="stylesheet" href="style/index.css">
    <link type="text/css" rel="stylesheet" href="style/styleform.css">
</head>
<body>

<div id="all_content">

    <%@ include file="/WEB-INF/views/include/Head.jspf" %>
    <%@ include file="/WEB-INF/views/include/Links.jspf" %>

    <div id="main">

        <h1>Add item:</h1>
        <h2>Fill out the form below and click "add item" to add</h2>

        <form class="body_form" action="item_add"
              onsubmit="return validateFormItem()" method="post">
            <div class="tableRow">
                <p> Parent id: </p>
                <p><input type="number" name="parentId" min="0" max="1000000" placeholder="1"></p>
            </div>
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
                    <input type="button" value="Back"
                           onclick="document.location.href='navigation?page=items'">
                </p>
            </div>
            <div class="tableRow">
                <p></p>
                <div id="message">
                    <%@ include file="/WEB-INF/views/include/DisplayMessage.jspf" %>
                </div>
            </div>
        </form>

    </div>
    <%--main--%>

    <%@ include file="/WEB-INF/views/include/Footer.jspf" %>

</div>
<%--all_content--%>

<script src="<%=request.getContextPath()%>/scripts/handlerButton.js"></script>

</body>
</html>
