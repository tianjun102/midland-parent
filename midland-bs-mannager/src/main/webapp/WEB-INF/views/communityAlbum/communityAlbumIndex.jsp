<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
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
				<a class = "setup"  target="contentF" href="${ctx}/rest/communityAlbum/to_add?hotHandId=${item.hotHandId}">新增</a>
			</p>
		<form action="${ctx }/rest/communityAlbum/list" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<input type="hidden" name="hotHandId" id="hotHandId" value="${item.hotHandId}"/>
				<li class="col-md-6"><span>类型：</span>
					<select name="type" id="type" class="dropdown">
						<option value="" >全部</option>
						<option value="0" <c:if test="${item.type == 0}">selected</c:if> >实景图</option>
						<option value="1" <c:if test="${item.type == 1}">selected</c:if> >户型图</option>
					</select>
				</li>
				<li>
					<input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/>
					<a  style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;" class = "public_btn bg1"  href="${ctx}/rest/hotHand/index" target="contentF">返回</a></li>
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
                area: ['500px','500px'],
                shadeClose: false, //点击遮罩关闭
                title:['新增'],
                resize: false,
                scrollbar:false,
                content:['${ctx}/rest/communityAlbum/to_add', 'no']
            });
        }
		 window.onload = function(){
             $('#searchForm').submit();
		}
	</script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>