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
	
	
	<!--委托列表界面-->
	<div class="box"> 
		<section class = "content">
			<p class = "detail-title">
			<span><c:if test="${type==0}">市场调究管理>>分类管理</c:if>
				  <c:if test="${type==1}">资讯管理>>分类管理</c:if>
				  <c:if test="${type==2}">精英会>>分类管理</c:if>
				  <c:if test="${type==3}">系统管理>>热门关注分类管理</c:if>
				  <c:if test="${type==4}">系统管理>>网站地图分类管理</c:if></span>
				<a class = "setup"  target="contentF" onclick="toAddPage()">新增</a>
			</p>
		<form action="${ctx }/rest//category/list" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<input type="hidden" name="type" value="${type}">
			<ul class = "userinfo row">
				<c:if test="${type==2}">
				<li>
					<span style = "float:left;">平台：</span>
					<select name="source" id="source" style="height: 28px;width: 120px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
						<option value="">全部</option>
						<option value="0">网站</option>
						<option value="1">微站</option>
					</select>
				</li>
				</c:if>
				<%@include file="../layout/sherchArea.jsp" %>
				<li>
					<span style = "float:left;">分类名称：</span>
					<input type="text" name="cateName" style="width: 150px;" value="" placeholder="请输入分类名称" >
				</li>
				<c:if test="${not empty isSuper}">
					<li>
						<span style = "float:left;">是否删除：</span>
						<select name="isDelete" id="isDelete" style="height: 28px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
							<option value="0">未删除</option>
							<option value="1">已删除</option>
						</select>
					</li>
				</c:if>
				<li><input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/></li>
			</ul>
			</form>
			<input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;" onclick="batchDelete(1)" class = "public_btn bg1" type="submit"  value = "批量删除"/>
			<c:if test="${not empty isSuper}"><input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;" onclick="batchDelete(0)" class = "public_btn bg1" type="submit"  value = "批量恢复"/></c:if>
			<div id="listDiv"></div>
		</section>
	</div>
	
	
	<script type="text/javascript">




        function toAddPage(){
            layer.open({
                type: 2,
                skin: 'layer-style',
                area: ['400px','380px'],
                shadeClose: false, //点击遮罩关闭
                title:['新增分类'],
                resize: false,
                scrollbar:false,
                content:['${ctx}/rest/category/to_add?type=${type}', 'no']
            });
        }
		 window.onload = function(){
             $('#searchForm').submit();
		}
	</script>
	<!-- 本页私有js -->

</body>
</html>