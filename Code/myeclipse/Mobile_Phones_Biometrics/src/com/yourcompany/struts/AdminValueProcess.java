/*
* Class AdminValueProcess
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

public class AdminValueProcess extends Action {
		
		Connection con;
		Statement stmt;
		ResultSet rs;
		String uname,pass,gender,emailid,contactno,accno,userid,bal,branch;
		String balance,dbuserid;
		
		
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		DynaValidatorForm loginForm = (DynaValidatorForm) form;
		String adminidvalue = loginForm.get("adminidvalue").toString();
		System.out.println("adminidvalue is..."+adminidvalue);
        HttpSession session = request.getSession(true);
        dbconnection dbconn=new dbconnection();
		con=dbconn.getConnect();
		Vector vcval=new Vector();
		String str=null;
            	Statement stat=con.createStatement();
        		ResultSet rs1=stat.executeQuery("select * from MPB_CLIENTREGISTER WHERE USERID='"+adminidvalue+"'");
        		while(rs1.next()){
        			uname=rs1.getString("USERNAME");
        			gender=rs1.getString("GENDER");
        			emailid=rs1.getString("EMAILID");
        			contactno=rs1.getString("CONTACTNO");
        			accno=rs1.getString("ACCNO");
        			bal=rs1.getString("BALANCE");
        			branch=rs1.getString("BANKBRANCH");
        			str=uname+"//"+gender+"//"+emailid+"//"+contactno+"//"+accno+"//"+bal+"//"+branch;
        			vcval.add(str);
        			System.out.println("vc1 is\n"+vcval);
        		}
        		    session.setAttribute("idtotalvalues",vcval);
                	return mapping.findForward("admintotalvalue");
		}
	
}