<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>美联物业管理平台</title>
	<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/assets/css/common.css">
</head>
	<body>
	<!--头部结构-->
	<header class = "head" >
		<c:if test="${user.userType==0}">
			<a href="${ctx}/rest/user/contentIndex" target="contentF"><img src="${ctx}/assets/img/logo.png" alt="智者汇" /></a>
		</c:if>
		<c:if test="${user.userType!=0}">
			<img src="${ctx}/assets/img/logo.png" alt="智者汇" />
		</c:if>
		<div class="txtScroll-top">
		<c:if test="${user.userType==0}">
			<div class="bd">
				<ul class="infoList">
				<c:choose>
				<c:when test="${!empty requestScope.list }">
						<c:forEach items="${requestScope.list }" var="notice" varStatus="xh">
							<li><a href="${ctx}/rest/notice/viewNotice?id=${notice.id }" target="contentF">系统公告：${notice.title }</a><span class="date">${fn:substring(notice.sendTime,0,10)}</span></li>
						</c:forEach>
				</c:when>
				<c:otherwise>
						<li><span class="date">2017-03-09</span><a href="javascript:;" target="_blank">系统公告：欢迎来到美联物业管理平台！</a></li>
				</c:otherwise>
				</c:choose>
				</ul>
			</div>
		</c:if>
		
		<c:if test="${user.userType==1}">
			<div class="bd">
				<ul class="infoList">
				<c:choose>
				<c:when test="${!empty requestScope.list }">
						<c:forEach items="${requestScope.list }" var="notice" varStatus="xh">
							<li><a href="${ctx}/rest/notice/viewNotice?id=${notice.id }" target="contentF">应用通知：${notice.title }</a><span class="date">${fn:substring(notice.sendTime,0,10)}</span></li>
						</c:forEach>
				</c:when>
				<c:otherwise>
						<li><span class="date">2017-03-09</span><a href="javascript:;" target="_blank">应用通知：欢迎来到智者汇经营管理平台！</a></li>
				</c:otherwise>
				</c:choose>
				</ul>
			</div>
		</c:if>
		
		</div>
		<ul class = "user">
			<li><a href="javascript:;" class = "admin"><img src="${ctx}/assets/img/admin.png"/></a></li>
			<li><span class = "user-name">${user.userCnName }</span></li>
			<li>
				<span class = "glyphicon glyphicon glyphicon-triangle-bottom sign"></span>
				<div class ="out"><em>退出</em></div>
			</li>	
		</ul>
		
	</header>
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer.js"></script>
	<script src="${ctx}/assets/scripts/jquery.SuperSlide.2.1.1.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		jQuery(".txtScroll-top").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"topLoop",autoPlay:true});

		$(".user").click(function(e){
			$(".out").slideToggle();
		})
		$(".out").click(function(event){
			var e = event || window.event;
			e.preventDefault();
			sessionStorage.removeItem("href");
			sessionStorage.removeItem("ID");
			window.location.href = "${ctx}/rest/user/logout";
		})
		
		$(document).ready(function() {
			$("a").click(function(event) {
				var e = event || window.event;
				if ($(this)[0].href) {
					var path = $(this)[0].getAttribute("href");
					sessionStorage.setItem("href", path);
				} 
				if ($(this)[0].id) {
					var id = $(this)[0].getAttribute("id");
					sessionStorage.setItem("ID", id);
				}

			})
		})
	</script>
	</body>
</html>
