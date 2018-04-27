<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style>

        td {

            white-space:nowrap;overflow:hidden;text-overflow: ellipsis;

        }

    </style>
</head>
<body>


<div class="table-responsive m40">
    <table style="table-layout: fixed;" class="table table-bordered table-add">
        <thead>
            <tr>
                <th style="width: 3%">编号</th>
                <th style="width: 8%">城市</th>
				<th style="width: 8%">平台</th>
                <th style="width: 8%">模块</th>
                <th style="width: 8%">子模块</th>
				<th style="width: 8%">title</th>
				<th style="width: 8%">keywords</th>
				<th style="width: 8%">description</th>

                <th style="width: 20%">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.items }">
                <c:forEach items="${requestScope.items }" var="item" varStatus="xh">
                    <tr>
                        <td>${xh.count }</td>
						<input type="hidden" id="id" value="${item.id}"/>
						<td>${item.cityName}</td>
                        <td><c:forEach items="${sources}" var="s">
                            <c:if test="${item.source == s.id}">${s.name}</c:if>
                        </c:forEach></td>
                        <td>${item.modeName}</td>
                        <td>${item.secondModeName}</td>
						<td title="${item.websiteTitle}">${item.websiteTitle}</td>
						<td title="${item.websiteKeyWords}">${item.websiteKeyWords}</td>
						<td title="${item.websiteDescription}">${item.websiteDescription}</td>
						<td>
                            <c:if test="${item.isDelete==0}">
                                <a target="contentF" onclick="deleteOrRecover(${item.id },1)" class="delete_img"></a>
                            </c:if>
                            <c:if test="${item.isDelete==1}">
                                <a target="contentF" class="recove_img" onclick="deleteOrRecover(${item.id },0)"></a>
                            </c:if>
                            <a target="contentF" class="edit_img"  onclick="to_edit(${item.id })"></a>
                            <a target="contentF" title="上移" class="up_img"
                               onclick="sort(${item.id },${item.orderBy},1)"></a>
                            <a target="contentF" title="下移" class="down_img"
                               onclick="sort(${item.id },${item.orderBy},2)"></a>
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

    function deleteOrRecover(id,value){
        debugger;
        $.ajax({
            type: "post",
            url: "${ctx}/rest/meta/update?id="+id+"&isDelete="+value,
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
            area: ['100%', '100%'],
            content: ['${ctx}/rest/meta/to_update?id='+id,'no']
        });
    }

    //排序
    function sort(id, orderById, sort) {
        var data = $("#searchForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/meta/sort?sort=" + sort + "&orderBy=" + orderById + "&id=" + id,
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