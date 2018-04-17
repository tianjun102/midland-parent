<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
<%@include file="../layout/zTree.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加热门关注</title>
    <link rel="stylesheet" href="${ctx}/assets/css/ztree/css/demo.css">
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">
    <link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css">
    <style type="text/css">
        .content ul.adminfo > li > span {
            float: left;
            display: inline-block;
            width: 100px;
            height: 28px;
            line-height: 28px;
            text-align: right;
            font-size: 14px;
            color: rgb(102, 102, 102);
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
            if(treeNode.id==0){
                return;
            }
            $("input[name='cateId']").val(treeNode.id);
            $("input[name='cateName']").val(treeNode.name);
            $("#showDiv").hide();
        }

        function showTree(event) {
            var data = $("#formId").serialize();
            data+="&type=1";
            $.ajax({
                type: "post",
                url: "${ctx}/rest/siteMap/choose",
                async: false, // 此处必须同步
                dataType: "json",
                data: data,
                success: function (data) {
                    var dfd={id:0, pId:0,name:'分类',open:true,nocheck:true,iconSkin:"pIcon01"};
                    catProNodes =[dfd];
                    $.each(data.list,function (i,listItem) {
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

        function hideTree(event) {
            $("#showDiv").hide();
        }

    </script>
</head>
<body>

<div class="box">
    <section class = "content">
        <p class = "detail-title">
            <span>修改资讯</span>
        </p>
        <form id="formId" action="${ctx}/rest/banner/addBanner" method="post" enctype="multipart/form-data" method="post">
            <ul class = "adminfo row">
                <input type="hidden" name="id" id="id" value="${item.id}">
                <li>
                    <span style = "float:left;">城市：</span>
                    <input type="hidden" name="cityName" id="cityName" value="">
                    <select onchange="setCityName()" name="cityId" id="cityId" style="height: 28px;width: 280px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" <c:if test="${empty isSuper}">disabled="disabled"</c:if> >
                        <c:forEach items="${cityList}" var="city">
                            <option <c:if test="${item.cityId == city.id}"> selected ='selected' </c:if>  value="${city.id}">${city.name}</option>
                        </c:forEach>
                    </select>
                </li>
                <li><span>平台：</span>
                    <select name="source" id="source" style="height: 28px;width: 280px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;padding-left:5px;">
                        <option <c:if test="${item.source==0}">selected="selected"</c:if> value="0" >网站</option>
                        <option <c:if test="${item.source==1}">selected="selected"</c:if> value="1" >微站</option>
                    </select>
                    <span class = "_star ">*</span>
                </li>

                <li>
                    <span>父节点：</span>
                    <div class="tree-select">
                        <input name="cateName" type="text" value="${item.cateName}" onclick="showTree()" readonly="readonly"/>
                        <input name="cateId" type="hidden"/>
                        <div id="showDiv" style="display: none;">
                            <div class="zTreeDemoBackground left" onblur="test(event)">
                                <ul id="categoryTree" class="ztree"></ul>
                            </div>
                            <img class="ztree-close" src="${ctx}/assets/img/Closed_16px.png" alt="关闭" onclick="hideTree()">
                        </div>
                        <div class="warn">(不选父分类则默认一级分类)</div>
                    </div>
                </li>

                <li>
                    <span>标题：</span>
                    <input type="text" id="title" name="title" value="${item.title}" onfocus="checkSelect('cateParentid|cateId','请填写一级分类！|请填写二级分类！')" onblur="notEmpty('title','title','标题不能为空！');" />
                </li>
                <li><span>平台：</span><input name="platform" id="platform" type="text" value="${item.platform}" />
                </li>
                <li><span>附件：</span>
                    <div style="float: left;">
                        <input type="hidden" name="enclosure" id="enclosure" value="${item.enclosure}">

                        <img style="margin-bottom: -2px;max-width:200px;max-height:200px" id="iconImg1"
                             src="${item.enclosure}">
                        <span id="fileUrl"></span>
                        <input type="file" name="file_upload" id="file_upload"/>
                    </div>
                </li>
                <li><span>META关键词：</span>
                    <input type="text" name="metaKeywords" id="metaKeywords" value="${item.metaKeywords}"/>
                </li>
                <li><span>META描述：</span>
                    <input type="text" name="metaDescription" id="metaDescription" value="${item.metaDescription}"/>
                </li>
                <li><span>META标题：</span>
                    <input type="text" name="metaTitle" id="metaTitle" value="${item.metaTitle}"/>
                </li>
                <li>
                    <span>作者：</span>
                    <input type="text" name="author" value="${item.author}"  />
                </li>
                <li><span>缩略图：</span>
                    <div style="float: left;">
                        <input type="hidden" name="imgUrl" id="imgUrl" value="${item.imgUrl}">

                        <img style="margin-bottom: 10px;max-width:200px;max-height:200px" id="iconImg2"
                             src="${item.imgUrl}">
                        <input type="file" name="file_upload1" id="file_upload1"/>
                    </div>
                </li>
                <li><span>图片说明：</span><input value="${item.imgDesc}" type="text" name="imgDesc"></li>
                <li style="overflow: hidden" id="textArea1">
                    <span style="float:left;">摘要：</span>
                    <textarea class="textarea-md" name="summary">${item.summary}</textarea>
                </li>
                <li style="overflow: hidden" id="textArea"><span style="float:left;">页面内容：</span><textarea
                        class="textarea-lg"
                        name="details" id="myEditor" rows="" cols="">${item.details}</textarea></li>
            </ul>




            <ul class = "adminfo row">
                <li>
                    <span></span>
                    <a onclick="subumintInformation();" target="contentF" class = "public_btn bg2">保存</a>
                    <a style="margin-left: 20px" onclick="closeWin()" target="contentF" class="public_btn bg3" id="cancel">取消</a>
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
    $("#source").change(function () {
        setEmpty();
    })
    $("#cityId").change(function () {
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

    function subumintInformation(){
        if(notEmpty('title','title','标题不能为空！')){
        var data = $("#formId").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/information/update",
            async: false, // 此处必须同步
            dataType: "json",
            data:data ,
            success: function (data) {
                if(data.state==0){
                    layer.msg("更新成功！",{icon:1});
                    parent.layer.closeAll();
                    parent.$("#inquery").click();
                } else {
                    layer.msg("更新失败！", {icon: 2});
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
function closeWin() {
    parent.layer.closeAll();
}

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
                $("#iconImg1").attr("src", "${ctx}/assets/UEditor/dialogs/attachment/fileTypeImages/"+getFileIcon(data));
                $("#fileUrl").html(    '<a style="font-size:12px; color:#0066cc;" title="' + data.substr(data.lastIndexOf('/')+1) + '">' + data.substr(data.lastIndexOf('/')+1) + '</a>' );
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
                $("#iconImg2").attr("src",data);
            },
            'onQueueComplete': function (queueData) {
                if (queueData.uploadsSuccessful < 1) {
                    alert('文件上传失败');
                }
            }

            // Your options here
        });

        var data = '${item.enclosure}';
        $("#iconImg1").attr("src", "${ctx}/assets/UEditor/dialogs/attachment/fileTypeImages/"+getFileIcon(data));
        $("#fileUrl").html(    '<a style="font-size:12px; color:#0066cc;"  title="' + data.substr(data.lastIndexOf('/')+1) + '">' + data.substr(data.lastIndexOf('/')+1) + '</a>' );

    })

    function setCateName(){
        $("#cateName").val($("#cateId option:selected").text())
    }


    function setChildCateName(){
        var parentId =  $("#cateParentid option:selected").val();

        $.ajax({
            type: "post",
            url: "${ctx}/rest/category/findChildList?parentId="+parentId,
            async: false, // 此处必须同步
            dataType: "json",
            data:"" ,
            success: function (data) {
                console.log(data);
                $("#cateId").html("<option value='' >请选择</option>");
                data.forEach(function(list) {
                    $("#cateId").append(
                        "<option value="+list.id+" >"
                        + list.cateName + "</option>");
                })
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

</script>
</html>