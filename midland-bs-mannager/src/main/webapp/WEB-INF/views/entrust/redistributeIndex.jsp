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


<!--预约看房重新分配经纪人-->
<div class="box">
	<section class = "content">
		<form action="${ctx }/rest/entrust/redistribute_page" method="POST" id="searchForm"
			  onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<li><input type="hidden" id="entrustId" value="${entrustId}"/></li>
				<li><span>经纪人工号：</span>
					<input type="text" name="jobNum" id="jobNum" placeholder="请输入工号" />
				</li>
				<li><span>经纪人或门店名称：</span>
					<input type="text" name="keyword" id="name" placeholder="请输入名称" />
				</li>
				<li><input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/></li>
			</ul>
		</form>
		<div id="listDiv"></div>

	</section>
</div>


<script type="text/javascript">
	/* $(function(){
	 $('#searchForm').submit();
	 }); */
    window.onload = function(){
        $('#searchForm').submit();
    }

</script>
<!-- 本页私有js -->

</body>
</html>