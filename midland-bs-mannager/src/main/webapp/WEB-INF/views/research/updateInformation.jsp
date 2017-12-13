<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>
<%@include file="../layout/zTree.jsp"%>
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
    <title>添加市场调究</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <meta name="MobileOptimized" content="320">
    <link rel="stylesheet" href="${ctx}/assets/css/ztree/css/demo.css">
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">
    <link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css"/>
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
    .vipcate{
        width: 250px;
        height: 28px;
        line-height: 28px;
        border: 1px solid #dbe2e6;
        border-radius: 4px;
        text-indent: 10px;
        outline-color: #0099e0;
    }

    .content ul.adminfo li:last-child>a:not(:nth-child(2)) {
        margin-left: 0px!important;
    }

</style>
<script type="text/javascript">

    var setting = {
        check: {
            enable: true,
            chkboxType: { "Y": "sp", "N": "sp" }


        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeClick: beforeClick
        }
    };
    var catProNodes =[{id:0, pId:0,name:'分类',open:true,nocheck:true,iconSkin:"pIcon01"},${categoryData}];


    $(document).ready(function(){
        $.fn.zTree.init($("#categoryTree"), setting, catProNodes);
    });

    function beforeClick(treeId, treeNode, clickFlag) {
        $("input[name='cateId']").val(treeNode.id);
        $("input[name='cateName']").val(treeNode.name);
        $("#showDiv").hide();
    }

    function showTree(event){
        $("#showDiv").show();
    }

    function hideTree(event){
        $("#showDiv").hide();
    }

</script>
<div class="box">
    <section class = "content">
        <p class = "detail-title">
            <span>修改市场调究</span>
        </p>
        <form id="formId" action="${ctx}/rest/banner/addBanner" method="post" enctype="multipart/form-data" method="post">
            <ul class = "adminfo row">
                <input type="hidden" value="${item.id}" name="id" id="id">
                <li><span>市场调究分类：</span><input class="vipcate" id="cateName" value="${item.cateName}" name="cateName" onclick="showTree()" readonly="readonly"/>
                    <input value="${item.cateId}" name="cateId" id="cateId" type="hidden"/><label style="color: red" class = "_star " >*</label>

                </li>
                <li  id="showDiv" style="display: none;padding-top: 0px;padding-left: 70px; position:relative;" >
                    <div class="zTreeDemoBackground left" style  = "position:absolute;left: 100px;top: -10px;"   onblur="test(event)">
                        <ul id="categoryTree" class="ztree" style  = "width:235px; height: 140px!important;"></ul>
                    </div>
                    <img  src="${ctx}/assets/img/Closed_16px.png"  alt="关闭" style="vertical-align: top;position:absolute; left: 320px;" onclick="hideTree()">
                </li>
                <li>
                    <span style = "float:left;">城市：</span>
                    <input type="hidden" name="cityName" id="cityName" value="">
                    <select onchange="setCityName()" name="cityId" id="cityId" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" <c:if test="${empty isSuper}">disabled="disabled"</c:if>>
                        <option value="">全部</option>
                        <c:forEach items="${cityList}" var="city">
                            <option <c:if test="${item.cityId == city.id}"> selected ='selected' </c:if>  value="${city.id}">${city.name}</option>
                        </c:forEach>
                    </select>
                </li>
                <li><span>平台：</span>
                    <select name="platform" id="platform" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option <c:if test="${item.platform==0}">selected="selected"</c:if> value="0" >网站</option>
                        <option <c:if test="${item.platform==1}">selected="selected"</c:if> value="1" >微站</option>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li>
                    <span>标题：</span>
                    <input type="text" id="title" name="title" value="${item.title}" />
                </li>
                <li><span>平台：</span><input name="source" id="source" type="text" value="${item.source}" />
                </li>
                <li><span>附件：</span>
                    <div style="float: left;">
                        <input type="hidden" name="enclosure" id="enclosure" value="${item.enclosure}">

                        <img style="margin-bottom: -5px;max-width:200px;max-height:200px" id="iconImg1"
                             src="${item.enclosure}">
                        <span id="fileUrl"></span>
                        <input type="file" name="file_upload" id="file_upload"/>
                    </div>
                </li>
                <li>
                    <span>META关键字：</span>
                    <input type="text" name="metaKeywords" value="${item.metaKeywords}" />
                </li>
                <li>
                    <span>META描述：</span>
                    <input type="text" name="metaDesc" value="${item.metaDesc}" />
                </li>
                <li>
                    <span>作者：</span>
                    <input type="text" name="author" value="${item.author}"  />
                </li>
                <li><span>缩略图：</span>
                    <div style="float: left;">
                        <input type="hidden" name="imgUrl" id="imgUrl" value="${item.imgUrl}">

                        <img style="margin-bottom: 10px;max-width:200px;max-height:200px" id="iconImg2"
                             src="${item.imgUrl}">
                        <input type="file" name="file_upload1" id="file_upload1"/>
                    </div>
                </li>
                <li><span>图片说明：</span><input type="text" name="imgDesc"></li>
                <li style="overflow: hidden" id="textArea"><span style = "float:left;">页面内容：</span><textarea style="width: 90%;min-height: 350px;resize:none; outline-color: #0099e0;float: right" name="details" id="myEditor" rows="" cols="">${item.details}</textarea></li>
            </ul>




            <ul class = "adminfo row">
                <li>
                    <span></span>
                    <a onclick="subumintInformation();" target="contentF" class = "public_btn bg2">保存</a>
                    <a style="margin-left: 20px" href="${ctx}/rest/research/index" target="contentF" class="public_btn bg3" id="cancel">取消</a>
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
        if(notEmpty('cateName','cateName','请填写市场分类！')&&notEmpty('title','title','标题不能为空！')){
        var data = $("#formId").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/information/update",
            async: false, // 此处必须同步
            dataType: "json",
            data:data ,
            success: function (data) {
                if(data.state==0){
                    layer.msg("更新成功！",{icon:1});
                    setTimeout(function(){window.open("${ctx}/rest/research/index","contentF");},2000);
                } else {
                    layer.msg("更新失败！", {icon: 2});
                }
            },
            error: function () {
                layer.msg("更新失败！", {icon: 2});
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
                $("#fileUrl").html(    '<a style="font-size:12px; color:#0066cc;" title="' + data.substr(data.lastIndexOf('/')+1) + '">' + data.substr(data.lastIndexOf('/')+1) + '</a>' );
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
        var data = '${item.enclosure}';
        $("#iconImg1").attr("src", "${ctx}/assets/UEditor/dialogs/attachment/fileTypeImages/"+getFileIcon(data));
        $("#fileUrl").html(    '<a style="font-size:12px; color:#0066cc;"  title="' + data.substr(data.lastIndexOf('/')+1) + '">' + data.substr(data.lastIndexOf('/')+1) + '</a>' );

    })

    function setCateName(){
        $("#cateName").val($("#cateId option:selected").text())
    }

</script>
</html>