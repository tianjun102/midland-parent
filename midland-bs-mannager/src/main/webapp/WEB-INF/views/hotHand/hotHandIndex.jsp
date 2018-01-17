<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="../indexJS.jsp" %>
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
			<a class = "setup"  target="contentF" onclick="toAddPage()">新增</a>
		</p>
		<form action="${ctx }/rest/hotHand/list" method="POST" id="searchForm"
			  onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<%@include file="../menu/area.jsp" %>
				<li><span>开发商：</span>
					<input type="text" name="developer" id="developer" placeholder="请输入开发商" /></li>
				</li>
				<li><span>地理位置：</span>
					<input type="text" name="position" id="position" placeholder="请输入地理位置" /></li>
				</li>
				<li><span>入伙时间：</span><input class="Wdate half" id="time1"
											 onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})"
											 name="startTime" /> <em class = "gang">-</em><input
						class="Wdate half"
						onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})"
						id="time2" name="endTime" /></li>
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
            area: ['100%','100%'],
            shadeClose: false, //点击遮罩关闭
            title:['新增'],
            resize: false,
            scrollbar:false,
            content:['${ctx}/rest/hotHand/to_add', 'yes']
        });
    }
    window.onload = function(){
        $('#searchForm').submit();
    }
</script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>