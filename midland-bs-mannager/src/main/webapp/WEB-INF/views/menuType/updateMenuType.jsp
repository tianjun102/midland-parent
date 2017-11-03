<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        .content ul.userinfo li > span {
            float: left;
            display: inline-block;
            width: 90px;
            height: 28px;
            line-height: 28px;
            text-align: right;
            font-size: 14px;
            color: rgb(102, 102, 102);
        }
    </style>
</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/menuType/update" method="post" id="dataForm">
        <ul class="userinfo updinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <%@include file="../menu/area_required.jsp" %>
            <li ><span>上级分类：</span>
                <select name="" id="parentIdTemp" class="dropdown" onchange="chooseParent()">
                    <c:forEach items="${rootMentTypes}" var="s">
                        <c:choose>
                            <c:when test="${s.id ==item.id}"></c:when>
                            <c:otherwise>
                                <option value="${s.id}" <c:if test="${item.parentId==s.id}">selected</c:if>>${s.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <input type="hidden" id="parentId" name="parentId" value="${item.parentId}">
                <input type="hidden" id="parentName" name="parentName" value="${item.parentName}">
            </li>
            <li><span>名称：</span>
               <input type="text" name="name" id="name" value="${item.name}" onblur="notEmpty('name','name','')"/>
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

    function chooseParent() {
        $("#parentId").val($("#parentIdTemp option:selected").val())
        if ($("#parentIdTemp option:selected").text()=="分类"){
            $("#parentName").val(" ")
        }else{
            $("#parentName").val($("#parentIdTemp option:selected").text())
        }
    }
    //保存数据
    function updateData() {

        if (!checkSelect('citys','请选择市级')||!notEmpty('name','name','')){
            return;
        }
        var data = $("#dataForm").serialize();
        debugger;
        $.ajax({
            type: "post",
            url: "${ctx}/rest/menuType/update",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("保存成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        parent.location.reload();
                    }, 1000);

                } else {
                    layer.msg("保存失败！", {icon: 2});
                }
            },
            error: function () {
                layer.msg("保存失败！", {icon: 2});
            }
        });
    }

    //取消
    function closeWin() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }
</script>
</body>
</html>