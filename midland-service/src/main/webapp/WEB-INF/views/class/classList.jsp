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
			<div id="" class = "table-responsive m40">
				<table class="table table-bordered table-add">
					<col width="20%" >
					<col width="10%" >
					<col width="10%" >
					<col width="10%" >
					<col width="15%" >
	 				<thead>
						<tr>
							<th>栏目名称</th>
							<th>栏目属性</th>
							<!-- <th>子级数</th> -->
							<th>排序</th>
							<th>state</th>
							<th style="display: none;">创建人</th>
							<th style="display: none;">创建时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${classList}" var="classList">
						<tr>
							<td>${classList.className}</td>
							<td>${classList.propertyName}</td>
							<%-- <td>${classList.counts}</td> --%>
							<td>${classList.sortOrder}</td>
							<td>
							<c:if test="${classList.isClose==0}">
								开放
							</c:if>
							<c:if test="${classList.isClose==1}">
								关闭
							</c:if>
							
							
							</td>
							<td style="display: none;">${classList.userBy}</td>
							<td style="display: none;">${classList.addTime}</td>
							<td>
								<c:if test="${classList.counts>0}">
								<a title="进入子菜单" target="contentF" class = "goin_img" href="${ctx}/rest/class/enterchildmenusList?classId=${classList.classId}&className=${classList.className}&propertyName=${classList.propertyName}&propertyId=${classList.propertyId}&catId=${classList.catId}"></a>
								</c:if>
       							<a title="编辑" href="${ctx}/rest/class/editmenus?classId=${classList.classId}" target="contentF" class = "edit_img"></a>
       							<a onclick="deleteClass(${classList.classId})" class = "delete_img" title = "删除"></a>
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