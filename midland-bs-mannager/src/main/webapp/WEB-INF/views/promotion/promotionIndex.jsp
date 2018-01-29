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
	<!--促销策略界面-->
	<div class="box"> 
		<section class = "content">
		 <form id="searchForm" action="${ctx}/rest/promotion/promotionlist" method="post" onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<p class = "detail-title">
				<span>促销策略</span>
				<a class = "setup" href="${ctx}/rest/promotion/enterPromotion" target="contentF">添加促销策略</a>
			</p>
			<ul class = "userinfo row">
				<li><span>策略编号：</span><input maxlength="20" type="text" name="prom_sn" id="" placeholder="" /></li>
				<li><span>策略名称：</span><input maxlength="50" type="text" name="prom_name" id="" placeholder="" /></li>
				<li><span>策略时间：</span><input onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')||\'new Date()\'}'})" class = "half date" type="text" name="prom_start" id="time1" placeholder="" /><em class = "gang">-</em><input onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}',maxDate:new Date()})" class = "half date" type="text" name="prom_end" id="time2" placeholder="" /></li>
				<li>
					<span style = "float:left;">策略性质：</span>
					<select name="type" id="" class="dropdown">
						<option value="" class="label">请选择</option>
					    <option value="0">买即赠</option>
					    <option value="1">满即赠</option>
					</select>
				</li>
				<li>
					<span style = "float:left;">策略状态：</span>
					<select name="enabled" id="" class="dropdown">
						<option value="" class="label">请选择</option>
					    <option value="0">禁用</option>
					    <option value="1">启用</option>
					</select>
				</li>
				<li><input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/></li>
			</ul>
		 </form>
		<div id="listDiv"></div>
		</section>
	</div>
    </body>
    <script type="text/javascript">
    $(function(){
    	$('#searchForm').submit();
    }) 
    
    function deleteProm(id){
    	
    	layer.open({
  		  type: 1,
  		  skin: 'layer-style',
  		  area: ['350px','200px'],
  		  shadeClose: false, //点击遮罩关闭
  		  title:['是否删除'],
  		  resize: false,
  		  scrollbar:false,
  		  content:
			 	'<section class = "content" style = "border:none; height:100%;">'+
					'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您确定要删除当前促销策略吗?</p>'+
				'</section>',
  		  btn:['确定','取消'],
  		  yes: function(index){
	  			$.ajax({ 
	  				type: "post", 
	  				url: "${ctx}/rest/promotion/deleteProm?id="+id, 
	  				async:false, // 此处必须同步
	  				dataType: "json",
	  				data:"",
	  				success: function(data){ 
	  					console.log(data.result);
	  					if(data.result=='ok'){
	  			            layer.msg("操作成功",{icon:1});
	  			            setTimeout(function(){$('#searchForm').submit();},1000);
	  					}
	  				} 
	  			});
  		    	layer.close(index);
  			 }
  			,success: function (layero) {
			      var btn = layero.find('.layui-layer-btn');
			      btn.css('text-align', 'center');
			  }
  		  });
	}
    </script>
</html>