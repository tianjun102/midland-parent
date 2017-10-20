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
        .content ul.adminfo li > span {
            float: left;
            display: inline-block;
            width: 160px;
            height: 38px;
            line-height: 38px;
            text-align: left;
            font-size: 14px;
            color: rgb(102, 102, 102);
        }
    </style>
</head>
<body>


<section class="content" style="border:none;">
    <form action="${ctx}/rest/setting/setTime" method="post" id="timeSett">
        <ul class="adminfo row">
            <li class="col-md-6"><span>预约告警时间：</span>
                <input type="text" name="appointmentWarn" id="appointmentWarn"
                       onblur="notEmpty('appointmentWarn','appointmentWarn','');InitInput.setNumber(this,9,2,2)"
                       value="${item.appointmentWarn}"><span>小时</span>
            </li>
            <li class="col-md-6"><span>预约关闭时间：</span>
                <input type="text" name="appointClose" id="appointClose"
                       onblur="notEmpty('appointClose','appointClose','');InitInput.setNumber(this,9,2,2)"
                       value="${item.appointClose}"><span>小时</span>
            </li>
            <li class="col-md-6"><span>任务执行间隔时间：</span>
                <input type="text" name="taskInterval" id="taskInterval"
                       onblur="notEmpty('taskInterval','taskInterval','');InitInput.setNumber(this,9,2,2)"
                       value="${item.taskInterval}"><span>小时</span>
            </li>

            <li class="col-md-6">
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="submitTime()">保存</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="cancel1();">取消</a>
            </li>
        </ul>

    </form>
</section>


<script type="text/javascript">
    function submitTime() {
        var data = $("#timeSett").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/setting/setTime",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("保存成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        window.open("${ctx}/rest/setting/time/index", "contentF");
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

    function cancel1() {
        window.open("${ctx}/rest/setting/time/index", "contentF");
    }
</script>
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>