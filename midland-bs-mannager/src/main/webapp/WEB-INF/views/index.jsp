<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>美联物业管理平台</title>
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/common.css">
	<script src = "assets/scripts/common.js"></script>
	<script src = "assets/scripts/jquery.min.js"></script>
	
	<script> 


	window.onload = function() {
		var NOW = document.getElementById("now");
		if(window.sessionStorage.getItem("href")){
			var PATH = sessionStorage.getItem("href");
			NOW.setAttribute("src",PATH);
		}else {
			NOW.src = "${ctx}/rest/user/contentIndex";
		}
		

		/* $("#now").load(function(){
			var _src = $(this)[0].getAttribute("src");
			console.log(_src);
		}); */
	}
	
	
	</script>
</head>
	<frameset rows= "80px,*" frameborder="0" border="0">
		<frame name="headFrame" src="${ctx}/rest/user/head" noresize/>
		<frameset cols="280px,*" frameborder="0" border="0">
			<frame name = "leftFrame" src = "${ctx}/rest/user/left" scrolling = "yes"/>
			<frame style = "background:#eff2f6;" name = "contentF" src = "" scrolling = "yes" id = "now"/>
		</frameset>
	</frameset>
	<body>
		<h1>真遗憾！您的浏览器不支持frame框架，无法显示页面，请使用现代浏览器体验哦~</h1>
	</body>
	
</html>
