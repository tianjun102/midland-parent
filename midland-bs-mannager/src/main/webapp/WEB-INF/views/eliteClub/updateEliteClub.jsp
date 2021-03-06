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
    <title>添加Banner图</title>
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
            <span>添加Banner</span>
        </p>
        <form id="formId" action="${ctx}/rest/banner/addBanner" method="post" enctype="multipart/form-data"
              method="post">
            <input type="hidden" name="cityName" id="cityName" value="${item.cityName}">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <ul class="adminfo row">

                <li><span>城市：</span>
                    <select onchange="setCityName();" name="cityId" id="cityId"
                            <c:if test="${empty isSuper}">disabled="disabled"</c:if>>
                        <c:forEach items="${cityList}" var="city">
                            <option
                                    <c:if test="${item.cityId==city.id}">selected="selected"</c:if>
                                    value="${city.id}">${city.name}</option>
                        </c:forEach>
                    </select>
                    <span class="_star ">*</span>
                </li>
                <li>
                    <span>活动时间：</span>
                    <input type="text" name="adTime" class="Wdate half" style="width: 280px;!important;" id="time3"
                           value="${item.adTime}"
                           onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
                </li>
                <li><span>图片：</span>
                    <div style="float: left;">
                        <input type="hidden" name="imgUrl" id="imgUrl" value="${item.imgUrl}">

                        <img style="margin-bottom: 10px;max-width:200px;max-height:200px" id="iconImg1"
                             src="${item.imgUrl}">
                        <input type="file" name="file_upload" id="file_upload"/>
                    </div>
                </li>
                <li><span>图片说明：</span><input type="text" id="imgDesc" name="imgDesc" value="${item.imgDesc}"></li>
                <li><span>活动名称：</span><input type="text" id="adName" name="adName" value="${item.adName}"></li>
                <li><span>活动主题：</span><input type="text" id="adTitle" name="adTitle" value="${item.adTitle}"></li>
                <li><span>活动地点：</span><input type="text" id="adAddress" name="adAddress" value="${item.adAddress}"></li>

                <li id="textArea" style=""><textarea
                        style="width: 92%;min-height: 350px;resize:none; outline-color: #0099e0;float: right"
                        name="detail" id="myEditor" rows="" cols="">${item.detail}</textarea></li>

            </ul>


            <ul class="adminfo row">
                <li>
                    <span></span>
                    <a onclick="subumintElite();" target="contentF" class="public_btn bg2">保存</a>
                    <a style="margin-left: 20px" onclick="closeWin()" target="contentF"
                       class="public_btn bg3" id="cancel">取消</a>
                </li>
            </ul>
        </form>
    </section>
</div>
</body>
<script type="text/javascript">
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

    function delecte(ths) {
        layer.open({
            type: 1,
            skin: 'layer-style',
            area: ['440px', '250px'],
            shadeClose: false, //点击遮罩关闭
            title: ['是否删除'],
            resize: false,
            scrollbar: false,
            content:
            '<section class = "content" style = "border:none;">' +
            '<dl>' +
            '<dt style = "text-align: center; margin-top: 20px;"><img src="${ctx}/assets/img/!.png"/></dt>' +
            '<dd>' +
            '<p style = "text-align: center; margin-top: 20px;">您确定要删除当前用户列表吗</p>' +
            '</dd>' +
            '</dl>' +
            '</section>',
            btn: ['确定', '取消'],
            btnAlign: 'c',
            yes: function (index) {
                var catId = $("#cat_Id").val();
                $(ths).parent().parent().remove();
                var array = new Array();
                $("input:hidden[name$='].prodId']").each(function () {
                    array.push($(this).val());
                });
                if ($("input:hidden[name$='].prodId']").size() > 0) {
                    $("#bannerId").val("/banner/bannerinfo?productId=" + array + "&catId=" + catId);
                } else {
                    $("#bannerId").val("");
                }
                layer.close(index);
                layer.msg("操作成功！", {icon: 1});
            }
            , success: function (layero) {
                var btn = layero.find('.layui-layer-btn');
                btn.css('text-align', 'center');
            }
        });

    }

    function deleteText() {


        layer.open({
            type: 1,
            skin: 'layer-style',
            area: ['440px', '250px'],
            shadeClose: false, //点击遮罩关闭
            title: ['是否删除'],
            resize: false,
            scrollbar: false,
            content:
            '<section class = "content" style = "border:none;">' +
            '<dl>' +
            '<dt style = "text-align: center; margin-top: 20px;"><img src="${ctx}/assets/img/!.png"/></dt>' +
            '<dd>' +
            '<p style = "text-align: center; margin-top: 20px;">将删除添加的商品</p>' +
            '</dd>' +
            '</dl>' +
            '</section>',
            btn: ['确定', '取消'],
            btnAlign: 'c',
            yes: function (index) {
                $("#cat_Id").val("");
                $("input[name='isAll']").val("");
                $("#catName").html("");
                $("#productList").find("tr").remove();
                $("#bannerId").val("");
                layer.close(index);
                layer.msg("操作成功！", {icon: 1});
            }
        });

    }

    function selectTypes() {
        if ($("#selectType option:selected").val() == 1) {
            $("#searchbatton").show();
            $("#catInfo").show();
            $("#prodInfo").show();
            $("#textArea").hide();
        } else if ($("#selectType option:selected").val() == 0) {
            $("#searchbatton").hide();
            $("#catInfo").hide();
            $("#prodInfo").hide();
            $("#picLike").hide();
            $("#textArea").show();
        } else {
            $("#searchbatton").hide();
            $("#catInfo").hide();
            $("#prodInfo").hide();
            $("#textArea").hide();
            $("#picLike").show();
        }

    }

    function subumintElite() {
        var data = $("#formId").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/eliteClub/update",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == "0") {
                    layer.msg("修改成功！", {icon: 1});
                    setTimeout(function () {
                        parent.layer.closeAll();
                        parent.$("#inquery").click();
                    }, 2000);
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
                $("#imgUrl").attr("value", data);
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