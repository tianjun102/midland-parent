<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	
	
	<!--列表界面-->
	<div class="box"> 
		<section class = "content">
			<p class = "detail-title">
				<a class = "setup"  target="contentF" onclick="toAddPage()">新增</a>
			</p>
		<form action="${ctx }/rest/loanType/list" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<%@include file="../layout/sherchArea.jsp" %>
				<li><span>选项名：</span>
					<input type="text" name="name" id="name" placeholder="请输入选项名称" /></li>
				</li>
				<li>
					<input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/>
				</li>
			</ul>
			</form>
			<input style="margin-left: 20px;width: 70px;height: 30px;line-height: 30px!important;" onclick="batchDelete(1)" class = "public_btn bg1" type="submit"  value = "批量删除"/>
			<input style="margin-left: 20px;width: 70px;height: 30px;line-height: 30px!important;" onclick="batchDelete(0)" class = "public_btn bg1" type="submit"  value = "批量恢复"/>
			<div id="listDiv"></div>
		</section>
	</div>
	
	
	<script type="text/javascript">
        function toAddPage(){
            layer.open({
                type: 2,
                skin: 'layer-style',
                area: ['500px','460px'],
                shadeClose: false, //点击遮罩关闭
                title:['新增'],
                resize: false,
                scrollbar:false,
                content:['${ctx}/rest/loanType/to_add', 'no']
            });
        }
		 window.onload = function(){
             $('#searchForm').submit();
		}
	</script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>