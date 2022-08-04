

$(document).ready(function(){
	
	$('#find_bt').click(function(){
		forgetCheck();
		
	})
	
})


function forgetCheck(){
	let id = $('#id').val()
	let name = $('#name').val()
	if( id == null || id == ""){
		alert("id를 입렵해주세요.")
		return;
	}
	if( name == null || name == ""){
		alert("이름을 입렵해주세요.")
		return;
	}	
	$.ajax({
		url: "/forgetCheck",
		data : {
			id: id,
			name : name
		},
		success: function(data){
			if(data != "true"){
				alert(data)
			}else{
				paintingMsg();
			}
		}			
	})
}

function paintingMsg(){
	$('#completeMsg').text("Email로 임시비밀번호 발송이 완료 되었습니다. ")
}

