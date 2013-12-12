/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yourcompany.struts;


import java.net.InetAddress;
import java.util.*;

public class RandomCreate { 

Random ran = new Random();
String id,acc,npass;


public String userid()
{
    try{
    	id=""+ran.nextInt(999999);
    }
    catch(Exception p){
        p.printStackTrace();
    }
    return id;
}

public String accno()
{
	try{
		acc="A"+ran.nextInt(99);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return acc;
}

public String newpass()
{
	try{
		npass=""+ran.nextInt(99);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return npass;
}


}