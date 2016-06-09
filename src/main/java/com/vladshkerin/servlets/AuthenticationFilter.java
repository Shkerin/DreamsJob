package com.vladshkerin.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The class of the servlet for user authentication.
 *
 * @author vlad
 * @since 06.06.2016
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //empty
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        if (req.getRequestURI().contains("login")) {
            chain.doFilter(request, response);
        } else if (session == null || session.getAttribute("CURRENT_USER") == null) {
            request.getRequestDispatcher("/WEB-INF/views/Login1.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        //empty
    }
}
