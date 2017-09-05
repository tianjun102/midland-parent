<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@include file="../layout/tablib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>智者汇 - 产品列表</title>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/assets/css/easydropdown.css" />
<link rel="stylesheet" href="${ctx}/assets/css/common.css">
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.css">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="MobileOptimized" content="320">

</head>
<body>

	<!--产品列表界面-->
	<div class="box">
		<section class="content">
			<p class="detail-title">
				<span>产品列表</span> <a class="setup"
					href="${ctx}/rest/product/showInputProduct" target="contentF">发布产品</a>
			</p>
			<form action="${ctx }/rest/product/showProductList" method="POST"
				id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
				<ul class="userinfo row">
					<li><span>产品名称：</span><input type="text" name="productName"
						id="" placeholder="请输入产品名称" /></li>
					<li><span>产品编码：</span><input type="text" name="productCode"
						id="" placeholder="请输入产品编码" /></li>
					<li><span>分类名称：</span><input type="text" name="catName" id=""
						placeholder="请输入分类名称" /></li>
					<li><span>价格：</span><input class="half" type="text"
						name="salePrice1" id="" placeholder="" /><em class = "gang">-</em><input
						class="half" type="text" name="salePrice2" id="" /></li>
					<li><span>上架时间：</span><input class="Wdate half" id="time1"
						onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})"
						name="planSaleStartTime" /> <em class = "gang">-</em><input
						class="Wdate half"
						onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})"
						id="time2" name="planSaleEndTime" /></li>
					<li><span style = "float:left;">推荐状态：</span> <select name="isRecommend"
						class="dropdown">
							<option value="" class="label">请选择</option>
							<option value="0">未推荐</option>
							<option value="1">已推荐</option>
					</select></li>
					<li><span style = "float:left;">销售状态：</span> <select name="prodStatus"
						class="dropdown">
							<option value="" class="label">请选择</option>
							<option value="1">已上架</option>
							<option value="2">已下架</option>
							<option value="3">待上架</option>
							<option value="4">售罄</option>
					</select></li>
					<li><input class="public_btn bg1" type="submit" name="inquery"
						id="inquery" value="查询" /> <input class="public_btn bg1"
						type="reset" name="clear" id="clear" value="清空条件" />
						<input class="public_btn bg1" type="submit" name="export"
						id="export" value="导出" />
						</li>
				</ul>
			</form>
			<p class="detail-titleBtn" style="text-align: left; margin-top: 5px">
				<!-- <a class="setup" style="float: left;" href="#" onclick="isChange(1)">商品上架</a>
				<a class="setup" style="float: left;" href="#" onclick="isChange(2)">商品下架</a>
				<a class="setup" style="float: left;" href="#" onclick="isChange(3)">设置推荐</a>
				<a class="setup" style="float: left;" href="#" onclick="isChange(4)">取消推荐</a> -->
				<input onclick="isChange(1)" class = "public_btn bg1" style = "float:left; margin-left:6px;" type="submit" name="inquery" value = "商品上架"/>
				<input onclick="isChange(2)" class = "public_btn bg1" style = "float:left; margin-left:23px;" type="submit" name="inquery" value = "商品下架"/>
				<input onclick="isChange(3)" class = "public_btn bg1" style = "float:left; margin-left:23px;" type="submit" name="inquery" value = "设置推荐"/>
				<input onclick="isChange(4)" class = "public_btn bg1" style = "float:left; margin-left:23px;" type="submit" name="inquery" value = "取消推荐"/>
			</p>
			<div id="listDiv"></div>
		</section>
	</div>



	<!-- 本页私有js -->
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/common.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/base.js"></script>
	<script src="${ctx}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript">
		window.onload = function() {
			$('#searchForm').submit();
		}

		function isChange(type) {
			var showMsg = "商品上架"
			if (type == 2) {
				showMsg = "商品下架";
			} else if (type == 3) {
				showMsg = "设置推荐";
			} else if (type == 4) {
				showMsg = "取消推荐";
			}
			var idStr = "";
			var flag = "";
			var errMsg = "";
			$("input[name='pid']:checked").each(
					function() {
						var status = "";
						var showIndex = "";
						var isRecommend = "";
						$($(this).parent().parent().find("input:hidden")).each(
								function(j) {
									if (j == 0) {
										showIndex = this.value;
									} else if (j == 1){
										status = this.value;
									} else {
										isRecommend = this.value;
									}
								});
						if (type == 1 && status=="1") {
							errMsg = errMsg +showIndex+",";
						} else if (type == 2 && status=="2") {
							errMsg = errMsg +showIndex+",";
						} else if (type == 3 && isRecommend=="1" ) {
							errMsg = errMsg +showIndex+",";
						} else if (type == 4 && isRecommend=="0") {
							errMsg = errMsg +showIndex+",";
						}

						flag = flag + status + ";";
						idStr = idStr + this.value + ";";
			});
			
			
			if (errMsg != "") {
				errMsg =  errMsg.substring(0, errMsg.length-1);
				if (type == 1) {
					errMsg = "第"+errMsg+"行商品为已上架,不能重复上架!";
				} else if (type == 2) {
					errMsg = "第"+errMsg+"行商品为已下架,不能重复下架!";
				} else if (type == 3) {
					errMsg = "第"+errMsg+"行商品为已推荐,不能重复推荐!";
				} else if (type == 4) {
					errMsg = "第"+errMsg+"行商品为已取消推荐,不能重复取消!";
				}
				layer.msg(errMsg, {
					icon : 7
				});
				return false;
			}
			if (idStr == "") {
				layer.msg("请选择您要操作的商品!", {
					icon : 7
				});
				return false;
			}

			layer.open({
						type : 1,
						skin : 'layer-style',
						area : [ '440px', '250px' ],
						shadeClose : false, //点击遮罩关闭
						title : [ '修改产品' ],
						resize : false,
						scrollbar : false,
						content : '<section class = "content" style = "border:none;">'
								+ '<dl>'
								+ '<dt style = "text-align: center; margin-top: 20px;"><img src="${ctx}/assets/img/!.png"/></dt>'
								+ '<dd>'
								+ '<p style = "text-align: center; margin-top: 20px;">您是否确定'
								+ showMsg
								+ '产品?</p>'
								+ '</dd>'
								+ '</dl>'
								+ '</section>',
						btn : [ '确定', '取消' ],
						yes : function(index) {
							$
									.ajax({
										type : "post",
										url : "${ctx}/rest/product/changeProductStatus?idStr="
												+ idStr
												+ "&type="
												+ type
												+ "&flag=" + flag,
										cache : false,
										async : false, // 此处必须同步
										dataType : "json",
										success : function(xmlobj) {
											if (xmlobj.flag == 1) {
												layer.msg(showMsg + "成功！", {
													icon : 1
												});
												setTimeout(function() {
													$("#searchForm").submit();
												}, 1000);
											}
											if (xmlobj.flag == 0) {
												layer.msg(showMsg + "失败！！", {
													icon : 7
												});
												setTimeout(function() {
													$("#searchForm").submit();
												}, 1000);
											}
											layer.close(index);
										}
									});
						}
						,success: function (layero) {
						      var btn = layero.find('.layui-layer-btn');
						      btn.css('text-align', 'center');
						 }
					});

		}
		
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
					window.open("${ctx}/rest/product/exportProduct?"
							+ formSerialize);
		});
	</script>
</body>
</html>