(function () {
    // 收集设备信息
    var args = {};
    // 时间秒
    args.time = new Date().getTime();
    if (document) {
        // 域名
        args.domain = document.domain || '';
        // 访问路径
        args.path = document.URL || '';
        // 页面标题
        args.title = document.title || '';
        // Referrer
        args.referrer = document.referrer || '';

    }
    if (window && window.screen) {
        // 分辨率
        args.screen = window.screen.width + "x" + window.screen.height || '';
        // 颜色深度
        args.colorDepth = window.screen.colorDepth || '';
    }

    if (navigator) {
        // 客户端语言
        args.lang = navigator.language || '';
    }
    //解析站点传输过来的配置参数
    if (_conf) {
        for (var i in _conf) {
            switch (_conf[i][0]) {
                case '_siteAccount':
                    args.account = _conf[i][1];
                    break;
                case '_uid':
                    args.uid = _conf[i][1];
                    break;
                case '_artId':
                    args.artId = _conf[i][1];
                    break;
                default:
                    args[_conf[i][0]] = _conf[i][1];
                    break;
            }
        }
    }
    var urlparam = "";
    for (var i in args) {
        if (urlparam != '') {
            urlparam += '&';
        }
        urlparam += i + '=' + encodeURIComponent(args[i]);
    }
    var base = document.location.protocol + "//" + args.domain + ":" + document.location.port;
    if (args._base !== undefined) {
        base = args._base;
    }
    new Image(1, 1).src = base + "/open/record/_img.gif?" + urlparam;

})()