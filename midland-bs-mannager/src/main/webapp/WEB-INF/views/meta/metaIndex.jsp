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


<!--列表界面-->
<div class="box">
    <section class="content">
        <p class="detail-title">
            <a class="setup" target="contentF" onclick="toAddPage()">新增</a>
        </p>
        <form action="${ctx }/rest/meta/list" method="POST" id="searchForm"
              onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
            <ul class="userinfo row">
                <%@include file="../menu/indexArea.jsp" %>
                <li><span>平台：</span>
                    <select name="source" id="source" class="dropdown">
                        <option value="">请选择</option>
                        <c:forEach items="${sources}" var="s1">
                            <option value="${s1.id}">
                                    ${s1.name}
                            </option>
                        </c:forEach>
                    </select>
                </li>
                <li><span>模块：</span>
                    <input type="hidden" name="modeName" id="modeName">
                    <select name="modeId" id="modeId" class="dropdown">
                        <option value="">请选择</option>
                        <option value="8">外销网</option>
                        <option value="9">市场调究</option>
                        <option value="10">资讯</option>
                        <option value="11">问答</option>
                    </select>
                </li>

                <li id="childMode"><span>子模块：</span>
                    <input type="hidden" name="secondModeName" id="secondModeName">
                    <select name="secondModeId" id="secondModeId"
                            style="height: 28px;width: 120px; display: inline-block;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value=>请选择</option>
                    </select>
                </li>
                <c:if test="${not empty isSuper}">
                    <li><span>是否删除：</span>
                        <select name="isDelete" id="isDelete" class="dropdown">
                            <option value="">全部</option>
                            <c:forEach items="${isDeletes}" var="s1">
                                <option value="${s1.id}" <c:if test="${s1.id==0}">selected</c:if>>
                                        ${s1.name}
                                </option>
                            </c:forEach>
                        </select>
                    </li>
                </c:if>
                <li>
                    <input class="public_btn bg1" type="submit" name="inquery" id="inquery" value="查询"/>
                </li>
            </ul>
        </form>
        <div id="listDiv"></div>
    </section>
</div>


<script type="text/javascript">


    $("#secondModeId").change(function () {
        $("#secondModeName").val($("#secondModeId option:selected").text());
    })


    $("#modeId").change(function () {
        $("#modeName").val($("#modeId option:selected").text())
        debugger;
        if ($("#modeId").val() == 8) {
            $("#childMode").show();
            getExportSaleCates();
        } else if ($("#modeId").val() == 9) {
            $("#childMode").show();
            var data = "type=0" + "&cityId=" + $("#cityId").val() + "&source=" + $("#source").val();
            getCate(data);
        } else if ($("#modeId").val() == 10) {
            $("#childMode").show();
            var data = "type=1" + "&cityId=" + $("#cityId").val() + "&source=" + $("#source").val();
            getCate(data);
        } else if ($("#modeId").val() == 11) {
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


    function toAddPage() {
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['100%', '100%'],
            shadeClose: false, //点击遮罩关闭
            title: ['新增'],
            resize: false,
            scrollbar: false,
            content: ['${ctx}/rest/meta/to_add', 'no']
        });
    }

    window.onload = function () {
        $('#searchForm').submit();
    }
</script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>