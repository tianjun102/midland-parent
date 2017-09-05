<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div class="table-responsive m40">
		<table class="table table-bordered table-add">
			<thead>
				<tr>
					<th style="width: 15%">日志类型</th>
					<th style="width: 20%">日志时间</th>
					<th style="width: 55%">日志内容</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${!empty requestScope.sysLogs }">
						<c:forEach items="${requestScope.sysLogs }" var="sysLog"
							varStatus="xh">
							<tr>
								<td>
									<c:if test="${sysLog.logType==0}">系统日志 </c:if> 
									<c:if test="${sysLog.logType==1}">操作日志 </c:if>
								</td>
								<td>${sysLog.addTime }</td>
								<td><div style="width:200px; margin:auto; text-align:center; overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">
								${sysLog.logContent }</div>
								</td>
								<td>
								  <a target="contentF" class = "see_img" onclick="view(${sysLog.logId});" title="查看"></a>
								 <%--  <a target="contentF" class = "delete_img" href="#" onclick="isDelete('${ctx}/rest/sysLog/deleteSysLog?logId=${sysLog.logId}')"></a> --%>
								 </td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="14">没有找到数据!</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	<!-- 分页 -->
	<c:if test="${!empty paginator}"> 
		  <c:set var="paginator" value="${paginator}"/>
		  <c:set var="target" value="listDiv"/>
		  <%@include file="../layout/pagination.jsp"%>
	</c:if>	
	
<script type="text/javascript">
	//查看
	function view(logId){
		layer.open({
			type: 2,
			title: ['查看日志'],
			shade: 0.6,
			area: ['480px', '400px'],
			content: ['${ctx}/rest/sysLog/findSysLog?logId='+logId,'no']
			});
	}
	//编辑
	function edit(logId){
		layer.open({
			type: 2,
			title: ['日志编辑'],
			shade: 0.6,
			area: ['480px', '400px'],
			content: ['${ctx}/rest/sysLog/findSysLog?logId='+logId,'no']
			});
	}
</script>		
</body>
</html>