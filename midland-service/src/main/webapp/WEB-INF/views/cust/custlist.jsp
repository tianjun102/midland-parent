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
							<th style="width: 20%">客户代码</th>
							<th style="width: 35%">客户简称</th>
							<th style="width: 10%">客户区域</th>
							<th style="width: 15%">客户类别</th>
							<th style="width: 10%">state</th>
							<th style="width: 10%">操作</th>
						</tr>
					</thead>
					<tbody>
					<c:choose>
						<c:when test="${!empty requestScope.custs }">
							<c:forEach items="${requestScope.custs }" var="cust" varStatus="xh">
								<tr>
									<td>${cust.custCode }</td>
									<td>${cust.custName }</td>
									<td>${cust.areaName }</td>
									<td>
										<c:if test="${cust.custType==1}">渠道服务商 </c:if> 
										<c:if test="${cust.custType==2}">终端服务商</c:if>
									</td>
									<td>
										<c:if test="${cust.status==0}">停用 </c:if> 
										<c:if test="${cust.status==1}">正常 </c:if>
									</td>
									<td>
										<a href="${ctx}/rest/cust/findCust?custId=${cust.custId}" target="contentF" class = "edit_img savePath" title = "编辑"></a>
										<a target="contentF" class = "delete_img" title = "删除" onclick="isDelete(${cust.custId});" 
										<c:if test="${cust.deleteFlag == '0'}">style="visibility: hidden;"</c:if>></a>
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

	//检查客户已使用
	function checkCustIsEmploy(custId){

		var bool=true;
		$.ajax({ 
			type: "post", 
			url: "${ctx }/rest/cust/checkCustIsEmploy",
			async:false, // 此处必须同步
			dataType: "json",
			data:{"custId":custId},
			success: function(xmlobj){ 
				if (xmlobj.flag==1){
					bool=false;
				}else{
					bool=true;
				}
			} 
		});
		return bool;
	}
	
	//删除
	function isDelete(custId){
		
		
		layer.open({
			  type: 1,
			  skin: 'layer-style',
			  area: ['350px','200px'],
			  shadeClose: false, //点击遮罩关闭
			  title:['删除客户'],
			  resize: false,
			  scrollbar:false,
			  content:
				  '<section class = "content" style = "border:none; height:100%;">'+
					'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您确定要删除吗?</p>'+
				  '</section>',
			  btn:['确定','取消'],
			  yes: function(index){
				  
				  if(!checkCustIsEmploy(custId)){
						layer.msg("客户已使用，不能删除！！",{icon:7});
						return false;
					}
				  
				  $.ajax({ 
						type: "post",
						url: "${ctx}/rest/cust/deleteCust?custId="+custId,
						cache:false, 
						async:false, // 此处必须同步
						dataType: "json",
						success: function(xmlobj){
							if(xmlobj.flag==1){
								layer.msg("删除成功！",{icon:1});
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