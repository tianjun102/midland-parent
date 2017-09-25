<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>

<%@include file="../layout/zTree.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>智者汇 - 库存查询</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />

</head>
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
		}
		
		function showTree(){
			$("#showCatDiv").show();
		}
		
		function hideTree(){
			$("#showCatDiv").hide();
		}
		
		
		function closeDiv(){
			$("input[name='catId']").val("");
			$("input[name='catName']").val("");
			$("#showCatDiv").hide();
		}
</SCRIPT>
<body>
	<!--库存查询界面-->
	<div class="box">
		<section class="content">
		<p class="detail-title">
			<span>库存查询</span> <a class="setup" href="javascript:;"
				target="contentFrame" onclick="toImportPage();">导入安全库存</a>
		</p>
		<form action="${ctx }/rest/stock/stockQueryList" method="POST"
			id="searchForm"
			onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class="userinfo row">
				<li><span>产品名称：</span><input type="text" name="productName"
					id="productName" placeholder="产品名称支持模糊查询" /></li>
				<li><span>分类：</span> <input name="catName" type="text"
					onclick="showTree()" readonly="readonly" /> <input name="catId"
					type="hidden" id="catId" /></li>
				<li id="showCatDiv" style="display: none; padding-top: 0px; padding-left: 70px; position: relative;">
					<div class="zTreeDemoBackground left"
						style  = "position:absolute; left: -270px; top: 50px;">
						<ul id="categoryTree" class="ztree" style="width: 240px;"></ul>
					</div> <img src="${ctx}/assets/img/Closed_16px.png" alt="关闭"
					style="vertical-align: top;position:absolute; left: -25px; top: 60px;" onclick="closeDiv()">
				</li>
				<li><span style = "float:left;">库存量：</span> <select name="kcl" id="kcl"
					class="dropdown">
						<option value="0">在库库存</option>
						<option value="1">可用库存</option>
						<option value="2">预警库存</option>
				</select> <input class="half" style="width: 70px; float:none;" type="text" name="l1"
					id="l1" onkeyup="this.value=this.value.replace(/\D/g,'')"
					onafterpaste="this.value=this.value.replace(/\D/g,'')"
					maxlength="5" /> <em>-</em> <input class="half"
					style="width: 70px; float:none;" type="text" name="l2" id="l2"
					onkeyup="this.value=this.value.replace(/\D/g,'')"
					onafterpaste="this.value=this.value.replace(/\D/g,'')"
					maxlength="5" /></li>
				<li><input class="public_btn bg1"
					type="submit" name="inquery" id="inquery" value="查询" />
					<input class="public_btn bg1" type="submit" name="export"
					id="export" value="导出" /> 
					</li>
			</ul>
		</form>
		<div id="listDiv"></div>
		</section>
	</div>
	<!-- 本页私有js -->
	<script type="text/javascript"
		src="${ctx}/assets/scripts/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${ctx}/assets/scripts/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/common.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/base.js"></script>

	<script type="text/javascript">
		window.onload = function(){
			$('#searchForm').submit();
		}
		
		$(document).ready(function(){
			$(".dropdown").eq(1).width(117);
		});
		
		//导出
		$("#export").click(
				function() {
					$("#searchForm input[type='text']").each(
							function() {
								$(this).val(
										$.trim($(this).val()).replace(
												new RegExp("'", "gm"), "")
												.replace(new RegExp("%", "gm"),
														""));
							});
					var formSerialize = $("#searchForm").serialize();
					window.open("${ctx}/rest/stock/exportStock?"
							+ formSerialize);
				});
		
		function toImportPage(){
			layer.open({
				type: 2,
				skin: 'layer-style',
				area: ['400px','200px'],
				shadeClose: false, //点击遮罩关闭
				title:['导入安全库存'],
				resize: false,
				scrollbar:false,
				content:['${ctx}/rest/stock/toImportPage', 'no']
			});
		}
    </script>
</body>
</html>