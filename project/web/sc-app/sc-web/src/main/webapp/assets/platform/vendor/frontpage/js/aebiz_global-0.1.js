//B2B2Cǰ̨ҳ�湫��js

$(function(){
	//ͷ�������л�������¼�
	$(".y_serlist").hover(function(){
		$(this).addClass("y_hover");
	},function(){
		$(this).removeClass("y_hover");
	});
	$(".y_serlist ul li").click(function(){
		$(".y_serlist .y_serspan").text($(this).text());
		$(this).parents(".y_serlist").removeClass("y_hover");	
	});
	
	//ͷ�����ﳵ����ʱ��ʾ���ﳵ��Ϣ
	$('.y_topcart').hover(function(){
		$(this).addClass('open');
	},function(){
		$(this).removeClass('open');
	})

	
	//ͷ������������ϵ���hoverIntent�����ʾ������������
	if ($.fn.hoverIntent) {
		$('ul.menu').hoverIntent({
			over: function() {
				$(this).addClass('active').children('ul, .mega-menu').fadeIn(200);
			},
			out: function() {
				$(this).removeClass('active').children('ul, .mega-menu').fadeOut(75);
			},
			selector: 'li',
			timeout: 145,
			interval: 55
		});
	}else{
		$('ul.menu li').hover(function(){
			$(this).addClass('active').children('ul, .mega-menu').fadeIn(200);
		},function(){
			$(this).removeClass('active').children('ul, .mega-menu').fadeOut(75);
		})
	};
	
	//��¼�������û�����������֤�ɹ��������¼��ť�ı䰴ť���ֵ��¼�
	$('#y_loadlogin').on('click', function () {
	  var $btn = $(this).button('loading'); //loading
	  //$btn.button('reset')  ����
	});
	
	//���ض���js
	$(".y_ifixed .y_totop").click(function(){
		$("html,body").animate({scrollTop:'0px'},300);
	});
	
	//����ҳ���ȣ��Ҳ�������ʺš��ղء������ʾ����ʾ������
	var rtfixshow = false,
			rtboxshow = false;
	rtcartshow();
	$(window).resize(function(){
		if(!$(".y_rtfixbx").hasClass("y_showrt")){
			rtcartshow();
		}
	});
	function rtcartshow(){
		var boxwidth=$(".container").width(),
		winwidth=$(window).width();
		if((winwidth/2-boxwidth/2)<1000){
			if(!$(".y_ifixed").hasClass("y_rtcthide")){
				$(".y_ifixed").addClass("y_rtcthide");
			};
			rtfixshow = false;
			/*
			if(!$(".y_rtfixbx").hasClass("y_showrt")){
				$(".y_ifixed").bind("mouseenter mouseleave", function(e) { 
					var w = $(this).width(); 
					var h = $(this).height(); 
					var x = (e.pageX - this.offsetLeft - (w / 2)) * (w > h ? (h / w) : 1); 
					var y = (e.pageY - this.offsetTop - (h / 2)) * (h > w ? (w / h) : 1); 
					var direction = Math.round((((Math.atan2(y, x) * (180 / Math.PI)) + 180) / 90) + 3) % 4; 
					var eventType = e.type; 
					if(e.type == 'mouseleave'/ && direction==2/){  
						if(!$(this).hasClass("y_rtcthide")){
							$(this).addClass("y_rtcthide");
						}
					}else{ 
						if($(this).hasClass("y_rtcthide")){
							$(".y_ifixed").removeClass("y_rtcthide");
						}
					} 
				});
			}
			*/
		}else{
			$(".y_ifixed").removeClass("y_rtcthide");
			rtfixshow = true;
		};
	};
	
	//�Ҳ๺�ﳵ���ղ���Ϣ����ʾ�¼�
	function y_rtctlopen(){
		$(".y_rtfixbx").addClass("y_showrt");
		$(".y_ifixed").removeClass("y_rtcthide");
		//�Ҳ๺�ﳵ���ղ���ʾʱ������Ҳ���������ĵط����Թر�
		$(document).one("click", function () {
	  	y_rtctloclose();
	  });
		rtboxshow = true;
	};
	$(".y_rtfixbx").click(function (event) {
		event.stopPropagation();
	});
	//�Ҳ๺�ﳵ���ղ���Ϣ�Ĺر��¼�
	function y_rtctloclose(){
		$(".y_rtcart,.y_rtlove").removeClass("y_xzcur");
		$(".y_rtfixbx").removeClass("y_showrt");
		$(".y_rtctbx,.y_rtlovebx").removeClass("y_rtmsshow");
		$(".y_rtfixbx").animate({width:"35px"},300);
		rtcartshow();
		rtboxshow = false;
	};
	//�Ҳർ����¼�� ������ﳵ�������رչ��ﳵ��Ϣ
	$(".y_rtfixbx").on("click",".y_rtcart",function(){
		if(!$(".y_rtctbx").is(".y_rtmsshow")){
			y_rtctlopen();
			$(this).addClass("y_xzcur");
			$(".y_rtlove").removeClass("y_xzcur");
			$(".y_rtctbx").addClass("y_rtmsshow");
			$(".y_rtlovebx").removeClass("y_rtmsshow");
			$(".y_rtfixbx").animate({width:"310px"},300);
			
		}else{
			y_rtctloclose();
		};
	});
	//�Ҳർ����¼�� ����ҵ��ղص������ر��ղ���Ϣ
	$(".y_rtfixbx").on("click",".y_haslogoin .y_rtlove",function(){
		if(!$(".y_rtlovebx").is(".y_rtmsshow")){
			y_rtctlopen();
			$(this).addClass("y_xzcur");
			$(".y_rtcart").removeClass("y_xzcur");
			$(".y_rtlovebx").addClass("y_rtmsshow");
			$(".y_rtctbx").removeClass("y_rtmsshow");
			$(".y_rtfixbx").animate({width:"270px"},300);
		}else{
			y_rtctloclose();
		};
	});
	//�Ҳ๺�ﳵ���ղ���ʾʱ���رհ�ť���¼�
	$(".y_closertms").click(function(){
		y_rtctloclose();
	});
	
	//�Ҳർ�������ʾ��¼������
	$(".y_rtfixbx").on("click",".y_nologin .y_userbtn",function(){
		$(".y_rtdlbox").removeClass("y_botshow").css({"display":"block","top":"34px","bottom":"auto"})
	});
	$(".y_nologin .y_userbtn,.y_nologin .y_rtcart,.y_nologin .y_rtlove").mouseleave(function(){
		$(".y_rtdlbox").hide();
		$(".y_rtdlbox").hover(function(){
			$(this).show();
		},function(){
			$(this).hide();
		});	
	});
	//�Ҳർ��δ��¼ʱ ������ﳵ���ҵ��ղص�����¼��
	/*
	$(".y_rtfixbx").on("click",".y_nologin .y_rtcart",function(){
		$(".y_rtdlbox").removeClass("y_botshow").css({"display":"block","top":"114px","bottom":"auto"})
	});
	*/
	$(".y_rtfixbx").on("click",".y_nologin .y_rtlove",function(){
		$(".y_rtdlbox").addClass("y_botshow").css({"display":"block","top":"auto","bottom":"70px"})
	});
	//����ر��Ҳ��½��������¼�
	$(".y_closedlbx").click(function(){
		$(this).parents(".y_rtdlbox").hide();	
	});
	//�Ҳ�����������������ʱ��ʾ�����ز���ģ��
	$(".y_ifixed").hover(function(){
		if(rtfixshow == false){
			$(".y_ifixed").removeClass("y_rtcthide");
		};
	},function(){
		if(rtfixshow == false && rtboxshow == false){
			$(".y_ifixed").addClass("y_rtcthide");
		};
	});
	
	//����ҳ������ҳ���������ȫ����Ʒ����ʱչʾ������
   $('.y_produall').hover(function(){
   	 $(this).find('.y_prudaltc').show();
 	 },function(){
 		$(this).find('.y_prudaltc').hide(); 
 	 });
 	 
 	 // Bootstrap tooltip
 	 if($.fn.tooltip) {
	 	$('.add-tooltip,[rel=tooltip]').tooltip();
	 };
	 
	 // Bootstrap popover
	 if($.fn.popover) {
		 $('.add-popover,[rel=popover]').popover();
	 };
	 
	//����.progress-animate��data-width���Զ�̬����Ԫ�صĿ�ȣ����ҳ��������jquery.appear.js�������Ԫ������λ�ò����÷���Ĭ������
	if ($.fn.appear) {
		$('.progress-animate').appear();
		$('.progress-animate').on('appear', function () {
			var $this = $(this),
				progressVal = $(this).data('width'),
				progressText = $this.find('.progress-text');
			$this.css({ 'width' : progressVal + '%'}, 400);
			progressText.fadeIn(500);
		});
	
	} else {
		$('.progress-animate').each(function () {
			var $this = $(this),
				progressVal = $(this).data('width'),
				progressText = $this.find('.progress-text');
			$this.css({ 'width' : progressVal + '%'}, 400);
			progressText.fadeIn(500);
		});
	}
	 
	 //�ղ���Ʒ�ɹ��������ڵ���Ʒ����
	 if($(".y_collpdowl").length > 0){
		 $('.y_collpdowl.owl-carousel').owlCarousel({
		    items: 3,
		    itemsDesktop : false,
		    itemsDesktopSmall: false,
		    itemsTablet: false,
		    pagination: false,
		    navigationText : false,
		    scrollPerPage: true,
		    autoPlay: 5000,
		    slideSpeed: 800,
		    navigation: true
		 });
	 };
	 
	 //����css3 animation������js wow.js���¼���ҳ�������Ԫ������λ��ʱִ��css3������
	 if (typeof WOW === 'function') {
			new WOW({
				boxClass:     'wow',      // default
				animateClass: 'animated', // default
				offset:       0          // default
			}).init();
		};
		
		//���÷Ŵ�ͼprettyPhoto�����js,����ҳ�Ŵ�ͼ��Сͼ��ɹ���õ���
		if ($.fn.prettyPhoto) {
			$("a[data-rel^='prettyPhoto']").prettyPhoto({
				hook: 'data-rel',
        animation_speed: 'fast',
        slideshow: 6000,
        autoplay_slideshow: true,
        show_title: false,
        deeplinking: false,
        social_tools: '',
        overlay_gallery: true,
				theme: 'light_square'
			});
		};
		
});
