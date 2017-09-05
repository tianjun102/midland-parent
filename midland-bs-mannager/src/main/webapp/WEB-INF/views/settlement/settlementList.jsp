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
        <title>栏目管理</title>
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
							<th width="10%">结算单号</th>
							<th width="10%">制单日期</th>
							<th width="15%">结算客户</th>
							<th width="10%">单据状态</th>
							<th width="8%">结算起始</th>
							<th width="8%">结算结束</th>
							<th width="12%">订单总额</th>
							<th width="12%">结算金额</th>
							<th width="15%">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${settlementList}" var="settlement" varStatus="sta">
						<tr>
					       	<td>${settlement.sett_sn}</td>
					       	<td>${fn:substring(settlement.create_time,0,10)}</td>
					       	<td>${settlement.cust_name}</td>
					       	<td>
					       	<c:if test="${settlement.status==0}">未确认</c:if>
					       	<c:if test="${settlement.status==1}">已确认</c:if>
					       	<c:if test="${settlement.status==2}">已记账</c:if>
					       	</td>
					       	<td>${fn:substring(settlement.begin_time,0,10)}</td>
					       	<td>${fn:substring(settlement.end_time,0,10)}</td>
					       	<td>${settlement.total_amount}</td>
					       	<td>${settlement.sett_amount}</td>
					       	<td>
					       	<a class = "see_img" title = "查看" href="${ctx}/rest/settlement/settlementDetails?id=${settlement.id}"></a>&nbsp;&nbsp;
					       	<c:if test="${userType==0}"><a <c:if test="${settlement.status!=2}">class = "edit_img" href="${ctx}/rest/settlement/settlementEdit?id=${settlement.id}"</c:if> target="contentF" ></a></c:if>
					       	<c:if test="${userType==0}"><a target="contentF" <c:if test="${settlement.status!=2}">class = "delete_img" title = "删除" onclick="deleteSettlement(${settlement.id});"</c:if> ></a>&nbsp;&nbsp;</c:if>
					       	</td>
						</tr>
				       </c:forEach>
					</tbody>
				</table>
			</div>
        
        
        
		 <c:if test="${!empty paginator}"> 
		    <c:set var="paginator" value="${paginator}"/>
		    <c:set var="target" value="listDiv"/>
		    <%@include file="../layout/pagination.jsp"%>
		 </c:if> 
    </body>
</html>