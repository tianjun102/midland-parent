<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<c:set var="ctx" value="${pageContext['request'].contextPath}" />
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
<title>订单打印</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="MobileOptimized" content="320">
</head>
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/scripts/jquery.easydropdown.js"></script>
<link rel="shortcut icon" href="${ctx}/assets/app/img/favicon.ico" />
<script type="text/javascript"
	src="${ctx}/assets/scripts/layer/layer.js"></script>
<link rel="shortcut icon" href="${ctx}/assets/app/img/favicon.ico" />
<link rel="stylesheet" href="${ctx }/assets/css/common.css">
<script type="text/javascript"
	src="${ctx}/assets/scripts/layer/layer.js"></script>
<body>
	<section class="content" style="border: none; width: 100%" id="showDiv">
		<form action="" id="OrderInfoForm" method="post">
			<h1 style="text-align: center;padding-bottom: 30px">订单发货单</h1>
			<table class='table table-bordered table-add' style='width: 100%;'	border="1px" cellspacing="0" >
				<tr>
					<th style='width: 20%'>订单单号</th>
					<td style='width: 30%;text-align: left;'>${order.orderSn}</td>
					<th style='width: 20%'>下单时间</th>
					<td style='width: 30%;text-align: left;'>${order.createTime}</td>
				</tr>
				<tr>
					<th>收货人</th>
					<td  style="text-align: left;">${order.consignee}</td>
					<th>手机</th>
					<td style="text-align: left;">${order.mobile}</td>
				</tr>
				<tr>
					<th>收货地址</th>
					<td colspan="3" style="text-align: left;">${order.provinceName}${order.cityName}${order.distName}
						${order.address}</td>
					
				</tr>
				<tr>
					<th colspan="4" style="text-align: left;"></th>
				</tr>
				<tr>
					<th colspan="4" style="text-align: left;">商品信息</th>
				</tr>
				<tr>
					<td colspan="4">
						<table class='table table-bordered table-add' style='width: 100%' border="1px" cellspacing="0">
							<tr>
								<th style="width: 30%">商品名称</th>
								<th style="width: 20%">商品编码</th>
								<th style="width: 15%">单价</th>
								<th style="width: 15%">数量</th>
								<th style="width: 20%">金额</th>
							</tr>
							<c:choose>
								<c:when test="${!empty orderItems}">
									<c:forEach items="${orderItems}" var="item">
										<tr>
											<td>${item.prodName}<c:if test="${item.isGift == 1}">
													<span style="color: red;">[赠品]</span>
												</c:if>
											</td>
											<td>${item.prodCode}</td>
											<td>${item.salePrice}元</td>
											<td>${item.quantity}</td>
											<td>${item.quantity*item.salePrice}元</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="5">没有找到数据!</td>
									</tr>
								</c:otherwise>
							</c:choose>
							<tr>
								<th>买家留言</th>
								<td style="text-align: left;">${order.buyerMessage}</td>
								<th>合计</th>
								<td>${count}</td>
								<td>${moneyCount}元</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</section>

	<div id="printbtn"
		style="width: 100%; text-align: center; margin: 10px auto;">
		<input id="btnPrint" value="打印"
			style="width: 50px; font-size: 16px;" type="button">
	</div>




	<script type="text/javascript">
		var hkey_root, hkey_path, hkey_key
		hkey_root = "HKEY_CURRENT_USER"
		hkey_path = "\\Software\\Microsoft\\Internet Explorer\\PageSetup\\"

		// 设置页眉页脚为空  
		function PageSetup_Null() {
			try {
				var RegWsh = new ActiveXObject("WScript.Shell");
				hkey_key = "header";
				RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");
				hkey_key = "footer";
				RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");
			} catch (e) {
			}
		}

		// 设置页眉页脚为默认值  
		function PageSetup_Default() {
			try {
				var RegWsh = new ActiveXObject("WScript.Shell");
				hkey_key = "header";
				RegWsh.RegWrite(hkey_root + hkey_path + hkey_key,"&w&b页码，&p/&P");
				hkey_key = "footer";
				RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "&u&b&d");
			} catch (e) {
			}
		}

		function doPrint() {
			PageSetup_Null();
			var html = $("#showDiv").html();
			var bodyHtml = document.body.innerHTML;
			document.body.innerHTML = html;
			window.print();
			//  document.body.innerHTML = bodyHtml;
			window.location.reload();
		}

		$(function() {
			//打印  
			$("#btnPrint").bind("click", function(event) {
				//document.body.innerHTML = "";
				doPrint();
			});
		});
	</script>
</body>
</html>
