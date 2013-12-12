<%@page import="java.util.*" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Package</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<%
	Vector vc1=(Vector)session.getAttribute("idtotalvalues");
	System.out.println("new values are..."+vc1);
	%>
<div id="header-bg">
  <div id="header"> <img src="images/logo1 copy.jpg" alt="" width="205" height="62" class="logo" />
    <div id="login-bg">
      <div id="login-area">
        
      </div>
    </div>
    <br class="spacer" />
  </div>
</div>
<div id="navigation-bg">
  <div id="navigation">
    <ul class="mainMenu">
      <li><a href="index.jsp">Home</a></li>
      <li><a href="aboutus.jsp">About Us</a></li>
      <li><a href="mapview.jsp">View Us</a></li>
      <li><a href="contactus.jsp">Contact Us</a></li>
    </ul>
    <br class="spacer" />
    <ul class="">
     <li class="noBg"><marquee><font color="">Your Citi never sleeps. Because the Citi never sleeps.</font></marquee></li>
    </ul>
    <br class="spacer" />
  </div>
</div>
<div id="ourCompany-bg">
  <div id="ourCompany-part">
    <h2 class="ourCompany-hdr">Admin Deposit Page</h2>
    <div id="ourCompany-leftPart">
      <h2 class="faq-Hdr">Corporate Office:</h2>
      <ul class="ourCompany-list"><img src="images/abgout_us.jpg" width="228" height="170"/>
        <font size="2">
        <br/><br/><br/><br/>
        <html:form action="/AdminDeposit">
     <table align="center" border="2"  cellpadding="3">
     
        
<font color="blue"><tr style="color:white">
<th><font color="blue">USERNAME</font></th>
<th><font color="blue">GENDER</font></th>
<th><font color="blue">E-MAIL ID</font></th>
<th><font color="blue">MOBILE NUMBER</font></th>
<th><font color="blue">ACC NUMBER</font></th>
<th><font color="blue">CURRENT BALANCE</font></th>
<th><font color="blue">BANK BRANCH</font></th>
<th><font color="blue">DEPOSIT AMOUNT</font></th>
</tr></font>   
<%
for(int ro=0;ro<vc1.size();ro++)
{ 
		String dd=vc1.get(ro).toString();
		dd=dd.replace("[","");
		dd=dd.replace("]","");
		System.out.println("dd is..."+dd);
		StringTokenizer token1=new StringTokenizer(dd,"//");
		String s1=token1.nextToken();
		String s2=token1.nextToken();
		String s3=token1.nextToken();
		String s4=token1.nextToken();
		String s5=token1.nextToken();
		String s6=token1.nextToken();
		String s7=token1.nextToken();
%>

<tr>
		<td><html:text name="" property="" value="<%=s1%>" readonly="true"/></td>
		<td><html:text name="" property="" value="<%=s2%>" readonly="true"/></td>
		<td><html:text name="" property="" value="<%=s3%>" readonly="true"/></td>
		<td><html:text name="" property="" value="<%=s4%>" readonly="true"/></td>
		<td><html:text name="AdminDepositForm" property="adminaccnumber" value="<%=s5%>" readonly="true"/></td>
		<td><html:text name="" property="" value="<%=s6%>" readonly="true"/></td>
		<td><html:text name="AdminDepositForm" property="adminbranch" value="<%=s7%>"/></td>
		<td><html:text name="AdminDepositForm" property="adminmoney"/></td>
</tr>
<% } %>
		   <div style="color:red">
     	<html:errors />
	   </div>
	</table>
	<br/>
	<center><html:submit value="Enter" style="background-color:black; color:white;"/>
    <html:reset value="Reset" style="background-color:black; color:white;"/></center>
  </html:form>
        
        </font> 
      </ul>
    </div>
        <div id="ourCompany-leftPart">
        <!-- HTML codes by Quackit.com -->
<font size="2" color="red"><marquee behavior="alternate">Unit Trust of India (UTI - I)</marquee></font>
<font size="4" color="blue">
</font>
        
        </div>
    <div id="ourCompany-rightPart">
      <p class="moreInfo-Text"><BR></BR></p>
      
    </div>
    <br class="spacer" />
  </div>
</div>
<div id="futurePlan-bg">
<div id="footer-bg">
  <div id="footer-menu">
    <ul class="footMenu">
      <li><a href="index.jsp">Home</a></li>
      <li><a href="aboutus.jsp">About Us</a></li>
      <li><a href="mapview.jsp">View Us</a></li>
      <li><a href="contactus.jsp">Contact Us</a></li>
    </ul>
    <br class="spacer" />
    <p class="copyright">Copyright &copy; Citi 2010 All Rights Reserved</p>
    <p class="copyright topPad">Powered by <a href="http://templatekingdom.com">vinnetworks.com</a></p>
  </div>
</div>
</body>
</html>
