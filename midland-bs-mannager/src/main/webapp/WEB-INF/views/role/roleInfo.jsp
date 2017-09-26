<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctx }/assets/css/common.css">
</head>
<body>
<section class="content" style="border:none;">
	<form action="" method="post" id="editFrom">
	<input type="hidden" name="id" id="id" value="${role.id}">
	<input type="hidden" name="rn" id="rn" value="${role.roleName}">
	<ul class = "userinfo row">
			<li><span>角色代码：</span><input style = "width:264px;" type="text" name="roleSign" id="roleSign" value="${role.roleSign }"  disabled="disabled"/></li>
			<li><span>角色名称：</span><input style = "width:264px;" type="text" name="roleName" id="roleName" value="${role.roleName }" onblur="checkRoleName();" maxlength="32"/><span class="_star">*</span></li>
			<li><span>角色描述：</span><input style = "width:264px;" type="text" name="description" id="description" value="${role.description }" maxlength="256"/></li>
			<%-- <li style = "display:flex;align-items:center">
				<span>用户类型：</span>
				<select name="roleType" id="roleType" class="dropdown">
					<option value=""
						<c:if test="${role.roleType=='-1'}">selected="selected"</c:if>>请选择</option>
					<option value="0"
						<c:if test="${role.roleType=='0'}">selected="selected"</c:if>>沃可视</option>
					<option value="1"
						<c:if test="${role.roleType=='1'}">selected="selected"</c:if>>省代</option>
					<option value="2"
						<c:if test="${role.roleType=='2'}">selected="selected"</c:if>>区服</option>
					<option value="3"
						<c:if test="${role.roleType=='3'}">selected="selected"</c:if>>门代</option>	
			</select>
			</li> --%>
			<li style = "padding-top:30px;">
				<span></span>
				<a target="contentF" class = "public_btn bg2" id="save" onclick="saveData()">保存</a>  
				<a style="margin-left: 20px" class = "public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
			</li>
		</ul>
		</form>
</section>	
<script type="text/javascript">
	
	//保存数据
	function saveData() {
		if (checkRoleName()) {
			var id = $("#id").val();
			//var roleSign = $("#roleSign").val();
			var roleName = $("input[name='roleName']").val();
			var description = $("input[name='description']").val();
			
			$.ajax({ 
				type: "post", 
				url: "${ctx}/rest/role/editRole", 
				async:false, // 此处必须同步
				dataType: "json",
				data:{"id":id,"roleName":roleName,"description":description},
				success: function(data){
					if(data.flag==1){
						layer.msg("保存成功！！！",{icon:1});
						$('#save').removeAttr("onclick");
						setTimeout(function(){parent.location.reload();},1000);
						
					}else{
						layer.msg("保存失败！",{icon:2});
					}
				},
				error: function(){
					layer.msg("保存失败！",{icon:2});
				}
			});
		}
		return false;
	}
	
	//取消
	function closeWin(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
	
	
	//检查角色名称唯一性
	function checkRoleName(){

		var roleName = $("input[name='roleName']").val();
		if(roleName==null|| roleName.trim() =="" ){
			layer.tips("角色名称不能为空!", "input[name='roleName']",{tips:1});
			return false;
		}
		var rn=$("#rn").val();
		if(roleName == rn){//无修改
			return true;
		}

		var boo=true;
		$.ajax({ 
			type: "post", 
			url: "${ctx}/rest/role/checkRoleUnique", 
			async:false, // 此处必须同步
			dataType: "json",
			data:{"roleName":roleName},
			success: function(xmlobj){ 
				if (xmlobj.flag == 0){
					layer.tips("该角色名称已存在!", "input[name='roleName']",{tips:1});
					boo = false;
				}else{
					$("#roleNameCheckMsg").text("");
					boo = true;
				}
			} 
		});
		return boo;
	}
</script>
</body>
</html>