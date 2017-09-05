<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>智者汇 - 栏目管理</title>
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
	<body>
	
	<!--栏目管理界面-->
	<div class="box"> 
		<section class = "content">
			<form action="${ctx}/rest/class/list" method="post" id="searchForm" onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<p class = "detail-title">
				<span>栏目管理</span>
				<a class = "setup" href="${ctx}/rest/class/addclass" target="contentF">添加栏目</a>
			</p>
			<ul class = "userinfo row">
				<li><span>栏目名称：</span><input maxlength="50" type="text" name="className" id="" placeholder="" /></li>
				<li>
					<span style = "float:left;">栏目状态：</span>
					<select name="isClose" id="" class="dropdown">
						<!-- <option value="" class="label">请选择</option> -->
					    <option value="0">开放</option>
					    <option value="1">关闭</option>
					</select>
				</li>
				<li><input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/></li>
			</ul>
			</form>
			<div id="listDiv"></div>
		</section>
	</div>
	
	<!-- 本页私有js -->
	<script type="text/javascript">
    $(function(){
    	$('#searchForm').submit();
    }) 
    
    function deleteClass(id){
    	
    	layer.open({
			  type: 1,
			  skin: 'layer-style',
			  area: ['350px','200px'],
			  shadeClose: false, //点击遮罩关闭
			  title:['删除栏目'],
			  resize: false,
			  scrollbar:false,
			  content:
				 	'<section class = "content" style = "border:none; height:100%;">'+
						'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您确定要删除当前栏目吗?</p>'+
					'</section>',
			  btn:['确定','取消'],
			  yes: function(index){
				  $.ajax({ 
						type: "post", 
						url: "${ctx}/rest/class/deleteClass?classId="+id, 
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
	</body>
</html>
