
//======�Ź�ϵͳ����js======

	//ͷ���������صĽ���ͼ
	 $(function(){
			$('.top-banner-flex.flexslider').flexslider({
		    animation: "slide",
				animationLoop: true,
				pauseOnAction: false,
				pauseOnHover: true,
				slideshowSpeed: 4000,
				directionNav: false
		  });	
		  $('.wrapper .close').click(function(){
		   $(this).parents('.wrapper').slideUp(300);
		  })
	  });

//======�Ź�ϵͳ-��������ҳ====

  //���ܾ�ѡͼƬ�л�
  $(function(){
		$('.choice-flex.flexslider').flexslider({
	    animation: "slide",
			animationLoop: true,
			pauseOnAction: false,
			pauseOnHover: true,
			slideshowSpeed: 4000,
			directionNav: false
	  });	  
  });
  
  //�Ҳ��ر��Ƴ����Ź�
  $(function(){
		$('.recom-flex.flexslider').flexslider({
	    animation: "slide",
			animationLoop: true,
			pauseOnAction: false,
			pauseOnHover: true,
			slideshowSpeed: 4000,
			directionNav: false
	  });	  
  });
  
  //�Ҳ�ר���Ƽ�
  $(function(){
		$('.subject-flex.flexslider').flexslider({
	    animation: "slide",
			animationLoop: true,
			pauseOnAction: false,
			pauseOnHover: true,
			slideshowSpeed: 4000,
			directionNav: false
	  });	  
  });
  
  //�����˵�ѡ��
   $('.tgnav-goods .item').hover(function(){
   	  $(this).find('.item-categ').show();
   	},function(){
   	  $(this).find('.item-categ').hide();
   	});
      
  //ѡ����е�����Ч��
  $(function(){
  	$('.select-city .input-group').click(function(){
  	  $(this).parents('.select-city').find('.select-city-box').slideDown(300);	  
  	})
    $('.select-city-box .btn').click(function(){
     $(this).parents('.select-city-box').slideUp(300);	
    })
  }) 
	  
	//���Ź�owl-carouselЧ��
	 $(function(){
	    $('.list-group.owl-carousel').owlCarousel({
	     items:5,
	     itemsDesktop:[1409,4],
	     itemsDesktopSmall:[1209,4],
	     itemsTablet:[1023,4],
	     navigation: true,
	     navigationText: false,
	     pagination: false,
	     slideSpeed: 500
	    })
	 });
	 
	 //������logo�ֲ�
	 $(function(){
	    $('.logo-list.owl-carousel').owlCarousel({
	     items:6,
	     itemsDesktop:[1409,5],
	     itemsDesktopSmall:[1209,5],
	     itemsTablet:[1023,4],
	     navigation: true,
	     navigationText: false,
	     pagination: false,
	     slideSpeed: 500
	    })
	 });
	 	
	 //���¥��������Ч
		$('body').scrollspy({ target: '.fixedLeft.life-fixed' })
		$('[data-spy="scroll"]').each(function () {
		  var $spy = $(this).scrollspy('refresh')	  
		})  
		
		$(window).scroll(function(){
		  if($(window).scrollTop() >= 1000){
	   		$('.fixedLeft.life-fixed').addClass('fixedLeft-cur');
		  }else{
				$('.fixedLeft.life-fixed').removeClass('fixedLeft-cur');
		  };	
		})
		
	 //���¥��������Ч������ʾ
	 $('.fixedLeft li').tooltip();		
	
	 //���¥��������Чƽ���ƶ���Ч��
	 $(".fixedLeft.life-fixed .nav li").click(function(){
	 	$(this).addClass('active').siblings().removeClass('active');
	 	var list=$(this).find('a').attr("href");
	  var ltop=$(list).offset().top;
		  $("html,body").animate({scrollTop:ltop},300);   
	 });

//======�Ź�ϵͳ-��Ʒ����ҳ====

   //����ͼbanner�л�
	 $(function(){
			$('.foucs-banner-flex.flexslider').flexslider({
		    animation: "slide",
				animationLoop: true,
				pauseOnAction: false,
				pauseOnHover: true,
				slideshowSpeed: 4000,
				directionNav: false
		  });	
		  $('.wrapper .close').click(function(){
		   $(this).parents('.wrapper').slideUp(300);
		  })
	  });
  
  //�Ƽ���Ʒowl carousel Ч������
   $(function(){
    $('.list-group.owl-carousel').owlCarousel({
     navigation: true,
     navigationText: false,
     pagination: false,
     slideSpeed: 500
    })
   });
  
  //���¥��������Ч
	$('body').scrollspy({ target: '.fixedLeft' })
	$('[data-spy="scroll"]').each(function () {
	  var $spy = $(this).scrollspy('refresh')	  
	})  
	
	$(window).scroll(function(){
		
	  if($(window).scrollTop() >= 500){
   		$('.fixedLeft').addClass('fixedLeft-cur');
	  }else{
			$('.fixedLeft').removeClass('fixedLeft-cur');
	  };	
	  
	})
	
	//���¥��������Ч������ʾ
	$('.fixedLeft li').tooltip();
 
  //���¥��������Чƽ���ƶ���Ч��
	 $(".fixedLeft .nav li").click(function(){
	 	$(this).addClass('active').siblings().removeClass('active'); 
	 	var list=$(this).find('a').attr("href");
	  var ltop=$(list).offset().top;
		  $("html,body").animate({scrollTop:ltop},300); 
	 });
	 
//======�Ź�ϵͳ-�б�ҳ====
  
	// ɸѡ���� ������ָ�������
  $('.filter-con .more-box').click(function(){
    $(this).find('.fa').toggleClass('fa-caret-down').toggleClass('fa-caret-up');
    $(this).parents('.filter-con').find('.filter-list').toggleClass('over-hidden');
  })

	// �б�ҳ ���� ѡ��ѡ��Ч��
	$('.sort-wrapper .check-box').click(function(){
	    $(this).find('.fa').toggleClass('fa-square-o').toggleClass('fa-check-square-o');
		  $(this).toggleClass('active');
	})
	$('.main-sort-container .check-box').click(function(){
	    $(this).find('.fa').toggleClass('fa-square-o').toggleClass('fa-check-square-o');
		$(this).toggleClass('active');
	})
  
  // �б�ҳ�����л�Ч��
	 $('.nav > .categ-tit').click(function(){
	  $(this).addClass('active').siblings().removeClass('active');
	 });
   
  $('.filter-con .categ-tit').click(function(){
   $(this).addClass('active').siblings().removeClass('active');
  });
  
  $('.filter-con .categ-tit').click(function(){
   $(this).addClass('active').siblings().removeClass('active');
   $(this).parent('.filter-list').prev('.categ-all').removeClass('active');  
  })
   
  $('.filter-con .categ-all').click(function(){
    $(this).next('.filter-list').find('.categ-tit').removeClass('active');
  }) 
   
  // �б�ҳ������л�Ч��
  $('.sort-left a').click(function(){
   $(this).addClass('active').siblings().removeClass('active');
  })
  
  //ȫ����Ʒ�����չʾ������   
   $('.nav-tree.nav-tree-hide').hover(function(){ 
    $(this).find('.nav-tree-con').slideDown(100);  
   },function(){
   	$(this).find('.nav-tree-con').slideUp(100);  
   })


	