//消息提示框
function msg(msg) {
    $("#msgmodal").html("");
    $("#msgmodal").html(msg);
    $("#myMsgModal").modal("show");
}
//导出模版和Excel工具
function formForSubmit(actionUrl) {
    var formFor = document.getElementById("formFor");
    formFor.action = actionUrl;
    formFor.method = "POST";
    formFor.type = "multipart/form-data";
    formFor.submit();
    formFor.reset();
}
//onchange事件
function search() {
    datatable.ajax.reload();
}

//多条数据审核
function checkStatus(url) {
    var chks = datatable.rows('.selected').data();
    if (chks.length > 0) {
        var ids = [];
        $.each(chks, function (i, n) {
            ids.push(n.id_);
            if (n.check_status != '0') {
                Toast.warning("已审核的数据不可再次审核！")
                ids.splice(0, ids.length);
                return false;
            } else {
                ids.push(n.id_);
            }
        });
        if (ids.length > 0) {
            var dialog = $("#dialogCheck");
            $("input:radio[name='check_status']").eq(0).prop("checked", true);
            $("input:radio[name='check_status']").eq(1).removeAttr("checked");
            dialog.modal("show");
            dialog.find("#check_note").val("");
            dialog.find("#check_status_deny").show();
            dialog.find("#check_status_pass").show();
            dialog.find("#submitBtn").unbind("click");
            dialog.find("#submitBtn").bind("click", function (event) {
                var btn = $(this);
                var check_status = $("input[name='check_status']:checked").val();
                var check_note = $("#check_note").val().trim();
                if (check_status == '2' && check_note == '') {
                    Toast.error('请填写不予通过原因');
                } else {
                    btn.button("loading");
                    $.post(url,
                        {
                            ids: ids.toString(),
                            check_status: check_status,
                            check_note: check_note
                        },
                        function (data) {
                            if (data.code == 0) {
                                datatable.ajax.reload(null, false);
                                Toast.success('审核成功');
                            } else {
                                Toast.error(data.msg);
                            }
                            btn.button("reset");
                            dialog.modal("hide");
                        }, "json");
                }
            });
        }
    } else {
        Toast.warning("请先选择要审核的项！");
    }
}


//重置搜索表单
function allClear() {
    $('input').val("");
    $('select').val("");
    $('textarea').val("");
}
//获取页面查询条件参数
function getQueryParams() {
    var params = '?1=1&';
    params += $("#search_form").serialize();
    $(".cd-panel-container input, .cd-panel-container select").each(function () {
        if (!$(this).is(":disabled")) {
            if ($(this).attr("type") == "checkbox" || $(this).attr("type") == "radio") {
                if ($(this).is(':checked')) {
                    params += "&" + $(this).attr("name") + "=" + $(this).val();
                }
            } else {
                params += "&" + $(this).attr("name") + "=" + $(this).val();
            }
        }
    });
    return params;
}

//导出Excel
function expAll(expurl) {
    var params = getQueryParams();
    formForSubmit(expurl + '?' + params);
}

//下载Excel模版
function downLoadExcel(fname) {
    formForSubmit(base + "/platform/ct/common/downLoadExcel/" + fname);
}

function clearFile() {
    $("#file").val("");
}

//多条同步
function doSync(url) {
    var chks = datatable.rows('.selected').data();
    if (chks.length > 0) {
        var ids = [];
        $.each(datatable.rows('.selected').data(), function (i, n) {
            if (n.data_from == 3 || "" == n.data_from) {
                Toast.warning("只可同步数据来源为页面增加或Excel导入的数据！")
                ids.splice(0, ids.length);
                return false;
            } else {
                ids.push(n.id_);
            }
        });
        if (ids.length > 0) {
            var dialog = $("#dialogSync");
            dialog.modal("show");
            dialog.find("#syncDel").unbind("click");
            dialog.find("#syncDel").bind("click", function (event) {
                var btn = $(this);
                btn.button("loading");
                $.post(url, {ids: ids.toString()}, function (data) {
                    if (data.code == 0) {
                        Toast.success(data.msg);
                        datatable.ajax.reload(null, false);
                    } else {
                        Toast.error(data.msg);
                    }
                    btn.button("reset");
                    dialog.modal("hide");
                }, "json");
            });
        }
    } else {
        Toast.warning("请先选择要同步的项！");
    }
}


//初始化查询和重置按钮单击事件
function initSearch() {
    $("#btn_search_ok").click(function () {
        datatable.ajax.reload();
    });
    $("#btn_search_reset").click(function () {
        allClear();
        datatable.ajax.reload();
    });

    $(".form-control.sc_sel").on("change", function () {
        datatable.ajax.reload();
    })
}

//初始化下拉框
function initSelect() {
    $("select.form-control.sc_sel").each(function () {
        var thisObj = $(this);
        $(this).empty();
        var selValue = $(this).attr("data-selvalue");
        var pText = ($(this).attr("data-placeholder") == null || $(this).attr("data-placeholder") == "") ? "请选择" : $(this).attr("data-placeholder");
        $("<option></option>").val("").text(pText).appendTo(thisObj);
        $.ajax({
            type: "GET",
            url: base + "/platform/sys/dict/json/" + $(this).attr("data-code") + "?t=" + Math.random(),
            dataType: 'json',
            cache: false,
            success: function (data) {
                try {
                    $.each(data, function (i, item) {
                        var optionObject = $("<option></option>").val(item.code).text(item.name);
                        if (item.code == selValue) {
                            optionObject.attr('selected', "selected");
                        }
                        optionObject.appendTo(thisObj);
                    });
                    search();
                } catch (err) {
                    //console.log("发生错误");
                }
            }
        });
    })
}

//初始化Excel导入
function initImp(impurl) {
    initUpload(impurl);
}
function initUpload(impurl) {
    $('#file').uploadifive({
        'auto': false,
        'multi': false,
        'width': '100%',
        'height': '25',
        'fileType': 'application/vnd.ms-excel',
        'buttonText': "导入Excel",
        'fileSizeLimit': 204800,
        'queueSizeLimit': 1,
        'removeCompleted': true,
        'removeTimeout': 0,
        'formData': {},
        'uploadScript': impurl,
        'onAddQueueItem': function (file) {
            var thisFile = file.name;
            var ext = "";
            var allowFileExt = ["xls"];
            if (thisFile.lastIndexOf(".") > 0 && thisFile.lastIndexOf(".") < thisFile.length - 1) {
                ext = thisFile.substring(thisFile.lastIndexOf(".") + 1).toLowerCase();
            }
            if ($.inArray(ext, allowFileExt) == -1) {
                Toast.error("请上传指定格式文件");

                $('#file').uploadifive('clearQueue');
                $("#uploadifive-file-queue").html("");
                $('#file').uploadifive('destroy');
                initUpload(impurl);
                $("#file").val("");
            } else {
                $('#file').uploadifive('upload');
            }

        },
        'onUploadComplete': function (file, data) {
            data = JSON.parse(data);
            if (data.code == 0) {
                msg(data.msg);
                datatable.ajax.reload(null, false);
                clearFile();
            } else {
                msg(data.msg)
                clearFile();
            }
        },
        'onDrop': function (file, fileDropCount) {
            clearFile();
        },
        'onClearQueue': function (queue) {
            clearFile();
        },
        'onCancel': function () {
            clearFile();
        }
    });
}
function checkFiletype() {
    var thisFile = $("#file").val();
    var ext = "";
    var allowFileExt = ["xls"];
    if (thisFile.lastIndexOf(".") > 0 && thisFile.lastIndexOf(".") < thisFile.length - 1) {
        ext = thisFile.substring(thisFile.lastIndexOf(".") + 1).toLowerCase();
    }
    if ($.inArray(ext, allowFileExt) == -1) {
        return false;
    } else {
        return true;
    }
}

//初始化日期选择器
function initDatePciker(view, format) {
    $('.form_datetime').datetimepicker({
        minView: view,
        format: format, //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        autoclose: true //选择日期后自动关闭
    });
}

function initDateForOpat() {
    $(".form_datetime").datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        format: "yyyy-mm-dd hh:ii"
    });
}

//初始化百度编辑器
function initBaiduUE(flag) {
    $("textarea").each(function (i, e) {
        var ue = new baidu.editor.ui.Editor({
            toolbars: [
                ['fullscreen', 'source', 'undo', 'redo', 'bold']
            ],
            enterTag: 'br',
            wordCount: false,
            maximumWords: 4000,
            elementPathEnabled: false,
            autoHeightEnabled: true,
            autoFloatEnabled: true,
            readonly: flag
        });
        ue.render(e.getAttribute("id"));
    })
}

// 初始化行政区划
function initCityPicker() {
    $(".city-picker").citypicker({
        province: "安徽省",
        city: "合肥市",
        county: "包河区"
    });
    $(".city-picker").val("安徽省/合肥市/包河区");
}

// 保存行政区划代码
function saveCityPicker() {
    //console.log("size: " + $(".city-picker-span .select-item").length);
    if ($(".city-picker-span .select-item").length < 3) {
        return false;
    } else {
        $("#area_county").val($(".city-picker-span .select-item").eq(2).attr("data-code"));
        return true;
    }
}

// 保存行政区划代码
function saveCityPickerQx() {
    //console.log("size: " + $(".city-picker-span .select-item").length);
    if ($(".city-picker-span .select-item").length < 3) {
        return false;
    } else {
        $("#cityid").val($(".city-picker-span .select-item").eq(1).attr("data-code"));
        $("#countyid").val($(".city-picker-span .select-item").eq(2).attr("data-code"));
        return true;
    }
}

// 初始化行政区划
function initCityPickerQx() {
    $(".city-picker").citypicker({
        province: "安徽省",
        city: "合肥市",
        county: "包河区"
    });
    //$("#cityid").val("340100");
    // $("#countyid").val("340111");
}

//
//
function initDatePickerCompare(date1, date2, view, format, dp) {
    var period = 86400000;
    if (dp) {
        period = dp * 60000;
    }
    $("#" + date2).datetimepicker({
        language: 'zh-CN',
        minView: view,
        format: format,
        autoclose: true
    }).on("changeDate", function (e2) {
        //alert(e2.date.getTime());
        $('#' + date1).datetimepicker('setEndDate', new Date(e2.date.getTime() - period));

    })

    $("#" + date1).datetimepicker({
        language: 'zh-CN',
        minView: view,
        format: format,
        autoclose: true
    }).on("changeDate", function (e1) {

        //alert(e1.date.getTime());
        $('#' + date2).datetimepicker('setStartDate', new Date(e1.date.getTime() + period));
    })
}

