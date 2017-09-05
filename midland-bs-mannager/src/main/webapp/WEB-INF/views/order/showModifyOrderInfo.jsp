<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
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
        <title>修改订单</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta name="MobileOptimized" content="320">

    </head>
    <!-- END HEAD -->

    <!-- BEGIN BODY -->
    <body>
   		  分类修改1
   		  <form action="${ctx}/rest/product/forModifyCategory">
	   		  <table>
	   		  	<tr>
	   		  		<td>分类名称</td>
	   		  		<td>
	   		  		<input type="hidden"  name="catId" value="${category.catId }" />
	   		  		<input  name="catName" value="${category.catName }" />
	   		  		</td>
	   		  	</tr>
	   		  	<tr>
	   		  		<td>父节点</td>
	   		  		<td><input  name="parentId" value="${category.parentId}"></td>
	   		  	</tr>
	   		  	<tr>
	   		  		<td>描述</td>
	   		  		<td><input  name="catDesc" value="${category.catDesc}" ></td>
	   		  	</tr>
	   		  	<tr>
	   		  		<td>排序值</td>
	   		  		<td><input  name="sortOrder" value="${category.sortOrder}"></td>
	   		  	</tr>
	   		  	<tr>
	   		  		<td><input type="reset"  value="重置"></td>
	   		  		<td><input type="submit" value="提交"></td>
	   		  	</tr>
	   		  </table>
   		  </form>
    </body>
</html>