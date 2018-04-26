"use strict";
var index = {
    init: function () {
        console.log(2), this.swiperEvent(), this.checkLable()
    }, swiperEvent: function () {
        new Swiper(".banner1", {
            loop: !0,
            autoplay: 2e3,
            pagination: ".swiper-pagination",
            nextButton: ".swiper-button-next",
            prevButton: ".swiper-button-prev"
        }), new Swiper(".banner2", {
            loop: !0,
            autoplay: 2e3,
            pagination: ".swiper-pagination"
        }), new Swiper(".banner3", {
            loop: !0,
            autoplay: 2e3,
            pagination: ".swiper-pagination",
            nextButton: ".swiper-button-next",
            prevButton: ".swiper-button-prev"
        }), new Swiper(".banner4", {
            direction: "vertical",
            loop: !0,
            autoplay: 2e3,
            pagination: ".swiper-pagination",
            nextButton: ".swiper-button-next"
        })
    }, checkLable: function () {
        $(document).on("click", "[data-label]", function () {
            $(this).addClass("active").siblings().removeClass("active")
        })
    }
};
