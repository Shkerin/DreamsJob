<%--
  The page displays logo.
  
  @author Vladimir Shkerin
  @since 17.05.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="top">
    <div id="top_logo">
        <a href="<%=request.getContextPath()%>/index.jsp">
            <img src="<%=request.getContextPath()%>/img/DreamsJob_logo.png"
                 alt="Dreams Job logo image">
        </a>
    </div>
    <div id="top_registration">
        <%
            if (session.getAttribute("login") != null) {
        %>
        Welcome back! (user: "<%=session.getAttribute("login")%>"; role: "<%=session.getAttribute("role")%>")
        <a href="<%=request.getContextPath()%>/logout">logout</a>
        <%
        } else {
        %>
        Unregistered user!
        <%
            }
        %>
    </div>
</header>
