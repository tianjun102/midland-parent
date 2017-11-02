<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="layout/tablib.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>美联物业 - 关于平台</title>
<link rel="stylesheet" href="${ctx }/assets/css/common.css">
	<%----%><script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="101432824" data-redirecturi="http://10.58.189.10:8085/thirdParty/callback/qq" charset="utf-8"></script>
</head>
<body>
<!--关于平台界面-->
	<div class="box" style = "min-width:auto;">
		<section class = "content" style = "width:auto;">
			<p class = "detail-title">
				<span>后台管理首页</span>
			</p>
			<dl>
			<dt style = "font-size:28px; color:#d20000; margin-top:45px; margin-bottom:30px; text-align:center;">美联物业首页面</dt>
			<dt style = "text-align:center;">
				<a href="https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101432824&redirect_uri=http://10.58.189.10:8085/thirdParty/callback/qq&state=1&scope=get_user_info,get_info">请使用你的QQ账号登陆</a>
				<a href="javascript:void(0);" onclick="loginWeiXin();">请使用你的微信账号登陆</a>
				<span id="qqLoginBtn"></span>
			</dt>
			</dl>
		</section>
	</div>
</body>
</html>

<script type="text/javascript">
   /* QC.Login({
        btnId:"qqLoginBtn"    //插入按钮的节点id
    });*/

   function loginWeiXin(){
	window.open("https://open.weixin.qq.com/connect/qrconnect?appid=wxece8a39111954f91&redirect_uri="+encodeURI('http://localhost:8085/thirdParty/contentIndex')+"&response_type=code&scope=snsapi_login&state=3d6be0a4035d839573b04816624a415e#wechat_redirect");
   }
</script>
