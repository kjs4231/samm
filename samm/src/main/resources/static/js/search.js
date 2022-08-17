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
});

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
	console.log(page);

	$('#paging').children().eq(page).addClass('active');
	console.log($('#paging').children().eq(page).text())
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

