
function clickArea(code){
	var active = document.querySelector('.active-area')
	if(active != null){		
		active.classList.remove("active-area");
	}
	document.querySelector("#area"+code).classList.add("active-area");

	$.ajax({
		url:'/callArea',
		data: {code},
		success:function(code){
			$('#festivalING').empty();
			$('#festivalING').append(paintingFestival(code));
		}
	});
	
};


function paintingFestival(code){
	var text = "";
	if(code == null || code ==""){
		text ='<h1 class="nosearchtxt">해당 지역에는 진행중인 축제가 현재 없습니다.</h1>';
	}else{
		for(i=0; i < code.length; i++){	
			text += '<div class="col-md-4 ftco-animate" > <div class="project-wrap"><a class="img"style="background-image:url('+code[i].firstimage+ ')"><span class="price">진행중</span></a><div class="text p-4"><span class="days"><span>' +code[i].eventstartdate+ '</span> ~ <span>'+code[i].eventenddate+'</span></span><h3><a href="/detail?contentid='+code[i].contentid+'">'+code[i].title+'<input name="contentid" hidden value="'+code[i].contentid+'"></a></h3><p class="location"><span class="fa fa-map-marker"></span><span>'+code[i].addr1+'</span></p></div></div></div>'		
		}
	}
	return text;
	
}

$(document).ready(function(){
	document.querySelector("#area1").classList.add("active-area");
	
})
	
	
