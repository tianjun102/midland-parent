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
    <title>添加资讯</title>
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
        height: 38px;
        line-height: 38px;
        text-align: right;
        font-size: 14px;
        color: rgb(102, 102, 102);
    }

    .layui-layer-msg{
        top:260px!important;
    }

    .vipcate{
        width: 250px;
        height: 38px;
        line-height: 38px;
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
        $("input[name='vipcate']").val(treeNode.name);
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
            <span>添加资讯</span>
        </p>
        <form id="formId" action="" method="post" enctype="multipart/form-data" method="post">
            <ul class = "adminfo row">
                <input type="hidden" name="cateName" id="cateName">
                <li><span>市场调究分类：</span><input class="vipcate" id="vipcate"  name="vipcate" onclick="showTree()" readonly="readonly"/>
                    <input name="cateId" type="hidden"/><label style="color: red" class = "_star " >*</label>

                </li>
                <li  id="showDiv" style="display: none;padding-top: 0px;padding-left: 70px; position:relative;" >
                    <div class="zTreeDemoBackground left" style  = "position:absolute;left: 100px;top:-10px;"   onblur="test(event)">
                        <ul id="categoryTree" class="ztree" style  = "width:235px; height: 140px!important;"></ul>
                    </div>
                    <img  src="${ctx}/assets/img/Closed_16px.png"  alt="关闭" style="vertical-align: top;position:absolute; left: 320px;" onclick="hideTree()">
                </li>
                <li>
                    <span style = "float:left;">城市：</span>
                    <input type="hidden" name="cityName" id="cityName" value="">
                    <select onchange="setCityName()" name="cityId" id="cityId" style="height: 38px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="">全部</option>
                        <c:forEach items="${cityList}" var="city">
                            <option value="${city.id}">${city.name}</option>
                        </c:forEach>
                    </select>
                </li>
                <li><span>平台：</span>
                    <select name="platform" id="platform" style="height: 38px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="0" >网站</option>
                        <option value="1" >微站</option>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li>
                    <span>标题：</span>
                    <input type="text" name="title" id="title"  onblur="notEmpty('title','title','标题不能为空！');"  onfocus="notEmpty('vipcate','vipcate','请填写市场分类！')"  />
                </li>
                <li><span>平台：</span><input name="source" id="source" type="text">
                </li>
                <li><span>附件：</span>
                    <div style="float: left;">
                        <input type="hidden" name="enclosure" id="enclosure" value="${item.iconImg}">

                        <img style="margin-bottom: -5px;max-width:200px;max-height:200px" id="iconImg1"
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

                <%--<li>
                    <span>多图上传：</span>
                    <button type="button" id="j_upload_img_btn">多图上传</button>
                    <div id="upload_img_wrap"></div>
                </li>

                <li>
                    <span>附件上传：</span>
                    <button type="button" id="j_upload_file_btn">附件上传</button>
                    <div id="upload_file_wrap"></div>
                </li>--%>
                <!-- 加载编辑器的容器 -->
                <%--<textarea id="uploadEditor" style="display: none;"></textarea>--%>
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
                    <a style="margin-left: 20px" href="${ctx}/rest/research/index" target="contentF" class="public_btn bg3" id="cancel">取消</a>
                </li>
            </ul>
        </form>
    </section>
</div>
</body>
<script type="text/javascript">
    UE.getEditor('myEditor');
    function subumintInformation(){
        var data = $("#formId").serialize();
        if(notEmpty('vipcate','vipcate','请填写市场分类！')&&notEmpty('title','title','标题不能为空！')){
        $.ajax({
            type: "post",
            url: "${ctx}/rest/research/add",
            async: false, // 此处必须同步
            dataType: "json",
            data:data ,
            success: function (data) {
                if(data.state==0){
                    layer.msg("保存成功！",{icon:1});
                    debugger;
                    setTimeout(function(){window.open("${ctx}/rest/research/index","contentF");},2000);
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
            'multi': true,// 是否支持多个文件上传
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

        });

    })

    function setCateName(){
        $("#cateName").val($("#cateId option:selected").text())
    }

</script>
<!-- 使用ue -->
<script type="text/javascript">

    /*// 实例化编辑器，这里注意配置项隐藏编辑器并禁用默认的基础功能。
    var uploadEditor = UE.getEditor("uploadEditor", {
        isShow: false,
        focus: false,
        enableAutoSave: false,
        autoSyncData: false,
        autoFloatEnabled:false,
        wordCount: false,
        sourceEditor: null,
        scaleEnabled:true,
        toolbars: [["insertimage", "attachment"]]
    });

    // 监听多图上传和上传附件组件的插入动作
    uploadEditor.ready(function () {
        uploadEditor.addListener("beforeInsertImage", _beforeInsertImage);
        uploadEditor.addListener("afterUpfile",_afterUpfile);
    });

    // 自定义按钮绑定触发多图上传和上传附件对话框事件
    document.getElementById('j_upload_img_btn').onclick = function () {
        var dialog = uploadEditor.getDialog("insertimage");
        dialog.title = '多图上传';
        dialog.render();
        dialog.open();
    };

    document.getElementById('j_upload_file_btn').onclick = function () {
        var dialog = uploadEditor.getDialog("attachment");
        dialog.title = '附件上传';
        dialog.render();
        dialog.open();
    };

    // 多图上传动作
    function _beforeInsertImage(t, result) {
        debugger;
        var imageHtml = '';
        for(var i in result){
            imageHtml += '<img src="'+result[i].src+'" alt="'+result[i].alt+'" height="150">';
        }
        document.getElementById('upload_img_wrap').innerHTML = '<li>'+imageHtml+'</li>';
    }

    // 附件上传
    function _afterUpfile(t, filelist) {
        debugger;
        var i, item, icon, title,
            html = '',
            URL = this.getOpt('UEDITOR_HOME_URL'),
            iconDir = URL + (URL.substr(URL.length - 1) == '/' ? '':'/') + 'dialogs/attachment/fileTypeImages/';
        for (i = 0; i < filelist.length; i++) {
            item = filelist[i];
            icon = iconDir + getFileIcon(item.url);
            title = item.title || item.url.substr(item.url.lastIndexOf('/') + 1);
            html += '<span style="line-height: 16px;">' +
                '<img style="vertical-align: middle; margin-right: 2px;" src="' + icon + '" _src="' + icon + '" />' +
                '<a style="font-size:12px; color:#0066cc;" href="' + item.url + '" title="' + title + '">' + title + '</a>' +
                '</span>';
        }
        document.getElementById('upload_file_wrap').innerHTML = html;
    }*/

</script>
</html>