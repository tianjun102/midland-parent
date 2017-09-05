<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>智者汇 - 订单列表</title>
	<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/assets/css/easydropdown.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/common.css">
	<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.css">
</head>
<style>
button.showbtn {
	float: right;
	width: 80px;
	height: 38px;
	line-height: 38px;
	text-align: center;
	background-color: #a0a9ba;
	color: #fff;
	font-size: 14px;
	border-radius: 5px;
}
</style>
<body>
	<!--订单列表界面-->
	<div class="box"> 
		<section class = "content">
			<p class = "detail-title">
				<span>订单列表</span>
			</p>
			<div>
   			
   		</div>
   			<form action="${ctx }/rest/order/showOrderInfoList" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<li><span>订单单号：</span>
					<input type="hidden" name="flag"  id="flag"/>
					<input type="text" name="orderSn"  placeholder="请输入订单单号" />
					</li>
				<li><span>客户名称：</span><input type="text" name="custName" id="" placeholder="请输入客户名称" /></li>
				<li>
					<span>下单日期：</span>
					<input type="text"  name="createStartTime"  class="Wdate half" id="time1" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})" />
					<em class = "gang">-</em>
					<input type="text"  name="createEndTime"   class="Wdate half" id="time2" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})" />
				</li>
				<li>
					<span>支付日期：</span>
					<input type="text" name="payStartTime" class="Wdate half" id="time3" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time4\')}'})" />
					<em class = "gang">-</em>
					<input type="text" name="payEndTime" class="Wdate half" id="time4" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time3\')}'})"/>
				</li>
				<li>
					<span style = "float:left;">订单状态：</span>
					<select name="orderStatus" id="orderStatus" class="dropdown">
						<option value="" class="label">请选择</option>
					    <option value="-2">删除</option>
					    <option value="-1">取消</option>
					    <option value="0">待确认</option>
					    <option value="1">待发货</option>
					    <option value="2">已发货</option>
					    <option value="3">交易完成</option>
					    <option value="4">订单取消</option>
					</select>
				</li>
				<li>
					<span style = "float:left;">支付状态：</span>
					<select name="payStatus"  class="dropdown">
						<option value="" class="label">请选择</option>
					    <option value="0">待支付</option>
					    <option value="1">已支付</option>
					</select>
				</li>
				<li><span>收货人：</span><input type="text" name="consignee" id="" placeholder="请输入收货人" /></li>
				<li>
					<span style = "float:left;">支付方式：</span>
					<select name="payCode"  class="dropdown">
						<option value="" class="label">请选择</option>
					    <option value="ZFB">支付宝</option>
					    <option value="WX">微信支付</option>
					    <option value="YL">银联支付</option>
					    <option value="TRANSFER">银行汇款/转帐</option>
					    <option value="CREDIT">预授信</option>
					    <option value="WALLET">账户余额</option>
					</select>
				</li>
				<li>
					<input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/>
					<input class = "public_btn bg1" type="reset"  name="clear" id="clear" value = "清空条件"/>
					<input class =" public_btn bg1" type="submit" name="export" id="export" value="导出" />
				</li>
			</ul>
			</form>
			<div id="listDiv"></div>
		</section>
	</div>
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/common.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/base.js" ></script>
	<script src="${ctx}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<!-- 本页私有js -->
	<script type="text/javascript">
		window.onload = function(){
			$('#searchForm').submit();
		}
		
		function isChange(type){
			$('#searchForm')[0].reset();
			if(type != 99)
			{
				$('#flag').val(type);
				if(type>=0){
					$('#orderStatus').val(type);
				}
			}else{
				$('#flag').val("");
			}
			$('#searchForm').submit();
		}
		
		//导出
		$("#export").click(
				function() {
					$("#searchForm input[type='text']").each(
							function() {
								$(this).val(
										$.trim($(this).val()).replace(
												new RegExp("'", "gm"), "")
												.replace(new RegExp("%", "gm"),
														""));
							});
					var formSerialize = $("#searchForm").serialize();
					window.open("${ctx}/rest/order/exportOrder?"
							+ formSerialize);
		});
	</script>
	</body>
</html>