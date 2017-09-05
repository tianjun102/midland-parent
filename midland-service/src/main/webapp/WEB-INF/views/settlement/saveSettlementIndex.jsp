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
<link rel="stylesheet" href="${ctx}/assets/css/easydropdown.css" />
<link rel="stylesheet" href="${ctx}/assets/css/common.css">
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
	
		<!--新增结算单-->
	<div class="box">
		<section class = "content">
			<form id="formId" action="${ctx}/rest/settlement/saveSettlement" method="post" onsubmit="setOrder(); return true;">
				<input type="hidden"  name="cust_id" value="">
				<input id="settId" name="id" value="" type="hidden">
			<div id="settlement">
				<p class = "detail-title">
					<span>新增结算单</span>
					<a class = "setup" href="${ctx}/rest/settlement/settlementIndex" target="contentF">财务结算单列表</a>
				</p>
				<ul class = "userinfo row">
					<li>
						<span>结算单号：</span>
						<input disabled="disabled"  value="${settSn}">
						<input type="hidden" name="sett_sn" value="${settSn}">
						<span class = "_star ">*</span>
					</li>
					<li>
						<span>创建日期：</span>
						<input disabled="disabled" value="${createDate}">
						<input type="hidden" name="create_time" value="${createDate}" >
					</li>
					<li style = "position:relative;">
						<span>结算客户：</span>
						<input id="CustName" name="" value="" style = "background: url(${ctx}/assets/img/search.png) no-repeat 98% center;background-size:24px;">
						<span class = "_star ">*</span>
						<dl style="width:248px; position: absolute;left: 72px;top: 57px;cursor:pointer; background:#eee;" id="customer"></dl>
					</li>
					<li>
						<span>state：</span>
						<input disabled="disabled" name="" value="新增">
						<span class = "_star ">*</span>
					</li>
					<li>
						<span>订单总额：</span>
						<input disabled="disabled" id="total_amount" value="">
						<input type="hidden" name="total_amount" value="">
					</li>
					<li>
						<span>结算金额：</span>
						<input name="sett_amount" value="" onfocus="InitInput.setNumber(this,9,2,2)">
						<span class = "_star ">*</span>
					</li>
					<input type="hidden" value=""/>
				</ul>
				<div style = "margin-top:15px;">
					<span style = "width:70px; float:left; display:inline-block; text-align:right; color:rgb( 102, 102, 102 );">描述：</span>
					<textarea style="margin-left: 5px; width:590px;height:100px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;" name="sett_note" rows="" cols=""></textarea>
				</div>
			</div>
			</form>
			<form id="searchForm" action="${ctx}/rest/settlement/seracheOrderList" method="post" onsubmit="submitSearch();return false;">
			<ul class = "userinfo" style="overflow: hidden; margin-top: 20px; background-color: #eef1f5; border: 1px solid #cdd7dd;">
				<li style  = "margin-left: 0; padding: 10px 0;"><span>结算日期：</span><input style="width: 200px;" id="time1" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'time2\')}'})" class="Wdate" type="text" value="" name="betweenTimeStart"><input type="hidden" id="custId" name="custId" value=""><em class = "gang">-</em><input onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'time1\')}'})" class="Wdate" style="width: 200px;" id="time2" type="text" name="betweenTimeEnd" value=""><span class = "_star ">*</span></li>
				<li style =  "padding: 10px 0;"><input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "搜索"/></li>
			</ul>
			</form>
			<div id="listDiv"></div>
			<p class = "bottomc">
				<a href="javascript:;" id="inquery-exports" class = "public_btn bg2">导出</a>	
				<a id="save" onclick="submitSettlement();" href="javascript:;" class = "public_btn bg2">保存</a>	
				<a id="confirm" onclick="confirm();" href="javascript:;" class = "public_btn bg2">确认</a>
				<!-- <a style="background-color: #a0a9ba;" id="reConfirm" onclick="reConfirm()" href="javascript:;" class = "public_btn bg2">反确认</a>	 -->
				<a style="display: none;" id="reConfirm" href="javascript:;" class = "public_btn bg2">反确认</a>
				<a id="accounting" onclick="accounting()" href="javascript:;" class = "public_btn bg2">记账</a>	
				<a href="${ctx}/rest/settlement/settlementIndex" target="contentF" class = "public_btn bg2">返回</a>	
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
	 						
	 						$("#customer").append("<dd onclick='setddvalue(this)' lay-value="+list.custId+" class=''>"+list.custName+"</setddvaluesetddvalue>");
	 						
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
		var required = true;
		if($("#CustName").val()==""||$("input[name='sett_amount']").val()==""){
			required = false;
			layer.msg("请完成必填项");
		}

		var SettlementData = $("#formId").serialize()+"&"+$("#orderInfo").serialize()+"&"+$("#searchForm").serialize();
		if(required){
		$.ajax({
			type : "post",
			url : "${ctx}/rest/settlement/saveSettlement",
			async : false, // 此处必须同步
			dataType : "json",
			data : SettlementData,
			success : function(data) {
				$("#settId").val(data.id);
				$("#save").hide();
				$("#save").removeAttr("onclick");
				layer.msg("保存成功",{icon:1});
			},
			error:function(){
				layer.msg("保存失败！",{icon:2});
			}
		});
		}
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
		if(settId==""){
			layer.alert("请先保存后再确认！",{icon:0});
			return false;
		}
		$.ajax({
			type : "post",
			url : "${ctx}/rest/settlement/settlementConfirm?id="+settId,
			async : false, // 此处必须同步
			dataType : "json",
			data : "",
			success : function(data) {
				$("#reConfirm").show();
				$("#reConfirm").attr("onclick","reConfirm()");
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
				layer.msg("反确认成功",{icon:1});
				$("#save").show();
				$("#save").attr("onclick","submitSettlement();");
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
				layer.msg("记账成功",{icon:1});
				$("#save").hide();
				$("#reConfirm").hide();
				$("#confirm").hide();
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