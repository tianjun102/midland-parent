<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8" />
    <title>添加资讯</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <meta name="MobileOptimized" content="320">
</head>
<body >
<style>

    .content ul.adminfo li > span {
        float: left;
        display: inline-block;
        width: 100px;
        height: 28px;
        line-height: 28px;
        text-align: right;
        font-size: 14px;
        color: rgb(102, 102, 102);
    }

    .layui-layer-msg{
        top:260px!important;
    }

    .content ul.adminfo li:last-child>a:not(:nth-child(2)) {
        margin-left: 0px!important;
    }

</style>
<div class="box">
    <section class = "content">
        <p class = "detail-title">
            <span>添加资讯</span>
        </p>
        <form id="formId" action="" method="post" enctype="multipart/form-data" method="post">
            <ul class = "adminfo row">
                <li>
                    <span style = "float:left;">城市：</span>
                    <c:if test="${empty isSuper}"><input type="hidden" name="cityId"  value="${cityId}"></c:if>
                    <input type="hidden" name="cityName" id="cityName" value="${cityName}">
                    <select onchange="setCityName()" name="cityId" id="cityId" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" <c:if test="${empty isSuper}"> disabled="disabled"</c:if> >
                        <option value="">全部</option>
                        <c:forEach items="${cityList}" var="city">
                            <c:if test="${empty isSuper}"><option selected="selected" value="${cityId}">${cityName}</option></c:if>
                            <option value="${city.id}">${city.name}</option>
                        </c:forEach>
                    </select>
                </li>
                <li><span>一级分类：</span>
                    <input type="hidden" name="cateParentName" id="cateParentName" value="">
                    <select name="cateParentid" id="cateParentid" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" <%--class="dropdown"--%> onchange="setChildCateName();">
                        <option value="" class="label">请选择</option>
                        <c:forEach items="${cateList}" var="cate">
                            <option value="${cate.id}">${cate.cateName}</option>
                        </c:forEach>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li><span>二级分类：</span>
                    <input type="hidden" name="cateName" id="cateName" value="">
                    <select name="cateId" id="cateId" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" <%--class="dropdown"--%> onchange="setCateName();">
                        <option value="" class="label">请选择</option>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li><span>平台：</span>
                    <select name="platform" id="platform" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="0" >网站</option>
                        <option value="1" >微站</option>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li>
                    <span>标题：</span>
                    <input type="text" name="title" id="title" onfocus="checkSelect('cateParentid|cateId','请填写一级分类！|请填写二级分类！')" onblur="notEmpty('title','title','标题不能为空！');" />
                    <span class = "_star ">*</span>
                </li>
                <li><span>平台：</span><input name="source" id="source" type="text">
                </li>
                <li><span>附件：</span>
                    <div style="float: left;">
                        <input type="hidden" name="enclosure" id="enclosure" value="${item.iconImg}">

                        <img style="margin-bottom: -2px;max-width:200px;max-height:200px" id="iconImg1"
                             src="${item.iconImg}">
                        <span id="fileUrl"></span>
                        <input type="file" name="file_upload" id="file_upload"/>
                    </div>
                </li>
                <li>
                    <span>META关键字：</span>
                    <input type="text" name="metaKeywords"  />
                </li>
                <li>
                    <span>META描述：</span>
                    <input type="text" name="metaDesc"  />
                </li>

                <li><span>缩略图：</span>
                <div style="float: left;">
                        <input type="hidden" name="imgUrl" id="imgUrl" value="${item.iconImg}">

                        <img style="margin-bottom: 10px;max-width:200px;max-height:200px" id="iconImg2"
                             src="${item.iconImg}">
                        <input type="file" name="file_upload1" id="file_upload1"/>
                    </div>
                </li>
                <li><span>图片说明：</span><input type="text" name="imgDesc"></li>
                <li style="overflow: hidden" id="textArea"><span style = "float:left;">页面内容：</span><textarea style="width: 90%;min-height: 350px;resize:none; outline-color: #0099e0;float: right" name="details" id="myEditor" rows="" cols=""></textarea></li>
            </ul>




            <ul class = "adminfo row">
                <li>
                    <span></span>
                    <a onclick="subumintInformation();" target="contentF" class = "public_btn bg2">保存</a>
                    <a style="margin-left: 20px" href="${ctx}/rest//information/index" target="contentF" class="public_btn bg3" id="cancel">取消</a>
                </li>
            </ul>
        </form>
    </section>
</div>
</body>
<script type="text/javascript">
    UE.getEditor('myEditor');

    function selectTypes(){
        if($("#selectType option:selected").val()==1){
            $("#searchbatton").show();
            $("#catInfo").show();
            $("#prodInfo").show();
            $("#textArea").hide();
        }else if($("#selectType option:selected").val()==0){
            $("#searchbatton").hide();
            $("#catInfo").hide();
            $("#prodInfo").hide();
            $("#picLike").hide();
            $("#textArea").show();
        }else{
            $("#searchbatton").hide();
            $("#catInfo").hide();
            $("#prodInfo").hide();
            $("#textArea").hide();
            $("#picLike").show();
        }

    }

    function subumintInformation(){
       if(checkSelect('cateParentid|cateId','请填写一级分类！|请填写二级分类！')&&notEmpty('title','title','标题不能为空！')){
        var data = $("#formId").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/information/add",
            async: false, // 此处必须同步
            dataType: "json",
            data:data ,
            success: function (data) {
                if(data.state==0){
                    layer.msg("保存成功！",{icon:1});
                    setTimeout(function(){window.open("${ctx}/rest/information/index","contentF");},2000);
                } else {
                    layer.msg("新增失败！", {icon: 2});
                }
            },
            error: function () {
                layer.msg("新增失败！", {icon: 2});
            }

        });
       }

    }


    function setCityName(){
        $("#cityName").val($("#cityId option:selected").text())
    }


    function getObjectURL(file) {
        var url = null;
        if (window.createObjectURL != undefined) {
            url = window.createObjectURL(file)
        } else if (window.URL != undefined) {
            url = window.URL.createObjectURL(file)
        } else if (window.webkitURL != undefined) {
            url = window.webkitURL.createObjectURL(file)
        }
        return url
    };

    $(function () {
        $('#file_upload').uploadify({
            'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
            'uploader': '${ctx }/rest/upload/img',
            'multi': false,// 是否支持多个文件上传
            'buttonText': '上传附件',
            'onUploadSuccess': function (file, data, response) {
                console.log(data);
                $("#enclosure").attr("value", data);
                $("#iconImg1").attr("src", "${ctx}/assets/UEditor/dialogs/attachment/fileTypeImages/"+getFileIcon(data));
                $("#fileUrl").html(    '<a style="font-size:12px; color:#0066cc;"  title="' + data.substr(data.lastIndexOf('/')+1) + '">' + data.substr(data.lastIndexOf('/')+1) + '</a>' );
            },
            'onQueueComplete': function (queueData) {
                if (queueData.uploadsSuccessful < 1) {
                    alert('文件上传失败');
                }
            }

            // Your options here
        });

        $('#file_upload1').uploadify({
            'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
            'uploader': '${ctx }/rest/upload/img',
            'multi': false,// 是否支持多个文件上传
            'buttonText': '上传图片',
            'fileTypeExts':'*.jpg;*.png;*.bmp;*.tiff;*.gif',
            'onUploadSuccess': function (file, data, response) {
                console.log(data);
                $("#imgUrl").attr("value", data);
                $("#iconImg2").attr("src", "${fileUrl}"+data);
            },
            'onQueueComplete': function (queueData) {
                if (queueData.uploadsSuccessful < 1) {
                    alert('文件上传失败');
                }
            }

            // Your options here
        });

    })

    function setCateName(){
        $("#cateName").val($("#cateId option:selected").text())
    }
    
    function setChildCateName(){
        var parentId =  $("#cateParentid option:selected").val();

        $.ajax({
            type: "post",
            url: "${ctx}/rest/category/findChildList?parentId="+parentId,
            async: false, // 此处必须同步
            dataType: "json",
            data:"" ,
            success: function (data) {
                console.log(data);
                $("#cateId").html("<option value='' >请选择</option>");
                data.forEach(function(list) {
                    $("#cateId").append(
                        "<option value="+list.id+" >"
                        + list.cateName + "</option>");
                })
            },
            error: function () {
                layer.msg("新增失败！", {icon: 2});
            }

        });

    }

</script>
</html>