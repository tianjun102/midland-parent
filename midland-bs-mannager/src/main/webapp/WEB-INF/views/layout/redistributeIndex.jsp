<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="${ctx }/assets/css/bootstrap.min.css">

<link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css" />
<link rel="stylesheet" href="${ctx }/assets/css/common.css">
<style type="text/css">
	.content ul.userinfo li>span {
		float: left;
		display: inline-block;
		width: 90px;
		height: 28px;
		line-height: 28px;
		text-align: right;
		font-size: 14px;
		color: rgb( 102, 102, 102 );
	}
	.content ul.userinfo li:not(:last-child) input {
		float: left;
		width: 180px;
		height: 28px;
		line-height: 28px;
		border: 1px solid #dbe2e6;
		border-radius: 4px;
		text-indent: 10px;
		outline-color: #0099e0;
	}
</style>
</head>
<body>
	
	
	<!--预约看房重新分配经纪人-->
	<div class="box"> 
		<section class = "content">
		<form action="${ctx }/rest/public/redistribute_page" method="POST" id="searchForm" onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<input type="hidden" id="id" value="${id}"/>
				<input type="hidden" id="url" value="${url}"/>
				<li><span >工号：</span><input type="text" name="jobNum" id="jobNum" placeholder="请输入工号" /></li>
				<li><span>名称：</span><input type="text" name="name" id="name" placeholder="请输入名称" /></li>
				<li><span>手机号码：</span><input type="text" name="phone" id="phone" placeholder="请输入手机号码" /></li>
				<li><span>门店：</span><input type="text" name="storeName" id="storeName" placeholder="请输入所属门店" /></li>

				<li><input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/></li>
			</ul>
			</form>
			<a href="#" class="public_btn bg1 listButton" onclick="saveData()">确定</a>
			<a href="#" class="public_btn bg1 listButton" onclick="closeWin();">取消</a>
			<div id="listDiv"></div>
		</section>
	</div>
	
	
	<script type="text/javascript">
		 window.onload = function(){
             $('#searchForm').submit();
		}
	</script>
</body>
</html>