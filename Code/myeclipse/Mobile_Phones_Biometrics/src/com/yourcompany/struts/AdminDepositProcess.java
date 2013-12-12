/*
* Class AdminDepositProcess
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

public class AdminDepositProcess extends Action {
		
		Connection con;
		Statement stmt;
		ResultSet rs;
		String uname,pass,gender,emailid,contactno,accno,userid,succ;
		String balance,dbuserid,newbal;
		int bvalue;
		
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		DynaValidatorForm loginForm = (DynaValidatorForm) form;
		String accno = loginForm.get("adminaccnumber").toString();
		String abranch = loginForm.get("adminbranch").toString();
		String amount = loginForm.get("adminmoney").toString();
        
        System.out.println("accno....."+accno);
        System.out.println("abranch....."+abranch);
        System.out.println("amount....."+amount);
        
        HttpSession session = request.getSession(true);
        dbconnection dbconn=new dbconnection();
		con=dbconn.getConnect();
		Vector vc=new Vector();
		Statement stat=con.createStatement();
        	
    		if(true){
            	System.out.println("LOGIN CORRECT");
        		ResultSet rs1=stat.executeQuery("select * from MPB_CLIENTREGISTER where ACCNO='"+accno+"'");
        		while(rs1.next()){
        			balance=rs1.getString("BALANCE");
        		}
        		bvalue=Integer.parseInt(amount)+Integer.parseInt(balance);
        		ResultSet rs3=stat.executeQuery("update MPB_CLIENTREGISTER set BALANCE='"+bvalue+"' where ACCNO='"+accno+"'");
        		ResultSet rs4=stat.executeQuery("update MPB_CLIENTREGISTER set BANKBRANCH='"+abranch+"' where ACCNO='"+accno+"'");
        		succ="SUCCESSFULLY DEPOSITED AMOUNT";
    		}
    		session.setAttribute("depositsuccess",succ);
        	return mapping.findForward("depositsuccess");    	
	} 
}