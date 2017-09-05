<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@include file="../layout/tablib.jsp"%>

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
    <title>添加Banner图</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <meta name="MobileOptimized" content="320">
</head>
<body >
<div class="box">
    <section class = "content">
        <p class = "detail-title">
            <span>添加特殊模块</span>
        </p>
        <form id="formId" action="${ctx}/rest/specialPage/add" method="post" enctype="multipart/form-data" method="post">
            <input type="hidden" name="cityName" id="cityName" value="" >
            <ul class = "adminfo row">
                <li><span>城市：</span>
                    <select onchange="setCityName();" name="cityId" id="cityId" style="height: 38px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="">全部</option>
                        <c:forEach items="${cityList}" var="city">
                            <option value="${city.id}">${city.name}</option>
                        </c:forEach>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li><span>平台：</span>
                    <select name="source" id="source" style="height: 38px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="0">网站</option>
                        <option value="1">微站</option>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li>
                    <span>模块名称：</span><input type="text" name="modeName" >
                </li>
                <li><span>位置：</span>
                    <select name="position" id="position" style="height: 38px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="0">位置１</option>
                        <option value="1">位置２</option>
                        <option value="2">位置３</option>
                        <option value="3">位置４</option>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li><span>标题：</span>
                    <input id="title" name="title" maxlength="255" type="text" value="">
                </li>
                <li><span>描述：</span>
                    <input id="description" name="description" maxlength="255" type="text" value="">
                </li>
                <li><span>均价：</span>
                    <input type="text" name="price" id="price" value=""/>
                </li>
                <li><span>地址：</span>
                    <input type="text" name="address" id="address" value=""/>
                </li>
                <li><span>地铁描述：</span>
                    <input type="text" name="subwayDistance" id="subwayDistance" value=""/>
                </li>
                <li><span>图片：</span>
                    <div style="float: left;">
                        <input type="hidden" name="imgUrl" id="imgUrl" value="${item.iconImg}">

                        <img style="margin-bottom: 10px;max-width:200px;max-height:200px" id="iconImg1"
                             src="${item.iconImg}">
                        <input type="file" name="file_upload" id="file_upload"/>
                    </div>
                </li>
                <li id="picLike"><span>图片链接：</span>
                    <input id="bannerId" name="linkUrl" maxlength="255" type="text" value="">
                </li>
                <li><span>缩略图说明：</span><input type="text" name="imgDesc"></li>
            </ul>




            <ul class = "adminfo row">
                <li>
                    <span></span>
                    <a onclick="subumintSpecialPage();" target="contentF" class = "public_btn bg2">保存</a>
                    <a style="margin-left: 20px" href="${ctx}/rest/specialPage/index" target="contentF" class="public_btn bg3" id="cancel">取消</a>
                </li>
            </ul>
        </form>
    </section>
</div>
</body>
<script type="text/javascript">

    UE.getEditor('myEditor');

    function delecte(ths){
        layer.open({
            type: 1,
            skin: 'layer-style',
            area: ['440px','250px'],
            shadeClose: false, //点击遮罩关闭
            title:['是否删除'],
            resize: false,
            scrollbar:false,
            content:
            '<section class = "content" style = "border:none;">'+
            '<dl>'+
            '<dt style = "text-align: center; margin-top: 20px;"><img src="${ctx}/assets/img/!.png"/></dt>'+
            '<dd>'+
            '<p style = "text-align: center; margin-top: 20px;">您确定要删除当前用户列表吗</p>'+
            '</dd>'+
            '</dl>'+
            '</section>',
            btn:['确定','取消'],
            btnAlign: 'c',
            yes: function(index){
                var catId =  $("#cat_Id").val();
                $(ths).parent().parent().remove();
                var array = new Array();
                $("input:hidden[name$='].prodId']").each(function(){
                    array.push($(this).val());
                });
                if($("input:hidden[name$='].prodId']").size()>0){
                    $("#bannerId").val("/banner/bannerinfo?productId="+array+"&catId="+catId);
                }else{
                    $("#bannerId").val("");
                }
                layer.close(index);
                layer.msg("删除成功！",{icon:1});
            }
            ,success: function (layero) {
                var btn = layero.find('.layui-layer-btn');
                btn.css('text-align', 'center');
            }
        });

    }

    function deleteText(){
        layer.open({
            type: 1,
            skin: 'layer-style',
            area: ['440px','250px'],
            shadeClose: false, //点击遮罩关闭
            title:['是否删除'],
            resize: false,
            scrollbar:false,
            content:
            '<section class = "content" style = "border:none;">'+
            '<dl>'+
            '<dt style = "text-align: center; margin-top: 20px;"><img src="${ctx}/assets/img/!.png"/></dt>'+
            '<dd>'+
            '<p style = "text-align: center; margin-top: 20px;">将删除添加的商品</p>'+
            '</dd>'+
            '</dl>'+
            '</section>',
            btn:['确定','取消'],
            btnAlign: 'c',
            yes: function(index){
                $("#cat_Id").val("");
                $("input[name='isAll']").val("");
                $("#catName").html("");
                $("#productList").find("tr").remove();
                $("#bannerId").val("");
                layer.close(index);
                layer.msg("删除成功！",{icon:1});
            }
        });

    }


    function subumintSpecialPage(){
        var data = $("#formId").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/specialPage/add",
            async: false, // 此处必须同步
            dataType: "json",
            data:data ,
            success: function (data) {
                if(data.state=="0"){
                    layer.msg("保存成功！",{icon:1});
                    setTimeout(function(){window.open("${ctx}/rest/specialPage/index","contentF");},2000);
                } else {
                    layer.msg("新增失败！", {icon: 2});
                }
            },
            error: function () {
                layer.msg("新增失败！", {icon: 2});
            }

        });

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
            'buttonText': '上传图片',
            'onUploadSuccess': function (file, data, response) {
                console.log(data);
                $("#imgUrl").attr("value", data);
                $("#iconImg1").attr("src", "http://localhost"+data);
            },
            'onQueueComplete': function (queueData) {
                if (queueData.uploadsSuccessful < 1) {
                    alert('文件上传失败');
                }
            }

            // Your options here
        });
    })

</script>
</html>