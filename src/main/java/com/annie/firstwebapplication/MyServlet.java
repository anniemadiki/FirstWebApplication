package com.annie.firstwebapplication;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "Annie", value = "/annie-servlet")
public class MyServlet implements Servlet {

    private String message;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.printf("This is init method");
        message="Hello Welcome !!!";
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        try{
            PrintWriter pw = null;
            try{
                pw=servletResponse.getWriter();
                pw.println("Good Morning");
            }catch (Exception e1){
                System.out.println();
            }

        }catch (Exception e){
            System.out.println("Error");

        }

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("This is destroy method");

    }
}
