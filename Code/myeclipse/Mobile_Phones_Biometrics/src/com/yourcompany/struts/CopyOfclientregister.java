package com.yourcompany.struts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;
import org.apache.struts.validator.DynaValidatorForm;

public class CopyOfclientregister extends org.apache.struts.action.Action 
{
	Connection con,con1;
	Statement stmt;
	ResultSet rs;
	PreparedStatement ps,ps1;

    @SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("---------");
    	String[] username;
    	String[] password;
    	String[] gender;
    	String[] email;
    	String[] contactnumber;
    	String image="";
    	String imgpath="";
    	PrintWriter pw=response.getWriter();
    	String newuname="",newupass="",ugend="",umail="",ucontno="",uuname="",uupass="",passcheck="",passchosen="";
    	String accno="",userid="",balance=String.valueOf(0),branch="";
    	dbconnection dbconn=new dbconnection();
		con=dbconn.getConnect();
		RandomCreate rc=new RandomCreate();
		Boolean status=true;
		
    	DynaValidatorForm dvf=(DynaValidatorForm)form;
    	MultipartRequestHandler mrh=dvf.getMultipartRequestHandler();
    	Hashtable<String,FormFile> ht= mrh.getFileElements();
    	Set<String> set=ht.keySet();
    	System.out.println(set);
    	if(set!=null){
    		Iterator<String> itr=set.iterator();
    		while(itr.hasNext()){
    			FormFile file=ht.get(itr.next());
    			File f=new File("USER REGISTERED IMAGES/"+file.getFileName());
    			imgpath=f.getAbsolutePath();
    			FileOutputStream fos=new FileOutputStream(f);
    			byte[] b=file.getFileData();
    			System.out.println(b.length);
    			fos.write(b);
    			fos.close();
    		}
    	}
    			
    		System.out.println("New Pass"+newupass);
    		Statement stat=con.createStatement();
    		ResultSet rs1=stat.executeQuery("select * from MPB_CLIENTREGISTER where PASSWORD='"+newupass+"'");
    		Boolean bolval=rs1.next();
    		System.out.println("bolval is.."+bolval);
    		if(bolval)
    		{	
            	pw.write("<success>");
            	pw.write("<accid>");
            	pw.write(passcheck);
            	pw.write("</accid>");
            	pw.write("<userid>");
            	pw.write(passchosen);
            	pw.write("</userid>");
            	pw.write("</success>");
    		}
    		
    		else
    		{
    		ps=con.prepareStatement("insert into MPB_clientregister values(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, newuname);
			ps.setString(2, newupass);
			ps.setString(3, ugend);
			ps.setString(4, umail);
			ps.setString(5, ucontno);
			ps.setString(6, imgpath);
			ps.setString(7, accno);
			ps.setString(8, userid);
			ps.setString(9, balance);
			ps.setString(10, branch);
			int va=ps.executeUpdate();
			ps.close();
			if(va==1){
				System.out.println("true");
			}
			else{
				System.out.println("false");
			}
			
        	pw.write("<success>");
        	pw.write("<accid>");
        	pw.write(accno);
        	pw.write("</accid>");
        	pw.write("<userid>");
        	pw.write(userid);
        	pw.write("</userid>");
        	pw.write("</success>");
    		}
    	
    	pw.close();
	return null;
  }
}