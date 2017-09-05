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
        <title>栏目管理</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta name="MobileOptimized" content="320">
        <style type="text/css">
			.pagination>li>a:focus, .pagination>li>a:hover, .pagination>li>span:focus, .pagination>li>span:hover {
				background-color:#a9b3c0!important;
				color:#ffffff!important;
			}
			.pagination>li>a, .pagination>li>span {
				color:#666666!important;
			}
			.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover{
				color:#ffffff!important;
				background-color:#18aec9!important;
			}
        </style>
    </head>
    <body >
        <script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
			<div class = "table-responsive m40">
				<table class="table table-bordered table-add">
	 				<thead>
		 				<col width="25%" >
						<col width="10%" >
						<col width="10%" >
						<col width="10%" >
						<col width="10%" >
						<col width="10%" >
						<col width="15%" >
						<tr>
							<th>活动标题</th>
							<th>缩略图</th>
							<th>访问次数</th>
							<th>排序</th>
							<th>创建人</th>
							<th>活动状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${activityList}" var="activityList">
						<tr>
					       	<td>${activityList.actiTitle}</td>
					       	<td><img style="height: 36px;" src="${activityList.acti_thumb_pic1}" class="suo"></td>
					       	<td>${activityList.clickNum}</td>
					       	<td>${activityList.sortOrder}</td>
					       	<td>${activityList.userBy}</td>
					       <%-- 	<td>${activityList.addTime}</td> --%>
					       	<td><c:if test="${activityList.isShow =='0'}">关闭</c:if><c:if test="${activityList.isShow =='1'}">开放</c:if>  </td>
							<td>
								<a href="${ctx}/rest/activity/enterEditActivity?id=${activityList.id}" target="contentF" class = "edit_img" title = "编辑"></a>
								<a onclick="deleteActivity(${activityList.id})" class = "delete_img" title = "删除"></a>
							</td>
						</tr>
					</c:forEach>
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