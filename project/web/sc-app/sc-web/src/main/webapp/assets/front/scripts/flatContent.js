//控制聊天窗口
function initMsg() {
    //监听div内容变化
    setInterval(function() {
        var mes = $.trim($('#div1').html());
        if (mes != "" && mes.length != 0) {
            $('#msgbtn').removeClass("layui-btn-disabled");
            $('#msgbtn').addClass("layui-btn-danger");
            $('#msgbtn').removeAttr("disabled");
        }else {
            $('#msgbtn').addClass("layui-btn-disabled");
            $('#msgbtn').removeClass("layui-btn-danger");
            $('#msgbtn').attr("disabled","disabled");
        }
    }, 100);
    //禁用erter键换行
    document.addEventListener("keydown", function() {
        if (event.keyCode == "13") {
            event.cancelBubble = true;
            event.preventDefault();
            event.stopPropagation();
        }
    });
    //ennter发送
    $("body").keydown(function() {
        if (event.keyCode == "13") {//keyCode=13是回车键
            var mes = $.trim($('#div1').html());
            if (mes != "" && mes.length != 0) {
                sendMessege();
            }
        }
    });
}
//查询条件拼接
function condition() {
    $("#keyWords").attr("value", $("#keywords2").val())
    queryCondition = "1=1";
    if ($("#keywords2").val() != "") {
        queryCondition += "&keyWords=" + $("#keywords2").val();
    }
    if ($("#channel_id").val() != "") {
        queryCondition += "&channel_id=" + $("#channel_id").val();
    }
    $(".cut").each(function () {
        var maxwidth = 12;
        if ($(this).text().length > maxwidth) {
            $(this).text($(this).text().substring(0, maxwidth));
            $(this).html($(this).html() + '...');
        }
    });
}

function js_method(dd) {
    $("#div1").html(dd);
    sendMessege();
}
//
function getBq() {
    $('.bot2').qqFace({
        id: 'facebox', //表情盒子的ID
        assign: 'saytext', //给那个控件赋值
        path: '/assets/front/images/face/'	//表情存放的路径
    });

};
function doSetscrp() {
    $('#div2').scrollTop($('#div2')[0].scrollHeight);
}
function sendMessege() {
    c = document.getElementById("div1").innerHTML;
    toSendmessege(c);
}
function toSendmessege(c) {
    var mes = $.trim($('#div1').html());
    if (mes != "" && mes.length != 0) {
        $.getJSON('http://www.tuling123.com/openapi/api?key=' + '99dd9b04d3ab4cf6a42db56f5bc450a4' + '&info=' + mes, function (data) {
            $('#div1').empty();
            $(".online-box").append('<div class="ask-box" style="margin-bottom:20px;" ><img src="/assets/front/images/details/头像.png" alt="" class="f-fr">' +
                '<div style="line-height: 20px;word-wrap: break-word" class="message f-fr">' + mes + '</div> </div>');

            $(".online-box").append('<div class="reply-box" style="float: left;"><img src="/assets/front/images/details/kefu.png" alt="" class="f-fl">' +
                '<div style="line-height: 20px;word-wrap: break-word" class="message f-fl">' + (data.text).replace(/\bbr\b/ig, "<br />") + '</div> </div>');
            doSetscrp();
        })
    }
}
function getbqz() {
    var bqvalue = $('#saytext').val();
    c = document.getElementById("div1").innerHTML;
    $("#div1").html(c + replace_em(bqvalue));
    $("#saytext").val("");
    $('#facebox').hide();
    $('#facebox').remove();
}
//查看结果
function replace_em(str) {
    str = str.replace(/\</g, '&lt;');
    str = str.replace(/\>/g, '&gt;');
    str = str.replace(/\n/g, '<br/>');
    str = str.replace(/\[em_([0-9]*)\]/g, '<img src="/assets/front/images/face/$1.gif" border="0" />');
    return str;
}
function dohot(id) {
    var dd = document.getElementById(id).innerHTML;
    $("#div1").html(dd);
    sendMessege();
}

function answerApply(contentid) {
    if(contentid!=""&&contentid!=null){
        var historyid =  $('#contentIdformsg').val();
        if(historyid!=contentid){
            $('#hotquestion').nextAll().remove();
            $('#contentIdformsg').attr("value",contentid);
        }
    }
    var e = null;
    layui.use("layer", function () {
        e = layui.layer.open({
            type: 1,
            content: $("#j-online-answer"),
            closeBtn: 0,
            title: "",
            area: ["1002px", "587px;"],
            offset: ["50px"],
            fixed: !1,
            shade: 0
        })
    })
    $(".j-spmodal-close").on("click", function () {
        layui.layer.close(), layui.layer.close(e)
    })
}

function getReadNum(artId) {
    var num = "";
    var msg = "点击量：";
    if (num == false || num == "") {
        $.ajax({
            type: "get",
            async: false,
            url: "/open/record/countArtViews?path=" + document.URL,
            dataType: "jsonp",
            success: function(data){
                num = data.data;
                set(artId, num);
                if (data.msg == "view") {
                    msg = "点击量：";
                }
                $("#readNum").html(msg + num);
            },
        });
    } else {
        $("#readNum").html(msg + num);
    }
}