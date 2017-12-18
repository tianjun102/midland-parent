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
<section class = "content" style = "border:none;">
<form action="${ctx}/rest/user/edit" method="post" id="userInfoForm">
	<ul class = "userinfo row">
		<input type="hidden" name="id" id="id" value="${user.id}">
		<input type="hidden" name="ph" id="ph" value="${user.phone}">
		<li><span>用户名：</span><input type="text" name="username" id="username" value="${user.username}" disabled="disabled"/></li>
		<li><span>用户名称：</span><input type="text" name="userCnName" id="userCnName" value="${user.userCnName}" disabled="disabled"/></li>
		<li><span>真实姓名：</span><input type="text"  value="${user.actualName}" disabled="disabled"/></li>
		<li><span>用户类型：</span><input type="text" name="userType" id="userType"
		<c:if test="${user.userType==0}">value="管理员"</c:if>
		<c:if test="${user.userType==1}">value="经纪人"</c:if>
		<c:if test="${user.userType==2}">value="前端用户"</c:if>
		disabled="disabled"/></li>
		<%-- <li>
				<span>用户类型：</span>
				<select style= "display:inline-block!important;" name="userType" id="userType" class="dropdown" disabled="disabled">
					<option value=""
						<c:if test="${user.userType==-1}">selected="selected"</c:if>>请选择</option>
					<option value="0"
						<c:if test="${user.userType==0}">selected="selected"</c:if>>智者汇</option>
					<option value="1"
						<c:if test="${user.userType==1}">selected="selected"</c:if>>渠道服务商</option>
					<option value="2"
						<c:if test="${user.userType==2}">selected="selected"</c:if>>终端服务商</option>
					<option value="3"
						<c:if test="${user.userType==3}">selected="selected"</c:if>>安装专员</option>
				</select>
			</li> --%>
			<li><span>手机号码：</span><input type="text" name="phone" id="phone" value="${user.phone}" disabled="disabled" onblur="checkPhone();"/></li>
			<li><span>邮箱：</span><input type="text" name="email" id="email" value="${user.email}" onblur="checkEmail();" disabled="disabled"/></li>
			<%-- <li style = "display:flex;align-items:center">
				<span>角色类型：</span>
				<c:if test="${!empty roles }">
					<c:forEach items="${roles }" var="role">
						<input style="width:20px;height: 20px" type="checkbox" name="userRoles" value="${role.id }"
							<c:forEach items="${userRoles }" var="userRole">
							 	<c:if test="${userRole.id ==role.id }">checked="true"</c:if>
							 </c:forEach> />
						<label>${role.roleName }</label>
					</c:forEach>
				</c:if>
			</li> --%>
			<li>
				<span></span>
				<%--<a target="contentF" class = "public_btn bg2" id="save" onclick="saveData()">保存</a> --%>
				<%--<a style="margin-left: 20px" class = "public_btn bg3" id="cancel" onclick="closeWin();">取消</a>--%>
			</li>
		</ul>
			
	</form>	
</section>

<script type="text/javascript">
	//保存数据
	function saveData(){
		if(checkPhone()&&checkEmail()){
			var id = $("#id").val();
			var username = $("#username").val();
			var userCnName = $("input[name='userCnName']").val();
			// var userType = $("input[name='userType']").val();
			// var userType = $("#userType option:selected").val();
			var phone = $("input[name='phone']").val();
			var email = $("input[name='email']").val();
			var userRoles =""; 
			$('input[name="userRoles"]:checked').each(function(){ 
				userRoles+=$(this).val()+","; 
			}); 
			
			$.ajax({ 
					type: "post", 
					url: "${ctx}/rest/user/edit", 
					async:false, // 此处必须同步
					dataType: "json",
					data:{"id":id,"username":username,"userCnName":userCnName,
						"phone":phone,"email":email,"userRoles":userRoles},
					success: function(data){
						if(data.state==0){
							layer.msg("保存成功！！！",{icon:1});
							$('#save').removeAttr("onclick");
							setTimeout(function(){parent.location.reload();},1000);
							
						}else{
							layer.msg("保存失败！",{icon:2});
						}
					},
                error: function (data) {
                    if (data.responseText != null) {
                        layer.msg(data.responseText, {icon: 2});
                    } else {
                        layer.msg("操作失败！", {icon: 2});
                    }
                }
				});
		}
	}
	//检查手机号格式
	function checkPhone() {

		var phone0 = $("#ph").val();
		var reg = /^1[3,4,5,7,8]\d{9}$/;
		var phone = $("input[name='phone']").val();
		if (phone.trim() == '') {
			layer.tips("手机号不能为空！", "input[name='phone']",{tips:1});
			return false;
		}
		if (!reg.test(phone)) {
			layer.tips("手机号格式有误,请核对!", "input[name='phone']",{tips:3});
			return false;
		}
		if(phone0==phone){
			return true;
		}
		
		var a=true;
		$.ajax({ 
			type: "post", 
			url: "${ctx }/rest/user/checkPhoneUnique",
			async:false, // 此处必须同步
			dataType: "json",
			data:{"phone":phone},
			success: function(xmlobj){ 
				if (xmlobj.flag==1){
					layer.tips("当前手机号码已被使用，请更换手机号码！", "input[name='phone']",{tips:1});
					a=false;
				}else{
					a=true;
				}
			} 
		});
		return a;
	}
	
	//检查邮箱格式
	function checkEmail() {
		var reg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
		var email = $("input[name='email']").val();
		if (email.trim() == '') {
			//layer.tips("邮箱不能为空！", "input[name='email']",{tips:3});
			return true;
		}
		if (!reg.test(email)) {
			layer.tips("邮箱格式有误,请核对!", "input[name='email']",{tips:3});
			//$("input[name='email']").focus();
			return false;
		}
		return true;
	}
	
	//取消
	function closeWin(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
</script>	
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>