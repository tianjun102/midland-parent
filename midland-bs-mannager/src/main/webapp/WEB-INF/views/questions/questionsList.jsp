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
    <a target="contentF" onclick="deletelist()">删除</a>

    <table class="table table-bordered table-add">
        <thead>
        <tr>
            <th style=""></th>
            <th style="">问题主题</th>
            <th style="">来源</th>
            <th style="">提问时间</th>
            <th style="">提问人昵称</th>
            <th style="">提问人手机号</th>
            <th style="">审核人</th>
            <th style="">状态</th>
            <th style="">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.questions }">
                <c:forEach items="${requestScope.questions }" var="item"
                           varStatus="xh">
                    <tr>
                        <td><input name="checkbox" id="checkbox" type="checkbox" value="${item.id }"/></td>
                        <td>${item.questionsTitle }</td>
                        <td> <c:if test="${item.source ==0 }">网站</c:if>
                            <c:if test="${item.source ==1 }">微站</c:if></td>
                        <td>${item.questionTime }</td>
                        <td>${item.questionName }</td>
                        <td>${item.questionPhone }</td>
                        <td>${item.auditor }</td>

                        <td>
                            <c:if test="${item.status ==0}">未审核</c:if>
                            <c:if test="${item.status ==1}">审核通过</c:if>
                            <c:if test="${item.status ==2}">审核失败</c:if>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${item.status==0}">
                                    <a style="width: auto;" target="contentF" onclick="toAudit(${item.id })">审核</a>
                                </c:when>
                                <c:otherwise>
                                    <a style="width: auto;" target="contentF" href="${ctx}/rest/questions/to_view?id=${item.id}">查看问题</a>
                                </c:otherwise>
                            </c:choose>
                            <a style="width: auto;" target="contentF" href="${ctx}/rest/questions/to_repeat?id=${item.id}">回答</a>
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




    function deletelist(){
        var ids="";
        $("input[name='checkbox']:checkbox:checked").each(function(){
            ids+=","+$(this).val()
        })
        $.ajax({
            type: "post",
            url: "${ctx}/rest/questions/delete",
            async: false, // 此处必须同步
            dataType: "json",
            data: {"ids": ids},
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("删除成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        parent.location.reload();
                    }, 1000);

                } else {
                    layer.msg("删除失败！", {icon: 2});
                }
            },
            error: function () {
                layer.msg("删除失败！", {icon: 2});
            }

        });
    }


    function viewQuestion(id) {
        layer.open({
            type: 2,
            title: ['查看回答'],
            shade: 0.3,
            area: ['1000px', '700px'],
            content: ['${ctx}/rest/questions/to_view?id=' + id , 'no']
        });
    }
function toAudit(id) {
        layer.open({
            type: 2,
            title: ['审核'],
            shade: 0.3,
            area: ['1000px', '700px'],
            content: ['${ctx}/rest/questions/toAudit?id=' + id , 'no']
        });
    }
</script>
</body>
</html>