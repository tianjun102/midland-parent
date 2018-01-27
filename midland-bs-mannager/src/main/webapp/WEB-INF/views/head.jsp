<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
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
	<script type="text/javascript">
		$(function(){
			mytime()
		})
        function mytime(){
           // var a = new Date().Format("yyyy-MM-dd HH:mm:ss");
            var a = format(new Date(),"yyyy-MM-dd HH:mm:ss")

            document.getElementById("time1").innerHTML = a;
        }

        setInterval(function() {mytime()},1000);
        function format(date,str){
            var mat={};
            mat.M=date.getMonth()+1;//月份记得加1
            mat.H=date.getHours();
            mat.s=date.getSeconds();
            mat.m=date.getMinutes();
            mat.Y=date.getFullYear();
            mat.D=date.getDate();
            mat.d=date.getDay();//星期几
            mat.d=check(mat.d);
            mat.H=check(mat.H);
            mat.M=check(mat.M);
            mat.D=check(mat.D);
            mat.s=check(mat.s);
            mat.m=check(mat.m);
            console.log(typeof mat.D)
            if(str.indexOf(":")>-1){
                mat.Y=mat.Y.toString().substr(0,4);
                return mat.Y+"/"+mat.M+"/"+mat.D+" "+mat.H+":"+mat.m+":"+mat.s;
            }
            if(str.indexOf("/")>-1){
                return mat.Y+"/"+mat.M+"/"+mat.D+" "+mat.H+"/"+mat.m+"/"+mat.s;
            }
            if(str.indexOf("-")>-1){
                return mat.Y+"-"+mat.M+"-"+mat.D+" "+mat.H+"-"+mat.m+"-"+mat.s;
            }
        }
        //检查是不是两位数字，不足补全
        function check(str){
            str=str.toString();
            if(str.length<2){
                str='0'+ str;
            }
            return str;
        }
	</script>
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
				<c:when test="${!empty list }">
						<c:forEach items="${list }" var="notice" varStatus="xh">
							<li><a href="${ctx}/rest/notice/viewNotice?id=${notice.id }" target="contentF">系统公告：${notice.title }</a><span class="date"><div class='time'></div></span></li>
						</c:forEach>
				</c:when>
				<c:otherwise>

					<li><a href="javascript:;" target="_blank">系统公告：欢迎来到美联物业管理平台!</a> </li>
				</c:otherwise>
				</c:choose>

				</ul>
			</div>
		</c:if>

		<c:if test="${user.userType==1}">

		</c:if>

		</div>
		<ul class = "user">
			<li><span class="date" id="time1"></span></li>
			<c:if test="${user.headImg == null}">
				<li><a href="" onclick="toUpdate('${user.id}')" class = "admin"><img src="${ctx}/assets/img/admin.png"/></a></li>
			</c:if>
			<c:if test="${user.headImg != null}">
				<li><a href="" onclick="toUpdate('${user.id}')" class = "admin"><img style="width:40px;height: 40px" src="${user.headImg}"/></a></li>
			</c:if>
			<li ><span class = "user-name">${user.userCnName }</span></li>
			<li>
				<span class = "user-name glyphicon glyphicon glyphicon-triangle-bottom sign"></span>
				<div class ="out"><em>退出</em></div>
			</li>	
		</ul>
		
	</header>
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer.js"></script>
	<script src="${ctx}/assets/scripts/jquery.SuperSlide.2.1.1.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		jQuery(".txtScroll-top").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"topLoop",autoPlay:true});

		$(".user-name").click(function(e){
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
        function toUpdate(userId) {
            window.open("${ctx}/rest/user/hrefUpdateUser?userId=" +userId, "contentF");
            <%--layer.open({--%>
                <%--type: 2,--%>
                <%--title: ['用户修改'],--%>
                <%--shade: 0.3,--%>
                <%--area: ['100%', '100%'],--%>
                <%--content: ['${ctx}/rest/user/toUpdatePage?userId=' +userId,"contentF"]--%>
            <%--});--%>
        }
	</script>
	</body>
</html>
