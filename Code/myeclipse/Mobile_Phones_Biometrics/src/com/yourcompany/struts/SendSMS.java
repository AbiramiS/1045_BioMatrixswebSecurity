package com.yourcompany.struts;
import java.io.InputStream;
import java.net.*;
import java.net.URLConnection;
import java.util.Date;

public class SendSMS{
	public SendSMS(String user,String pass,String number,String mess) {

	try{
		int c;
		URL url = new URL("http://ubaid.tk/sms/sms.aspx?uid="+user+"&pwd="+pass+"&msg="+mess+"&phone="+number+"&provider=way2sms");
		URI uri = new URI(url.getProtocol(), url.getAuthority(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
		URL hp=new URL(uri.toString());
		URLConnection hpCon = hp.openConnection();
		System.out.println("Date: " + new Date(hpCon.getDate()));
		System.out.println("Content-Type: " +
		hpCon.getContentType());
		System.out.println("Expires: " + hpCon.getExpiration());
		System.out.println("Last-Modified: " +
		new Date(hpCon.getLastModified()));
		int len = hpCon.getContentLength();
		System.out.println("Content-Length: " + len);
		if (len > 0)
		{
		System.out.println("=== Content ===");
		InputStream input = hpCon.getInputStream();
		int ii = len;
		while (((c = input.read()) != -1) && (ii > 0))
		{
		System.out.print((char) c);
		}		
		input.close();
		System.out.println("Msg Sent Successfully........");
		}
		else
		{
		System.out.println("No Content Available");
		}
        String data=new String();
  	}
	catch(Exception e1){
		e1.printStackTrace();
   	}
}

}
