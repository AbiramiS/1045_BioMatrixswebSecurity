<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>HL7-MAP PAGE</title>
<link href="default.css" rel="stylesheet" type="text/css" />
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
<body onload="callme('CITI BANK','13.034636340483546','80.2139014005661','Ashok Nagar, Chennai.')">

<div id="map_canvas" style="width: 600px; height: 250px;"></div>
<div>
</body>
</html>
