<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="${ctx }/assets/css/bootstrap.min.css">

<link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css" />
<link rel="stylesheet" href="${ctx }/assets/css/common.css">
	<style type="text/css">
		.content ul.userinfo li>span{
			float: left;
			display: inline-block;
			width: 90px;
			height: 38px;
			line-height: 38px;
			text-align: right;
			font-size: 14px;
			color: rgb( 102, 102, 102 );
		}
		.dropdown {
			height: 38px;
			line-height: 38px;
			width: 200px;
			display: inline-table;
			border-width: 1px !important;
			border-style: solid !important;
			border-color: rgb(219, 226, 230) !important;
			border-image: initial !important;
		}
	</style>
</head>
<body>
	
	
	<!--用户列表界面-->
	<div class="box"> 
		<section class = "content">
			<p class = "detail-title">
				<span>看房记录列表</span>
				<%--<a class = "setup"  target="contentF" onclick="addAppointment()">新增</a>--%>
			</p>
		<form action="${ctx }/rest/appoint/page" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<li><span>小区名：</span><input type="text" name="communityName" id="communityName" placeholder="请输入小区名" /></li>
				<li><span>预约人：</span><input type="text" name="nickName" id="nickName" placeholder="请输入预约人" /></li>
				<li><span>手机号码：</span><input type="text" name="phone" id="phone" placeholder="请输入手机号码" /></li>
				<li>
					<span>分类：</span>
					<select name="sellRent" id="sellRent" class="dropdown">
						<option value="">全部</option>
						<c:forEach items="${sellRents}" var="s" >
							<option value="${s.id}">
									${s.name}
							</option>
						</c:forEach>
					</select>
				</li>
				<li><span>状态：</span>
					<select name="status" id="status" class="dropdown">
						<option value="">全部</option>
						<c:forEach items="${statusList}" var="s1" >
							<option value="${s1.id}">
									${s1.name}
							</option>
						</c:forEach>
					</select>
				</li>
				<li><span>平台：</span>
					<select name="source" id="source" class="dropdown">
						<option value="">全部</option>
						<c:forEach items="${sources}" var="s1" >
							<option value="${s1.id}">
									${s1.name}
							</option>
						</c:forEach>
					</select>
				</li>
				<li><span>预约时间：</span><input class="Wdate half" id="time1"
										onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})"
										name="startTime" /> <em class = "gang">-</em><input
						class="Wdate half"
						onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})"
						id="time2" name="endTime" /></li>

				<li><input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/>
					<a href="#" onclick="export1()">导出</a>
				</li>
			</ul>
			</form>


			<div id="listDiv"></div>
		</section>
	</div>
	
	
	<script type="text/javascript">
        function export1(){
            var data = $("#searchForm").serialize();

            window.location.href="${ctx}/rest/appoint/export?"+data;
        }
        function addAppointment(){
            layer.open({
                type: 2,
                skin: 'layer-style',
                area: ['500px','700px'],
                shadeClose: false, //点击遮罩关闭
                title:['预约'],
                resize: false,
                scrollbar:false,
                content:['${ctx}/rest/appoint/to_add', 'no']
            });
        }
		 window.onload = function(){
             $('#searchForm').submit();
		}

	</script>
	<!-- 本页私有js -->
	
	
	</body>
</html>