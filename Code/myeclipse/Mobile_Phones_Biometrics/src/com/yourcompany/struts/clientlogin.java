package com.yourcompany.struts;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.swt.program.Program;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;
import org.apache.struts.validator.DynaValidatorForm;

public class clientlogin extends org.apache.struts.action.Action 
{
	Connection con;
	Statement stmt;
	ResultSet rs;
	String val1="",val2="";
	PreparedStatement ps;
	Base64 base64=new Base64();
	String tottot="",dbvalue="";
	final static int IMG_WIDTH = 92;
	final static int IMG_HEIGHT = 112;
	BufferedImage original, grayscale, binarized;
	native int addTwoNos(int a,int b);
	native int reigsterAssemblyHandler(String str);
    @SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try{
    	String[] userid;
    	String[] accno;
    	String recvalue="",val="",imgpathtotal1="",pp="",dbimgname="",pp1="";
    	FileInputStream fis1 = null;
    	File f=new File("");
    	File f1=new File("");
    	FormFile file=null, file1=null;
    	String dbpath11="";
    	RandomCreate rm=new RandomCreate();
    	String newuserid="",smsusername="9962446876",smspassword="9962446876",newpass="",newpassencrypted="",newaccno="",dbcont="",dbname="MPB_CLIENTREGISTER",dbid="",dbacc="",dbimgpath="",dbpath1="",imgpath="";
    	Encryption encry=new Encryption();	
    	DynaValidatorForm dvf=(DynaValidatorForm)form;
    	MultipartRequestHandler mrh=dvf.getMultipartRequestHandler();
    	dbconnection dbconn=new dbconnection();
		con=dbconn.getConnect();
		
    	if(mrh!=null){
	    	Hashtable<String,FormFile> ht= mrh.getFileElements();
	    	Hashtable<String,Object> ht1=mrh.getTextElements();
	    	System.out.println(ht);
	    	Set<String> set=ht.keySet();
	    	Set<String> set1=ht1.keySet();
	    	System.out.println(set);
	    	System.out.println(set1);
	    	
	    	if(set1!=null){
	    		Iterator<String> itr1=set1.iterator();
	    		while(itr1.hasNext()){
	    			String fieldname=itr1.next();
	    			if(fieldname.equals("userid")){
	    				userid=(String[])ht1.get(fieldname);
	    				for(int i=0;i<userid.length;i++)
	    				{
	    					newuserid=userid[i].toString();
	    				}
	    			}
	    			else if(fieldname.equals("accno")){
	    				accno=(String[])ht1.get(fieldname);
	    				for(int i=0;i<accno.length;i++)
	    				{
	    					newaccno=accno[i].toString();
	    				}
	    			}
	    		}
	    	}
	    	
	    	
	    	if(set!=null){
	    		Iterator<String> itr=set.iterator();
	    		while(itr.hasNext()){
	    			file=ht.get(itr.next());
	    			f=new File("CAPTUREDIMAGES/"+newuserid+".jpg");
	    			pp="CAPTUREDIMAGES/"+newuserid+".jpg";
	    			FileOutputStream fos=new FileOutputStream(f);	    			
	    			byte[] b=file.getFileData();
	    			System.out.println(b.length);
	    			fos.write(b);
	    			fos.close();
	    			
	    			/*file1=ht.get(itr.next());
	    			f1=new File("D:/CAPTURED_IMAGES/input.jpg");
	    			pp1="D:/CAPTURED_IMAGES/"+"input.jpg";
	    			FileOutputStream fos1=new FileOutputStream(f1);	    			
	    			byte[] b1=file1.getFileData();
	    			System.out.println(b1.length);
	    			fos1.write(b1);
	    			fos1.close();*/
	    			
	    			File original_f = new File("CAPTUREDIMAGES/"+newuserid+".jpg");
	        		String format = newuserid+".jpg";
	        		original = ImageIO.read(original_f);
	        		grayscale = toGray(original);
	        		writeImage(grayscale,format,newuserid);
	        		
	        		File sourceimage = new File("E:/DATABASE_IMAGES/"+newuserid+"/"+newuserid+".jpg");
	        		if(!sourceimage.exists()){
	        			System.out.println("+++++++FAKE-USER+++++++");
	        	    	String result1 = "FAKE USER";
	        	    	PrintWriter pw1;
	        			pw1 = response.getWriter();
	        			pw1.write("<result>");
	        	        pw1.write(result1);
	        	        pw1.write("</result>");
	        	        pw1.close();
	        		}
	        		String format1 = newuserid+".jpg";
	        		original = ImageIO.read(sourceimage);
	        		writeImage1(original,format1,newuserid);
	    		}
	    	}
	    	
	    	Program.launch("Run.bat");
	    	Thread.sleep(10000);
	    			
    		stmt=con.createStatement();
    		String dbvalue = "";
            ResultSet rs = stmt.executeQuery("select * from FACEVALIDATION where USERID='"+newuserid+"'");
            Boolean result=rs.next();
	        if(result){
	        	dbvalue = rs.getString("VALUE");
			}
	        ResultSet rs1 = stmt.executeQuery("select * from MPB_CLIENTREGISTER where USERID='"+newuserid+"' and ACCNO='"+newaccno+"'");
            Boolean result11=rs1.next();
	        if(result11){
	           if(dbvalue.equals("TRUE")){
		        	System.out.println("+++++++USER RECOGNIZED+++++++");
			    	String result1 = "USER RECOGNIZED";
			    	PrintWriter pw1=response.getWriter();
			    	pw1.write("<result>");
			        pw1.write(result1);
			        pw1.write("</result>");
			        pw1.close();
	            }
	           else if(dbvalue.equals("FALSE")){
		        	System.out.println("+++++++USER NOT RECOGNIZED+++++++");
			    	String result1 = "USER NOT RECOGNIZED";
			    	PrintWriter pw1=response.getWriter();
			    	pw1.write("<result>");
			        pw1.write(result1);
			        pw1.write("</result>");
			        pw1.close();
		        }
	        }
	        
	        else if(result11.equals("false")){
	        	System.out.println("+++++++FAKE USER+++++++");
		    	String result1 = "FAKE USER";
		    	PrintWriter pw1=response.getWriter();
		    	pw1.write("<result>");
		        pw1.write(result1);
		        pw1.write("</result>");
		        pw1.close();
	        }    	
		  }
    	
    	
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return null;
    }
    
    private static void createDir(String folderName) {
        File file = new File(folderName);
        if (!file.mkdir()) {
            throw new RuntimeException("Cannot Create Directory");
        }
    }
    
    private void writeImage(BufferedImage grayscale2, String format, String userid) throws IOException {
    	
    	File file = new File("CAPTURED_GRAYSCALE/");
        if (!file.isDirectory()) {
            createDir("CAPTURED_GRAYSCALE/");
        }
		File file1 = new File("CAPTURED_GRAYSCALE/"+format);
		ImageIO.write(grayscale2, "jpg", file1);		
		BufferedImage originalImage = ImageIO.read(new File("CAPTURED_GRAYSCALE/"+format));
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			
		BufferedImage resizeImagePng = resizeImage(originalImage, type);
		ImageIO.write(resizeImagePng, "jpg", new File("E:/INPUTIMAGE/"+"1.jpg"));
	}
    
private void writeImage1(BufferedImage original, String format, String userid) throws IOException {
    	
    	/*File file = new File("CAPTURED_GRAYSCALE/");
        if (!file.isDirectory()) {
            createDir("CAPTURED_GRAYSCALE/");
        }
		File file1 = new File("CAPTURED_GRAYSCALE/"+format);
		ImageIO.write(original, "jpg", file1);		
		BufferedImage originalImage = ImageIO.read(new File("CAPTURED_GRAYSCALE/"+format));
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			
		BufferedImage resizeImagePng = resizeImage(originalImage, type);*/
	
		ImageIO.write(original, "jpg", new File("E:/CHECK/"+format));
	}
    
    private static BufferedImage resizeImage(BufferedImage originalImage, int type){
    	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
    	Graphics2D g = resizedImage.createGraphics();
    	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
    	g.dispose();

    	return resizedImage;
        }
    
    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type){

    	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
    	Graphics2D g = resizedImage.createGraphics();
    	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
    	g.dispose();
    	g.setComposite(AlphaComposite.Src);

    	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
    	RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    	g.setRenderingHint(RenderingHints.KEY_RENDERING,
    	RenderingHints.VALUE_RENDER_QUALITY);
    	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    	RenderingHints.VALUE_ANTIALIAS_ON);

    	return resizedImage;
        }
    
	// The luminance method
	private BufferedImage toGray(BufferedImage original) {
		int alpha, red, green, blue;
		int newPixel;
		BufferedImage lum = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
		for(int i=0; i<original.getWidth(); i++) {
			for(int j=0; j<original.getHeight(); j++) {
				// Get pixels by R, G, B
				alpha = new Color(original.getRGB(i, j)).getAlpha();
				red = new Color(original.getRGB(i, j)).getRed();
				green = new Color(original.getRGB(i, j)).getGreen();
				blue = new Color(original.getRGB(i, j)).getBlue();

				red = (int) (0.21 * red + 0.71 * green + 0.07 * blue);
				// Return back to original format
				newPixel = colorToRGB(alpha, red, red, red);

				// Write pixels into image
				lum.setRGB(i, j, newPixel);
			}
		}
		return lum;
	}
	
	// Convert R, G, B, Alpha to standard 8 bit
	private int colorToRGB(int alpha, int red, int green, int blue) {
		int newPixel = 0;
		newPixel += alpha;
		newPixel = newPixel << 8;
		newPixel += red; newPixel = newPixel << 8;
		newPixel += green; newPixel = newPixel << 8;
		newPixel += blue;
		return newPixel;
	}
  }
