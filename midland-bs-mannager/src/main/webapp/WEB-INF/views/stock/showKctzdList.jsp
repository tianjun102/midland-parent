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
							<th style="width: 25%">调整单号</th>
							<th style="width: 15%">下单日期</th>
							<th style="width: 15%">业务日期</th>
							<th style="width: 10%">数量</th>
							<th style="width: 15%">单据状态</th>
							<th style="width: 20%">操作</th>
						</tr>
					</thead>
					<tbody>
				<c:choose>
					<c:when test="${!empty kctzdList}">
						  <c:forEach var="kctzd" items="${kctzdList}">
							<tr>
							    <td>${kctzd.djbh}</td>
							    <td>${kctzd.addTime}</td>
						     	<td>${kctzd.updateTime}</td>
							    <td>${kctzd.totalNum}</td>
						     	<td>${kctzd.isCompleteName}</td>
							    <td>
							    <a href="${ctx}/rest/stock/showKctzdDetail?id=${kctzd.id}"  target="contentF" class = "see_img" title = "查看"></a>
							    <a  target="contentF" href="${ctx}/rest/stock/showModifyKctzd?id=${kctzd.id}&djbh=${kctzd.djbh}" 
							     <c:if test="${kctzd.isComplete==1}">
									style="visibility: hidden;"
								</c:if>
							    class = "edit_img" title="编辑"></a>    
								<a  target="contentF" class = "delete_img"  
								 <c:if test="${kctzd.isComplete==1}">
									style="visibility: hidden;"
								</c:if>
								onclick="isDelete(${kctzd.id})" title = "删除"></a>
								<a  target="contentF" class = "check_img" 
								 <c:if test="${kctzd.isComplete==1}">
									style="visibility: hidden;"
								</c:if>
								onclick="isChange(${kctzd.id})" title = "验收"></a>
							  
							    </td>
							</tr>
						 </c:forEach>	
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="6">没有找到数据!</td>
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
			  title:['删除验收单'],
			  resize: false,
			  scrollbar:false,
			  content:
			 	'<section class = "content" style = "border:none;">'+
					'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您确定要删除该验收单吗?</p>'+
				'</section>',
			  btn:['确定','取消'],
			  yes: function(index){
				  $.ajax({ 
						type: "post", 
						url: "${ctx}/rest/stock/forRemoveKctzd?id="+id,
						cache:false, 
						async:false, // 此处必须同步
						dataType: "json",
						success: function(xmlobj){
							if(xmlobj.flag==1){
								layer.msg("删除成功！",{icon:1});
								setTimeout(function(){window.open("${ctx}/rest/stock/showKctzdIndex","contentF");},2000);
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

	function isChange(id){
		layer.open({
			  type: 1,
			  skin: 'layer-style',
			  area: ['350px','200px'],
			  shadeClose: false, //点击遮罩关闭
			  title:['验收单'],
			  resize: false,
			  scrollbar:false,
			  content:
			 	'<section class = "content" style = "border:none;">'+
					'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您确定要验收吗?</p>'+
				'</section>',
			  btn:['确定','取消'],
			  btnAlign: 'c',
			  yes: function(index){
				  $.ajax({ 
						type: "post", 
						url: "${ctx}/rest/stock/forChangeKctzd?id="+id,
						cache:false, 
						async:false, // 此处必须同步
						dataType: "json",
						success: function(xmlobj){
							if(xmlobj.flag==1){
								layer.msg("验收成功！",{icon:1});
								setTimeout(function(){window.open("${ctx}/rest/stock/showKctzdIndex","contentF");},2000);
							}
							if(xmlobj.flag==0){
								layer.msg("验收失败，请校验验收单！！",{icon:7});
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