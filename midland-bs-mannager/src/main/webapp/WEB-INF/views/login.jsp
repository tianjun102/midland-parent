<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="layout/tablib.jsp"%>

<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.0.3
Version: 1.5.5
Author: KeenThemes
Website: http://www.keenthemes.com/
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<base href="<%=basePath%>">
<meta charset="utf-8"/>
<title>登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<meta name="MobileOptimized" content="320">
<%--
<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="1106342971" data-redirecturi="http://175.102.18.14:8083/wechat/" charset="utf-8"></script>
--%>

<%
     String username = "";
     String password = "";
 
     Cookie[] cookies = request.getCookies();
     if (cookies != null) {
		 for (int i = 0; i < cookies.length; i++) {
			 if ("username".equals(cookies[i].getName())) {
				 username = cookies[i].getValue();
			 } else if ("password".equals(cookies[i].getName())) {
				 password = cookies[i].getValue();
			 }
		 }
	 }
%>
<script type="text/javascript" > 
if (window != top) 
top.location.href = location.href; 
</script>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
<!--登录顶部-->
	
	<div class="login_head">		
		<div class="container">
			<div class="row">
				<a href="index.html">
					<img src="${ctx}/assets/img/logo.png" title="wks">
				</a>
				<div class="login">
					<a href="http://www.jeavox.com.cn/">美联物业</a>
					<a class = "two_code" href="javascript:;">
						<img src="${ctx}/assets/img/APP.png"/>
						手机APP
						<img class = "two_2" src="${ctx}/assets/img/two.png"/>
					</a>
				</div>	
			</div>
		</div>	
	</div>


	<!--登录界面-->
	
	<div class="main login_bg">
		<div class="container">
			<div class="row">
				<div class="login-bg">
					<div class="longin-layer">
						<ul class="login-tab">
							<li class="li_focus"><a href="javascript:;">用户登录</a></li>
							<!-- <li><a href="javascript:;">管理员登录</a></li> -->
							<input type="hidden" value="0" id="hid" />
						</ul>
						<form action="" method="post" id="bill">
							<div class="login-box">
								<input class = "boxwrap" type="text" name="username" id="username" value="<%=username %>" placeholder="用户名" /> 
								<img src="${ctx}/assets/img/user.png" id="user"> 
								<input class = "boxwrap" type="password" name="password" id="password" value="<%=password %>" placeholder="密码" /> 
								<img src="${ctx}/assets/img/lock.png" id="lock"> 
								<input class = "boxwrap" type="text" name="id-code" id="id-code" placeholder="验证码" maxlength="4" onblur="javascript:;" /> 
								<img src="${ctx}/assets/img/key.png" id="key">
								<a href="#" class="yz-code"><input class = "boxwrap" type="button" id="code" onclick="createCode();" /></a>
								<em class = "remeber"><input type = "checkbox" name="remember" value="1"/>记住密码</em> 
								<span class="warn-area"	id="warnArea"></span>
							</div>
							<input type="button" value="登录" id="logsub" />
							<p class = "forget"><a href="javascript:;">忘记密码?</a><%--<span id="qqLoginBtn"></span>--%></p>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- 底部 -->

	<div class="login_foot">
		<div class="container">
			<div class="row">
				<div class="foot_copy">
					<p>Copyright ©美联后台管理平台 All Rights Reserved. 保留所有权利 </p>
					<p>粤ICP备11003044号</p>
				</div>
			</div>
		</div>
	</div>



	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/common.js"></script>

	<!-- 本页私有js -->
	<script type="text/javascript">
		//二维码显示
		
		$(".two_code").hover(function() {
			$(".two_2").show();
		},function() {
			$(".two_2").hide();
		});

	
	
		// jQuery完成登录界面的tab切换
		$('.login-tab').on('click', 'li', function() {
			/*  */
			$('.login-tab li').removeClass('li_focus');
			$(this).addClass('li_focus');
			$("input.boxwrap:not(:last-child)").each(function() {
				$(this).val('');
			});

			var index = $(this).index();
			if (index == 0) {
				$(".login_bg").removeClass("bg_2");
				$(".login_bg").addClass("bg_1");
				$(".forget").show();
			} else {
				$(".login_bg").removeClass("bg_1");
				$(".login_bg").addClass("bg_2");
				$(".forget").hide();
			}
			$('.form-list').addClass('form-hide');
			$('.form-list').eq(index).removeClass('form-hide');
		})

		//原生js判断用户名密码是否为空
		function checkUser1() {
			var userName = document.getElementById("username").value;
			var passWord = document.getElementById("password").value;
			var warnArea = document.getElementsByClassName("warn-area")[0];
			if (userName == "") {
				warnArea.innerHTML = "用户名不能为空";
				return false;
			}
			if (passWord == "") {
				warnArea.innerHTML = "密码不能为空";
				return false;
			} else {
				return true;
			}
		}

		window.onload = function() {
			createCode();
			(function error() {
				var isError = window.location.search.indexOf("error");
				var error="${error}";
				var warnArea = document.getElementsByClassName("warn-area");

				if (error != "") {
					warnArea[0].innerHTML = error;
				} else {
					return true;
				}
			})();

			$(function() {

				$(".login-tab li").eq(0).click(function() {
					$("#hid").val("0");
				})
				$(".login-tab li").eq(1).click(function() {
					$("#hid").val("1");
				})

				$("#logsub").click(function() {
					if (checkUser1()) {
						var userName = $("#username").val();
						var passWord = $("#password").val();
						if ($("#hid").val() == "0") {
							$("#bill").attr("action", "rest/user/login");
							$("#bill").submit();
						} else {
							$("#bill").attr("action", "rest/user/login");
							$("#bill").submit();
						}
					}

				})
			});

			var error = window.location.search.indexOf("error0");
			if (error != -1) {
				$('.login-tab li').removeClass('li_focus');
				$(".login-tab li").eq(1).addClass('li_focus');
				$("#hid").val("1");
			}

		}
		
		//回车后提交
		document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];                 
             if(e && e.keyCode==13){ // enter 键                 
                 document.getElementById("logsub").click();
            }
    }; 

		var code; //在全局定义验证码   
		//产生验证码  
		function createCode() {
			code = "";
			var codeLength = 4;//验证码的长度  
			var checkCode = document.getElementById("code");
			var random = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C',
					'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
					'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');//随机数  
			for (var i = 0; i < codeLength; i++) {//循环操作  
				var index = Math.floor(Math.random() * 36);//取得随机数的索引（0~35）  
				code += random[index];//根据索引取得随机数加到code上  
			}
			checkCode.value = code;//把code值赋给验证码  
		}

		//校验验证码  
		function validate() {
			var inputCode = document.getElementById("id-code").value
					.toUpperCase(); //取得输入的验证码并转化为大写        
			if (inputCode.length <= 0) { //若输入的验证码长度为0  

				document.getElementById("warnArea").innerHTML = "请输入验证码！";
				return false;
			} else if (inputCode != code) { //若输入的验证码与产生的验证码不一致时  
				createCode();//刷新验证码  
				document.getElementById("warnArea").innerHTML = "验证码错误！";
				return false;
			} else { //输入正确时  
				document.getElementById("warnArea").innerHTML = "";
				return true;
			}
		}

        /*QC.Login({
            btnId:"qqLoginBtn"    //插入按钮的节点id
        });*/
	</script>

</body>
<!-- END BODY -->
</html>