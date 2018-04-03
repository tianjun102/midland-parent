//设置cookie，cookie有效期时间未GMT时间（距1970年1月1日临时的毫秒）
//例如可以设置setCookie("password","12345",(3600000*24*180)),180有效
function setCookie(name, value, path, expires) {
    if (value != '') {
        deleteCookie(name,path);
    }
    var expdate = new Date();
    expires = 3600000 * 24 * 7;
    expdate.setTime(expdate.getTime() + expires);
    document.cookie = name + "=" + escape(value) +
        "; expires=" + expdate.toGMTString() + "; path=" + path;
}

//根据cookie名，取得cookie值
function getCookie(name,url) {
    var search = name + "=";
    if (document.URL != url){
        return;
    }
    offset = document.cookie.indexOf(search)
    if (offset != -1) {
        offset += search.length;
        end = document.cookie.indexOf(";", offset);
        if (end == -1)
            end = document.cookie.length;
        return unescape(document.cookie.substring(offset, end));
    }
    else
        return "";
}

//删除某一cookie
function deleteCookie(name,path)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}

//检查是否存在此cookie
function checkCookie(cookieName, cookieValue) {
    debugger;
    if (getCookie(cookieName) == cookieValue) {
        return true;
    } else {
        return false;
    }
}
