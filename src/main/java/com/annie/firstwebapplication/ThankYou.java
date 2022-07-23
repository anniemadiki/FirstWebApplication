package com.annie.firstwebapplication;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "ThankYou",value="/thankyou_page")
public class ThankYou extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("inside thankyou class");
        RequestDispatcher rd1 = request.getRequestDispatcher("ThankYou.html");
        rd1.forward(request,response);
    }
}
