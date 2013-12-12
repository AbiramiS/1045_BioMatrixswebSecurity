<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Package</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="style.css" rel="stylesheet" type="text/css" />
<!--<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAzr2EBOXUKnm_jVnk0OJI7xSosDVG8KKPE1-m51RBrvYughuyMxQ-i1QfUnH94QxWIa6N4U6MouMmBA"></script>-->
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAACezl5OES8dMDmq_DvDePXxR1DJfR2IAi0TSZmrrsgSOYoGgsxBTxN5RDergBZIkIAfmbeWXNM3zdtg"
            type="text/javascript"></script>
<script type="text/javascript" src="js/latlong.js">/* Latitude/Longitude formulae JavaScript implementation */</script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>

    <script type="text/javascript">
          
          function callme(username,fir1,sec1,time) { 
          		
              //alert();
              if (GBrowserIsCompatible()) { // if the browser is compatible with Google Map's
                  var map = document.getElementById("map_canvas"); // Get div element
                  var m = new GMap2(map); // new instance of the GMap2 class and pass in our div location.

                  //m.setCenter(new GLatLng(13.035143,80.213950), 17); // pass in latitude, longitude, and zoom level.
                  

		  m.setCenter(new GLatLng(fir1,sec1), 15); // pass in latitude, longitude, and zoom level.
		  fir=fir1;
		  sec=sec1;
					
                  m.openInfoWindow(m.getCenter(), document.createTextNode("Get a glance of our "+username+" located at "+ time)); // displays the text
                  m.setMapType(G_NORMAL_MAP); // sets the default mode. G_NORMAL_MAP, G_HYBRID_MAP

                  //m.setmapTypeId(google.maps.MapTypeId.ROADMAP);
                  var c = new GMapTypeControl(); // switch map modes
                  m.addControl(c);

                  m.addControl(new GLargeMapControl()); // creates the zoom feature
              }
              else {
                  alert("Upgrade your browser, man!");
              }
             
          }
     </script>
</head>
<body>
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
      <li><a href="map.jsp">View Us</a></li>
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
    <h2 class="ourCompany-hdr">Know About Us</h2>
      <br/>
      <h2 class="moreIdeas-Hdr">Company Overview</h2>
      &nbsp;&nbsp;&nbsp;<h1>OUR SITE MAP</h1>
<table align="left">
<tr>
<td>
<%@include file="map.jsp" %>
</td>
</tr></table>
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
