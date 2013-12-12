/*
* Class AdminProcess
*
* Version 1.2
*
* Copyright owned by Vinodh Kumar. R (Global Techno Solutions)
*/
package com.yourcompany.struts;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

public class AdminProcess extends Action {
		
		Connection con;
		Statement stmt;
		ResultSet rs;
		String uname,pass,gender,emailid,contactno,accno,userid;
		String balance,dbuserid;
		
		
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		DynaValidatorForm loginForm = (DynaValidatorForm) form;
		String aname = loginForm.get("adminname").toString();
        String apassword = loginForm.get("adminpassword").toString();
        HttpSession session = request.getSession(true);
        dbconnection dbconn=new dbconnection();
		con=dbconn.getConnect();
		Vector vc=new Vector();
        	
    		
    		if(aname.equalsIgnoreCase("admin") && apassword.equalsIgnoreCase("admin"))
    		{
            	System.out.println("LOGIN CORRECT");
            	Statement stat=con.createStatement();
        		ResultSet rs1=stat.executeQuery("select * from MPB_CLIENTREGISTER");
        		while(rs1.next()){
        			userid=rs1.getString("USERID");
        			vc.add(userid);
        		}
        		session.setAttribute("useridvalues",vc);
            	return mapping.findForward("adminloginsuccess");

    		}
            else
            {
            	System.out.println("LOGIN NOT CORRECT");
            	return mapping.findForward("adminloginfailure");
            }
	}
}