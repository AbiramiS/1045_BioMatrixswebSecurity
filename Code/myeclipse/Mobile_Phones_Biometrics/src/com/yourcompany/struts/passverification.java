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

public class passverification extends Action {
		
		Connection con;
		Statement stmt;
		ResultSet rs;
		String ucont;
		String succ="Validation Successfull";
		String succ1="",uuserid="",uimeino="";
		
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		DynaValidatorForm loginForm = (DynaValidatorForm) form;
        String sqlitevalue = loginForm.get("sqlitevalue").toString();
        String mobpass = loginForm.get("mobpass").toString();
        String mobimeino = loginForm.get("mobimeino").toString();
        
        dbconnection dbconn=new dbconnection();
		con=dbconn.getConnect();
        
        System.out.println("mobvalue......"+sqlitevalue);
        System.out.println("mobpassword......"+mobpass);
        System.out.println("mobimeino......"+mobimeino);
        try {
	        Statement stat=con.createStatement();
			ResultSet rs1=stat.executeQuery("select * from MPB_CLIENTREGISTER where  PASSWORD='"+mobpass+"'");
			while(rs1.next()){
				uimeino=rs1.getString("IMEINO");
				uuserid=rs1.getString("USERID");
			}		
			if(mobimeino.equals(uimeino) && sqlitevalue.equals(uimeino)){
				PrintWriter pw1=response.getWriter();
		    	pw1.write("<successfull>");
		        pw1.write(uuserid);
		        pw1.write("</successfull>");
		        pw1.close();
			}
			else if(!mobimeino.equals(uimeino) && !sqlitevalue.equals(uimeino)){
				PrintWriter pw1=response.getWriter();
		    	pw1.write("<successfull>");
		        pw1.write(succ1);
		        pw1.write("</successfull>");
		        pw1.close();
			}
        }
        catch(Exception e){
        	e.printStackTrace();
        }
		return null;
	}
}