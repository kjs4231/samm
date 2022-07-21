
let cnt = 0;
let map;
let container;
let lon = $('#lon').val();
let lat = $('#lat').val();
var locPosition = new kakao.maps.LatLng(lat, lon)
var length = document.getElementsByClassName("carousel-item").length;
function next() {
	$('#next').click(function() {
		cnt++;
		if (cnt >= length) {
			cnt = 0;
		}
		$('.carousel-item').eq(cnt - 1).removeClass('active');
		$('.carousel-item').eq(cnt).addClass('active');
	});
}

function prev() {
	$('#prev').click(function() {
		$('.carousel-item').eq(cnt).removeClass('active');
		cnt--;
		if (cnt < 0) {
			cnt = length - 1;
		}
		$('.carousel-item').eq(cnt).addClass('active');
	})
}


function paintingMap(lat, lon) {
	container = document.getElementById('dmap'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(lat, lon), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	};
	map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	displayMarker(locPosition)
}

function displayMarker(locPosition) {
	// 마커를 생성합니다
	marker = new kakao.maps.Marker({
		map: map,
		position: locPosition
	});
	map.setCenter(locPosition);
}

	$(document).ready(function() {
		next();
		prev();
		paintingMap(lat, lon);
		$('#moreInfo').click(function(){
			$('.infoDetail').toggleClass('hidden');
		})
	});