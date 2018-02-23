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
    <form action="${ctx}/rest/siteProtocol/update" method="post" id="dataForm">
        <ul class="adminfo width-lg row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <c:if test="${not empty isSuper}">
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
            </c:if>

            <li id="textArea" style="display: block;"><span>注册协议：</span>
                <textarea id="myEditor" name="registrationProtocol" class="textarea-lg">${item.registrationProtocol}</textarea>
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

    $("#citys").change(function () {
        if ($("#cityId").val()!=''){
            siteProtocol();
        }
    })
     $("#source").change(function () {
         siteProtocol();
    })

    function siteProtocol() {
        debugger;
        if ($("#cityId").val()==''){
            checkSelect('citys','请选择市级');
            return;
        }
        var data = "source="+$("#source").find("option:selected").val()+"&cityId="+$("#cityId").val();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/siteProtocol/siteProtocol",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                debugger;
                if (data.state == 0) {
                    $("#id").val(data.item.id);
                    UE.getEditor('myEditor').setContent(data.item.registrationProtocol==null?"":data.item.registrationProtocol);

                } else {
                    layer.msg("查询失败！", {icon: 2});
                }
            },
            error: function (data) {
                if (data.responseText != null) {
                    layer.msg(data.responseText, {icon: 2});
                } else {
                    layer.msg("查询失败！", {icon: 2});
                }
            }
        });
    }


    //保存数据
    function updateData() {
        var data = $("#dataForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/siteProtocol/update/registrationProtocol",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("保存成功！！！", {icon: 1});

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
        window.location.reload();
    }
</script>
</body>
</html>