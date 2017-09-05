<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctx }/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css" />
<link rel="stylesheet" href="${ctx }/assets/css/common.css">
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/base.js" ></script>
</head>
<body>
<div id="listDiv">
	<section class="content" style="border:none;">
	<div  class="table-responsive">
	<input type="hidden" name="roleId" id="roleId" value="${role.id}">
		<table class="table table-bordered table-add">
			<thead>
				<tr>
					<th><input type="checkbox" id="selectAll" name="selectAll" /></th>
					<th>序号</th>
					<th>用户名</th>
					<th>用户名称</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users }" var="user" varStatus="xh">
				<tr>
					<td><input type="checkbox" name="userId"  value="${user.id }" 
						<c:forEach items="${roleUsers }" var="roleUser">
							 <c:if test="${roleUser.id ==user.id }">checked="true"</c:if>
						</c:forEach>/>
					</td>
					<td>${xh.count }</td>
					<td><label>${user.username }</label></td>
					<td><label>${user.userCnName }</label></td>
				</tr>
				</c:forEach>				
			</tbody>
		</table>
	</div>
	<a target="contentF" class = "public_btn bg2" id="save" onclick="saveData()">保存</a>  
	<a style="margin-left: 20px" class = "public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
	</section>
</div>	
	<!-- 分页 -->
	<c:if test="${!empty paginator}"> 
		  <c:set var="paginator" value="${paginator}"/>
		  <c:set var="target" value="listDiv"/>
		  <%@include file="../layout/pagination.jsp"%>
	</c:if>		
<script type="text/javascript">

	$(document).ready(function(){
		//checkbox的整体选中与取消选中
		$("#selectAll").on("click",function() {

			if($("input[name = 'selectAll']").is(":checked")){
				$("table td input").each(function(){
					$(this)[0].checked = true;
				})
			}
			if(!($("input[name = 'selectAll']").is(":checked"))){
				$("table td input").each(function(){
					$(this)[0].checked = false;
				})
			}
		})		
	})
	
	//保存
	function saveData(){
		var roleId=$("#roleId").val();
		var array = new Array();
		$("input[name='userId']").each(function(){
			if($(this).is(':checked')){
				array.push($(this).val());
			}
		})
		var userIds = array.join();
		$.ajax({ 
			type: "post", 
			url: "${ctx}/rest/role/saveRoleUser", 
			cache:false, 
			async:false, // 此处必须同步
			dataType: "json",
			data: {"roleId":roleId,"userIds":userIds},
			success: function(data){ 
				if(data.flag==1){
					layer.msg("保存成功！！！",{icon:1});
					$('#save').removeAttr("onclick");
					setTimeout(function(){parent.location.reload();},1000);
					
				}else{
					layer.msg("保存失败！",{icon:2});
				}
			} 
		});
	}
	
	//取消
	function closeWin() {
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
</script>
</body>
</html>