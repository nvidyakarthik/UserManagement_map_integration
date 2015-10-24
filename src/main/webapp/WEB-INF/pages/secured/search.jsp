<!DOCTYPE html>
<html>
<head>

<title>Search Nearby Universities</title>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true&libraries=places"></script>

<style>
#map {
	height: 400px;
	width: 600px;
	border: 1px solid #333;
	margin-top: 0.6em;
}
</style>
<style>
/* html,body,#map-canvas {
	height: 100%;
	margin: 0px;
	padding: 0px
} */

#panel {
	position: absolute;
	top: 5px;
	left: 50%;
	margin-left: -180px;
	z-index: 5;
	background-color: #fff;
	padding: 5px;
	border: 1px solid #999;
}
</style>

<script>
	var map;
	var geocoder;
	var infowindow;
	var pyrmont;
	var temp1 = new Array();

	function initialize() {
		geocoder = new google.maps.Geocoder();
		pyrmont = new google.maps.LatLng(33.45353, -112.07304);
		map = new google.maps.Map(document.getElementById('map'), {
			mapTypeId : google.maps.MapTypeId.ROADMAP,
			center : pyrmont,
			zoom : 15
		});

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
	function searchNearby() {
		//initialize();
		alert('insidesearchNearby');
		var address = document.getElementById('address').value;
		alert('address value insidesearchNearby is '+address);
		alert('geocode insidesearchNearby is '+ results[0].geometry.location);
		var request = {
			//                location: pyrmont,
			//location : new google.maps.LatLng(33.45353, -112.07304),
			location:results[0].geometry.location,//seems to be the problem, it is not picking the latest search location. It is fixed on the initial reference location.
			radius : 50000,
			//       types: ['school']
			types : [ txtPlaceType.value ]
		};

		infowindow = new google.maps.InfoWindow();
		var service = new google.maps.places.PlacesService(map);
		service.nearbySearch(request, GetResults);
	}

	function GetResults(results, status) {
		if (results.length > 0) {

			if (status == google.maps.places.PlacesServiceStatus.OK) {
				for (var i = 0; i < results.length; i++) {

					//alert(results[i].geometry.location);
					temp1[i] = results[i].geometry.location;

					createMarker(results[i]);

				}
			}
		} else {
			alert("No result.");
		}

		document.getElementById("result").value = temp1;
	}

	function createMarker(place) {
		var placeLoc = place.geometry.location;
		var marker = new google.maps.Marker({
			map : map,
			position : placeLoc
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
	<div id="panel">
		<input id="address" type="text" value="Arizona State University, AZ" />
		<input type="button" value="Search" onclick="codeAddress()" />
	</div>
	<div id="Search">
		<label id="lblSearchLabel">Type</label> <input id="txtPlaceType"
			type="text" style="width: 100px; height: 20px;" /> <input
			id="btnSearch" type="button" value="Search"
			onclick="return searchNearby()" /> Results<input id="result"
			type="text" />
	</div>

	<div id="map"></div>
</body>
</html>