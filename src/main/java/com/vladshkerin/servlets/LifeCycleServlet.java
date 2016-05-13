package com.vladshkerin.servlets;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Demonstration of life cycle of servlet
 *
 * @author Vladimir Shkerin
 * @since 13.05.2016
 */
public class LifeCycleServlet implements Servlet {

    private ServletConfig servletConfig;

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("***** init() *****");

        servletConfig = config;

        ServletContext servletContext = config.getServletContext();
        servletContext.setAttribute("password", "drowssap");

        Enumeration parameters = config.getInitParameterNames();
        while (parameters.hasMoreElements()) {
            String parameter = (String) parameters.nextElement();
            System.out.println("Parameter name  : " + parameter);
            System.out.println("Parameter value : " + config.getInitParameter(parameter));
        }
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("***** service() *****");

        ServletContext servletContext = servletConfig.getServletContext();
        Enumeration attributes = servletContext.getAttributeNames();
        while (attributes.hasMoreElements()) {
            String attribute = (String) attributes.nextElement();
            if ("password".equals(attribute)) {
                System.out.println("Attribute name  : " + attribute);
                System.out.println("Attribute value : " + servletContext.getAttribute(attribute));
            }
        }
        System.out.println("Major version : " + servletContext.getMajorVersion());
        System.out.println("Minor version : " + servletContext.getMinorVersion());
        System.out.println("Servlet info  : " + servletContext.getServerInfo());
    }

    @Override
    public void destroy() {
        System.out.println("***** destroy() *****");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }
}
