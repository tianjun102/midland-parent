<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/appoint/update" method="post" id="addForm">
        <ul class="userinfo width-md row">
            <%@include file="../menu/area_up_required-mod6.jsp" %>
            <li class="col-md-6"><span>平台：</span>
                <select name="source" id="source" class="dropdown">
                    <option value="">请选择</option>
                    <c:forEach items="${sources}" var="s1">
                        <option value="${s1.id}">
                                ${s1.name}
                        </option>
                    </c:forEach>
                </select>
                <label style="color: red;padding-top: 0!important;" class = "_star " >*</label>
            </li>

            <li class="col-md-6"><span>模块：</span>
                <input type="hidden" name="modeName" id="modeName">
                <select name="modeId" id="modeId" class="dropdown">
                    <option value="">请选择</option>
                    <option value="1">新房首页</option>
                    <option value="8">外销网</option>
                    <option value="9">市场调究</option>
                    <option value="10">资讯</option>
                    <option value="11">问答</option>
                </select>
                <label style="color: red;padding-top: 0!important;" class = "_star " >*</label>
            </li>

            <li class="col-md-6" id="childMode"><span>子模块：</span>
                <input type="hidden" name="secondModeName" id="secondModeName">
                <select name="secondModeId" id="secondModeId" style="height: 28px;width: 250px; display: inline-block;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option value=>请选择</option>
                </select>
            </li>
            <li class="col-md-11"><span>title：</span>
                <textarea name="websiteTitle" id="title"
                          style="width:calc(100% - 120px);height:50px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;"></textarea>
            </li>
            <li class="col-md-11"><span>keywords：</span>
                <textarea name="websiteKeyWords" id="keywords"
                          style="width:calc(100% - 120px);height:50px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;"></textarea>
            </li>
            <li class="col-md-11"><span>description：</span>
                <textarea name="websiteDescription" id="description"
                          style="width:calc(100% - 120px);height:50px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;"></textarea>
            </li>



            <li class="col-md-6">
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="updateData()">保存</a>

                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin1()">取消</a>
            </li>
        </ul>

    </form>
</section>

<script type="text/javascript">

    $("#secondModeId").change(function () {
        $("#secondModeName").val($("#secondModeId option:selected").text());
    })


    $("#modeId").change(function () {
        $("#modeName").val($("#modeId option:selected").text())
        debugger;
        if ($("#modeId").val()==8){
            getExportSaleCates()
        }else if ($("#modeId").val()==9){
            $("#childMode").show();
            var data = "type=0"+"&cityId="+$("#cityId").val()+"&source="+$("#source").val();
            getCate(data);
        }else if ($("#modeId").val()==10){
            $("#childMode").show();
            var data = "type=1"+"&cityId="+$("#cityId").val()+"&source="+$("#source").val();
            getCate(data);
        }else if ($("#modeId").val()==11 || $("#modeId").val()==1){
            $("#childMode").hide();
        }
    })

function getCate(data) {
    $.ajax({
        type: "post",
        url: "${ctx}/rest/category/getCate",
        async: false, // 此处必须同步
        dataType: "json",
        data: data,
        success: function (data) {
            if (data.state == 0) {
                var obj = data.data;
                debugger;
                var html = "<option value=>请选择</option>";
                for (var i = 0; i < obj.length; i++) {
                    html += "<option value=\"" + obj[i].id + "\">" + obj[i].cateName + "</option>";
                }
                $("#secondModeId").html(html);

            } else {
                layer.msg("保存失败！", {icon: 2});
            }
        },
        error: function (data) {
            if (data.responseText != null) {
                layer.msg(data.responseText, {icon: 2});
            } else {
                layer.msg("操作失败！", {icon: 2});
            }
        }
    });
}

    function getExportSaleCates() {
        $.ajax({
            type: "post",
            url: "${ctx}/rest/meta/getExportSaleCates",
            async: false, // 此处必须同步
            dataType: "json",
            success: function (data) {
                if (data.state == 0) {
                    var obj = data.data;
                    debugger;
                    var html = "<option value=>请选择</option>";
                    for (var i = 0; i < obj.length; i++) {
                        html += "<option value=\"" + obj[i].id + "\">" + obj[i].cateName + "</option>";
                    }
                    $("#secondModeId").html(html);

                } else {
                    layer.msg("保存失败！", {icon: 2});
                }
            },
            error: function (data) {
                if (data.responseText != null) {
                    layer.msg(data.responseText, {icon: 2});
                } else {
                    layer.msg("操作失败！", {icon: 2});
                }
            }
        });
    }

    //保存数据
    function updateData() {
        if (checkSelect('citys', '城市不能为空', '城市不能为空')&& checkSelect('source', '平台不能为空', '') &&checkSelect('modeId', '模块不能为空', '') ) {
            var data = $("#addForm").serialize();
            debugger
            $.ajax({
                type: "post",
                url: "${ctx}/rest/meta/add",
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
                        layer.msg("操作失败！", {icon: 2});
                    }
                }
            });
        }
    }


    //取消
    function closeWin1() {
        parent.layer.closeAll();
    }
</script>
</body>
</html>