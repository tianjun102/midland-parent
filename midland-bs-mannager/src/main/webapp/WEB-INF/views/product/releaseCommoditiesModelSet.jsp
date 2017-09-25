<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/zTree.jsp"%>
<%@include file="../layout/source.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
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

		var catProNodes =[${categoryData}];
		
		$(document).ready(function(){
			$.fn.zTree.init($("#categoryTree"), setting, catProNodes);
		});
		
		function beforeClick(treeId, treeNode, clickFlag) {
				$("input[name='catId']").val(treeNode.id);
				$("input[name='catName']").val(treeNode.name);
				hideTree();
				showProperty(treeNode.id);
		}
		
		function showTree(){
			$("#showCatDiv").show();
		}
		
		function hideTree(){
			$("#showCatDiv").hide();
		}
</SCRIPT>
</head>
<body>
	<div class="box" style= "min-width:1070px;">
		<section class="content">
		<p class="detail-title">
			<span>产品分类属性配置</span>
		</p>
			<form action="${ctx}/rest/product/saveProductProperty" method="post" id="propertyForm">
			<ul class="adminfo row">
				<li><span>分类：</span> <input name="catName" type="text"
					onclick="showTree()" readonly="readonly" /> <input name="catId"
					type="hidden" id="catId" /></li>
				<li id="showCatDiv"
					style="display: none; padding-top: 0px; padding-left: 70px; position: relative; margin:5px 0;">
					<style>
 		  				.content ul.adminfo li:last-child>a:not(:nth-child(2)){
 		  					margin-left: 0px;
 		  				}
 		  			</style>
					<div class="zTreeDemoBackground left" style  = "position:absolute; top: -15px; left: 89px;">
						<ul id="categoryTree" class="ztree" style  = "width:250px;"></ul>
					</div>
					<img src="${ctx}/assets/img/Closed_16px.png" alt="关闭"
					style="vertical-align: top;position:absolute; position:absolute; left: 342px; top: -3px;" onclick="hideTree()">
				</li>
				<li>
					<span>属性信息：</span>
					<input class = "public_btn bg1" type="button" value="添加" onclick="addPro()" />
				</li>
				<li >
					<table id="tb">
						<!-- <tr>
							<td><label>属性名：</label> <input type='hidden'
								name='propIdStr' value='' /> <input type="text" name="propName"
								value="" /></td>
							<td><label>类型：</label> <select name="propType" id="propType">
									<option value="option">单选</option>
									<option value="checkbox">多选</option>
									<option value="text">文本框</option>
							</select></td>
							<td><label>属性值：</label><input type="text" name="propValue"
								value="" /></td>
							<td><a onclick="delPro(this)">删除</a></td>
						</tr> -->
					 </table>
				</li>
				<li>
					<span></span>
					<a target="contentF" class="public_btn bg2" id="save" onclick="checkForm()">保存</a>
				</li>
			</ul>
			</form>
		</section>
	</div>
	<script type="text/javascript">
	
		window.onload = function(){
			addPro();
		}
	
		//添加tr
		function addPro(propId, propName, propType, propValue) {
			if (propId == null || propId == "undefined") {
				propId = "";
			}
			if (propName == null || propName == "undefined") {
				propName = "";
			}
			if (propType == null || propType == "undefined") {
				propType = "";
			}
			if (propValue == null || propValue == "undefined") {
				propValue = "";
			}
			var context = "";
			var context = "";
			context = "<tr style='height:55px'>"
					+ "<td style='' >"
					+ "<label style=' width: 70px; text-align:right;'>属性名：</label>"
					+ "<input type='hidden' name = 'propIdStr' value= '"+propId+"'/>"
					+ "<input style='float:none; margin-left:3px; width: 250px;' type='text' name='propName' value='"+propName+"'/>"
					+ "</td>";
			//选中判断
			if (propType == "checkbox") {
				context = context
						+ "<td >"
						+ "	<label style=' width: 70px; text-align:right;'>类型：</label> "
						+ "<select name='propType' id='propType' class='dropdown' style='width: 100px; display:inline-block!important;'>"
						+ "<option value='option'>单选</option>"
						+ "<option value='checkbox' selected='selected'>多选</option>"
						+ "<option value='text'>文本框</option>" + "</select>"
						+ "</td>";
			} else if (propType == "text") {
				context = context
						+ "<td>"
						+ "<label style=' width: 70px; text-align:right;'>类型：</label> "
						+ "<select name='propType' id='propType' class='dropdown'  style='width: 100px; display:inline-block!important;' >"
						+ "<option value='option'>单选</option>"
						+ "<option value='checkbox'>多选</option>"
						+ "<option value='text' selected='selected'>文本框</option>"
						+ "</select>" + "</td>";
			} else {
				context = context
						+ "<td>"
						+ "<label style=' width: 70px; text-align:right;'>类型：</label> "
						+ "<select name='propType' id='propType' class='dropdown' style='width: 100px; display:inline-block!important;'>"
						+ "<option value='option' selected='selected'>单选</option>"
						+ "<option value='checkbox'>多选</option>"
						+ "<option value='text'>文本框</option>" + "</select>"
						+ "</td>";
			}

			context = context
					+ "<td>"
					+ "<label style=' width: 70px; text-align:right;'>属性值：</label>"
					+ "<input style=' float:none; width: 250px;' type='text' name='propValue' value='"+propValue+"'/>"
					+ "</td>" + "<td>"
					+ "<a onclick='delPro(this)' style='margin-left:30px' class ='public_btn bg1'>删除</a>"
					+ "</td>" + "</tr>";

			$("#tb").append(context);
		}

		//动态显示分类下的属性列表
		function showProperty(catId) {
			$("#tb").empty();
			var context = "";
			if (catId != "") {
				$.ajax({
					url : "${ctx}/rest/product/searchPropertyList",
					type : 'post', //数据发送方式 
					dataType : 'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json) 
					data : {
						"catId" : catId
					},
					async : false,
					success : function(result) {
						//console.log(result);
						//
						if (result.result.length == 0) {
							addPro();//默认添加一行空白的
						} else {
							for (var i = 0; i < result.result.length; i++) {
								var enabled = result.result[i].enabled; //是否为必须属性
								var propId = result.result[i].propId;
								var name = result.result[i].propName;
								var type = result.result[i].propType;
								var value = result.result[i].propValue;
								addPro(propId, name, type, value);//添加行
							}
						}
					}
				});
			}
		}

		function delPro(obj) {
			var prodStr = $(obj).parent().parent().find("input:hidden").val();
			if (prodStr=="") {
				$(obj).parent().parent().remove();
				layer.msg("删除成功！",{icon:1});
				return false;
			}
			layer.open({
						type : 1,
						skin : 'layer-style',
						area: ['350px','200px'],
						shadeClose : false, //点击遮罩关闭
						title : [ '删除属性' ],
						resize : false,
						scrollbar : false,
						content : '<section class = "content" style = "border:none;">'
								+ '<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您是否确定删除该属性?</p>'
								+ '</section>',
						btn : [ '确定', '取消' ],
						yes : function(index) {
								$.ajax({
									type : "post",
									url : "${ctx}/rest/product/forRemoveProperty?prodId="+ prodStr,
									cache : false,
									async : false, // 此处必须同步
									dataType : "json",
									success : function(xmlobj) {
										if (xmlobj.flag == 1) {
											layer.msg("删除成功！", {icon : 1});
											$(obj).parent().parent().remove();
										}
										if (xmlobj.flag == 0) {
											layer.msg("删除失败！！", {icon : 7});
										}
										layer.close(index);
									}
								});
						}
						,success: function (layero) {
						      var btn = layero.find('.layui-layer-btn');
						      btn.css('text-align', 'center');
						  }
					})
		}
		
		function checkForm(){
			var catId =  $("input[name='catId']").val();
			if(catId==""){
				layer.msg("请选择您要操作的分类属性!", {icon : 7});
				return false;
			}
			
			var b = true;
			$("#tb tr").each(function(j){
				var name =  $(this).find("td:first").find("input:text").val();
				if(name==""){
					layer.msg("第"+(j+1)+"行,属性名不能为空!", {icon : 7});
					b = false;
					return false;
				}
			})
			if(b){
				$.ajax({
					type : "post",
					url : "${ctx}/rest/product/saveProductProperty",
					cache : false,
					async : false, // 此处必须同步
					dataType : "json",
					data:$('#propertyForm').serialize(),
					success : function(xmlobj) {
						if (xmlobj.flag == 1) {
							layer.msg("操作成功！", {icon : 1});
							$('#save').removeAttr("onclick");
							setTimeout(function(){window.open("${ctx}/rest/product/rcms","contentF");},2000);  
						}
						if (xmlobj.flag == 0) {
							layer.msg("操作失败！！", {icon : 7});
						}
						layer.close(index);
					}
				});
			}
		}
	</script>
</body>
</html>