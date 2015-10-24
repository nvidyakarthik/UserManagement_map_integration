<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta charset="utf-8">
<title>Search Map</title>
<style>
html,body,#map-canvas {
	height: 100%;
	margin: 0px;
	padding: 0px
}

#panel {
	position: absolute;
	top: 40px;
	left: 50%;
	margin-left: -180px;
	z-index: 5;
	background-color: #fff;
	padding: 5px;
	border: 1px solid #999;
}
</style>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
<!-- <script>
	var geocoder;
	var map;

	function initialize() {
		geocoder = new google.maps.Geocoder();
		var latlng = new google.maps.LatLng(33.45353, -112.07304);
		var mapOptions = {
			zoom : 8,
			center : latlng
		};
		map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);
	}

	function codeAddress() {
		var address = document.getElementById('address').value;
		var a;
		alert(address);
		geocoder.geocode({
			'address' : address
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				alert(results[0].geometry.location);
				map.setCenter(results[0].geometry.location);
				var marker = new google.maps.Marker({
					map : map,
					position : results[0].geometry.location
				});

				a = results[0].geometry.location;

			} else {
				alert('Geocode was not successful for the following reason: '
						+ status);
			}
			document.getElementById("geocode").value = a;

		});
	}
	google.maps.event.addDomListener(window, 'load', initialize);
</script> -->
<script>
var map;
var infowindow;

function initialize() {
  var pyrmont = new google.maps.LatLng(-33.8665433, 151.1956316);

  map = new google.maps.Map(document.getElementById('map-canvas'), {
    center: pyrmont,
    zoom: 15
  });

  var request = {
    location: pyrmont,
    radius: 500,
    types: ['store']
  };
  infowindow = new google.maps.InfoWindow();
  var service = new google.maps.places.PlacesService(map);
  service.nearbySearch(request, callback);
}

function callback(results, status) {
  if (status == google.maps.places.PlacesServiceStatus.OK) {
    for (var i = 0; i < results.length; i++) {
      createMarker(results[i]);
    }
  }
}

function createMarker(place) {
  var placeLoc = place.geometry.location;
  var marker = new google.maps.Marker({
    map: map,
    position: place.geometry.location
  });

  google.maps.event.addListener(marker, 'click', function() {
    infowindow.setContent(place.name);
    infowindow.open(map, this);
  });
}

google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body>
	
   
    <%@ include file="headerInclude.jsp"%>
    <br><br>
    
	<div id="panel">
	 <form method="get" action="${pageContext.request.contextPath}/secured/save">
			<input id="address" name="location" type="text" value="Arizona State University, AZ" />
		     <input type="button" value="Search" onclick="codeAddress()" />	<br>
			<input id="geocode" name="geocode"/> 
			<input type="submit" value="Save location"/>
		</form>
	</div>
	<div id="map-canvas"></div>
</body>
</html>