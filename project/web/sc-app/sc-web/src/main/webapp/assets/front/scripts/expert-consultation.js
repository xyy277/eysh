var expertConsultation = {
    init: function () {
        this.initEvent(), this.loadPage()
    }, initEvent: function () {
        $(document).on("click", ".tab-title a", function () {
            var i = $(this), t = i.index();
            i.addClass("active").siblings().removeClass("active"), $(".tab-content .tab-item").eq(t).removeClass("hide").siblings().addClass("hide")
        })
        $(document).on("click", ".f-cb a", function () {
            var i = $(this), t = i.index();
            i.addClass("active").siblings().removeClass("active")
        })
        var index=0;
        var orderId=$("#orderId").val();
        var id=$("#id").val();
        if(orderId!=null&&orderId!=""){
            $.getJSON("/front/login/isLogin",{},function(data){
                data=eval("("+data+")");
                if(data.flag == true){
                    index=index+1;
                }else{
                    layer.msg("请先登录");
                    window.location.href = "/sso/login?returnUrl=" + encodeURIComponent("/front/expert/info/"+id);
                }
            });
        }
        $(document).on('click', '.js-skip', function (e) {
            var el = $(e.target);
            if(index==0){
                $.getJSON("/front/login/isConsult",{},function(data){
                    data=eval("("+data+")");
                    if(data.flag == true){
                        if(data.checkStatus == true) {
                            if ($("#user_tel_no").val() == "") {
                                layer.msg("请填写联系电话");
                                $("#user_tel_no").focus();
                                return;
                            } else {
                                var reg = new RegExp("0?(13|14|15|17|18)[0-9]{9}");
                                if (!reg.test($("#user_tel_no").val())) {
                                    layer.msg("请填写正确的手机号码");
                                    return;
                                }
                            }
                            index = index + 1;
                            $.getJSON("/front/expert/getOrderNo", {
                                expert_id: expert_id,
                                user_tel_no: $("#user_tel_no").val()
                            }, function (data) {
                                orderId = data;
                                $("#order_id").html("订单号：" + orderId);
                                $(".service-process").eq(index).removeClass("hide").siblings().addClass("hide");
                                $(".process-bar").addClass("step" + (index));
                            });
                        }else{
                            if(data.expert == true){
                                layer.msg("您不可以使用此服务");
                            }else{
                                layer.msg("您还不能使用此服务,请完善基本信息提交审核通过后，方可购买");
                            }
                        }
                    }else{
                        layer.msg("请先登录");
                        window.location.href = "/sso/login?returnUrl=" + encodeURIComponent("/front/expert/info/"+id);
                    }
                });
            }
            if(index==1){
                if($(".active i").attr("class")=="weixin"){
                    $.post("/open/pay/wxExpertPay/order",{"orderId":orderId},function (result) {
                        if (result.code == 0) {
                            $("#show_code").html("");
                            $("#show_code").qrcode({width: 200, height: 200, text: result.data});
                            var open_index = layer.open({
                                type: 1,
                                shade: false,
                                title: "请扫描下方二维码进行支付", //不显示标题
                                content: $('#show_code'),
                                end: function () {
                                    window.clearInterval(t);
                                }
                            });
                            var t ={};
                            t=  setInterval(function(){
                                $.post("/front/expert/isPaySuccess",{"orderId":orderId},function (result) {
                                    if(result.code == 0){
                                        layer.close(open_index);
                                        layer.alert("支付成功");
                                        $(".service-process").eq(2).removeClass("hide").siblings().addClass("hide");
                                        $(".process-bar").addClass("step"+(2));
                                        index = index+1;
                                    }
                                })
                            },2000);
                            //index=index+1;
                        } else {
                            layer.msg(result.msg);
                        }
                    })
                }else{
                    window.open("/open/pay/zfbExpertpay/order?orderId="+orderId);
                    var open_index = layer.open({
                        type: 1,
                        shade: false,
                        title: "正在等待支付...", //不显示标题
                        content: $('#show_zfb'),
                        area:['550px', '350px'],
                        end: function () {
                            window.clearInterval(t);
                        }
                    });
                    var t ={};
                    t=  setInterval(function(){
                        $.post("/front/expert/isPaySuccess",{"orderId":orderId},function (result) {
                            if(result.code == 0){
                                layer.close(open_index);
                                layer.alert("支付成功");
                                $(".service-process").eq(2).removeClass("hide").siblings().addClass("hide");
                                $(".process-bar").addClass("step"+(2));
                                index = index+1;
                            }
                        })
                    },2000);
                }

            }
            if (index == 2 || el.attr("id") == "sbtAsk") {
                var title = $("#title").val();
                if(title==""){
                    layer.msg("请输入标题");
                    $("#title").focus();
                    return;
                }
                var content = $("#content").val();
                if(content==""){
                    layer.msg("请输入您的问题");
                    $("#content").focus();
                    return;
                }
                $.post("/front/expert/addDo",{orderId:orderId,title:title,content:content},function (result) {
                    if (result.code == 0) {
                        layer.msg("提交成功，请等待专家回复");
                        index=0;
                        $(".service-process").eq(index).removeClass("hide").siblings().addClass("hide");
                        $(".process-bar").removeClass("step2").removeClass("step1");
                    } else {
                        layer.msg(result.msg);
                    }
                })
            }
        })
    }, loadPage: function () {
        layui.use("laypage", function () {
            var a = layui.laypage;
            $.getJSON('/front/expert/queryAppraises', {expert_id:expert_id,pageNo: 1}, function(res){ //从第6页开始请求。返回的json格式可以任意定义
                initData(res.list);
                a.render({
                    elem: "appraise-page",
                    groups: 5, //连续显示分页数
                    count: res.totalCount, //通过后台拿到的总页数
                    curr:1,
                    limit:5,
                    jump: function(e, first) { //触发分页后的回调
                        if (first==undefined) { //一定要加此判断，否则初始时会无限刷新
                            $.getJSON('/front/expert/queryAppraises', {expert_id:expert_id,pageNo: e.curr}, function(res){
                                //渲染
                                initData(res.list);
                                $('html,body').animate({scrollTop:$('.app_top').offset().top},0);
                            });
                        }
                    }
                })
            });

            var b = layui.laypage;
            $.getJSON('/front/expert/queryContents', {expert_id:expert_id,pageNo: 1}, function(res){ //从第6页开始请求。返回的json格式可以任意定义
                initContentData(res.list,res.totalCount);
                b.render({
                    elem: "content-page",
                    groups: 5, //连续显示分页数
                    count: res.totalCount, //通过后台拿到的总页数
                    curr:1,
                    limit:5,
                    jump: function(e, first) { //触发分页后的回调
                        if (first==undefined) { //一定要加此判断，否则初始时会无限刷新
                            $.getJSON('/front/expert/queryContents', {expert_id:expert_id,pageNo: e.curr}, function(res){
                                //渲染
                                initContentData(res.list);
                                $('html,body').animate({scrollTop:$('.article_title').offset().top},0);
                            });
                        }

                    }
                })
            });
            //i.render({elem: "m-page", count: 50})
        })
    }
};
expertConsultation.init();