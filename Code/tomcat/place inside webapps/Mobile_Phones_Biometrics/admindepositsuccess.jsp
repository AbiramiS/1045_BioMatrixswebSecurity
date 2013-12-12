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
	String depositsuccess=(String)session.getAttribute("depositsuccess");
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
    <h2 class="ourCompany-hdr">Deposit Success Page</h2>
    <div id="ourCompany-leftPart">
      <h2 class="faq-Hdr">Success Page:</h2>
      <ul class="ourCompany-list">&nbsp;&nbsp;&nbsp;<img src="images/imagesgfg.jpeg" width="228" height="170"/>
        <font size="2">
        <br/><br/><br/><br/><br/><br/><br/><br/><br/>
        <%= depositsuccess %> &nbsp;&nbsp;&nbsp;&nbsp;<center> <a href="userlistpage.jsp"><font color="blue">DEPOSIT AGAIN</font></a></center>
        
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
