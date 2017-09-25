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

</head>
<body>
	
	
	<!--用户列表界面-->
	<div class = "box">
		<section class = "content">
			<p class = "detail-title">
				<span>产品分销方案</span>
				<a class = "setup"  target="contentF" href="${ctx}/rest/distPlan/showInputDistPlan">添加分销方案</a>
			</p>
		<form action="${ctx }/rest/distPlan/showDistPlanList" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<li><span>方案代码：</span><input type="text" name="distPlanSn"  placeholder="请输入方案代码" /></li>
				<li><span>方案名称：</span><input type="text" name="distPlanName" placeholder="请输入方案名称" /></li>
				<li>
					<input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/>
					<input class = "public_btn bg1" type="reset" name="clear" id="clear" value = "清空条件"/>
				</li>
			</ul>
			</form>
			<div id="listDiv"></div>
		</section>
	</div>
	
	<script type="text/javascript">
		
		 window.onload = function(){
			$('#searchForm').submit();
		}
	</script>
	<!-- 本页私有js -->
	
	
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/common.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/base.js" ></script>
</body>
</html>