<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/zTree.jsp"%>
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
        <title>新增分类</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta name="MobileOptimized" content="320">
        <link rel="stylesheet" href="${ctx}/assets/css/ztree/css/demo.css" type="text/css">
		<link rel="shortcut icon" href="${ctx}/assets/app/img/favicon.ico"/>
		<link rel="stylesheet" href="${ctx }/assets/css/common.css">
		<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
    </head>
    <!-- END HEAD -->
<SCRIPT type="text/javascript">
		
		var setting = {
			check: {
				enable: true,
				chkboxType: { "Y": "sp", "N": "sp" }


			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick
			}
		};
		var catProNodes =[{id:0, pId:0,name:'分类',open:true,nocheck:true},${categoryData}];
		
		
		$(document).ready(function(){
			$.fn.zTree.init($("#categoryTree"), setting, catProNodes);
		});
		
		function beforeClick(treeId, treeNode, clickFlag) {
			$("input[name='parentId']").val(treeNode.id);
			$("input[name='parentName']").val(treeNode.name);
			$("#showDiv").hide();
		}
</SCRIPT>
    <!-- BEGIN BODY -->
<body>
<section class="content" style="border:none;">
	<form action="" method="post" id="inputForm">
	<ul class = "userinfo row">
			<li><span>分类名称：</span><input name="catName" /><label style="color: red"  class = "_star "  class = "_star ">*</label></li>
			<li><span>父节点：</span><input name="parentName" onclick="showTree()" readonly="readonly"/>
							<input name="parentId" type="hidden"/><label style="color: red" class = "_star " >*</label>
								
			</li>
			<li  id="showDiv" style="display: none;padding-top: 0px;padding-left: 70px; position:relative;" >
					<div class="zTreeDemoBackground left" style  = "position:absolute; left: -278px; top: 52px;"   onblur="test(event)">
						<ul id="categoryTree" class="ztree" style  = "width:235px; height: 240px;"></ul>
					</div>
					<img  src="${ctx}/assets/img/Closed_16px.png"  alt="关闭" style="vertical-align: top;position:absolute; left: -30px; top: 63px;" onclick="hideTree()">
			</li>
			<li ><span>描述：</span><input name="catDesc" /></li>
			<li><span>排序值：</span><input name="sortOrder" maxlength="6"><label style="color: red" class = "_star " >*</label></li>
			<li>
				<a target="contentF"  class = "public_btn bg2" style="margin-left: 50px;" id="save" onclick="saveData()">保存</a>  
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
    
    
    function saveData() {
		if (checkForm()){
			$.ajax({ 
					type: "post", 
					url: "${ctx}/rest/product/forInputCategory", 
					async:false, // 此处必须同步
					dataType: "json",
					data:$('#inputForm').serialize(),
					success: function(data){
						if(data.flag==1){
							layer.msg("新增成功！！！",{icon:1});
							$('#save').removeAttr("onclick");
							setTimeout(function(){window.open("${ctx}/rest/product/showCategoryIndex","contentF");},2000);  
							
						}else{
							layer.msg("新增失败！",{icon:2});
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
	
 	
    
    
	function checkForm(){
		var catName = $("input[name='catName']").val();
		var parentId = $("input[name='parentId']").val();
		var sortOrder = $("input[name='sortOrder']").val();
		var catDesc = $("input[name='catDesc']").val();
		
		
		if(catName.trim() =="" || catName==null){
			layer.tips("分类名称不能为空！", "input[name='catName']",{tips:1});
			return false;
		}
		
		if(!getStrLenght(catName,15)){
			layer.tips("分类名称长度不能超过15个字符！", "input[name='catName']",{tips:1});
			return false;
		}
		
		if(parentId.trim() =="" || parentId==null){
			layer.tips("父节点不能为空！", "input[name='parentName']",{tips:1});
			return false;
		}
		
		if(isNaN(parentId)){
			layer.tips("请选择正确的父节点！", "input[name='parentName']",{tips:1});
			return false;
		}
		
		if(!getStrLenght(catDesc,50)){
			layer.tips("描述长度不能超过50个字符！", "input[name='catDesc']",{tips:1});
			return false;
		}
		
		if(sortOrder.trim() =="" || sortOrder==null){
			layer.tips("排序值不能为空！", "input[name='sortOrder']",{tips:1});
			return false;
		}
		
		if(sortOrder!=null && sortOrder.trim() !=""){
			if(isNaN(sortOrder) || sortOrder<=0){
				layer.tips("请输出正确的排序值！", "input[name='sortOrder']",{tips:1});
				return false;
			}
		}
		var a=true;
		$.ajax({ 
			type: "post", 
			url:"${ctx}/rest/product/searchCatNameIsExist",
			async:false, // 此处必须同步
			dataType: "json",
			data :{"catName":catName},
			success : function(result) {
				if("1"==result.result){
					layer.tips("该分类已存在，请重新输入！", "input[name='catName']",{tips:1});
					a=false;
				}else{
					
					a=true;
				}
			}
		});
		return a;
	}
	

	function showTree(event){
		$("#showDiv").show();
	}
	
	function hideTree(event){
		$("#showDiv").hide();
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
    </script>
</body>
</html>