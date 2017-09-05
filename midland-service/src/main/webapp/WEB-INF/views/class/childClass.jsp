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
       	<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ctx}/assets/css/common.css">
		<link rel="stylesheet" href="${ctx}/assets/css/easydropdown.css" />
		<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
		<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
		<script src="${ctx}/assets/scripts/common.js"></script>
		<script src="${ctx}/assets/scripts/layer.js" type="text/javascript"></script>
		<script src="${ctx}/assets/scripts/base.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/assets/My97DatePicker/WdatePicker.js"></script>
    </head>
    <body >
    	<div class="box"> 
		<section class = "content">
			<p class = "detail-title">
				<span>栏目管理</span>
				<a class = "setup" href="${ctx}/rest/class/addclass?classId=${classes.classId}" target="contentF">添加栏目</a>
			</p>
			<div id="" class = "table-responsive m40">
				<table class="table table-bordered table-add">
	 				<thead>
						<tr>
							<th>栏目名称</th>
							<th>栏目属性</th>
							<th>子级数</th>
							<th>排序</th>
							<th>state</th>
							<th>创建人</th>
							<th>创建时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${childMenusList}" var="classList">
						<tr>
							<td>${classList.className}</td>
							<td>${classList.propertyName}</td>
							<td>${classList.counts}</td>
							<td>${classList.sortOrder}</td>
							<td>${classList.isClose}</td>
							<td>${classList.userBy}</td>
							<td>${classList.addTime}</td>
							<td>
       							<c:if test="${classList.counts>0}"><a title="进入子菜单" target="contentF" class = "goin_img"  href="${ctx}/rest/class/enterchildmenusList?classId=${classList.classId}&className=${classList.className}&propertyName=${classList.propertyName}&propertyId=${classList.propertyId}&catId=${classList.catId}"></a></c:if>
       							<c:if test="${!empty classList.parentId}"><a target="contentFrame" href="javascript:void(0);" onclick="window.history.back();">返回</a>&nbsp;&nbsp;</c:if>
       							<a title="编辑" target="contentF" class = "edit_img" href="${ctx}/rest/class/editmenus?classId=${classList.classId}"></a>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</section>
			</div>
    </body>
</html>