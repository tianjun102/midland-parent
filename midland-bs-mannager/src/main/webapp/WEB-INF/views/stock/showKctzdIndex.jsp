<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>智者汇 - 库存调整单</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <meta name="MobileOptimized" content="320">
    <link rel="stylesheet" href="${ctx }/assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css" />
	<link rel="stylesheet" href="${ctx }/assets/css/common.css">
</head>
	<body>
	
	<!--库存调整单界面-->
	<div class="box">
		<section class = "content">
			<p class = "detail-title">
				<span>库存调整单</span>
				<a class = "setup"  href="${ctx}/rest/stock/showInputKctzd"  target="contentF">新建调整单</a>
			</p>
			<form action="${ctx }/rest/stock/showKctzdList" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">	
				<li><span>调整单号：</span><input type="text" name="djbh" id="" placeholder="" /></li>
				<li><span>下单日期：</span>
					<input type="text" name="addTimeStart"  class="Wdate half" id="time1" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})" />
					<em class = "gang">-</em>
					<input type="text" name="addTimeEnd"   class="Wdate half" id="time2" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})" />
				</li>
				<li><span>业务日期：</span>
					<input  type="text" name="updateTimeStart" class="Wdate half" id="time3" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time4\')}'})" />
					<em  class = "gang">-</em>
					<input  type="text" name="updateTimeEnd" class="Wdate half" id="time4" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time3\')}'})"/>
				</li>
				
				<li>
					<span style = "float:left;">单据状态：</span>
					<select style = "float:right;" name="isComplete" id="" class="dropdown">
						<option value="" >全部</option>
					    <option value="0">未验收</option>
					    <option value="1">已验收</option>
					</select>
				</li>
				<li>
					<input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/>
					<input class = "public_btn bg1" type="reset" name="clear" id="clear" value = "清空条件"/>
				</li>
				
			</ul>
			</form>
			<div id="listDiv"></div>
		</section>
	</div>
	<!-- 本页私有js -->
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/common.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/base.js" ></script>
	<script src="${ctx}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript">
		window.onload = function(){
			$('#searchForm').submit();
		}
    </script>
	</body>
</html>