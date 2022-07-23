package com.annie.firstwebapplication;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Objects;

@WebServlet(name = "addemployee",value="/add_employee_main")
public class AddEmployee extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html");
//        RequestDispatcher rd1 = request.getRequestDispatcher("AddServlet.html");
//        rd1.forward(request,response);
        System.out.println("Add Employee response");

        int emp_id= Integer.parseInt(request.getParameter("empId"));
        System.out.println(request.getParameter("empId"));
        String emp_name=request.getParameter("empName");
        double emp_sal= Double.parseDouble(request.getParameter("empSalary"));
        System.out.println(emp_sal);
        PrintWriter pw=null;

        employeeManagment em=new employeeManagment(emp_id,emp_name,emp_sal);
        try {
            pw=response.getWriter();
            boolean result=em.addEmployee();
            System.out.println("result is ="+result);
            if (result){
                RequestDispatcher rd2 = request.getRequestDispatcher("ThankYou.html");
                System.out.println("testing"+request+"--"+response);
                rd2.forward(request,response);
                System.out.println("response back from thankyou html");
            }else
            {
                pw.println("User already exist. Please enter new user id");
                RequestDispatcher rd1 = request.getRequestDispatcher("AddServlet.html");
                rd1.include(request,response);

            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





}
