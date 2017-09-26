<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--角色列表界面-->
	<div class="box"> 
		<section class = "content">
			<p class = "detail-title">
				<span>角色列表</span>
				<a class = "setup" href="javascript:;" target="contentF" onclick="toAddPage()">创建角色</a>
			</p>
			<form action="${ctx}/rest/role/roleList" method="post" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<li><span>角色代码：</span><input type="text" name="roleSign"
				id="roleSign" placeholder="请输入角色代码" value="${roleSign}" /></li>
			<li><span>角色名称：</span><input type="text" name="roleName"
				id="roleName" placeholder="请输入角色名称" value="${roleName}" /></li>
			<%-- <li><span>类型：</span> <select name="roleType" id="roleType"
				class="dropdown">
					<option value=""
						<c:if test="${roleType=='-1'}">selected="selected"</c:if>>请选择</option>
					<option value="0"
						<c:if test="${roleType=='0'}">selected="selected"</c:if>>沃可视</option>
					<option value="1"
						<c:if test="${roleType=='1'}">selected="selected"</c:if>>省代</option>
					<option value="2"
						<c:if test="${roleType=='2'}">selected="selected"</c:if>>区服</option>
					<option value="3"
						<c:if test="${roleType=='3'}">selected="selected"</c:if>>门代</option>	
			</select></li> --%>
				<li><input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/></li>
			</ul>
			</form>
			<div id="listDiv"></div>
		</section>
	</div>
	
	
<script type="text/javascript">
	window.onload = function(){
		$('#searchForm').submit();
	}
	
	//新增
	function toAddPage(){
		layer.open({
			type: 2,
			title: ['创建角色'],
			shade: 0.3,
			area: ['480px', '350px'],
			content: ['${ctx}/rest/role/toAddPage','no']
			});
	}
	
</script>
</body>
</html>