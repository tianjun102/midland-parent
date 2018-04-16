<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../../layout/tablib.jsp" %>
<%@include file="../../layout/source.jsp"%>
<%@include file="../../layout/zTree.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加热门关注</title>
    <link rel="stylesheet" href="${ctx}/assets/css/ztree/css/demo.css">
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">
    <link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css"/>
    <style type="text/css">
        .dropdown {
            width: 248px;!important;

        }
    </style>

</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/menu/add" method="post" id="dataForm">
        <ul class="userinfo width-md row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <%@include file="../area_required.jsp" %>
            <input type="hidden" name="menuTypeId" value="${item.menuTypeId}" />
            <li style="display:flex;align-items:center">
                <span>平台：</span>
                <select name="source" id="source" class="dropdown" <c:if test="${empty isSuper}">disabled="disabled"</c:if> onchange="fieldChange()">
                    <c:forEach items="${sources}" var="s">
                        <option value="${s.id}" <c:if test="${s.id == item.source}">selected="selected"</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>
                </select>
            </li>

            <li><span>菜单名：</span>
                <input type="text" name="menuName" id="menuName" value="${item.menuName}" onblur="notEmpty('menuName','menuName','')"/>
                <label style="color: red" class = "_star " >*</label>
            </li>
            <li><span>链接：</span>
                <input type="text" name="url" id="url" value="${item.url}" placeholder="相对路径"/>
            </li>

            <li>
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="updateData()">更新</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>
    </form>

</section>
<script type="text/javascript">


    //保存数据
    function updateData() {
        if (!checkSelect('citys','请选择市级')||!notEmpty('menuName','menuName','')){
            return;
        }
        var data = $("#dataForm").serialize();

        $.ajax({
            type: "post",
            url: "${ctx}/rest/menu/update",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("保存成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        parent.layer.closeAll();
                        parent.$("#inquery").click();
                    }, 1000);

                } else {
                    layer.msg("保存失败！", {icon: 2});
                }
            },
            error: function (data) {
                if (data.responseText != null) {
                    layer.msg(data.responseText, {icon: 2});
                } else {
                    layer.msg("保存失败！", {icon: 2});
                }
            }
        });
    }

    //取消
    function closeWin() {
        parent.layer.closeAll();
    }



</script>
</body>
</html>