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
       <form action="${ctx}/rest/activity/list" method="post">
	       <table>
		       <tr>
			       <td>活动标题：</td><td><input maxlength="50" type="text" name="actiTitle" ></td>
			       <td>活动状态：</td>
			       <td>
				       <select name="isShow">
				       <option value="">请选择</option>
				       <option value="1">关</option>
				       <option value="0">开</option>
				       </select>
			       </td>
			       <td><input type="submit"  ></td>
			       <td><a href="${ctx}/rest/activity/enterActivity">发布活动</a></td>
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
			<tr>
				<th>活动标题</th>
				<th>缩略图</th>
				<th>访问次数</th>
				<th>排序</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>活动状态</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${activityList}" var="activityList">
	       	<tr>
		       	<td>${activityList.actiTitle}</td>
		       	<td>${activityList.acti_thumb_pic1}</td>
		       	<td>${activityList.clickNum}</td>
		       	<td>${activityList.sortOrder}</td>
		       	<td>${activityList.userBy}</td>
		       	<td>${activityList.addTime}</td>
		       	<td><c:if test="${activityList.isShow =='0'}">关闭</c:if><c:if test="${activityList.isShow =='1'}">开放</c:if>  </td>
		       	<td><a href="${ctx}/rest/activity/enterEditActivity?id=${activityList.id}">编辑</a>&nbsp;&nbsp;</td>
	       	</tr>
       		</c:forEach>
       	</table>
        </div>
    </body>
</html>