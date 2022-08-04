

$(document).ready(function() {
	$('#signin_bt').click(function() {
		let id = $('#id').val();
		let pwd = $('#pwd').val();
		if (id == null || id == "") {
			alert("id를 입력해주세요.")
			return;
		}
		if (pwd == null || pwd == "") {
			alert("password를 입력해주세요.")
			return;
		}
		logincheck();

	});
	$('#signout').click(function() {
		confirm("로그아웃 하시겠습니까?")
		kakaoLogout();
	})

});

function submitLogin() {
	$('#login_form').attr({
		'method': 'post',
		'action': '/loginimpl'
	});
	$('#login_form').submit();
}

function logincheck() {
	let id = $('#id').val();
	let pwd = $('#pwd').val();
	$.ajax({
		url: "/loginCheck",
		data: {
			"id": id,
			"pwd": pwd
		},
		success: function(data) {
			if(data != "true"){
				alert(data);	
			}else{
				submitLogin();
			}			
		}
	})
}

window.Kakao.init('1f0d8b55d9f1a8931df0a3ae663baf4e');
function kakaoLogin() {

	window.Kakao.Auth.login({
		scope: 'profile_nickname,profile_image, account_email, gender', //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
		success: function(response) {
			console.log(response) // 로그인 성공하면 받아오는 데이터
			window.Kakao.API.request({ // 사용자 정보 가져오기 
				url: '/v2/user/me',
				success: (res) => {
					const kakao_account = res.kakao_account;

					console.log(kakao_account);
					submitkakao(kakao_account);
				}
			});

		},
		fail: function(error) {
			console.log(error);
		}
	});
}



function kakaoLogout() {

	if (!Kakao.Auth.getAccessToken()) {
		console.log('Not logged in.');
		window.location.href = '/logout'
		return;
	}
	Kakao.Auth.logout(function(response) {
		console.log(response + ' logout');
		window.location.href = '/logout'
	});
}


function submitkakao(kakao_account) {
	const kakaojson = JSON.stringify(kakao_account)
	const profile = JSON.stringify(kakao_account.profile)
	console.log(kakao_account.profile);
	console.log("kakaojson::" + kakaojson);
	console.log("kakao_account::" + kakao_account);
	$.ajax({
		url: "submitkakao",
		data: {
			"kakao": kakaojson,
			"profile": profile
		},
		success: function(data) {
			window.location.href = '/' //리다이렉트 되는 코드
		}
	})


}
