//B2B2C�Ź�-����������Ʒ����ҳjs
$(function(){
	 //ȫ����Ʒ�����չʾ������   
	 $('.nav-tree').hover(function(){ 
	  $(this).find('.nav-tree-con').slideDown(100);  
	 },function(){
	 	$(this).find('.nav-tree-con').slideUp(100);  
	 })
	
	//�Ź�-����������Ʒ����ҳ��Сͼ��js
	$('#carousel').flexslider({
    animation: "slide",
    controlNav: false,
    directionNav: false,
    animationLoop: false,
    slideshow: false,
    itemWidth: 88,
    asNavFor: '#slider'
  });
  $('#slider').flexslider({
    animation: "slide",
    controlNav: false,
    animationLoop: false,
    slideshow: false,
    sync: "#carousel"
  });
	//��Ʒ�����ղ�ʱ�����ղسɹ�������
	$(".y_collecpd").click(function(){
		$("#modal-collect-form").modal();
		return false;
	});
	//�Ź�����ҳ�����ʷ��������Ӧλ��ʱ����
	var y_tghistory = $(".y_tgbrowhistory");
	y_tghistory.affix({
		offset: {
			top: function() {
				return this.top = y_tghistory.offset().top
			},
			bottom: function() {
				return this.bottom = $(".footer").outerHeight(true)+30
			}
		}
	});
	//���빺�ﳵ���빺�ﳵЧ���ĵ���
	$('.y_gobuybtn .btn-cart,.y_tgtbbtn .btn-cart').click(function(event){
		var this_x = $(this).offset().left + $(this).width()/2,
				this_y = $(this).offset().top- $(window).scrollTop() - 50;
		$(".y_lifebigpic .flex-active-slide img").shoping({
			star_x:this_x,//����Ԫ�ص����x��λ��
			star_y:this_y//����Ԫ�ص����y��λ��
		});
		event.preventDefault();;
	});
	// ��������Ʒ����ҳtab�л� --- �Ź�����������Ʒ����ҳ
	var navContainer = $('.y_tgmaintan'),
	navContainerTop = navContainer.offset().top-4;
	navContainer.affix({
		offset: {
			top: navContainerTop,
			bottom: function() {
				return this.bottom = $(".footer").outerHeight(true)+30
			}
		}
	});
	$(".y_tgmaintan .tab_style2 li a").click(function(){
		$(this).parent().addClass("active").siblings().removeClass("active");
		return false;
	});
	var dettop = $("#y_lifedet").offset().top-60;
	var storetop = $("#y_lifestore").offset().top-60;
	$(".y_lifedetbtn").click(function(){
		$('body,html').animate({ scrollTop: dettop }, 200);
	});
	$(".y_lifestorebtn").click(function(){
		$('body,html').animate({ scrollTop: storetop }, 200);
	});
	
});