<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.content ul.userinfo li>span {
		float: left;
		display: inline-block;
		width: 90px;
		height: 38px;
		line-height: 38px;
		text-align: right;
		font-size: 14px;
		color: rgb( 102, 102, 102 );
	}
	.table-add tr td a {
		display: inline-block;
		width: 35px;
		height: 20px;
		margin: 0 5px;
		background-size: contain!important;
	}
	.content ul.userinfo li:not(:last-child) input {
		float: left;
		width: 175px;
		height: 38px;
		line-height: 38px;
		border: 1px solid #dbe2e6;
		border-radius: 4px;
		text-indent: 10px;
		outline-color: #0099e0;
	}
</style>
</head>
<body>
	
	
	<!--列表界面-->
	<div class="box"> 
		<section class = "content">
			<p class = "detail-title">
				<span>行情信息管理>>留言管理</span>
				<%--<a class = "setup"  target="contentF" onclick="toAddPage()">新增</a>--%>
			</p>
		<form action="${ctx }/rest/leaveMsg/list" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<li><span>用户名称：</span>
					<input type="text" name="userName" id="userName" placeholder="请输入用户名称" /></li>
				</li>
				<li><span>手机号码：</span>
					<input type="text" name="phone" id="phone" placeholder="请输入手机号码" /></li>
				</li>
				<li><span>邮箱：</span>
					<input type="text" name="email" id="email" placeholder="请输入邮箱" /></li>
				</li>
				<li><span>留言时间：</span><input class="Wdate half" id="time1" style="width:140;"
										  onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})"
										  name="startTime" /> <em class = "gang">-</em><input
						class="Wdate half"
						onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})"
						id="time2"  style="width:140;" name="endTime" /></li>
				<li>
				<li>
					<input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/>
				</li>
			</ul>
			</form>
			<input style="margin-left: 20px;width: 70px;height: 30px;line-height: 30px!important;margin-top: 10px;" onclick="batchDelete(1)" class = "public_btn bg1" type="submit"  value = "批量删除"/>
			<input style="margin-left: 20px;width: 70px;height: 30px;line-height: 30px!important;" onclick="batchDelete(0)" class = "public_btn bg1" type="submit"  value = "批量恢复"/>
			<div id="listDiv"></div>
		</section>
	</div>
	
	
	<script type="text/javascript">
        function toAddPage(){
            layer.open({
                type: 2,
                skin: 'layer-style',
                area: ['500px','700px'],
                shadeClose: false, //点击遮罩关闭
                title:['新增'],
                resize: false,
                scrollbar:false,
                content:['${ctx}/rest/leaveMsg/to_add', 'no']
            });
        }
		 window.onload = function(){
             $('#searchForm').submit();
		}
	</script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>