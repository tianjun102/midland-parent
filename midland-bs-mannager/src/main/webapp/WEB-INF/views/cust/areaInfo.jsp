<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctx }/assets/css/common.css">
</head>
<body>

<section class = "content" style = "border:none;">
	<form action="" method="post" id="areaInfoForm">
	<input type="hidden" name="areaId" id="areaId" value="${area.areaId}">
	<input type="hidden" name="an" id="an" value="${area.areaName}">
	<ul class = "adminfo row">
		<li><span>区域名称：</span><input style = "width:264px;" type="text" name="areaName" id="areaName" value="${area.areaName}"  onblur="checkAreaName();"maxlength="20"/><span class="_star">*</span></li>
		<li><span>区域描述：</span><input style = "width:264px;" type="text" name="areaDescription" id="areaDescription" value="${area.areaDescription}" maxlength="50"/></li>
		<li style ="padding-top:30px;">
			<a style="margin-left: 70px;" target="contentF" class = "public_btn bg2" id="save" onclick="saveData()">保存</a>  
			<a style="margin-left: 20px" class = "public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
		</li>
	</ul>
	</form>	
</section>
<script type="text/javascript">
	//保存数据
	function saveData() {
		
		if (checkAreaName()) {
			var areaId = $("#areaId").val();
			var areaName = $("#areaName").val();
			var areaDescription = $("input[name='areaDescription']").val();
						
			$.ajax({ 
					type: "post", 
					url: "${ctx}/rest/cust/editArea", 
					async:false, // 此处必须同步
					dataType: "json",
					data:{"areaId":areaId,"areaName":areaName,"areaDescription":areaDescription},
					success: function(data){
						if(data.flag==1){
							layer.msg("保存成功！！！",{icon:1});
							$('#save').removeAttr("onclick");
							setTimeout(function(){parent.location.reload();},1000);
						}else{
							layer.msg("修改失败！",{icon:2});
						}
					},
					error: function(){
						layer.msg("修改失败！",{icon:2});
					}
				});
		}
	}
	//检查区域名称是否已使用
	function checkAreaName(){
		var areaName = $("#areaName").val();
		var an = $("#an").val();
		if(areaName =="" || areaName==null){
			layer.tips("区域名不能为空！", "input[name='areaName']",{tips:1});
			return false;
		}
		if(an==areaName){
			return true;
		}
		
		var a=true;
		$.ajax({ 
			type: "post", 
			url: "${ctx }/rest/cust/checkAreaName",
			async:false, // 此处必须同步
			dataType: "json",
			data:{"areaName":areaName},
			success: function(xmlobj){ 
				if (xmlobj.flag==1){
					layer.tips("该名称已存在！", "input[name='areaName']",{tips:1});
					a=false;
				}else{
					a=true;
				}
			} 
		});
		return a;
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