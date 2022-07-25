let container;
let map;
var marker;
// lat = mapy
// lng = mapx

function paintingMap(lat, lng) {
	container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(lat, lng), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	};
	map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
}

function getGeolocation() {
	// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
	if (navigator.geolocation) {
		// GeoLocation을 이용해서 접속 위치를 얻어옵니다
		navigator.geolocation.getCurrentPosition(function (position) {
			var lat = position.coords.latitude, // 위도
				lng = position.coords.longitude; // 경도
			var locPosition = new kakao.maps.LatLng(lat, lng) // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
			console.log(lat, lng)
			console.log("locPosition::" + locPosition);
			// 마커와 인포윈도우를 표시합니다
			paintingMap(lat, lng)
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
	var iwContent = "t", // 인포윈도우에 표시할 내용
		iwRemoveable = true;
	// 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({
		content: iwContent,
		removable: iwRemoveable
	});
	// 인포윈도우를 마커위에 표시합니다 
	infowindow.open(map, marker);
	// 지도 중심좌표를 접속위치로 변경합니다
	map.setCenter(locPosition);
}

function e_searchmap(mapx, mapy, firstimage, eventstartdate, eventenddate, title, addr1) {
	if (typeof firstimage == "undefined" || firstimage == null || firstimage == "") {
		var gen_l_card_left = ''
		var class_l_card_right = 'l-card-noimage col-12';
	} else {
		var gen_l_card_left = '<div class="l-card-left col-3"><img class="l-card-firstimage"' +
			'src="' + firstimage + '"></img></div>';
		var class_l_card_right = 'l-card-right col-9';
	};
	if (typeof addr1 == "undefined" || addr1 == null || addr1 == "") {
		var gen_l_card_txt_location = '';
	} else {
		var gen_l_card_txt_location = '<div class="l-card-txt location"><span class="fa fa-map-marker"></span> ' +
			'<span>' + addr1 + '</span></div>';
	};
	var e = '<a class="l-card item container" href="#" mapx=' + mapx + ' mapy=' + mapy + '>' +
		gen_l_card_left +
		'<div class="' + class_l_card_right + '">' +
		'<div class="inline">' +
		'<div class="l-card-txt dbtable">축제</div>' +
		'<div class="l-card-txt ongoing">진행중</div>' +
		'<div class="l-card-txt days"><span>' + eventstartdate + '</span> ~ ' +
		'<span>' + eventenddate + '</span>' +
		'</div>' +
		'</div>' +
		'<div>' +
		'<div class="l-card-txt title">' + title + '</div>' +
		'</div>' +
		gen_l_card_txt_location +
		'</div>' +
		'</a>' +
		'<div class="mt-2"></div>';
	return e;
};

function searchmap(keyword, page, mapx, mapy) {
	$.ajax({
		url: '/searchmap',
		data: { keyword: keyword, page: page, mapx: mapx, mapy: mapy },
		method: 'get',
		dataType: 'json',
		success: function (json) {
			var result = '';
			$.each(json, function (i, element) {
				result = result.concat(e_searchmap(element.mapx, element.mapy, element.firstimage, element.eventstartdate, element.eventenddate, element.title, element.addr1));
			});
			$('#map-searchlist').html(result);
		}
	})
};

function e_countsearchmap(count, page) {
	var maxPage = parseInt(count / 12) + 1;
	var startPage = parseInt((page - 1) / 5) * 5 + 1;
	var endPage = startPage + 4;
	if (endPage > maxPage) {
		endPage = maxPage;
	}

	var pager = '';
	for (var i = startPage; i <= endPage; i++) {
		if (i == page) {
			pager = pager + '<li class="active"><span>' + i + '</span></li> '
		} else {
			pager = pager + '<li><a href="#">' + i + '</a></li> '
		}
	};

	var e = $('<div />', { "class": "row mt-3" }).html(
		$('<div />', { "class": "col text-center" }).html(
			$('<div />', { "class": "block-27" }).html(
				$('<ul />').html(
					$('<li />').html($('<a />').text('<'))
						.append('&nbsp;')
						.append(pager)
						.append($('<li />').html($('<a />').text('>')))
				)
			)
		));
	return $(e).prop('outerHTML');
}

function countsearchmap(keyword) {
	$.ajax({
		url: '/countsearchmap',
		data: { keyword: keyword },
		method: 'get',
		dataType: 'text',
		success: function (count) {
			var result = '';
			result = result.concat(e_countsearchmap(count, 1));
			$('#map-searchpager').html(result);
		}
	})
};

$(document).ready(function () {
	getGeolocation();
});

$(document).on("click", ".l-card", function () {
	var loc = new kakao.maps.LatLng($(this).attr('mapy'), $(this).attr('mapx'))
	map.panTo(loc);
	displayMarker(loc)
});

$(document).on("click", ".btn-search", function () {
	var keyword = $('#map-searchform input[name="search"]').val();
	searchmap(keyword, 1, 127.052153, 37.5071772);
	countsearchmap(keyword);
});
