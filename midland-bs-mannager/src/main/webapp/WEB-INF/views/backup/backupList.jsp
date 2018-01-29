<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据库备份列表页面</title>
</head>
<body>
<div class = "table-responsive m40">
		<table class="table table-bordered table-add">
			<thead>
				<tr>
					<th style="width: 5%">序号</th>
					<th style="width: 60%">备份路径</th>
					<th style="width: 20%">备份时间</th>
					<th style="width: 15%">操作</th>

				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${!empty requestScope.pageList }">
						<c:forEach items="${requestScope.pageList }" var="item"
							varStatus="state">
							<tr>
								<td>${state.count }</td>
								<td>${item.path }</td>
								<td>${item.createTime }</td>
								<td><a onclick="isDelete(${item.id})" target="contentF" class = "delete_img" title = "删除"></a></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="15">没有找到数据!</td>
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
	function isDelete(backId){
		layer.open({
			  type: 1,
			  skin: 'layer-style',
			  area: ['350px','200px'],
			  shadeClose: false, //点击遮罩关闭
			  title:['删除'],
			  resize: false,
			  scrollbar:false,
			  content:
				  '<section class = "content" style = "border:none; height:100%;">'+
					'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您确定要删除吗?</p>'+
				  '</section>',
			  btn:['确定','取消'],
			  yes: function(index){
				  $.ajax({ 
						type: "get", 
						url: "${ctx}/rest/dataBackup/delete?id="+backId,
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
	
</script>
</body>
</html>