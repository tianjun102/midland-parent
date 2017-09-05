<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<div class = "table-responsive" style = "margin-top:15px;">
				<table class="table table-bordered table-add">
	 				<thead>
						<tr>
							<th style="width: 10%"><a href="#" onclick="checkall()" >全选</a> / <a href="#" onclick="delcheckall()" >全部取消</a></th>
							<th style="width: 14%">产品名称</th>
							<th style="width: 8%">产品编码</th>
							<th style="width: 10%">分类</th>
							<th style="width: 7%">销售价格</th>
							<th style="width: 7%">销售状态</th>
							<th style="width: 10%">创建时间</th>
							<th style="width: 10%">上架时间</th>
							<th style="width: 10%">下架时间</th>
							<th style="width: 14%">操作</th>
						</tr>
					</thead>
					<tbody>
				<c:choose>
					<c:when test="${!empty productList}">
						<c:forEach items="${productList}" var="product" varStatus="status">
							<tr>
								<td><input type="checkbox" name="pid" value="${product.productId}">${(status.index)+1}
								<span style="color: red;
								 <c:if test="${product.isRecommend==0}">
									visibility: hidden;
									    </c:if>
								">[推荐]</span>
								</td>
								<td>${product.productName}</td>
							    <td>${product.productCode}</td>
							    <td>${product.catName}</td>
							    <td><fmt:formatNumber type="number" value="${product.salePrice+0}" pattern="0.00" maxFractionDigits="2"/></td>
							    <td>
							    	<input type="hidden" value="${(status.index)+1}"/>
							    	<input type="hidden" value="${product.prodStatus}"/>
							    	<input type="hidden" value="${product.isRecommend}"/>
							    	${product.prodStatusName}
							    </td>
							    <td>${product.addTime}</td>
							    <td>${product.planSaleTime}</td>
							    <td>${product.shelvesTime}</td>
								<td>
							   		<a href="${ctx}/rest/product/showCopyProduct?productId=${product.productId}" target="contentF" class = "copy_img" title="复制"></a>
							    	<a href="${ctx}/rest/product/showModifyProduct?productId=${product.productId}" target="contentF" class = "edit_img" title="编辑"></a>   
							        <a href="#" onclick="isDelete(${product.productId},${product.prodStatus})"  class = "delete_img" title="删除"></a>
							   
							    </td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="10">没有找到数据!</td>
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
	function isDelete(productId,status){
		
		if(status!=2){
			layer.msg("商品不是下架状态,请先下架该商品!",{icon:7});
			return false;
		}
		
		
		layer.open({
			  type: 1,
			  skin: 'layer-style',
			  area: ['350px','200px'],
			  shadeClose: false, //点击遮罩关闭
			  title:['删除用户'],
			  resize: false,
			  scrollbar:false,
			  content:
			 	'<section class = "content" style = "border:none;">'+
					'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您是否确定删除该产品?</p>'+
				'</section>',
			  btn:['确定','取消'],
			  yes: function(index){
				  $.ajax({ 
						type: "post", 
						url: "${ctx}/rest/product/forRemoveProduct?productId="+productId,
						cache:false, 
						async:false, // 此处必须同步
						dataType: "json",
						success: function(xmlobj){
							if(xmlobj.flag==1){
								layer.msg("删除成功！",{icon:1});
								setTimeout(function(){window.open("${ctx}/rest/product/showProductIndex","contentF");},2000);
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
		})	
	}
	
	

	
	
		function checkall(){
			$("input[name='pid']").each(function(){
				this.checked=true;
			}); 
		}
		
		
		
		function delcheckall(){
			$("input[name='pid']").each(function(){
				this.checked=false;
			}); 
		}
		
	
</script>
</body>
</html>