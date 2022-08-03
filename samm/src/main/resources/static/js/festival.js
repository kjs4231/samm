
let cnt = 0;
let map;
let container;
let lon = $('#lon').val();
let lat = $('#lat').val();
var locPosition = new kakao.maps.LatLng(lat, lon)
var length = document.getElementsByClassName("carousel-item").length;

let title = $('#ftitle').text();
let overview = $('.header--info').text();
let images = $('#festivalimg').val();
let url = window.location.href;

let reviewStar = $('.reviewStar').text();

function createReviewStars(){
	
}

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


function submitReview() {
	$('#sumbit-review').click(function() {
		if ($('#star').val() == null || $('#star').val() == "") {
			alert("별점을 입력해주세요!")
			$('#star-warnig').text("<--- 별점을 입력해주세요!")
			return;
		}
		if ($('#contents').val() == null || $('#contents').val() == "") {
			alert("내용을 입력해주세요!")
			return;
		}

		if ($('#uid').val() == null) {
			alert("login후 이용 가능합니다.")
		} else {
			$('#festival--review').attr({
				"method": "post",
				"action": "/reviewimpl"
			})
			$('#festival--review').submit();
		}
	})

}

function filecheck() {

	if ($('#uid').val() == null) {
		alert("login후 이용 가능합니다.")
	} else {
		$('#file-form').attr({
			'enctype': 'multipart/form-data',
			'method': 'post',
			'action': '/imgimpl'
		});
		$('#file-form').submit();
		alert("사진 요청이 완료 되었습니다. 관리자가 확인 후 반영 예정입니다.")
	}


};


function registerWish() {
	$('.heart').children().toggleClass("bi-heart");
	$('.heart').children().toggleClass("bi-heart-fill");

}


function shareTwitter() {
	var sendText = title; // 전달할 텍스트
	var sendUrl = url; // 전달할 URL
	window.open("https://twitter.com/intent/tweet?text=" + sendText + "&url=" + sendUrl, 'Twitter',
		'top=10,left=10,height=300, status=no, menubar=no, toolbar=no, resizable=no');
}


function shareFacebook() {
	var sendUrl = url; // 전달할 URL
	window.open("http://www.facebook.com/sharer/sharer.php?u=" + sendUrl, 'facebook',
		'top=10,left=10,height=300, status=no, menubar=no, toolbar=no, resizable=no');
}


function shareKakao() {
	console.log("HI KAKAO")
	// 사용할 앱의 JavaScript 키 설정
	Kakao.init('1f0d8b55d9f1a8931df0a3ae663baf4e');

	// 카카오링크 버튼 생성
	Kakao.Link.createDefaultButton({
		container: '.kakao', // 카카오공유버튼ID
		objectType: 'feed',
		content: {
			title: title, // 보여질 제목
			description: overview, // 보여질 설명
			imageUrl: images, // 콘텐츠 URL
			link: {
				mobileWebUrl: url,
				webUrl: url
			}
		}
	});
}

function finNav() {
	const header = document.querySelector('.imgmenu');
	const headerheight = header.clientHeight;

	document.addEventListener('scroll', onScroll, { passive: true })

	function onScroll() {
		const scrollposition = pageYOffset;//스크롤 위치
		const nav = document.querySelector('.detail-navbar');//네비게이션
		if (headerheight <= scrollposition) {//만약 헤더높이<=스크롤위치라면
			nav.classList.add('menufixed')//fix클래스를 네비에 추가
		}
		else {//그 외의 경우
			nav.classList.remove('menufixed');//fix클래스를 네비에서 제거
		}
	}
}


$(document).ready(function() {
	next();
	prev();
	finNav();
	paintingMap(lat, lon);
	$('#moreInfo').click(function() {
		$('.infoDetail').toggleClass('hidden');
	})
	$('.fa-star').click(function(event) {
		if (event.target.classList.contains('4')) {
			if ($('.fa-star').hasClass('star')) {
				$('.fa-star').removeClass("star");
				$('.fa-star').addClass("star");
			} else {
				$('.fa-star').addClass("star");
			}
		} else if (event.target.classList.contains('3')) {
			if ($('.fa-star').hasClass('star')) {
				$('.fa-star').removeClass("star");
				$('.fa-star').eq(0).toggleClass("star");
				$('.fa-star').eq(1).toggleClass("star");
				$('.fa-star').eq(2).toggleClass("star");
				$('.fa-star').eq(3).toggleClass("star");
			} else {
				$('.fa-star').eq(0).toggleClass("star");
				$('.fa-star').eq(1).toggleClass("star");
				$('.fa-star').eq(2).toggleClass("star");
				$('.fa-star').eq(3).toggleClass("star");
			}
		} else if (event.target.classList.contains('2')) {
			if ($('.fa-star').hasClass('star')) {
				$('.fa-star').removeClass("star");
				$('.fa-star').eq(0).toggleClass("star");
				$('.fa-star').eq(1).toggleClass("star");
				$('.fa-star').eq(2).toggleClass("star");
			} else {
				$('.fa-star').eq(0).toggleClass("star");
				$('.fa-star').eq(1).toggleClass("star");
				$('.fa-star').eq(2).toggleClass("star");
			}
		} else if (event.target.classList.contains('1')) {
			if ($('.fa-star').hasClass('star')) {
				$('.fa-star').removeClass("star");
				$('.fa-star').eq(0).toggleClass("star");
				$('.fa-star').eq(1).toggleClass("star");
			} else {
				$('.fa-star').eq(0).toggleClass("star");
				$('.fa-star').eq(1).toggleClass("star");
			}
		} else if (event.target.classList.contains('0')) {
			if ($('.fa-star').hasClass('star')) {
				$('.fa-star').removeClass("star");
				$('.fa-star').eq(0).addClass("star");
			} else {
				$('.fa-star').eq(0).addClass("star");
			}
		}
		var stars = document.getElementsByClassName('star').length;
		$('#star').val(stars)
	})

	submitReview();

});

