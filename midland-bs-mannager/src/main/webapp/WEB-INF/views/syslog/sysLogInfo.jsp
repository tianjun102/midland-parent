<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctx }/assets/css/common.css">
<style type="text/css">
li textarea{
	width:250px;
	height:60px;
	resize:none; 
	border: 1px solid #dbe2e6; 
	border-radius: 4px; 
	outline-color: #0099e0;
}
</style>
</head>
<body>
<section class = "content" style = "border:none;">
<form action="${ctx}/rest/user/edit" method="post" id="userInfoForm">
	<ul class = "adminfo row">
		<input type="hidden" name="logId" id="logId" value="${sysLog.logId}">
		<li style = "display:flex;align-items:center">
				<span>日志类型：</span>
				<select style= "display:inline-block!important;" name="logType" id="logType" class="dropdown" disabled="disabled">
						<option value=""
							<c:if test="${sysLog.logType==-1}">selected="selected"</c:if>>请选择</option>
						<option value="0"
							<c:if test="${sysLog.logType==0}">selected="selected"</c:if>>系统日志</option>
						<option value="1"
							<c:if test="${sysLog.logType==1}">selected="selected"</c:if>>操作日志</option>
				</select>
		</li>
		<li><span>日志时间：</span><input type="text" name="addTime" id="addTime" value="${sysLog.addTime}" disabled="disabled" /></li>
		<li style = "overflow:hidden;">
			<span style = "float:left;">日志内容：</span>
			<textarea style = "float:left;" name="logContent" id="logContent" rows="" cols="" disabled="disabled">${sysLog.logContent}</textarea>
		</li>
		<li><span>操作用户：</span><input type="text" name="userName" id="userName" value="${sysLog.userName}" disabled="disabled"/></li>
		</ul>
	</form>	
</section>
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>