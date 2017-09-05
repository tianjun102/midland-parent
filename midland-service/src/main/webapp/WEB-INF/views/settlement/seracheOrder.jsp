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
    </head>
    <body >
        
        	<input type="hidden" id="orderAmountCount1" value="${orderAmountCount}"  >
        	<div class = "table-responsive">
        	<form id="orderInfo" action="">
				<table class="table table-bordered table-add">
	 				<thead>
						<tr>
							<th>订单单号</th>
							<th>下单日期</th>
							<th>付款日期</th>
							<th>下级客户名称</th>
							<!-- <th>单据状态</th> -->
							<th>支付方式</th>
							<th>订单金额</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
				<c:choose>
					<c:when test="${!empty orderInfoList}">
						<c:forEach items="${orderInfoList}" var="orderInfo" varStatus="sta">
				       	<tr>
					       	<td><input name="orderInfoList[${sta.count-1}].id" value="${orderInfo.id}" type="hidden" ><input type="hidden" value="${orderInfo.orderSn}" name="orderInfoList[${sta.count-1}].orderSn" > ${orderInfo.orderSn}</td>
					       	<td><input name="orderInfoList[${sta.count-1}].createTime" value="${orderInfo.createTime}" type="hidden" >${orderInfo.createTime}</td>
					       	<td><input name="orderInfoList[${sta.count-1}].payTime" value="${orderInfo.payTime}" type="hidden" >${orderInfo.payTime}</td>
					       	<td><input name="orderInfoList[${sta.count-1}].custName" value="${orderInfo.custName}" type="hidden" >${orderInfo.custName}</td>
					       	<td><input name="orderInfoList[${sta.count-1}].payId" value="${orderInfo.payId}" type="hidden" >${orderInfo.payId}</td>
					       	<td><input name="orderInfoList[${sta.count-1}].orderAmount" value="${orderInfo.orderAmount}" type="hidden" >${orderInfo.orderAmount}</td>
					       	<td><a class="delete_img" onclick="deleteOrder(${orderInfo.id},${orderInfo.orderAmount},this);"></a>&nbsp;&nbsp;</td>
				       	</tr>
			       		</c:forEach>
			       	</c:when>
					<c:otherwise>
						<tr>
							<td colspan="7">没有找到数据!</td>
						</tr>
					</c:otherwise>
				</c:choose>
					</tbody>
				</table>
			</form>
			</div>
        
        
        <!-- 分页 -->
		 <%-- <c:if test="${!empty paginator}"> 
		    <c:set var="paginator" value="${paginator}"/>
		    <c:set var="target" value="listDiv"/>
		    <%@include file="../layout/pagination.jsp"%>
		 </c:if>  --%>
    </body>
</html>