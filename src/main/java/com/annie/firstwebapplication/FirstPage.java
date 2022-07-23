package com.annie.firstwebapplication;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Date;

@WebServlet(name = "firstpage",value="/first-page")
public class FirstPage extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

        PrintWriter pw= null;
        try{
            String malePrefix="Hello Mr.";
            String femalePrefix="Hello Mrs.";
            String timePrefix = null;
            pw=response.getWriter();
            pw.println("Below is User Information page Servlet");
            pw.println();


            String first_name=request.getParameter("fname");
            String last_name= request.getParameter("lname");
            String genderValue =request.getParameter("gender");

            Date todayDate = new Date();
            pw.println("\t \t \t *10 Date: "+ todayDate.getDate()+" "+todayDate.getMonth()+" "+todayDate.getYear());
            int hours=todayDate.getHours();
            pw.println("Time: " +todayDate.getHours()+":"+todayDate.getMinutes()+":"+ todayDate.getSeconds());
            pw.println();
            pw.println();

            if (hours>= 8 && hours<= 11){
                timePrefix = "Good Morning";
            } else if (hours>= 12 && hours<= 16) {
                timePrefix = "Good Afternoon";
            } else if (hours>= 16 && hours<= 20) {
                timePrefix = "Good Evening";
            }else {
                timePrefix = "Good Night";
            }

            if (genderValue.toLowerCase().equals("m")){
                pw.println(malePrefix+first_name+" "+last_name+"....."+timePrefix);
            }else{
                pw.println(femalePrefix+first_name+" "+last_name+"....."+timePrefix);
            }


//            System.out.println(first_name +"----"+last_name+"----"+genderValue);
//            System.out.println("b4 if condition-"+genderValue);

//            pw.println(request.getAttribute("fname"));
//
//            pw.println("First Name = "+first_name);
//            pw.println("");
//            pw.println("Last Name = "+last_name);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
