<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../layout/tablib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


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
        <title>库存调整单</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta name="MobileOptimized" content="320">
	   	<link rel="stylesheet" href="${ctx }/assets/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css" />
		<link rel="stylesheet" href="${ctx }/assets/css/common.css">
    </head>
    <!-- END HEAD -->

    <!-- BEGIN BODY -->
    <body>
    <div class = "box">
 <section class="content" style="border:none;">  		 
   		 <p class = "detail-title">
					<span>库存调整单</span>
		</p>
   		 <c:if test="${kctzd.pageFlag==0}">
	   		  <form action="${ctx}/rest/stock/forInputKctzd" method="post" id="forInputKctzdFrom" >
	   		  	<ul class = "adminfo row">
	   		 			<li><span>调整单号：</span><input name="showDjbh" type="text"  value="${kctzd.djbh}" readonly="readonly"/>
	   		 					<input name="djbh"type="hidden"  value="${kctzd.djbh}" />	
	   		 				<span>state：</span><input name="type"  type="text" readonly="readonly"  value="新增">
	   		 			</li>
	   		  			<li><span>总数量：</span>
	   		  				<input name="showTotalNum" type="text" readonly="readonly"  value="0" >
		   		  			<input name="totalNum"  type="hidden">
		   		  		</li>
	   		  			<li><span style = "float:left;">描述：</span><textarea name="kctzdNote" style="width: 45%;height: 150px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;"></textarea></li>
	   		  			<li><span>商品信息：</span><input onclick="showProduct()" value = "搜索" class = "public_btn bg1" type="button"/></br></li>
		   		  		<li>
					   		<div  style="width: 100%;vertical-align: top;">
					   			 <table border="1" style="width: 100%" id="showTable" class="table table-bordered table-add" >
					   			 	<tr>
					   			 		<th style="width: 30%">商品编号</th>
					   			 		<th style="width: 30%">商品名称</th>
					   			 		<th style="width: 20%">数量</th>
					   			 		<th style="width: 20%">操作</th>
					   			 	</tr>
					   			 </table>
					   		 </div>
		   				</li>
		   				<li>
	   		  				<span></span>
	   		  				<a target="contentF" class = "public_btn bg2" id="save" onclick="formSubmit(1)">保存</a>  
							<a style="margin-left: 20px" href="${ctx}/rest/stock/showKctzdIndex" target="contentF" class = "public_btn bg3" id="cancel">取消</a>
		   		  		</li>
		   			</ul>
	   		  </form>
   		  </c:if>
   		  
   		  <c:if test="${kctzd.pageFlag==1}">
	   		  <form action="${ctx}/rest/stock/forModifyKctzd"  method="post" id="forModifyKctzdFrom">
  		  		<ul class = "adminfo row">
   		 			<li>
   		 				<span>调整单号：</span>
   		 				<input name="id"  type="hidden" value="${kctzd.id}"/>
   		 				<input name="showDjbh" type="text" value="${kctzd.djbh}"  readonly="readonly"/>
   		 				<input name="djbh"type="hidden"  value="${kctzd.djbh}" />	
   		 				<span>state：</span><input name="type" readonly="readonly" type="text"
   		  				  <c:if test="${kctzd.isComplete==1}">
   		  						value="已验收"
   		  				  </c:if>
   		  				  <c:if test="${kctzd.isComplete==0}">
   		  				     	value="未验收"
   		  				  </c:if>
   		  			>
   		 				</li>
   		  			<li>
   		  				<span>下单时间：</span><input name="type"readonly="readonly" type="text" value="${kctzd.addTime}">
   		  				<span>总数量：</span>
   		  				<input name="showTotalNum" readonly="readonly"  type="text"  value="${kctzd.totalNum}">
	   		  			<input name="totalNum"  type="hidden" type="text"  value="${kctzd.totalNum}">	
   		  			</li>
   		  			<li><span style = "float:left;">描述：</span><textarea name="kctzdNote" style="width: 45%;height: 150px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;">${kctzd.kctzdNote}</textarea></li>
   		  			<li><span>商品信息：</span><input onclick="showProduct()" value = "搜索" class = "public_btn bg1" type="button"/></br></li>
		   		  	<li>
				   		  <div  style="width: 100%;text-align: center;vertical-align: top;">
				   			 <table border="1" style="width: 100%" id="showTable" class="table table-bordered table-add">
				   			 	<tr>
				   			 		<th style="width: 30%">商品编号</th>
				   			 		<th style="width: 30%">商品名称</th>
				   			 		<th style="width: 20%">数量</th>
				   			 		<th style="width: 20%">操作</th>
				   			 	</tr>
				   			 	<c:forEach var="item" varStatus="status" items="${itemList}">
				   			 		<tr>
					   			 		<td>${item.productCode}</td>
					   			 		<td>${item.productName}</td>
					   			 		<td>
					   			 		<input type="hidden" name="itemList[${status.index}].prodId" value="${item.prodId}" />
					   			 		<input type="hidden" name="itemList[${status.index}].id" value="${item.id}" />
					   			 		<input type="text" name="itemList[${status.index}].num" maxlength="8"  onblur="sum()" value="${item.num}" /></td>
					   			 		<td><a class='delete_img' onclick='delItem(this)' title="删除" ></a></td>
				   			 		</tr>
				   			 	</c:forEach>
				   			 </table>
				   		 </div>
				   	</li>
				   	<li>
   		  				<span></span>
   		  				<a target="contentF" class = "public_btn bg2" id="save" onclick="formSubmit(2)">保存</a>  
						<a style="margin-left: 20px" href="${ctx}/rest/stock/showKctzdIndex" target="contentF" class = "public_btn bg3" id="cancel">取消</a>
	   		  		</li>
				   	</ul>
	   		  </form>
   		  </c:if>
   		  
   		  
   		  
   		   <c:if test="${kctzd.pageFlag==2}">
	   		  <form action="${ctx}/rest/stock/forModifyKctzd"  >
   		  		<ul class = "adminfo row">
   		 			<li>
   		 				<span>调整单号：</span><span><label>${kctzd.djbh}</label></span>
   		 				<span style = "margin-left:30px;">状 &nbsp; &nbsp;态：</span>
   		 				<span style = "text-align:left;">
   		 					<label>
		   		  			  <c:if test="${kctzd.isComplete==1}">
		   		  						已验收
		   		  				  </c:if>
		   		  				  <c:if test="${kctzd.isComplete==0}">
		   		  				     	未验收
		   		  				  </c:if>
	   		  				</label>	
   		 				</span>
   		 			</li>
   		  			<li>
   		  				<span>下单时间：</span>
   		  				<span style = "text-align:left; width:auto;">
   		  					<label>${kctzd.addTime}</label><span style = "margin-left:41px;">总数量：</span><label>${kctzd.totalNum}</label>
   		  				</span>
   		  			</li>
   		  			<c:if test="${kctzd.isComplete==1}">
   		  				<li><span style = "margin-left:15px;">验收时间：</span><label>${kctzd.updateTime}</label></li>
		   		  	</c:if>
   		  			<li><span style = "float:left;">描 &nbsp; &nbsp;&nbsp;&nbsp;述：</span><textarea name="kctzdNote" readonly="readonly" style="width: 45%;height: 150px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;">${kctzd.kctzdNote}</textarea></li>
		   		  	<li><span>商品信息：</span></li>
		   		  	<li style="padding-top: 5px">
				   		  <div  style="width: 100%;text-align: center;vertical-align: top;">
				   			 <table border="1" style="width: 100%" id="showTable" class="table table-bordered table-add">
				   			 	<tr>
				   			 		<th style="width: 40%">商品名称</th>
				   			 		<th style="width: 40%">商品编号</th>
				   			 		<th style="width: 20%">数量</th>
				   			 	</tr>
				   			 	<c:forEach var="item" varStatus="status" items="${itemList}">
				   			 		<tr>
					   			 		<td>${item.productName}</td>
					   			 		<td>${item.productCode}</td>
					   			 		<td>${item.num}</td>
				   			 		</tr>
				   			 	</c:forEach>
				   			 </table>
				   		 	</div>
			   		 </li>
			   		 <li>
   		  					<span></span>
							<a style="margin-left: 20px" href="${ctx}/rest/stock/showKctzdIndex" target="contentF" class = "public_btn bg3" id="cancel">取消</a>
	   		  		</li>
			   		</ul>
	   		  </form>
   		  </c:if>
</section>
</div>	
</body>
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/common.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/base.js" ></script>
    <script type="text/javascript">
    	
  	function submintAccden(){
  		
  		var catName = $("input[name='catName']").val();
  		if(catName!=""){
  			$.ajax({ 
      			url:"${ctx}/rest/product/searchCatNameIsExist",
      			type : 'post', 		//数据发送方式 
      			dataType : 'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json) 
      			data :{"catName":catName},
      			success : function(result) {
      				if("1"==result.result){
      					$("#nameDiv").show();	
      					$("input[name='catName']").focus();
      				}
      			}
      		});
  		}
  	}
    	
    function delItem(obj){
    	var id = "";
    	$(obj).parent().parent().find("input:hidden").each(function (j){
    			if(j==1){
    				id = $(this).val();
    			}
    			
    		
    	})
    	
    	if(id ==null  ||  id =="" ){
    		$(obj).parent().parent().remove();
    		$("#showTable tr:not(:first)").each(function(j) {
        		$(this).find("td").find("input:hidden(first)").attr("name","itemList["+(j)+"].prodId");
        		$(this).find("td").find("input:hidden(last)").attr("name","itemList["+(j)+"].id");
        		$(this).find("td").find("input:text").attr("name","itemList["+(j)+"].num");
        	})
        	sum();
    	}else{
    		layer.open({
				type : 1,
				skin : 'layer-style',
				area: ['350px','200px'],
				shadeClose : false, //点击遮罩关闭
				title : [ '删除商品' ],
				resize : false,
				scrollbar : false,
				content : '<section class = "content" style = "border:none;">'
						+ '<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您是否确定删除该商品?</p>'
						+ '</section>',
				btn : [ '确定', '取消' ],
				yes : function(index) {
						$.ajax({
							type : "post",
							url : "${ctx}/rest/stock/forRemoveKctzdItem?itemId="+ id,
							cache : false,
							async : false, // 此处必须同步
							dataType : "json",
							success : function(xmlobj) {
								if (xmlobj.flag == 1) {
									layer.msg("删除成功！", {icon : 1});
									$(obj).parent().parent().remove();
									$("#showTable tr:not(:first)").each(function(j) {
							    		$(this).find("td").find("input:hidden(first)").attr("name","itemList["+(j)+"].prodId");
							    		$(this).find("td").find("input:hidden(last)").attr("name","itemList["+(j)+"].id");
							    		$(this).find("td").find("input:text").attr("name","itemList["+(j)+"].num");
							    	})
							    	sum();
								}
								if (xmlobj.flag == 0) {
									layer.msg("删除失败！！", {icon : 7});
								}
								layer.close(index);
							}
						});
				}
				  ,success: function (layero) {
				      var btn = layero.find('.layui-layer-btn');
				      btn.css('text-align', 'center');
				  }
			})
    	}
    	
    }
    
    
    function sum(){
    	var trArr = $("#showTable tr:not(:first)");
    	var snum = 0;
    	for(var i = 0;i<trArr.length;i++){
    		var  num = parseInt($("input[name='itemList["+(i)+"].num']").val());
    		snum  =  snum +num;   
    	}

    	$("input[name='showTotalNum']").val(snum);  
    	$("input[name='totalNum']").val(snum);  
    }
    
    function formSubmit(obj){
    	var url = "${ctx}/rest/stock/forInputKctzd";
    	var date = "#forInputKctzdFrom";
    	var msg = "新增";
    	if(obj==2){
    		 url = "${ctx}/rest/stock/forModifyKctzd";
        	 date = "#forModifyKctzdFrom";
        	 msg = "修改";
    		
    	}
    	
    	if(checkForm()){
	    	$.ajax({ 
				url:url,
				type : 'post', 		//数据发送方式 
				dataType : 'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json) 
				data :$(date).serialize(),
				async:false,
				success : function(result) {
					if(result.flag==1){
						layer.msg(msg+"成功！！！",{icon:1});
						$('#save').removeAttr("onclick");
						setTimeout(function(){window.open("${ctx}/rest/stock/showKctzdIndex","contentF");},2000);
					}else{
						layer.msg(msg+"失败！",{icon:2});
					}
				},
				error: function(){
					layer.msg(msg+"失败！",{icon:2});
				}
			});
    	}
    }
    
    
    
    function showProduct(){
    	var codeStr = $("#propCode").val();
    	layer.open({
    		type: 2,
    		skin: 'layer-style',
    		title: ['商品搜索'],
    		shadeClose: false,
    		shade: 0.5,
    		area: ['1050px', '500px'],
    		content: "${ctx}/rest/stock/searchProduct?codeStr="+codeStr
   		});
    }
    
    function checkForm(){
    	var  result = true;
    	var msg="";
    	var ex = /^\d+$/;
    	$("#showTable input:text").each(function() {
    		var num = $(this).val();
   			if(num.trim() =="" || num==null ){
	   				msg ="商品数量不能为空！";
	   				result = false;
	   				return ;
   			}else{
   				
   				if(!ex.test(Math.abs(num)) || num == 0){
   					msg="商品数量必须输入非零整数！";
   					result = false;
	   				return ;
   				}
   				
   			}
    	})
    	if(msg!=""){
    		layer.msg(msg,{tips:7});
    	}
    	return result;
    }
    </script>
</html>