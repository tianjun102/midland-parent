function urlreplace(url,paramname,paramvalue){
    if(typeof url !== "string"){
        return;
    }
    var index = url.indexOf("?");
    if(index==-1){
        url = url + "?" + paramname + "=" + paramvalue;
    }else{
        var s1 = url.split("?");
        var params = s1[1].split("&");
        var pn = "";
        var flag = false;
        for(var i=0;i<params.length;i++){
            pn = params[i].split("=")[0];
            if(pn==paramname){
                params[i]=paramname+"="+paramvalue;
                flag = true;
                break;
            }
        }
        if(!flag){
            url = url + "&" + paramname + "=" + paramvalue;
        }else{
            url = s1[0] + "?";
            for(i=0;i<params.length;i++){
                if(i>0){
                    url = url + "&";
                }
                url = url + params[i];
            }
        }

    }
    return url;
}
function setPage(pageNo,targets,pageSize){
    if(targets==''||targets=='null'){
        alert('TODO');
    }else{
        var url=$("#"+targets).attr('url');
        if(pageSize!=undefined){
        url=urlreplace(url,"pageNo",pageNo);
        url=urlreplace(url,"pageSize",pageSize);
        }else{
        	url=urlreplace(url,"pageNo",pageNo); 
        }
        $("#"+targets).attr("url",url);
        ajaxGet(url,targets);
    }
}

function setLimit(limit,targets){
    if(targets==''||targets=='null'){
        alert('TODO');
    }else{
        var url=$("#"+targets).attr('url');
        url=urlreplace(url,"pageNo","1");
        url=urlreplace(url,"pageSize",limit);
        //保存翻页信息到目标层
        $("#"+targets).attr('url',url);
        ajaxGet(url,targets);
    }
}
function commonSuccessCallback(target, data,textStatus){
    $("#" + target).html(data);
}
function getRandedURL(url){
    return urlreplace(url,"_rand",new Date().getTime());
}
/**
 * AJAX Get调用，主要用于查询/刷新页面等请求
 *
 * @params url 请求的URL地址
 * @params target 响应信息输出显示的DIV ID名称
 * @params callback 可选参数，自定义的回调函数
 * @returns
 */
function ajaxGet(url, target, callback) {
    url=getRandedURL(url);
    $("#"+target).attr("url",url);
    $.get(url,null,function(data, textStatus) {
        if(callback){
            callCommon=callback(data);
            if(callCommon){
                commonSuccessCallback(target,data);
            }
        }else{
            commonSuccessCallback(target,data);
        }
    });
}
function submitSearchRequest(formId,target){
    $("#"+formId+" input[type='text']").each(function(){
        $(this).val($.trim($(this).val()).replace(new RegExp("'","gm"),"").replace(new RegExp("%","gm"),""));
    });
    var formSerialize=$("#"+formId).serialize();
    var url=$("#"+formId).attr("action");
    url=getRandedURL(url);
    $.get(url,formSerialize,function(data, textStatus) {
        commonSuccessCallback(target,data);
        $("#"+target).attr("url",url+"&"+formSerialize);
    });
}

/**
 * AJAX Post调用，主要用于URL提交请求
 *
 * @params url 提交URL
 * @returns
 */
function ajaxPostURL(url,data,callback) {
    url=getRandedURL(url);
    $.post(encodeURI(url),data,function(response, textStatus) {
        if(callback){
            callback(response);
        }
    });
}

function setPageSize(pageNo,targets){
	var pageSize = $("#pageSize option:selected").val();
	
	setPage(1,targets,pageSize);
}
