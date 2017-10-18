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
	.dropdown {
		width: 150px!important;
	}
</style>
</head>
<body>
	
	
	<!--列表界面-->
	<div class="box"> 
		<section class = "content">
			<p class = "detail-title">
				<span>招聘管理</span>
				<a class = "setup"  target="contentF" href="${ctx}/rest/recruitManager/to_add">新增</a>
			</p>
		<form action="${ctx }/rest/recruitManager/list" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<li><span>城市：</span>
					<select name="cityId" id="cityId" style="height: 38px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
						<option value="" >全部</option>
						<c:forEach items="${cityList}" var="item">
						<option value="${item.id}" >${item.name}</option>
						</c:forEach>
					</select>
				</li>
				<li><span>类别：</span>
					<select name="type" id="type" class="dropdown">
						<option value="" >全部</option>
							<option value="1" >校招</option>.
						    <option value="2" >社招</option>
					</select>
				</li>
				<li><span>发布状态：</span>
					<select name="releaseStatus" id="releaseStatus" class="dropdown">
						<option value="" >全部</option>
						<option value="0" >已发布</option>.
						<option value="1" >未发布</option>
					</select>
				</li>
				<li><span>岗位：</span>
					<input style="width: 150px;" type="text" name="post" id="post" placeholder="请输入岗位" />
				</li>
				<li>
					<span>发布时间：</span><input class="Wdate half" id="time1" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})" name="startTime"/> <em class="gang">-</em><input class="Wdate half" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})" id="time2" name="endTime"/>
				</li>
				<li>
					<input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/>
				</li>
			</ul>
			</form>
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
                content:['${ctx}/rest/recruitManager/to_add', 'no']
            });
        }
		 window.onload = function(){
             $('#searchForm').submit();
		}
	</script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>