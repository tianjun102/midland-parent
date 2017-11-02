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


<div class="table-responsive m40" id='table-cont'>
    <table class="table table-bordered table-add">
        <thead>
        <tr>
            <th style="width: 8%"><a href="#" onclick="checkall()">全选</a> / <a href="#" onclick="delcheckall()">取消</a>
            </th>
            <th style="width: 8%">类型id</th>
            <th style="width: 8%">类型名称</th>
            <th style="width: 8%">父类型名称</th>
            <th style="width: 8%">城市</th>
            <th style="width: 8%">删除状态</th>
            <th style="width: 10%">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.items }">
                <c:forEach items="${requestScope.items }" var="item" varStatus="xh">
                    <tr>
                        <td><input type="checkbox" name="pid" value="${item.id}"></td>
                        <input type="hidden" id="id" value="${item.id}"/>
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <td>${item.parentName}</td>
                        <td>${item.cityName}</td>
                        <td>
                            <c:forEach items="${isDeletes}" var="s1">
                                <c:if test="${s1.id==item.isDelete}">${s1.name}</c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <a target="contentF" onclick="to_edit(${item.id })" class="edit_img"></a>
                            <c:if test="${item.isDelete==0}">
                                <a target="contentF" onclick="deleteOrRecover(${item.id },1)" class="delete_img"></a>
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
    $(function () {
        var headIndex = $("#headIndex").height();
        $("#table-cont").css({maxHeight: allHeight - headIndex - 100 - 17});
        var tableCont = document.querySelector('#table-cont');

        /**
         * scroll handle
         * @param {event} e -- scroll event
         */
        function scrollHandle(e) {
            var scrollTop = this.scrollTop;
            this.querySelector('thead').style.transform = 'translateY(' + scrollTop + 'px)';
        }

        tableCont.addEventListener('scroll', scrollHandle);
    })

    function checkall() {
        $("input[name='pid']").each(function () {
            this.checked = true;
        });
    }

    function batchDelete(status) {
        var ids = [];
        $("input[name='pid']").each(function () {
            if (this.checked) {
                ids.push($(this).val());
            }
        });

        $.ajax({
            type: "post",
            url: "${ctx}/rest/menuType/batchUpdate?ids=" + ids + "&isDelete=" + status,
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state == 0) {
                    layer.msg("操作成功！", {icon: 1});
                    $('#searchForm').submit();
                }
            },
            error: function () {
                layer.msg("操作失败！", {icon: 2});
            }
        })
    }

    function delcheckall() {
        $("input[name='pid']").each(function () {
            this.checked = false;
        });
    }

    function deleteOrRecover(id, flag) {
        $.ajax({
            type: "post",
            url: "${ctx}/rest/menuType/update?id=" + id + "&isDelete=" + flag,
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state == 0) {
                    $('#searchForm').submit();
                }
            },
            error: function () {
                layer.msg("操作失败！", {icon: 2});
            }
        })
    }

    function to_edit(id) {
        layer.open({
            type: 2,
            title: ['修改'],
            shade: 0.3,
            area: ['500px', '300px'],
            content: ['${ctx}/rest/menuType/to_update?id=' + id, 'no']
        });
    }


</script>
</body>
</html>