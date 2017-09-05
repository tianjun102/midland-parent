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
        <title>注册协议</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta name="MobileOptimized" content="320">
		<link rel="stylesheet" href="${ctx}/assets/css/layer.css">
		<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ctx}/assets/css/common.css">
		<link rel="stylesheet" href="${ctx}/assets/css/easydropdown.css" />
		<link rel="stylesheet" type="text/css" href="${ctx }/assets/scripts/uploadify/uploadify.css">

	</head>
	<body >
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="${ctx}/assets/plugins/jquery-validation/lib/jquery.form.js"></script>
		<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
		<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
		<script src="${ctx}/assets/scripts/common.js"></script>
		<script src="${ctx}/assets/scripts/layer.js" type="text/javascript"></script>
		<script src="${ctx}/assets/scripts/base.js" type="text/javascript"></script>
		<script src="${ctx}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${ctx}/assets/UEditor/ueditor.config.js" type="text/javascript"></script>
		<script src="${ctx}/assets/UEditor/ueditor.all.js" type="text/javascript"></script>
		<script src="${ctx}/assets/UEditor/lang/zh-cn/zh-cn.js" type="text/javascript"></script>
		<script src="${ctx}/assets/scripts/inputControl.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx }/assets/scripts/uploadify/jquery.uploadify.min.js"></script>
	<div class="box">
		<section class = "content">
			<p class = "detail-title">
				<span>注册协议</span>
			</p>
		<form id="formId" action="${ctx}/rest/banner/addBanner" method="post" enctype="multipart/form-data" method="post">
			<input type="hidden" name="id" id="id" value="${item.id}" >
			<ul class = "adminfo row">

				<li id="textArea" style="display: block;">
					<span>页面内容：</span>
					<textarea style="width: 92%;min-height: 350px;resize:none; outline-color: #0099e0;" name="registrationProtocol" id="myEditor" rows="" cols="">${item.registrationProtocol}</textarea>
				</li>

	       	<ul class = "adminfo row">
	       		<li>
					<span></span>
					<a onclick="subumintBanner();" target="contentF" class = "public_btn bg2">保存</a>
					<a style="margin-left: 20px" href="${ctx}/rest/banner/bannerindex" target="contentF" class="public_btn bg3" id="cancel">取消</a>
				</li>
	       	</ul>
		</form>
		</section>
	</div>
    </body>
    <script type="text/javascript">

        HasCheked=true;
        UE.getEditor('myEditor');


        function subumintBanner(){
            var data = $("#formId").serialize();
            $.ajax({
                type: "post",
                url: "${ctx}/rest/footer/update",
                async: false, // 此处必须同步
                dataType: "json",
                data:data ,
                success: function (data) {
                    if(data.state==0){
                        layer.msg("保存成功！",{icon:1});
                    } else {
                        layer.msg("保存失败！", {icon: 2});
                    }
                },
                error: function () {
                    layer.msg("保存失败！", {icon: 2});
                }

            });

        }


	</script>
</html>