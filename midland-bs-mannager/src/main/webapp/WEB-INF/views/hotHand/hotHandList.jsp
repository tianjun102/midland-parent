<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>


<div class="table-responsive m40">
    <table class="table table-bordered table-add">
        <thead>
            <tr>
				<th style="width: 8%">price</th>
				<th style="width: 8%">intoTime</th>
				<th style="width: 8%">managerCosts</th>
				<th style="width: 8%">unitTotal</th>
				<th style="width: 8%">landArea</th>
				<th style="width: 8%">parkingNum</th>
				<th style="width: 8%">buildingType</th>
				<th style="width: 8%">propertyAddress</th>
				<th style="width: 8%">developer</th>
				<th style="width: 8%">buildingAddress</th>
				<th style="width: 8%">propertyManagement</th>
				<th style="width: 8%">propertyDesc</th>
				<th style="width: 8%">propertyAdvantages</th>
				<th style="width: 8%">position</th>
				<th style="width: 8%">supporting</th>
				<th style="width: 8%">houseName</th>
				<th style="width: 8%">imgUrl</th>
				<th style="width: 8%">agentName</th>
				<th style="width: 8%">agentId</th>
				<th style="width: 8%">createTime</th>
                <th style="width: 10%">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.items }">
                <c:forEach items="${requestScope.items }" var="item" varStatus="xh">
                    <tr>
						<input type="hidden" id="id" value="${item.id}"/>
						<td>${item.price}</td>
						<td>${item.intoTime}</td>
						<td>${item.managerCosts}</td>
						<td>${item.unitTotal}</td>
						<td>${item.landArea}</td>
						<td>${item.parkingNum}</td>
						<td>${item.buildingType}</td>
						<td>${item.propertyAddress}</td>
						<td>${item.developer}</td>
						<td>${item.buildingAddress}</td>
						<td>${item.propertyManagement}</td>
						<td>${item.propertyDesc}</td>
						<td>${item.propertyAdvantages}</td>
						<td>${item.position}</td>
						<td>${item.supporting}</td>
						<td>${item.houseName}</td>
						<td>${item.imgUrl}</td>
						<td>${item.agentName}</td>
						<td>${item.agentId}</td>
						<td>${item.createTime}</td>
						<td>
                            <a target="contentF" onclick="to_edit(${item.id })">编辑</a>
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