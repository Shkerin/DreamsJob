<%--
  The page is displayed when an incorrect query in the servlet navigation.
  
  @author Vladimir Shkerin
  @since 31.05.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dreams Job</title>
</head>
<body>

<div align="center">
    <h1>Error 404</h1>
    <h3>Page does not exist:</h3>
    <h3><%= session.getAttribute("error_url") %></h3>
    <br>
    <img src="img/dismissal.jpg" height="500" width="500">
</div>

</body>
</html>
