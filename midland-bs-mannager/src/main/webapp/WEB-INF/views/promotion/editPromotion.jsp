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
		<style type="text/css">
			.content ul.adminfo li>span {
				width: 100px;
			}
		</style>
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
				<span>修改促销策略</span>
			</p>
		<form id="formId" action="${ctx}/rest/promotion/savePromotionEdit" method="post">
			<ul class = "adminfo row">
				<li><span>策略编号：</span><input type="hidden" name="id" value="${promotion.id}" ><input disabled="disabled" type="text"  value="${promotion.prom_sn}"><input type="hidden" name="prom_sn"  value="${promotion.prom_sn}"></li>
				<li><span>创建时间：</span><input class="Wdate date" type="text" name="creat_time"  value="${promotion.creat_time}" onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"></li>
				<li><span>策略名称：</span><input maxlength="50" type="text" name="prom_name"  value="${promotion.prom_name}"><span class = "_star ">*</span></li>
				<li>
					<span>策略性质：</span>
		       	    <select class="dropdown" name="type" id="type" onchange="updateType()">
			       		<c:if test="${promotion.type == 0}">
				       		<option selected="selected" value="0">买即赠</option>
				       		<!-- <option value="1">满即赠</option> -->
			       		</c:if>
			       		<c:if test="${promotion.type == 1}">
				       		<!-- <option value="0">买即赠</option> -->
				       		<option selected="selected" value="1">满即赠</option>
			       		</c:if>
		       		</select>
				</li>
				<li><span>开始日期：</span><input id="time1" class="Wdate date" type="text" name="prom_start"  value="${promotion.prom_start}" onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'time2\')}'})"><span class = "_star ">*</span></li>
				<li><span>结束日期：</span><input id="time2" class="Wdate date" type="text" name="prom_end"  value="${promotion.prom_end}" onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'time1\')}'})"><span class = "_star ">*</span></li>
			</ul>
		<div class = "table-responsive m40">
	       <c:if test="${promotion.type== 0 }">
	       	<div id="buy">
	       	<span>活动产品：</span><input style="cursor: pointer;" onclick="a(1);" value = "搜索" type="button" class = "public_btn bg1"/></br></br>
	       	<!-- 购买的商品 -->
	       	<table id = "product1" class="table table-bordered table-add">
	       	<thead>
			    <tr>
			       	<th width="30%">产品货号</th>
			       	<th width="30%">产品名称</th>
			       	<th width="15%">数量</th>
			       	<!-- <th>价格：</th> -->
			       	<th width="15%">操作</th>
			    </tr>
			</thead>
			<tbody>
			    <c:forEach items="${promotionPropList}" var="promotionProp" varStatus="sta">
			    <tr>
			       	<td><input type="hidden" name="PromotionPropList[${sta.count-1}].prom_id" value="${promotionProp.prom_id}"  ><input type="hidden" name="PromotionPropList[${sta.count-1}].id" value="${promotionProp.id}"  ><input type="hidden" name="PromotionPropList[${sta.count-1}].prod_code" value="${promotionProp.prod_code}"><input type="hidden" name="PromotionPropList[${sta.count-1}].prod_id" value="${promotionProp.prod_id}">${promotionProp.prod_code}</td>
			       	<td><input type="hidden" name="PromotionPropList[${sta.count-1}].prod_name" value="${promotionProp.prod_name}">${promotionProp.prod_name}</td>
			       	<td><input style="border: 1px solid #dbe2e6;border-radius: 4px;" name="PromotionPropList[${sta.count-1}].prod_number" value="${promotionProp.prod_number}"></td>
			       	<!-- <td>价格：</td> -->
			       	<td><a class='delete_img'  onclick='delecte(this)'></a> </td>
			    </tr>
			    </c:forEach>
			</tbody>
	       	</table>
	       	<span>赠送产品：</span><input style="cursor: pointer;" onclick="a(2);" value = "搜索" type="button" class = "public_btn bg1"/></br></br>
	       	<table id = "product2" class="table table-bordered table-add">
	       	<thead>
		       	<tr>
			       	<th width="30%">产品货号</th>
			       	<th width="30%">产品名称</th>
			       	<th width="15%">数量</th>
			       	<th style='display:none'>价格</th>
			       	<th width="15%">操作 </th>
		       	</tr>
		    </thead>
		    <tbody>
		       	<c:forEach items="${promotionGiftList}" var="promotionGift" varStatus="sta">
		       	<tr>
			       	<td><input type="hidden" name="PromotionGiftList[${sta.count-1}].prom_id" value="${promotionGift.prom_id}"  ><input type="hidden" name="PromotionGiftList[${sta.count-1}].id" value="${promotionGift.id}"><input type="hidden" name="PromotionGiftList[${sta.count-1}].prod_code" value="${promotionGift.prod_code}"><input type="hidden" name="PromotionGiftList[${sta.count-1}].prod_id" value="${PromotionGift.prod_id}">${promotionGift.prod_code}</td>
			       	<td><input type="hidden" name="PromotionGiftList[${sta.count-1}].prod_name" value="${promotionGift.prod_name}">${promotionGift.prod_name}</td>
			       	<td><input style=" border: 1px solid #dbe2e6;border-radius: 4px;" name="PromotionGiftList[${sta.count-1}].prod_number" value="${promotionGift.prod_number}"></td>
			       	<td style="display: none"><input type="hidden" name="PromotionGiftList[${sta.count-1}].prod_price" value="${promotionGift.prod_price}">${promotionGift.prod_price}</td>
			       	<td><a class='delete_img'  onclick='delecte(this)'></a> </td>
		       	</tr>
		       	</c:forEach>
		    </tbody>
	       	</table>
	       	</div>
	       	</c:if>
	       	<c:if test="${promotion.type== 1 }">
	       	<div id="full">
	       	<%-- <p>订单金额满：</p><input name="prom_order_amount" value="${promotion.prom_order_amount}"> --%>
	       	<ul class = "adminfo row">
	       	<li><span>订单金额满：</span><input type="text" name="prom_order_amount" value="${promotion.prom_order_amount}"><span class = "_star ">*</span></li>
	       	</ul>
	       	</br></br>
	       	<span>活动产品：</span><input style="cursor: pointer;" onclick="a(3);" value = "搜索" type="button"  class = "public_btn bg1"/></br></br>
	       	<table id = "product3" class="table table-bordered table-add">
	       	<!-- 购买的商品 方案二-->
	       	<thead>
			    <tr>
			       	<th width="30%">产品货号</th>
			       	<th width="30%">产品名称</th>
			       	<th width="15%">数量</th>
			       	<!-- <th>价格：</th> -->
			       	<th width="15%">操作</th>
			    </tr>
			 </thead>
			 <tbody>
			   <c:forEach items="${promotionPropList}" var="promotionProp" varStatus="sta">
			    <tr>
			       	<td><input type="hidden" name="PromotionPropList[${sta.count-1}].prom_id" value="${promotionProp.prom_id}"  ><input type="hidden" name="PromotionPropList[${sta.count-1}].id" value="${promotionProp.id}"  ><input type="hidden" name="PromotionPropList[${sta.count-1}].prod_code" value="${promotionProp.prod_code}"><input type="hidden" name="PromotionPropList[${sta.count-1}].prod_id" value="${promotionProp.prod_id}">${promotionProp.prod_code}</td>
			       	<td><input type="hidden" name="PromotionPropList[${sta.count-1}].prod_name" value="${promotionProp.prod_name}">${promotionProp.prod_name}</td>
			       	<td><input style="border: 1px solid #dbe2e6;border-radius: 4px;" name="PromotionPropList[${sta.count-1}].prod_number" value="${promotionProp.prod_number}"></td>
			       	<!-- <td>价格：</td> -->
			       	<td><a class='delete_img'  onclick='delecte(this)'></a> </td>
			    </tr>
			   </c:forEach>
			 </tbody>  
	       	</table>
	       	<span>赠送产品：</span><input style="cursor: pointer;" onclick="a(4);" value = "搜索" type="button" class = "public_btn bg1"/></br></br>
	       	<table id = "product4" class="table table-bordered table-add">
	       		<thead>
			       	<tr>
				       	<th width="30%">产品货号</th>
				       	<th width="30%">产品名称</th>
				       	<th width="15%">数量</th>
				       	<th style='display:none'>价格</th>
				       	<th width="15%">操作</th>
			       	</tr>
		       	</thead>
		       	<tbody>
			       	<c:forEach items="${promotionGiftList}" var="promotionGift" varStatus="sta">
			       	<tr>
				       	<td><input type="hidden" name="PromotionGiftList[${sta.count-1}].prom_id" value="${promotionGift.prom_id}"  ><input type="hidden" name="PromotionGiftList[${sta.count-1}].prod_id" value="${promotionGift.prod_id }"><input type="hidden" name="PromotionGiftList[${sta.count-1}].id" value="${promotionGift.id}"><input type="hidden" name="PromotionGiftList[${sta.count-1}].prod_code" value="${promotionGift.prod_code}">${promotionGift.prod_code}</td>
				       	<td><input type="hidden" name="PromotionGiftList[${sta.count-1}].prod_name" value="${promotionGift.prod_name}">${promotionGift.prod_name}</td>
				       	<td><input style="border: 1px solid #dbe2e6;border-radius: 4px;" name="PromotionGiftList[${sta.count-1}].prod_number" value="${promotionGift.prod_number}"></td>
				       	<td style="display: none"><input type="hidden" name="PromotionGiftList[${sta.count-1}].prod_price" value="${promotionGift.prod_price}">${promotionGift.prod_price}</td>
				       	<td><a class='delete_img' onclick='delecte(this)'></a> </td>
			       	</tr>
			       </c:forEach>
			       </tbody>
	       	</table>
	       	</div>
	       	</c:if>

	    </div>
	       	<ul class = "adminfo row">
	       		<li>
					<span></span>
					<a onclick="subumintPromotion();" target="contentF" class = "public_btn bg2">保存</a>
					<a style="margin-left: 20px" href="${ctx}/rest/promotion/enterPromotionIndex" target="contentF" class="public_btn bg3" id="cancel">取消</a>
				</li>
	       	</ul>
		</form>
		</section>
	</div>
    <script type="text/javascript">
/* 	$(function(){
		   $("#full").hide();
		   $("#buy").show();
 	}) */
    function a(flag){
	layer.open({
		type: 2,
		title: ['产品搜索'],
		shadeClose: false,
		shadeClose: false,
		shade: 0.5,
		area: ['1000px', '500px'],
		content: "${ctx}/rest/promotion/enterSearchProduct?flag="+flag
	
		});
    }
    
   function updateType(){
	   if( $("#type option:selected").val()=="0"){
		   $("#full").hide();
		   $("#buy").show();
	   }
	   if( $("#type option:selected").val()=="1"){
		   $("#buy").hide();
		   $("#full").show();
		   
	   }
	   
   }
   
   function delecte(ths){
		layer.open({
			  type: 1,
			  skin: 'layer-style',
			  area: ['440px','250px'],
			  shadeClose: false, //点击遮罩关闭
			  title:['删除用户'],
			  resize: false,
			  scrollbar:false,
			  content:
			 	'<section class = "content" style = "border:none;">'+
					'<dl>'+
						'<dt style = "text-align: center; margin-top: 20px;"><img src="${ctx}/assets/img/!.png"/></dt>'+
						'<dd>'+
							'<p style = "text-align: center; margin-top: 20px;">您确定要删除该产品吗</p>'+
						'</dd>'+
					'</dl>'+
				'</section>',
			  btn:['确定','取消'],
			  yes: function(index){
				    $(ths).parent().parent().remove();
				    layer.close(index);
					layer.msg("操作成功！",{icon:1});
				 }
				,success: function (layero) {
				      var btn = layero.find('.layui-layer-btn');
				      btn.css('text-align', 'center');
				  }
			  });
   }
   
   function subumintPromotion(){
	   		var form = document.getElementById('formId');
			var data = new FormData(form);
			var required = true;
			if($("#full").attr("style")=="display: block;"){
				if($("input[name='prom_order_amount']").val()==""){
					required = false;
					layer.msg("请完成必填项");
				}
				if($("#product4").find("td").length==0){
					required = false;
					layer.msg("请选择赠送商品！");
				}
			}
			if($("#full").attr("style")=="display: none;"){
				if($("#product1").find("td").length==0){
					required = false;
					layer.msg("请选择促销商品！");
				}
				if($("#product2").find("td").length==0){
					required = false;
					layer.msg("请选择赠送商品！");
				}
			}
			if($("input[name='prom_name']").val()==""||$("input[name='prom_start']").val()==""||$("input[name='prom_end']").val()==""){
				required = false;
				layer.msg("请完成必填项");
			}
			if(required){
			$.ajax({
				url : "${ctx}/rest/promotion/savePromotionEdit",
				type : "post",
				cache : false,
				contentType : false,
				processData : false,
				dataType: "json",
				data : data,
				async : false,
				success : function(result) {
				if(result.result=="ok"){
					layer.msg("保存成功！",{icon:1});
					setTimeout(function(){window.open("${ctx}/rest/promotion/enterPromotionIndex","contentF");},2000); 
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
    
    
    </script>
    </body>
</html>