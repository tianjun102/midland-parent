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
    <title>添加Banner图</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <meta name="MobileOptimized" content="320">
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">


    <style>
        .layui-layer-msg {
            top: 260px !important;
        }
    </style>
</head>
<body>
<div class="box">
    <section class="content">
        <p class="detail-title">
            <span>添加Banner</span>
        </p>
        <form id="formId" action="${ctx}/rest/banner/addBanner" method="post" enctype="multipart/form-data"
              method="post">
            <input type="hidden" name="cityName" id="cityName" value="${cityName}">
            <c:if test="${empty isSuper}"><input type="hidden" name="cityId" value="${cityId}"></c:if>
            <ul class="adminfo width-lg row">
                <li><span>展示方式：</span>
                    <select name="type" id="selectType" class="dropdown" onchange="selectTypes();">
                        <option value="0">单页面</option>
                        <option value="2">外网链接</option>
                    </select>
                    <span class="_star ">*</span>
                </li>
                <li>
                    <span>上线时间：</span>
                    <input type="text" name="startTime" id="startTime" class="Wdate half" value="${item.startTime}"
                           onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'})"/>
                    <em class="gang">-</em>
                    <input type="text" name="endTime" id="endTime" class="Wdate half" value="${item.endTime}"
                           onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'})"/>
                </li>
                <li><span>城市：</span>
                    <select onchange="setCityName();" name="cityId" id="cityId"

                            <c:if test="${empty isSuper}">disabled="disabled"</c:if>>
                        <option value="">全部</option>
                        <c:forEach items="${cityList}" var="city">
                            <c:if test="${empty isSuper}">
                                <option selected="selected" value="${cityId}">${cityName}</option>
                            </c:if>
                            <option value="${city.id}">${city.name}</option>
                        </c:forEach>
                    </select>
                    <span class="_star ">*</span>
                </li>
                <li><span>平台：</span>
                    <select name="source" id="source">
                        <option value="0">网站</option>
                        <option value="1">微站</option>
                    </select>
                    <span class="_star ">*</span>
                </li>
                <li><span>模块：</span>
                    <select name="model" id="model">
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
                    <span class="_star ">*</span>
                </li>
                <li><span>位置：</span>
                    <select name="position" id="position">
                        <option value="0">位置１</option>
                        <option value="1">位置２</option>
                        <option value="2">位置３</option>
                        <option value="3">位置４</option>
                    </select>
                    <span class="_star ">*</span>
                </li>
                <li><span>图片：</span>
                    <div style="float: left;">
                        <input type="hidden" name="bannerImg" id="bannerImg" value="${item.bannerImg}">

                        <img style="margin-bottom: 10px;max-width:200px;max-height:200px" id="iconImg1"
                             src="${item.bannerImg}">
                        <input type="file" name="file_upload" id="file_upload"/>
                    </div>
                </li>
                <li><span>图片说明：</span><input type="text" name="imgDesc" placeholder="图片的alt"></li>
                <!-- <li><span>小图：</span><img  src="" width="120px" height="100px" alt=""><input type="file" name="file2" id="file2" onchange="previewImg(this)"></li> -->
                <li id="picLike" style="display: none;"><span>图片链接：</span>
                    <input id="bannerLinkurl" name="bannerLinkurl" maxlength="255" type="text"
                           onblur="checkUrl('bannerLinkurl','bannerLinkurl','网址格式不正确！')" value="">
                </li>
                <li id="nofollowId" style="display: none;"><span>nofollow：</span>
                    <label class="checkitem">
                        <input type="radio" name="nofollow" class="nofollow" value="1"  >
                        <span>是</span></label>
                    <label class="checkitem">
                        <input type="radio" name="nofollow" class="nofollow" value="0" checked="checked" >
                        <span>否</span></label>
                </li>
                <li id="textArea" style="display: block;"><textarea
                        style="width: 92%;min-height: 350px;resize:none; outline-color: #0099e0;float: right"
                        name="detail" id="myEditor" rows="" cols=""></textarea>
                </li>

            </ul>
            <ul class="adminfo row">
                <li>
                    <span></span>
                    <a onclick="subumintBanner();" target="contentF" class="public_btn bg2">保存</a>
                    <a style="margin-left: 20px" onclick="closeWin()" target="contentF"
                       class="public_btn bg3" id="cancel">取消</a>
                </li>
            </ul>
        </form>
    </section>
</div>
</body>
<script type="text/javascript">


    $("#model").change(function () {
        if ($("#model").val()=="12"){
            $("#position").html(" <option value=\"0\">招聘首页</option>")
            $("#position").append(" <option value=\"1\">校园职位</option>")
            $("#position").append(" <option value=\"2\">招聘流程</option>")
            $("#position").append(" <option value=\"3\">走进美联</option>")
            $("#position").append(" <option value=\"4\">校园福利</option>")
        }else {
            $("#position").html("<option value=\"0\">位置１</option>")
            $("#position").append("<option value=\"1\">位置２</option>")
            $("#position").append("<option value=\"2\">位置３</option>")
            $("#position").append("<option value=\"3\">位置４</option>")
        }
    })

    var ImgObj = new Image();   //建立一个图像对象
    var AllImgExt = ".jpg|.jpeg|.gif|.bmp|.png|"//全部图片格式类型
    var FileObj, ImgFileSize, ImgWidth, ImgHeight, FileExt, ErrMsg, FileMsg, HasCheked, IsImg//全局变量 图片相关属性

    //以下为限制变量
    var AllowExt = ".jpg|.jpeg|.png|" //允许上传的文件类型 ŀ为无限制 每个扩展名后边要加一个"|" 小写字母表示
    var AllowImgFileSize = 1;  //允许上传图片文件的大小 0为无限制  单位：M
    var AllowImgWidth = 225;   //允许上传的图片的宽度 ŀ为无限制　单位：px(像素)
    var AllowImgHeight = 140;   //允许上传的图片的高度 ŀ为无限制　单位：px(像素)
    HasCheked = true;
    UE.getEditor('myEditor');

    function addIsShow() {
        if ($('input[name="isshow"]:checked ').val() == "0") {
            $("#enabled").val("0");
        }
        if ($('input[name="isshow"]:checked ').val() == "1") {
            $("#enabled").val("1");
        }
    }


    function closeWin() {
        parent.layer.closeAll();
    }

    //图片预览
    function previewImg(t) {

        if (t.value == "") return false;

        FileExt = t.value.substr(t.value.lastIndexOf(".")).toLowerCase();

        if (AllowExt != 0 && AllowExt.indexOf(FileExt + "|") == -1) //判断文件类型是否允许上传
        {
            ErrMsg = "\n该文件类型不允许上传。请上传 " + AllowExt + " 类型的文件，当前文件类型为" + FileExt;
            $(t).val("");
            layer.alert(ErrMsg);
            return false;
        }

        var src = window.URL.createObjectURL(document.getElementById(t.id).files.item(0));
        ImgObj.src = src;

        ImgFileSize = document.getElementById(t.id).files.item(0).size;
        ImgFileSize = Math.round(ImgFileSize * 1000 / (1024 * 1024)) / 1000;//取得图片文件的大小
        console.log("图片大小为" + ImgFileSize)
        if (ImgFileSize == 0) {
            var url = window.URL.createObjectURL(document.getElementById(t.id).files.item(0));
            $(t).prev().attr("src", "");
            HasCheked = false;
            return false;
        } else {
            var url = window.URL.createObjectURL(document.getElementById(t.id).files.item(0));
            $(t).prev().attr("src", url);
            HasCheked = true;
            return true;
        }

    }

    function selectTypes() {
       if ($("#selectType option:selected").val() == 0) {
            $("#searchbatton").hide();
            $("#catInfo").hide();
            $("#prodInfo").hide();
            $("#picLike").hide();
            $("#textArea").show();
            $("#nofollowId").hide();
        } else {
            $("#searchbatton").hide();
            $("#catInfo").hide();
            $("#prodInfo").hide();
            $("#textArea").hide();
            $("#picLike").show();
            $("#nofollowId").show();
        }

    }

    function subumintBanner() {
        if (!(notEmpty('startTime', 'startTime', '开始时间不能为空！') && notEmpty('endTime', 'endTime', '结束时间不能为空！') && checkSelect("source|model|position|cityId", "平台不能为空！|模块不能为空！|位置不能为空！|城市不能为空!"))) {
            return;
        }
        debugger;
        if ($("#picLike").attr("style") != 'display: none;') {
            if (!checkUrl('bannerLinkurl', 'bannerLinkurl', '网址格式不正确！')) {
                return;
            }
        }
        var data = $("#formId").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/setting/addBanner",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.result == "ok") {
                    layer.msg("保存成功！", {icon: 1});
                    setTimeout(function () {
                        parent.layer.closeAll();
                        parent.$("#inquery").click();
                    }, 2000);
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


    $(function () {
        $(".filepath").on("change", function () {
//          alert($('.imgbox').length);
            FileExt = this.value.substr(this.value.lastIndexOf(".")).toLowerCase();

            if (AllowExt != 0 && AllowExt.indexOf(FileExt + "|") == -1) //判断文件类型是否允许上传
            {
                ErrMsg = "\n该文件类型不允许上传。请上传 " + AllowExt + " 类型的文件，当前文件类型为" + FileExt;
                $(this).val("");
                layer.alert(ErrMsg);
                return false;
            }
            ImgFileSize = document.getElementById(this.id).files.item(0).size;
            ImgFileSize = Math.round(ImgFileSize * 1000 / (1024 * 1024)) / 1000;//取得图片文件的大小
            if (ImgFileSize > 1) {
                layer.alert("图片大小为" + ImgFileSize + "M，请上传小于1M的图片！");
                return false;
            }
            var srcs = getObjectURL(this.files[0]);   //获取路径
            $(this).nextAll(".img1").hide();   //this指的是input
            $(this).nextAll(".img2").show();  //fireBUg查看第二次换图片不起做用
            $(this).nextAll('.close').show();   //this指的是input
            $(this).nextAll(".img2").attr("src", srcs);    //this指的是input
            //$(this).val('');    //必须制空
            $(".close").on("click", function () {
                $(this).hide();     //this指的是span
                $(this).nextAll(".img2").hide();
                $(this).nextAll(".img1").show();
                $(this).prev().val('');
            })
        })
    })


    function setCityName() {
        $("#cityName").val($("#cityId option:selected").text())
    }


    function getObjectURL(file) {
        var url = null;
        if (window.createObjectURL != undefined) {
            url = window.createObjectURL(file)
        } else if (window.URL != undefined) {
            url = window.URL.createObjectURL(file)
        } else if (window.webkitURL != undefined) {
            url = window.webkitURL.createObjectURL(file)
        }
        return url
    };

    $(function () {
        $('#file_upload').uploadify({
            'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
            'uploader': '${ctx }/rest/upload/img',
            'multi': false,// 是否支持多个文件上传
            'buttonText': '上传图片',
            'onUploadSuccess': function (file, data, response) {
                console.log(data);
                $("#bannerImg").attr("value", data);
                $("#iconImg1").attr("src",  data);
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