package com.annie.firstwebapplication;

import java.sql.*;
import java.util.Objects;

import static java.lang.System.out;

public class employeeManagment {

    private int empId;
    private String empName;
    private double empSalary;

    static Connection con = null;
    private String selectQuery=null;
    private static PreparedStatement pstmt=null;
    private ResultSet res1;
    private int count;
    private boolean rec_found=false;


    public employeeManagment(int empId,String empName,double empSalary) {
        out.println("constructor --------"+empId+"----"+empName+"----"+empSalary);
        this.empId = empId;
        this.empName=empName;
        this.empSalary=empSalary;
    }

    static {
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

    }
    public boolean addEmployee() throws SQLException {

        out.println("inadd employee");

        selectQuery= "select * from employee where emp_id=('"+this.empId+"')";
        out.println(selectQuery);

        pstmt = con.prepareStatement(selectQuery);
        out.println("after add employee pstmt");
        res1 =pstmt.executeQuery(selectQuery);

        while (res1.next()) {
            out.println("Employee with Employee ID "+this.empId+"already exists");
            return false;
        }

        selectQuery= "insert into employee values ("+this.empId+", '"+this.empName+"', "+this.empSalary+");";
        out.println(selectQuery);

        pstmt = con.prepareStatement(selectQuery);
        out.println("after pstmt");
        count =pstmt.executeUpdate(selectQuery);
        if(count<=0){
        return false;
        }
        else return true;

    }


    public boolean updateEmployee() throws SQLException {

        out.println("in update employee");

        selectQuery= "select * from employee where emp_id=('"+this.empId+"')";
        out.println(selectQuery);

        pstmt = con.prepareStatement(selectQuery);
        out.println("after pstmt");
        res1 =pstmt.executeQuery(selectQuery);

        while (res1.next()) {
            selectQuery="update employee set emp_name='"+this.empName+"', emp_salary="+this.empSalary+" where emp_id=" + this.empId+";";
            out.println(selectQuery);

            pstmt = con.prepareStatement(selectQuery);
            out.println("after pstmt");
            count =pstmt.executeUpdate(selectQuery);
            return true;
        }
           out.println("Employee with Employee ID "+this.empId+"does not exists");
           return false;
    }


    public boolean deleteEmployee() throws SQLException {

        out.println("in delete employee");

        selectQuery= "select * from employee where emp_id=('"+this.empId+"')";
        out.println(selectQuery);

        pstmt = con.prepareStatement(selectQuery);
        out.println("after pstmt");
        res1 =pstmt.executeQuery(selectQuery);

        while (res1.next()) {
            selectQuery="delete from  employee where emp_id=" + this.empId+";";
            out.println(selectQuery);

            pstmt = con.prepareStatement(selectQuery);
            out.println("after pstmt");
            count =pstmt.executeUpdate(selectQuery);
            return true;
        }
        out.println("Employee with Employee ID "+this.empId+"does not exists");
        return false;
    }


    public boolean displayEmployee() throws SQLException {

        out.println("in display employee");


        selectQuery= "select * from employee";
        out.println(selectQuery);

        pstmt = con.prepareStatement(selectQuery);
        out.println("after pstmt");
        res1 =pstmt.executeQuery(selectQuery);

        while (res1.next()) {
            int n = res1.getInt("empid");
            String nm = res1.getString("empname");
            double s = res1.getDouble("sal");
            out.println("<tr><td>" + n + "</td><td>" + nm + "</td><td>" + s + "</td></tr>");
            return true;
        }

        return false;
    }
}