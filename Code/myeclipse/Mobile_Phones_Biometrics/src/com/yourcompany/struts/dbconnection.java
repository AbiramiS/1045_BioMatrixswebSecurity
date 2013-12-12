package com.yourcompany.struts;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
public class dbconnection{
Connection conn;
Statement st;
String DBase="",uname="",pass="";
Properties prop=null;
public Connection getConnect() {
try {
prop=new Properties();
prop.load(new FileInputStream("DBConnect.properties"));
DBase = prop.getProperty("systemname");
uname = prop.getProperty("username");
pass =prop.getProperty("password");
String driver="jdbc:oracle:thin:@"+DBase;
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
conn=DriverManager.getConnection(driver,uname,pass);

System.out.println("========Connected========");
}
catch(Exception e)
{
	e.printStackTrace();
	}
return conn;
}
}

