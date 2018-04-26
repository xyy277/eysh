if (typeof $ != "undefined"){
;$(function(){
var win = $(window);
var isIE = !!window.ActiveXObject;
var isIE6 = isIE && !window.XMLHttpRequest;
var isIE8 = isIE && !!document.documentMode;
var isIE7 = isIE && !isIE6 && !isIE8;


//二级导航
$('.nav .li_o').hover(function(){
$(this).children('.sec_nav').stop().show('slow');
$(this).addClass('hover_li');
},function(){
$(this).children('.sec_nav').stop().hide();
$(this).removeClass('hover_li');
});
//
	
// 
if ($().slide) {
$(".banner").slide({mainCell:".bd",titCell:".hd",effect:"leftLoop", vis:"auto",autoPage: true, autoPlay: true, pnLoop:true, delayTime: 500, interTime: 5000});
//
$(".h_pro").slide({mainCell:".bd",effect:"leftLoop",autoPage: true, autoPlay: true, pnLoop:false, vis:4, scroll: 1, interTime: 5000});
//
};
// end
	
//点击高亮
$('.click_li li').click(function(){
$(this).addClass('light').siblings().removeClass('light');
});
//
	
//隔行变色
$('.even_ul').each(function(){
$(this).children('li:odd').addClass('even_bj');
});
//

//左导航二级菜单
$('.l_nav .show').addClass('dl_click');
$('.l_nav .show dd').show('slow');
$('.l_nav dl dt').click(function(){
$(this).parent().toggleClass('dl_click').siblings().removeClass('dl_click');
$(this).parent().children('dd').slideToggle().end().siblings().children('dd').slideUp();
});
$('.l_nav_dl dd p').click(function(){
$(this).addClass('light').siblings().removeClass('light');
});
//

});
//文本框
function placeholder(input){
	var placeholder = input.attr("placeholder"),
		defaultValue = input.defaultValue;
	if(!defaultValue){
		input.val() == "" ?	input.val(placeholder).addClass("phcolor") : 0;
	}
	input.focus(function(){
		input.val() == placeholder ? $(this).val("") : 0;
	});
	input.blur(function(){
		input.val() == "" ? $(this).val(placeholder).addClass("phcolor") : 0;
	});
	input.keydown(function(){
		$(this).removeClass("phcolor");
	});
}
;$(function(){
	supportPlaceholder="placeholder"in document.createElement("input");
	if(!supportPlaceholder){
		$("input").each(function(){
			var type = $(this).attr("type");
			text = $(this).attr("placeholder");
			if(type == "text" || type == "number" || type == "search" || type == "email" || type == "date" || type == "url"){
				placeholder($(this));
			}
		});
	}
});
//end文本框

};
// end jq