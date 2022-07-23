package com.annie.firstwebapplication;

import javax.servlet.RequestDispatcher;
import java.sql.*;
import java.util.Objects;


public class dataBaseConnectivity {

    static Connection con = null;
    private String user_name=null;
    private String user_pwd=null;
    private String selectQuery=null;
    private static PreparedStatement pstmt=null;
    private ResultSet res1;
    private boolean rec_found= false;


    public dataBaseConnectivity(String user_name, String user_pwd) {
        this.user_name = user_name;
        this.user_pwd = user_pwd;
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

    public boolean checkUserDetails() throws SQLException {

        System.out.println("inside check user para");

        selectQuery= "select * from User_list where user_name=('"+this.user_name+"')";
        System.out.println(selectQuery);

        pstmt = con.prepareStatement(selectQuery);
        System.out.println("after login pstmt");
        ResultSet res1 =pstmt.executeQuery(selectQuery);

        while (res1.next()) {
            rec_found=true;
            if (!(Objects.equals(this.user_pwd, res1.getString(2)))){
                System.out.println("Password is incorrect. Please check and retry");
                return false;
            }else {
                System.out.println("Valid User");
                return true;
            }
        }
        if(!rec_found){
            System.out.println("Given record not found in database");
        }
        return false;
    }



    public boolean addNewUser() throws SQLException {

        System.out.println("inside chkusr para");

        selectQuery= "insert into User_list values ('"+this.user_name+"',"+this.user_pwd+"';)";
        System.out.println(selectQuery);

        pstmt = con.prepareStatement(selectQuery);
        System.out.println("after pstmt");
        int result=pstmt.executeUpdate();

        if(result<=0){
            System.out.println("Record was not added to Database");
            return false;
//            send error message with same screen
        }
        return true;
    }
}