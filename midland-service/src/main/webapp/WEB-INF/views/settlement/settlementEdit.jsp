<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<title>新增结算单</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="MobileOptimized" content="320">
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/assets/css/common.css">
<link rel="stylesheet" href="${ctx}/assets/css/easydropdown.css" />
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
<script src="${ctx}/assets/scripts/common.js"></script>
<script src="${ctx}/assets/scripts/layer.js" type="text/javascript"></script>
<script src="${ctx}/assets/scripts/base.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/assets/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/assets/scripts/inputControl.js" type="text/javascript"></script>
<style>
	#customer>dd {
		margin:3px 0;
	}
	#customer>dd:hover {
		background:#ddd;
	}
</style>
</head>
<body>


		<!--修改结算单-->
	<div class="box">
		<section class = "content">
			<form id="formId" action="${ctx}/rest/settlement/saveSettlement" method="post" onsubmit="setOrder(); return true;">
			<input id="settId" name="id" value="${settlement.id}" type="hidden">
			<input type="hidden"  name="cust_id" value="${settlement.cust_id}">
			<div id="settlement">
				<p class = "detail-title">
					<span>编辑结算单</span>
					<a class = "setup" href="${ctx}/rest/settlement/settlementIndex" target="contentF">财务结算单列表</a>
				</p>
				<ul class = "userinfo row">
					<li><span>结算单号：</span><input disabled="disabled" value="${settlement.sett_sn}"><input type="hidden" name="sett_sn" value="${settlement.sett_sn}"><span class = "_star ">*</span></li>
					<li><span>创建日期：</span><input onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="create_time" value="${fn:substring(settlement.create_time,0,19)}"></li>
					<li style = "position:relative;">
						<span>结算客户：</span><input id="CustName" name="" value="${settlement.cust_name}" style="background: url(${ctx}/assets/img/search.png) no-repeat 98% center;background-size:24px;"><dl style="width: 248px; position: absolute; left: 72px; top: 57px; cursor: pointer; background: rgb(238, 238, 238); display: none;" id="customer"></dl>
						<span class = "_star ">*</span>
					</li>
					<li><span>state：</span><input type="hidden" name="status" value="${settlement.status}" ><input disabled="disabled" name="" value="<c:if test="${settlement.status==0}">未确认</c:if><c:if test="${settlement.status==1}">已确认</c:if><c:if test="${settlement.status==2}">已记账</c:if>"><span class = "_star ">*</span></li>
					<li><span>订单总额：</span><input disabled="disabled" id="total_amount" value="${settlement.total_amount}"><input type="hidden" name="total_amount" value="${settlement.total_amount}"></li>
					<li><span>结算金额：</span><input onfocus="InitInput.setNumber(this,9,2,2)" name="sett_amount" value="${settlement.sett_amount}"><span class = "_star ">*</span></li>
					<li><span>描述：</span><textarea style="width:590px;height:100px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;" name="sett_note" rows="" cols="">${settlement.sett_note}</textarea></li>
				</ul>
			</div>
			</form>
			<form id="searchForm" action="${ctx}/rest/settlement/seracheOrderList" method="post" onsubmit="submitSearch();return false;">
			<ul class = "userinfo" style="overflow: hidden; margin-top: 20px; background-color: #eef1f5; border: 1px solid #cdd7dd;">
				<li style  = "margin-left: 0; padding: 10px 0;">
				<span>结算日期：</span>
				<input id="time1" class="Wdate" style="width: 200px;" onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'time2\')}'})"  type="text" value="${fn:substring(settlement.begin_time,0,19)}" name="betweenTimeStart">
				<input type="hidden" id="custId" name="custId" value="${settlement.cust_id}"><em class = "gang">-</em><input onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'time1\')}'})" style="width: 200px;" class="Wdate" id="time2" type="text" name="betweenTimeEnd" value="${fn:substring(settlement.end_time,0,19)}"><span class = "_star ">*</span></li>
				<li style =  "padding: 10px 0;"><input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "确定"/></li>
			</ul>
			</form>
			
			
			
			
			
			<div id="listDiv">
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
							<c:forEach items="${orderInfoList}" var="orderInfo" varStatus="sta">
					       	<tr>
						       	<td><input name="orderInfoList[${sta.count-1}].id" value="${orderInfo.id}" type="hidden" ><input name="orderInfoList[${sta.count-1}].orderSn" value="${orderInfo.orderSn}" type="hidden" > ${orderInfo.orderSn}<input type="hidden" id="orderAmountCount${sta.count-1}" value="${orderAmountCount}"  ></td>
						       	<td><input name="orderInfoList[${sta.count-1}].createTime" value="${orderInfo.createTime}" type="hidden" >${orderInfo.createTime}</td>
						       	<td><input name="orderInfoList[${sta.count-1}].payTime" value="${orderInfo.payTime}" type="hidden" >${orderInfo.payTime}</td>
						       	<td><input name="orderInfoList[${sta.count-1}].custName" value="${orderInfo.custName}" type="hidden" >${orderInfo.custName}</td>
						       	<td><input name="orderInfoList[${sta.count-1}].payId" value="${orderInfo.payId}" type="hidden" >${orderInfo.payId}</td>
						       	<td><input name="orderInfoList[${sta.count-1}].orderAmount" value="${orderInfo.orderAmount}" type="hidden" >${orderInfo.orderAmount}</td>
						       	<td><a class="delete_img" onclick="deleteOrder(${orderInfo.id},${orderInfo.orderAmount},this);"></a>&nbsp;&nbsp;</td>
					       	</tr>
				       		</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
			</div>
			<p class = "bottomc">
				<a id="inquery-exports" href="javascript:;" class = "public_btn bg2">导出</a>	
				<a id="save" <c:if test="${settlement.status==1}">style="display: none;"</c:if> onclick="submitSettlement();" href="javascript:;" class = "public_btn bg2">保存</a>	
				<a id="confirm" <c:if test="${settlement.status==1}">style="display: none;"</c:if> onclick="confirm();" href="javascript:;" class = "public_btn bg2">确认</a>
				<a id="reConfirm" <c:if test="${settlement.status==0}">style="display: none;"</c:if> onclick="reConfirm()" href="javascript:;" class = "public_btn bg2">反确认</a>	
				<a onclick="accounting()" href="javascript:;" class = "public_btn bg2">记账</a>	
				<a target="contentF" href="${ctx}/rest/settlement/settlementIndex" class = "public_btn bg2">返回</a>	
			</p>
		</section>
	</div>
</body>
<script type="text/javascript">

window.onload=function(){
	$('#CustName').bind('input propertychange', function() {
		getCustName(); 
	});  
} 
	
	function getCustName() {
		$("#customer").show();
		var CustName = $("#CustName").val();
		$("#customer").find("dd").remove();
		$.ajax({
					type : "post",
					url : "${ctx}/rest/settlement/seracheCustomer?custName="
							+ CustName,
					async : false, // 此处必须同步
					dataType : "json",
					data : "",
					success : function(data) {
	 					data.data.forEach(function(list){
	 						
	 						$("#customer").append("<dd onclick='setddvalue(this)' lay-value="+list.custId+" class=''>"+list.custName+"</dd>");
	 						
	 					})
					}
				});

	};
	
	function setddvalue (ths){
		var custId = $(ths).attr("lay-value");
		var custName =  $(ths).text();
		$("#CustName").val(custName);
		$("#custId").val(custId);
		$("input[name='cust_id']").val(custId);
		$("#customer").hide();
	}
	
	$(document).ready(function(){
		$(document).click(function(e){
			if($(this).parent().attr("id") != "customer") {
				$("#customer").hide();
			}
		})
	});
	
	function setOrder(){

		var html = $("input[name^='orderInfoList']");
		$("#settlement").append(html);
	}
	
	function submitSettlement(){

		var SettlementData = $("#formId").serialize()+"&"+$("#orderInfo").serialize()+"&"+$("#searchForm").serialize();
		$.ajax({
			type : "post",
			url : "${ctx}/rest/settlement/saveSettlement",
			async : false, // 此处必须同步
			dataType : "json",
			data : SettlementData,
			success : function(data) {
				$("#save").hide();
				$("#settId").val(data.id);
				layer.msg("保存成功",{icon:1});
			},
			error:function(){
				layer.msg("保存失败！",{icon:2});
			}
		});
	}
	function submitSearch(){
		var SettlementData = $("#searchForm").serialize();
		$.ajax({
			type : "post",
			url : "${ctx}/rest/settlement/seracheOrderList",
			async : false, // 此处必须同步
			dataType : "html",
			data : SettlementData,
			success : function(data) {
				$("#listDiv").html(data);
				//
				$("input[name='total_amount']").val($("#orderAmountCount1").val());
				$("#total_amount").val($("#orderAmountCount1").val());
			}
		});
		
	}
	
	function confirm(){
		var settId = $("#settId").val();
		$.ajax({
			type : "post",
			url : "${ctx}/rest/settlement/settlementConfirm?id="+settId,
			async : false, // 此处必须同步
			dataType : "json",
			data : "",
			success : function(data) {
				$("#reConfirm").show();
				$("#save").hide();
				layer.msg("确认成功",{icon:1});
			},
			error:function(){
				layer.msg("确认失败！",{icon:2});
			}
		});
	}
	
	function reConfirm(){
		var settId = $("#settId").val();
		$.ajax({
			type : "post",
			url : "${ctx}/rest/settlement/reversalConfirm?id="+settId,
			async : false, // 此处必须同步
			dataType : "json",
			data : "",
			success : function(data) {
				$("#save").show();
				$("#confirm").show();
				layer.msg("反确认成功",{icon:1});
			},
			error:function(){
				layer.msg("反确认失败！",{icon:2});
			}
		});
	}
	
	function accounting(){
		var settId = $("#settId").val();
		$.ajax({
			type : "post",
			url : "${ctx}/rest/settlement/accounting?id="+settId,
			async : false, // 此处必须同步
			dataType : "json",
			data : "",
			success : function(data) {
				$("#save").hide();
				$("#reConfirm").hide();
				$("#confirm").hide();
				layer.msg("记账成功",{icon:1});
			},
			error:function(){
				layer.msg("记账失败！",{icon:2});
			}
		});
	}
	
	function deleteOrder(orderId,orderAmount,ths){

		var settId = $("#settId").val();
		var total_amount = $("#total_amount").val();//订单总额
		if (settId==""){
			$(ths).parent().parent().remove();
			$("#total_amount").val(total_amount-orderAmount);
			$("input[name='total_amount']").val(total_amount-orderAmount);
			return;
		}
		$.ajax({
			type : "post",
			url : "${ctx}/rest/settlement/deleteSettlementItem?id="+settId+"&total_amount="+total_amount+"&orderId="+orderId+"&orderAmount="+orderAmount,
			async : false, // 此处必须同步
			dataType : "json",
			data : "",
			success : function(data) {
				console.log("成功 ")
				$(ths).parent().parent().remove();
				$("#total_amount").val(total_amount-orderAmount);
				$("input[name='total_amount']").val(total_amount-orderAmount);
			}
		});
	}
	
    $("#inquery-exports").click(function(){
    	var SettlementData = $("#formId").serialize()+"&"+$("#orderInfo").serialize();
       	window.open("${ctx}/rest/settlement/exportExSettlement?"+SettlementData);
   	 });
	
</script>

</html>