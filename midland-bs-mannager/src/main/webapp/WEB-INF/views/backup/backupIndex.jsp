<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctx }/assets/css/layer.css">
<link rel="stylesheet" href="${ctx }/assets/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css" />
<link rel="stylesheet" href="${ctx }/assets/css/common.css">
<style type="text/css">
.pagination>li>a:focus, .pagination>li>a:hover, .pagination>li>span:focus,
	.pagination>li>span:hover {
	background-color: #a9b3c0 !important;
	color: #ffffff !important;
}

.pagination>li>a, .pagination>li>span {
	color: #666666 !important;
}

.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover,
	.pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover
	{
	color: #ffffff !important;
	background-color: #18aec9 !important;
}
</style>
</head>
<body>
<div class="box"> 
	<section class = "content">
		<p class = "detail-title">
			<span>数据备份</span>
			<a class = "setup" href="javascript:;" target="contentF" onclick="dataBackup()">备份数据</a>
		</p>
	<form action="${ctx }/rest/dataBackup/list" method="POST" id="searchForm"
		onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
		<ul class = "userinfo row">
			<%-- <li><span>用户名：</span><input type="text" name="userName"
				id="userName" value="${requestScope.username }" placeholder="请输入用户名" /></li> --%>
			<li><span>备份时间：</span>		
			<input  class="Wdate half" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}'})" name="beginTime" id="beginTime" />
			<em class = "gang">-</em>
			<input  class="Wdate half" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}'})"  name="endTime" id="endTime"/>
			</li>
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
		
		function dataBackup(){
			window.location.href = "${ctx }/rest/dataBackup/backup";
		}
	</script>
	<script src="${ctx}/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/assets/scripts/layer.js" type="text/javascript"></script>
	<script src="${ctx}/assets/scripts/base.js" type="text/javascript"></script>
	<script src="${ctx}/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${ctx}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</body>
</html>