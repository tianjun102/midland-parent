<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!doctype html>
<html lang="en">
<head>

<meta charset="UTF-8">
<title>角色</title>

<link rel="stylesheet" href="${ctx }/assets/css/zTreeStyle.css">
<link rel="stylesheet" href="${ctx }/assets/css/common.css">

<script src="${ctx}/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/scripts/jstree.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/scripts/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="${ctx}/assets/scripts/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>

</head>


<SCRIPT type="text/javascript">
	var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};

	var zNodes =[${dataFmt }];
	
	var code;
	
	function setCheck() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		type = { "Y":"ps", "N":"ps"};
		zTree.setting.check.chkboxType = type;
		showCode('setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };');
	}
	function showCode(str) {
		if (!code) code = $("#code");
		code.empty();
		code.append("<li>"+str+"</li>");
	}
	
	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		setCheck();
	});
</SCRIPT>
<script type="text/javascript">
	$(function() {
		var strs = "";
		$("#save").click(function() {
            var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
            nodes=treeObj.getCheckedNodes(true);
            var v="";
            for(var i=0;i<nodes.length;i++){
            	strs += nodes[i].id + ","; //获取选中节点的值
            }
			/**/
			var flag=true;
			//提交
			$.ajax({
				url : '${ctx}/rest/role/saveRolePermissions',
				type : 'post', //数据发送方式 
				async:false,
				dataType : 'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json) 
				data : {
					roleId : $("#roleid").val(),
					permissionIds : strs
				},
				success : function(data) { //成功 
					if (data.saveFlag > 0) {
						
					}else{
						flag=false;
						
					}
				}
			});
			if(flag){
				setTimeout(function(){parent.window.location.reload();},1000);
			}
			
		});
	});
	
	//取消
	function closeWin() {
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	
</script>

<body>
	<!--表单公共-->
	<div class="detial_listwarp">
		
		<div class="public_div">
			<div class="content_wrap">
				<input type="hidden" id="roleid" value="${roles.id}">
				
				<div class="zTreeDemoBackground left">
					<ul id="treeDemo" class="ztree"></ul>
				</div>
			
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div class="pop-footer" style = "padding-bottom:20px;">
	<a href="javascript:;"  target="contentF" class = "public_btn bg2" id="save" >保存</a>  
	<a style="margin-left: 20px"  class = "public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
	
	</div>
</body>
</html>
