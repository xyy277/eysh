var serviceOrg = {
    init: function () {
        this.loadSwiper(), this.loadPage(), this.loadTabs(), this.applyModal()
    }, loadSwiper: function () {
        new Swiper(".swiper-container", {loop: !0, pagination: ".swiper-pagination", autoplay: 1500})
    }, loadPage: function () {
        layui.use("laypage", function () {
            var a = layui.laypage;
            $.getJSON('/front/serviceorg/queryInfos?'+queryCondition, {pageNo: 1}, function(res){ //从第6页开始请求。返回的json格式可以任意定义
                initDate(res.list);
                a.render({
                    elem: "m-page",
                    groups: 2, //连续显示分页数
                    count: res.totalCount, //通过后台拿到的总页数
                    curr:1,
                    jump: function(e, first) { //触发分页后的回调
                        if (!first) { //一定要加此判断，否则初始时会无限刷新
                            $.getJSON('/front/serviceorg/queryInfos?'+queryCondition, {pageNo: e.curr}, function(res){
                                //渲染
                                initDate(res.list);
                                $('html,body').animate({scrollTop:$('.m-screen').offset().top},0);

                            });
                        }

                    }
                })
            });
        })
    }, loadTabs: function () {
        layui.use("element", function () {
            layui.element
        })
    }, applyModal: function () {
        var a = null;
        $(".j-sp-apply").on("click", function () {
            layui.use("layer", function () {
                a = layui.layer.open({
                    type: 1,
                    content: $(".g-sp-modal"),
                    closeBtn: 0,
                    title: "",
                    area: ["780px", "800px"],
                    offset: ["200px"],
                    fixed: !1
                })
            })
        }), $(".j-spmodal-close").on("click", function () {
            var e = layui.layer.confirm("确定关闭表单吗？您所填写的内容不会被保存", function () {
                layui.layer.close(e), layui.layer.close(a)
            })
        })
    }
};
serviceOrg.init();