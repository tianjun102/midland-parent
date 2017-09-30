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
        <title>关于我们</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta name="MobileOptimized" content="320">
		<style type="text/css">
			.layui-layer{
				top:260px!important;
			}
		</style>

	</head>
	<body >

	<div class="box">
		<section class = "content">
			<p class = "detail-title">
				<span>关于我们</span>
			</p>
		<form id="formId" action="${ctx}/rest/banner/addBanner" method="post" enctype="multipart/form-data" method="post">
			<input type="hidden" name="id" id="id" value="${item.id}" >
			<ul class = "adminfo row">

				<li id="textArea" style="display: block;">
					<span>页面内容：</span>
					<textarea style="width: 92%;min-height: 350px;resize:none; outline-color: #0099e0;" name="aboutUs" id="myEditor" rows="" cols="">${item.aboutUs}</textarea>
				</li>

	       	<ul class = "adminfo row">
	       		<li>
					<span></span>
					<a onclick="subumintBanner();" target="contentF" class = "public_btn bg2">保存</a>
					<a style="margin-left: 20px" onclick="window.parent.location.reload();" href="#" target="contentF" class="public_btn bg3" id="cancel">取消</a>
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