$(document).ready(function() {
	$('#search-btn').click(function(e) {
		searchFestival();
		e.preventDefault();
		
	});
	$('#search-btn2').click(function() {
		searchKeyowrd();
	});

	createPaging();
	endParty();
	incommingParty();
	hiidenPosting();
	$('#moreFestival').click(function(){
		var size = $('#eventSize').val();
		var noHidden = size - $('.row .hidden').length;
		for(i=0; i < 9; i++){
			var cnt = noHidden+i;
			$('#festival'+cnt).removeClass("hidden");
		}	
		if($('.row .hidden').length === 0 ){
			$("#moreFestival").hide();
		}else{
			$("#moreFestival").text("더 보기"+"("+$('.row .hidden').length+")");
		}
	});
});

function hiidenPosting(){
	let size = $('#eventSize').val();
	for(i=0; i < size; i++){
		if(i > 8){
			$('#festival'+i).addClass("hidden");
			
		}
	}
	var printingFestival = $('.row .hidden').length;
	$("#moreFestival").text("더 보기"+"("+printingFestival+")");
}

function endParty(){
	let size = $('#eventSize').val();
	for(i=0;i<size;i++){
		$('#event'+i).addClass('endParty');
	}
}

function incommingParty(){
	let size = $('#eventSize').val();
	for(i=0;i<size;i++){
		$('#incomming'+i).addClass('incommingParty');
	}	
}

function searchFestival() {
	if (($('#startdate').val() == null || $('#startdate').val() == "") || ($('#enddate').val() == null || $('#enddate').val() == "")) {
		alert("날짜를 모두 입력해 주세요.")
		return;
	} else {
		$('#search-festival-form').attr({
			action: "/searchFestivalimpl",
			method: "post"
		})
		$('#search-festival-form').submit();
	}



}

function searchKeyowrd() {
	$('#search-keyword-form').attr({
		action: "/searchKeyword",
		method: "post"
	})
	$('#search-keyword-form').submit();

}

function createPaging() {
	var count = Number($('#count').val());
	var keyword = $('#keyword').val();
	let query = window.location.search;
	let param = new URLSearchParams(query);
	let page = param.get('page');
	for (i = 0; i < count; i++) {
		if (count - i <= 10) {
			$('#page-prev').after('<li><a href="searchfestival?keyword=' + keyword + '&page=' + (count - i) + '">' + (count - i) + '</a></li>');
		} else {
			$('#page-prev').after('<li class="hidden"><a href="searchfestival?keyword=' + keyword + '&page=' + (count - i) + '">' + (count - i) + '</a></li>');
		}
	}
	let actvienum = Number($('#paging').children().eq(page).text());
	var pnum = Math.floor(actvienum / 10) * 10;
	$('#paging').children().not(".marking").addClass('hidden');
	for (i = 1; i <= 10; i++) {
		$('#paging').children().eq(pnum + i).removeClass('hidden');
	}
	$('#paging').children().eq(page).addClass('active');
}

function nextpage() {
	var count = Number($('#count').val());
	var ten = Number($('#paging').children().not(".hidden").eq(10).text());
	if (count == ten || ten == 0) {
		return;
	}
	$('#paging').children().not(".marking").addClass('hidden');
	for (i = 1; i <= 10; i++) {
		$('#paging').children().eq(ten + i).removeClass('hidden');
	}

}
function prevpage() {
	var one = Number($('#paging').children().not(".hidden").eq(1).text());
	if (one == 1) {
		return;
	}
	$('#paging').children().not(".marking").addClass('hidden');
	for (i = 1; i <= 10; i++) {
		$('#paging').children().eq(one - i).removeClass('hidden');
	}
}

