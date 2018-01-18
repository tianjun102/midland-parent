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
    <style type="text/css">
        .content ul.userinfo li:not(:last-child) input {
            float: left;
            width: 250px;
            height: 28px;
            line-height: 28px;
            border: 1px solid #dbe2e6;
            border-radius: 4px;
            text-indent: 10px;
            outline-color: #0099e0;
        }

        .dropdown {
            width: 250px;
        }
    </style>
</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/feedback/update" method="post" id="dataForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <li><span>昵称：</span>
                <input type="text" name="nickName" disabled="disabled" id="nickName" value="${item.nickName}"/>
            </li>
            <li><span>手机号：</span>
                <input type="text" name="phone" disabled="disabled" id="phone" value="${item.phone}"/>
            </li>
            <li><span>反馈内容：</span>
                <input type="text" name="feedbackContent" disabled="disabled" id="feedbackContent"
                       value="${item.feedbackContent}"/>
            </li>
            <li><span>状态：</span>
                <select name="status" id="status" class="dropdown">
                    <option value="0" <c:if test="${item.status == 0}">selected</c:if>>未处理</option>
                    <option value="1"
                            <c:if test="${item.status == 1}">selected</c:if> >处理中
                    </option>
                    <option value="2"
                            <c:if test="${item.status == 2}">selected</c:if> >已处理
                    </option>
                    <option value="3"
                            <c:if test="${item.status == 3}">selected</c:if> >已取消
                    </option>
                </select>
            <li>
            <li><span>备注：</span>
                <textarea rows="" cols="" style="width: 250px;height: 100px;border: 1px solid #dbe2e6;" name="remark"
                          id="remark">${item.remark}</textarea>
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
            url: "${ctx}/rest/feedback/update",
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