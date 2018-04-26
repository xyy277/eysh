//价格分转元的公共方法
function setPrice(str){
    return (str/100).toFixed(2);
}

/**
 * 截断字符串
 * @author zyang
 * @param str 字符串
 * @param len 长度
 * @returns {string}
 */
function formatString(str, len) {
    if (len == undefined || len < 0) {
        len = 10;
    }
    return str.substring(0, len) + "...";
}

/**
 * 向localStorage设置值
 * @author zyang
 * @param key 键名
 * @param val 值
 * @param ext 过期时间（秒）
 */
function setLocalStorage(key, val, ext) {
    var extTime = new Date().getTime();
    if (ext == undefined || isNaN(ext)) {
        extTime = 24 * 3600 * 1000;
    } else {
        extTime += ext * 1000;
    }

    window.localStorage.setItem(key, JSON.stringify({data: val, time: extTime}));
}
/**
 * 从localStorage中获取值
 * @author zyang
 * @param key 键名
 * @returns {null}
 */
function getLocalStorage(key) {
    var data = JSON.parse(window.localStorage.getItem(key));
    if (data == null)return null;
    var current = new Date().getTime();
    if (data.time != 0) {
        if (current > data.time) {
            window.localStorage.removeItem(key);
            return null;
        }
    }
    return data.data;
}
/**
 * 重置select
 * @param dom select jQuery对象
 * @param defaultHtml 设置的默认html内容
 */
function resetSelect(dom, defaultHtml) {
    dom.empty();
    dom.html(defaultHtml);
}