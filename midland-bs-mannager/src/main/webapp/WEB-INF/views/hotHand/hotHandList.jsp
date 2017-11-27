<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		.table-add tr td a {
			display: inline-block;
			width: 50px;
			height: 20px;
			margin: 0 5px;
			background-size: contain!important;
		}
	</style>
</head>
<body>


<div class="table-responsive m40">
	<table class="table table-bordered table-add">
		<thead>
		<tr>
			<th style="width: 8%">建筑类型</th>
			<th style="width: 8%">物业座数</th>
			<th style="width: 8%">均价</th>
			<th style="width: 8%">入伙日期</th>
			<th style="width: 8%">管理费用</th>
			<th style="width: 8%">单位总数</th>
			<th style="width: 8%">装修标准</th>
			<th style="width: 8%">物业地址</th>
			<th style="width: 8%">开发商</th>
			<th style="width: 8%">物业管理</th>
			<th style="width: 8%">地理位置</th>
			<th style="width: 10%">操作</th>
		</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${!empty requestScope.items }">
				<c:forEach items="${requestScope.items }" var="item" varStatus="xh">
					<tr>
						<input type="hidden" id="id" value="${item.id}"/>
						<td>${item.buildingType}</td>
						<td>${item.unitTotal}</td>
						<td>${item.price}</td>
						<td>${item.intoTime}</td>
						<td>${item.managerCosts}</td>
						<td>${item.landArea}</td>
						<td>${item.parkingNum}</td>
						<td>${item.propertyAddress}</td>
						<td>${item.developer}</td>
						<td>${item.propertyManagement}</td>
						<td>${item.position}</td>
						<td>
							<a target="contentF" href="${ctx}/rest/communityAlbum/index?hotHandId=${item.id}">小区图片</a>
							<a target="contentF" href="${ctx}/rest/layoutMap/index?hotHandId=${item.id}">主推户型</a>
							<a target="contentF" href="${ctx}/rest/hotHand/to_update?id=${item.id}">编辑</a>
							<a target="contentF" onclick="delete1(${item.id })">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="14">没有找到数据!</td>
				</tr>
			</c:otherwise>
		</c:choose>
		</tbody>
	</table>
</div>
<!-- 分页 -->
<c:if test="${!empty paginator}">
	<c:set var="paginator" value="${paginator}"/>
	<c:set var="target" value="listDiv"/>
	<%@include file="../layout/pagination.jsp" %>
</c:if>

<script type="text/javascript">

    function delete1(id){
        $.ajax({
            type: "post",
            url: "${ctx}/rest/hotHand/update?id="+id+"&isDelete=1",
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state==0){
                    $('#searchForm').submit();
                }
            },
            error: function () {
                layer.msg("操作失败！", {icon: 2});
            }
        })
    }

    function to_edit(id){
        layer.open({
            type: 2,
            title: ['修改'],
            shade: 0.3,
            area: ['500px', '700px'],
            content: ['${ctx}/rest/hotHand/to_update?id='+id,'no']
        });
    }


</script>
</body>
</html>