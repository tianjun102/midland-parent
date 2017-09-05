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

</head>
<body>
<!--用户列表界面-->
	<div class="box"> 
		<section class = "content">
		<p class = "detail-title">
			<span>日志列表</span>
		</p>
		<form action="${ctx }/rest/sysLog/sysLogList" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
		<ul class = "userinfo row">
			
			<li><span>日志内容：</span><input type="text" name="logContent" id="logContent" placeholder="日志内容支持模糊查询" /></li>
			<li><span>日志时间：</span>
				<input  class="Wdate half" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'new Date()\'}'})" name="startTime" id="startTime" />
				<em class = "gang">-</em>
				<input  class="Wdate half" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:new Date()})"  name="endTime" id="endTime"/>
			</li>
			<li><span>操作用户：</span><input type="text" name="userName"
				id="userName" placeholder="" value="${userName}" /></li>
			<li><span>日志类型：</span> <select name="logType" id="logType"
				class="dropdown" style = "display:inline-block!important;">
					<option value=""
						<c:if test="${logType==-1}">selected="selected"</c:if>>请选择</option>
					<option value="0"
						<c:if test="${logType==0}">selected="selected"</c:if>>系统日志</option>
					<option value="1"
						<c:if test="${logType==1}">selected="selected"</c:if>>操作日志</option>
			</select></li>	
			<li><input  class = "public_btn bg1" type="submit" name="inquery-role" id="inquery-role" value="查询" /></li>
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
	<script src="${ctx}/assets/scripts/layer.js" type="text/javascript"></script>
	<script src="${ctx}/assets/scripts/base.js" type="text/javascript"></script>
	<script src="${ctx}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="${ctx}/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${ctx}/assets/scripts/jquery.easydropdown.js" type="text/javascript"></script>
</body>
</html>