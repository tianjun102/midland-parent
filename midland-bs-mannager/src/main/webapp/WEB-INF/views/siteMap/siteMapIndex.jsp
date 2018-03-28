<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
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


<!--列表界面-->
<div class="box">
    <section class="content">
        <p class="detail-title">
            <span>站点管理>>网站地图管理列表</span>
            <a class="setup" target="contentF" onclick="toAddPage()">新增</a>
        </p>
        <form action="${ctx }/rest/siteMap/list" method="POST" id="searchForm"
              onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
            <ul class="userinfo row">

                <%@include file="../layout/sherchArea.jsp" %>
                <%--<li>--%>
                    <%--<span style="float:left;">平台：</span>--%>
                    <%--<select name="source" id="source" class="dropdown">--%>
                        <%--<option value="">全部</option>--%>
                        <%--<option value="0">网站</option>--%>
                        <%--<option value="1">微站</option>--%>
                    <%--</select>--%>
                <%--</li>--%>
                <c:if test="${not empty isSuper}">
                    <li>
                        <span style="float:left;">是否删除：</span>
                        <select name="isDelete" id="isDelete"
                                style="height: 28px;width: 100px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                            <option value="0">未删除</option>
                            <option value="1">已删除</option>
                        </select>
                    </li>
                </c:if>


                <li><span>分类名称：</span>
                    <select name="cateId" id="cateId"
                            style="height: 28px; width:120px;display: inline-block;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="">请选择</option>
                        <c:forEach items="${cateList}" var="s">
                            <option value="${s.cateId}">
                                    ${s.cateName}
                            </option>
                        </c:forEach>
                    </select>
                </li>


                <li><span>模块名称：</span>
                    <select name="modeId" id="modeId"
                            style="height: 28px; width:120px;display: inline-block;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="">请选择</option>
                        <c:forEach items="${modeList}" var="s">
                            <option value="${s.modeId}">
                                    ${s.modeName}
                            </option>
                        </c:forEach>
                    </select>                </li>
                <li>
                    <input class="public_btn bg1" type="submit" name="inquery" id="inquery" value="查询"/>
                </li>
            </ul>
        </form>
        <input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;"
               onclick="batchDelete(1)" class="public_btn bg1" type="submit" value="批量删除"/>
        <c:if test="${not empty isSuper}"><input
                style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;"
                onclick="batchDelete(0)" class="public_btn bg1" type="submit" value="批量恢复"/></c:if>
        <div id="listDiv"></div>
    </section>
</div>


<script type="text/javascript">
    function toAddPage() {
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['450px', '400px'],
            shadeClose: false, //点击遮罩关闭
            title: ['新增'],
            resize: false,
            scrollbar: false,
            content: ['${ctx}/rest/siteMap/to_add?type=4', 'no']
        });
    }

    window.onload = function () {
        $('#searchForm').submit();
    }
</script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
<script type="text/javascript">
    $("#citys").change(function () {
        getcates();
        getmodes();
    })
    $("#cateId").change(function () {
        getmodes();
    })
    $("#isDelete").change(function () {
        getcates();
        getmodes();
    })

    function getcates() {
        var data = "&cityId="+$("#cityId").val()+"&isDelete="+$("#isDelete").val();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/siteMap/getCate",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    var obj = data.data;
                    var html = "<option value=>请选择</option>";
                    for (var i = 0; i < obj.length; i++) {
                        html += "<option value=\"" + obj[i].cateId + "\">" + obj[i].cateName + "</option>";
                    }
                    $("#cateId").html(html);

                } else {
                    layer.msg("新增失败！", {icon: 2});
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

function getmodes() {
    var data = "cateId=" + $("#cateId").val()+"&cityId="+$("#cityId").val()+"&isDelete="+$("#isDelete").val();

        $.ajax({
            type: "post",
            url: "${ctx}/rest/siteMap/getMode",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    debugger;
                    var obj = data.data;
                    var html = "<option value=>请选择</option>";
                    for (var i = 0; i < obj.length; i++) {

                        html += "<option value=\"" + obj[i].modeId + "\">" + obj[i].modeName + "</option>";
                    }
                    $("#modeId").html(html);

                } else {
                    layer.msg("新增失败！", {icon: 2});
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




    function beforeClick(treeId, treeNode, clickFlag) {
        if (treeNode.id == 0) {
            $("input[name='cateId']").val("");
            $("input[name='cateName']").val("");
            $("input[name='noteType']").val(treeNode.type);
            $("input[name='vipcate']").val("");
        } else {
            $("input[name='cateId']").val(treeNode.id);
            $("input[name='cateName']").val(treeNode.name);
            $("input[name='noteType']").val(treeNode.type);
            $("input[name='vipcate']").val(treeNode.name);
        }
        $("#showDiv").hide();
    }

    function showTree(event) {
        var data = $("#searchForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/siteMap/choose",
            async: false, // 此处必须同步
            dataType: "json",
            data: data + "&type=4",
            success: function (data) {
                var dfd = {id: 0, pId: 0, name: '分类', open: true, nocheck: true, iconSkin: "pIcon01"};
                catProNodes = [dfd];
                $.each(data.list, function (i, listItem) {
                    catProNodes.push(listItem);
                });
                $.fn.zTree.init($("#categoryTree"), setting, catProNodes);
                $("#showDiv").show();
            },
            error: function (data) {
                if (data.responseText != null) {
                    layer.msg(data.responseText, {icon: 2});
                } else {
                    layer.msg("保存失败！", {icon: 2});
                }
            }
        });
        $("#showDiv").show();
    }

    $("#source").change(function () {
        setEmpty();
    })
    $("#citys").change(function () {
        setEmpty();
    })
    $("#provinces").change(function () {
        setEmpty();
    })

    function hideTree(event) {
        $("#showDiv").hide();
    }

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