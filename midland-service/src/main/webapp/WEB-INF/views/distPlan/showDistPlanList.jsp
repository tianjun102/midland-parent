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


<div class = "table-responsive m40">
				<table class="table table-bordered table-add">
	 				<thead>
						<tr>
			   				<th style="width: 37%">分销方案代码</th>
			   				<th style="width: 37%">分销方案名称</th>
			   				<th style="width: 26%">操作</th>
			   			</tr>
					</thead>
					<tbody>
				<c:choose>
					<c:when test="${!empty distPlanList}">
						<c:forEach var="distPlan" items="${distPlanList}">
							<tr>
								<td>${distPlan.distPlanSn}</td>
							    <td>${distPlan.distPlanName}</td>
							    <td><a href="${ctx}/rest/distPlan/showModifyDistPlan?id=${distPlan.id}"  target="contentF" class = "edit_img" title="编辑"></a>    
							    <a href="#" onclick="isDelete('${distPlan.id}')" class = "delete_img"   title = "删除"></a></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="3">没有找到数据!</td>
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
	//删除
	function isDelete(id){
		layer.open({
			  type: 1,
			  skin: 'layer-style',
			  area: ['350px','200px'],
			  shadeClose: false, //点击遮罩关闭
			  title:['删除分销方案'],
			  resize: false,
			  scrollbar:false,
			  content:
			 	'<section class = "content" style = "border:none;">'+
					'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您确定要删除当前分销方案吗</p>'+
				'</section>',
			  btn:['确定','取消'],
			  yes: function(index){
				  $.ajax({ 
						type: "post", 
						url: "${ctx}/rest/distPlan/forRemoveDistPlan?id="+id,
						cache:false, 
						async:false, // 此处必须同步
						dataType: "json",
						success: function(xmlobj){
							if(xmlobj.flag==1){
								layer.msg("删除成功！",{icon:1});
								setTimeout(function(){window.open("${ctx}/rest/distPlan/showDistPlanIndex","contentF");},2000);
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

	
</script>
</body>
</html>