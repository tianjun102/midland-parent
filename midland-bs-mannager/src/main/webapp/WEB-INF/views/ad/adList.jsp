<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <style type="text/css">
		.pagination>li>a:focus, .pagination>li>a:hover, .pagination>li>span:focus, .pagination>li>span:hover {
			background-color:#a9b3c0!important;
			color:#ffffff!important;
		}
		.pagination>li>a, .pagination>li>span {
			color:#666666!important;
		}
		.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover{
			color:#ffffff!important;
			background-color:#18aec9!important;
		}
     </style>
	<title>智者汇 - 活动管理</title>
</head>
	<body>
	
	<!--活动管理界面-->

			<div class = "table-responsive m40">
				<table class="table table-bordered table-add">
	 				<thead>
						<tr>
							<!-- <th><input type="checkbox" id="selectAll" name = "selectAll"/></th> -->
							<th width="20%">广告位名称</th>
							<th width="12%">缩略图</th>
							<!-- <th>访问次数</th> -->
							<th width="8%">排序</th>
							<th width="10%">创建人</th>
							<th width="20%">创建时间</th>
							<th width="15%">广告状态</th>
							<th width="15%">操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${adList}" var="adList">
						<tr>
							<!-- <td><input type="checkbox"></td> -->
							<td>${adList.adName}</td>
							<td><img style="height: 36px;" src="${adList.ad_thumb_pic1}" class="suo"/></td>
							<%-- <td>${adList.clickNum}</td> --%>
							<td>${adList.sortOrder}</td>
							<td>${adList.userBy}</td>
							<td>${adList.addTime}</td>
							<td><c:if test="${adList.enabled =='0'}">关闭</c:if><c:if test="${adList.enabled =='1'}">开放</c:if>  </td>
							<td>
								<a target="contentF" class = "edit_img" title = "编辑" href="${ctx}/rest/ad/enterEditAd?adId=${adList.adId}"></a>
								<a onclick="deleteAd(${adList.adId})" class = "delete_img" title = "删除"></a>
								<!-- <a href="javascript:;" target="contentFrame" class = "admin_img" title = "管理图片"></a> -->
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- 分页 -->
			 <c:if test="${!empty paginator}"> 
			    <c:set var="paginator" value="${paginator}"/>
			    <c:set var="target" value="listDiv"/>
			    <%@include file="../layout/pagination.jsp"%>
			 </c:if> 
	<!-- 本页私有js -->
	<script type="text/javascript">
	$(document).ready(function(){
		//checkbox的整体选中
	
		$("#selectAll").on("click",function() {
			if($("input[name = 'selectAll']").is(":checked")){
				$("table td input").each(function(){
					$(this)[0].checked = true;
				})
			}
			if(!($("input[name = 'selectAll']").is(":checked"))){
				$("table td input").each(function(){
					$(this)[0].checked = false;
				})
			}
		})
	})
	

	</script>
	</body>
</html>
