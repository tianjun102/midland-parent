<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>
<%@include file="../layout/zTree.jsp"%>
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
    <title>添加资讯</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <meta name="MobileOptimized" content="320">
    <link rel="stylesheet" href="${ctx}/assets/css/ztree/css/demo.css">
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">
    <link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css"/>
</head>
<body >
<style>

    .content ul.adminfo li > span {
        float: left;
        display: inline-block;
        width: 100px;
        height: 38px;
        line-height: 38px;
        text-align: right;
        font-size: 14px;
        color: rgb(102, 102, 102);
    }

    .layui-layer{
        top:260px!important;
    }

    .vipcate{
        width: 250px;
        height: 38px;
        line-height: 38px;
        border: 1px solid #dbe2e6;
        border-radius: 4px;
        text-indent: 10px;
        outline-color: #0099e0;
    }


</style>
<script type="text/javascript">

    var setting = {
        check: {
            enable: true,
            chkboxType: { "Y": "sp", "N": "sp" }


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
    var catProNodes =[{id:0, pId:0,name:'分类',open:true,nocheck:true,iconSkin:"pIcon01"},${categoryData}];


    $(document).ready(function(){
        $.fn.zTree.init($("#categoryTree"), setting, catProNodes);
    });

    function beforeClick(treeId, treeNode, clickFlag) {
        $("input[name='cateId']").val(treeNode.id);
        $("input[name='cateName']").val(treeNode.name);
        $("#showDiv").hide();
    }

    function showTree(event){
        $("#showDiv").show();
    }

    function hideTree(event){
        $("#showDiv").hide();
    }

</script>
<div class="box">
    <section class = "content">
        <p class = "detail-title">
            <span>添加资讯</span>
        </p>
        <form id="formId" action="" method="post" enctype="multipart/form-data" method="post">
            <ul class = "adminfo row">
                <input type="hidden" name="cateName" id="cateName">
                <li><span>会员分类：</span><input class="vipcate" name="cateName" onclick="showTree()" readonly="readonly"/>
                    <input name="cateId" type="hidden"/><label style="color: red" class = "_star " >*</label>

                </li>
                <li  id="showDiv" style="display: none;padding-top: 0px;padding-left: 70px; position:relative;" >
                    <div class="zTreeDemoBackground left" style  = "position:absolute;left: 100px;"   onblur="test(event)">
                        <ul id="categoryTree" class="ztree" style  = "width:235px; height: 140px!important;"></ul>
                    </div>
                    <img  src="${ctx}/assets/img/Closed_16px.png"  alt="关闭" style="vertical-align: top;position:absolute; left: 340px;" onclick="hideTree()">
                </li>
                <li>
                    <span style = "float:left;">城市：</span>
                    <input type="hidden" name="cityName" id="cityName" value="">
                    <select onchange="setCityName()" name="cityId" id="cityId" style="height: 38px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="">全部</option>
                        <c:forEach items="${cityList}" var="city">
                            <option value="${city.id}">${city.name}</option>
                        </c:forEach>
                    </select>
                </li>
                <li>
                    <span>标题：</span>
                    <input type="text" name="title"  />
                </li>
                <li><span>来源：</span><input name="source" id="source" type="text">
                </li>
                <li><span>附件：</span>
                    <div style="float: left;">
                        <input type="hidden" name="enclosure" id="enclosure" value="${item.iconImg}">

                        <img style="margin-bottom: 10px;max-width:200px;max-height:200px" id="iconImg1"
                             src="${item.iconImg}">
                        <input type="file" name="file_upload" id="file_upload"/>
                    </div>
                </li>
                <li>
                    <span>META关键字：</span>
                    <input type="text" name="metaKeywords"  />
                </li>
                <li>
                    <span>META描述：</span>
                    <input type="text" name="metaDesc"  />
                </li>

                <li><span>缩略图：</span>
                <div style="float: left;">
                        <input type="hidden" name="imgUrl" id="imgUrl" value="${item.iconImg}">

                        <img style="margin-bottom: 10px;max-width:200px;max-height:200px" id="iconImg2"
                             src="${item.iconImg}">
                        <input type="file" name="file_upload1" id="file_upload1"/>
                    </div>
                </li>
                <li><span>图片说明：</span><input type="text" name="imgDesc"></li>
                <li style="overflow: hidden" id="textArea"><span style = "float:left;">页面内容：</span><textarea style="width: 90%;min-height: 350px;resize:none; outline-color: #0099e0;float: right" name="details" id="myEditor" rows="" cols=""></textarea></li>
            </ul>




            <ul class = "adminfo row">
                <li>
                    <span></span>
                    <a onclick="subumintInformation();" target="contentF" class = "public_btn bg2">保存</a>
                    <a style="margin-left: 20px" href="${ctx}/rest/research/index" target="contentF" class="public_btn bg3" id="cancel">取消</a>
                </li>
            </ul>
        </form>
    </section>
</div>
</body>
<script type="text/javascript">
    UE.getEditor('myEditor');

    function selectTypes(){
        if($("#selectType option:selected").val()==1){
            $("#searchbatton").show();
            $("#catInfo").show();
            $("#prodInfo").show();
            $("#textArea").hide();
        }else if($("#selectType option:selected").val()==0){
            $("#searchbatton").hide();
            $("#catInfo").hide();
            $("#prodInfo").hide();
            $("#picLike").hide();
            $("#textArea").show();
        }else{
            $("#searchbatton").hide();
            $("#catInfo").hide();
            $("#prodInfo").hide();
            $("#textArea").hide();
            $("#picLike").show();
        }

    }

    function subumintInformation(){
        var data = $("#formId").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/research/add",
            async: false, // 此处必须同步
            dataType: "json",
            data:data ,
            success: function (data) {
                if(data.state==0){
                    layer.msg("保存成功！",{icon:1});
                    setTimeout(function(){window.open("${ctx}/rest/research/index","contentF");},2000);
                } else {
                    layer.msg("新增失败！", {icon: 2});
                }
            },
            error: function () {
                layer.msg("新增失败！", {icon: 2});
            }

        });

    }



    $(function() {
        $(".filepath").on("change",function() {
//          alert($('.imgbox').length);
            FileExt=this.value.substr(this.value.lastIndexOf(".")).toLowerCase();

            if(AllowExt!=0&&AllowExt.indexOf(FileExt+"|")==-1) //判断文件类型是否允许上传
            {
                ErrMsg="\n该文件类型不允许上传。请上传 "+AllowExt+" 类型的文件，当前文件类型为"+FileExt;
                $(this).val("");
                layer.alert(ErrMsg);
                return false;
            }
            ImgFileSize=document.getElementById(this.id).files.item(0).size;
            ImgFileSize=Math.round(ImgFileSize*1000/(1024*1024))/1000;//取得图片文件的大小
            if(ImgFileSize>1){
                layer.alert("图片大小为"+ImgFileSize+"M，请上传小于1M的图片！");
                return false;
            }
            var srcs = getObjectURL(this.files[0]);   //获取路径
            $(this).nextAll(".img1").hide();   //this指的是input
            $(this).nextAll(".img2").show();  //fireBUg查看第二次换图片不起做用
            $(this).nextAll('.close').show();   //this指的是input
            $(this).nextAll(".img2").attr("src",srcs);    //this指的是input
            //$(this).val('');    //必须制空
            $(".close").on("click",function() {
                $(this).hide();     //this指的是span
                $(this).nextAll(".img2").hide();
                $(this).nextAll(".img1").show();
                $(this).prev().val('');
            })
        })
    })


    function setCityName(){
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
            'buttonText': '上传附件',
            'onUploadSuccess': function (file, data, response) {
                console.log(data);
                $("#enclosure").attr("value", data);
                $("#iconImg1").attr("src", "${fileUrl}"+data);
            },
            'onQueueComplete': function (queueData) {
                if (queueData.uploadsSuccessful < 1) {
                    alert('文件上传失败');
                }
            }

            // Your options here
        });

        $('#file_upload1').uploadify({
            'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
            'uploader': '${ctx }/rest/upload/img',
            'multi': false,// 是否支持多个文件上传
            'buttonText': '上传图片',
            'onUploadSuccess': function (file, data, response) {
                console.log(data);
                $("#imgUrl").attr("value", data);
                $("#iconImg2").attr("src", "${fileUrl}"+data);
            },
            'onQueueComplete': function (queueData) {
                if (queueData.uploadsSuccessful < 1) {
                    alert('文件上传失败');
                }
            }

        });

    })

    function setCateName(){
        $("#cateName").val($("#cateId option:selected").text())
    }

</script>
</html>