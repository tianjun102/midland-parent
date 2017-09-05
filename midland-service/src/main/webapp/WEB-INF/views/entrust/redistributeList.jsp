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
            <th style="width: 5%"></th>
            <th style="width: 5%">序号</th>
            <th style="width: 5%">用户名</th>
            <th style="width: 10%">手机号码</th>
            <th style="width: 5%">注册时间</th>
            <th style="width: 10%">注册来源</th>
            <th style="width: 4%">实名状态</th>
            <th style="width: 5%">审核人</th>
            <th style="width: 10%">审核时间</th>
            <th style="width: 10%">用户类型</th>
            <th style="width: 15%">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.users }">
                <c:forEach items="${requestScope.users }" var="cust" varStatus="xh">
                    <tr>
                        <td><input name="radio" type="radio" value="${cust.id }"/></td>

                        <td>${xh.count }</td>
                        <td>${cust.username }</td>
                        <td>${cust.phone }</td>
                        <td>${cust.createTime }</td>
                        <td> <c:if test="${appoint.source ==0 }">网站</c:if>
                            <c:if test="${appoint.source ==1 }">微站</c:if></td>
                        <td>${cust.auditStatus }</td>
                        <td>${cust.auditName }</td>
                        <td>${cust.auditTime }</td>
                        <td>
                            <c:if test="${cust.userType==0}">智者汇</c:if>
                            <c:if test="${cust.userType==1}">渠道服务商</c:if>
                        </td>
                        <td>

                            <a target="contentF" onclick="alterUser(${cust.id })">编辑</a>

                            <a target="contentF" onclick="takeblacklist(${cust.id })">加入黑名单</a>
                            <a target="contentF" onclick="viewRealRegistration(${cust.id })">
                                <c:choose>
                                    <c:when test="${cust.auditStatus==0}">审核实名信息
                                    </c:when>
                                    <c:otherwise>查看实名信息
                                    </c:otherwise>
                                </c:choose>
                            </a>
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
<ul class = "userinfo row">
    <li style="padding-top:30px;">
        <span></span>
        <a target="contentF" class="public_btn bg2" id="save" onclick="saveData()">确定</a>
        <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
    </li>
</ul>

<script type="text/javascript">
    function saveData(id){
        var entrustId = $("#entrustId").val();
        var intHot = $("input[name='radio']:checked");
        var agentId = intHot.parent().next().text();
        var agentName = intHot.parent().next().next().text();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/entrust/reset_agent",
            async: false, // 此处必须同步
            dataType: "json",
            data: {
                "id": entrustId, "userId": agentId, "userCnName": agentName
            },
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("分配成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        parent.location.reload();
                    }, 1000);

                } else {
                    layer.msg("分配失败！", {icon: 2});
                }
            },
            error: function () {
                layer.msg("分配失败！", {icon: 2});
            }

        });
    }
</script>
</body>
</html>