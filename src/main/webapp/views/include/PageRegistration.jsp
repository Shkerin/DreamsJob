<%--
  The page displays information about user registration.
  
  @author Vladimir Shkerin
  @since 11.05.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="registration">
    <%
    if (session.getAttribute("login") != null) {
    %>
        User: <%=session.getAttribute("login")%>, Role: <%=session.getAttribute("role")%>).
        <a href="<%=request.getContextPath()%>/logout">logout</a>
    <%
    } else {
    %>
        Unregistered user!
    <%
    }
    %>

    <%--<%--%>
    <%--Object login = session.getAttribute("login");--%>
    <%--if (session.isNew() || login == null) {--%>
    <%--%>--%>
        <%--<h1>User registration</h1>--%>
        <%--<form action="<%=request.getContextPath()%>/login" method="post">--%>
            <%--login : <input type="text" name="login"><br>--%>
            <%--password : <input type="password" name="password"><br>--%>
            <%--<input type="submit">--%>
        <%--</form>--%>
    <%--<%--%>
    <%--} else {--%>
    <%--%>--%>
        <%--Welcome back, <%=session.getAttribute("login")%> (<%=session.getAttribute("role")%>).--%>
        <%--<a href="<%=request.getContextPath()%>/logout">logout</a>--%>
    <%--<%--%>
    <%--}--%>
    <%--%>--%>
</div>
