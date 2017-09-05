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
        <title>Banner列表</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta name="MobileOptimized" content="320">
    </head>
    <body >
      	<!--活动管理界面-->
	<div class="box"> 
	<form id="searchForm" action="${ctx}/rest/setting/bannerList" method="post" onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
		<section class = "content">
			<p class = "detail-title">
				<span>Banner管理</span>
				<a class = "setup" href="${ctx}/rest/setting/enterBanner" target="contentF">添加Banner</a>
			</p>
			<ul class = "userinfo row">
				<li>
					<span style = "float:left;">城市：</span>
					<select name="cityId" id="cityId" style="height: 38px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
						<option value="">全部</option>
						<c:forEach items="${cityList}" var="city">
							<option value="${city.id}">${city.name}</option>
						</c:forEach>
					</select>
				</li>

				<li>
					<span style = "float:left;">平台：</span>
					<select name="source" id="source" class="dropdown">
						<option value="">全部</option>
						<option value="1">网站</option>
						<option value="2">微站</option>
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
    
    	function deleteBanner(id){
    	layer.open({
			  type: 1,
			  skin: 'layer-style',
			  area: ['350px','200px'],
			  shadeClose: false, //点击遮罩关闭
			  title:['删除Banner'],
			  resize: false,
			  scrollbar:false,
			  content:
				 	'<section class = "content" style = "border:none; height:100%;">'+
						'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您确定要删除当前Banner吗?</p>'+
					'</section>',
			  btn:['确定','取消'],
			  yes: function(index){
				  $.ajax({ 
						type: "post", 
						url: "${ctx}/rest/setting/deleteBanner?id="+id,
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