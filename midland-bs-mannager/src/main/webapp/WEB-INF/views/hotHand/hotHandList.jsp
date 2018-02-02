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
			<th style="width: 8%">城市</th>
			<th style="width: 8%">房源名</th>
			<th style="width: 8%">建筑类型</th>
			<th style="width: 8%">物业座数</th>
			<th style="width: 8%">均价</th>
			<th style="width: 8%">入伙日期</th>
			<th style="width: 8%">管理费用</th>
			<th style="width: 8%">单位总数</th>
			<th style="width: 8%">物业座数</th>
			<th style="width: 8%">装修标准</th>
			<th style="width: 8%">状态</th>
			<th style="width: 8%">经纪人</th>
			<th style="width: 10%">操作</th>
		</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${!empty requestScope.items }">
				<c:forEach items="${requestScope.items }" var="item" varStatus="xh">
					<tr>
						<input type="hidden" id="id" value="${item.id}"/>
						<td>${item.cityName}</td>
						<td>${item.houseName}</td>
						<td>${item.buildingType}</td>
						<td>${item.unitTotal}</td>
						<td>${item.price}</td>
						<td>${item.intoTime}</td>
						<td>${item.managerCosts}</td>
						<td>${item.landArea}</td>
						<td>${item.parkingNum}</td>
						<td><c:if test="${item.isDelete==0}">正常</c:if>
							<c:if test="${item.isDelete==1}">删除</c:if></td>
						<td>${item.agentName}</td>
						<td>
							<c:forEach items="${decorations}" var="s">
								<c:if test="${s.id==item.decoration}">${s.name}</c:if>
							</c:forEach>
						</td>
						<td>
							<c:if test="${item.isDelete==0}">
								<a target="contentF" onclick="commu('${item.id}')" >小区图片</a>
								<a target="contentF" onclick="layoutMap('${item.id}')">主推户型</a>
								<a target="contentF" class="arrange_img" title="重新分配经纪人"
								   onclick="toRedistribute(${item.id })"></a>
								<a target="contentF"  class="edit_img" onclick="toUpdatePage(${item.id })"></a>
								<a target="contentF" onclick="deleteOrRecover(${item.id },1)" class="delete_img"></a>
								<a target="contentF" title="上移" class="up_img"
								   onclick="sort(${item.id },${item.orderBy},1)"></a>
								<a target="contentF" title="下移" class="down_img"
								   onclick="sort(${item.id },${item.orderBy},2)"></a>
							</c:if>
							<c:if test="${item.isDelete==1}">
								<a target="contentF" class="recove_img" onclick="deleteOrRecover(${item.id },0)"></a>
							</c:if>

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
	function toUpdatePage(id) {
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['100%','100%'],
            shadeClose: false, //点击遮罩关闭
            title:[''],
            resize: false,
            scrollbar:false,
            content:['${ctx}/rest/hotHand/to_update?id='+ id, 'yes']
        });

    }
    function commu(id) {
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['100%','100%'],
            shadeClose: false, //点击遮罩关闭
            title:[''],
            resize: false,
            scrollbar:false,
            content:['${ctx}/rest/communityAlbum/index?hotHandId=' + id, 'yes']
        });
    }
    function layoutMap(id) {
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['100%','100%'],
            shadeClose: false, //点击遮罩关闭
            title:[''],
            resize: false,
            scrollbar:false,
            content:['${ctx}/rest/layoutMap/index?hotHandId=' + id , 'yes']
        });
    }
    function toRedistribute(id) {
        //重新分配经纪人时，保存的地址
        var url = "${ctx}/rest/hotHand/reset_agent";
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['100%','100%'],
            shadeClose: false, //点击遮罩关闭
            title:['新增'],
            resize: false,
            scrollbar:false,
            content:['${ctx}/rest/public/toRedistribute?id=' + id + "&url=" + url, 'yes']
        });
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
    function deleteOrRecover(id, flag) {
        $.ajax({
            type: "post",
            url: "${ctx}/rest/hotHand/update?id=" + id + "&isDelete=" + flag,
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state == 0) {
                    $('#searchForm').submit();
                }
            },
            error: function (data) {
                if (data.responseText != null) {
                    layer.msg(data.responseText, {icon: 2});
                } else {
                    layer.msg("操作失败！", {icon: 2});
                }
            }
        })
    }

</script>
</body>
</html>