<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
</style>
</head>
<body>
	
	
	<!--列表界面-->
	<div class="box"> 
		<section class = "content">
			<p class = "detail-title">
				<span>联络人列表</span>
				<a class = "setup"  target="contentF" onclick="toAddPage()">新增</a>
			</p>
		<form action="${ctx }/rest/liaisonRecordEmail/list" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<%@include file="../menu/area.jsp" %>
				<li><span>联络人：</span>
					<input type="text" name="contactName" id="contactName" placeholder="请输入联络人" /></li>
				</li>
				<li><span>邮箱：</span>
					<input type="text" name="email" id="email" placeholder="请输入邮箱" /></li>
				</li>
				<li><span>手机号码：</span>
					<input type="text" name="phone" id="phone" placeholder="请输入手机号码" /></li>
				</li>
				<li>
					<input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/>
					<a href="${ctx}/rest//liaisonRecord//index" class="public_btn bg1" target="contentF" >返回</a>
				</li>
			</ul>
			</form>
			<div id="listDiv"></div>
		</section>
	</div>
	
	
	<script type="text/javascript">
        function toAddPage(){
            layer.open({
                type: 2,
                skin: 'layer-style',
                area: ['100%', '100%'],
                shadeClose: false, //点击遮罩关闭
                title:['新增'],
                resize: false,
                scrollbar:false,
                content:['${ctx}/rest/liaisonRecordEmail/to_add', 'yes']
            });
        }
		 window.onload = function(){
             $('#searchForm').submit();
		}
	</script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>