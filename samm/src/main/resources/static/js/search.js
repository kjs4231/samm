$(document).ready(function() {
	$('#search-btn').click(function() {
		searchFestival();
	});
	$('#search-btn2').click(function() {
		searchKeyowrd();
	});
	createPaging();
});


function searchFestival() {
	$('#search-festival-form').attr({
		action: "/searchFestivalimpl",
		method: "post"
	})
	$('#search-festival-form').submit();

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
	let actvienum = Number($('ul').eq(1).children().eq(page).text());
	var pnum = Math.floor(actvienum/10) *10;
	$('ul').eq(1).children().not(".marking").addClass('hidden');
	for (i = 1; i <= 10; i++) {
		$('ul').eq(1).children().eq(pnum + i).removeClass('hidden');
	}
	console.log(page);

	$('ul').eq(1).children().eq(page).addClass('active');
	console.log($('ul').eq(1).children().eq(page).text())
}

function nextpage() {
	var count = Number($('#count').val());
	var ten = Number($('ul').eq(1).children().not(".hidden").eq(10).text());
	if (count == ten || ten == 0) {
		return;
	}
	$('ul').eq(1).children().not(".marking").addClass('hidden');
	for (i = 1; i <= 10; i++) {
		$('ul').eq(1).children().eq(ten + i).removeClass('hidden');
	}

}
function prevpage() {
	var one = Number($('ul').eq(1).children().not(".hidden").eq(1).text());
	if (one == 1) {
		return;
	}
	$('ul').eq(1).children().not(".marking").addClass('hidden');
	for (i = 1; i <= 10; i++) {
		$('ul').eq(1).children().eq(one - i).removeClass('hidden');
	}
}

