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
    <title>修改会员</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <meta name="MobileOptimized" content="320">
    <link rel="stylesheet" href="${ctx}/assets/css/ztree/css/demo.css">
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">
    <link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css"/>
</head>
<body>
<div class="box">
    <section class="content">
        <p class="detail-title">
            <span>修改会员</span>
        </p>
        <form id="formId" action="${ctx}/rest/banner/addBanner" method="post" enctype="multipart/form-data"
              method="post">
            <ul class="adminfo  width-lg row">
                <input type="hidden" name="id" id="id" value="${item.id}">
                <%@include file="../menu/area_required.jsp" %>
                <li><span>会员级别：</span>
                    <input type="hidden" name="cateId" id="cateId" value="${item.cateId}"/>
                    <input type="hidden" name="cateName" id="cateName" value="${item.cateName}">
                    <select id="cates" name="cates">
                        <c:forEach items="${vipCateGory}" var="s">
                            <option value="${s.id}"
                                    <c:if test="${s.id==item.cateId}">selected="selected"</c:if>>${s.cateName}</option>
                        </c:forEach>
                    </select>
                </li>
                <li><span>会员分类：</span>
                    <input type="text" name="level" id="level" value="${item.level}"/>
                </li>
                <li><span>中文名称：</span>
                    <input type="text" name="cname" id="cname" value="${item.cname}"/>
                </li>
                <li><span>英文名称：</span>
                    <input type="text" name="ename" id="ename" value="${item.ename}"/>
                </li>
                <li><span>所属地区：</span>
                    <input type="text" name="address" id="address" value="${item.address}"/>
                </li>
                <li><span>职位：</span>
                    <input type="text" name="post" id="post" value="${item.post}"/>
                </li>
                <li><span>图片：</span>
                    <div style="float: left;">
                        <input type="hidden" name="imgUrl" id="imgUrl" value="${item.imgUrl}">

                        <img style="margin-bottom: 10px;max-width:200px;max-height:200px" id="iconImg1"
                             src="${item.imgUrl}">
                        <input type="file" name="file_upload" id="file_upload"/>
                    </div>
                </li>
                <li><span>照片描述：</span>
                    <input type="text" name="imgDesc" id="imgDesc" value="${item.imgDesc}"/>
                </li>

            </ul>

            <ul class="adminfo row">
                <li>
                    <span></span>
                    <a onclick="updateData();" target="contentF" class="public_btn bg2">保存</a>
                    <a style="margin-left: 20px" onclick="closeWin()" target="contentF" class="public_btn bg3"
                       id="cancel">取消</a>
                </li>
            </ul>
        </form>
    </section>
</div>
</body>
<script type="text/javascript">


    $(function () {
        if (${item.cateId==null}) {
            if (${vipCateGory.size()>0}) {
                $("#cateId").val("${vipCateGory[0].id}");
                $("#cateName").val("${vipCateGory[0].cateName}");
            }
        }
    })


    $("#cates").change(function () {
        $("#cateId").val($("#cates option:selected").val());
        if ($("#cates option:selected").text() == "请选择") {
            $("#cateName").val("");
        } else {
            $("#cateName").val($("#cates option:selected").text());
        }

    })

    $("#citys").change(function () {
        $("#cateId").val("");
        $("#cateName").val("");
        var data = $("#cityId").val();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/eliteVip/getVipCate",
            async: false, // 此处必须同步
            dataType: "json",
            data: {cityId: data},
            success: function (data) {
                if (data.state == 0) {
                    $("#cates").html("<option  value=''>请选择</option>");
                    data.result.forEach(function (list) {

                        $("#cates").append(
                            "<option value=" + list.id + " >" + list.cateName + "</option>");
                    })

                } else {
                    layer.msg("获取失败！", {icon: 2});
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
    })

    //保存数据
    function updateData() {
        if (checkSelect("citys|cates", "请选择城市!|请选择会员级别!", "请选择会员级别!")) {


            var data = $("#formId").serialize();
            $.ajax({
                type: "post",
                url: "${ctx}/rest/eliteVip/update",
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
                $("#iconImg1").attr("src", data);
            },
            'onQueueComplete': function (queueData) {
                if (queueData.uploadsSuccessful < 1) {
                    alert('文件上传失败');
                }
            }

            // Your options here
        });
    })


</script>
</html>