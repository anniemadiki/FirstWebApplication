package com.annie.firstwebapplication;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "updateemployee",value="/update_employee")
public class UpdateEmployee extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd1 = request.getRequestDispatcher("UpdateServlet.html");
        rd1.forward(request,response);

        int emp_id= Integer.parseInt(request.getParameter("empId"));
        System.out.println(request.getParameter("empId"));
        String emp_name=request.getParameter("empName");
        double emp_sal= Double.parseDouble(request.getParameter("empsalary"));

        employeeManagment em=new employeeManagment(emp_id,emp_name,emp_sal);
        try {
            em.updateEmployee();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
