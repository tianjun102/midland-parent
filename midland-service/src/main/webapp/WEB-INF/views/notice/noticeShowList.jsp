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
<div class = "table-responsive m40">
				<table class="table table-bordered table-add">
	 				<thead>
						<tr>
							<th style="width: 5%">序号</th>
							<th style="width: 50%">公告标题</th>
							<th style="width: 15%">公告类型</th>
							
							<th style="width: 20%">发布时间</th>
							<th style="width: 10%">操作</th>
						</tr>
					</thead>
					<tbody>
				<c:choose>
					<c:when test="${!empty requestScope.notices }">
						<c:forEach items="${requestScope.notices }" var="notice" varStatus="xh">
							<tr>
								<td>${xh.count }</td>
								<td><a style = "width:auto;height:auto;margin:auto;" href="${ctx}/rest/notice/viewNotice?id=${notice.id}" target="contentF">${notice.title}</a></td>
								<td>
									<c:if test="${notice.msgType==1}">系统消息 </c:if> 
									<c:if test="${notice.msgType==2}">应用通知 </c:if>
								</td>
								
								
								<td>${notice.sendTime }</td>
								<td>
								<a href="${ctx}/rest/notice/viewNotice?id=${notice.id}" target="contentF" class = "see_img" title = "查看"></a>
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
</body>
</html>