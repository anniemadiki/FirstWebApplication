package com.annie.firstwebapplication;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "deleteemployee",value="/delete_employee")
public class DeleteEmployee extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd1 = request.getRequestDispatcher("DeleteServlet.html");
        rd1.forward(request,response);

        int emp_id= Integer.parseInt(request.getParameter("empId"));
        System.out.println(request.getParameter("empId"));
        String emp_name=null;
        double emp_sal=0;

        employeeManagment em=new employeeManagment(emp_id,emp_name,emp_sal);
        try {
            em.deleteEmployee();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
