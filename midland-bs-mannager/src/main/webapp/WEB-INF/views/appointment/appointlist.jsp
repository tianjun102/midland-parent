<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<style type="text/css">
    .table-add tr td .house-detail {
         width: auto;
    }
</style>
<body>


<div class="table-responsive m40" id='table-cont'>
    <table class="table table-bordered table-add">
        <thead>
        <tr>
            <th style="width:auto">预约编号</th>
            <th style="width:auto">平台</th>
            <th style="width:auto">预约人</th>
            <th style="width:auto">手机号码</th>
            <th style="width:auto">性别</th>
            <th style="width:auto">类型</th>
            <th style="width:auto">分类</th>
            <th style="width:auto">预约时间</th>
            <th style="width:auto">所属区域</th>
            <th style="width:auto">小区名</th>
            <th style="width:auto">门牌地址</th>

            <th style="width:auto">经纪人</th>
            <th style="width:auto">状态</th>
            <th style="width:auto">处理时间</th>
            <th style="width:auto">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.appoint }">
                <c:forEach items="${requestScope.appoint }" var="item"
                           varStatus="xh">
                    <tr>
                        <td>${item.appointSn }</td>
                        <td><c:forEach items="${sources}" var="s">
                            <c:if test="${item.source == s.id}">${s.name}</c:if>
                        </c:forEach></td>
                        <td>${item.nickName }</td>
                        <td>${item.phone }</td>
                        <td><c:forEach items="${sexs}" var="s">
                            <c:if test="${item.sex == s.id}">${s.name}</c:if>
                        </c:forEach></td>
                        <td><c:forEach items="${houses}" var="s">
                            <c:if test="${item.houseType == s.id}">${s.name}</c:if>
                        </c:forEach></td>
                        <td><c:forEach items="${sellRents}" var="s">
                            <c:if test="${item.sellRent == s.id}">${s.name}</c:if>
                        </c:forEach></td>
                        <td>${item.appointmentTime }</td>
                        <td>${item.areaName }</td>
                        <td>${item.communityName }</td>
                        <td><a target="_blank" href="${item.viewUrl}" class="house-detail">房源详情</a></td>
                        <td>${item.agentName }</td>
                        <td><c:forEach items="${statusList}" var="s">
                            <c:if test="${item.status == s.id}">${s.name}</c:if>
                        </c:forEach></td>
                        <td>${item.handleTime }</td>
                        <td>
                            <c:if test="${item.resetFlag==1 and item.status !=3}">
                                <a target="contentF" class="arrange_img" title="重新分配经纪人"
                                   onclick="toRedistribute(${item.id })"></a>
                            </c:if>

                            <a target="contentF" class="edit_img" title="编辑" onclick="toUpdateAppointment(${item.id})"></a>
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
        $("#table-cont").css({maxHeight:allHeight-headIndex-100-17});
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
    //修改
    function toRedistribute(id) {
        //重新分配经纪人时，保存的地址
        var pageNo = ${paginator.page};
        var pageSize = ${paginator.limit};
        var parame1 = "&pageNo="+pageNo+"&pageSize="+pageSize;
        var url = "${ctx}/rest/appoint/reset_agent";
        var indexUrl="${ctx}/rest/appoint/index";
        window.open("${ctx}/rest/public/toRedistribute?id=" + id + "&url=" + url+"&indexUrl="+indexUrl+parame1, "contentF");
        <%--layer.open({--%>
            <%--type: 2,--%>
            <%--title: ['重新分配经纪人'],--%>
            <%--shade: 0.3,--%>
            <%--area: ['1000px',  '500px'],--%>
            <%--content: ['${ctx}/rest/public/toRedistribute?id=' + id + "&url=" + url, 'no']--%>
        <%--});--%>
    }


    function toUpdateAppointment(appointId) {
        var pageNo = ${paginator.page};
        var pageSize = ${paginator.limit};
        var param = "&pageNo="+pageNo+"&pageSize="+pageSize;
        window.open("${ctx}/rest/appoint/to_update?appointId=" + appointId +param, "contentF");
        <%--layer.open({--%>
            <%--type: 2,--%>
            <%--title: ['预约详情'],--%>
            <%--shade: 0.3,--%>
            <%--area: ['1000px', '500px'],--%>
            <%--content: ['${ctx}/rest/appoint/to_update?appointId=' + appointId]--%>
        <%--});--%>
    }
</script>
</body>
</html>