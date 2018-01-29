<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="table-responsive m40">
		<table class="table table-bordered table-add">
			<thead>
				<tr>
					<th style="width: 30%">区域名称</th>
					<th style="width: 55%">区域描述</th>
					<th style="width: 15%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${!empty requestScope.areas }">
						<c:forEach items="${requestScope.areas }" var="area" varStatus="xh">
							<tr>
								<td>${area.areaName }</td>
								<td>${area.areaDescription }</td>
								<td>
								  	<a target="contentF" class = "edit_img" title = "编辑" onclick="toEditPage(${area.areaId});"></a>
									<a target="contentF" class = "delete_img" title = "删除" onclick="isDelete('${area.areaId}')"></a>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="14">没有找到数据!</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	<!-- 分页 -->
	<c:if test="${!empty paginator}"> 
		  <c:set var="paginator" value="${paginator}"/>
		  <c:set var="target" value="listDiv"/>
		  <%@include file="../layout/pagination.jsp"%>
	</c:if>
<script type="text/javascript">
	//编辑
	function toEditPage(areaId){
		layer.open({
			type: 2,
			skin: 'layer-style',
			area: ['480px','300px'],
			shadeClose: false, //点击遮罩关闭
			title:['编辑'],
			resize: false,
			scrollbar:false,
			content:['${ctx}/rest/cust/findArea?areaId='+areaId, 'no']
		});
	}
	
	//删除
	function isDelete(areaId){

		if(isEmploy(areaId)){
			layer.msg("使用中不能删除！",{icon:7});
			return ;
		}
		
		layer.open({
			  type: 1,
			  skin: 'layer-style',
			  area: ['350px','200px'],
			  shadeClose: false, //点击遮罩关闭
			  title:['删除区域'],
			  resize: false,
			  scrollbar:false,
			  content:
				  '<section class = "content" style = "border:none; height:100%;">'+
					'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您确定要删除吗?</p>'+
				  '</section>',
			  btn:['确定','取消'],
			  yes: function(index){
				  $.ajax({ 
						type: "post", 
						url: "${ctx}/rest/cust/deleteArea?areaId="+areaId,
						cache:false, 
						async:false, // 此处必须同步
						dataType: "json",
						success: function(xmlobj){
							if(xmlobj.flag==1){
								layer.msg("操作成功！",{icon:1});
								setTimeout(function(){$("#searchForm").submit();},1000);
							}
							if(xmlobj.flag==0){
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
	
	//检查是否已使用
	function isEmploy(areaId){
		var bool=false;
		$.ajax({ 
			type: "post", 
			url: "${ctx}/rest/cust/check?areaId="+areaId,
			cache:false, 
			async:false, // 此处必须同步
			dataType: "json",
			success: function(xmlobj){
				if(xmlobj.flag==1){
					bool= true;
				}else{
					bool= false;
				}
			} 
		});
		
		return bool;
	}
</script>
</body>
</html>