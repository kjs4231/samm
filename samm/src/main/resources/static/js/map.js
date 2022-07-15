let container;
let map;
var marker;

function paintingMap(lat, lon) {
	container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(lat, lon), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	};
	map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
}

function getGeoloacation() {
	// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
	if (navigator.geolocation) {

		// GeoLocation을 이용해서 접속 위치를 얻어옵니다
		navigator.geolocation.getCurrentPosition(function(position) {

			var lat = position.coords.latitude, // 위도
				lon = position.coords.longitude; // 경도

			var locPosition = new kakao.maps.LatLng(lat, lon) // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다

			console.log(lat, lon)
			console.log("locPosition::" + locPosition);
			// 마커와 인포윈도우를 표시합니다
			paintingMap(lat, lon)

			displayMarker(locPosition);

		});

	} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

		var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
			message = 'geolocation을 사용할수 없어요..'
		paintingMap(33.450701, 126.570667);
		displayMarker(locPosition);
	}

}



// 지도에 마커와 인포윈도우를 표시하는 함수입니다
function displayMarker(locPosition) {
	// 마커를 생성합니다
	marker = new kakao.maps.Marker({
		map: map,
		position: locPosition
	});
	/*	var iwContent = message, // 인포윈도우에 표시할 내용
			iwRemoveable = true;
	
		// 인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
			content: iwContent,
			removable: iwRemoveable
		});
	
		// 인포윈도우를 마커위에 표시합니다 
		infowindow.open(map, marker);*/

	// 지도 중심좌표를 접속위치로 변경합니다
	map.setCenter(locPosition);
}


/*
// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {

	// 클릭한 위도, 경도 정보를 가져옵니다 
	var latlng = mouseEvent.latLng;

	var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
	message += '경도는 ' + latlng.getLng() + ' 입니다';

	var resultDiv = document.getElementById('result');
	resultDiv.innerHTML = message;
	alert(message);
});
*/

$(document).ready(function() {
	$('#ftco-navbar').css('display', 'none');
	getGeoloacation();

});