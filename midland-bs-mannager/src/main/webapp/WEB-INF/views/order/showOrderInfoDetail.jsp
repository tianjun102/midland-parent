<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../layout/tablib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<c:set var="ctx" value="${pageContext['request'].contextPath}"/>

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
        <title>订单详情</title>
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
		<script type="text/javascript" src="${ctx}/assets/scripts/common.js"></script>
		<script type="text/javascript" src="${ctx}/assets/scripts/layer.js"></script>
		<script type="text/javascript" src="${ctx}/assets/scripts/base.js"></script>
		<script type="text/javascript" src="${ctx}/assets/My97DatePicker/WdatePicker.js"></script>
    </head>
    <!-- END HEAD -->

    <!-- BEGIN BODY -->
<body>
<section class="content" style="border:none;">
	<p class = "detail-title">
					<span>订单明细</span>
	</p>
	<form action="" id="OrderInfoForm" method="post">
	<table class='table table-bordered table-add' style='width:100%'>
		<tr>
			<th colspan="4" style="text-align: left;">订单信息
				<input type="hidden" name="id" id="id" value="${order.id}"/>		
				<input type="hidden" name="orderSn" id="orderSn" value="${order.orderSn}"/>		
				<input type="hidden" name="custId" id="custId" value="${order.custId}"/>		
				<input type="hidden" name="orderStatus" id="orderStatus" value="${order.orderStatus}"/>		
				<input type="hidden" name="payStatus" id="payStatus" value="${order.payStatus}"/>	
				<input type="hidden" name="custType" id="custType" value="${order.custType}"/>	
				<input type="hidden" name="payCodeName" id="payCodeName" value="${order.payCodeName}"/>
				<input type="hidden" name="orderAmount" id="orderAmount" value="${order.orderAmount}"/>	
				<input type="hidden" name="shippingId" id="shippingId" value="${order.shippingId}"/>
				<input type="hidden" name="shippingSn" id="shippingSn" value="${order.shippingSn}"/>	
			</th>
		</tr>
		<tr>
			<th style='width:25%'>订单单号</th>
			<td style='width:25%'>${order.orderSn}</td>
			<th style='width:25%'>订单状态</th>
			<td style='width:25%'>${order.orderStatusName}</td>
		</tr>
		<tr>
			<th>下单时间</th>
			<td>${order.createTime}</td>
			<th>支付状态</th>
			<td>${order.payStatusName}</td>
		</tr>
		<tr>
			<th>支付时间</th>
			<td>${order.payTime}</td>
			<th>支付方式</th>
			<td>${order.payCodeName}</td>
		</tr>
		<tr>
			<th>客户名称</th>
			<td>${order.custName}</td>
			<th>买家留言</th>
			<td>${order.buyerMessage}</td>
		</tr>
		<tr>
			<th colspan="4" ></th>
		</tr>
		<tr>
			<th colspan="4">
			<p style="text-align: left;float: left;">发货信息</p>
			<p style="text-align: right;padding-right: 3%">
			<c:if test="${order.orderStatus==0 || order.orderStatus==1 }">
				<a onclick="editData(${order.id})" id="editA">编辑</a>
			</c:if>
		</p>
		</th>
		</tr>
		<tr>
			<th>收货人</th>
			<td>${order.consignee}</td>
			<th>手机</th>
			<td>${order.mobile}</td>
		</tr>
		<tr>
			<th>收货地址</th>
			<td>${order.provinceName} ${order.cityName} ${order.distName} ${order.address}</td>
			<th>配送仓库</th>
			<td>${order.shipperCode}</td>
		</tr>
		<tr>
			<th>配送方式</th>
			<td>${order.shippingName}</td>
			<th>配送单号</th>
			<td>${order.shippingSn}</td>
		</tr>
		<tr>
			<th colspan="4" style="text-align: left;">订单金额</th>
		</tr>
		<tr>
			<th>订单应付款</th>
			<td>${order.orderAmount} 元</td>
			<th>订单已付款</th>
			<td>${order.moneyPaid} <c:if test="${!empty order.moneyPaid}">元</c:if></td>
		</tr>
		<tr>
			<th>运费</th>
			<td>${order.shippingFee}元</td>
			<th></th>
			<th></th>
		</tr>
		<tr>
			<th colspan="4" style="text-align: left;"></th>
		</tr>
		<tr>
			<th colspan="4" >
					<c:if test="${userInfo.username==order.shipperCode  || '000' == order.shipperCode}" >
						<c:if test="${order.orderStatus==0 && ( order.custType==2 ||  order.payStatus==1) }">
							<a target="contentF" class = "public_btn bg2" id="save" onclick="changeStatus(1)">确定</a> 
						</c:if>
						<c:if test="${order.orderStatus==1}">
							<a style="margin-left: 20px" target="contentF" class = "public_btn bg2" id="save" onclick="changeStatus(2)">反确定</a> 
						</c:if>
						<c:if test="${order.orderStatus==0 && order.custType==1 && order.payStatus==0}">
							<a style="margin-left: 20px" target="contentF" class = "public_btn bg2" id="save" onclick="hintStatus(3)">财审</a> 
						</c:if>
							
						<c:if test="${order.orderStatus==1}">
							<a style="margin-left: 20px" target="contentF" class = "public_btn bg2" id="save" onclick="changeStatus(5)">发货</a> 
						</c:if>
					</c:if>
				
				
					<a style="margin-left: 20px" target="_blank"  class = "public_btn bg2" id="save" href="${ctx}/rest/order/showOrderPrintDetail?id=${order.id}" >打印</a> 
					<a style="margin-left: 20px" href="${ctx}/rest/order/showOrderInfoIndex" target="contentF" class = "public_btn bg3" id="cancel">返回</a> 
			</th>
		</tr>
		
		<tr>
			<th colspan="4" style="text-align: left;">商品信息</th>
		</tr>
		<tr>
			<td colspan="4" >
				<table class='table table-bordered table-add' style='width:100%'>
					<tr>
						<th style="width: 30%">商品名称</th>
						<th style="width: 20%">商品编码</th>
						<th style="width: 15%">单价</th>
						<th style="width: 15%">数量</th>
						<th style="width: 20%">金额</th>
					</tr>
					<c:choose>
					<c:when test="${!empty orderItems}">
						<c:forEach items="${orderItems}" var="item" >
							<tr>
								<td>${item.prodName}
								<c:if test="${item.isGift == 1}">
								<span style="color:red;">[赠品]</span>
								</c:if>
								</td>
								<td>${item.prodCode}</td>
								<td>${item.salePrice}元</td>
								<td>${item.quantity}</td>
								<td>${item.salePrice}元</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5">没有找到数据!</td>
						</tr>
					</c:otherwise>
				</c:choose>
				</table>
			</td>
		</tr>
		<tr>
			<th colspan="4" style="text-align: left;">备注</th>
		</tr>
		<tr>
			<td colspan="4" >
				<table class='table table-bordered table-add' style='width:100%'>
						<tr>
							<th style="width: 20%">操作人</th>
							<th style="width: 30%">备注时间</th>
							<th style="width: 50%">备注内容</th>
						</tr>
						<c:choose>
						<c:when test="${!empty orderRemark}">
							<c:forEach items="${orderRemark}" var="remark" >
								<tr>
									<td>${remark.userName}</td>
									<td>${remark.remarkTime}</td>
									<td>${remark.remarkDesc}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="3">没有找到数据!</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
			</td>
		</tr>
		<tr>
			<th colspan="4" style="text-align: left;"></th>
		</tr>
		<tr>
			<th colspan="4" style="text-align: left;">日志</th>
		</tr>
		<tr>
			<td colspan="4" >
				<table class='table table-bordered table-add' style='width:100%'>
						<tr>
							<th style="width: 20%">操作人</th>
							<th style="width: 30%">操作时间</th>
							<th style="width: 50%">操作内容</th>
						</tr>
						<c:choose>
						<c:when test="${!empty orderLog}">
							<c:forEach items="${orderLog}" var="log" >
								<tr>
									<td>${log.operateUserName}</td>
									<td>${log.operateTime}</td>
									<td>${log.operateInfo}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="3">没有找到数据!</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
			
			</td>
		</tr>
	</table>
	 </form>
</section>	
</body>
    <script type="text/javascript">
    	function editData(orderId){
    		layer.open({
				type: 2,
				skin: 'layer-style',
				area: ['800px','430px'],
				shadeClose: false, //点击遮罩关闭
				title:['修改收货信息'],
				resize: false,
				scrollbar:false,
				content:['${ctx}/rest/order/showReceiptDetail?id='+orderId, 'no']
			});
    	}
    	
    	function changeStatus(type){
    		var orderid = $("input[name='id']").val();
    		var orderStatus = $("input[name='orderStatus']").val(); //订单类型
    		var custType = $("input[name='custType']").val();// 客户类型
    		var payStatus = $("input[name='payStatus']").val();//支付状态
    		
    		
    		var shippingId = $("input[name='shippingId']").val();//配送方式
    		var shippingSn = $("input[name='shippingSn']").val();//物流单号
    		
    		
    		if(type==1){
    			if(payStatus==0 && custType ==2){
    				layer.msg("当前订单未支付!",{icon:7});
    				return false;
    			}
    		}
    		
    		if(type==5){
    			if(shippingId.trim()==""|| shippingId ==null ){
    				layer.msg("配送方式不能为空!",{icon:7});
    				return false;
    			}
    			if(shippingSn.trim()==""|| shippingSn ==null ){
    				layer.msg("配送单号不能为空!",{icon:7});
    				return false;
    			}
    		}
    		
    		$.ajax({ 
    			type: "post", 
    			url:"${ctx}/rest/order/forChangeOrderStatus",
    			async:false, // 此处必须同步
    			dataType: "json",
    			cache:false, 
    			data :{"idStr":orderid,"orderStatusStr":orderStatus,"type":type},
    			success: function(data){
					if(data.flag==1){
						layer.msg("操作成功！",{icon:1});
						setTimeout(function(){window.open("${ctx}/rest/order/showOrderInfoDetail?id="+orderid,"contentF");},2000);  
					}else{
						layer.msg("操作失败！",{icon:2});
					}
				},
				error: function(){
					layer.msg("操作失败！",{icon:2});
				}
    		});
    	}
    	
    	
    	function hintStatus(type){
    		var orderId = $("input[name='id']").val();
    		var orderStatus = $("input[name='orderStatus']").val(); //订单类型
    		var payStatus = $("input[name='payStatus']").val();//支付状态
    		var payCodeName = $("input[name='payCodeName']").val();//支付方式
    		var orderAmount = $("input[name='orderAmount']").val();//支付金额
    		
    		var content = "";
    		var msg =""
    		var title = "";
    		if(type==3){
    			title = "财审"; 
    			msg = "财审";
    			content= '<section class = "content" style = "border:none;">'+
					'<dl>'+
						'<dt style = "text-align: center; margin-top: 20px;"><img src="${ctx}/assets/img/!.png"/></dt>'+
						'<dd>'+
							'<p style = "text-align: center; margin-top: 20px;">订单金额：'+orderAmount+'元</p>'+
							'<p style = "text-align: center; margin-top: 20px;">支付方式：'+
								'<input type="radio" name="test" value="1" checked="checked"/>'+payCodeName+
								'  <input type="radio" name="test" value="2"/>预授信  '+
								'<input type="radio" name="test" value="3"/>账户余额  </p>'+
						'</dd>'+
					'</dl>'+
				'</section>';
    			
    		}
    		
    		layer.open({
  			  type: 1,
  			  skin: 'layer-style',
  			  area: ['460px','300px'],
  			  shadeClose: false, //点击遮罩关闭
  			  title:[title],
  			  resize: false,
  			  scrollbar:false,
  			  content:content,
  			  btn:['确定','取消'],
  			  yes: function(index){
  				var  newPayType = $('input:radio:checked').val();
  				  $.ajax({ 
	  					type: "post", 
	  	    			url:"${ctx}/rest/order/forAudit",
	  	    			async:false, // 此处必须同步
	  	    			dataType: "json",
	  	    			cache:false, 
	  	    			data :{"orderId":orderId,"type":newPayType},
  						success: function(data){
  							if(data.flag==1){
  								layer.msg(msg+"成功！",{icon:1});
  								setTimeout(function(){window.open("${ctx}/rest/order/showOrderInfoDetail?id="+orderId,"contentF");},2000);  
  							}
  							if(data.flag==0){
  								layer.msg(msg+"失败！！",{icon:7});
  							}
  								layer.close(index);
  						},
  						error: function(){
  								layer.msg("操作失败！",{icon:2});
  						} 
  					});
  				 }
	    		,success: function (layero) {
				      var btn = layero.find('.layui-layer-btn');
				      btn.css('text-align', 'center');
				  }
  			  });
    	}
    	
    </script>
</html>