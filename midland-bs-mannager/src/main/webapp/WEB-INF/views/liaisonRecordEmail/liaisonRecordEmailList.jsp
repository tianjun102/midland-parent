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
            width: 35px;
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
                <th style="width: 8%">编号</th>
				<th style="width: 8%">城市</th>
                <th style="width: 8%">联络人</th>
				<th style="width: 8%">邮箱</th>
				<th style="width: 8%">手机</th>
                <th style="width: 8%">主指</th>
                <th style="width: 10%">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.items }">
                <c:forEach items="${requestScope.items }" var="item" varStatus="xh">
                    <tr>
						<input type="hidden" id="id" value="${item.id}"/>
                        <td>${xh.count}</td>
                        <td>${item.cityName}</td>
                        <td>${item.contactName}</td>
                        <td>${item.email}</td>
                        <td>${item.phone}</td>
                        <td> <c:forEach items="${categorys}" var="s">
                            <c:if test="${item.category == s.id}">${s.name}</c:if>
                        </c:forEach></td>
						<td>
                            <a target="contentF" class="edit_img" onclick="to_edit(${item.id })"></a>
                            <c:if test="${item.isDelete==0}">
                                <a target="contentF" onclick="delete1(${item.id },1)" class="delete_img"></a>
                            </c:if>
                            <c:if test="${item.isDelete==1}">
                                <a target="contentF" class="recove_img" title="恢复" onclick="delete1(${item.id },0)"></a>
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

    function delete1(id,isDelete){
        var msg = "您确定要删除当前分类吗？";
        if(isDelete==0){
            msg = "您确定要恢复当前分类吗？"
        }
        $.ajax({
            type: "post",
            url: "${ctx}/rest/liaisonRecordEmail/update?id="+id+"&isDelete="+isDelete,
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
            content: ['${ctx}/rest/liaisonRecordEmail/to_update?id='+id,'yes']
        });
    }


</script>
</body>
</html>