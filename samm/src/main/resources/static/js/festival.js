
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
const url = window.location.href;

let reviewStar = $('.reviewStar').text();

function hiddenReviewlist() {
	let size = $('#size').val();
	for(i=0; i < size; i++ ){
		if( i > 2){
			$('.comment'+i).addClass("hidden")
		}
	}
	$('#moreReview').click(function(){
		showReviewlist();
	})

}

function showReviewlist() {
	let size = $('#size').val();
	for(i=3; i < size; i++ ){
		$('.comment'+i).removeClass("hidden")
	}
	$('#moreReview').hide();

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

		if ($('#uuid').val() == null || $('#uuid').val() == "") {
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

	if ($('#uuid').val() == null) {
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

function getWish() {
	let loginuser = $('#loginuser').val();
	let fid = $('#fid').val();
	if (loginuser != null) {
		$.ajax({
			url: "getWish",
			data: {
				"loginuser": loginuser,
				"fid": fid
			},
			success: function(data) {
				$('.heart').children().addClass(data);
			},
			fail: function() {
				alert("error")
			}
		})
	} else {
		$('.heart').children().addClass("bi-heart");
	}
}


function registerWish() {
	let loginuser = $('#loginuser').val();
	let fid = $('#fid').val();
	if (loginuser == null || loginuser == "") {
		alert("login 후 이용 가능합니다");
	} else {
		if ($('.heart').children().hasClass("bi-heart")) {
			$.ajax({
				url: "registerWish",
				data: {
					"uid": loginuser,
					"fid": fid
				},
				success: function(data) {
					alert(data)
				}
			})
			$('.heart').children().toggleClass("bi-heart");
			$('.heart').children().toggleClass("bi-heart-fill");
		} else {
			console.log("bye")
			$.ajax({
				url: "deleteWish",
				data: {
					"uid": loginuser,
					"fid": fid
				},
				success: function(data) {
					alert(data)
				}
			})
			$('.heart').children().toggleClass("bi-heart-fill");
			$('.heart').children().toggleClass("bi-heart");
		}

	}
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

function paintingCommentStar() {
	const size = $('#size').val();
	for (i = 0; i < size; i++) {
		let star = $('#review' + i).val();
		for (j = 0; j < star; j++) {
			$('#comment' + i).children().eq(j).addClass("star");
		}
	}
}

function updateStarPaiting(num, index) {
	$('#comment' + index).children().removeClass('star');
	for (j = 0; j <= num; j++) {
		$('#comment' + index).children().eq(j).addClass("star");
	}
	var stars = document.getElementById('comment' + index).getElementsByClassName('star').length;
	$('#review' + index).val(stars);
	console.log($('#review' + index).val());
}

function updateReview(pnum, index) {
	const comment = $('#commnetText' + pnum)
	const commentarea = $('#comment-textarea' + pnum)
	let commentStar = $('#comment' + index);

	comment.hide();
	commentarea.removeClass("hidden");
	commentStar.children().eq(0).click(function() {
		updateStarPaiting(0, index);
	})
	commentStar.children().eq(1).click(function() {
		updateStarPaiting(1, index);
	})
	commentStar.children().eq(2).click(function() {
		updateStarPaiting(2, index);
	})
	commentStar.children().eq(3).click(function() {
		updateStarPaiting(3, index);
	})
	commentStar.children().eq(4).click(function() {
		updateStarPaiting(4, index);
	})

	console.log("star::" + star);
	commentarea.keypress(function(e) {
		let star = $('#review' + index).val();
		let content = commentarea.val();
		let uid = $('#uid').val();
		let fid = $('#fid').val();
		if (e.keyCode === 13) {
			e.preventDefault();
			console.log("enter!")
			comment.text($('#comment-textarea' + pnum).val());
			comment.show();
			commentarea.addClass("hidden");
			$.ajax({
				url: "modifyReview",
				data: {
					"star": star,
					"contents": content,
					"pnum": pnum,
					"uid": uid,
					"fid": fid
				},
				success: function(data) {

				}

			})
		}
	});
}

function deleteReview(pnum) {
	if (confirm("삭제하시겠습니까>")) {
		$.ajax({
			url: "deleteReview",
			data: { "pnum": pnum },
			success: function(data) {
				alert(data);
				$('#reviewlist' + pnum).remove();
			}
		})
	}

}


$(document).ready(function() {
	hiddenReviewlist();
	getWish();
	next();
	prev();
	finNav();
	paintingMap(lat, lon);
	$('#moreInfo').click(function() {
		$('.infoDetail').toggleClass('hidden');
	})
	let CSTAR = ".cstar";
	$(CSTAR).click(function(event) {
		if (event.target.classList.contains('4')) {
			if ($(CSTAR).hasClass('star')) {
				$(CSTAR).removeClass("star");
				$(CSTAR).addClass("star");
			} else {
				$(CSTAR).addClass("star");
			}
		} else if (event.target.classList.contains('3')) {
			if ($(CSTAR).hasClass('star')) {
				$(CSTAR).removeClass("star");
				$(CSTAR).eq(0).toggleClass("star");
				$(CSTAR).eq(1).toggleClass("star");
				$(CSTAR).eq(2).toggleClass("star");
				$(CSTAR).eq(3).toggleClass("star");
			} else {
				$(CSTAR).eq(0).toggleClass("star");
				$(CSTAR).eq(1).toggleClass("star");
				$(CSTAR).eq(2).toggleClass("star");
				$(CSTAR).eq(3).toggleClass("star");
			}
		} else if (event.target.classList.contains('2')) {
			if ($(CSTAR).hasClass('star')) {
				$(CSTAR).removeClass("star");
				$(CSTAR).eq(0).toggleClass("star");
				$(CSTAR).eq(1).toggleClass("star");
				$(CSTAR).eq(2).toggleClass("star");
			} else {
				$(CSTAR).eq(0).toggleClass("star");
				$(CSTAR).eq(1).toggleClass("star");
				$(CSTAR).eq(2).toggleClass("star");
			}
		} else if (event.target.classList.contains('1')) {
			if ($(CSTAR).hasClass('star')) {
				$(CSTAR).removeClass("star");
				$(CSTAR).eq(0).toggleClass("star");
				$(CSTAR).eq(1).toggleClass("star");
			} else {
				$(CSTAR).eq(0).toggleClass("star");
				$(CSTAR).eq(1).toggleClass("star");
			}
		} else if (event.target.classList.contains('0')) {
			if ($(CSTAR).hasClass('star')) {
				$(CSTAR).removeClass("star");
				$(CSTAR).eq(0).addClass("star");
			} else {
				$(CSTAR).eq(0).addClass("star");
			}
		}
		var stars = document.getElementById("chooseStar").getElementsByClassName('star').length;
		$('#star').val(stars)
	})

	submitReview();
	paintingCommentStar();
});


function toggleStar() {


}
