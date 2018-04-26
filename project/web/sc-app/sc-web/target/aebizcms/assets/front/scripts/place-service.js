/*
 * @Author: jacobwang
 * @Date:   2016-11-09 09:19:27
 * @Last Modified by:   jacobwang
 * @Last Modified time: 2016-11-15 16:01:51
 */

'use strict';
var placeServiceDetail = {
    init: function() {

        this.swiperEvent();
        this.initEvent();
        this.loadPage();
    },
    swiperEvent: function() {
        var mySwiper = new Swiper('.place-swiper', {

            // 如果需要分页器
            pagination: '.swiper-pagination',
            slidesPerView: 5,



            // 如果需要前进后退按钮
            nextButton: '.swiper-button-next',
            prevButton: '.swiper-button-prev',
        });
    },
    initEvent: function() {
        $('.swiper-slide').on("mouseover", function() {
            $(this).addClass("active").siblings().removeClass("active");
            $(".js-place").attr("src", $(".swiper-slide.active img").attr("src"));
        })
    },
    loadPage: function() {
        layui.use('laypage', function() {
            var laypage = layui.laypage;

            //执行一个laypage实例
            laypage.render({
                elem: 'm-page',
                count: 50
            });
        })
    }

}
placeServiceDetail.init();