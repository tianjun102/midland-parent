<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/zTree.jsp"%>

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
        <link rel="stylesheet" href="${ctx}/assets/css/ztree/css/demo.css"type="text/css">
		<link rel="stylesheet" href="${ctx }/assets/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css" />
		<link rel="stylesheet" href="${ctx }/assets/css/common.css">
		<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.css">
        
		<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
		<script src="${ctx}/assets/scripts/common.js"></script>
		<script src="${ctx}/assets/scripts/layer.js" type="text/javascript"></script>
		<script src="${ctx}/assets/scripts/base.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/assets/My97DatePicker/WdatePicker.js"></script>
<style>
.layui-layer{
	top:260px!important;
}
</style>
    </head>
    <SCRIPT type="text/javascript">
		
		
		var setting = {
				check: {
					enable: true,
					chkboxType: { "Y": "sp", "N": "sp" }
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					beforeClick: beforeClick
				}
		};

		var catProNodes =[${categoryData}];
		
		$(document).ready(function(){
			$.fn.zTree.init($("#categoryTree"), setting, catProNodes);
		});
		
		function beforeClick(treeId, treeNode, clickFlag) {
				$("input[name='catId']").val(treeNode.id);
				$("input[name='catName']").val(treeNode.name);
				hideTree();
		}
		
		function showTree(){
			$("#showCatDiv").show();
		}
		
		function hideTree(){
			$("#showCatDiv").hide();
		}
		
		function closeDiv(){
			$("input[name='catId']").val("");
			$("input[name='catName']").val("");
			$("#showCatDiv").hide();
		}
</SCRIPT>

    <body >
<%--         <div>
	       <form action="${ctx}/rest/promotion/SearchProduct?flag=${flag}" method="post">
	       <!-- 购买的商品查询 -->
	       	 <table width="900px;" border="1" cellspacing="0" cellpadding="0">
		       	<col width="300px;">
	       		<col width="300px;">
	       		<col width="300px;">
		       	<tr>
			       	<td>商品编号：<input type="text" name="codeStr"></td>
			       	<td>商品名称：<input type="text" name="nameStr"></td>
			       	<td>商品分类：
			       	<select name="catId">
			       	<option value="">请选择</option>
			       	<c:forEach items="${categoryList}" var="category">
			       	<option value="${category.catId}">${category.catName}</option>
			       	</c:forEach>
			       	</select></td>
		       	</tr>       	
	       	</table>
	       	<input type="submit" value="提交" >
	       </form>
	       
	       
	       	<table width="1000px;" border="1" cellspacing="0" cellpadding="0">
		       	<col width="200px;">
	       		<col width="200px;">
	       		<col width="200px;">
	       		<col width="300px;">
		       	<tr>
			       	<td>商品编号</td>
			       	<td>商品名称</td>
			       	<td>商品分类</td>
			       	<td>商品价格</td>
			       	<td>操作</td>
		       	</tr>
		       	<c:forEach items="${productList}" var="product">       	
		       	<tr>
		       		<td ><input type="hidden" name="productId" value="${product.productId }" ><input name="productCode" value="${product.productCode }"/></td>
			       	<td><input name ="productName" value="${product.productName }"/> </td>
			       	<td><input name ="catName" value="${product.catName }"/></td>
			       	<td><input name ="marketPrice" value="${product.marketPrice }"/></td>
			       	<td><a onclick="setVlue(this);">添加</a></td>
		       	</tr> 
		       	</c:forEach>      	
	       	</table>

        </div> --%>
        
     <div class="box"> 
		<section class = "content">
		 <form id="searchForm" action="${ctx}/rest/promotion/SearchProduct?flag=${flag}" method="post" >
			<ul class = "userinfo row">
				<li><span>商品编号：</span><input maxlength="20" style="width: 150px;" type="text" name="codeStr"></li>
				<li><span>商品名称：</span><input maxlength="50" style="width: 150px;" type="text" name="nameStr"></li>
				<li><span>分类：</span> <input name="catName" type="text"
					onclick="showTree()" readonly="readonly" /> <input name="catId"
					type="hidden" id="catId" /></li>
				<li id="showCatDiv" style="display: none; padding-top: 0px; padding-left: 70px; position: relative;">
					<div class="zTreeDemoBackground left"
						style  = "position:absolute; left: -270px; top: 55px;">
						<ul id="categoryTree" class="ztree" style="width: 240px;"></ul>
					</div> <img src="${ctx}/assets/img/Closed_16px.png" alt="关闭"
					style="vertical-align: top;position:absolute; left: -25px; top: 63px;" onclick="closeDiv()">
				</li>
				<li><input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/></li>
			</ul>
		 </form>
		      <div class = "table-responsive m40">
				<table class="table table-bordered table-add">
				<thead>
			       	<tr>
				       	<th style="width: 20%;font-weight:bold;">商品编号</th>
				       	<th style="width: 30%;font-weight:bold;">商品名称</th>
				       	<th style="width: 20%;font-weight:bold;">商品分类</th>
				       	<th style="width: 15%;font-weight:bold;">商品价格</th>
				       	<th style="width: 15%;font-weight:bold;">操作</th>
			       	</tr>
		       	</thead>
		       	<tbody>
			       	<c:forEach items="${productList}" var="product">       	
			       	<tr>
			       		<td ><input type="hidden" name="productId" value="${product.productId }" ><input type="hidden" name="productCode" value="${product.productCode }"/>${product.productCode }</td>
				       	<td><input type="hidden" name ="productName" value="${product.productName }"/> ${product.productName }</td>
				       	<td><input type="hidden" name ="catName" value="${product.catName }"/>${product.catName }</td>
				       	<td><input type="hidden" name ="marketPrice" value="${product.marketPrice }"/>${product.marketPrice }</td>
				       	<td><a style="display:inline;" onclick="setVlue(this);">添加</a></td>
			       	</tr> 
			       	</c:forEach>  
		       	</tbody>
				</table>
			</div>
		</section>
	</div>
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

    </body>
    <script type="text/javascript">
    function setVlue(obj){
    var productCode = $(obj).parent().parent().find("input[name='productCode']").val();;
    var productName = $(obj).parent().parent().find("input[name='productName']").val();
    var catName = $(obj).parent().parent().find("input[name='catName']").val();
    var marketPrice = $(obj).parent().parent().find("input[name='marketPrice']").val();
    var productId = $(obj).parent().parent().find("input[name='productId']").val();
    var prom_id = parent.$("input[name='id']").val();
    if(typeof(prom_id) == "undefined"){
    	prom_id= "";
    }
    var num = 0;
    var flg = true;
    parent.$("#product${flag}").find("tr").each(function(){
    	if(!!$(this).find("input:hidden[name$='.prod_id']").val() && $(this).find("input[name$='.prod_id']").val()==productId){
    		/* num=parseInt($(this).find("input[name$='prod_number']").val())+1;
    		$(this).find("input[name$='prod_number']").val(num); */
    		layer.msg("商品已经添加，请勿重复添加！")
    		flg = false;
    	}
    	
    });
    	
    if(flg){
    	if("${flag}"==1||"${flag}"==3){
    		if(!!parent.$("#product${flag}").find("tr").size() && parent.$("#product${flag}").find("tr").size()>0){
    			var size=parent.$("#product${flag}").find("tr").size()-1;
    		}
        	parent.$("#product${flag}").find("tr").last().after(
        	    	//"<tr><td><input type='hidden' name='prom_prod_idList["+size+"]' value='"+productId+"' /><input type='hidden' name='productId' value='"+productId+"' /><input name='prod_code' value='"+productCode+"' /></td><td><input name='prod_name' value='"+productName+"'/></td><td><input name='prod_number' value='1'/></td><td><input name='prod_price' value='"+marketPrice+"' /></td><td>操作： </td></tr>");
        			"<tr><td><input type='hidden' name='PromotionPropList["+size+"].prom_id' value='"+prom_id+"' /><input type='hidden' name='PromotionPropList["+size+"].prod_id' value='"+productId+"' /><input type='hidden' name='productId' value='"+productId+"' /><input type='hidden' name='PromotionPropList["+size+"].prod_code' value='"+productCode+"' />"+productCode+"</td><td><input type='hidden' name='PromotionPropList["+size+"].prod_name' value='"+productName+"'/>"+productName+"</td><td><input style='border: 1px solid #dbe2e6;border-radius: 4px;' type='text' name='PromotionPropList["+size+"].prod_number' value='1'/></td><td><a class='delete_img' onclick='delecte(this)'></a> </td></tr>");
        	layer.msg("添加成功！",{icon:1});
    	}else{
    		if(!!parent.$("#product${flag}").find("tr").size() && parent.$("#product${flag}").find("tr").size()>0){
    			var size=parent.$("#product${flag}").find("tr").size()-1;
    		}
    	parent.$("#product${flag}").find("tr").last().after(
    	            "<tr><td><input type='hidden' name='PromotionGiftList["+size+"].prom_id' value='"+prom_id+"' /><input type='hidden' name='PromotionGiftList["+size+"].prod_id' value='"+productId+"' /><input type='hidden' name='productId' value='"+productId+"' /><input type='hidden' name='PromotionGiftList["+size+"].prod_code' value='"+productCode+"' />"+productCode+"</td><td><input type='hidden' name='PromotionGiftList["+size+"].prod_name' value='"+productName+"'/>"+productName+"</td><td><input style='border: 1px solid #dbe2e6;border-radius: 4px;' type='text' name='PromotionGiftList["+size+"].prod_number' value='1'/></td><td style='display:none'><input type='hidden' name='PromotionGiftList["+size+"].prod_price' value='"+marketPrice+"' />"+marketPrice+"</td><td><a class='delete_img' onclick='delecte(this)'></a> </td></tr>");
    		layer.msg("添加成功！",{icon:1});
    	}
    }
    }
    </script>
</html>