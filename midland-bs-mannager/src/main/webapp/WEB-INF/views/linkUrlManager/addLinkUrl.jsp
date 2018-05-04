<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加友情链接</title>
    <style type="text/css">
        .content ul.userinfo > li {
            float: none !important;
            margin-left: 20px;
            padding-top: 20px;
        }

        .dropdown {
            width: 274px !important;
        }
    </style>
</head>
<body>
<section class="content" style="border:none;">
    <form action="" method="post" id="addFrom">
        <ul class="userinfo row">
            <li>
                <span>城市：</span>
                <p style="display: inline-block;height: 28px;">
                    <input type="hidden" name="cityName" id="cityName" value="${cityName}">
                    <c:if test="${empty isSuper}"><input type="hidden" name="cityId" value="${cityId}"></c:if>
                    <select onchange="SetcityNam();" name="cityId" id="cityId"
                            style="height: 28px;width: 274px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;padding-left:5px; "
                            <c:if test="${empty isSuper}">disabled="disabled"</c:if>>
                        <option value="">请选择</option>
                        <c:forEach items="${cityList}" var="city">
                            <c:if test="${empty isSuper}">
                                <option selected="selected" value="${cityId}">${cityName}</option>
                            </c:if>
                            <option value="${city.id}">${city.name}</option>
                        </c:forEach>
                    </select>
                    <span class="_star">*</span>
                </p>
            </li>
            <li>
                <span style="float:left;">平台：</span>
                <select name="source" id="source" style="height: 28px;width: 274px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;padding-left:5px;" >
                    <option value="">请选择</option>
                    <option value="0">网站</option>
                    <option value="1">微站</option>
                </select>
                <span class="_star">*</span>
            </li>
            <li>
                <span style="float:left;">模块：</span>
                <input type="hidden" name="modeName" id="modeName">
                <select name="modeId" id="modeId"
                        style="height: 28px;width: 274px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;padding-left:5px;">
                    <option value="">请选择</option>
                    <option value="0">首页</option>
                    <option value="1">新房</option>
                    <option value="2">二手房</option>
                    <option value="3">租房</option>
                    <option value="4">写字楼</option>
                    <option value="5">商铺</option>
                    <option value="6">小区</option>
                    <option value="7">经纪人</option>
                    <option value="8">外销网</option>
                    <option value="9">市场调究</option>
                    <option value="10">资讯</option>
                    <option value="11">问答</option>
                </select>
                <span class="_star">*</span>
            </li>
            <li><span>链接名：</span><input style="width:274px;" type="text" name="linkName" id="linkName"
                                        onblur="notEmpty('linkName','linkName','链接名不能为空！');" maxlength="50"/><span
                    class="_star">*</span></li>

            <li>
                <span>链接URL：</span><input style="width:274px;" type="text" name="linkUrl" id="linkUrl" value=""
                                          onblur="checkUrl('linkUrl','linkUrl','网址格式不正确！')"/><span
                    class="_star">*</span>
            </li>
            <li><span>联系人：</span><input style="width:274px;" type="text" name="contacts" id="contacts"
                                        maxlength="50"/><span class="_star"></span></li>
            <li><span>联系方式：</span><input style="width:274px;" type="text" name="phone" id="phone" maxlength="50"/><span
                    class="_star"></span></li>
            <li><span>备注：</span> <textarea name="remarks"
                                           id="remarks"
                                           style="width: 61%; height: 50px; resize: none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;"></textarea>
            </li>
            <li style="padding-top:30px;">
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="saveData()">保存</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>
    </form>

</section>
<script type="text/javascript">
    function saveData() {
        if (checkSelect("cityId|source|modeId", "请选择城市！|请选择平台！|请选择模块！") &&notEmpty('linkName', 'linkName', '链接名不能为空！') &&  notEmpty('linkUrl', 'linkUrl', '链接URL不能为空！') && checkUrl('linkUrl', 'linkUrl', '网址格式不正确！')) {
            var data = $("#addFrom").serialize();

            $.ajax({
                type: "post",
                url: "${ctx}/rest/linkUrlManager/add",
                async: false, // 此处必须同步
                dataType: "json",
                data: data,
                success: function (data) {
                    if (data.state == 0) {
                        debugger;
                        var test = window.location.href;
                        setCookie("linkUrlCityId", $("#cityId").val(), test);
                        setCookie("linkUrlCityName", $("#cityName").val(), test);
                        setCookie("linkUrlModeName", $("#modeName").val(), test);
                        setCookie("linkUrlModeId", $("#modeId").val(), test);
                        setCookie("linkUrlSource", $("#source").val(), test);
                        layer.msg("新增成功！！！", {icon: 1});
                        $('#save').removeAttr("onclick");
                        setTimeout(function () {
                            $('#searchForm', window.parent.document).submit();
                            closeWin();
                        }, 1000);

                    } else {
                        layer.msg("新增失败！", {icon: 2});
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
    }

    $("#modeId").change(function () {
        $("#modeName").val($("#modeId").find("option:selected").text());
    })
    $(function () {
        var test = window.location.href;
        //设置城市选择的cookie
        var cityId = getCookie("linkUrlCityId", test);
        var cityName = getCookie("linkUrlCityName", test);
        var modeId = getCookie("linkUrlModeId", test);
        var modeName = getCookie("linkUrlModeName", test);
        var source = getCookie("linkUrlSource", test);
        if ($("#cityName").val() == '') {
            $("#cityName").val($("#cityId option:selected").text());
        }
        if (cityId != '') {
            $("#cityId").val(cityId);
            $("#cityName").val(cityName);
        }
        if (modeId != '') {
            $("#modeId").val(modeId);
            $("#modeName").val(modeName);
        }
        if (source !=''){
            $("#source").val(source);
        }

    })

    //取消
    function closeWin() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }


    //省级赋值
    function initProvince() {
        var addrId = $("#provinces option:selected").val();
        var addName = $("#provinces option:selected").text();
        $("#districts").html("<option  >请选择</option>");
        $("input[name=provinceId]").val(addrId);
        $("input[name=provinceName]").val(addName);
        $.ajax({
            type: "post",
            url: "${ctx}/rest/setting/seleAddress?flag=city&id=" + addrId,
            async: false, // 此处必须同步
            dataType: "json",
            data: "",
            success: function (data) {
                $("#citys").html("<option  >请选择</option>");

                data.result.forEach(function (list) {
                    $("#citys").append(
                        "<option value=" + list.id + " >" + list.name + "</option>");
                })
            }
        });
    }


    //市级赋值
    function initCity() {
        var addrId = $("#citys option:selected").val();
        var addName = $("#citys option:selected").text();
        if ("请选择" == addName) {
            //下级改变成请选择
            $("#districts option:selected").text(addName);
            $("#districts").html("<option  >请选择</option>");
            //其值及其下级值变成空
            $("input[name='cityId']").val("");
            $("input[name='cityName']").val("");
            $("input[name='distId']").val("");
            $("input[name='distName']").val("");
            return;
        }
        $("input[name='cityId']").val(addrId);
        $("input[name='cityName']").val(addName);
        $.ajax({
            type: "post",
            url: "${ctx}/rest/setting/seleAddress?flag=area&id=" + addrId,
            async: false, // 此处必须同步
            dataType: "json",
            data: "",
            success: function (data) {
                $("#districts").html("<option value='' >请选择</option>");
                data.result.forEach(function (list) {
                    $("#districts").append(
                        "<option value=" + list.id + " >"
                        + list.name + "</option>");
                })
            }
        });
    }


    //区级赋值
    function initDistrict() {
        var cityId = $("input[name='cityId']").val();
        var addrId = $("#districts option:selected").val();
        var addName = $("#districts option:selected").text();
        if ("请选择" == addName) {
            //下级改变成请选择
            $("#districts option:selected").text(addName);
            //其值及其下级值变成空
            $("input[name='distId']").val("");
            $("input[name='distName']").val("");
            return;
        }
        $("input[name='distId']").val(addrId);
        $("input[name='distName']").val(addName);

        $.ajax({
            type: "post",
            url: "${ctx}/rest/setting/seleAddress?flag=sheet&id=" + cityId + "&parentId=" + addrId,
            async: false, // 此处必须同步
            dataType: "json",
            data: "",
            success: function (data) {
                $("#sheets").html("<option value='' >请选择</option>");
                data.result.forEach(function (list) {
                    $("#sheets").append(
                        "<option value=" + list.id + " >"
                        + list.name + "</option>");
                })
            }
        });

    }


    //区级赋值
    function initSheet() {
        var cityId = $("input[name='cityId']").val();
        var addrId = $("#sheets option:selected").val();
        var addName = $("#sheets option:selected").text();
        if ("请选择" == addName) {
            //下级改变成请选择
            $("#sheets option:selected").text(addName);
            //其值及其下级值变成空
            $("input[name='sheetId']").val("");
            $("input[name='sheetName']").val("");
            return;
        }
        $("input[name='sheetId']").val(addrId);
        $("input[name='sheetName']").val(addName);

    }

    function SetcityNam() {
        $("#cityName").val($("#cityId option:selected").text())
    }


</script>
</body>
</html>