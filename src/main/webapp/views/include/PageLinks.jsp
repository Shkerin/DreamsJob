<%--
  The page displays links.
  
  @author Vladimir Shkerin
  @since 17.05.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav>
    <ul>
        <li><a href="<%=request.getContextPath()%>/index.jsp">HOME</a></li>
        <li><a href="<%=request.getContextPath()%>/views/UserView.jsp">USERS</a></li>
        <li><a href="<%=request.getContextPath()%>/views/ItemView.jsp">ITEMS</a></li>
    </ul>
</nav>
