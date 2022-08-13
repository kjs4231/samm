
$(document).ready(function() {
	$('.mypageEdit').click(function() {
		if ($('.mypage').attr("readonly") == undefined) {
			$('.mypage').attr("readonly", true);
			$('.mypage').addClass("mypagebi")
			$(this).text("Edit");
			updateMypage();
		} else {
			$('.mypage').attr("readonly", false);
			$('.mypage').removeClass("mypagebi")
			$(this).text("Done");
		}

	})
	
})

function changeprofile(){
	alert("프로필 변경이 완료 되었습니다.")
	$('#profileImg').attr({
		"action" : "/userProfile",
		"method" : "post",
		"enctype":"multipart/form-data"
	})
	$('#profileImg').submit();
	
}

function updateMypage(){
	let id = $('#id').val();
	let pwd = $('#pwda').val();
	let email = $('#email').val();
	let phone = $('#phone').val();
	let address = $('#address').val();
	let gender = $('#gender').val();
	let name = $('#name').val();
	$.ajax({
		url : "/updateMypage",
		data : {
			email : email,
			address : address,
			phone : phone,
			gender : gender,
			pwd : pwd,
			id : id,
			name : name
		}, 
		success : function(data){
			alert("기본정보 수정이 완료되었습니다.")
		}
	})
	
}