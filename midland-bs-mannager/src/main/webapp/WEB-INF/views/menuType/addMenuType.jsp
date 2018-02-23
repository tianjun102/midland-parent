<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/menuType/add" method="post" id="dataForm">
        <ul class="userinfo updinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <%@include file="../menu/area_required.jsp" %>
            <li><span>父分类：</span>
                <select name="" id="parentIdTemp" class="dropdown" onchange="chooseParent()">
                    <c:forEach items="${rootMentTypes}" var="s">
                        <option value="${s.id}">${s.name}</option>
                    </c:forEach>
                </select>
                <input type="hidden" id="parentId" name="parentId" value="0">
                <input type="hidden" id="parentName" name="parentName">
                <label style="color: red" class="_star ">*</label>
            </li>

            <li><span>名称：</span>
                <input type="text" name="name" id="name" onblur="notEmpty('name','name','')"/>
                <label style="color: red" class="_star ">*</label>
            </li>
            <li>
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="updateData()">保存</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>

    </form>
</section>

<script type="text/javascript">

    function chooseParent() {
        $("#parentId").val($("#parentIdTemp option:selected").val())
        if ($("#parentIdTemp option:selected").text() == "分类") {
            $("#parentName").val()
        } else {
            $("#parentName").val($("#parentIdTemp option:selected").text())
        }
    }

    //保存数据
    function updateData() {

        if (!checkSelect('citys', '请选择市级') || !notEmpty('name', 'name', '')) {
            return;
        }
        var data = $("#dataForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/menuType/add",
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