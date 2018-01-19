<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
<%@include file="../layout/zTree.jsp" %>

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
    <title>添加会员</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <meta name="MobileOptimized" content="320">
    <link rel="stylesheet" href="${ctx}/assets/css/ztree/css/demo.css">
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">
    <link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css"/>
    <style>
        .layui-layer {
            top: 260px !important;
        }

        .vipcate {
            width: 250px;
            height: 28px;
            line-height: 28px;
            border: 1px solid #dbe2e6;
            border-radius: 4px;
            text-indent: 10px;
            outline-color: #0099e0;
        }

        .content ul.adminfo li:last-child > a:not(:nth-child(2)) {
            margin-left: 0px !important;
        }

    </style>
    <script type="text/javascript">

        var setting = {
            check: {
                enable: true,
                chkboxType: {"Y": "sp", "N": "sp"}


            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick
            }
        };
        var catProNodes = [{
            id: 0,
            pId: 0,
            name: '分类',
            open: true,
            nocheck: true,
            iconSkin: "pIcon01"
        }, ${categoryData}];


        $(document).ready(function () {
            $.fn.zTree.init($("#categoryTree"), setting, catProNodes);
        });

        function beforeClick(treeId, treeNode, clickFlag) {
            $("input[name='cateId']").val(treeNode.id);
            $("input[name='cateName']").val(treeNode.name);
            $("#showDiv").hide();
        }

        function showTree(event) {
            $("#showDiv").show();
        }

        function hideTree(event) {
            $("#showDiv").hide();
        }

    </script>
</head>
<body>
<div class="box">
    <section class="content">
        <p class="detail-title">
            <span>添加会员</span>
        </p>
        <form id="formId" action="${ctx}/rest/banner/addBanner" method="post" enctype="multipart/form-data"
              method="post">
            <input type="hidden" name="cityName" id="cityName" value="">
            <ul class="adminfo row">

                <%--<li><span>会员分类：</span>
                    <select id="cateId" name="cateId" class="dropdown" onchange="setCateName();">
                        <option value="1">会员分类1</option>
                        <option value="2">会员分类2</option>
                        <option value="3">会员分类3</option>
                        <option value="4">会员分类4</option>
                    </select>
                </li>--%>
                <li><span>会员分类：</span><input class="vipcate" name="cateName" onclick="showTree()" readonly="readonly"/>
                    <input name="cateId" type="hidden"/><label style="color: red" class="_star ">*</label>

                </li>
                <li id="showDiv" style="display: none;padding-top: 0px;padding-left: 70px; position:relative;">
                    <div class="zTreeDemoBackground left" style="position:absolute;top: -10px;" onblur="test(event)">
                        <ul id="categoryTree" class="ztree" style="width:235px; height: 140px!important;"></ul>
                    </div>
                    <img src="${ctx}/assets/img/Closed_16px.png" alt="关闭"
                         style="vertical-align: top;position:absolute; left: 290px;" onclick="hideTree()">
                </li>
                <li><span>会员等级：</span>
                    <input type="text" name="level" id="level"/>
                </li>
                <li><span>中文名称：</span>
                    <input type="text" name="cname" id="cname"/>
                </li>
                <li><span>英文名称：</span>
                    <input type="text" name="ename" id="ename"/>
                </li>
                <li><span>所属地区：</span>
                    <input type="text" name="address" id="address"/>
                </li>
                <li><span>职位：</span>
                    <input type="text" name="post" id="post"/>
                </li>
                <li><span>图片：</span>
                    <div style="float: left;">
                        <input type="hidden" name="imgUrl" id="imgUrl" value="${item.iconImg}">

                        <img style="margin-bottom: 10px;max-width:200px;max-height:200px" id="iconImg1"
                             src="${item.iconImg}">
                        <input type="file" name="file_upload" id="file_upload"/>
                    </div>
                </li>
                <li><span>照片描述：</span>
                    <input type="text" name="imgDesc" id="imgDesc"/>
                </li>

            </ul>

            <ul class="adminfo row">
                <li>
                    <span></span>
                    <a onclick="updateData();" target="contentF" class="public_btn bg2">保存</a>
                    <a style="margin-left: 20px" onclick="closeWin()" target="contentF"
                       class="public_btn bg3" id="cancel">取消</a>
                </li>
            </ul>
        </form>
    </section>
</div>
</body>
<script type="text/javascript">
    //保存数据
    function updateData() {
        var data = $("#formId").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/eliteVip/add",
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

    $(function () {
        $('#file_upload').uploadify({
            'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
            'uploader': '${ctx }/rest/upload/img',
            'multi': false,// 是否支持多个文件上传
            'buttonText': '上传图片',
            'onUploadSuccess': function (file, data, response) {
                console.log(data);
                $("#imgUrl").attr("value", data);
                $("#iconImg1").attr("src", "${fileUrl}" + data);
            },
            'onQueueComplete': function (queueData) {
                if (queueData.uploadsSuccessful < 1) {
                    alert('文件上传失败');
                }
            }

            // Your options here
        });
    })

    function setCateName() {
        $("#cateName").val($("#cateId option:selected").text())
    }
</script>
</html>