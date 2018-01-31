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
    <form action="${ctx}/rest/disclaimer/add" method="post" id="dataForm">
        <ul class="adminfo width-lg row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <%@include file="../menu/area_required.jsp" %>
            <li><span>平台:</span>
                <select name="source" id="source">
                    <option
                            <c:if test="${item.source =='0'}">selected='selected'</c:if> value="0">网站
                    </option>
                    <option
                            <c:if test="${item.source =='1'}">selected='selected'</c:if> value="1">微站
                    </option>
                </select>
            </li>

            <li id="textArea" style="display: block;"><span>免责申明：</span>
                <textarea class="textarea-lg"
                          name="disclaimer"
                          id="myEditor" rows="" cols="">${item.disclaimer}</textarea>
            </li>
            <li id="textArea" style="display: block;"><span>联系我们：</span>
                <textarea class="textarea-lg"
                          name="contantUs"
                          id="myEditor1" rows="" cols="">${item.contantUs}</textarea>
            </li>
            <li id="textArea" style="display: block;"><span>关于美联：</span>
                <textarea class="textarea-lg"
                          name="aboutUs"
                          id="myEditor2" rows="" cols="">${item.aboutUs}</textarea>
            </li>
            <li id="textArea" style="display: block;"><span>脚文件：</span>
                <textarea class="textarea-lg"
                          name="cornerFile"
                          id="myEditor3" rows="" cols="">${item.cornerFile}</textarea>
            </li>
            <li id="textArea" style="display: block;"><span>私隐政策：</span>
                <textarea class="textarea-lg"
                          name="privacy"
                          id="myEditor4" rows="" cols="">${item.privacy}</textarea>
            </li>
            <li id="textArea" style="display: block;"><span>注册协议：</span>
                <textarea class="textarea-lg"
                          name="registrationProtocol"
                          id="myEditor5" rows="" cols="">${item.registrationProtocol}</textarea>
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
    UE.getEditor('myEditor');
    UE.getEditor('myEditor1');
    UE.getEditor('myEditor2');
    UE.getEditor('myEditor3');
    UE.getEditor('myEditor4');
    UE.getEditor('myEditor5');


    //保存数据
    function updateData() {
        var data = $("#dataForm").serialize();
        debugger;
        $.ajax({
            type: "post",
            url: "${ctx}/rest/disclaimer/add",
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