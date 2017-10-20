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


<div class="table-responsive m40" style="overflow-y:scroll;">
    <table class="table table-bordered table-add">
        <thead>
            <tr>
                <th style="width: 10%"><a href="#" onclick="checkall()" >全选</a> / <a href="#" onclick="delcheckall()" >全部取消</a></th>
                <th style="width: 5%">编号</th>
                <th style="width: 5%">类型</th>
				<th style="width: 20%">评论</th>
                <th style="width: 15%">评论时间</th>
				<th style="width: 8%">用户</th>
				<th style="width: 7%">状态</th>
				<th style="width: 6%">平台</th>
                <th style="width: 15%">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.items }">
                <c:forEach items="${requestScope.items }" var="item" varStatus="xh">
                    <tr>
						<input type="hidden" id="id" value="${item.id}"/>
                        <td><input type="checkbox" name="pid" value="${item.id}"></td>
                        <td>${xh.count}</td>
                        <td>
                            <c:if test="${item.type==0}">资讯</c:if>
                            <c:if test="${item.type==1}">委托</c:if>
                            <c:if test="${item.type==2}">预约</c:if></td>
						<td>${item.comment}</td>
                        <td>${item.commentTime}</td>
						<td>${item.user}</td>
						<td><c:if test="${item.status==0}">审核通过</c:if><c:if test="${item.status==1}">审核拒绝</c:if></td>
						<td><c:if test="${item.source==0}">网站</c:if><c:if test="${item.source==1}">微站</c:if></td>
						<td>
                            <a target="contentF" class="confirm_img" title="审核通过" onclick="editStatus(${item.id },0)"></a>
                            <a target="contentF" class="reset_img" title="审核拒绝" onclick="editStatus(${item.id },1)"></a>
                            <a target="contentF" class="delete_img" title="删除" onclick="delete1(${item.id })"></a>
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
            url: "${ctx}/rest/comment/update?id="+id+"&isDelete=1",
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

    function editStatus(id,status){
        $.ajax({
            type: "post",
            url: "${ctx}/rest/comment/update?id="+id+"&status="+status,
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

    function checkall(){
        $("input[name='pid']").each(function(){
            this.checked=true;
        });
    }



    function delcheckall(){
        $("input[name='pid']").each(function(){
            this.checked=false;
        });
    }

    function batchUpdate(status) {
        var ids = [];
        $("input[name='pid']").each(function(){
            if(this.checked){
                ids.push($(this).val());
            }
        });

        $.ajax({
            type: "post",
            url: "${ctx}/rest/comment/batchUpdate?ids="+ids+"&status="+status,
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state==0){
                    layer.msg("操作成功！", {icon: 1});
                    $('#searchForm').submit();
                }
            },
            error: function () {
                layer.msg("操作失败！", {icon: 2});
            }
        })
    }

    function batchDelete(status) {
        var ids = [];
        $("input[name='pid']").each(function(){
            if(this.checked){
                ids.push($(this).val());
            }
        });

        $.ajax({
            type: "post",
            url: "${ctx}/rest/comment/batchUpdate?ids="+ids+"&isDelete="+status,
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state==0){
                    layer.msg("操作成功！", {icon: 1});
                    $('#searchForm').submit();
                }
            },
            error: function () {
                layer.msg("操作失败！", {icon: 2});
            }
        })
    }

</script>
</body>
</html>