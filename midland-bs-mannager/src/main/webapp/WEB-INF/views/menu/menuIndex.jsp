<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="${ctx }/assets/css/bootstrap.min.css">

<link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css" />
<link rel="stylesheet" href="${ctx }/assets/css/common.css">

</head>
<body>
	
	
	<!--委托列表界面-->
	<div class="box"> 
		<section class = "content">
			<p class = "detail-title">
				<span>菜单管理列表</span>
				<a class = "setup"  target="contentF" onclick="addMenu()">新增</a>
			</p>
		<form action="${ctx }/rest/menu/list" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<li>
					<%@include file="../layout/area.jsp" %>
				</li>
				<li><span>菜单名称：</span>
					<input type="text" name="menuName" id="menuName" placeholder="请输入菜单名称" /></li>
				</li>
				<li><span>来源：</span>
					<select name="source" id="source" class="dropdown">
                        <option value="" >请选择</option>
						<c:forEach items="${sources}" var="s">
							<option value="${s.id}" <c:if test="${s.id == item.source}">selected="selected"</c:if>>
									${s.name}
							</option>
						</c:forEach>
					</select>
				</li>

				<li><input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/></li>
			</ul>
			</form>
			<div id="listDiv"></div>
		</section>
	</div>
	
	
	<script type="text/javascript">

		function addMenu() {
            layer.open({
                type: 2,
                skin: 'layer-style',
                area: ['500px','700px'],
                shadeClose: false, //点击遮罩关闭
                title:['新增菜单'],
                resize: false,
                scrollbar:false,
                content:['${ctx}/rest/menu/to_add', 'no']
            });
        }

		 window.onload = function(){
             $('#searchForm').submit();
		}
	</script>
	<!-- 本页私有js -->
	
	
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/common.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/base.js" ></script>
	<script src="${ctx}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</body>
</html>