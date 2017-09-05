<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<%
		String basePath = request.getScheme() + "://" + request.getServerName() + 
			":" + request.getServerPort() + request.getContextPath() + "/";
	%>
    <base href="<%= basePath%>" />
    <script src="/resource/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>
	<script src="/resource/jquery/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<script src="/resource/jquery/validate/jquery.validate.min.js" type="text/javascript"></script>
	<link href="/resource/jquery/validate/jquery.validate.css" rel="stylesheet" type="text/css" />
	<!--[if lt IE 9]>
	<script src="/resource/Js/html5shiv.min.js" type="text/javascript"></script>
	<![endif]-->
	<script src="/resource/js/swfobject.js" type="text/javascript"></script>
	<script src="/resource/layer/layer.min.js" type="text/javascript"></script>
	<script src="/resource/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="/resource/UEditor/ueditor.config.js" type="text/javascript"></script>
	<script src="/resource/UEditor/ueditor.all.js" type="text/javascript"></script>
	<script src="/resource/UEditor/lang/zh-cn/zh-cn.js" type="text/javascript"></script>
	<script src="/resource/common/lib.js" type="text/javascript"></script>
	<script src="/resource/common/user.js" type="text/javascript"></script>
	
	<script src="/manage/resource/js/admin.js" type="text/javascript"></script>
	<link href="/manage/resource/css/style.css" rel="stylesheet" type="text/css" />
	

<script src="/resource/js/uploadPreview.min.js" type="text/javascript"></script>
	<script src="/view/resource/js/JScript.js"></script>
