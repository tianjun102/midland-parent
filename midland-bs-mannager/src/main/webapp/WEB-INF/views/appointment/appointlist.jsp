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
            <th style="width:auto">委托编号</th>
            <th style="width:auto">信息来源</th>
            <th style="width:auto">称呼</th>
            <th style="width:auto">电话</th>
            <th style="width:auto">类型</th>
            <th style="width:auto">分类</th>
            <th style="width:auto">委托时间</th>
            <th style="width:auto">所属区域</th>
            <th style="width:auto">小区名</th>
            <th style="width:auto">门牌地址</th>
            <th style="width:auto">户型</th>
            <th style="width:auto">面积</th>
            <th style="width:auto">售价/租价</th>
            <th style="width:auto">预约时间</th>
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
                        <td><c:forEach items="${houses}" var="s">
                            <c:if test="${item.houseType == s.id}">${s.name}</c:if>
                        </c:forEach></td>
                        <td><c:forEach items="${sellRents}" var="s">
                            <c:if test="${item.sellRent == s.id}">${s.name}</c:if>
                        </c:forEach></td>
                        <td>${item.appointmentTime }</td>
                        <td>${item.areaName }</td>
                        <td>${item.communityName }</td>
                        <td>${item.address }</td>
                        <td>${item.layout }</td>
                        <td>${item.measure }</td>
                        <td>${item.price }</td>
                        <td>${item.entrustTime }</td>
                        <td>${item.userCnName }</td>
                        <td><c:forEach items="${statusList}" var="s">
                            <c:if test="${item.status == s.id}">${s.name}</c:if>
                        </c:forEach></td>
                        <td>${item.handleTime }</td>
                        <td>

                            <a target="contentF" class="edit_img" title="重新分配经纪人"
                               onclick="toRedistribute(${item.id })"></a>

                            <a target="contentF" class="edit_img" title="编辑"
                               onclick="toUpdateAppointment(${item.id})"></a>
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
    //删除
    //修改
    function toRedistribute(id) {
        layer.open({
            type: 2,
            title: ['重新分配经纪人'],
            shade: 0.3,
            area: ['1000px', '700px'],
            content: ['${ctx}/rest/appoint/toRedistribute?appointId=' + id, 'no']
        });
    }


    function toUpdateAppointment(appointId) {
        layer.open({
            type: 2,
            title: ['更新'],
            shade: 0.3,
            area: ['1000px', '700px'],
            content: ['${ctx}/rest/appoint/to_update?appointId=' + appointId, 'no']
        });
    }
</script>
</body>
</html>