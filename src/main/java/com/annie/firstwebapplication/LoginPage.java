package com.annie.firstwebapplication;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "loginpage",value="/login-page")

public class LoginPage extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

//        System.out.println(request.getParameter("login"));

            String user_name=request.getParameter("userName");
            String user_pwd= request.getParameter("pwd");

            dataBaseConnectivity dbc = new dataBaseConnectivity(user_name,user_pwd);
            try {
                boolean valid_user=dbc.checkUserDetails();
                if (!valid_user){
                    System.out.println("InValid user true condition login page");
                    RequestDispatcher rd1 = request.getRequestDispatcher("Login.html");
                    rd1.forward(request,response);
                }else {
                    System.out.println("valid user fasle condition emp page");
                    RequestDispatcher rd1 = request.getRequestDispatcher("EmployeeMgt.html");
                    rd1.forward(request,response);

                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
}
