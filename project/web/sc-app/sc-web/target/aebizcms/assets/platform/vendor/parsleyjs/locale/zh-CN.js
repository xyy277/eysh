// ParsleyConfig definition if not already set
window.ParsleyConfig = window.ParsleyConfig || {};
window.ParsleyConfig.i18n = window.ParsleyConfig.i18n || {};
window.ParsleyConfig.validators = window.ParsleyConfig.validators || {};
// Define then the messages
window.ParsleyConfig.i18n.zh_cn = $.extend(window.ParsleyConfig.i18n.zh_cn
    || {}, {
    defaultMessage : "不正确的值",
    type : {
        email : "请输入一个有效的电子邮箱地址",
        url : "请输入一个有效的链接",
        number : "请输入正确的数字",
        integer : "请输入正确的整数",
        digits : "请输入正确的号码",
        alphanum : "请输入字母或数字",
        rate : "请输入正确的汇率",
        htmlName:"请输入正确的html文件名称",
        selectrequired:"该项为必填项"
    },
    notblank : "请输入值",
    required : "必填项",
    pattern : "格式不正确",
    min : "输入值请大于或等于 %s",
    max : "输入值请小于或等于 %s",
    range : "输入值应该在 %s 到 %s 之间",
    minlength : "请输入至少 %s 个字符",
    maxlength : "请输入至多 %s 个字符",
    length : "字符长度应该在 %s 到 %s 之间",
    mincheck : "请至少选择 %s 个选项",
    maxcheck : "请选择不超过 %s 个选项",
    check : "请选择 %s 到 %s 个选项",
    equalto : "输入值不同",
    dateiso : "请输入正确格式的日期 (YYYY-MM-DD)"
});
// If file is loaded after Parsley main file, auto-load locale
if ('undefined' !== typeof window.Parsley)
    window.Parsley.addCatalog('zh_cn', window.ParsleyConfig.i18n.zh_cn, true);
$(function() {
    window.Parsley.addValidator('price', {
        validateString : function(value) {
            var price = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
            return (price.test(value));
        },
        messages : {
            zh_cn : '请填写正确的金额'
        }
    });
    window.Parsley.addValidator('phone', {
        validateString : function(value) {
            var phone = /^1[3|4|5|7|8][0-9]\d{8}$/;
            return (phone.test(value));
        },
        messages : {
            zh_cn : '请填写正确的手机号码'
        }
    });
    window.Parsley.addValidator('selectrequired', {
        validateString:function(value) {
            var selectrequired=/\S/;
            return (selectrequired.test(value));
        },
        messages : {
            zh_cn : '该项为必选项'
        }
    });
    window.Parsley.addValidator('htmlname', {
        validateString:function (value) {
            var htmlname = /^\w+(\.[h][t][m][l])$/;
            return (htmlname.test(value));s
        },
        messages : {
            zh_cn : '请输入正确的html文件名称'
        }
    });
    window.Parsley.addValidator('rate', {
        validateString : function(value) {
            var rate = /^([1-9]\d*|0)(\.\d{1,2})?$/;
            return (rate.test(value));
        },
        messages : {
            zh_cn : '请填写正确的比率'
        }
    });

    window.Parsley.addValidator('email', {
        validateString : function(value) {
            var email = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))){2,6}$/i;
            return (email.test(value));
        },
        messages : {
            zh_cn : '请填写正确的电子邮箱'
        }
    });

    window.Parsley.addValidator('fax', {
        validateString : function(value) {
            var regStr = /^(\d{3,4}-)?\d{7,8}$/;
            return (regStr.test(value));
        },
        messages : {
            zh_cn : '请填写正确的传真'
        }
    });

    window.Parsley.addValidator('postcode', {
        validateString : function(value) {
            var regStr = /^([1-9][0-9]{5})$/;
            return (regStr.test(value));
        },
        messages : {
            zh_cn : '请填写正确的邮政代码'
        }
    });

    window.Parsley.addValidator('orgcode', {
        validateString : function(value) {
            var regStr = /^[a-zA-Z0-9]{8}[-]{0,1}[a-zA-Z0-9]$/;
            return (regStr.test(value));
        },
        messages : {
            zh_cn : '请填写正确的组织机构代码'
        }
    });

    window.Parsley.addValidator('currency', {
        validateString : function(value) {
            var currency = /^([1-9][\d*\.\d*]{0,11}|0)\.[\d]{2}$/;
            return (currency.test(value));
        },
        messages : {
            zh_cn : '请填写精确到小数点后两位的正整数金额且金额整数部分不能大于12位有效数字'
        }
    });


    window.Parsley.addValidator('reportyear', {
        validateString : function(value) {
            var currency = /^(19|20)\d{2}$/;
            return (currency.test(value));
        },
        messages : {
            zh_cn : '请填写四位正整数年度值，范围1900-2099'
        }
    });

    window.Parsley.addValidator('positiveinteger', {
        validateString : function(value) {
            var currency = /^[1-9]\d*$/;
            return (currency.test(value));
        },
        messages : {
            zh_cn : '请填写正整数'
        }
    });


});
