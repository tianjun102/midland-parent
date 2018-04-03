<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@include file="../../layout/tablib.jsp" %>
<%@include file="../../layout/source.jsp" %>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8"/>
    <title>Banner列表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <meta name="MobileOptimized" content="320">
    <style type="text/css">
        .dropdown {
            position: relative;
            width: 150px;
            border: 1px solid #ccc;
            cursor: pointer;
            background: #fff;
            border-radius: 3px;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        .content ul.userinfo li > span {
            float: left;
            display: inline-block;
            width: 90px;
            height: 28px;
            line-height: 28px;
            text-align: right;
            font-size: 14px;
            color: rgb(102, 102, 102);
        }
    </style>
</head>
<body>
<!--活动管理界面-->
<div class="box">
    <form id="searchForm" action="${ctx}/rest/setting/bannerList" method="post"
          onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
        <section class="content">
            <p class="detail-title">
                <span>系统管理>>Banner管理</span>
                <a class="setup" onclick="toAddPage()" target="contentF">添加Banner</a>
            </p>
            <ul class="userinfo row">
                <%--<li>
                    <span style = "float:left;">城市：</span>
                    <select name="cityId" id="cityId" style="height: 28px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="">全部</option>
                        <c:forEach items="${cityList}" var="city">
                            <option value="${city.id}">${city.name}</option>
                        </c:forEach>
                    </select>
                </li>--%>
                <%@include file="../../layout/sherchArea.jsp" %>
                <li>
                    <span style="float:left;">平台：</span>
                    <select name="source" id="source" class="dropdown">
                        <option value="">全部</option>
                        <c:forEach items="${sources}" var="s1">
                            <option value="${s1.id}">
                                    ${s1.name}
                            </option>
                        </c:forEach>
                    </select>
                </li>
                <li><span>模块：</span>
                    <select name="model" id="model"
                            style="height: 28px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="">全部</option>
                        <option value="0">首页</option>
                        <option value="1">新房</option>
                        <option value="2">市场研究</option>
                        <option value="3">资讯</option>
                        <option value="4">地产新闻</option>
                        <option value="5">美联资讯</option>
                        <option value="6">购房指南</option>
                        <option value="7">新政解读</option>
                        <option value="8">人物专访</option>
                        <option value="9">购房资格</option>
                        <option value="10">关于我们</option>
                        <option value="11">外销网</option>
                        <option value="12">招聘管理</option>
                    </select>
                </li>
                <li><span>位置：</span>
                    <select name="position" id="position"
                            style="height: 28px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="">全部</option>
                        <option value="0">位置１</option>
                        <option value="1">位置２</option>
                        <option value="2">位置３</option>
                        <option value="3">位置４</option>
                    </select>
                </li>
                <li><span>状态：</span>
                    <select name="enabled" id="enabled"
                            style="height: 28px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="">全部</option>
                        <option value="1">关闭</option>
                        <option value="0">开放</option>
                    </select>
                </li>
                <li><span>上线时间：</span><input class="Wdate half" id="time1"
                                             onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})"
                                             name="startTime"/> <em class="gang">-</em><input
                        class="Wdate half"
                        onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})"
                        id="time2" name="endTime"/></li>
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
                <li><input class="public_btn bg1" type="submit" name="inquery" id="inquery" value="查询"/></li>
            </ul>
            <input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;"
                   onclick="batchDelete(1)" class="public_btn bg1" type="submit" value="批量删除"/>
            <c:if test="${not empty isSuper}"><input
                    style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;"
                    onclick="batchDelete(0)" class="public_btn bg1" type="submit" value="批量恢复"/></c:if>
            <div id="listDiv"></div>
        </section>
    </form>
</div>

</body>
<script type="text/javascript">
    $(function () {
        $('#searchForm').submit();
    })

    $("#model").change(function () {
        if ($("#model").val()=="12"){
            $("#position").html(" <option value=\"\">全部</option>")
            $("#position").append(" <option value=\"0\">招聘首页</option>")
            $("#position").append(" <option value=\"1\">校园职位</option>")
            $("#position").append(" <option value=\"2\">招聘流程</option>")
            $("#position").append(" <option value=\"3\">走进美联</option>")
            $("#position").append(" <option value=\"4\">校园福利</option>")
        }else {
            $("#position").html(" <option value=\"\">全部</option>")
            $("#position").append("<option value=\"0\">位置１</option>")
            $("#position").append("<option value=\"1\">位置２</option>")
            $("#position").append("<option value=\"2\">位置３</option>")
            $("#position").append("<option value=\"3\">位置４</option>")
        }
    })

    function deleteBanner(id, isDelete) {
        var msg = "您确定要删除当前数据吗？";
        if (isDelete == 0) {
            msg = "您确定要恢复当前数据吗？"
        }
        layer.open({
            type: 1,
            skin: 'layer-style',
            area: ['350px', '200px'],
            shadeClose: false, //点击遮罩关闭
            title: ['删除Banner'],
            resize: false,
            scrollbar: false,
            content:
            '<section class = "content" style = "border:none; height:100%;">' +
            '<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">' + msg + '</p>' +
            '</section>',
            btn: ['确定', '取消'],
            yes: function (index) {
                $.ajax({
                    type: "post",
                    url: "${ctx}/rest/setting/deleteBanner?id=" + id + "&isDelete=" + isDelete,
                    cache: false,
                    async: false, // 此处必须同步
                    dataType: "json",
                    success: function (data) {
                        if (data.result == 'ok') {
                            layer.msg("操作成功！", {icon: 1});
                            setTimeout(function () {
                                $("#searchForm").submit();
                            }, 1000);
                        } else {
                            layer.msg("操作失败！！", {icon: 7});
                        }
                        layer.close(index);
                    }
                });
            }
            , success: function (layero) {
                var btn = layero.find('.layui-layer-btn');
                btn.css('text-align', 'center');
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
            content: ['${ctx}/rest/setting/enterBanner', 'yes']
        });
    }
</script>

</html>