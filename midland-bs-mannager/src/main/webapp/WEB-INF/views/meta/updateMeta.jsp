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
    <form action="${ctx}/rest/meta/update" method="post" id="dataForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <li><span>source：</span>
               <input type="text" name="source" id="source" value="${item.source}"/>
            </li>
            <li><span>cityId：</span>
               <input type="text" name="cityId" id="cityId" value="${item.cityId}"/>
            </li>
            <li><span>cityName：</span>
               <input type="text" name="cityName" id="cityName" value="${item.cityName}"/>
            </li>
            <li><span>title：</span>
               <input type="text" name="title" id="title" value="${item.title}"/>
            </li>
            <li><span>keywords：</span>
               <input type="text" name="keywords" id="keywords" value="${item.keywords}"/>
            </li>
            <li><span>description：</span>
               <input type="text" name="description" id="description" value="${item.description}"/>
            </li>
            <li><span>modeId：</span>
               <input type="text" name="modeId" id="modeId" value="${item.modeId}"/>
            </li>
            <li><span>modeName：</span>
               <input type="text" name="modeName" id="modeName" value="${item.modeName}"/>
            </li>
            <li><span>secondModeName：</span>
               <input type="text" name="secondModeName" id="secondModeName" value="${item.secondModeName}"/>
            </li>
            <li><span>secondModeId：</span>
               <input type="text" name="secondModeId" id="secondModeId" value="${item.secondModeId}"/>
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
        debugger;
        $.ajax({
            type: "post",
            url: "${ctx}/rest/meta/update",
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