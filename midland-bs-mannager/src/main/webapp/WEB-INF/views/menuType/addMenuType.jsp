<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
    </script>

</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/menuType/add" method="post" id="dataForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">

            <li class="col-md-6"><span>父分类：</span>
                <select name="" id="parentIdTemp" class="dropdown" onchange="chooseParent()">
                    <c:forEach items="${rootMentTypes}" var="s">
                        <option value="${s.id}">${s.name}</option>
                    </c:forEach>
                </select>
                <input type="hidden" id="parentId" name="parentId" value="0">
                <input type="hidden" id="parentName" name="parentName">
            </li>

            <li><span>名称：</span>
               <input type="text" name="name" id="name" ß/>
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
            $("#parentName").val()
        }else{
            $("#parentName").val($("#parentIdTemp option:selected").text())
        }
    }

    //保存数据
    function updateData() {
        var data = $("#dataForm").serialize();
        debugger;
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