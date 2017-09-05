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
        <link rel="stylesheet" href="${ctx}/assets/css/layer.css">
		<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ctx}/assets/css/common.css">
		<link rel="stylesheet" href="${ctx}/assets/css/easydropdown.css" />
    </head>
    <body >
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
	<script src="${ctx}/assets/scripts/common.js"></script>
	<script src="${ctx}/assets/scripts/layer.js" type="text/javascript"></script>
	<script src="${ctx}/assets/scripts/base.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/assets/My97DatePicker/WdatePicker.js"></script>
      	<!--活动管理界面-->
	<div class="box"> 
	<form id="searchForm" action="${ctx}/rest/activity/list" method="post" onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
		<section class = "content">
			<p class = "detail-title">
				<span>活动管理</span>
				<a class = "setup" href="${ctx}/rest/activity/enterActivity" target="contentF">活动发布</a>
			</p>
			<ul class = "userinfo row">
				<li><span>活动标题：</span><input maxlength="50" type="text" name="actiTitle" id="" placeholder="" /></li>
				<li>
					<span style = "float:left;">活动状态：</span>
					<select name="isShow" id="" class="dropdown">
						<!-- <option value="" class="label">请选择</option> -->
					    <option value="1">开放</option>
					    <option value="0">关闭</option>
					</select>
				</li>
				<li><input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/></li>
			</ul>
		    <div id="listDiv"></div> 
		</section>
		</form>
	</div>
      
    </body>
    <script type="text/javascript">
    $(function(){
    	$('#searchForm').submit();
    }) 
    
    function deleteActivity(id){
    	layer.open({
			  type: 1,
			  skin: 'layer-style',
			  area: ['350px','200px'],
			  shadeClose: false, //点击遮罩关闭
			  title:['删除活动信息'],
			  resize: false,
			  scrollbar:false,
			  content:
				 	'<section class = "content" style = "border:none; height:100%;">'+
						'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您确定要删除当前活动吗?</p>'+
					'</section>',
			  btn:['确定','取消'],
			  yes: function(index){
				  $.ajax({ 
						type: "post", 
						url: "${ctx}/rest/activity/deleteActivity?id="+id, 
						cache:false, 
						async:false, // 此处必须同步
						dataType: "json",
						success: function(data){
							if(data.result=='ok'){
								layer.msg("删除成功！",{icon:1});
								setTimeout(function(){$("#searchForm").submit();},1000);
							}else{
								layer.msg("删除失败！！",{icon:7});
							}
							layer.close(index);
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