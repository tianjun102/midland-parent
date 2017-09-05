<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="${ctx }/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx }/assets/css/common.css">
<link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css" />
</head>
<body>
<!--客户列表界面-->
<div class="box">
		<section class = "content">
			<p class = "detail-title">
				<span>客户列表</span>
				<a class = "setup savePath" href="${ctx}/rest/cust/toAddCustPage" target="contentF">创建客户</a>
			</p>
		<form action="${ctx}/rest/cust/custList" method="post" id="searchForm"
			onsubmit="submitSearchRequest('searchForm','listDiv');return false;">	
			<ul class = "userinfo row _align">
				<li><span>客户代码：</span><input type="text" name="custCode" id="custCode" placeholder="请输入客户代码" /></li>
				<li><span>客户简称：</span><input type="text" name="custName" id="custName" placeholder="请输入客户简称" /><em class = "_msg" style = "display: none; position:relative; margin-left:5px; height:12px; width:12px; background: url(${ctx}/assets/img/!.png) no-repeat; background-size:contain;"><span style = "display:none; position:absolute; bottom:10px; left:12px; border:;1px solid #ddd; background-color:#eee; color:#000; border-radius:5px 5px 5px 0;">123456</span></em></li>
				<li><span style = "float:left;">客户区域：</span>
				<select class="dropdown"  name="areaId" >
						<option value="" class="">全部</option>
							<c:if test="${!empty areas }">
								<c:forEach items="${areas }" var="rost">
									<option value="${rost.areaId }"
											${requestScope.areaId==rost.areaId ? 'selected' : '' }>${rost.areaName }</option>
								</c:forEach>
							</c:if>
				</select>
				</li>
				<li><span style = "float:left;">客户类别：</span>
					<select name="custType" id="custType" class="dropdown">
						<option value="">全部</option>
					    <option value="1">渠道服务商</option>
					    <option value="2">终端服务商</option>
					</select></li>
				<li>
					<span style = "float:left;">客户状态：</span>
					<select name="status" id="status" class="dropdown">
						<option value="">全部</option>
					    <option value="0">停用</option>
					    <option value="1">正常</option>
					</select>
				</li>
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
		/* $("._msg").mouseover(function(){
			$("._msg>span").show();
		})
		$("._msg").mouseout(function(){
			$("._msg>span").hide();
		}) */
	</script>
	
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/base.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
</body>
</html>