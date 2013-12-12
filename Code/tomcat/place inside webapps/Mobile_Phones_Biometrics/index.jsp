<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Package</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header-bg">
  <div id="header"> <img src="images/logo1 copy.jpg" alt="" width="205" height="62" class="logo" />
    <div id="login-bg">
      <div id="login-area">
      <html:form action="/Admin">
          <label>Admin Login:</label>
          <html:text name="AdminForm" property="adminname"/>
          <html:password name="AdminForm" property="adminpassword"/>
          <html:submit value="Login" style="background-color:black; color:white;"/>
          <html:reset value="Reset" style="background-color:black; color:white;"/>
          <br class="spacer" />
          <div style="color:red">
     	<html:errors />
	</div>  
</html:form>



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
  
    <ul class="">
     <li class="noBg"><marquee><font color="white">Your Citi never sleeps. Because the Citi never sleeps.</font></marquee></li>
    </ul>
    <br class="spacer" />
  </div>
</div>
<div id="ourCompany-bg">
  <div id="ourCompany-part">
    <h2 class="ourCompany-hdr">Our Bank's Main Features</h2>
    <div id="ourCompany-leftPart">
      <h2 class="faq-Hdr">About</h2>
      <ul class="ourCompany-list">
        <li>Welcome to the Citi Bank US Page.</li>
        <li>CitiBank is found in more than 100 countries, delivering a wide array of banking,
         lending and investment services to individual consumers and small businesses.</li>
      </ul>
      <h2 class="moreIdeas-Hdr">Company Overview</h2>
      <ul class="ourCompany-list noBottomPadding">
        <li>The Citi family of companies includes: </li>
        <li>Citibank, CitiFinancial, Citi Capital Advisors. </li>
        <li>Citi Cards, Citi Investment Research, Citi Microfinance.</li>
        <li>Banamex, Women & Co, CitiMortgage.</li>
      </ul>
    </div>
    <div id="ourCompany-rightPart">
      <h2 class="moreInfo-Hdr">More Informations - Ideas Ahead</h2>
      <ul class="ourCompany-list noBottomPadding">
        <li><a href="http://www.free-css.com/">The Thinking Behind the Money</a></li>
      </ul>
      <p class="moreInfo-Text">Citi Bank. We value your time.</p>
      <h2 class="searchUrl-Hdr">Search Our Url's</h2>
      <ul class="ourCompany-list noBottomPadding">
        <li><a href="http://www.facebook.com/citibank?v=info">http://www.facebook.com/citibank?v=info</a></li>
        <li><a href="http://www.citibank.co.in/">http://www.citibank.co.in/</a></li>
        <li><a href="https://www.citibank.co.in/ssjsps/ssindex.do">https://www.citibank.co.in/ssjsps/ssindex.do</a></li>
        <li><a href="http://www.citibank.com/us/home.htm">http://www.citibank.com/us/home.htm</a></li>
        <li><a href="https://www.billdesk.com/pgidsk/pgijsp/citicard/citibank_card.jsp">https://www.billdesk.com/pgidsk/pgijsp/citicard/citibank_card.jsp</a></li>
      </ul>
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
