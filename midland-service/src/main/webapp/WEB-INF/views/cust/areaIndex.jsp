<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../layout/tablib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/assets/css/easydropdown.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/common.css">
	<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.css">
</head>
<body>
<!--区域界面-->
	<div class="box"> 
		<section class = "content">
			<p class = "detail-title">
				<span>区域</span>
				<a class = "setup" target="contentF" onclick="toAddPage();">新增区域</a>
			</p>
		<form action="${ctx}/rest/cust/areaList" method="post" id="searchForm"
			onsubmit="submitSearchRequest('searchForm','listDiv');return false;">	
			<ul class = "userinfo row">
				<li><span>区域名称：</span><input type="text" name="areaName" id="areaName" placeholder="请输入区域名称" /></li>
				<li><input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/></li>
			</ul>
		</form>
		<div id="listDiv"></div>
		</section>
	</div>
	
<script type="text/javascript">
	window.onload = function(){
		$('#searchForm').submit();
	}
	
	function toAddPage(){
		layer.open({
			type: 2,
			skin: 'layer-style',
			area: ['480px','300px'],
			shadeClose: false, //点击遮罩关闭
			title:['新增区域'],
			resize: false,
			scrollbar:false,
			content:['${ctx}/rest/cust/toAddAreaPage', 'no']
		});
	}
	
</script>
<script src="${ctx}/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/scripts/layer.js" type="text/javascript"></script>
<script src="${ctx}/assets/scripts/base.js" type="text/javascript"></script>
<script src="${ctx}/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
</body>
</html>