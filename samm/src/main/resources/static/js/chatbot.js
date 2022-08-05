let today = new Date();
let days = today.getDay();
let hours = String(today.getHours()).padStart(2,"0");
let minutes = String(today.getMinutes()).padStart(2,"0");
let year = today.getFullYear();
let aaa = "String"
let m = '5';

$(document).ready(function() {
	var day = getTodayLabel();
	var month = getMonthLabel();

	$('#chatbot-div').mouseenter(function() {
		$('#chatbot').css('font-size', '3em');
		$('#chatbot-text').removeClass('hidden');
		$('#chatbot-text').css('color', '#fd7e14');
	});
	$('#chatbot-div').mouseleave(function() {
		$('#chatbot').css('font-size', '2.7em');
		$('#chatbot-text').addClass('hidden');
	});

	$('#chatbot').click(function() {
		window.open('/chatbot', 'ChatBot',
			'top=10,left=10,height=300, status=no, menubar=no, toolbar=no, resizable=no')
	});
	
	// 채팅 메시지 전송
	$("#chat").keydown(function(keyNum) {
		if (keyNum.keyCode == 13) {
			sendMessage();

		}
	})
	$('.chat__timestamp').text(day + ', ' + month + ' ' + days + ', ' + year);
	$('#mainTime').children().text(hours + ':' + minutes);
	$('.message__time').text(hours + ':' + minutes);
});

function getTodayLabel() {
	var week = new Array('Sunday', 'Monday', 'TuesDay', 'Wensday', 'Thursday', 'Friday', 'Saturday');
	var today = new Date().getDay();
	var todayLabel = week[today];
	return todayLabel;
}

function getMonthLabel() {
	var week = new Array('January', 'February', 'Mars', 'April', 'May', 'Jun', 'July',
		'August', 'September', 'October', 'November', 'December');
	var today = new Date().getMonth();
	var todayLabel = week[today];
	return todayLabel;
}
let mcnt = 0;
function recevieMessage(data) {
	mcnt++;
	let text = '<div class="message-row">' +
		'<img src="/images/imbot.jpg" />' +
		'<div class=":message-row__content">' +
		'<span class="message__author">Chatbot</span>' +
		'<div class="message__info">' +
		'<span class="message__bubble" id="message'+mcnt+'">' + data[0] +'</span>' + 
		'<span class="message__time">' + hours + ':' + minutes + '</span>' +
		'</div></div></div>'
		
	let urltext = '<br><br><a class="chatbotlink" id="clink'+mcnt+'" href="#" >축제로이동</a> '
	
	$(document).ready(function(){
		$('#clink'+mcnt).click(function(){
		console.log("click!")
		window.open(data[1], 'Search Festival');
	});
	})

	$('.main-chat').append(text);
	if(data[1] != null){
		$('#message'+mcnt).append(urltext);
	}
	
}


function sendMessage() {
	var text = $('#chat').val();
	let chatMessage = '<div class="message-row message-row--own">' +
		'<div class=":message-row__content">' +
		'<div class="message__info">' +
		'<span class="message__bubble">' + text + '</span>' +
		'<span class="message__time">' + hours + ':' + minutes + '</span>' +
		'</div></div></div>';
	if (text == null || text == "") {
		alert("내용을 입력해주세요.");
	} else {
		$.ajax({
			url: 'chat',
			type: 'post',
			data: { chat: text },
			success: function(data) {
				console.log(data[0]);
				$('.main-chat').append(chatMessage);
				$('#chat').val("");
				recevieMessage(data);
			},
			error: function(error) {
				console.log(error);
			}
		})
	}


}


