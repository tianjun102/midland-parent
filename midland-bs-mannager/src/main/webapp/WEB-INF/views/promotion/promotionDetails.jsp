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
		<link rel="stylesheet" href="${ctx}/assets/css/common.css">
		<link rel="stylesheet" href="${ctx}/assets/css/easydropdown.css" />
		<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
		<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
		<script src="${ctx}/assets/scripts/common.js"></script>
		<script src="${ctx}/assets/scripts/layer.js" type="text/javascript"></script>
		<script src="${ctx}/assets/scripts/base.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/assets/My97DatePicker/WdatePicker.js"></script>
    </head>
    <body >
        <div class="box">
        <section class = "content">
			<p class = "detail-title"> 
				<span>查看优惠活动页</span>
				<a class = "setup" href="${ctx}/rest/promotion/enterPromotionIndex" target="contentF">优惠活动列表</a>
			</p>
	       	<ul class = "adminfo row">
				<li><span>策略编号：</span><input type="hidden" name="id" value="${promotion.id}" ><input type="text" name="prom_sn"  value="${promotion.prom_sn}"></li>
				<li><span>创建时间：</span><input class="Wdate date" type="text" name="creat_time"  value="${promotion.creat_time}" onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"></li>
				<li><span>策略名称：</span><input type="text" name="prom_name"  value="${promotion.prom_name}"></li>
				<li>
					<span>策略性质：</span>
		       	    <select class="dropdown" name="type" id="type" onchange="updateType()">
			       		<c:if test="${promotion.type == 0}">
				       		<option selected="selected" value="0">买即赠</option>
				       		<option value="1">满即赠</option>
			       		</c:if>
			       		<c:if test="${promotion.type == 1}">
				       		<option value="0">买即赠</option>
				       		<option selected="selected" value="1">满即赠</option>
			       		</c:if>
		       		</select>
				</li>
				<li><span>开始日期：</span><input id="time1" class="Wdate date" type="text" name="prom_start"  value="${promotion.prom_start}" onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'time2\')}'})"></li>
				<li><span>结束日期：</span><input id="time2" class="Wdate date" type="text" name="prom_end"  value="${promotion.prom_end}" onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'time1\')}'})"></li>
			</ul>
			<div class = "table-responsive" style = "overflow-x:visible;">
	       	<c:if test="${promotion.type== 0 }">
	       	<div id="buy">
	       	</br>
	       	</br>
	       	<span>购买产品：</span>
	       	<!-- 购买的商品 -->
	       	<table id = "product1" class="table table-bordered table-add">
	       	<thead>
			    <tr>
			       	<th style="width: 35%">产品货号</th>
			       	<th style="width: 50%">产品名称</th>
			       	<th style="width: 15%">数量</th>
			    </tr>
			 </thead>
			<c:choose>
				<c:when test="${!empty promotionPropList}">
			    <c:forEach items="${promotionPropList}" var="promotionProp">
			    <tr>
			       	<td>${promotionProp.prod_code}</td>
			       	<td>${promotionProp.prod_name}</td>
			       	<td>${promotionProp.prod_number}</td>
			    </tr>
			    </c:forEach>
			    </c:when>
					<c:otherwise>
						<tr>
							<td colspan="3">暂无数据!</td>
						</tr>
					</c:otherwise>
			</c:choose> 
	       	</table>
	       	<span>赠送产品：</span>
	       	<table id = "product2" class="table table-bordered table-add">
	       	<thead>
	       		<tr>
		       		<th style="width: 35%">产品货号</th>
			       	<th style="width: 50%">产品名称</th>
			       	<th style="width: 15%">数量</th>
		       	</tr>
		    </thead>
		    <c:choose>
				<c:when test="${!empty promotionGiftList}">
		       		<c:forEach items="${promotionGiftList}" var="promotionGift">
			       	<tr>
				       	<td>${promotionGift.prod_code}</td>
				       	<td>${promotionGift.prod_name}</td>
				       	<td>${promotionGift.prod_number}</td>
			       	</tr>
			       	</c:forEach>
		       	</c:when>
					<c:otherwise>
						<tr>
							<td colspan="3">暂无数据!</td>
						</tr>
					</c:otherwise>
			</c:choose> 
	       	</table>
	       	</div>
	       	</c:if>
	       	<c:if test="${promotion.type== 1 }">
	       	<div id="full">
	       	<ul class = "adminfo row">
	       		<li><span>订单金额：</span><input type = "text" name="prom_order_amount" value="${promotion.prom_order_amount}"></li>
	       	</ul>
	       	</br>
	       	</br>
	       	<span>购买产品：</span>
	       	<table id = "product3" class="table table-bordered table-add">
	       	<!-- 购买的商品 方案二-->
		       	<thead>
				    <tr>
				       	<th style="width: 35%">产品货号</th>
			       		<th style="width: 50%">产品名称</th>
			       		<th style="width: 15%">数量</th>
				    </tr>
				 </thead>
				 <tbody>
			<c:choose>
				<c:when test="${!empty promotionPropList}">
					   <c:forEach items="${promotionPropList}" var="promotionProp">
					    <tr>
					       	<td>${promotionProp.prod_code}</td>
					       	<td>${promotionProp.prod_name}</td>
					       	<td>${promotionProp.prod_number}</td>
					    </tr>
					   </c:forEach>
				   	</c:when>
					<c:otherwise>
						<tr>
							<td colspan="3">暂无数据!</td>
						</tr>
					</c:otherwise>
			</c:choose>  
			    </tbody>
	       	</table>
	       		<span>赠送产品：</span>
	       	<table id = "product4" class="table table-bordered table-add">
	       		<thead>
		       	<tr>
			       	<th style="width: 35%">产品货号</th>
			       	<th style="width: 50%">产品名称</th>
			       	<th style="width: 15%">数量</th>
		       	</tr>
		       	</thead>
		       <tbody>
		       <c:choose>
					<c:when test="${!empty promotionGiftList}">
				       <c:forEach items="${promotionGiftList}" var="promotionGift">
				       	<tr>
					       	<td>${promotionGift.prod_code}</td>
					       	<td>${promotionGift.prod_name}</td>
					       	<td>${promotionGift.prod_number}</td>
				       	</tr>
				       </c:forEach>
		       		</c:when>
					<c:otherwise>
						<tr>
							<td colspan="3">暂无数据!</td>
						</tr>
					</c:otherwise>
				</c:choose>
		       </tbody>
	       	</table>
	       	</div>
	       	</c:if>
	       </div>
	       	<ul class = "adminfo row">
	       		<li>
					<span></span>
					<a target="contentF" class = "public_btn bg2" href="${ctx}/rest/promotion/enterPromotionIndex">返回</a>
				</li>
	       	</ul>
	       	</section>
        </div>
    </body>
</html>