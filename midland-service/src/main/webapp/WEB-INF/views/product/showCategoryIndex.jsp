<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../layout/zTree.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <meta name="MobileOptimized" content="320">
	<title>智者汇 - 分类</title>
	

	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/common.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/base.js" ></script>
	<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/assets/css/easydropdown.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/common.css">
</head>

	<body>
	
	<!--分类界面-->
	<div class="box"> 
		<section class = "content">
			
			<p class = "detail-title">
				<span>分类</span>
				<a class = "setup" onclick="toAddPage()" target="contentF" >添加分类</a>
			</p>
			<form action="${ctx}/rest/product/showCategoryList" method="POST" id="searchForm" onsubmit="submitSearchRequest('searchForm','listDiv');return false;" >
			<ul class = "userinfo row">
				<li><span>分类名称：</span><input type="text" name="catName" id="" placeholder="请输入分类名称" />
				<input type="hidden" name="catId" id="catId" />
				</li>
				<li><input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/></li>
			</ul>
			<div id="listDiv" style="width: 100%"></div>
			</form>
			
		</section>
	</div>
	<!-- 本页私有js -->
	<script type="text/javascript">

		window.onload = function(){
			$('#searchForm').submit();
		}
	
		function toAddPage(){
			var index = layer.open({
				type: 2,
				skin: 'layer-style',
				area: ['470px','450px'],
				shadeClose: false, //点击遮罩关闭
				title:['创建分类'],
				resize: false,
				scrollbar:false,
				content:['${ctx}/rest/product/showInputCategory','no']
			});
			//layer.iframeAuto(index);
		}
	</script>
	</body>
</html>
