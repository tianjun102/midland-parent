<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
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
    <form action="${ctx}/rest/liaisonRecord/update" method="post" id="dataForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <li><span>name：</span>
               <input type="text" name="name" id="name" value="${item.name}"/>
            </li>
            <li><span>phone：</span>
               <input type="text" name="phone" id="phone" value="${item.phone}"/>
            </li>
            <li><span>category：</span>
               <input type="text" name="category" id="category" value="${item.category}"/>
            </li>
            <li><span>leavingMessage：</span>
               <input type="text" name="leavingMessage" id="leavingMessage" value="${item.leavingMessage}"/>
            </li>
            <li><span>addTime：</span>
               <input type="text" name="addTime" id="addTime" value="${item.addTime}"/>
            </li>
            <li><span>isOntact：</span>
               <input type="text" name="isOntact" id="isOntact" value="${item.isOntact}"/>
            </li>
            <li><span>isDelete：</span>
               <input type="text" name="isDelete" id="isDelete" value="${item.isDelete}"/>
            </li>
            <li><span>userId：</span>
               <input type="text" name="userId" id="userId" value="${item.userId}"/>
            </li>
            <li><span>email：</span>
               <input type="text" name="email" id="email" value="${item.email}"/>
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
        var data = $("#dataForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/liaisonRecord/update",
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