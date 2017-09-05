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
        <title>新增订单</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta name="MobileOptimized" content="320">
        
        <script type="text/javascript" src="${ctx}/assets/plugins/jquery/jquery-1.11.1.min.js"></script>

		<link rel="shortcut icon" href="${ctx}/assets/app/img/favicon.ico"/>
    </head>
    <!-- END HEAD -->

    <!-- BEGIN BODY -->
    <body>
   		  订单添加
   		  <form action="${ctx}/rest/order/forInputOrderInfo">
	   		  <table>
	   		  	<tr>
	   		  		<td>1商品</td>
	   		  		<td>商品ID<input name="shoppingCarts[0].productId" >
	   		  			数量<input name="shoppingCarts[0].quantity" >
	   		  			是否赠品<input name="shoppingCarts[0].isGift" >
	   		  		</td>
	   		  	</tr>
	   		  	<tr>
	   		  		<td>2商品</td>
	   		  		<td>商品ID<input name="shoppingCarts[1].productId" >
	   		  			数量<input name="shoppingCarts[1].quantity" >
	   		  			是否赠品<input name="shoppingCarts[1].isGift" >
	   		  		</td>
	   		  	</tr>
	   		  	
	   		  	<tr>
	   		  		<td><input type="reset"  value="重置"></td>
	   		  		<td><input type="submit" value="提交"></td>
	   		  	</tr>
	   		  </table>
   		  </form>
    </body>
</html>