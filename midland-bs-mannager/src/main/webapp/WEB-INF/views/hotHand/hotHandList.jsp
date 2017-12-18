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
			<th style="width: 8%">物业座数</th>
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
						<td>${item.propertyNum}</td>
						<td>${item.propertyAddress}</td>
						<td>${item.developer}</td>
						<td>${item.propertyManagement}</td>
						<td>${item.position}</td>
						<td>
							<a target="contentF" href="${ctx}/rest/communityAlbum/index?hotHandId=${item.id}">小区图片</a>
							<a target="contentF" href="${ctx}/rest/layoutMap/index?hotHandId=${item.id}">主推户型</a>
							<a target="contentF" class="arrange_img" title="重新分配经纪人"
							   onclick="toRedistribute(${item.id })"></a>
							<a target="contentF"  class="edit_img" href="${ctx}/rest/hotHand/to_update?id=${item.id}"></a>
							<a target="contentF" class="delete_img" onclick="delete1(${item.id })"></a>
							<a target="contentF" title="上移" class="up_img"
							   onclick="sort(${item.id },${item.orderBy},2)"></a>
							<a target="contentF" title="下移" class="down_img"
							   onclick="sort(${item.id },${item.orderBy},1)"></a>
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
    function toRedistribute(id) {
        //重新分配经纪人时，保存的地址
        var pageNo = ${paginator.page};
        var pageSize = ${paginator.limit};
        var parame1 = "&pageNo="+pageNo+"&pageSize="+pageSize;
        var url = "${ctx}/rest/hotHand/reset_agent";
        var indexUrl="${ctx}/rest/hotHand/index";
        window.open("${ctx}/rest/public/toRedistribute?id=" + id + "&url=" + url+"&indexUrl="+indexUrl+parame1, "contentF");
        <%--layer.open({--%>
        <%--type: 2,--%>
        <%--title: ['重新分配经纪人'],--%>
        <%--shade: 0.3,--%>
        <%--area: ['1000px',  '500px'],--%>
        <%--content: ['${ctx}/rest/public/toRedistribute?id=' + id + "&url=" + url, 'no']--%>
        <%--});--%>
    }
    //排序
    function sort(id, orderById, sort) {
        var data = $("#searchForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/hotHand/sort?sort=" + sort + "&orderBy=" + orderById + "&id=" + id,
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    $('#searchForm').submit();
                } else {
                    layer.msg("操作频繁！", {icon: 2});
                }
            },
            error: function () {
                layer.msg("操作失败！", {icon: 2});
            }
        })
    }



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