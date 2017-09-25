<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


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
        <title>修改分销方案</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta name="MobileOptimized" content="320">
        <link rel="stylesheet" href="${ctx}/assets/css/ztree/css/demo.css" type="text/css">
		<link rel="stylesheet" href="${ctx}/assets/css/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	   	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="${ctx}/assets/scripts/ztree/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="${ctx}/assets/scripts/ztree/js/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
		<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
		<script type="text/javascript" src="${ctx}/assets/scripts/common.js"></script>
		<script type="text/javascript" src="${ctx}/assets/scripts/base.js" ></script>
		<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ctx}/assets/css/easydropdown.css" />
		<link rel="stylesheet" href="${ctx}/assets/css/common.css">
		<style>
		._displan {
			width: 250px;
			height: 38px;
			line-height: 38px;
			border: 1px solid #dbe2e6;
			border-radius: 4px;
			text-indent: 10px;
			margin-left: 5px;
			outline-color: #0099e0;
		}
		/* #showTable>tr>td:first-child {
			display: inline-block;
			width: 70px;
			height: 38px;
			line-height: 38px;
			text-align: right;
			font-size: 14px;
			color: rgb( 102, 102, 102 );
		} */
		#showTable>tr {
			margin-top: 10px;
		}
		</style>		
    </head>
    <!-- END HEAD -->

    <!-- BEGIN BODY -->
<body>
<div class = "box">
<section class="content" style="border:none;width: 98%">
		<p class = "detail-title">
					<span>修改分销方案</span>
		</p>
   		  <form action="${ctx}/rest/distPlan/forModifyDistPlan" method="post" id="ModifyDistPlanForm" >
	   		  <table id="showTable">
	   		  	<tr>
	   		  		<td>分销方案代码：</td>
	   		  		<td><input class = "_displan" name="distPlanSn" value="${distplan.distPlanSn}" readonly="readonly"/><label style="color: red"  class = "_star ">*</label>
	   		  		<input name="id"  type="hidden" value="${distplan.id}"/>
	   		  		</td>
	   		  	</tr>
	   		  	<tr style = "line-height:4;">
	   		  		<td>分销方案名称：</td>
	   		  		<td><input class = "_displan" name="distPlanName" value="${distplan.distPlanName}"><label style="color: red"  class = "_star ">*</label></td>
	   		  	</tr>
	   		  	<tr>
	   		  		<td style="vertical-align: middle; ">分销产品：</td>
	   		  		<td>
	   		  			<div class="" style = "overflow:hidden; margin-left: 6px; padding-bottom:20px;">
			   		  		<div class="zTreeDemoBackground" style = "margin-right:30px; float:left; width:auto; height:auto;">
								<ul id="categoryTree" class="ztree" style = "width:400px; height:300px!important;"></ul>
							</div>
							
							<div class="zTreeDemoBackground" style = "float:left; width:auto; height:auto;">
								<ul id="productTree" class="ztree" style = "width:400px; height:300px!important;"></ul>
							</div>
						</div>
	   		  		</td>
	   		  	</tr>
	   		  	<tr>
	   		  		<td style="vertical-align: middle; ">客户信息：</td>
	   		  		<td>
		   		  		<div class="" style = "overflow:hidden; padding-bottom:20px;">
		   		  			<div class="zTreeDemoBackground" style = "margin-right:30px; margin-left: 6px; float:left; width:auto; height:auto;">
								<ul id="areaTree" class="ztree" style = "width:400px; height:300px!important;"></ul>
							</div>
							
							<div class="zTreeDemoBackground" style = "float:left; width:auto; height:auto;">
								<ul id="customerTree" class="ztree" style = "width:400px; height:300px!important;"></ul>
							</div>
						</div>
	   		  		</td>
	   		  	</tr>
	   		  	<tr>
	   		  		<td colspan="2" style="text-align: center;">
	   		  			<a target="contentF" class = "public_btn bg2" id="save" onclick="formSubmit()">保存</a>  
						<a style="margin-left: 20px" href="${ctx}/rest/distPlan/showDistPlanIndex" target="contentF" class = "public_btn bg3" id="cancel">取消</a>
	   		  		</td>
	   		  	</tr>
	   		  </table>
   		  </form>
   </section>
  </div>
</body>
<script type="text/javascript">
		
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
				onCheck: onCheck
			}
		};

		var catProNodes =[${catProData}];
		
		var areaCustNodes =[${areaCustData}]; 
		
		
		var code;
		

		$(document).ready(function(){
			$.fn.zTree.init($("#categoryTree"), setting, catProNodes);
		 	$.fn.zTree.init($("#areaTree"), setting, areaCustNodes);
		 	onCheck();
		});
		
		function onCheck(event,treeId, treeNode) {
			$("#productTree").empty();
			var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
			var tree = treeObj.getCheckedNodes(true);
			for(var i = 0 ; i<tree.length;i++){
				var id = tree[i].id;
				var name = tree[i].name;
				var type = tree[i].type;
				if(type!=0){
					$("#productTree").append("<li onmouseover='testa(this)' onmouseout='testb(this)' id='"+id+"'  >"+name+"</li>");
				}
			}
			
			$("#customerTree").empty();
			var areaTreeObj = $.fn.zTree.getZTreeObj("areaTree");
			var areaTree = areaTreeObj.getCheckedNodes(true);
			for(var i = 0 ; i<areaTree.length;i++){
				var id = areaTree[i].id;
				var name = areaTree[i].name;
				var type = areaTree[i].type;
				if(type!=0){
					$("#customerTree").append("<li onmouseover='testa(this)' onmouseout='testb(this)' id='"+id+"' >"+name+"</li>");
				}
			}
			
			
		}
		
		
		function testa(obj){
			obj.style.background = "red";
		}
		function testb(obj){
			obj.style.background = "";
		}
		
	
		function formSubmit(){
			if(checkForm()){
				var str1 = "";
				var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
				var tree = treeObj.getCheckedNodes(true);
				var z = 0 ;
				for(var i = 0 ; i<tree.length;i++){
					var id = parseInt(tree[i].id)-100000;
					var name = tree[i].name;
					var type = tree[i].type;
					if(type!=0){
						str1 = str1 + "<input type='hidden' name='proList["+z+"].prodId'  value='"+id+"' />";
						z++;
					}
				}
				$("#showTable").append("<tr colspan='2'><td>"+str1+"</td></tr>");
				
				var x = 0 ;
				var str2 = "";
				var areaTreeObj = $.fn.zTree.getZTreeObj("areaTree");
				var areaTree = areaTreeObj.getCheckedNodes(true);
				for(var i = 0 ; i<areaTree.length;i++){
					var id = parseInt(areaTree[i].id)-100000;
					var name = areaTree[i].name;
					var type = areaTree[i].type;
					if(type!=0){
						str2 = str2 + "<input type='hidden' name='custList["+x+"].custId'  value='"+id+"' />";
						x++;
					}
				}
				$("#showTable").append("<tr colspan='2'><td>"+str2+"</td></tr>");
				
				$.ajax({ 
					type: "post", 
					url: "${ctx}/rest/distPlan/forModifyDistPlan", 
					async:false, // 此处必须同步
					dataType: "json",
					data:$('#ModifyDistPlanForm').serialize(),
					success: function(data){
						if(data.flag==1){
							layer.msg("修改成功！！！",{icon:1});
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
		
		
		
		function checkForm(){
			var distPlanSn = $("input[name='distPlanSn']").val();//分销方案代码
			var distPlanName = $("input[name='distPlanName']").val();//分销方案名称	
			
			
			//单位
			if(distPlanSn.trim() =="" || distPlanSn==null){
				layer.tips("分销方案代码不能为空！", "input[name='distPlanSn']",{tips:1});
				return false;
			}
			if(!getStrLenght(distPlanSn,20)){
				layer.tips("分销方案代码长度不能超过20个字符！", "input[name='distPlanSn']",{tips:1});
				return false;
			}
			//分销方案名称	
			if(distPlanName.trim() =="" || distPlanName==null){
				layer.tips("分销方案名称不能为空！", "input[name='distPlanName']",{tips:1});
				return false;
			}
			if(!getStrLenght(distPlanName,30)){
				layer.tips("分销方案名称长度不能超过30个字符！", "input[name='distPlanName']",{tips:1});
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
		 
	</script>
</html>