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
            <th style="width: 5%">序号</th>
            <th style="width: 20%">角色代码</th>
            <th style="width: 25%">角色名称</th>
            <!-- <th>类型</th> -->
            <th style="width: 35%">角色描述</th>
            <th style="width: 15%">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.roles }">
                <c:forEach items="${requestScope.roles }" var="role" varStatus="xh">
                    <tr>
                        <td>${xh.count }</td>
                        <td>${role.roleSign }</td>
                        <td>${role.roleName }</td>
                            <%-- <td>
                                <c:if test="${role.roleType=='0'}">沃可视 </c:if>
                                <c:if test="${role.roleType=='1'}">省代 </c:if>
                                <c:if test="${role.roleType=='2'}">区服</c:if>
                                <c:if test="${role.roleType=='3'}">门代 </c:if>
                            </td> --%>
                        <td>${role.description }</td>
                        <td>
                            <a onclick="distribute(${role.id})" target="contentF" class="distri_img" title="分派权限"></a>
                                <%-- <a onclick="roleUser(${role.id },'${role.roleName }')" target="contentF" class = "uList_img" title = "角色列表"></a> --%>
                            <a onclick="edit(${role.id})" target="contentF" class="edit_img" title="编辑"></a>
                            <a onclick="isDelete(${role.id})" target="contentF" class="delete_img" title="删除"></a>
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

    //分配权限
    function distribute(roleId) {
        layer.open({
            type: 2,
            title: ['分配权限'],
            shade: 0.3,
            area: ['400px', '370px'],
            content: '${ctx}/rest/role/toAllocation?roleId=' + roleId
        });
    }

    //编辑
    function edit(roleId) {
        layer.open({
            type: 2,
            title: ['角色编辑'],
            shade: 0.3,
            area: ['480px', '350px'],
            content: ['${ctx}/rest/role/findRole?roleId=' + roleId, 'no']
        });
    }


    function isDelete(roleId) {
        layer.open({
            type: 1,
            skin: 'layer-style',
            area: ['350px', '200px'],
            shadeClose: false, //点击遮罩关闭
            title: ['删除角色'],
            resize: false,
            scrollbar: false,
            content:
            '<section class = "content" style = "border:none; height:100%;">' +
            '<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您确定要删除当前角色吗?</p>' +
            '</section>',
            btn: ['确定', '取消'],
            yes: function (index) {
                $.ajax({
                    type: "post",
                    url: "${ctx}/rest/role/deleteRole?roleId=" + roleId,
                    cache: false,
                    async: false, // 此处必须同步
                    dataType: "json",
                    success: function (xmlobj) {
                        if (xmlobj.flag == 1) {
                            layer.msg("操作成功！", {icon: 1});
                            setTimeout(function () {
                                $("#searchForm").submit();
                            }, 1000);
                        }
                        if (xmlobj.flag == 0) {
                            layer.msg("删除失败！！", {icon: 7});
                        }
                        layer.close(index);
                    }
                });
            }
            , success: function (layero) {
                var btn = layero.find('.layui-layer-btn');
                btn.css('text-align', 'center');
            }
        });

    }

    function roleUser(roleId, roleName) {
        layer.open({
            type: 2,
            title: ['角色用户信息:' + roleName],
            shade: 0.6,
            area: ['800px', '550px'],
            content: ['${ctx}/rest/role/roleUser?roleId=' + roleId, 'yes']
        });
    }
</script>
</body>
</html>