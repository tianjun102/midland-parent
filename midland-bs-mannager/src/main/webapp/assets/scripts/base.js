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
    var pageNo = GetQueryString("pageNo",$("#"+target).attr("url"));
    $("#"+formId+" input[type='text']").each(function(){
        $(this).val($.trim($(this).val()).replace(new RegExp("'","gm"),"").replace(new RegExp("%","gm"),""));
    });
    var formSerialize=$("#"+formId).serialize();
    var url=$("#"+formId).attr("action");
    url=getRandedURL(url);
    if(pageNo!=null){
        url = url+"&pageNo="+pageNo;
    }
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


function getFileIcon(url){
    var ext = url.substr(url.lastIndexOf('.') + 1).toLowerCase(),
        maps = {
            "rar":"icon_rar.gif",
            "zip":"icon_rar.gif",
            "tar":"icon_rar.gif",
            "gz":"icon_rar.gif",
            "bz2":"icon_rar.gif",
            "doc":"icon_doc.gif",
            "docx":"icon_doc.gif",
            "pdf":"icon_pdf.gif",
            "mp3":"icon_mp3.gif",
            "xls":"icon_xls.gif",
            "chm":"icon_chm.gif",
            "ppt":"icon_ppt.gif",
            "pptx":"icon_ppt.gif",
            "avi":"icon_mv.gif",
            "rmvb":"icon_mv.gif",
            "wmv":"icon_mv.gif",
            "flv":"icon_mv.gif",
            "swf":"icon_mv.gif",
            "rm":"icon_mv.gif",
            "exe":"icon_exe.gif",
            "psd":"icon_psd.gif",
            "txt":"icon_txt.gif",
            "jpg":"icon_jpg.gif",
            "png":"icon_jpg.gif",
            "jpeg":"icon_jpg.gif",
            "gif":"icon_jpg.gif",
            "ico":"icon_jpg.gif",
            "bmp":"icon_jpg.gif",
            "xlsx":"icon_xls.gif",
            "mp4":"icon_mv.gif"
        };
    return maps[ext] ? maps[ext]:maps['txt'];
}


function GetQueryString(name,url)
{
    if(url==undefined){
        return null;
    }
    var index = url.indexOf("?");
    var data=url.substring(index,url.length);
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = data.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}
