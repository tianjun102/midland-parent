
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8" />
    <title>页面配置</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <meta name="MobileOptimized" content="320">
    <style>
        .content ul.adminfo li > span {
            float: left;
            display: inline-block;
            width: 130px;
            height: 38px;
            line-height: 38px;
            text-align: right;
            font-size: 14px;
            color: rgb(102, 102, 102);
        }

        .layui-layer{
            top:260px!important;
        }
    </style>
</head>
<body >
<div class="box">
    <section class = "content">
        <p class = "detail-title">
            <span>页面配置</span>
        </p>
        <form id="formId" action="${ctx}/rest/pageConf/add" method="post" enctype="multipart/form-data" method="post">
            <input type="hidden" name="cityName" id="cityName" value="" >
            <ul class = "adminfo row">
                <li><span>城市：</span>
                    <select onchange="setCityName();" name="cityId" id="cityId" style="height: 38px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="">全部</option>
                        <c:forEach items="${cityList}" var="city">
                            <option value="${city.id}">${city.name}</option>
                        </c:forEach>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li><span>平台：</span>
                    <select name="source" id="source" style="height: 38px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" onchange="selectSource()">
                        <option value="0">网站</option>
                        <option value="1">微站</option>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li><span>页面：</span>
                    <%--<input type="text" name="model" id="model" value=""/>--%>
                    <select name="model" id="model" style="height: 38px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="0">首页</option>
                        <option value="1">新房</option>
                        <option value="2">二手房</option>
                        <option value="3">租房</option>
                        <option value="4">写字楼</option>
                        <option value="5">商铺</option>
                        <option value="6">小区</option>
                    </select>
                </li>
                <li><span>META关键词：</span>
                    <input type="text" name="metaLable" id="metaLable" value=""/>
                </li>
                <li><span>CNZZ状态：</span>
                    <%--<input type="hidden" name="metaShow" id="metaShow" value=""/>--%>
                    <span style="width: 50px;"> 开启&nbsp; </span><input type="radio" name="metaShow" value="1">
                    <span style="width: 50px;"> 关闭&nbsp; </span><input type="radio" name="metaShow" value="0">
                </li>
                <li><span>百度计量状态：</span>
                    <%--<input type="hidden" name="baiduShow" id="baiduShow" value=""/>--%>
                   <span style="width: 50px;"> 开启&nbsp; </span><input type="radio" name="baiduShow" value="1">
                   <span style="width: 50px;"> 关闭&nbsp; </span><input type="radio" name="baiduShow" value="0">
                </li>
                <li><span>META描述：</span>
                    <input type="text" name="metaDesc" id="metaDesc" value=""/>
                </li>
                <li><span>标题：</span>
                    <input type="text" name="title" id="title" value=""/>
                </li>
                <li id="cnzzPc"><span>CNZZ配置：</span><textarea style="width: 87%;min-height: 250px;resize:none; outline-color: #0099e0;float: right" name="cnzzCode" id="myEditor" rows="" cols=""></textarea></li>
                <li id="baiduPc"><span>百度计量代码：</span><textarea style="width: 87%;min-height: 250px;resize:none; outline-color: #0099e0;float: right" name="baiduCode" id="myEditor1" rows="" cols=""></textarea></li>

                <li id="cnzzWechat" style="display: none"><span>CNZZ微站配置：</span><textarea style="width: 87%;min-height: 250px;resize:none; outline-color: #0099e0;float: right" name="cnzzCodeWechat" id="myEditor2" rows="" cols=""></textarea></li>
                <li id="baiduWechat" style="display: none"><span>百度计量微站代码：</span><textarea style="width: 87%;min-height: 250px;resize:none; outline-color: #0099e0;float: right;" name="baiduCodeWechat" id="myEditor3" rows="" cols=""></textarea></li>

            </ul>
            <ul class = "adminfo row">
                <li>
                    <span></span>
                    <a onclick="saveData();" target="contentF" class = "public_btn bg2">保存</a>
                    <a style="margin-left: 20px" href="${ctx}/rest/pageConf/index" target="contentF" class="public_btn bg3" id="cancel">取消</a>
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
            url: "${ctx}/rest/pageConf/add",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("保存成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        setTimeout(function(){window.open("${ctx}/rest/pageConf/index","contentF");},1000);
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


    function selectSource(){
        //网站
        if($("#source option:selected").val()==0){
            $("#cnzzPc").show();
            $("#baiduPc").show();
            $("#cnzzWechat").hide();
            $("#baiduWechat").hide();
         //微站
        }else if($("#source option:selected").val()==1){
            $("#cnzzPc").hide();
            $("#baiduPc").hide();
            $("#cnzzWechat").show();
            $("#baiduWechat").show();
        }

    }

    function setCityName(){
        $("#cityName").val($("#cityId option:selected").text())
    }




</script>
</html>