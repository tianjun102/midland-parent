<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="${ctx }/assets/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css" />
<link rel="stylesheet" href="${ctx }/assets/css/common.css">
</head>
<body >
<!--公告管理界面-->
<div class="box">
	<section class = "content">
			<p class = "detail-title">
				<span>公告管理</span>
				<a class = "setup" href="${ctx}/rest/notice/toAddPage" target="contentF">添加公告</a>
			</p>
	<form action="${ctx}/rest/notice/noticeList" method="post" id="searchForm"
		onsubmit="submitSearchRequest('searchForm','listDiv');return false;">	
			<ul class = "userinfo row">
				<li><span>公告标题：</span><input type="text" name="title" id="title" placeholder="请输入公告标题" /></li>
				<li><span>发布时间：</span><input  class="Wdate half" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'sendTime2\')||\'new Date()\'}'})" name="sendTime1" id="sendTime1" />
				<em class = "gang">-</em>
				<input  class="Wdate half" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'sendTime1\')}',maxDate:new Date()})"  name="sendTime2" id="sendTime2"/></li>
				<li>
					<span style = "float:left;">公告类型：</span> <select name="msgType" id="msgType" class="dropdown">
					<option value=""
						<c:if test="${msgType==-1}">selected="selected"</c:if>>全部</option>
					<option value="1"
						<c:if test="${msgType==1}">selected="selected"</c:if>>系统消息</option>
					<option value="2"
						<c:if test="${msgType==2}">selected="selected"</c:if>>应用通知</option>
			</select>
				</li>
				<li>
					<span style = "float:left;">公告状态：</span>
					<select name="isSend" id="isSend" class="dropdown">
					<option value=""
						<c:if test="${isSend==-1}">selected="selected"</c:if>>全部</option>
					<option value="0"
						<c:if test="${isSend==0}">selected="selected"</c:if>>未发送</option>
					<option value="1"
						<c:if test="${isSend==1}">selected="selected"</c:if>>已发送</option>
					</select>
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

</script>
	<script src="${ctx}/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/assets/scripts/layer/layer.js" type="text/javascript" ></script>
	<script src="${ctx}/assets/scripts/base.js" type="text/javascript"></script>
	<script src="${ctx}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="${ctx}/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${ctx}/assets/scripts/jquery.easydropdown.js" type="text/javascript"></script>
</body>
</html>