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
		<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ctx}/assets/css/easydropdown.css" />
		<link rel="stylesheet" href="${ctx}/assets/css/common.css">
		<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
    </head>
    <body >
        	<!--新增结算单-->
	<div class="box">
		<section class = "content">
			<p class = "detail-title">
				<span>结算单列表</span>
				<a class = "setup" href="${ctx}/rest/settlement/settlementIndex" target="contentF">财务结算单列表</a>
			</p>
			<form id="formId">
			<ul class = "userinfo row" style="margin-bottom: 20px;">
				<li><span>结算单号：</span><input disabled="disabled"  value="${settlement.sett_sn}"><input type="hidden" name="sett_sn" value="${settlement.sett_sn}"></li>
				<li><span>创建日期：</span><input disabled="disabled"  value="${settlement.create_time}"><input type="hidden" name="create_time" value="${settlement.create_time}"></li>
				<li>
					<span>结算客户：</span><input disabled="disabled" id="CustName" name="" value="${settlement.cust_name}"><input type="hidden" name="cust_name" value="${settlement.cust_name}">
				</li>
				<li><span>state：</span><input type="hidden" name="status" value="${settlement.status}" ><input disabled="disabled" name="" value="<c:if test="${settlement.status==0}">未确认</c:if><c:if test="${settlement.status==1}">已确认</c:if><c:if test="${settlement.status==2}">已记账</c:if>"></li>
				<li><span>订单总额：</span><input disabled="disabled" id="" value="${settlement.total_amount}"><input type="hidden" name="total_amount" value="${settlement.total_amount}"></li>
				<li><span>结算金额：</span><input disabled="disabled" name="" value="${settlement.sett_amount}"><input type="hidden" name="sett_amount" value="${settlement.sett_amount}"></li>
				<li><span>描述：</span><textarea disabled="disabled" style="width:590px;height:100px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;" name="" rows="" cols="">${settlement.sett_note}</textarea><input type="hidden" name="sett_note" value="${settlement.sett_note}" ></li>
			</ul>
			<ul class = "userinfo" style="overflow: hidden; margin-top: 20px; background-color: #eef1f5; border: 1px solid #cdd7dd;">
				<li style="margin-left: 0;padding: 10px 0;"><span>结算日期：</span><input disabled="disabled" style="border-radius:4px;border: 1px solid #dbe2e6;text-indent: 10px;height: 38px;margin-left: 0px;" type="text"  value="${fn:substring(settlement.begin_time,0,19)}" ><input type="hidden" name="betweenTimeStart" value="${fn:substring(settlement.begin_time,0,19)}" ><em>-</em><input disabled="disabled" style="border-radius:4px;border: 1px solid #dbe2e6;text-indent: 10px;height: 38px;margin-left: 0px;" type="text" value="${fn:substring(settlement.end_time,0,19)}"><input type="hidden" name="betweenTimeEnd" value="${fn:substring(settlement.end_time,0,19)}" ></li>
			</ul>
			</form>
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
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orderInfoList}" var="orderInfo" varStatus="sta">
				       	<tr>
						    <td><input name="orderInfoList[${sta.count-1}].id" value="${orderInfo.id}" type="hidden" ><input name="orderInfoList[${sta.count-1}].orderSn" value="${orderInfo.orderSn}" type="hidden" > ${orderInfo.orderSn}</td>
						    <td><input name="orderInfoList[${sta.count-1}].createTime" value="${orderInfo.createTime}" type="hidden" >${orderInfo.createTime}</td>
						    <td><input name="orderInfoList[${sta.count-1}].payTime" value="${orderInfo.payTime}" type="hidden" >${orderInfo.payTime}</td>
						    <td><input name="orderInfoList[${sta.count-1}].custName" value="${orderInfo.custName}" type="hidden" >${orderInfo.custName}</td>
						    <td><input name="orderInfoList[${sta.count-1}].payId" value="${orderInfo.payId}" type="hidden" >${orderInfo.payId}</td>
						    <td><input name="orderInfoList[${sta.count-1}].orderAmount" value="${orderInfo.orderAmount}" type="hidden" >${orderInfo.orderAmount}</td>
				       	</tr>
			       		</c:forEach>
					</tbody>
				</table>
				</form>
			</div>
			<p class = "bottomc">
				<a id="inquery-exports" href="javascript:;" class = "public_btn bg2">导出</a>	
			</p>
		</section>
	</div>
        
        
     <script type="text/javascript">
     $("#inquery-exports").click(function(){
     	var SettlementData = $("#formId").serialize()+"&"+$("#orderInfo").serialize();
        	window.open("${ctx}/rest/settlement/exportExSettlement?"+SettlementData);
    	 });
     </script>   
    </body>
</html>