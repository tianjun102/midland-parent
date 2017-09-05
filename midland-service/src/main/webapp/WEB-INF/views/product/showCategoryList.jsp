<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<SCRIPT type="text/javascript">
		
		var setting = {
			check: {
				enable: true,
				chkboxType: { "Y": "sp", "N": "sp" }


			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick
			}
		};
		var catProNodes =[{id:0, pId:0,name:'分类',open:true,nocheck:true,iconSkin:"pIcon01"},${categoryData}];
		
		
		$(document).ready(function(){
			$.fn.zTree.init($("#categoryTree"), setting, catProNodes);
		});
		
		function beforeClick(treeId, treeNode, clickFlag) {
			$('#searchForm')[0].reset();
			$('#catId').val(treeNode.id);
			$('#searchForm').submit();
			$('#catId').val("");
		}

</SCRIPT>
<body>
<div class = "table-responsive m40" style = "background-color: #f7fdff;">
				<div id = "responseBox1" class="content_wrap" style = "float:left; width: 25%; min-height:461px!important; overflow-y:scroll;">
   		  			<div class="zTreeDemoBackground left"  style = "width: 100%;">
						<ul id="categoryTree" class="ztree" style = "width: 98%;margin-top: 0" ></ul>
					</div>
				</div>
				<div id = "responseBox2" style= "width: 75%; float:left; margin-bottom: 0;">
					<table class="table table-bordered table-add">
		 				<thead>
				   			<tr>
				   				<th style="width: 30%">分类名称</th>
				   				<th style="width: 20%">父节点</th>
				   				<th style="width: 35%">描述</th>
				   				<th style="width: 15%">操作</th>
				   			</tr>
				   		</thead>
						<tbody>
							<c:choose>
								<c:when test="${!empty categoryList }">
									<c:forEach var="category" items="${categoryList}">
									<tr>
									    <td>${category.catName}</td>
									    <td>${category.parentName}</td>
									    <td>${category.catDesc}</td>
									    <td>
										    <a  onclick="showModify(${category.catId})"  target="contentF" class = "edit_img" title = "编辑"></a>    
										    <a  onclick="isDelete(${category.catId},${category.chirdCount},${category.productCount})"   class = "delete_img" title = "删除"></a>
									    </td>
									</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="4">没有找到数据!</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					
				</div>
</div>
<!-- 分页 -->
				<c:if test="${!empty paginator}"> 
					  <c:set var="paginator" value="${paginator}"/>
					  <c:set var="target" value="listDiv"/>
					  <%@include file="../layout/pagination.jsp"%>
				</c:if>		
	  
<script type="text/javascript">
	$(document).ready(function(){
		$("#responseBox1").height($("#responseBox2").height());
	})
	
	function isDelete(catId,chirdCount,productCount){
		if(catId==null || catId == 0 ){
			layer.msg("请选择分类！",{icon:7});
			return false;
		}else if (productCount==null || productCount>0){
			layer.msg("存在商品，请先删除商品！",{icon:7});
			return false;
		}else if (chirdCount==null || chirdCount>0){
			layer.msg("存在子分类，请先删除子分类！",{icon:7});
			return false;
		}
		
		layer.open({
			  type: 1,
			  skin: 'layer-style',
			  area: ['350px','200px'],
			  shadeClose: false, //点击遮罩关闭
			  title:['删除分类'],
			  resize: false,
			  scrollbar:false,
			  content:
			 	'<section class = "content" style = "border:none;">'+
				'<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您是否确定删除该分类?</p>'+
				'</section>',
			  btn:['确定','取消'],
			  yes: function(index){
				  $.ajax({ 
						type: "post", 
						url: "${ctx}/rest/product/forRemoveCategory?catId="+catId,
						cache:false, 
						async:false, // 此处必须同步
						dataType: "json",
						success: function(xmlobj){
							if(xmlobj.flag==1){
								layer.msg("删除成功！",{icon:1});
								setTimeout(function(){window.open("${ctx}/rest/product/showCategoryIndex","contentF");},2000);
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
	
	
	
	
	
	
	function showModify(catId){
		layer.open({
			type: 2,
			skin: 'layer-style',
			area: ['470px','450px'],
			shadeClose: false, //点击遮罩关闭
			title: ['分类信息'],
			resize: false,
			scrollbar:false,
			content: ['${ctx}/rest/product/showModifyCategory?catId='+catId,'no']
			});
	}
</script>
</body>
</html>