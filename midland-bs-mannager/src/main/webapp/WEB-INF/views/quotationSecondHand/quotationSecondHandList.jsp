<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        .table-add tr td a.house-detail {
            width: auto;
            height: auto;
            padding: 2px 3px;
            background-color: #00aeee;
            border-radius: 2px;
            color: #fff;
        }
        .table-add tr td a {
            display: inline-block;
            width: 38px;!important;
            height: 20px;
            margin: 0 5px;
            background-size: contain!important;
        }
        td
        {
            white-space: nowrap;
        }
        th
        {
            white-space: nowrap;
        }
    </style>
</head>
<body>
<div class="table-responsive m40"  style="max-height: 480px;overflow: auto;"  id='table-cont'>
<table class="table table-bordered table-add">
    <thead>
    <tr>
        <th style="width: 8%"><a href="#" onclick="checkall()" >全选</a> / <a href="#" onclick="delcheckall()" >取消</a></th>
        <th style="width: 8%">编号</th>
        <th style="width: 8%">城市</th>
        <th style="width: 8%">区域</th>
        <th style="width: 8%">类型</th>
        <th style="width: 8%">成交套数</th>
        <th style="width: 8%">成交面积</th>
        <th style="width: 8%">数据时间</th>
        <th style="width: 8%">更新时间</th>
        <th style="width: 10%">操作</th>
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
                    <td>${item.cityName}</td>
                    <td>${item.areaName}</td>
                    <td><c:forEach items="${types}" var="s">
                        <c:if test="${item.type == s.id}" >${s.name}</c:if>
                    </c:forEach></td>
                    <td>${item.dealNum}</td>
                    <td>${item.dealAcreage}</td>
                    <%--<td>--%>
                        <%--<c:choose>--%>
                            <%--<c:when test="${item.ringRatio<0}">--%>
                                <%--${item.ringRatio < 0 ? -item.ringRatio:item.ringRatio}--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>

                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                        <%--</td>--%>
                    <td>${item.dataTime}</td>
                    <td>${item.updateTime}</td>
                    <td>
                        <a target="contentF"  class="edit_img" title="编辑" onclick="to_edit(${item.id })"></a>
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
<c:if test="${!empty paginator}">
    <c:set var="paginator" value="${paginator}"/>
    <c:set var="target" value="listDiv"/>
    <%@include file="../layout/pagination.jsp" %>
</c:if>
</body>

<script type="text/javascript">

    $(function () {
        var tableCont = document.querySelector('#table-cont');
        /**
         * scroll handle
         * @param {event} e -- scroll event
         */
        function scrollHandle (e){
            var scrollTop = this.scrollTop;
            this.querySelector('thead').style.transform = 'translateY(' + scrollTop + 'px)';
        }

        tableCont.addEventListener('scroll',scrollHandle);
    })

    function delete1(id){
        $.ajax({
            type: "post",
            url: "${ctx}/rest/quotationSecondHand/update?id="+id+"&isDelete=1",
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
            area: ['500px','500px'],
            content: ['${ctx}/rest/quotationSecondHand/to_update?id='+id,'no']
        });
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

    function batchDelete(status) {
        var ids = [];
        $("input[name='pid']").each(function(){
            if(this.checked){
                ids.push($(this).val());
            }
        });

        $.ajax({
            type: "post",
            url: "${ctx}/rest/quotationSecondHand/batchUpdate?ids="+ids+"&isDelete="+status,
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
</html>