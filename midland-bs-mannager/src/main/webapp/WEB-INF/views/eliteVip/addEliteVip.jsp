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
    <title>添加会员</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <meta name="MobileOptimized" content="320">
    <style>
        .layui-layer{
            top:260px!important;
        }
    </style>
</head>
<body >
<div class="box">
    <section class = "content">
        <p class = "detail-title">
            <span>添加会员</span>
        </p>
        <form id="formId" action="${ctx}/rest/banner/addBanner" method="post" enctype="multipart/form-data" method="post">
            <input type="hidden" name="cityName" id="cityName" value="" >
            <ul class = "adminfo row">

                <input type="hidden" name="id" id="id" value="${item.id}">
                <input type="hidden" name="cateName" id="cateName">
                <li><span>会员分类：</span>
                    <select id="cateId" name="cateId" class="dropdown" onchange="setCateName();">
                        <option value="1">会员分类1</option>
                        <option value="2">会员分类2</option>
                        <option value="3">会员分类3</option>
                        <option value="4">会员分类4</option>
                    </select>
                </li>
                <li><span>会员等级：</span>
                    <input type="text" name="level" id="level"/>
                </li>
                <li><span>中文名称：</span>
                    <input type="text" name="cname" id="cname"/>
                </li>
                <li><span>英文名称：</span>
                    <input type="text" name="ename" id="ename"/>
                </li>
                <li><span>所属地区：</span>
                    <input type="text" name="address" id="address"/>
                </li>
                <li><span>职位：</span>
                    <input type="text" name="post" id="post"/>
                </li>
                <li><span>图片：</span>
                    <div style="float: left;">
                        <input type="hidden" name="imgUrl" id="imgUrl" value="${item.iconImg}">

                        <img style="margin-bottom: 10px;max-width:200px;max-height:200px" id="iconImg1"
                             src="${item.iconImg}">
                        <input type="file" name="file_upload" id="file_upload"/>
                    </div>
                </li>
                <li><span>照片描述：</span>
                    <input type="text" name="imgDesc" id="imgDesc"/>
                </li>

            </ul>

            <ul class = "adminfo row">
                <li>
                    <span></span>
                    <a onclick="updateData();" target="contentF" class = "public_btn bg2">保存</a>
                    <a style="margin-left: 20px" href="${ctx}/rest/eliteClub/index" target="contentF" class="public_btn bg3" id="cancel">取消</a>
                </li>
            </ul>
        </form>
    </section>
</div>
</body>
<script type="text/javascript">
    //保存数据
    function updateData() {
        var data = $("#formId").serialize();
        debugger;
        $.ajax({
            type: "post",
            url: "${ctx}/rest/eliteVip/add",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("保存成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        parent.location.reload();
                    }, 1000);

                } else {
                    layer.msg("保存失败！", {icon: 2});
                }
            },
            error: function () {
                layer.msg("保存失败！", {icon: 2});
            }
        });
    }

    //取消
    function closeWin() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }

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

    function setCateName(){
        $("#cateName").val($("#cateId option:selected").text())
    }
</script>
</html>