package com.yourcompany.struts;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

public class transaction extends Action {
		
		Connection con;
		Statement stmt;
		ResultSet rs;
		String balance,balance1,dbuserid,userid,contno;
		PreparedStatement ps;
		String vval;
		
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		DynaValidatorForm loginForm = (DynaValidatorForm) form;
        String senderid = loginForm.get("senderid").toString();
        String bankname = loginForm.get("bankname").toString();
        String accno = loginForm.get("accno").toString();
        String branchname = loginForm.get("branchname").toString();
        String amount = loginForm.get("amount").toString();
        
        dbconnection dbconn=new dbconnection();
		con=dbconn.getConnect();
        try{
        	System.out.println("SENDER IS: "+senderid);
        	System.out.println("BANKNAME IS: "+bankname);
        	System.out.println("ACCNO IS: "+accno);
        	System.out.println("BRANCHNAME IS: "+branchname);
        	System.out.println("AMOUNT IS: "+amount);
        	
        	Statement stat=con.createStatement();
    		ResultSet rs1=stat.executeQuery("select * from MPB_CLIENTREGISTER where USERID='"+senderid+"'");
    		while(rs1.next()){
    			balance=rs1.getString("BALANCE");
    			userid=rs1.getString("USERID");
    			contno=rs1.getString("CONTACTNO");
    		}
    		int newbal=Integer.parseInt(balance)-Integer.parseInt(amount);
    		ps=con.prepareStatement("update MPB_CLIENTREGISTER set BALANCE='"+String.valueOf(newbal)+"' where USERID='"+senderid+"'");
			ps.executeUpdate();
			
			if(bankname.equalsIgnoreCase("INDIANBANK")){
				Statement stat1=con.createStatement();
	    		ResultSet rs2=stat.executeQuery("select * from "+bankname+" where ACCNO='"+accno+"'");
	    		while(rs2.next()){
	    			balance1=rs2.getString("BALANCE");
	    		}
	    		//String mobilemessage="Hello "+senderid+". Credited an amount of Rs."+amount+" from the account number ACC******/BANK NAME: "+bankname+". For further details login to https://online.citibank.com/US/Welcome.c";                 
	    		//SendSMS sms=new SendSMS("9962446876","9962446876",contno,mobilemessage);  
	    		
	    		int addedvalue=Integer.parseInt(balance1)+Integer.parseInt(amount);
	    		ps=con.prepareStatement("update "+bankname+" set BALANCE='"+String.valueOf(addedvalue)+"' where ACCNO='"+accno+"'");
				ps.executeUpdate();
				}			
				vval="Your current balance is "+newbal;	
				if(senderid.equals(userid)){
				PrintWriter pw1=response.getWriter();
		    	pw1.write("<newbalance>");
		        pw1.write(vval);
		        pw1.write("</newbalance>");
		        pw1.close();
			}
			else if(bankname.equalsIgnoreCase("STATEBANK")){
				Statement stat1=con.createStatement();
	    		ResultSet rs2=stat.executeQuery("select * from "+bankname+" where ACCNO='"+accno+"'");
	    		while(rs2.next()){
	    			balance1=rs2.getString("BALANCE");
	    		}
	    		String mobilemessage="Hello "+senderid+". Credited an amount of Rs."+amount+" from the account number ACC******/BANK NAME: "+bankname+". For further details login to https://online.citibank.com/US/Welcome.c";                 
	    		SendSMS sms=new SendSMS("9962446876","9962446876",contno,mobilemessage);  
	    		
	    		int addedvalue=Integer.parseInt(balance1)+Integer.parseInt(amount);
	    		ps=con.prepareStatement("update "+bankname+" set BALANCE='"+String.valueOf(addedvalue)+"' where ACCNO='"+accno+"'");
				ps.executeUpdate();
				}			
				vval="Your current balance is "+newbal;	
				if(senderid.equals(userid)){
				PrintWriter pw1=response.getWriter();
		    	pw1.write("<newbalance>");
		        pw1.write(vval);
		        pw1.write("</newbalance>");
		        pw1.close();
			}
        }        
        catch(Exception e){
        	e.printStackTrace();
        }
        return null;
	}
}