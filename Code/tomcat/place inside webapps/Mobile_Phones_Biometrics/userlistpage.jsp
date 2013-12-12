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
	Vector vc=(Vector)session.getAttribute("useridvalues");
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
    <h2 class="ourCompany-hdr">Contact Us</h2>
    <div id="ourCompany-leftPart">
      <h2 class="faq-Hdr">Admin Selection:</h2>
      <ul class="ourCompany-list">&nbsp;&nbsp;&nbsp;<img src="images/bank_administration_img.jpg" width="228" height="170"/>
        <font size="2">
        <center>
        <html:form action="/AdminValue">
        <html:select name="AdminValueForm" property="adminidvalue">
        <html:option value="">Select User Id</html:option>
<%
for(int ro=0;ro<vc.size();ro++)
{ 
		String dd=vc.get(ro).toString();
		dd=dd.replace("[","");
		dd=dd.replace("]","");
		StringTokenizer token1=new StringTokenizer(dd,",");
		String s1=token1.nextToken();
%>
			
			<html:option value="<%=s1%>"><%=s1%></html:option>
			
<% } %>
		    
			<html:submit value="Enter" style="background-color:black; color:white;"/>
            <html:reset value="Reset" style="background-color:black; color:white;"/>
			</html:select>
		   <div style="color:red">
     	<html:errors />
	</div>  
</html:form>
</center>     
        </font> 
      </ul>
    </div>
        <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
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
