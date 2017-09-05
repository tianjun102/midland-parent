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

    </head>
    <body >
        <script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>


        <div>
       <form action="${ctx}/rest/class/list" method="post">
	       <table>
		       <tr>
			       <td>栏目名称：</td><td><input type="text" name="className" ></td>
			       <td>栏目状态：</td>
			       <td>
				       <select name="isClose">
				       <option value="">请选择</option>
				       <option value="1">关</option>
				       <option value="0">开</option>
				       </select>
			       </td>
			       <td><input type="submit"  ></td>
			       <td><a href="${ctx}/rest/class/addclass?classId=${classId}">添加栏目</a></td>
		       </tr>
	       </table>
       </form>
       	<table width="1200px;" border="1" cellspacing="0" cellpadding="0">
	       	<col width="50px;">
			<col width="70px;">
			<col width="120px;">
			<col width="70px;">
			<col width="70px;">
			<col width="70px;">
			<col width="70px;">
			<col width="70px;">
		<c:forEach items="${classList}" var="classList">
       	<tr>
       	<td>${classList.classId}</td>
       	<td>${classList.propertyName}</td>
       	<td>${classList.className}</td>
       	<td>${classList.classPic}</td>
       	<td>${classList.targetUrl}</td>
       	<td>${classList.linkUrl}</td>
       	<td>${classList.classDescription}</td>
       	<td>${classList.catId}</td>
       	<td>${classList.parentId}
       	<c:if test="${ empty classList.parentId}">parentId是空的</c:if></td>
       	<td>${classList.addTime}</td>
       	<td>${classList.isClose}</td>
       	<td>${classList.sortOrder}</td>
       	<td>
       	<c:if test="${ empty classList.childMenus}">子菜单是空的</c:if>
       	${classList.childMenus}</td>
       	<td>${classList.counts}</td>
       	<td>
       		<c:if test="${classList.counts>0}"><a href="${ctx}/rest/class/enterchildmenusList?classId=${classList.classId}&className=${classList.className}&propertyName=${classList.propertyName}&propertyId=${classList.propertyId}&catId=${classList.catId}">进入子菜单</a>&nbsp;&nbsp;<a href="${ctx}/rest/class/editmenus?classId=${classList.classId}">编辑</a></c:if>
       		<c:if test="${!empty classList.parentId}"><a>进入上级菜单</a>&nbsp;&nbsp;<a href="${ctx}/rest/class/editmenus?classId=${classList.classId}">编辑</a></c:if>
       	 </td>
       	
       	</tr>
       	</c:forEach>
       	
       	</table>
       	
        </div>

    </body>
</html>