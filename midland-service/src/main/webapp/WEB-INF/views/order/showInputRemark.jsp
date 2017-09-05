<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <base href="<%=basePath%>">
        <meta charset="utf-8" />
        <title>新增订单备注</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta name="MobileOptimized" content="320">

    </head>
    <script type="text/javascript" src="${ctx}/assets/plugins/jquery/jquery-1.11.1.min.js"></script>
	<link rel="shortcut icon" href="${ctx}/assets/app/img/favicon.ico"/>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
	<link rel="shortcut icon" href="${ctx}/assets/app/img/favicon.ico"/>
	<link rel="stylesheet" href="${ctx }/assets/css/common.css">
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
<body>
<section class="content" style="border:none; padding-left:20px;">
	<form action="" method="post" id="addForm">
	<ul class = "userinfo row">
			<li><span>订单单号：</span>
				<input style="width:264px;" name=orderSn type="text" value="${orderInfo.orderSn }" readonly="readonly" />
				<input type="hidden"  name=orderId value="${orderInfo.id}" />
			</li>
			<li><span>备注信息：</span>
				<textarea name="remarkDesc" id="remarkDesc"  style="width:260px;height: 150px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;" ></textarea>
			</li>
			<li style = "padding-top:30px;">
				<a target="contentF" class = "public_btn bg2" style="margin-left: 70px;" id="save" onclick="saveData()">保存</a>  
				<a style="margin-left: 20px" class = "public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
			</li>
		</ul>
		</form>
		
</section>	    
    <script type="text/javascript">
    
    //取消
	function closeWin(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
    
    function checkform(){
    	var remarkDesc  =  $("#remarkDesc").val();
    	if(remarkDesc.trim() =="" || remarkDesc==null){
			layer.tips("备注信息不能为空！", "#remarkDesc",{tips:1});
			return false;
		}
		
		if(!getStrLenght(remarkDesc,200)){
			layer.tips("备注信息长度不能超过200个字符！", "#remarkDesc",{tips:1});
			return false;
		}
		return true;
    }
    
    
    //长度校验 
	function getStrLenght(message,MaxLenght) {
        var strlenght = 0; //初始定义长度为0
        var txtval = message.trim();
        for (var i = 0; i < txtval.length; i++) {
               strlenght = strlenght + 1; //一个字符
        }
        return strlenght > MaxLenght ? false : true;
    }
    
    function saveData() {
    	
    	if(checkform()){
			$.ajax({ 
					type: "post", 
					url: "${ctx}/rest/order/forInputRemark", 
					async:false, // 此处必须同步
					dataType: "json",
					data:$("#addForm").serialize(),
					success: function(data){
						if(data.flag==1){
							layer.msg("新增成功！",{icon:1});
							$('#save').removeAttr("onclick");
							setTimeout(function(){window.open("${ctx}/rest/order/showOrderInfoIndex","contentF");},2000);
						}else{
							layer.msg("新增失败！",{icon:2});
						}
					},
					error: function(){
						layer.msg("新增失败！",{icon:2});
					}
						
				});
    	}
	}
	
    </script>
</body>    
</html>