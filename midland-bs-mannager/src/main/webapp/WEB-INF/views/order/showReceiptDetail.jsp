<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
        <title>修改订单收货地址</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta name="MobileOptimized" content="320">

    </head>
   	<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/assets/css/easydropdown.css" />
	<link rel="stylesheet" href="${ctx}/assets/css/common.css">
	<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.css">
	<style>
		.content ul.userinfo li:not(:last-child) input{
			float:none;
		}
		.content ul.userinfo li>span {
			float:none;
		}
	</style>
<body>
<section class="content" style="border:none;">
	<form action="" method="post" id="receiptFrom">
	<ul class = "userinfo row">
			<li>
				<input type="hidden"  name="id"  id="id" value="${order.id}" />
				<span>收货人：</span>
				<input type="text"  name="consignee"  value="${order.consignee}"  /><label style="color: red"  class = "_star ">*</label>
				<span style = "margin-left:28px;">手机号：</span>
				<input type="text" name="mobile" maxlength="11" value="${order.mobile}"  /><label style="color: red"  class = "_star ">*</label>
				
			</li>
			
			<li>
				<span>地址：</span>
					<p id="province" style = "display:inline-block; height:28px; line-height:28px;">
						<label for="pop-14"></label> 
						<input type="hidden" name="province"  value="${order.province}"/>
						<input type="hidden" name="regionSn">
						<input type="hidden" name="provinceName">
						
						<!-- 第一次进页面加载省 -->
						<select id="provinces" onchange="initProvince()" style = "display:inline-table; height:100%; float:left;">
							<!-- <option>请选择</option> -->
							<c:forEach items="${result}" var="result">
								<option value="${result.regionId},${result.parentId},${result.regionSn}"
									${order.province==result.regionId ? 'selected' : '' }>${result.regionName}</option>
							</c:forEach>
						</select>
					</p> <!-- 市 -->
					<p id="city" style = "display:inline-block; height:28px; line-height:28px; margin-left: 20px;">
						<label for="pop-15"></label> 
						<input type="hidden" name="city"  value="${order.city}">
						<input type="hidden" name="cityName"> 
						<select id="citys" onchange="initCity()" style = "display:inline-table; height:100%; float:left;">
							<option value="">请选择</option>
							<c:if test="${!empty resultcity}">
								<c:forEach items="${resultcity}" var="result">
									<option value="${result.regionId},${result.parentId}"
										<c:if test="${order.city ==result.regionId }">selected="selected"</c:if>>${result.regionName}</option>
								</c:forEach>
							</c:if>
							<c:if test="${empty resultcity}">
								<option value="">请选择</option>
							</c:if>
						</select>
					</p>
					<p id="district" style = "display:inline-block; height:28px; line-height:28px; margin-left: 20px;">
						<label for="pop-16"></label> 
						<input type="hidden" name="district"  value="${order.district}"> 
						<input type="hidden" name="distName"> 
						<select id="districts" onchange="initDistrict()" style = "display:inline-table; height:100%; float:left;">
							<c:if test="${!empty resultdist}">
								<option value="">请选择</option>
								<c:forEach items="${resultdist}" var="result">
									<option value="${result.regionId},${result.parentId}"
										<c:if test="${order.district ==result.regionId }">selected="selected"</c:if>>${result.regionName}</option>
								</c:forEach>
							</c:if>
							<c:if test="${empty resultdist}">
								<option value="">请选择</option>
							</c:if>
						</select>
					</p><label style="color: red"  class = "_star ">*</label>
			</li>
			<li>
				<span>详细地址：</span>
				<input type="text" name="address" value="${order.address}"/><label style="color: red"  class = "_star ">*</label>
				<span style = "margin-left:30px;">配送仓库：</span>
				<input type="text" name="shipperCode" value="${order.shipperCode}" disabled="disabled"/><label style="color: red"  class = "_star ">*</label>
			</li>
			<li>
				<span>配送方式：</span>
				<select name="shippingId" class="dropdown">
					<option value="">请选择</option>
					<c:forEach var="ship"  items="${shipList}">
						<c:if test="${ship.shippingId==order.shippingId}">
							<option value="${ship.shippingId}" selected="selected">${ship.shippingName}</option>
						</c:if>
						<c:if test="${ship.shippingId!=order.shippingId}">
							<option value="${ship.shippingId}">${ship.shippingName}</option>
						</c:if>
					</c:forEach>
				</select>
				<span style = "margin-left:42px;">配送单号：</span>
				<input type="text" name="shippingSn" value="${order.shippingSn}" maxlength="20"/>
			</li>
			<li>
				<span></span>
				<a target="contentF" class = "public_btn bg2" id="save" onclick="saveData()">保存</a>  
			</li>
		</ul>
		</form>
		
</section>	    
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/common.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/base.js" ></script>
	<script src="${ctx}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script type="text/javascript">
    
	function saveData(){
		
		var orderid = $('#id').val();
		if(checkForm()){
			$.ajax({ 
				type: "post", 
				url:"${ctx}/rest/order/forModifyOrderInfo",
				async:false, // 此处必须同步
				dataType: "json",
				cache:false, 
				data :$("#receiptFrom").serialize(),
				success: function(data){
					if(data.flag==1){
						layer.msg("保存成功！",{icon:1});
						setTimeout(function(){window.open("${ctx}/rest/order/showOrderInfoDetail?id="+orderid,"contentF");},2000);  
					}else{
						layer.msg("保存失败！",{icon:2});
					}
				},
                error: function (data) {
                    if (data.responseText != null) {
                        layer.msg(data.responseText, {icon: 2});
                    } else {
                        layer.msg("操作失败！", {icon: 2});
                    }
                }
			});
		}
	}
    
	
    function initProvince() {
		var addrId = $("#provinces option:selected").val();
		var addName = $("#provinces option:selected").text();
		var arr = addrId.split(",");
		var id = arr[0];
		var regionSn=arr[2];
		$("#districts").html("<option  >请选择</option>");
		if ("请选择" == addName) {
			//下级改变成请选择
			$("#citys option:selected").text(addName);
			$("#districts option:selected").text(addName);
			$("#citys").html("<option  >请选择</option>");

			$("input[name=province]").val("");
			$("input[name=provinceName]").val("");
			$("input[name=regionSn]").val("");
			$("input[name='city']").val("");
			$("input[name='cityName']").val("");
			$("input[name='district']").val("");
			$("input[name='distName']").val("");
			return;
		}
		$("input[name=province]").val(id);
		$("input[name=regionSn]").val(regionSn);
		$("input[name=provinceName]").val(addName);
		$.ajax({
			type : "post",
			url : "${ctx}/rest/cust/seleAddress?pid=" + id,
			async : false, // 此处必须同步
			dataType : "json",
			data : "",
			success : function(data) {
				$("#citys").html("<option  >请选择</option>");
				data.result.forEach(function(list) {
					$("#citys").append(
							"<option value="+list.regionId+","+list.parentId+","+list.regionSn+" >"
									+ list.regionName + "</option>");
				})
			}
		});
	}

	//市级赋值
	function initCity() {
		var addrId = $("#citys option:selected").val();
		var addName = $("#citys option:selected").text();
		var arr = addrId.split(",");
		var id = arr[0];
		if ("请选择" == addName) {
			//下级改变成请选择
			$("#districts option:selected").text(addName);
			$("#districts").html("<option  >请选择</option>");
			//其值及其下级值变成空
			$("input[name='city']").val("");
			$("input[name='cityName']").val("");
			$("input[name='district']").val("");
			$("input[name='distName']").val("");
			return;
		}
		$("input[name='city']").val(id);
		$("input[name='cityName']").val(addName);
		$.ajax({
			type : "post",
			url : "${ctx}/rest/cust/seleAddress?pid=" + id,
			async : false, // 此处必须同步
			dataType : "json",
			data : "",
			success : function(data) {
				$("#districts").html("<option value='' >请选择</option>");
				data.result.forEach(function(list) {
					$("#districts").append(
							"<option value="+list.regionId+","+list.parentId+" >"
									+ list.regionName + "</option>");
				})
			}
		});
	}

	//区级赋值
	function initDistrict() {
		var addrId = $("#districts option:selected").val();
		var addName = $("#districts option:selected").text();
		var arr = addrId.split(",");
		var id = arr[0];
		if ("请选择" == addName) {
			//下级改变成请选择
			$("#districts option:selected").text(addName);
			//其值及其下级值变成空
			$("input[name='district']").val("");
			$("input[name='distName']").val("");
			return;
		}
		$("input[name='district']").val(id);
		$("input[name='distName']").val(addName);

	} 	
	
	
	
	function checkForm(){
		var consignee = $("input[name='consignee']").val(); //收货人
		var mobile = $("input[name='mobile']").val();//手机号
		var province = $("input[name='province']").val();//省
		var city = $("input[name='city']").val();//市
		var district = $("input[name='district']").val();//区
		var address = $("input[name='address']").val();//详细地址
		var shippingSn = $("input[name='shippingSn']").val();//配送单号
		
		if(consignee.trim() =="" || consignee==null){
			layer.tips("收货人不能为空！", "input[name='consignee']",{tips:1});
			return false;
		}
		
		if(!getStrLenght(consignee,20)){
			layer.tips("分类名称长度不能超过20个字符！", "input[name='consignee']",{tips:1});
			return false;
		}
		
		if(mobile.trim() =="" || mobile==null){
			layer.tips("手机号不能为空！", "input[name='mobile']",{tips:1});
			return false;
		}
		
		if(!getStrLenght(mobile,11)){
			layer.tips("手机号长度不能超过11个字符！", "input[name='mobile']",{tips:1});
			return false;
		}
		
		if(isNaN(mobile)){
			layer.tips("手机号必须为数字！", "input[name='mobile']",{tips:1});
			return false;
		}
		
		if(province.trim() =="" || province==null){
			layer.tips("请选择省份！", "#provinces",{tips:1});
			return false;
		}
		
		if(city.trim() =="" || city==null){
			layer.tips("请选择市区！", "#citys",{tips:1});
			return false;
		}
		
		if(district.trim() =="" || district==null){
			layer.tips("请选择区域！", "#districts",{tips:1});
			return false;
		}
		
		if(address.trim() =="" || address==null){
			layer.tips("详细地址不能为空！", "input[name='address']",{tips:1});
			return false;
		}
		
		if(!getStrLenght(address,20)){
			layer.tips("详细地址长度不能超过20个字符！", "input[name='address']",{tips:1});
			return false;
		}
		
		
		if(shippingSn.trim()!="" && isNaN(shippingSn)){
			layer.tips("配送单号必须为数字！", "input[name='shippingSn']",{tips:1});
			return false;
		}
		
		if(!getStrLenght(shippingSn,20)){
			layer.tips("配送单号长度不能超过20个字符！", "input[name='shippingSn']",{tips:1});
			return false;
		}
		
		return true;
	}
	
	 //长度校验 
	function getStrLenght(message,MaxLenght) {
        var strlenght = 0; //初始定义长度为0
        var txtval = message.trim();
        for (var i = 0; i < txtval.length; i++) {
               strlenght = strlenght + 1; //一个字符
        }
        return strlenght > MaxLenght ? false : true;
    }
    </script>
</body>    
</html>