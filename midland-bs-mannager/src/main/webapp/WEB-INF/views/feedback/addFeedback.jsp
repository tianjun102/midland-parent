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

</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/feedback/add" method="post" id="dataForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <li><span>nickName：</span>
               <input type="text" name="nickName" id="nickName" value="${item.menuName}"/>
            </li>
            <li><span>phone：</span>
               <input type="text" name="phone" id="phone" value="${item.menuName}"/>
            </li>
            <li><span>feedbackContent：</span>
               <input type="text" name="feedbackContent" id="feedbackContent" value="${item.menuName}"/>
            </li>
            <li><span>addTime：</span>
               <input type="text" name="addTime" id="addTime" value="${item.menuName}"/>
            </li>
            <li><span>status：</span>
               <input type="text" name="status" id="status" value="${item.menuName}"/>
            </li>
            <li><span>remark：</span>
               <input type="text" name="remark" id="remark" value="${item.menuName}"/>
            </li>
            <li><span>isDelete：</span>
               <input type="text" name="isDelete" id="isDelete" value="${item.menuName}"/>
            </li>
            <li><span>purpose：</span>
               <input type="text" name="purpose" id="purpose" value="${item.menuName}"/>
            </li>
            <li><span>userId：</span>
               <input type="text" name="userId" id="userId" value="${item.menuName}"/>
            </li>
            <li><span>operatorId：</span>
               <input type="text" name="operatorId" id="operatorId" value="${item.menuName}"/>
            </li>
            <li><span>operatorName：</span>
               <input type="text" name="operatorName" id="operatorName" value="${item.menuName}"/>
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
            url: "${ctx}/rest/feedback/add",
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
             error: function (data) {                        if (data.responseText!= null){                            layer.msg(data.responseText, {icon: 2});                        }else {                            layer.msg("保存失败！", {icon: 2});                        }                    }
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