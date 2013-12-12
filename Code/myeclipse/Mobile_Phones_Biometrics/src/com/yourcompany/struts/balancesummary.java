package com.yourcompany.struts;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

public class balancesummary extends Action {
		
		Connection con;
		Statement stmt;
		ResultSet rs;
		String balance,dbuserid;
		
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		DynaValidatorForm loginForm = (DynaValidatorForm) form;
        String balance = loginForm.get("balance").toString();
        String userid = loginForm.get("userid").toString();
        
        dbconnection dbconn=new dbconnection();
		con=dbconn.getConnect();
        try
        {
        	Statement stat=con.createStatement();
    		ResultSet rs1=stat.executeQuery("select * from MPB_CLIENTREGISTER where  USERID='"+userid+"'");
    		while(rs1.next()){
    			dbuserid=rs1.getString("USERID");
    			balance=rs1.getString("BALANCE");
    		}
    		System.out.println("balance is..."+balance);
    		String newbal="Your Balance is "+balance;
    		if(dbuserid.equals(userid)){
    			PrintWriter pw1=response.getWriter();
    	    	pw1.write("<balance>");
    	        pw1.write(newbal);
    	        pw1.write("</balance>");
    	        pw1.close();
    		}
        }
       
        catch(Exception e)
        {
        	e.printStackTrace();
        }
         
		return null;
	}
}