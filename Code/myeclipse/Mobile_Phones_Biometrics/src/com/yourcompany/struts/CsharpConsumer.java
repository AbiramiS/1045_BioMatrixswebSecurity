/**
 * Project: Signature Verification
 * @author Ajay R, Keshav Kumar HK and Sachin Sudheendra
 */

package com.yourcompany.struts;
public class CsharpConsumer {

	static{
		System.loadLibrary("JNICsharpBridge");
	}

	public static void FRecognition(String userid) {
		CsharpConsumer csc = new CsharpConsumer();
		csc.reigsterAssemblyHandler("FaceRegonization");
		int uservalue = Integer.parseInt(userid);
		System.out.println("Result of adding = "+ csc.addTwoNos(uservalue, 0));
	}
	native int addTwoNos(int a,int b);
	native int reigsterAssemblyHandler(String str);
}
