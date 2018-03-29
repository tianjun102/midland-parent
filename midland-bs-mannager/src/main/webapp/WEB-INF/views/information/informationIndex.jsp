<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
<%@include file="../layout/zTree.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${ctx}/assets/css/ztree/css/demo.css">
    <link rel="stylesheet" href="${ctx }/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css"/>
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">
</head>
<body>


<!--委托列表界面-->
<div class="box">
    <section class="content">
        <p class="detail-title">
            <span>资讯管理>>资讯列表</span>
            <a class="setup" target="contentF" onclick="toAddPage()">新增</a>
            <a style="margin-right: 10px;" class="setup" target="contentF"
               href="${ctx}/rest/category/index?type=1">分类管理</a>
        </p>
        <form action="${ctx }/rest/information/list" method="POST" id="searchForm"
              onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
            <ul class="userinfo row">
                <input type="hidden" name="articeType" id="articeType" value="1"/>
                <%@include file="../layout/sherchArea.jsp" %>
                <li><span>平台：</span>
                    <select name="source" id="source" class="dropdown">
                        <option value>全部</option>
                        <option value="0">网站</option>
                        <option value="1">微站</option>
                    </select>
                </li>

                <li>
                    <span style="float:left;">状态：</span>
                    <select name="status" id="status"
                            style="height: 28px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="">全部</option>
                        <option value="0">上架</option>
                        <option value="1">下架</option>
                    </select>
                </li>
                <c:if test="${not empty isSuper}">
                    <li>
                        <span style="float:left;">是否删除：</span>
                        <select name="isDelete" id="isDelete"
                                style="height: 28px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                            <option value="0">未删除</option>
                            <option value="1">已删除</option>
                        </select>
                    </li>
                </c:if>
                <li><span>分类：</span>
                    <select name="cateName" id="id"
                            style="height: 28px; width:120px;display: inline-block;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="">请选择</option>
                        <c:forEach items="${cateList}" var="s">
                            <option value="${s.cateName}">
                                    ${s.cateName}
                            </option>
                        </c:forEach>
                    </select>
                </li>
                <li><span>标题：</span><input type="text" name="title" id="title" placeholder="请输入标题"/></li>
                <li>
                    <span>发布时间：</span><input class="Wdate half" id="time1"
                                             onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})"
                                             name="startTime"/> <em class="gang">-</em><input
                        class="Wdate half"
                        onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})"
                        id="time2" name="endTime"/>
                </li>

                <li><input class="public_btn bg1" type="submit" name="inquery" id="inquery" value="查询"/></li>
            </ul>
        </form>
        <input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;"
               onclick="batchDelete(1)" class="public_btn bg1" type="submit" value="批量删除"/>
        <c:if test="${not empty isSuper}"><input
                style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;"
                onclick="batchDelete(0)" class="public_btn bg1" type="submit" value="批量恢复"/></c:if>
        <c:choose>
            <c:when test="${openFlag==1}">
                <input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;"
                       onclick="openOrCloseBanner(0)" class="public_btn bg1" type="submit" value="开启滚动"/>
            </c:when>
            <c:otherwise>
                <input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;"
                       onclick="openOrCloseBanner(1)" class="public_btn bg1" type="submit" value="关闭滚动"/>
            </c:otherwise>
        </c:choose>
        <div id="listDiv"></div>
    </section>
</div>

<!-- 本页私有js -->

<script type="text/javascript">

    $("#source").change(function () {
        getCate();
    })
    $("#status").change(function () {
        getCate();
    })
    $("#citys").change(function () {
        getCate();
    })
    $("#isDelete").change(function () {
        getCate();
    })

    function getCate() {
        var data = "articeType=" + $("#articeType").val() + "&cityId=" + $("#cityId").val() +
            "&isDelete=" + $("#isDelete").val() + "&source=" + $("#source").val() + "&status=" + $("#status").val();

        $.ajax({
            type: "post",
            url: "${ctx}/rest/information/getCate",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    debugger;
                    var obj = data.data;
                    var html = "<option value=>请选择</option>";
                    for (var i = 0; i < obj.length; i++) {
                        html += "<option value=\"" + obj[i].cateName + "\">" + obj[i].cateName + "</option>";
                    }
                    $("#id").html(html);

                } else {
                    layer.msg("获取资讯分类失败！", {icon: 2});
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


    function openOrCloseBanner(id) {
        //0隐藏，1显示
        $.ajax({
            type: "post",
            url: "${ctx}/rest/information/banner/openOrClose?id=" + id,
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state == 0) {
                    window.location.reload();
                } else {
                    layer.msg("操作失败！", {icon: 2});
                }
            },
            error: function (data) {
                if (data.responseText != null) {
                    layer.msg(data.responseText, {icon: 2});
                } else {
                    layer.msg("操作失败！", {icon: 2});
                }
            }
        })
    }


    window.onload = function () {
        $('#searchForm').submit();
    }

    function toAddPage() {
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['100%', '100%'],
            shadeClose: false, //点击遮罩关闭
            title: ['资讯'],
            resize: false,
            scrollbar: false,
            content: ['${ctx}/rest/information/to_add', 'yes']
        });
    }

    $(document).ready(function () {

    });


    $("#source").change(function () {
        setEmpty();
        $("#cityName").val($("#cityId option:selected").text());
    })

    $("#citys").change(function () {
        setEmpty();
        $("#cityName").val($("#cityId option:selected").text());
    })

    function setEmpty() {
        $("input[name='cateId']").val("");
        $("input[name='cateName']").val("");
        $("input[name='showCateName']").val("");
        $("input[name='noteType']").val("");
        $("input[name='modeId']").val("");
        $("input[name='modeName']").val("");
        $("#showDiv").hide();
    }
</script>

</body>
</html>