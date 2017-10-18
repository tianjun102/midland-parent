<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	
	
	<!--列表界面-->
	<div class="box"> 
		<section class = "content">
			<p class = "detail-title">
				<%--<a class = "setup"  target="contentF" onclick="toAddPage()">新增</a>--%>
			</p>
		<form action="${ctx }/rest/liaisonRecord/list" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<li><span>姓名：</span>
					<input type="text" name="name" id="name" placeholder="请输入姓名" /></li>
				</li>
				<li><span>电话：</span>
					<input type="text" name="phone" id="phone" placeholder="请输入电话" /></li>
				</li>
				<li><span>邮箱：</span>
					<input type="text" name="email" id="email" placeholder="请输入邮箱" /></li>
				</li>
				<li><span>主旨：</span>
					<select name="category" id="category" class="dropdown">
						<option value="" >全部</option>
						<c:forEach items="${categorys}" var="item">
						<option value="${item.id}" >${item.name}</option>
						</c:forEach>
					</select>
				</li>
				<li><span>已联系：</span>
					<select name="isOntact" id="isOntact" class="dropdown">
						<option value="" >全部</option>
						<option value="0" >否</option>
						<option value="1" >是</option>
					</select>
				</li>
				<li><span>时间：</span><input class="Wdate half" id="time1"
											onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})"
											name="startTime" /> <em class = "gang">-</em><input
						class="Wdate half"
						onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})"
						id="time2" name="endTime" /></li>
				<li>
					<input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/>
					<a class="left" onclick="cantactSetting()" >联络人设置</a>
				</li>
			</ul>
			</form>
			<a href="#" onclick="exportLiaison()">导出</a>
			<div id="listDiv"></div>
		</section>
	</div>
	
	
	<script type="text/javascript">

		function cantactSetting() {
            window.open("${ctx}/rest/liaisonRecordEmail/index","contentF");
        }

        function exportLiaison(){
            var data = $("#searchForm").serialize();

            window.location.href="${ctx}/rest/liaisonRecord/export?"+data;
        }

        function toAddPage(){
            layer.open({
                type: 2,
                skin: 'layer-style',
                area: ['500px','700px'],
                shadeClose: false, //点击遮罩关闭
                title:['新增'],
                resize: false,
                scrollbar:false,
                content:['${ctx}/rest/liaisonRecord/to_add', 'no']
            });
        }
		 window.onload = function(){
             $('#searchForm').submit();
		}
	</script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>