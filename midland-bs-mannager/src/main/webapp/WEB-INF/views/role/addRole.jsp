<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">
    <link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css"/>
</head>
<body>
<section class="content" style="border:none;">
    <form action="" method="post" id="addFrom">
        <ul class="userinfo row">
            <li><span>角色代码：</span><input style="width:264px;" type="text" name="roleSign" id="roleSign"
                                         onblur="checkRoleCode();" maxlength="128"/><span class="_star">*</span>
                <div style="font-size:12px; color:#afadad;text-indent: 70px;">(角色代码唯一)</div>
            </li>
            <li><span>角色名称：</span><input style="width:264px;" type="text" name="roleName" id="roleName"
                                         onblur="checkRoleName();" maxlength="32"/><span class="_star">*</span>
                <div style="font-size:12px; color:#afadad;text-indent: 70px;">(角色名称唯一)</div>
            </li>
            <li><span>角色描述：</span><input style="width:264px;" type="text" name="description" id="description"
                                         maxlength="256"/></li>
            <%-- <li style = "display:flex;align-items:center">
                <span>类型：</span>
                <select name="roleType" id="roleType" class="dropdown">
                    <option value=""
                        <c:if test="${roleType=='-1'}">selected="selected"</c:if>>请选择</option>
                    <option value="0"
                        <c:if test="${roleType=='0'}">selected="selected"</c:if>>沃可视</option>
                    <option value="1"
                        <c:if test="${roleType=='1'}">selected="selected"</c:if>>省代</option>
                    <option value="2"
                        <c:if test="${roleType=='2'}">selected="selected"</c:if>>区服</option>
                    <option value="3"
                        <c:if test="${roleType=='3'}">selected="selected"</c:if>>门代</option>
            </select>
            </li> --%>
            <li style="padding-top:30px;">
                <span></span>
                <a class="public_btn bg2" id="save" onclick="saveData()">保存</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>
    </form>
</section>
<script type="text/javascript">

    //保存数据
    function saveData() {

        if (checkRoleCode() && checkRoleName()) {
            var roleSign = $("#roleSign").val();
            var roleName = $("input[name='roleName']").val();
            var roleType = $("#roleType option:selected").val();
            var description = $("input[name='description']").val();

            $.ajax({
                type: "post",
                url: "${ctx}/rest/role/addRole",
                async: false, // 此处必须同步
                dataType: "json",
                data: {"roleSign": roleSign, "roleName": roleName, "description": description},
                success: function (data) {
                    if (data.flag == 1) {
                        layer.msg("新增成功！！！", {icon: 1});
                        $('#save').removeAttr("onclick");
                        setTimeout(function () {
                            parent.layer.closeAll();
                            parent.$("#inquery").click();
                        }, 1000);

                    } else {
                        layer.msg("新增失败！", {icon: 2});
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
    }

    //取消
    function closeWin() {
        parent.layer.closeAll();
    }

    //检查角色代码唯一性
    function checkRoleCode() {
        var roleSign = $("input[name='roleSign']").val();
        if (roleSign == null || roleSign.trim() == "") {
            layer.tips("角色代码不能为空！", "input[name='roleSign']", {tips: 1});
            return false;
        }
        var regName = /^[a-zA-Z0-9_]{4,15}$/;
        if (!regName.test(roleSign.trim())) {
            layer.tips("仅支持英文及数字,长度为4-15个字符！", "input[name='roleSign']", {tips: 1});
            return false;
        }

        var boo = true;
        $.ajax({
            type: "post",
            url: "${ctx}/rest/role/checkRoleUnique",
            async: false, // 此处必须同步
            dataType: "json",
            data: {"roleSign": roleSign},
            success: function (xmlobj) {
                if (xmlobj.flag == 0) {
                    layer.tips("该角色代码已存在!", "input[name='roleSign']", {tips: 1});
                    boo = false;
                } else {
                    boo = true;
                }
            }
        });
        return boo;
    }

    //检查角色名称唯一性
    function checkRoleName() {
        var roleName = $("input[name='roleName']").val();
        if (roleName == null || roleName.trim() == "") {
            layer.tips("角色名称不能为空!", "input[name='roleName']", {tips: 1});
            return false;
        }

        var boo = true;
        $.ajax({
            type: "post",
            url: "${ctx}/rest/role/checkRoleUnique",
            async: false, // 此处必须同步
            dataType: "json",
            data: {"roleName": roleName},
            success: function (xmlobj) {
                if (xmlobj.flag == 0) {
                    layer.tips("该角色名称已存在!", "input[name='roleName']", {tips: 1});
                    boo = false;
                } else {
                    $("#roleNameCheckMsg").text("");
                    boo = true;
                }
            }
        });
        return boo;
    }
</script>
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
<script src="${ctx}/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>

</body>
</html>