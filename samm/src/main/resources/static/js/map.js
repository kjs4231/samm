let container;
let map;
var markers = new Map();
var elm_overlays = new Map();
var customOverlay;

let geoloc_lat = 33.450701;
let geoloc_lng = 126.570667;
let currentKeyword = "";
let maxPage;
let startPage;
let endPage;

function checkNull(o) {
	return (o == null || o.length <= 0 || o == undefined) ? true : false;
};

function paintingMap(lat, lng) {
	container = document.getElementById('map-kakao'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(lat, lng), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	};
	map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
};

function getGeolocation() {
	// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
	if (navigator.geolocation) {
		// GeoLocation을 이용해서 접속 위치를 얻어옵니다
		navigator.geolocation.getCurrentPosition(function (position) {
			geoloc_lat = position.coords.latitude, // 위도
				geoloc_lng = position.coords.longitude; // 경도
			var locPosition = new kakao.maps.LatLng(geoloc_lat, geoloc_lng) // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
			console.log(geoloc_lat, geoloc_lng)
			console.log("locPosition::" + locPosition);

			paintingMap(geoloc_lat, geoloc_lng)
		});
	} else {
		paintingMap(geoloc_lat, geoloc_lng);
	};
};


function elm_searchmap(contentid, mapx, mapy, firstimage, eventstartdate, eventenddate, title, addr1) {
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
	var elm = '<a class="l-card item container" href="javascript:void(0)" onclick="openOverlay(' + contentid + ',' + mapx + ',' + mapy + ',' + 1 + '); " mapx="' + mapx + '" mapy="' + mapy + '" contentid="' + contentid + '">' +
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
	return elm;
};


function elm_overlay(contentid, eventstartdate, eventenddate, title, addr1, infotext) {
	var gen_imageheader = '<a class="img">';
	var elm =
		'<div class="project-wrap map-overlay"' +'contentid="'+contentid+'">' +
		gen_imageheader + '<span class="price">진행중</span>' + '</a>' +
		'<a href="#" class="map-overlayclose" onclick="closeOverlay()" title="닫기"></a>' +
		'<div class="text p-4">' +
		'<span class="days"><span>' + eventstartdate + '</span> ~ <span>' + eventenddate + '</span></span>' +
		'<h3 class="animate"><a href="/detail?contentid=' + contentid + '"><input name="contentid" hidden value="' + contentid + '">' + title + '</a></h3>' +
		'<span class="detail">' + infotext + '</span>' +
		'<p class="location"><span class="fa fa-map-marker"></span> <span>' + addr1 + '</span></p>' +
		'<a class="btn-map-gobtn" href="/detail?contentid=' + contentid + '">이 축제 가기</a>' +
		'<div class="detail-icon">' +
		'<a class="heart dicon" onClick="registerWish()"><i class="bi bi-heart"></i></a> ' +
		'<span class="dicon" data-toggle="modal" data-target="#myModal"><a class="share"><i class="bi bi-share" ></i></a></span>' +
		'</div>' +
		'</div>' +
		'</div>';
	return elm;
};

function openOverlay(contentid, mapx, mapy, isPanTo) {
	customOverlay.setMap(null);
	var content = elm_overlays.get(contentid);
	var position = new kakao.maps.LatLng(mapy, mapx);
	customOverlay = new kakao.maps.CustomOverlay({
		position: position,
		content: content,
		xAnchor: 0.5,
		yAnchor: 0.95
	});
	customOverlay.setMap(map);

	if (isPanTo == 1) {
		map.panTo(position);
	};
	let setOverlay = $('.project-wrap.map-overlay[contentid='+contentid+']')
	if (setOverlay.clientWidth < setOverlay.find('h3').clientWidth) {
		setOverlay.find('h3').addClass("animate");
	}
};

function closeOverlay(){
	customOverlay.setMap(null);
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
	markers.forEach((value) => {
		value.setMap(null);
	});
	markers = new Map();
};

// function removeInfowindow() {
// 	infowindows.forEach((value) => {
// 		value.close();
// 	});
// 	infowindows = new Map();
// };

function searchmap(keyword, page, mapx, mapy) {
	$.ajax({
		url: '/searchmap',
		data: { keyword: keyword, page: page, mapx: mapx, mapy: mapy },
		method: 'get',
		dataType: 'json',
		success: function (json) {
			bounds = new kakao.maps.LatLngBounds();
			var result = '';
			removeMarker();
			if (JSON.stringify(json) === '{}' || JSON.stringify(json) === '[]') {
				result = result.concat('<a class="l-card item l-card-none container"><div>없습니다</div></a>');
			} else {
				$.each(json, function (i, element) {
					result = result.concat(elm_searchmap(element.contentid, element.mapx, element.mapy, element.firstimage, element.eventstartdate, element.eventenddate, element.title, element.addr1));
					var locPosition = new kakao.maps.LatLng(element.mapy, element.mapx);
					bounds.extend(locPosition);
					var marker = new kakao.maps.Marker({
						position: locPosition,
						clickable: true
					});
					var overlay = elm_overlay(element.contentid, element.eventstartdate, element.eventenddate, element.title, element.addr1, element.infotext)
					marker.setMap(map);
					markers.set(element.contentid, marker);
					elm_overlays.set(element.contentid, overlay);
					kakao.maps.event.addListener(marker, 'click', function () {
						openOverlay(element.contentid, element.mapx, element.mapy);
					});
				});
				map.setBounds(bounds);
			};
			$('#map-searchlist').html(result);
			$('#map-searchlist').removeAttr("style");
		}
	})
};

function elm_countsearchmap(count, page) {
	maxPage = parseInt(count / 12) + 1;
	startPage = parseInt((page - 1) / 5) * 5 + 1;
	endPage = startPage + 4;
	if (endPage > maxPage) {
		endPage = maxPage;
	}

	var pager = '';
	for (var i = startPage; i <= endPage; i++) {
		if (i == page) {
			pager = pager + '<li class="active"><span>' + i + '</span></li> '
		} else {
			pager = pager + '<li><a href="javascript:void(0)" onclick="pagemove(' + i + ');">' + i + '</a></li> '
		}
	};

	var elm = $('<div />', { "class": "row mt-3" }).html(
		$('<div />', { "class": "col text-center" }).html(
			$('<div />', { "class": "block-27" }).html(
				$('<ul />').html(
					$('<li class="pager-prev" />').html($('<a />').text('<'))
						.append('&nbsp;')
						.append(pager)
						.append($('<li class="pager-next" />').html($('<a />').text('>')))
				)
			)
		));
	return $(elm).prop('outerHTML');
}

function countsearchmap(keyword, page) {
	$.ajax({
		url: '/countsearchmap',
		data: { keyword: keyword },
		method: 'get',
		dataType: 'text',
		success: function (count) {
			var result = '';
			result = result.concat(elm_countsearchmap(count, page));
			$('#map-searchpager').html(result);
			$('#map-searchpager').removeAttr("style");
		}
	})
};

function searchmapinput() {
	currentKeyword = $('#map-searchform input[name="search"]').val();
	searchmap(currentKeyword, 1, geoloc_lng, geoloc_lat);
	countsearchmap(currentKeyword, 1);
};

function pagemove(page) {
	searchmap(currentKeyword, page, geoloc_lng, geoloc_lat);
	countsearchmap(currentKeyword, page);
};

$(document).ready(function () {
	getGeolocation();
	customOverlay = new kakao.maps.CustomOverlay({
		position: new kakao.maps.LatLng(geoloc_lat, geoloc_lng),
		content: '<div></div>'
	});
	customOverlay.setMap(null);
});

$(document).on("click", ".btn-search", function () {
	searchmapinput();
});

$('#map-searchform input[name="search"]').keyup(function (e) {
	if (e.keyCode == 13) {
		searchmapinput();
	}
});

$(document).on("click", ".pager-prev a", function () {
	// alert($('#map-searchpager li').eq(1).text());
	// alert($('#map-searchpager li').eq(-2).text());
	if ($('#map-searchpager li').eq(1).text() == 1) {

	} else {
		countsearchmap(currentKeyword, startPage - 5);
	}
});

$(document).on("click", ".pager-next a", function () {
	if ($('#map-searchpager li').eq(-2).text() == maxPage) {

	} else {
		countsearchmap(currentKeyword, startPage + 5);
	}
});

// function openInfowindow(contentid, mapx, mapy) {
// 	markers.forEach((value, key) => {
// 		if (key == contentid) {
// 			var marker = value;
// 			infowindows.forEach((value, key) => {
// 				value.close();
// 				if (key == contentid) {
// 					value.open(map, marker);
// 				}
// 			})
// 		}
// 	})
// 	if (!(checkNull(mapx) || checkNull(mapy))) {
// 		locPosition = new kakao.maps.LatLng(mapy, mapx);
// 		map.panTo(locPosition);
// 	}
// };