<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>GMaps.js &mdash; Geolocation</title>
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
  <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
  <script type="text/javascript" src="gmaps.js"></script>
  <link rel="stylesheet" href="http://twitter.github.com/bootstrap/1.3.0/bootstrap.min.css" />
 <!--  <link rel="stylesheet" type="text/css" href="examples.css" /> -->
  <script type="text/javascript">

    $(document).ready(function(){
           
      var latitude = 0;
      var longitude = 0;
      GMaps.geolocate({
        success: function(position){
        	 latitude = position.coords.latitude;
        	 longitude = position.coords.longitude;
        	 document.getElementById('idLatitude').value = latitude;
        	 document.getElementById('idLongitude').value = longitude;
        },
        error: function(error){
          alert('Geolocation failed: '+error.message);
        },
        not_supported: function(){
          alert("Your browser does not support geolocation");
        }
        
       
        
      });
      
    });
  </script>
</head>
<body>
<form id="formMap">
<input  id="idLatitude">
<input  id="idLongitude">
</form>
   
    
</body>
</html>