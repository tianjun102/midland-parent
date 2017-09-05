<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="../layout/tablib.jsp"%>

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
<title>栏目管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="MobileOptimized" content="320">
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/assets/css/common.css">
<link rel="stylesheet" href="${ctx}/assets/css/easydropdown.css" />
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/scripts/jquery.easydropdown.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/scripts/bootstrap.min.js"></script>
<script src="${ctx}/assets/scripts/common.js"></script>
<script src="${ctx}/assets/scripts/layer.js" type="text/javascript"></script>
<script src="${ctx}/assets/scripts/base.js" type="text/javascript"></script>
<script type="text/javascript"
	src="${ctx}/assets/My97DatePicker/WdatePicker.js"></script>

</head>
<body>
	<div class="box">
		<section class="content">
			<form id="searchForm" action="${ctx}/rest/stock/searchProduct"
				method="post">
				<ul class="userinfo row">
					<li><span>商品编号：</span><input style="width: 150px;" type="text"
						name="codeStr"></li>
					<li><span>商品名称：</span><input style="width: 150px;" type="text"
						name="nameStr"></li>
					<li></li>
					<li><input class="public_btn bg1" type="submit" name="inquery"
						id="inquery" value="查询" /></li>
				</ul>
			</form>
			<table class="table table-bordered table-add" style="margin-top: 20px">
				<thead>
					<tr>
						<th style="width: 30%">商品编号</th>
						<th style="width: 30%">商品名称</th>
						<th style="width: 30%">商品分类</th>
						<th style="width: 10%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${result}" var="product" varStatus="p">
						<tr>
							<td><input type="hidden" name="Sid${p.index}"
								value="${product.productId }"> <input type="hidden"
								name="Sname${p.index}" value="${product.productName}" /> <input
								type="hidden" name="Scode${p.index}"
								value="${product.productCode}" /> ${product.productCode}</td>
							<td>${product.productName}</td>
							<td>${product.catName}</td>
							<td><a href="javaScript:addItems(${p.index})">添加</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>
	</div>


</body>
<script type="text/javascript">
	function addItems(i) {

		var content = "";
		parent.$("#showTable tr:not(:first)").each(function() {
			var prodId = $(this).find("td").find("input:hidden(first)").val();
			content = content + "["+prodId+"],";

		})

		var sid = $("input[name='Sid" + i + "']").val();
		var scode = $("input[name='Scode" + i + "']").val();
		var sname = $("input[name='Sname" + i + "']").val();
		var newContent = "["+sid+"]";
		if(content.indexOf(newContent)!=-1){
			layer.msg("商品已存在,不可重复添加!",{icon:7});
			
		}else{
			var index = parent.$("#showTable tr").length;
			var content = "<tr>"
							+"<td>"+scode+"</td>"
							+"<td>"+sname+"</td>"
							+"<td><input type='hidden' name='itemList["+(index-1)+"].prodId' value='"+sid+"' />"
								+"<input type='hidden' name='itemList["+(index-1)+"].id'/>"
								+"<input type='text'   name='itemList["+(index-1)+"].num' maxlength='8' onblur='sum()' />"
							+"</td>"
							+"<td><a class='delete_img' onclick='delItem(this)'  title='删除'></a></td>"
							+"</tr>";
			parent.$("#showTable").append(content);
			layer.msg("添加成功！",{icon:1});
			layer.close();
		}
		
	
	}
</script>
</html>