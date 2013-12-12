package mobile.bio;

public class DetailsPage{

	String ret,rec;

	public String details(byte[] response){
		String resp=new String(response);
		String total=Split(resp,"success");
		System.out.println(total);
		ret="ACC NO:"+Split(resp,"accid")+"\n\n"+"USER ID:"+Split(resp,"userid");
		if(Split(resp,"accid").equals(""))	{
			ret="Sorry, Password already chosen...Try some other password";
		}
		else if(Split(resp,"accid").equals("***"))	{
			ret="Fake User Found., Identity Mismatching..."; 
		}
		return ret; 
	}
	
	
	public String details1(byte[] response){
		String resp=new String(response);
		String total=Split(resp,"success");
		System.out.println(total);
		ret="ACC NO:"+Split(resp,"accid")+"\n\n"+"USER ID:"+Split(resp,"userid");
		if(Split(resp,"accid").equals(""))	{			
		}
		else if(Split(resp,"accid").equals("***"))	{			
		}
		return ret;
	}

	public String Split(String splitStr,String find){
		String rettur="";
		int startingTag=splitStr.indexOf("<"+find+">");
		int endingTag=splitStr.indexOf("</"+find+">");
		if(startingTag!=-1 && endingTag!=-1){
			rettur=splitStr.substring(startingTag+find.length()+2,endingTag);
		}
		return rettur;
	}
	
	public String recognitiondetails(byte[] recognition1){
		String resp1=new String(recognition1);
		String rec=Split(resp1,"result");
		System.out.println("recognition value....."+rec);
		return rec;
	}

	
	public String sigcheck(byte[] recognition1){
		String resp1=new String(recognition1);
		String rec=Split(resp1,"signaturecheck");
		if(Split(resp1,"signaturecheck").equals("Signature Not Matching")){
			rec="Signature Not Matching";
		}
		return rec;
	}
	
	public String homedetails1(byte[] successfull){
		String resp1=new String(successfull);
		String rec=Split(resp1,"useridsuccess");
		if(Split(resp1,"useridsuccess").equals(""))
			rec="";
		return rec;
	}
	
	public String homedetails(byte[] successfull){
		String resp1=new String(successfull);
		String rec=Split(resp1,"successfull");
		System.out.println("recognition value....."+rec);
		if(Split(resp1,"successfull").equals(""))
			rec="";
		return rec;
	}
	
	public String balancedetails(byte[] balance){
		String resp1=new String(balance);
		String rec=Split(resp1,"balance");
		System.out.println("recognition value....."+rec);
		if(Split(resp1,"balance").equals(""))
			rec="";
		return rec;
	}
	
	public String resultpagedetails(byte[] newbalance){
		String resp1=new String(newbalance);
		String rec=Split(resp1,"newbalance");
		System.out.println("newbalance value....."+rec);
		return rec;
	}

}