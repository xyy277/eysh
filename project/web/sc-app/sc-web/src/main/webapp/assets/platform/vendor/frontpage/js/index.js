//B2B2Cǰ̨��ҳjs
$(function() {
  // ������������
	$(window).on('scroll', function () {
		stickyMenu();
	});
	function stickyMenu() {
		var windowTop = $(window).scrollTop(),
	  navContainer = $('.main-serch-container'),
	  navContainerHeight = navContainer.height();
	     	
	  if (windowTop >= 180) {
	  	if(!navContainer.closest('.fixed-wrapper').length) {
	  		navContainer.wrap(function() {
	  			return '<div class="fixed-wrapper" style="height:'+ navContainerHeight +'px"></div>';
	  		});
	  	}
	  	if(!navContainer.hasClass("fixed")){
	      navContainer.addClass('fixed');
	    }
	  } else {
	  	navContainer.removeClass('fixed');
	  }
	};
	
	//��ҳ������ղ�ʱ���õ�¼������
	$(".y_inflor1 .y_lorbtn").click(function(){//����ʱ1F���õ�¼��������ʽ��������Ҫ.y_inflor1
		$("#modal-login-form").modal();
		return false;
	});
	//��ҳ������ղ�ʱ�����ղسɹ�������
	$(".y_inflor2 .y_lorbtn").click(function(){//����ʱ2F���ü����ղص�������ʽ��������Ҫ.y_inflor2
		$("#modal-collect-form").modal();
		return false;
	});
	
	//����ҳ���ȣ�������������ʾ������
	ltflorshow();
	$(window).resize(function(){
		ltflorshow();
	});
	function ltflorshow(){
		var boxwidth=$(".container").width(),
		winwidth=$(window).width();
		if((winwidth/2-boxwidth/2)<77){
			$(".y_florfix").show();
		}else{
			$(".y_florfix").show();
		};
	};
	//��ҳ�������¥����Ч
	$(window).scroll(function() {
		$("[data-flor]").each(function(){
			var whei=$(window).height()/2;
    	var list1=$(this).attr("data-flor");
    	var opt1=$(".y_inflor"+list1).offset().top-whei;
    	if ($(window).scrollTop() >= opt1) {
      	$(".y_florfix span").eq(list1-1).addClass("y_active").siblings().removeClass("y_active");
      }
    });
   if($(window).scrollTop() >= 200){
   		if(!$('.y_florfix').hasClass("y_fixshow")){
       	$('.y_florfix').addClass("y_fixshow");
      }
	 }else{
			if($('.y_florfix').hasClass("y_fixshow")){
       	$('.y_florfix').removeClass("y_fixshow");
      }
	 };
  });
  $(".y_florfix span").click(function(){
  	var list=$(this).attr("data-fix");
  	var ltop=$(".y_inflor"+list).offset().top-55;
  	$("html,body").animate({scrollTop:ltop},300);
  });
});