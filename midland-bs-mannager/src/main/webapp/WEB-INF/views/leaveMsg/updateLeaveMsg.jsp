<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
    </script>
<style type="text/css">
    .content ul.userinfo li>span {
        float: left;
        display: inline-block;
        width: 90px;
        height: 28px;
        line-height: 28px;
        text-align: right;
        font-size: 14px;
        color: rgb( 102, 102, 102 );
    }
</style>
</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/leaveMsg/update" method="post" id="dataForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <li><span>用户：</span>
                <input type="text" disabled="disabled" id="userName" value="${item.userName}"/>
            </li>
            <li><span>手机号码：</span>
                <input type="text" disabled="disabled" id="phone" value="${item.phone}"/>
            </li>
            <li><span>邮箱：</span>
                <input type="text" disabled="disabled" id="email" value="${item.email}"/>
            </li>
            <li><span>留言：</span>
               <input type="text"  disabled="disabled" id="message" value="${item.message}"/>
            </li>

            <li><span>回复：</span>
                <textarea style="width: 250px;height: 100px;" type="text" name="replyMsg" id="replyMsg" >${item.replyMsg}</textarea>
            </li>

            <li>
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="updateData()">确定</a>
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
            url: "${ctx}/rest/leaveMsg/update",
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
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }
</script>
</body>
</html>