<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
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
				<span>简历管理</span>
			</p>
		<form action="${ctx }/rest/resumeManager/list" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<li><span>岗位：</span>
					<input type="text" name="post" id="post" placeholder="请输入岗位" /></li>
				</li>
				<li><span>姓名：</span>
					<input type="text" name="deliverer" id="deliverer" placeholder="请输入岗位" /></li>
				</li>
				<li><span>联系方式：</span>
					<input type="text" name="phone" id="phone" placeholder="请输入岗位" /></li>
				</li>
				<li>
					<span>上传时间：</span>
					<input type="text" name="startTime" class="Wdate half" id="time3" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time4\')}'})" />
					<em class = "gang">-</em>
					<input type="text" name="endTime" class="Wdate half" id="time4" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time3\')}'})"/>
				</li>
				<li><span>工作地点：</span>
					<select name="cityId" id="cityId" class="dropdown">
						<option value="" >全部</option>
						<c:forEach items="${cityList}" var="item">
							<option value="${item.id}" >${item.name}</option>
						</c:forEach>
					</select>
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
                content:['${ctx}/rest/resumeManager/to_add', 'no']
            });
        }
		 window.onload = function(){
             $('#searchForm').submit();
		}
	</script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>