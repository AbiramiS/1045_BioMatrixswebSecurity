
package com.yourcompany.struts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;
import org.apache.struts.validator.DynaValidatorForm;

public class SignatureVerificationOne extends org.apache.struts.action.Action 
{
	Connection con,con1;
	Statement stmt;
	ResultSet rs;
	PreparedStatement ps,ps1;
	private SignatureData signatureDataTest;
	public Enroll enroll = new Enroll();
	public MessageDigestCalculator messageDigestCalculator = new MessageDigestCalculator();
    private LinkedList<Double> xt = new LinkedList<Double>();
    private LinkedList<Double> yt = new LinkedList<Double>();
    private double xval1,yval1;
	Base64a base64=new Base64a();
    @SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception,IOException {
		
    	String[] positions;
    	String[] uservalues;
    	String[] uid;
    	String[] uploadedFile;
    	String[] password;
    	String[] gender;

		String aac="",ause="",a1="",a2="";
		
    	double aDouble, bDouble;
    	String[] email;
    	String[] contactnumber;
    	String[] proof11;
    	String[] proof112;
    	String image="";
    	String imgcontent="",imgpathnew="";
    	String sorry="***",user="***";
    	PrintWriter pw=response.getWriter();
    	String newuname="",uploadedFile1="",response1="",positionvalues="",positionvaluesnew="",newupass="",ugend="",umail="",ucontno="",proof="",proof66="",uuname="",uupass="",passcheck="",passchosen="";
    	String accno="",userid="",balance=String.valueOf(0),branch="";
    	dbconnection dbconn=new dbconnection();
		con=dbconn.getConnect();
		RandomCreate rc=new RandomCreate();
		Boolean status=true;
		try{
    	DynaValidatorForm dvf=(DynaValidatorForm)form;
    	MultipartRequestHandler mrh=dvf.getMultipartRequestHandler();
    	Hashtable<String,Object> ht1= mrh.getTextElements();
    	Set<String> set1=ht1.keySet();
    	if(set1!=null){
    		Iterator<String> itr1=set1.iterator();
    		while(itr1.hasNext()){
    			String fieldname=itr1.next();
    			if(fieldname.equals("signaturefile")){
    				uploadedFile=(String[])ht1.get(fieldname);
    				for(int i=0;i<uploadedFile.length;i++)
    				{
    					imgcontent=uploadedFile[i].toString();
    				}
    			}
    			else if(fieldname.equals("positionvalues")){
    				positions=(String[])ht1.get(fieldname);
    				for(int i=0;i<positions.length;i++)
    				{
    					positionvalues=positions[i].toString();
    				}
    			}
    			else if(fieldname.equals("useridinfo")){
    				uid=(String[])ht1.get(fieldname);
    				for(int i=0;i<uid.length;i++)
    				{
    					response1=uid[i].toString();
    				}
    			}
    		}
    		
    		System.out.println("\n\n"+response1+"\n\n");
    		StringTokenizer st11=new StringTokenizer(response1, "\n");
    		while(st11.hasMoreTokens()){
    			aac=st11.nextToken();
    			ause=st11.nextToken();
    			StringTokenizer st112=new StringTokenizer(ause, ":");
        		while(st112.hasMoreTokens()){
        			a1=st112.nextToken();
        			a2=st112.nextToken();       			
        		}
    		}
    		
    		
    		
    		//---------------------------------------
    		int count=0;int count1=0;
    		for(int k=0;k<positionvalues.length();k++) {
    			String temp = positionvalues.substring(k, k+1);
    			if(temp.equals("[")) {
    				count++;
    			}
    			else if(temp.equals("]")) {
    				count++;
    			}
    			else if(temp.equals(",")) {
    				count1++;
    				if(count1==1) {
    					positionvaluesnew += " ";
    				}
    				else {
    					positionvaluesnew += "\n";
    					count1=0;
    				}
    			}
    			else {
    				if((count1%2)==0) {
    					positionvaluesnew += temp;
    				}
    				else {
    					positionvaluesnew += temp;
    				}
    			}
    		}
    		//System.out.println(positionvaluesnew);
    		//---------------------------------------
    		
    		String xval="",yval="";
    		StringTokenizer st=new StringTokenizer(positionvaluesnew, "\n");
    		while(st.hasMoreTokens()){
    			StringTokenizer st1=new StringTokenizer(st.nextToken(), " ");
    			while(st1.hasMoreTokens()){
    				xval=st1.nextToken();
    				yval=st1.nextToken();
    				 aDouble = Double.parseDouble(xval);
    				 bDouble = Double.parseDouble(yval);
    				xval1 = round(aDouble,0,BigDecimal.ROUND_HALF_UP);
    				yval1= round(bDouble,0,BigDecimal.ROUND_HALF_UP);
    				xt.add(new Double(xval1));
                    yt.add(new Double(yval1));
    			}    			
    		}
    		
    		System.out.println(xt+"\n\n\n"+yt);
    		
    		
    		BufferedWriter output = null;
   		  
     		File f=new File("SIGNATURE IMAGES1/"+a2+"encryptedvalues"+".txt");
     		if(!f.exists()){
     		f.createNewFile();
   			output = new BufferedWriter(new FileWriter(f));
   		    output.write(imgcontent);
   		    output.close();
   		    System.out.println("New file has been created to the current directory");
     		}  
     		
     		
     		File f1=new File("SIGNATURE IMAGES1/"+a2+"xypositions"+".txt");
     		if(!f1.exists()){
     		f1.createNewFile();
   			imgpathnew=f1.getAbsolutePath();
   			output = new BufferedWriter(new FileWriter(f1));
   		    output.write(positionvaluesnew);
   		    output.close();
   		    System.out.println("New file has been created to the current directory");
     		}
     		 
         	byte[] buffer = base64.decode(imgcontent);    		
             File ffnew = new File("SIGNATURE IMAGES1/"+a2+".jpg");
     		FileOutputStream fos = new FileOutputStream(ffnew);
     		fos.write(buffer);
     		fos.close();     		
     		
    		//----------------------------------------------------
    		SignatureData sigData1 = new SignatureData(xt, yt, 0);
    		SignatureData sigData1Norm = enroll.enrollSignature(sigData1);
    		
    		try {
                DataIO.writeData(sigData1, a2, "sig1");
                DataIO.writeData(sigData1Norm, a2, "sig1Norm");
            } catch (IOException e1) {
            }
            try {
                byte[] dig = messageDigestCalculator.computeDigest(a2, "sig1Norm");
                
                System.out.println("DIG is------"+dig);
                messageDigestCalculator.recordDigestToDisk(dig, a2, "sig1NormDigest");
            } catch (NoSuchAlgorithmException e1) {
            } catch (IOException e1) {
            }
    		
    		//----------------------------------------------------
    	   
    	}
    	pw.write("<useridsuccess>");
    	pw.write(a2);
    	pw.write("</useridsuccess>");
    	
    	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	return null;
  }
    
  //Method to round the doubles to a max of 3 decimal places
    public static double round(double unrounded, int precision, int roundingMode)
    {
        BigDecimal bd = new BigDecimal(unrounded);
        BigDecimal rounded = bd.setScale(precision, roundingMode);
        return rounded.doubleValue();
    }
    
}

class Base64a
{

    public final static int NO_OPTIONS = 0;


    public final static int ENCODE = 1;

    public final static int DECODE = 0;

    public final static int GZIP = 2;

    public final static int DONT_GUNZIP = 4;

    public final static int DO_BREAK_LINES = 8;


     public final static int URL_SAFE = 16;


     public final static int ORDERED = 32;


    public final static int MAX_LINE_LENGTH = 76;


    public final static byte EQUALS_SIGN = (byte)'=';


    public final static byte NEW_LINE = (byte)'\n';

    public final static String PREFERRED_ENCODING = "US-ASCII";


    public final static byte WHITE_SPACE_ENC = -5; // Indicates white space in encoding
    public final static byte EQUALS_SIGN_ENC = -1; // Indicates equals sign in encoding


    public final static byte[] _STANDARD_ALPHABET = {
        (byte)'A', (byte)'B', (byte)'C', (byte)'D', (byte)'E', (byte)'F', (byte)'G',
        (byte)'H', (byte)'I', (byte)'J', (byte)'K', (byte)'L', (byte)'M', (byte)'N',
        (byte)'O', (byte)'P', (byte)'Q', (byte)'R', (byte)'S', (byte)'T', (byte)'U',
        (byte)'V', (byte)'W', (byte)'X', (byte)'Y', (byte)'Z',
        (byte)'a', (byte)'b', (byte)'c', (byte)'d', (byte)'e', (byte)'f', (byte)'g',
        (byte)'h', (byte)'i', (byte)'j', (byte)'k', (byte)'l', (byte)'m', (byte)'n',
        (byte)'o', (byte)'p', (byte)'q', (byte)'r', (byte)'s', (byte)'t', (byte)'u',
        (byte)'v', (byte)'w', (byte)'x', (byte)'y', (byte)'z',
        (byte)'0', (byte)'1', (byte)'2', (byte)'3', (byte)'4', (byte)'5',
        (byte)'6', (byte)'7', (byte)'8', (byte)'9', (byte)'+', (byte)'/'
    };


    public final static byte[] _STANDARD_DECODABET = {
        -9,-9,-9,-9,-9,-9,-9,-9,-9,                 // Decimal  0 -  8
        -5,-5,                                      // Whitespace: Tab and Linefeed
        -9,-9,                                      // Decimal 11 - 12
        -5,                                         // Whitespace: Carriage Return
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 14 - 26
        -9,-9,-9,-9,-9,                             // Decimal 27 - 31
        -5,                                         // Whitespace: Space
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,              // Decimal 33 - 42
        62,                                         // Plus sign at decimal 43
        -9,-9,-9,                                   // Decimal 44 - 46
        63,                                         // Slash at decimal 47
        52,53,54,55,56,57,58,59,60,61,              // Numbers zero through nine
        -9,-9,-9,                                   // Decimal 58 - 60
        -1,                                         // Equals sign at decimal 61
        -9,-9,-9,                                      // Decimal 62 - 64
        0,1,2,3,4,5,6,7,8,9,10,11,12,13,            // Letters 'A' through 'N'
        14,15,16,17,18,19,20,21,22,23,24,25,        // Letters 'O' through 'Z'
        -9,-9,-9,-9,-9,-9,                          // Decimal 91 - 96
        26,27,28,29,30,31,32,33,34,35,36,37,38,     // Letters 'a' through 'm'
        39,40,41,42,43,44,45,46,47,48,49,50,51,     // Letters 'n' through 'z'
        -9,-9,-9,-9,-9                              // Decimal 123 - 127
        ,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,       // Decimal 128 - 139
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 140 - 152
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 153 - 165
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 166 - 178
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 179 - 191
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 192 - 204
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 205 - 217
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 218 - 230
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 231 - 243
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9         // Decimal 244 - 255
    };


    public final static byte[] _URL_SAFE_ALPHABET = {
      (byte)'A', (byte)'B', (byte)'C', (byte)'D', (byte)'E', (byte)'F', (byte)'G',
      (byte)'H', (byte)'I', (byte)'J', (byte)'K', (byte)'L', (byte)'M', (byte)'N',
      (byte)'O', (byte)'P', (byte)'Q', (byte)'R', (byte)'S', (byte)'T', (byte)'U',
      (byte)'V', (byte)'W', (byte)'X', (byte)'Y', (byte)'Z',
      (byte)'a', (byte)'b', (byte)'c', (byte)'d', (byte)'e', (byte)'f', (byte)'g',
      (byte)'h', (byte)'i', (byte)'j', (byte)'k', (byte)'l', (byte)'m', (byte)'n',
      (byte)'o', (byte)'p', (byte)'q', (byte)'r', (byte)'s', (byte)'t', (byte)'u',
      (byte)'v', (byte)'w', (byte)'x', (byte)'y', (byte)'z',
      (byte)'0', (byte)'1', (byte)'2', (byte)'3', (byte)'4', (byte)'5',
      (byte)'6', (byte)'7', (byte)'8', (byte)'9', (byte)'-', (byte)'_'
    };


    public final static byte[] _URL_SAFE_DECODABET = {
      -9,-9,-9,-9,-9,-9,-9,-9,-9,                 // Decimal  0 -  8
      -5,-5,                                      // Whitespace: Tab and Linefeed
      -9,-9,                                      // Decimal 11 - 12
      -5,                                         // Whitespace: Carriage Return
      -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 14 - 26
      -9,-9,-9,-9,-9,                             // Decimal 27 - 31
      -5,                                         // Whitespace: Space
      -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,              // Decimal 33 - 42
      -9,                                         // Plus sign at decimal 43
      -9,                                         // Decimal 44
      62,                                         // Minus sign at decimal 45
      -9,                                         // Decimal 46
      -9,                                         // Slash at decimal 47
      52,53,54,55,56,57,58,59,60,61,              // Numbers zero through nine
      -9,-9,-9,                                   // Decimal 58 - 60
      -1,                                         // Equals sign at decimal 61
      -9,-9,-9,                                   // Decimal 62 - 64
      0,1,2,3,4,5,6,7,8,9,10,11,12,13,            // Letters 'A' through 'N'
      14,15,16,17,18,19,20,21,22,23,24,25,        // Letters 'O' through 'Z'
      -9,-9,-9,-9,                                // Decimal 91 - 94
      63,                                         // Underscore at decimal 95
      -9,                                         // Decimal 96
      26,27,28,29,30,31,32,33,34,35,36,37,38,     // Letters 'a' through 'm'
      39,40,41,42,43,44,45,46,47,48,49,50,51,     // Letters 'n' through 'z'
      -9,-9,-9,-9,-9                              // Decimal 123 - 127
      ,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 128 - 139
      -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 140 - 152
      -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 153 - 165
      -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 166 - 178
      -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 179 - 191
      -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 192 - 204
      -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 205 - 217
      -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 218 - 230
      -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 231 - 243
      -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9         // Decimal 244 - 255
    };




    public final static byte[] _ORDERED_ALPHABET = {
      (byte)'-',
      (byte)'0', (byte)'1', (byte)'2', (byte)'3', (byte)'4',
      (byte)'5', (byte)'6', (byte)'7', (byte)'8', (byte)'9',
      (byte)'A', (byte)'B', (byte)'C', (byte)'D', (byte)'E', (byte)'F', (byte)'G',
      (byte)'H', (byte)'I', (byte)'J', (byte)'K', (byte)'L', (byte)'M', (byte)'N',
      (byte)'O', (byte)'P', (byte)'Q', (byte)'R', (byte)'S', (byte)'T', (byte)'U',
      (byte)'V', (byte)'W', (byte)'X', (byte)'Y', (byte)'Z',
      (byte)'_',
      (byte)'a', (byte)'b', (byte)'c', (byte)'d', (byte)'e', (byte)'f', (byte)'g',
      (byte)'h', (byte)'i', (byte)'j', (byte)'k', (byte)'l', (byte)'m', (byte)'n',
      (byte)'o', (byte)'p', (byte)'q', (byte)'r', (byte)'s', (byte)'t', (byte)'u',
      (byte)'v', (byte)'w', (byte)'x', (byte)'y', (byte)'z'
    };


    public final static byte[] _ORDERED_DECODABET = {
      -9,-9,-9,-9,-9,-9,-9,-9,-9,                 // Decimal  0 -  8
      -5,-5,                                      // Whitespace: Tab and Linefeed
      -9,-9,                                      // Decimal 11 - 12
      -5,                                         // Whitespace: Carriage Return
      -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 14 - 26
      -9,-9,-9,-9,-9,                             // Decimal 27 - 31
      -5,                                         // Whitespace: Space
      -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,              // Decimal 33 - 42
      -9,                                         // Plus sign at decimal 43
      -9,                                         // Decimal 44
      0,                                          // Minus sign at decimal 45
      -9,                                         // Decimal 46
      -9,                                         // Slash at decimal 47
      1,2,3,4,5,6,7,8,9,10,                       // Numbers zero through nine
      -9,-9,-9,                                   // Decimal 58 - 60
      -1,                                         // Equals sign at decimal 61
      -9,-9,-9,                                   // Decimal 62 - 64
      11,12,13,14,15,16,17,18,19,20,21,22,23,     // Letters 'A' through 'M'
      24,25,26,27,28,29,30,31,32,33,34,35,36,     // Letters 'N' through 'Z'
      -9,-9,-9,-9,                                // Decimal 91 - 94
      37,                                         // Underscore at decimal 95
      -9,                                         // Decimal 96
      38,39,40,41,42,43,44,45,46,47,48,49,50,     // Letters 'a' through 'm'
      51,52,53,54,55,56,57,58,59,60,61,62,63,     // Letters 'n' through 'z'
      -9,-9,-9,-9,-9                                 // Decimal 123 - 127
       ,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 128 - 139
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 140 - 152
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 153 - 165
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 166 - 178
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 179 - 191
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 192 - 204
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 205 - 217
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 218 - 230
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 231 - 243
        -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9         // Decimal 244 - 255
    };


    public final static byte[] getAlphabet( int options ) {
        if ((options & URL_SAFE) == URL_SAFE) {
            return _URL_SAFE_ALPHABET;
        } else if ((options & ORDERED) == ORDERED) {
            return _ORDERED_ALPHABET;
        } else {
            return _STANDARD_ALPHABET;
        }
    }	// end getAlphabet


    public final static byte[] getDecodabet( int options ) {
        if( (options & URL_SAFE) == URL_SAFE) {
            return _URL_SAFE_DECODABET;
        } else if ((options & ORDERED) == ORDERED) {
            return _ORDERED_DECODABET;
        } else {
            return _STANDARD_DECODABET;
        }
    }	// end getAlphabet




    public Base64a(){}



    public static byte[] encode3to4( byte[] b4, byte[] threeBytes, int numSigBytes, int options ) {
        encode3to4( threeBytes, 0, numSigBytes, b4, 0, options );
        return b4;
    }   // end encode3to4



    public static byte[] encode3to4(
    byte[] source, int srcOffset, int numSigBytes,
    byte[] destination, int destOffset, int options ) {

	byte[] ALPHABET = getAlphabet( options );


        int inBuff =   ( numSigBytes > 0 ? ((source[ srcOffset     ] << 24) >>>  8) : 0 )
                     | ( numSigBytes > 1 ? ((source[ srcOffset + 1 ] << 24) >>> 16) : 0 )
                     | ( numSigBytes > 2 ? ((source[ srcOffset + 2 ] << 24) >>> 24) : 0 );

        switch( numSigBytes )
        {
            case 3:
                destination[ destOffset     ] = ALPHABET[ (inBuff >>> 18)        ];
                destination[ destOffset + 1 ] = ALPHABET[ (inBuff >>> 12) & 0x3f ];
                destination[ destOffset + 2 ] = ALPHABET[ (inBuff >>>  6) & 0x3f ];
                destination[ destOffset + 3 ] = ALPHABET[ (inBuff       ) & 0x3f ];
                return destination;

            case 2:
                destination[ destOffset     ] = ALPHABET[ (inBuff >>> 18)        ];
                destination[ destOffset + 1 ] = ALPHABET[ (inBuff >>> 12) & 0x3f ];
                destination[ destOffset + 2 ] = ALPHABET[ (inBuff >>>  6) & 0x3f ];
                destination[ destOffset + 3 ] = EQUALS_SIGN;
                return destination;

            case 1:
                destination[ destOffset     ] = ALPHABET[ (inBuff >>> 18)        ];
                destination[ destOffset + 1 ] = ALPHABET[ (inBuff >>> 12) & 0x3f ];
                destination[ destOffset + 2 ] = EQUALS_SIGN;
                destination[ destOffset + 3 ] = EQUALS_SIGN;
                return destination;

            default:
                return destination;
        }   // end switch
    }   // end encode3to4



    public static void encode( java.nio.ByteBuffer raw, java.nio.ByteBuffer encoded ){
        byte[] raw3 = new byte[3];
        byte[] enc4 = new byte[4];

        while( raw.hasRemaining() ){
            int rem = Math.min(3,raw.remaining());
            raw.get(raw3,0,rem);
            Base64a.encode3to4(enc4, raw3, rem, Base64a.NO_OPTIONS );
            encoded.put(enc4);
        }   // end input remaining
    }


    public static void encode( java.nio.ByteBuffer raw, java.nio.CharBuffer encoded ){
        byte[] raw3 = new byte[3];
        byte[] enc4 = new byte[4];

        while( raw.hasRemaining() ){
            int rem = Math.min(3,raw.remaining());
            raw.get(raw3,0,rem);
            Base64a.encode3to4(enc4, raw3, rem, Base64a.NO_OPTIONS );
            for( int i = 0; i < 4; i++ ){
                encoded.put( (char)(enc4[i] & 0xFF) );
            }
        }   // end input remaining
    }



    public static String encodeObject( java.io.Serializable serializableObject )
    throws java.io.IOException {
        return encodeObject( serializableObject, NO_OPTIONS );
    }   // end encodeObject



    public static String encodeObject( java.io.Serializable serializableObject, int options )
    throws java.io.IOException {

        if( serializableObject == null ){
            throw new NullPointerException( "Cannot serialize a null object." );
        }   // end if: null

        // Streams
        java.io.ByteArrayOutputStream  baos  = null;
        java.io.OutputStream           b64os = null;
        java.util.zip.GZIPOutputStream gzos  = null;
        java.io.ObjectOutputStream     oos   = null;


        try {
            // ObjectOutputStream -> (GZIP) -> Base64a -> ByteArrayOutputStream
            baos  = new java.io.ByteArrayOutputStream();
            b64os = new Base64a.OutputStream( baos, ENCODE | options );
            if( (options & GZIP) != 0 ){
                // Gzip
                gzos = new java.util.zip.GZIPOutputStream(b64os);
                oos = new java.io.ObjectOutputStream( gzos );
            } else {
                // Not gzipped
                oos = new java.io.ObjectOutputStream( b64os );
            }
            oos.writeObject( serializableObject );
        }   // end try
        catch( java.io.IOException e ) {
            // Catch it and then throw it immediately so that
            // the finally{} block is called for cleanup.
            throw e;
        }   // end catch
        finally {
            try{ oos.close();   } catch( Exception e ){}
            try{ gzos.close();  } catch( Exception e ){}
            try{ b64os.close(); } catch( Exception e ){}
            try{ baos.close();  } catch( Exception e ){}
        }   // end finally

        // Return value according to relevant encoding.
        try {
            return new String( baos.toByteArray(), PREFERRED_ENCODING );
        }   // end try
        catch (java.io.UnsupportedEncodingException uue){
            // Fall back to some Java default
            return new String( baos.toByteArray() );
        }   // end catch

    }   // end encode


    public static String encodeBytes( byte[] source ) {

        String encoded = null;
        try {
            encoded = encodeBytes(source, 0, source.length, NO_OPTIONS);
        } catch (java.io.IOException ex) {
            assert false : ex.getMessage();
        }   // end catch
        assert encoded != null;
        return encoded;
    }   // end encodeBytes


    public static String encodeBytes( byte[] source, int options ) throws java.io.IOException {
        return encodeBytes( source, 0, source.length, options );
    }   // end encodeBytes


    public static String encodeBytes( byte[] source, int off, int len ) {

        String encoded = null;
        try {
            encoded = encodeBytes( source, off, len, NO_OPTIONS );
        } catch (java.io.IOException ex) {
            assert false : ex.getMessage();
        }   // end catch
        assert encoded != null;
        return encoded;
    }   // end encodeBytes




    public static String encodeBytes( byte[] source, int off, int len, int options ) throws java.io.IOException {
        byte[] encoded = encodeBytesToBytes( source, off, len, options );

        // Return value according to relevant encoding.
        try {
            return new String( encoded, PREFERRED_ENCODING );
        }   // end try
        catch (java.io.UnsupportedEncodingException uue) {
            return new String( encoded );
        }   // end catch

    }   // end encodeBytes

    public static byte[] encodeBytesToBytes( byte[] source ) {
        byte[] encoded = null;
        try {
            encoded = encodeBytesToBytes( source, 0, source.length, Base64a.NO_OPTIONS );
        } catch( java.io.IOException ex ) {
            assert false : "IOExceptions only come from GZipping, which is turned off: " + ex.getMessage();
        }
        return encoded;
    }


    public static byte[] encodeBytesToBytes( byte[] source, int off, int len, int options ) throws java.io.IOException {

        if( source == null ){
            throw new NullPointerException( "Cannot serialize a null array." );
        }   // end if: null

        if( off < 0 ){
            throw new IllegalArgumentException( "Cannot have negative offset: " + off );
        }   // end if: off < 0

        if( len < 0 ){
            throw new IllegalArgumentException( "Cannot have length offset: " + len );
        }   // end if: len < 0

        if( off + len > source.length  ){
            throw new IllegalArgumentException(
            String.format( "Cannot have offset of %d and length of %d with array of length %d", off,len,source.length));
        }   // end if: off < 0



        // Compress?
        if( (options & GZIP) != 0 ) {
            java.io.ByteArrayOutputStream  baos  = null;
            java.util.zip.GZIPOutputStream gzos  = null;
            Base64a.OutputStream            b64os = null;

            try {
                // GZip -> Base64a -> ByteArray
                baos = new java.io.ByteArrayOutputStream();
                b64os = new Base64a.OutputStream( baos, ENCODE | options );
                gzos  = new java.util.zip.GZIPOutputStream( b64os );

                gzos.write( source, off, len );
                gzos.close();
            }   // end try
            catch( java.io.IOException e ) {
                // Catch it and then throw it immediately so that
                // the finally{} block is called for cleanup.
                throw e;
            }   // end catch
            finally {
                try{ gzos.close();  } catch( Exception e ){}
                try{ b64os.close(); } catch( Exception e ){}
                try{ baos.close();  } catch( Exception e ){}
            }   // end finally

            return baos.toByteArray();
        }   // end if: compress

        // Else, don't compress. Better not to use streams at all then.
        else {
            boolean breakLines = (options & DO_BREAK_LINES) != 0;


            int encLen = ( len / 3 ) * 4 + ( len % 3 > 0 ? 4 : 0 ); // Bytes needed for actual encoding
            if( breakLines ){
                encLen += encLen / MAX_LINE_LENGTH; // Plus extra newline characters
            }
            byte[] outBuff = new byte[ encLen ];


            int d = 0;
            int e = 0;
            int len2 = len - 2;
            int lineLength = 0;
            for( ; d < len2; d+=3, e+=4 ) {
                encode3to4( source, d+off, 3, outBuff, e, options );

                lineLength += 4;
                if( breakLines && lineLength >= MAX_LINE_LENGTH )
                {
                    outBuff[e+4] = NEW_LINE;
                    e++;
                    lineLength = 0;
                }   // end if: end of line
            }   // en dfor: each piece of array

            if( d < len ) {
                encode3to4( source, d+off, len - d, outBuff, e, options );
                e += 4;
            }   // end if: some padding needed


            // Only resize array if we didn't guess it right.
            if( e <= outBuff.length - 1 ){

                byte[] finalOut = new byte[e];
                System.arraycopy(outBuff,0, finalOut,0,e);
                //System.err.println("Having to resize array from " + outBuff.length + " to " + e );
                return finalOut;
            } else {
                //System.err.println("No need to resize array.");
                return outBuff;
            }

        }   // end else: don't compress

    }   // end encodeBytesToBytes



    private static int decode4to3(
    byte[] source, int srcOffset,
    byte[] destination, int destOffset, int options ) {

        // Lots of error checking and exception throwing
        if( source == null ){
            throw new NullPointerException( "Source array was null." );
        }   // end if
        if( destination == null ){
            throw new NullPointerException( "Destination array was null." );
        }   // end if
        if( srcOffset < 0 || srcOffset + 3 >= source.length ){
            throw new IllegalArgumentException( String.format(
            "Source array with length %d cannot have offset of %d and still process four bytes.", source.length, srcOffset ) );
        }   // end if
        if( destOffset < 0 || destOffset +2 >= destination.length ){
            throw new IllegalArgumentException( String.format(
            "Destination array with length %d cannot have offset of %d and still store three bytes.", destination.length, destOffset ) );
        }   // end if


        byte[] DECODABET = getDecodabet( options );

        // Example: Dk==
        if( source[ srcOffset + 2] == EQUALS_SIGN ) {
            // Two ways to do the same thing. Don't know which way I like best.
          //int outBuff =   ( ( DECODABET[ source[ srcOffset    ] ] << 24 ) >>>  6 )
          //              | ( ( DECODABET[ source[ srcOffset + 1] ] << 24 ) >>> 12 );
            int outBuff =   ( ( DECODABET[ source[ srcOffset    ] ] & 0xFF ) << 18 )
                          | ( ( DECODABET[ source[ srcOffset + 1] ] & 0xFF ) << 12 );

            destination[ destOffset ] = (byte)( outBuff >>> 16 );
            return 1;
        }

        // Example: DkL=
        else if( source[ srcOffset + 3 ] == EQUALS_SIGN ) {
            // Two ways to do the same thing. Don't know which way I like best.
          //int outBuff =   ( ( DECODABET[ source[ srcOffset     ] ] << 24 ) >>>  6 )
          //              | ( ( DECODABET[ source[ srcOffset + 1 ] ] << 24 ) >>> 12 )
          //              | ( ( DECODABET[ source[ srcOffset + 2 ] ] << 24 ) >>> 18 );
            int outBuff =   ( ( DECODABET[ source[ srcOffset     ] ] & 0xFF ) << 18 )
                          | ( ( DECODABET[ source[ srcOffset + 1 ] ] & 0xFF ) << 12 )
                          | ( ( DECODABET[ source[ srcOffset + 2 ] ] & 0xFF ) <<  6 );

            destination[ destOffset     ] = (byte)( outBuff >>> 16 );
            destination[ destOffset + 1 ] = (byte)( outBuff >>>  8 );
            return 2;
        }

        // Example: DkLE
        else {
            // Two ways to do the same thing. Don't know which way I like best.
          //int outBuff =   ( ( DECODABET[ source[ srcOffset     ] ] << 24 ) >>>  6 )
          //              | ( ( DECODABET[ source[ srcOffset + 1 ] ] << 24 ) >>> 12 )
          //              | ( ( DECODABET[ source[ srcOffset + 2 ] ] << 24 ) >>> 18 )
          //              | ( ( DECODABET[ source[ srcOffset + 3 ] ] << 24 ) >>> 24 );
            int outBuff =   ( ( DECODABET[ source[ srcOffset     ] ] & 0xFF ) << 18 )
                          | ( ( DECODABET[ source[ srcOffset + 1 ] ] & 0xFF ) << 12 )
                          | ( ( DECODABET[ source[ srcOffset + 2 ] ] & 0xFF ) <<  6)
                          | ( ( DECODABET[ source[ srcOffset + 3 ] ] & 0xFF )      );


            destination[ destOffset     ] = (byte)( outBuff >> 16 );
            destination[ destOffset + 1 ] = (byte)( outBuff >>  8 );
            destination[ destOffset + 2 ] = (byte)( outBuff       );

            return 3;
        }
    }   // end decodeToBytes



    public static byte[] decode( byte[] source )
    throws java.io.IOException {
        byte[] decoded = null;

            decoded = decode( source, 0, source.length, Base64a.NO_OPTIONS );
        return decoded;
    }



    public static byte[] decode( byte[] source, int off, int len, int options )
    throws java.io.IOException {

        // Lots of error checking and exception throwing
        if( source == null ){
            throw new NullPointerException( "Cannot decode null source array." );
        }   // end if
        if( off < 0 || off + len > source.length ){
            throw new IllegalArgumentException( String.format(
            "Source array with length %d cannot have offset of %d and process %d bytes.", source.length, off, len ) );
        }   // end if

        if( len == 0 ){
            return new byte[0];
        }else if( len < 4 ){
            throw new IllegalArgumentException(
            "Base64a-encoded string must have at least four characters, but length specified was " + len );
        }   // end if

        byte[] DECODABET = getDecodabet( options );

        int    len34   = len * 3 / 4;       // Estimate on array size
        byte[] outBuff = new byte[ len34 ]; // Upper limit on size of output
        int    outBuffPosn = 0;             // Keep track of where we're writing

        byte[] b4        = new byte[4];     // Four byte buffer from source, eliminating white space
        int    b4Posn    = 0;               // Keep track of four byte input buffer
        int    i         = 0;               // Source array counter
        byte   sbiDecode = 0;               // Special value from DECODABET

        for( i = off; i < off+len; i++ ) {  // Loop through source

            sbiDecode = DECODABET[ source[i]&0xFF ];


            if( sbiDecode >= WHITE_SPACE_ENC )  {
                if( sbiDecode >= EQUALS_SIGN_ENC ) {
                    b4[ b4Posn++ ] = source[i];         // Save non-whitespace
                    if( b4Posn > 3 ) {                  // Time to decode?
                        outBuffPosn += decode4to3( b4, 0, outBuff, outBuffPosn, options );
                        b4Posn = 0;

                        // If that was the equals sign, break out of 'for' loop
                        if( source[i] == EQUALS_SIGN ) {
                            break;
                        }   // end if: equals sign
                    }   // end if: quartet built
                }   // end if: equals sign or better
            }   // end if: white space, equals sign or better
            else {
                // There's a bad input character in the Base64a stream.
                throw new java.io.IOException( String.format(
                "Bad Base64a input character decimal %d in array position %d", ((int)source[i])&0xFF, i ) );
            }   // end else:
        }   // each input character

        byte[] out = new byte[ outBuffPosn ];
        System.arraycopy( outBuff, 0, out, 0, outBuffPosn );
        return out;
    }   // end decode



    public static byte[] decode( String s ) throws java.io.IOException {
        return decode( s, NO_OPTIONS );
    }



    public static byte[] decode( String s, int options ) throws java.io.IOException {

        if( s == null ){
            throw new NullPointerException( "Input string was null." );
        }   // end if

        byte[] bytes;
        try {
            bytes = s.getBytes( PREFERRED_ENCODING );
        }   // end try
        catch( java.io.UnsupportedEncodingException uee ) {
            bytes = s.getBytes();
        }   // end catch
		//</change>

        // Decode
        bytes = decode( bytes, 0, bytes.length, options );

        // Check to see if it's gzip-compressed
        // GZIP Magic Two-Byte Number: 0x8b1f (35615)
        boolean dontGunzip = (options & DONT_GUNZIP) != 0;
        if( (bytes != null) && (bytes.length >= 4) && (!dontGunzip) ) {

            int head = ((int)bytes[0] & 0xff) | ((bytes[1] << 8) & 0xff00);
            if( java.util.zip.GZIPInputStream.GZIP_MAGIC == head )  {
                java.io.ByteArrayInputStream  bais = null;
                java.util.zip.GZIPInputStream gzis = null;
                java.io.ByteArrayOutputStream baos = null;
                byte[] buffer = new byte[2048];
                int    length = 0;

                try {
                    baos = new java.io.ByteArrayOutputStream();
                    bais = new java.io.ByteArrayInputStream( bytes );
                    gzis = new java.util.zip.GZIPInputStream( bais );

                    while( ( length = gzis.read( buffer ) ) >= 0 ) {
                        baos.write(buffer,0,length);
                    }   // end while: reading input

                    // No error? Get new bytes.
                    bytes = baos.toByteArray();

                }   // end try
                catch( java.io.IOException e ) {
                    e.printStackTrace();
                    // Just return originally-decoded bytes
                }   // end catch
                finally {
                    try{ baos.close(); } catch( Exception e ){}
                    try{ gzis.close(); } catch( Exception e ){}
                    try{ bais.close(); } catch( Exception e ){}
                }   // end finally

            }   // end if: gzipped
        }   // end if: bytes.length >= 2

        return bytes;
    }   // end decode



    public static Object decodeToObject( String encodedObject )
    throws java.io.IOException, java.lang.ClassNotFoundException {
        return decodeToObject(encodedObject,NO_OPTIONS,null);
    }


    public static Object decodeToObject(
    String encodedObject, int options, final ClassLoader loader )
    throws java.io.IOException, java.lang.ClassNotFoundException {

        // Decode and gunzip if necessary
        byte[] objBytes = decode( encodedObject, options );

        java.io.ByteArrayInputStream  bais = null;
        java.io.ObjectInputStream     ois  = null;
        Object obj = null;

        try {
            bais = new java.io.ByteArrayInputStream( objBytes );

            // If no custom class loader is provided, use Java's builtin OIS.
            if( loader == null ){
                ois  = new java.io.ObjectInputStream( bais );
            }   // end if: no loader provided


            else {
                ois = new java.io.ObjectInputStream(bais){
                    @Override
                    public Class<?> resolveClass(java.io.ObjectStreamClass streamClass)
                    throws java.io.IOException, ClassNotFoundException {
                        @SuppressWarnings("rawtypes")
						Class c = Class.forName(streamClass.getName(), false, loader);
                        if( c == null ){
                            return super.resolveClass(streamClass);
                        } else {
                            return c;   // Class loader knows of this class.
                        }   // end else: not null
                    }   // end resolveClass
                };  // end ois
            }   // end else: no custom class loader

            obj = ois.readObject();
        }   // end try
        catch( java.io.IOException e ) {
            throw e;    // Catch and throw in order to execute finally{}
        }   // end catch
        catch( java.lang.ClassNotFoundException e ) {
            throw e;    // Catch and throw in order to execute finally{}
        }   // end catch
        finally {
            try{ bais.close(); } catch( Exception e ){}
            try{ ois.close();  } catch( Exception e ){}
        }   // end finally

        return obj;
    }   // end decodeObject


    public static void encodeToFile( byte[] dataToEncode, String filename )
    throws java.io.IOException {

        if( dataToEncode == null ){
            throw new NullPointerException( "Data to encode was null." );
        }   // end iff

        Base64a.OutputStream bos = null;
        try {
            bos = new Base64a.OutputStream(
                  new java.io.FileOutputStream( filename ), Base64a.ENCODE );
            bos.write( dataToEncode );
        }   // end try
        catch( java.io.IOException e ) {
            throw e; // Catch and throw to execute finally{} block
        }   // end catch: java.io.IOException
        finally {
            try{ bos.close(); } catch( Exception e ){}
        }   // end finally

    }   // end encodeToFile



    public static void decodeToFile( String dataToDecode, String filename )
    throws java.io.IOException {

        Base64a.OutputStream bos = null;
        try{
            bos = new Base64a.OutputStream(
                      new java.io.FileOutputStream( filename ), Base64a.DECODE );
            bos.write( dataToDecode.getBytes( PREFERRED_ENCODING ) );
        }   // end try
        catch( java.io.IOException e ) {
            throw e; // Catch and throw to execute finally{} block
        }   // end catch: java.io.IOException
        finally {
                try{ bos.close(); } catch( Exception e ){}
        }   // end finally

    }   // end decodeToFile



    public static byte[] decodeFromFile( String filename )
    throws java.io.IOException {

        byte[] decodedData = null;
        Base64a.InputStream bis = null;
        try
        {
            // Set up some useful variables
            java.io.File file = new java.io.File( filename );
            byte[] buffer = null;
            int length   = 0;
            int numBytes = 0;

            // Check for size of file
            if( file.length() > Integer.MAX_VALUE )
            {
                throw new java.io.IOException( "File is too big for this convenience method (" + file.length() + " bytes)." );
            }   // end if: file too big for int index
            buffer = new byte[ (int)file.length() ];

            // Open a stream
            bis = new Base64a.InputStream(
                      new java.io.BufferedInputStream(
                      new java.io.FileInputStream( file ) ), Base64a.DECODE );

            // Read until done
            while( ( numBytes = bis.read( buffer, length, 4096 ) ) >= 0 ) {
                length += numBytes;
            }   // end while

            // Save in a variable to return
            decodedData = new byte[ length ];
            System.arraycopy( buffer, 0, decodedData, 0, length );

        }   // end try
        catch( java.io.IOException e ) {
            throw e; // Catch and release to execute finally{}
        }   // end catch: java.io.IOException
        finally {
            try{ bis.close(); } catch( Exception e) {}
        }   // end finally

        return decodedData;
    }   // end decodeFromFile



    public static String encodeFromFile( String filename )
    throws java.io.IOException {

        String encodedData = null;
        Base64a.InputStream bis = null;
        try
        {
            // Set up some useful variables
            java.io.File file = new java.io.File( filename );
            byte[] buffer = new byte[ Math.max((int)(file.length() * 1.4+1),40) ]; // Need max() for math on small files (v2.2.1); Need +1 for a few corner cases (v2.3.5)
            int length   = 0;
            int numBytes = 0;

            // Open a stream
            bis = new Base64a.InputStream(
                      new java.io.BufferedInputStream(
                      new java.io.FileInputStream( file ) ), Base64a.ENCODE );

            // Read until done
            while( ( numBytes = bis.read( buffer, length, 4096 ) ) >= 0 ) {
                length += numBytes;
            }   // end while

            // Save in a variable to return
            encodedData = new String( buffer, 0, length, Base64a.PREFERRED_ENCODING );

        }   // end try
        catch( java.io.IOException e ) {
            throw e; // Catch and release to execute finally{}
        }   // end catch: java.io.IOException
        finally {
            try{ bis.close(); } catch( Exception e) {}
        }   // end finally

        return encodedData;
        }   // end encodeFromFile


    public static void encodeFileToFile( String infile, String outfile )
    throws java.io.IOException {

        String encoded = Base64a.encodeFromFile( infile );
        java.io.OutputStream out = null;
        try{
            out = new java.io.BufferedOutputStream(
                  new java.io.FileOutputStream( outfile ) );
            out.write( encoded.getBytes("US-ASCII") ); // Strict, 7-bit output.
        }   // end try
        catch( java.io.IOException e ) {
            throw e; // Catch and release to execute finally{}
        }   // end catch
        finally {
            try { out.close(); }
            catch( Exception ex ){}
        }   // end finally
    }   // end encodeFileToFile



    public static void decodeFileToFile( String infile, String outfile )
    throws java.io.IOException {

        byte[] decoded = Base64a.decodeFromFile( infile );
        java.io.OutputStream out = null;
        try{
            out = new java.io.BufferedOutputStream(
                  new java.io.FileOutputStream( outfile ) );
            out.write( decoded );
        }   // end try
        catch( java.io.IOException e ) {
            throw e; // Catch and release to execute finally{}
        }   // end catch
        finally {
            try { out.close(); }
            catch( Exception ex ){}
        }   // end finally
    }   // end decodeFileToFile



    public static class InputStream extends java.io.FilterInputStream {

        private boolean encode;         // Encoding or decoding
        private int     position;       // Current position in the buffer
        private byte[]  buffer;         // Small buffer holding converted data
        private int     bufferLength;   // Length of buffer (3 or 4)
        private int     numSigBytes;    // Number of meaningful bytes in the buffer
        private int     lineLength;
        private boolean breakLines;     // Break lines at less than 80 characters
        private int     options;        // Record options used to create the stream.
        private byte[]  decodabet;      // Local copies to avoid extra method calls



        public InputStream( java.io.InputStream in ) {
            this( in, DECODE );
        }   // end constructor



        public InputStream( java.io.InputStream in, int options ) {

            super( in );
            this.options      = options; // Record for later
            this.breakLines   = (options & DO_BREAK_LINES) > 0;
            this.encode       = (options & ENCODE) > 0;
            this.bufferLength = encode ? 4 : 3;
            this.buffer       = new byte[ bufferLength ];
            this.position     = -1;
            this.lineLength   = 0;
            this.decodabet    = getDecodabet(options);
        }   // end constructor


        @Override
        public int read() throws java.io.IOException  {

            // Do we need to get data?
            if( position < 0 ) {
                if( encode ) {
                    byte[] b3 = new byte[3];
                    int numBinaryBytes = 0;
                    for( int i = 0; i < 3; i++ ) {
                        int b = in.read();

                        // If end of stream, b is -1.
                        if( b >= 0 ) {
                            b3[i] = (byte)b;
                            numBinaryBytes++;
                        } else {
                            break; // out of for loop
                        }   // end else: end of stream

                    }   // end for: each needed input byte

                    if( numBinaryBytes > 0 ) {
                        encode3to4( b3, 0, numBinaryBytes, buffer, 0, options );
                        position = 0;
                        numSigBytes = 4;
                    }   // end if: got data
                    else {
                        return -1;  // Must be end of stream
                    }   // end else
                }   // end if: encoding

                // Else decoding
                else {
                    byte[] b4 = new byte[4];
                    int i = 0;
                    for( i = 0; i < 4; i++ ) {
                        // Read four "meaningful" bytes:
                        int b = 0;
                        do{ b = in.read(); }
                        while( b >= 0 && decodabet[ b & 0x7f ] <= WHITE_SPACE_ENC );

                        if( b < 0 ) {
                            break; // Reads a -1 if end of stream
                        }   // end if: end of stream

                        b4[i] = (byte)b;
                    }   // end for: each needed input byte

                    if( i == 4 ) {
                        numSigBytes = decode4to3( b4, 0, buffer, 0, options );
                        position = 0;
                    }   // end if: got four characters
                    else if( i == 0 ){
                        return -1;
                    }   // end else if: also padded correctly
                    else {
                        // Must have broken out from above.
                        throw new java.io.IOException( "Improperly padded Base64a input." );
                    }   // end

                }   // end else: decode
            }   // end else: get data

            // Got data?
            if( position >= 0 ) {
                // End of relevant data?
                if( /*!encode &&*/ position >= numSigBytes ){
                    return -1;
                }   // end if: got data

                if( encode && breakLines && lineLength >= MAX_LINE_LENGTH ) {
                    lineLength = 0;
                    return '\n';
                }   // end if
                else {
                    lineLength++;   // This isn't important when decoding
                                    // but throwing an extra "if" seems
                                    // just as wasteful.

                    int b = buffer[ position++ ];

                    if( position >= bufferLength ) {
                        position = -1;
                    }   // end if: end

                    return b & 0xFF; // This is how you "cast" a byte that's
                                     // intended to be unsigned.
                }   // end else
            }   // end if: position >= 0

            // Else error
            else {
                throw new java.io.IOException( "Error in Base64a code reading stream." );
            }   // end else
        }   // end read



        @Override
        public int read( byte[] dest, int off, int len )
        throws java.io.IOException {
            int i;
            int b;
            for( i = 0; i < len; i++ ) {
                b = read();

                if( b >= 0 ) {
                    dest[off + i] = (byte) b;
                }
                else if( i == 0 ) {
                    return -1;
                }
                else {
                    break; // Out of 'for' loop
                } // Out of 'for' loop
            }   // end for: each byte read
            return i;
        }   // end read

    }   // end inner class InputStream


    public static class OutputStream extends java.io.FilterOutputStream {

        private boolean encode;
        private int     position;
        private byte[]  buffer;
        private int     bufferLength;
        private int     lineLength;
        private boolean breakLines;
        private byte[]  b4;         // Scratch used in a few places
        private boolean suspendEncoding;
        private int     options;    // Record for later
        private byte[]  decodabet;  // Local copies to avoid extra method calls


        public OutputStream( java.io.OutputStream out ) {
            this( out, ENCODE );
        }   // end constructor



        public OutputStream( java.io.OutputStream out, int options ) {
            super( out );
            this.breakLines   = (options & DO_BREAK_LINES) != 0;
            this.encode       = (options & ENCODE) != 0;
            this.bufferLength = encode ? 3 : 4;
            this.buffer       = new byte[ bufferLength ];
            this.position     = 0;
            this.lineLength   = 0;
            this.suspendEncoding = false;
            this.b4           = new byte[4];
            this.options      = options;
            this.decodabet    = getDecodabet(options);
        }   // end constructor



        @Override
        public void write(int theByte)
        throws java.io.IOException {
            // Encoding suspended?
            if( suspendEncoding ) {
                this.out.write( theByte );
                return;
            }   // end if: supsended

            // Encode?
            if( encode ) {
                buffer[ position++ ] = (byte)theByte;
                if( position >= bufferLength ) { // Enough to encode.

                    this.out.write( encode3to4( b4, buffer, bufferLength, options ) );

                    lineLength += 4;
                    if( breakLines && lineLength >= MAX_LINE_LENGTH ) {
                        this.out.write( NEW_LINE );
                        lineLength = 0;
                    }   // end if: end of line

                    position = 0;
                }   // end if: enough to output
            }   // end if: encoding

            // Else, Decoding
            else {
                // Meaningful Base64a character?
                if( decodabet[ theByte & 0x7f ] > WHITE_SPACE_ENC ) {
                    buffer[ position++ ] = (byte)theByte;
                    if( position >= bufferLength ) { // Enough to output.

                        int len = Base64a.decode4to3( buffer, 0, b4, 0, options );
                        out.write( b4, 0, len );
                        position = 0;
                    }   // end if: enough to output
                }   // end if: meaningful Base64a character
                else if( decodabet[ theByte & 0x7f ] != WHITE_SPACE_ENC ) {
                    throw new java.io.IOException( "Invalid character in Base64a data." );
                }   // end else: not white space either
            }   // end else: decoding
        }   // end write



        @Override
        public void write( byte[] theBytes, int off, int len )
        throws java.io.IOException {
            // Encoding suspended?
            if( suspendEncoding ) {
                this.out.write( theBytes, off, len );
                return;
            }   // end if: supsended

            for( int i = 0; i < len; i++ ) {
                write( theBytes[ off + i ] );
            }   // end for: each byte written

        }   // end write




        public void flushBase64a() throws java.io.IOException  {
            if( position > 0 ) {
                if( encode ) {
                    out.write( encode3to4( b4, buffer, position, options ) );
                    position = 0;
                }   // end if: encoding
                else {
                    throw new java.io.IOException( "Base64a input not properly padded." );
                }   // end else: decoding
            }   // end if: buffer partially full

        }   // end flush



        @Override
        public void close() throws java.io.IOException {
            // 1. Ensure that pending characters are written
            flushBase64a();

            // 2. Actually close the stream
            // Base class both flushes and closes.
            super.close();

            buffer = null;
            out    = null;
        }   // end close



        public void suspendEncoding() throws java.io.IOException  {
            flushBase64a();
            this.suspendEncoding = true;
        }   // end suspendEncoding


        public void resumeEncoding() {
            this.suspendEncoding = false;
        }   // end resumeEncoding



    }   // end inner class OutputStream


}   // end class Base64a