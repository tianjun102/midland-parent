<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
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
    <title>页面配置</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <meta name="MobileOptimized" content="320">
    <style>
        .layui-layer {
            top: 260px !important;
        }
    </style>
</head>
<body>
<div class="box">
    <section class="content">
        <p class="detail-title">
            <span>页面配置</span>
        </p>
        <form id="formId" action="${ctx}/rest/pageConf/add" method="post" enctype="multipart/form-data" method="post">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <input type="hidden" name="cityName" id="cityName" value="${item.cityName}">
            <ul class="adminfo width-lg row">
                <li><span>城市：</span>
                    <select onchange="setCityName();" name="cityId" id="cityId"
                            <c:if test="${empty isSuper}">disabled="disabled"</c:if>>
                        <c:forEach items="${cityList}" var="city">
                            <option
                                    <c:if test="${item.cityId == city.id}">selected='selected'</c:if>
                                    value="${city.id}">${city.name}</option>
                        </c:forEach>
                    </select>
                    <span class="_star ">*</span>
                </li>
                <li><span>平台：</span>
                    <select name="source" id="source"
                            onchange="selectSource()">
                        <option
                                <c:if test="${item.source =='0'}">selected="selected"</c:if> value="0">网站
                        </option>
                        <option
                                <c:if test="${item.source =='1'}">selected="selected"</c:if> value="1">微站
                        </option>
                    </select>
                    <span class="_star ">*</span>
                </li>
                <li><span>页面：</span>
                    <%--<input type="text" name="model" id="model" value="${item.model}"/>--%>
                    <select name="model" id="model" onchange="selectSource()">
                        <option
                                <c:if test="${item.model =='11'}">selected="selected"</c:if> value="11">整站
                        </option>
                        <option
                                <c:if test="${item.model =='0'}">selected="selected"</c:if> value="0">首页
                        </option>
                        <option
                                <c:if test="${item.model =='1'}">selected="selected"</c:if> value="1">新房
                        </option>
                        <option
                                <c:if test="${item.model =='2'}">selected="selected"</c:if> value="2">二手房
                        </option>
                        <option
                                <c:if test="${item.model =='3'}">selected="selected"</c:if> value="3">租房
                        </option>
                        <option
                                <c:if test="${item.model =='4'}">selected="selected"</c:if> value="4">写字楼
                        </option>
                        <option
                                <c:if test="${item.model =='5'}">selected="selected"</c:if> value="5">商铺
                        </option>
                        <option
                                <c:if test="${item.model =='6'}">selected="selected"</c:if> value="6">小区
                        </option>
                        <option
                                <c:if test="${item.model =='7'}">selected="selected"</c:if> value="7">经纪人
                        </option>
                        <option
                                <c:if test="${item.model =='8'}">selected="selected"</c:if> value="8">外销网
                        </option>
                        <option
                                <c:if test="${item.model =='9'}">selected="selected"</c:if> value="9">市场研究
                        </option>
                        <option
                                <c:if test="${item.model =='10'}">selected="selected"</c:if> value="10">资讯
                        </option>
                    </select>
                </li>
                <%--<li><span>META关键词：</span>--%>
                    <%--<input type="text" name="metaLable" id="metaLable" value="${item.metaLable}"/>--%>
                <%--</li>--%>
                <%--<li><span>META描述：</span>--%>
                    <%--<input type="text" name="metaDesc" id="metaDesc" value="${item.metaDesc}"/>--%>
                <%--</li>--%>
                <%--<li><span>标题：</span>--%>
                    <%--<input type="text" name="title" id="title" value="${item.title}"/>--%>
                <%--</li>--%>
                <li><span>CNZZ状态：</span>
                    <%--<input type="text" name="metaShow" id="metaShow" value="${item.metaShow}"/>--%>
                    <span style="width: 50px !important;"> 开启&nbsp; </span><input
                            <c:if test="${item.metaShow=='1'}">checked="checked"</c:if> type="radio" name="metaShow"
                            value="1">
                    <span style="width: 50px !important;"> 关闭&nbsp; </span><input
                            <c:if test="${item.metaShow=='0'}">checked="checked"</c:if> type="radio" name="metaShow"
                            value="0">
                </li>
                <li id="cnzzPc"
                    <c:if test="${item.source=='1'}">style='display: none'</c:if> ><span>CNZZ配置：</span><script
                        style="width: 87%;min-height: 250px;resize:none; outline-color: #0099e0;float: left"
                        name="cnzzCode" id="myEditor" rows="" cols="">${item.cnzzCode}</script></li>
                <li id="cnzzWechat"
                    <c:if test="${item.source=='0'}">style='display: none'</c:if> ><span>CNZZ微站配置：</span><script
                        style="width: 87%;min-height: 250px;resize:none; outline-color: #0099e0;float: left"
                        name="cnzzCodeWechat" id="myEditor2" rows="" cols="">${item.cnzzCodeWechat}</script></li>
                <li><span>百度计量状态：</span>
                    <%--<input type="text" name="baiduShow" id="baiduShow" value="${item.baiduShow}"/>--%>
                    <span style="width: 50px !important;"> 开启&nbsp; </span><input
                            <c:if test="${item.baiduShow=='1'}">checked="checked"</c:if> type="radio" name="baiduShow"
                            value="1">
                    <span style="width: 50px !important;"> 关闭&nbsp; </span><input
                            <c:if test="${item.baiduShow=='0'}">checked="checked"</c:if> type="radio" name="baiduShow"
                            value="0">
                </li>
                <li id="baiduPc"
                    <c:if test="${item.source=='1'}">style='display: none'</c:if> ><span>百度计量代码：</span><script
                        style="width: 87%;min-height: 250px;resize:none; outline-color: #0099e0;float: left"
                        name="baiduCode" id="myEditor1" rows="" cols="">${item.baiduCode}</script></li>
                <li id="baiduWechat"
                    <c:if test="${item.source=='0'}">style='display: none'</c:if> ><span>百度计量微站代码：</span><script
                        style="width: 87%;min-height: 250px;resize:none; outline-color: #0099e0;float: left;"
                        name="baiduCodeWechat" id="myEditor3" rows="" cols="">${item.baiduCodeWechat}</script></li>

            </ul>
            <ul class="adminfo row">
                <li>
                    <span></span>
                    <a onclick="saveData();" target="contentF" class="public_btn bg2">保存</a>
                    <a style="margin-left: 20px" onclick="closeWin()" target="contentF"
                       class="public_btn bg3" id="cancel">取消</a>
                </li>
            </ul>
        </form>
    </section>
</div>
</body>
<script type="text/javascript">
    UE.getEditor("myEditor");
    UE.getEditor("myEditor1");
    UE.getEditor("myEditor2");
    UE.getEditor("myEditor3");

    //保存数据
    function saveData() {
        var data = $("#formId").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/pageConf/update",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("修改成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        setTimeout(function () {
                            parent.layer.closeAll();
                            parent.$("#inquery").click();
                        }, 1000);
                    }, 1000);

                } else {
                    layer.msg("修改失败！", {icon: 2});
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

    //取消
    function closeWin() {
        parent.layer.closeAll();
    }


    function selectSource() {
        //网站
        if ($("#source option:selected").val() == 0) {
            $("#cnzzPc").show();
            $("#baiduPc").show();
            $("#cnzzWechat").hide();
            $("#baiduWechat").hide();
            //微站
        } else if ($("#source option:selected").val() == 1) {
            $("#cnzzPc").hide();
            $("#baiduPc").hide();
            $("#cnzzWechat").show();
            $("#baiduWechat").show();
        }

    }

    function setCityName() {
        $("#cityName").val($("#cityId option:selected").text())
    }


</script>
</html>