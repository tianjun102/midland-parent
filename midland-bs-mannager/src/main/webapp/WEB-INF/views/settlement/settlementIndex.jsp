<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>


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
<title>新增结算单</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="MobileOptimized" content="320">
</head>
<body>
	
		<!--财务结算单界面-->
	<div class="box">
		<section class = "content">
	<form id="searchForm" action="${ctx}/rest/settlement/settlementList" method="post" onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<p class = "detail-title">
				<span>财务结算单</span>
				<a class = "setup" href="${ctx}/rest/settlement/saveSettlementIndex" target="contentF">添加财务结算单</a>
			</p>
			<ul class = "userinfo row">
				<li><span>结算单号：</span><input name="sett_sn" value=""></li>
				<li><span>下单时间：</span><input class="Wdate half" id="time1" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})"  type="text" value="" name="betweenTimeStart"><em class = "gang">-</em><input class="Wdate half" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})" id="time2" type="text" name="betweenTimeEnd" value=""></li>
				<c:if test="${userType==0}">
					<li>
						<span>客户名称：</span><input id="CustName" name="cust_name" value="">
					</li>
				</c:if>
				<li>
					<span style = "float:left;">单据状态：</span>
					<select name="status" id="" class="dropdown">
						<option value="" class="label">请选择</option>
						<option value="0">未确认</option>
						<option value="1">已确认</option>
						<option value="2">已记账</option>
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

function deleteSettlement(id){
	
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
					'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您确定要删除当前结算单吗?</p>'+
				'</section>',
		  btn:['确定','取消'],
		  yes: function(index){
				$.ajax({ 
					type: "post", 
					url: "${ctx}/rest/settlement/deleteSettlement?id="+id, 
					async:false, // 此处必须同步
					dataType: "json",
					data:"",
					success: function(data){ 
						if(data.result=='ok'){
				            layer.msg("删除成功",{icon:1});
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