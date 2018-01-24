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
            <th style="width: 5%">工号</th>
            <th style="width: 5%">名称</th>
            <th style="width: 10%">手机号码</th>
            <th style="width: 5%">门店</th>
            <%--<th style="width: 10%">店铺地址</th>--%>
            <th style="width: 4%">服务类型</th>
            <th style="width: 5%">服务区域</th>
            <th style="width: 10%">职称</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.agents }">
                <c:forEach items="${requestScope.agents }" var="cust" varStatus="xh">
                    <tr>
                        <td><input name="radio" type="radio" value="${cust.id }"/></td>
                        <td>${cust.jobNum }</td>
                        <td>${cust.name }</td>
                        <td>${cust.phone }</td>
                        <td>${cust.storeName }</td>
                            <%--<td> ${cust.photoUrl}</td>--%>
                        <td>${cust.serviceType }</td>
                        <td>${cust.serviceAreaName }</td>
                        <td>${cust.post }</td>

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
        var agentNo = intHot.parent().next().text();
        var agentName = intHot.parent().next().next().text();
        var agentPhone = intHot.parent().next().next().next().text();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/entrust/reset_agent",
            async: false, // 此处必须同步
            dataType: "json",
            data: {
                "id": entrustId, "agentId": agentNo, "agentName": agentName,"agentPhone":agentPhone
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
            error: function (data) {
                if (data.responseText != null) {
                    layer.msg(data.responseText, {icon: 2});
                } else {
                    layer.msg("操作失败！", {icon: 2});
                }
            }

        });
    }

    function closeWin(){
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }
</script>
</body>
</html>