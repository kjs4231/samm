
$(document).ready(function(){
	$('#chatbot-div').mouseenter(function(){
		$('#chatbot').css('font-size','3em');
		$('#chatbot-text').removeClass('hidden');
	});
	$('#chatbot-div').mouseleave(function(){
		$('#chatbot').css('font-size','2.7em');
		$('#chatbot-text').addClass('hidden');
	});
	
	$('#chatbot').click(function(){
		window.open('/chatbot','ChatBot',
		'top=10,left=10,height=300, status=no, menubar=no, toolbar=no, resizable=no')
	});
	
});