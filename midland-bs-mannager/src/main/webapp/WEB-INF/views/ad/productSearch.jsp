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
		<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ctx}/assets/css/ztree/css/demo.css" type="text/css">
		<link rel="stylesheet" href="${ctx}/assets/css/common.css">
		<link rel="stylesheet" href="${ctx}/assets/css/easydropdown.css" />
		<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
		<script src="${ctx}/assets/scripts/common.js"></script>
		<script src="${ctx}/assets/scripts/layer.js" type="text/javascript"></script>
		<script src="${ctx}/assets/scripts/base.js" type="text/javascript"></script>
<style>
.layui-layer{
	top:260px!important;
}
</style>
    </head>
    <body >
     <div class="box"> 
		<section class = "content">
		 <form id="searchForm" action="${ctx}/rest/ad/SearchProduct" method="post" >
			<ul class = "userinfo row">
				<li>
					<span>商品类型：</span>
					<%-- <select id="selectCategory" name="catId" class="dropdown" onchange="updateCat();">
				       	<option value="">请选择</option>
				       	<c:forEach items="${categoryList}" var="category">
				       	<option value="${category.catId}">${category.catName}</option>
				       	</c:forEach>
			       	</select> --%>
			       	<input maxlength="50" name="catName" onchange="updateCat();" onclick="showTree()" />
					<input value="${catId}" name="catId" type="hidden"/>
					<span class = "_star ">*</span>
			       	</li>
			       	<li  id="showDiv" style="display: none;padding-top: 0px;padding-left: 70px; position:relative;">
						<div class="zTreeDemoBackground left" style  = "position:absolute; left: -271px; top: 52px;">
							<ul id="categoryTree" class="ztree" style  = "width:240px;"></ul>
						</div>
						<img src="${ctx}/assets/img/Closed_16px.png" alt="关闭"
					style="vertical-align: top;position:absolute; left: -25px; top: 63px;" onclick="closeDiv()">
					</li>
				<li><span>商品编号：</span><input maxlength="20" style="width: 150px;" type="text" name="codeStr"></li>
				<li><span>商品名称：</span><input maxlength="50" style="width: 150px;" type="text" name="nameStr"></li>
				<li><input style="cursor:pointer; " onclick="submitSearchProduct();" class = "public_btn bg1" name="inquery" type="button" id="inquery" value = "查询"/><input type="checkbox" id="selectall" onchange="selectAll();" ><input type="hidden" name="isAll" value="${isAll}">选择该类下所有商品</li>
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
			       		<td><input type="hidden" name="cat_id" value="${product.catId}" ><input type="hidden" name="productId" value="${product.productId }" ><input type="hidden" name="productCode" value="${product.productCode }"/>${product.productCode }</td>
				       	<td><input type="hidden" name ="productName" value="${product.productName }"/>${product.productName }</td>
				       	<td><input type="hidden" name ="catName" value="${product.catName }"/>${product.catName }</td>
				       	<td><input type="hidden" name ="marketPrice" value="${product.marketPrice }"/>${product.marketPrice }</td>
				       	<td><a onclick="setVlue(this);">添加</a></td>
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
    var productCode = $(obj).parent().parent().find("input[name='productCode']").val();//商品编号
    var productName = $(obj).parent().parent().find("input[name='productName']").val();//商品名称
    var catName = $(obj).parent().parent().find("input[name='catName']").val();//分类名称
    var marketPrice = $(obj).parent().parent().find("input[name='marketPrice']").val();//价格
    var productId = $(obj).parent().parent().find("input[name='productId']").val();//商品id
    var catId = $(obj).parent().parent().find("input[name='cat_id']").val();
    var num = 0;
    var flg = true;
    var size = 0;
     parent.$("#productList").find("tr").each(function(){
    	if(!!$(this).find("input:hidden[name='productId']").val() && $(this).find("input[name='productId']").val()==productId){
    		flg =  false;
    		layer.msg("商品已添加,请勿重复添加！");
    	}
    }); 
    	if(flg){
    		if(!!parent.$("#productList").find("tr").size() && parent.$("#productList").find("tr").size()>0){
    			size=parent.$("#productList").find("tr").size();
    		}
        	parent.$("#productList").append(
        			"<tr><td><input type='hidden' name='adProdList["+size+"].catId' value='"+catId+"' /><input type='hidden' name='adProdList["+size+"].prodId' value='"+productId+"' /><input type='hidden' name='productId' value='"+productId+"' /><input type='hidden' name='adProdList["+size+"].prod_code' value='"+productCode+"' />"+productCode+"</td><td><input type='hidden' name='adProdList["+size+"].prod_name' value='"+productName+"'/>"+productName+"</td><td><input type='hidden' name='adProdList["+size+"].catName' value='"+catName+"'/>"+catName+"</td><td><input type='hidden' name='' value='"+marketPrice+"' />"+marketPrice+"</td><td><a class='delete_img' onclick='delecte(this)'></a> </td></tr>");
        	var array = new Array();
        	parent.$("input:hidden[name$='].prodId']").each(function(){
        		array.push($(this).val());
        	});
        	parent.$("#adId").val("/ad/adinfo?productId="+array+"&catId="+catId);
    		parent.$("#cat_Id").val(catId);
    		layer.msg("添加成功！",{icon:1});
    	}
    }
    
    function updateCat(){
    	var catId = $("input[name='catId']").val();
    	var catName = $("input[name='catName']").val();
    	parent.$("#cat_Id").val(catId);
    	//查询该类的父节点
		$.ajax({ 
			type: "post", 
			url: "${ctx}/rest/ad/selectCatParnet?catId="+catId, 
			async:false, // 此处必须同步
			dataType: "json",
			data:"",
			success: function(data){
				 parent.$("#catName").html("");
				 data.result.forEach(function(list){
						
					parent.$("#catName").append("<input type='text' disabled='disabled' value="+list.catName+">");
						
					}); 
					parent.$("#catName").append("<input type='text' disabled='disabled' value="+catName+">");
					parent.$("#catName").append("<a onclick='deleteText()' >删除</a>");
			} 
		});
    	
    	
    }
    
    function selectAll(){
    	if(parent.$("#cat_Id").val()!=""){
    	if($("#selectall").prop("checked")==true){
    		parent.$("input[name='isAll']").val("1");
    		$("input[name='isAll']").val("1");
    		parent.$("#productList").find("tr").remove();
    		parent.$("#prodInfo").hide();
    		parent.$("#adId").val("/ad/adinfo?catId="+parent.$("#cat_Id").val());
    		$("tbody").find("a").removeAttr("onclick");
    	}else{
    		parent.$("#prodInfo").show();
    		parent.$("input[name='isAll']").val("0");
    		$("input[name='isAll']").val("0");
    		parent.$("#adId").val("");
    		$("tbody").find("a").attr("onclick","setVlue(this)");
    	}
    	
    }else{
    	layer.alert("请选择类型！",{icon:0});
    	$("#selectall").prop("checked",false)
    }
    }
    
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
			$("#showDiv").hide();
			updateCat();
		}
		
		function showTree(){
			$("#showDiv").show();
		}
		
		function hideTree(){
			$("#showDiv").hide();
		}
		
		function closeDiv(){
			$("input[name='catId']").val("");
			$("input[name='catName']").val("");
			$("#showDiv").hide();
		}
		
		$(function(){
			if("${categoryList.catId}"!=""){
				$("input[name='catId']").val("${categoryList.catId}");
				$("input[name='catName']").val("${categoryList.catName}");
				$("input[name='catName']").attr("disabled","disabled");
			}
			
			if("${catId}"!=""){
				$("input[name='catId']").val("${catId}");
				$("input[name='catName']").val("${categoryList.catName}");
				$("input[name='catName']").attr("disabled","disabled");
			}
			
			if("${isAll}"=="1" ){
				$("#selectall").attr("checked","checked");
				$("tbody").find("a").removeAttr("onclick");
			}
			
		})
		
		function submitSearchProduct(){
			if($("input[name='catId']").val()==""){
				layer.msg("请先选择产品类型!");
				return false;
			}else{
				$('#searchForm').submit();
			}
			
		}
		
    </script>
</html>