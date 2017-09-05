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
        <link rel="stylesheet" href="assets/css/layer.css">
        <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">
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
		<script src="assets/scripts/layer.js" type="text/javascript"></script>
		<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <div>
       <%-- <form action="${ctx}/rest/ad/list" method="post">
	       <table>
		       <tr>
			       <td>活动标题：</td><td><input type="text" name="adName" ></td>
			       <td>活动状态：</td>
			       <td>
				       <select name="enabled">
				       <option value="">请选择</option>
				       <option value="1">关</option>
				       <option value="0">开</option>
				       </select>
			       </td>
			       <td><input type="submit"  ></td>
			       <td><a href="${ctx}/rest/ad/enterAd">发布活动</a></td>
		       </tr>
	       </table>
       </form> --%>
       	<table width="1300px;" border="1" cellspacing="0" cellpadding="0">
	       	<col width="50px;">
			<col width="70px;">
			<col width="120px;">
			<col width="70px;">
			<col width="70px;">
			<col width="70px;">
			<col width="70px;">
			<col width="70px;">
			<col width="70px;">
			<tr>
				<th>编号</th>
				<th>广告位名称</th>
				<th>缩略图</th>
				<th>访问次数</th>
				<th>排序</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>广告状态</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${adList}" var="adList">
	       	<tr>
		       	<td>${adList.adId}</td>
		       	<td>${adList.adName}</td>
		       	<td>${adList.ad_thumb_pic1}</td>
		       	<td>${adList.clickNum}</td>
		       	<td>${adList.sortOrder}</td>
		       	<td>${adList.userBy}</td>
		       	<td>${adList.addTime}</td>
		       	<td><c:if test="${adList.enabled =='0'}">关闭</c:if><c:if test="${adList.enabled =='1'}">开放</c:if>  </td>
		       	<td><a href="${ctx}/rest/ad/enterEditAd?adId=${adList.adId}">编辑</a>&nbsp;&nbsp;</td>
	       	</tr>
       		</c:forEach>
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