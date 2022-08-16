$(document).ready(function() {

	$('#registerbtn').click(function() {
		
		$('.user').attr({
			'method': 'post',
			'action': '/users/addimpl'
		});
		$('.user').submit();
		
		
	});

	$('input[name="id"]').keyup(function() {
		var id = $(this).val();
		$('#id-span').removeClass("warning");
		if (id.length < 6) {
			$("#id-span").text("6자 이상입력해주세요.");
			$("#nextbtn").attr("disabled", true);
			return;
		} else {
			$("#id-span").text("OK");
			$("#nextbtn").attr("disabled", false);
		}
		if (id.length > 20) {
			$("#id-span").text("20자 이내로 입력해주세요.");
			return;
		} else {
			$("#id-span").text("OK");
			$("#nextbtn").attr("disabled", false);
		}
	});

	$('input[name="pwd"]').keyup(function() {
		var pwd = $(this).val();
		var id = $('input[name="id"]').val();
		var pattern1 = /[0-9]/;
		var pattern2 = /[a-zA-z]/;
		var pattern3 = /[~!@#$%^&*()_+]/;
		if (!pattern1.test(pwd) || !pattern2.test(pwd) || !pattern3.test(pwd) || pwd.length < 6) {
			$("#pwd-span").text("영문,숫자,특수기호 포함 8자리 이상 구성하여야 합니다.");
			$("#nextbtn").attr("disabled", true);
			return;
		} else {
			$("#pwd-span").text("OK");
			$("#nextbtn").attr("disabled", false);
		}
		if (pwd.indexOf(id) > 1) {
			$("#pwd-span").text("비밀번호는 ID를 포함할 수 없습니다.");
			return;
		}

	});

	$('input[name="vpwd"]').keyup(function() {
		if ($(this).val() != $('input[name="pwd"]').val()) {
			$("#vpwd-span").text("비밀번호와 일치하지않습니다.");
			$("#nextbtn").attr("disabled", true);
			return;
		} else {
			$("#vpwd-span").text("OK");
			$("#nextbtn").attr("disabled", false);
		}

	});

	$('input[name="phone"]').keyup(function() {
		checkPhone();
	});

	$('input[name="email"]').keyup(function() {

		checkEmail();
	});

	$('#nextbtn').click(function() {
		nextbtnClick();
	})



	$('#prevbtn').click(function() {
		$("#nextbtn").attr("disabled", false);
		if ($('#id-div').hasClass("hidden") && $('#pwd-div').hasClass("hidden") == false) {
			$('#id-div').removeClass("hidden")
			$('#pwd-div').addClass("hidden")
			$('#prevbtn').addClass("hidden")
			$('input[name="id"]').focus();
			return;
		}
		if ($('#pwd-div').hasClass("hidden") && $('#vpwd-div').hasClass("hidden") == false) {
			$('#pwd-div').removeClass("hidden")
			$('#vpwd-div').addClass("hidden")
			$('input[name="pwd"]').focus();
			return;
		}
		if ($('#vpwd-div').hasClass("hidden") && $('#name-div').hasClass("hidden") == false) {
			$('#vpwd-div').removeClass("hidden")
			$('#name-div').addClass("hidden")
			$('input[name="vpwd"]').focus();
			return;
		}
		if ($('#name-div').hasClass("hidden") && $('#email-div').hasClass("hidden") == false) {
			$('#name-div').removeClass("hidden")
			$('#email-div').addClass("hidden")
			$('input[name="name"]').focus();
			return;
		}
		if ($('#email-div').hasClass("hidden") && $('#addr-div').hasClass("hidden") == false) {
			$('#email-div').removeClass("hidden")
			$('#addr-div').addClass("hidden")
			$('input[name="email"]').focus();
			$('#registerbtn').attr("disabled", true);
			return;
		}
		if ($('#addr-div').hasClass("hidden") && $('#phone-div').hasClass("hidden") == false) {
			$('#addr-div').removeClass("hidden")
			$('#phone-div').addClass("hidden")
			$('input[name="addr"]').focus();
			return;
		}
		if ($('#phone-div').hasClass("hidden") && $('#gender-div').hasClass("hidden") == false) {
			$('#phone-div').removeClass("hidden")
			$('#gender-div').addClass("hidden")
			$('input[name="phone"]').focus();
			return;
		}
	})

});

function nextbtnClick() {
	if ($('#id-div').hasClass("hidden") == false && $('#pwd-div').hasClass("hidden")) {
		if ($('input[name="id"]').val() == "") {
			$('#id-span').addClass("warning");
			$('input[name="id"]').focus();
			return;
		}
		idOverlapCheck();
	}
	if ($('#pwd-div').hasClass("hidden") == false && $('#vpwd-div').hasClass("hidden")) {
		if ($('input[name="pwd"]').val() == "") {
			$('#pwd-span').addClass("warning");
			$('input[name="pwd"]').focus();
			return false;
		}
		$('#pwd-div').addClass("hidden")
		$('#vpwd-div').removeClass("hidden")
		$('input[name="vpwd"]').focus();
		return;
	}
	if ($('#vpwd-div').hasClass("hidden") == false && $('#name-div').hasClass("hidden")) {
		if ($('input[name="vpwd"]').val() == "") {
			$('#vpwd-span').addClass("warning");
			$('input[name="vpwd"]').focus();
			return false;
		}
		$('#vpwd-div').addClass("hidden")
		$('#name-div').removeClass("hidden")
		$('input[name="name"]').focus()
		return;
	}
	if ($('#name-div').hasClass("hidden") == false && $('#email-div').hasClass("hidden")) {
		if ($('input[name="name"]').val() == "") {
			$('#name-span').addClass("warning");
			$('input[name="name"]').focus();
			return false;
		}
		$('#name-div').addClass("hidden")
		$('#email-div').removeClass("hidden")
		$('input[name="email"]').focus()
		return;
	}
	if ($('#email-div').hasClass("hidden") == false && $('#addr-div').hasClass("hidden")) {
		if ($('input[name="email"]').val() == "") {
			$('#email-span').addClass("warning");
			$('input[name="email"]').focus();
			return false;
		}
		$('#email-div').addClass("hidden")
		$('#addr-div').removeClass("hidden")
		$('#registerbtn').attr("disabled", false);
		$('input[name="addr"]').focus()
		return;
	}
	if ($('#addr-div').hasClass("hidden") == false && $('#phone-div').hasClass("hidden")) {
		if ($('input[name="addr"]').val() == "") {
			$('#addr-span').addClass("warning");
			$('input[name="addr"]').focus();
			return false;
		}
		$('#addr-div').addClass("hidden")
		$('#phone-div').removeClass("hidden")
		$('input[name="phone"]').focus()
		return;
	}
	if ($('#phone-div').hasClass("hidden") == false && $('#gender-div').hasClass("hidden")) {
		if ($('input[name="phone"]').val() == "") {
			$('#phone-span').addClass("warning");
			$('input[name="phone"]').focus();
			return false;
		}
		$('#phone-div').addClass("hidden")
		$('#gender-div').removeClass("hidden")
		return;
	}
}

function idOverlapCheck() {
	let id = $('input[name="id"]').val();
	$.ajax({
		url: "/idOverlapCheck",
		data: { "id": id },
		success: function(data) {
			if (data == true) {
				$('#id-div').addClass("hidden")
				$('#pwd-div').removeClass("hidden")
				$('#prevbtn').removeClass("hidden")
				$('input[name="pwd"]').focus();
			} else {
				$("#id-span").text("'" + id + "'" + "는 " + "중복된 아이디 입니다.");
				return;
			}
		}

	})

}

function pressEnter(e) {
	if (e.keyCode === 13) {
		if ($("#nextbtn").attr("disabled")) {
			return;
		} else {
			nextbtnClick();
		}
	}
}

addEventListener("keypress", pressEnter);

function checkPhone() {
	var text = $('input[name="phone"]').val();

	var regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
	if (regPhone.test(text) === false) {
		$('#phone-span').text("휴대폰 번호가 아닙니다.")
		$("#nextbtn").attr("disabled", true);
		return;
	} else {
		$('#phone-span').text("OK")
		$("#nextbtn").attr("disabled", false);
	}
}

function checkEmail() {
	var text = $('input[name="email"]').val();

	var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
	if (regEmail.test(text) === false) {
		$('#email-span').text("이메일 양식이 아닙니다.")
		$("#nextbtn").attr("disabled", true);
	} else {
		$('#email-span').text("OK")
		$("#nextbtn").attr("disabled", false);
	}
}