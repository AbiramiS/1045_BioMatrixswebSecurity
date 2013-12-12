import java.sql.*;
import java.io.*;
import java.util.*;

class CsharpConsumer {
	static String dbvalue = "";
	static String files = "";
	static String resultvalue = "FALSE";
	static String fileName = "", finalvalue = "";

	static{
		System.loadLibrary("JNICsharpBridge");
	}

	public static void main(String[] args) {
		try {
			CsharpConsumer csc = new CsharpConsumer();
			csc.reigsterAssemblyHandler("FaceRegonization");
			int value = csc.addTwoNos(1, 0);
			System.out.println(value);
			if(value==1){
					resultvalue = "TRUE";
					System.out.println("User Recognized");
			}

			  // Directory path here

			  File folder = new File("E://CHECK");
			  File[] listOfFiles = folder.listFiles();


			  for (int i = 0; i < listOfFiles.length; i++)
			  {

			     if (listOfFiles[i].isFile())
			     {
				    files = listOfFiles[i].getName();
					StringTokenizer str = new StringTokenizer(files,".");
					fileName = str.nextToken().toString().trim();
					if(fileName.equals("DBImage")){

					}
					else{
						finalvalue = fileName;
					}
				 }
			  }



			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost","system","redhat");
			Statement stat=con.createStatement();

			ResultSet rs = stat.executeQuery("select * from FACEVALIDATION where USERID='"+finalvalue+"'");
			        Boolean result=rs.next();
					        if(result) {
					        	System.out.println("USER ALREADY EXISTING... Updation in Progress");
					        	stat.executeUpdate("update FACEVALIDATION set VALUE='"+resultvalue+"' where USERID='"+finalvalue+"'");
					        	stat.close();
								con.close();
							}
							else {
								System.out.println("NEW USER FOUND... Creating New Record...");
								String sql = "insert into FACEVALIDATION values ('"+finalvalue+"','"+resultvalue+"')";
								int val = stat.executeUpdate(sql);
								stat.close();
								con.close();
							}


			File file = new File("E://INPUTIMAGE/1.jpg");
			File file1 = new File("E://CHECK/"+finalvalue+".jpg");
			if(file.exists()) {
				//System.out.println("****"+file.getAbsolutePath());
				file.delete();
			}
			if(file1.exists()) {
				//System.out.println("****1"+file1.getAbsolutePath());
				file1.delete();
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	native int addTwoNos(int a,int b);
	native int reigsterAssemblyHandler(String str);

}
