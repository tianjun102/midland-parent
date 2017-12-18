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
            height: 28px;
            line-height: 28px;
            text-align: left;
            font-size: 14px;
            color: rgb(102, 102, 102);
        }
    </style>
</head>
<body>


<section class="content" style="border:none;">
    <form action="${ctx}/rest/sensitive/add" method="post" id="timeSett">
        <ul class="adminfo row">
            <li class="col-md-6"><span>新增敏感字符：</span>
                <input type="text" name="V" id="V"
                       onblur="notEmpty('V','V','');"
                       >
            </li>
            <li class="col-md-6"><span>删除敏感字符：</span>
                <input type="text" name="V1" id="V1"
                       onblur="notEmpty('V1','V1','')" >
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
            url: "${ctx}/rest/sensitive/add",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("保存成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        window.open("${ctx}/rest/sensitive/index", "contentF");
                    }, 1000);

                } else {
                    layer.msg("保存失败！", {icon: 2});
                }
            },
            error: function (data) {
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