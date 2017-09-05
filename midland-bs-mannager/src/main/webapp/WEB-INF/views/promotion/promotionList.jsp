<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@include file="../layout/tablib.jsp"%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <base href="<%=basePath%>">
        <meta charset="utf-8" />
        <title>促销策略</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta name="MobileOptimized" content="320">
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
    </head>
    <body >
        	<div class = "table-responsive m40">
				<table class="table table-bordered table-add">
	 				<thead>
						<tr>
							<th width="15%">策略编号</th>
							<th width="20%">策略名称</th>
							<th width="10%">策略性质</th>
							<th width="10%">审核状态</th>
							<th width="10%">开始日期</th>
							<th width="10%">结束日期</th>
							<th width="25%">操作</th>
						</tr>
					</thead>
					<tbody>
				<c:choose>
					<c:when test="${!empty promotionList}">
					 	<c:forEach items="${promotionList}" var="promotion">       	
						<tr>
				       		<td ><input type="hidden" name="id" value="${promotion.id }" >${promotion.prom_sn}</td>
					       	<td>${promotion.prom_name}</td>
					       	<td>
					       	<c:if test="${promotion.type == 0}">买即赠</c:if>
					       	<c:if test="${promotion.type == 1}">满即赠</c:if>
					       	</td>
					       	<td>
					       	<c:if test="${promotion.is_mutex =='1'}">已审核</c:if>
					       	<c:if test="${promotion.is_mutex =='0'}">未审核</c:if>
					       	</td>
					       	<td>${promotion.prom_start}</td>
					       	<td>${promotion.prom_end}</td>
					       	<td>
					       		<a title="查看详细" class="see_img" href="${ctx}/rest/promotion/enterPromotionDetails?prom_id=${promotion.id}"></a>
					       		<a <c:if test="${promotion.is_mutex =='0'}">target="contentF" class = "edit_img" title = "编辑" href="${ctx}/rest/promotion/enterPromotionEdit?prom_id=${promotion.id}"</c:if> ></a>
					       		<c:if test="${promotion.is_mutex =='1'}"><a></a></c:if> 
					       		<c:if test="${promotion.is_mutex =='0'}"><a class = "examine_img" onclick="UpdateisMutex(${promotion.is_mutex},${promotion.id })"></a></c:if>
					       		<a target="contentF"  <c:if test="${promotion.enabled =='0'}"> onclick="deleteProm(${promotion.id })" class = "delete_img" </c:if> ></a>
					       		
					       		<c:if test="${promotion.enabled =='1'}"><a class="onoff_img" title="启用" onclick="updateEnabled(${promotion.enabled},${promotion.id })"></a></c:if>
					       		<c:if test="${promotion.enabled =='0'}"><a class="offon_img" title="禁用" onclick="updateEnabled(${promotion.enabled},${promotion.id })"></a></c:if>
					       	</td>
						</tr>
						</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="7">暂无数据!</td>
							</tr>
						</c:otherwise>
					</c:choose>
					</tbody>
				</table>
			</div>
        
        <!-- 分页 -->
	   <c:if test="${!empty paginator}"> 
		    <c:set var="paginator" value="${paginator}"/>
		    <c:set var="target" value="listDiv"/>
		    <%@include file="../layout/pagination.jsp"%>
	   </c:if> 

    </body>
    <script type="text/javascript">
    //启用，禁用
    function updateEnabled(enabled,id){
    	if(enabled==1){
    		enabled=0;
    	}else if(enabled == 0){
    		enabled = 1;
    	}
    	
		$.ajax({ 
			type: "post", 
			url: "${ctx}/rest/promotion/updateEnabled?enabled="+enabled+"&id="+id, 
			async:false, // 此处必须同步
			dataType: "json",
			data:"",
			success: function(data){ 

				$('#searchForm').submit();
			} 
		});
    }
    //审核
    function UpdateisMutex(isMutex,id){
    	if(isMutex==1){
    		isMutex=0;
    	}else if(isMutex == 0){
    		isMutex = 1;
    	}
    	
		$.ajax({ 
			type: "post", 
			url: "${ctx}/rest/promotion/updateisMutex?is_mutex="+isMutex+"&id="+id, 
			async:false, // 此处必须同步
			dataType: "json",
			data:"",
			success: function(data){
				layer.msg("审核成功！",{icon:1});
				$('#searchForm').submit();
			} 
		});
    }
    
    </script>
</html>