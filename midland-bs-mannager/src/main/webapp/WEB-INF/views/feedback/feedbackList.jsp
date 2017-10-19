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
                <th style="width: 8%">编号</th>
				<th style="width: 8%">用户昵称</th>
                <th style="width: 8%">反馈类型</th>
				<th style="width: 8%">联系方式</th>
				<th style="width: 8%">反馈内容</th>
				<th style="width: 8%">反馈时间</th>
                <th style="width: 8%">平台</th>
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
						<td>${item.nickName}</td>
                        <td>
                            <c:if test="${item.type==0}">咨询</c:if>
                            <c:if test="${item.type==1}">建议</c:if>
                            <c:if test="${item.type==2}">投诉</c:if>
                            <c:if test="${item.type==3}">其它</c:if>
                        </td>
						<td>${item.phone}</td>
						<td>${item.feedbackContent}</td>
						<td>${item.addTime}</td>
                        <td><c:if test="${item.source==0}">网站</c:if><c:if test="${item.source==1}">微站</c:if></td>
                        <td>
                            <a class="edit_img" target="contentF" onclick="to_edit(${item.id })"></a>
                            <a class="delete_img" target="contentF" onclick="delete1(${item.id })"></a>
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
            url: "${ctx}/rest/feedback/update?id="+id+"&isDelete=1",
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
            content: ['${ctx}/rest/feedback/to_update?id='+id,'no']
        });
    }


</script>
</body>
</html>