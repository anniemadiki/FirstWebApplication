package com.annie.firstwebapplication;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet(name = "displayemployee",value="/display_employees")
public class DisplayAllEmployees extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        int emp_id=0;
        String emp_name=null;
        double emp_sal=0;
        Connection con=null;

        PrintWriter pw = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_schema","root","password123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String selectQuery= "select * from employee";

        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(selectQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        pw.println("<body style='background-color:lightcoral;'>");
        pw.println("<center><h1>Below are details of all Employees in DataBase</h1></center>");


        pw.println("<table>" +
                "<tr>" +
                "<th>Employee ID</th>" +
                "<th>Employee Name</th>" +
                "<th>Employee Salary</th>" +
                "</tr>");

        try {
            ResultSet res1 =pstmt.executeQuery(selectQuery);

            while (res1.next())
            {
                int n = res1.getInt(1);
                String nm = res1.getString(2);
                double s = res1.getDouble(3);
                pw.println("<tr><td>" + n + "</td><td>" + nm + "</td><td>" + s + "</td></tr>");
                pw.println(" ");
                pw.println(" ");
//                pw.println("</table>");
//                pw.println("</html></body>");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
