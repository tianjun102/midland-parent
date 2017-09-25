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
<!--公告管理界面-->
<div class="box">
	<section class = "content">
			<p class = "detail-title">
				<span>公告列表</span>
			</p>
		<form action="${ctx}/rest/notice/noticeShowList" method="post" id="searchForm"
			onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
		</form>		
		<div id="listDiv"></div>
	</section>
</div>
	

<script type="text/javascript">
	window.onload = function(){
		$('#searchForm').submit();
	}

</script>
	<script src="${ctx}/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/assets/scripts/layer.js" type="text/javascript"></script>
	<script src="${ctx}/assets/scripts/base.js" type="text/javascript"></script>
</body>
</html>