<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="${ctx }/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx }/assets/css/common.css">
<style type="text/css">
.msg {
	color: red;
	padding-left: 13px;
}
</style>
<script src="${ctx}/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</head>
<body>

<!--密码修改界面-->
	<div class="box">
		<section class = "content">
			<p class = "detail-title">
				<span>密码修改</span>
				<%-- <a class = "setup" href="${ctx }/rest/user/contentIndex" target="contentF">返回</a> --%>
			</p>
		<form action="${ctx}/rest/user/submit" method="post" id="searchFrom">	
			<ul class = "adminfo row">
				<li><span>原密码：</span><input style="width: 300px;" type="password" name="oldPwd" id="oldPwd" onblur="checkOldPwd();"/>
				<label class="_star" id="checkOldPwdMsg">*</label></li>
				<li><span>新密码：</span><input style="width: 300px;" type="password" name="newPwd" id="newPwd" onblur="checkNewPwd();"/>
				<label class="_star" id="checkNewPwdMsg">*</label></li>
				<li><span>确认密码：</span><input style="width: 300px;" type="password" name="confirmPwd" id="confirmPwd" onblur="checkConfirmPwd();"/>
				<label class="_star" id="checkConfirmPwdMsg">*</label></li>
				<li>
					<span></span>
					<a target="_top" class = "public_btn bg2" id="confirm" onclick="saveData();">确认</a>
					<a href="${ctx}/rest/user/toModifyPwdPage" target="contentF" class = "public_btn bg3" id="cancel">取消</a>
				</li>
			</ul>
		</form>			
		</section>
	</div>
	
	<script type="text/javascript">
		function saveData() {

			if (checkOldPwd() && checkNewPwd() && checkConfirmPwd()) {
				
				var newPwd=$("#newPwd").val();
				$.ajax({
					type : "post",
					url : "${ctx}/rest/user/updatePwd",
					async : false, // 此处必须同步
					dataType : "json",
					data : {"newPwd" : newPwd},
					success : function(data) {
						if (data.flag == 1) {
							layer.msg("修改成功,请重新登陆！",{icon:1});
							setTimeout(function(){window.open("${ctx}/","_top")},2000);
						} else {
							layer.msg("修改失败！",{icon:7});
						}
					}
				});
				
			}
		}

		function checkOldPwd() {

			$("#checkOldPwdMsg").text("*");
			var bool = false;
			var oldPwd = $("#oldPwd").val();
			if (oldPwd == "" || oldPwd == null) {
				$("#checkOldPwdMsg").text("原密码不能为空!");
				return bool;
			}

			$.ajax({
				type : "post",
				url : "${ctx }/rest/user/checkOldPwd",
				async : false, // 此处必须同步
				dataType : "json",
				data : {
					"oldPwd" : oldPwd
				},
				success : function(data) {
					if (data.flag == 1) {
						$("#checkOldPwdMsg").text("*");
						bool = true;
					} else {
						$("#checkOldPwdMsg").text("原密码错误！");
						bool = false;
					}
				}
			});
			return bool;
		}

		function checkNewPwd() {
			var reg = /^[a-zA-Z0-9!@#$%^&*()_+|{}?><\.\-\]\\[\/]*$/;//密码格式校验

			var regex1 = /[0-9]+/;
			var regex2 = /[a-zA-Z]+/;
			var regex3 = /[!@#$%^&*()_+|{}?><\.\-\]\\[\/]+$/;

			var flag = 0;

			$("#checkNewPwdMsg").text("*");
			var oldPwd = $("#oldPwd").val();
			var newPwd = $("#newPwd").val();
			if (newPwd == "" || newPwd == null) {
				$("#checkNewPwdMsg").text("新密码不能为空!");
				return false;
			} else if (newPwd.length <8) {
				$("#checkNewPwdMsg").text("密码不能小于8位数！");
				return false;
			} else if (newPwd.length > 20) {
				$("#checkNewPwdMsg").text("密码不能大于20位数！");
				return false;
			} else if (!reg.test(newPwd)) {
				$("#checkNewPwdMsg").text("请输入正确格式的密码!");
				return false;
			}

			if (regex1.test(newPwd)) {
				flag++;
			}
			if (regex2.test(newPwd)) {
				flag++;
			}
			if (regex3.test(newPwd)) {
				flag++;
			}

			if (flag < 2) {
				$("#checkNewPwdMsg").text("请使用8-20位字母/数字/符号的至少两种组合的密码!");
				return false;
			}
			if (oldPwd == newPwd) {
				$("#checkNewPwdMsg").text("新密码与原密码一致!");
				return false;
			}
			return true;
		}

		function checkConfirmPwd() {
			$("#checkConfirmPwdMsg").text("*");
			var newPwd1 = $("#newPwd").val();
			var confirmPwd = $("#confirmPwd").val();
			if (confirmPwd == "" || confirmPwd == null) {
				$("#checkConfirmPwdMsg").text("确认密码不能为空!");
				return false;
			}
			if (newPwd1 != confirmPwd) {
				$("#checkConfirmPwdMsg").text("两次输入密码不一致!");
				return false;
			}
			return true;
		}
	</script>
	
</body>
</html>