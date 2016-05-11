<%--
  To display information about user registration.
  
  @author vlad
  @since 11.05.2016
--%>
<p>
    <%
    if (session.getAttribute("login") != null) {
        %>Welcome back, <%=session.getAttribute("login")%> (<%=session.getAttribute("role")%>).
        <a href="<%=request.getContextPath()%>/logout">logout</a><%
    } else {
        %>Unregistered user!<%
    }
    %>
</p>
