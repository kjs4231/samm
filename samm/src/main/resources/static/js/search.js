$(document).ready(function(){
	$('#search-btn').click(function(){
		searchFestival();
	});
	
	
});


function searchFestival(){
	$('#search-festival-form').attr({
		action:"/searchFestivalimpl",
		method:"post"
	})
	$('#search-festival-form').submit();
	
}