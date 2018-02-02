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
				<th style="width: 8%">类型</th>
				<th style="width: 8%">图片</th>
				<th style="width: 8%">删除状态</th>
				<th style="width: 8%">显示状态</th>
				<th style="width: 8%">图片描述</th>
				<th style="width: 8%">创建时间</th>
                <th style="width: 10%">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.items }">
                <c:forEach items="${requestScope.items }" var="item" varStatus="xh">
                    <tr>
						<input type="hidden" id="id" value="${item.id}"/>
						<td><c:if test="${item.type ==0}">实景图</c:if>
                            <c:if test="${item.type ==1}">户型图</c:if></td>
						<td><img src="${item.imgUrl}" alt=""  style="width:40px;height:40px" ></td>
						<td><c:if test="${item.isDelete ==0}">正常</c:if>
                            <c:if test="${item.isDelete ==1}">删除</c:if></td>
						<td><c:if test="${item.isShow ==0}">显示</c:if>
                            <c:if test="${item.isShow ==1}">隐藏</c:if></td>
						<td>${item.description}</td>
						<td>${item.createTime}</td>
						<td>
                            <a target="contentF" onclick="to_edit(${item.id })">编辑</a>
                            <a target="contentF" onclick="delete1(${item.id })">删除</a>
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
    //排序
    function sort(id, orderById, sort) {
        var data = $("#searchForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/communityAlbum/sort?sort=" + sort + "&orderBy=" + orderById + "&id=" + id,
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
            url: "${ctx}/rest/communityAlbum/update?id="+id+"&isDelete=1",
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
            area: ['500px', '500px'],
            content: ['${ctx}/rest/communityAlbum/to_update?id='+id,'no']
        });
    }


</script>
</body>
</html>