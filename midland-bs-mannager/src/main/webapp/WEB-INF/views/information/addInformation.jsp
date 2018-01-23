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
        /*.content ul.userinfo>li {
            float: none !important;
            margin-left: 20px;
            padding-top: 20px;
        }*/

        .dropdown {
            width: 274px !important;
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
    <section class="content">
        <p class="detail-title">
            <span>添加资讯</span>
        </p>
        <form id="formId" action="" method="post" enctype="multipart/form-data" method="post">
            <ul class="adminfo width-lg  row">
                <li>
                    <span style="float:left;">城市：</span>
                    <c:if test="${empty isSuper}"><input type="hidden" name="cityId" value="${cityId}"></c:if>
                    <input type="hidden" name="cityName" id="cityName" value="${cityName}">
                    <select onchange="setCityName()" name="cityId" id="cityId"
                            <c:if test="${empty isSuper}"> disabled="disabled"</c:if> >
                        <c:forEach items="${cityList}" var="city">
                            <c:if test="${empty isSuper}">
                                <option selected="selected" value="${cityId}">${cityName}</option>
                            </c:if>
                            <option value="${city.id}">${city.name}</option>
                        </c:forEach>
                    </select>
                </li>
                <li><span>平台：</span>
                    <select name="source" id="source">
                        <option value="0">网站</option>
                        <option value="1">微站</option>
                    </select>
                    <span class="_star ">*</span>
                </li>
                <li>
                    <span>父节点：</span>
                    <div class="tree-select">
                        <input name="cateName" type="text" onclick="showTree()" readonly="readonly"/>
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
                    <input type="text" name="title" id="title"
                           onfocus="checkSelect('cateParentid|cateId','请填写一级分类！|请填写二级分类！')"
                           onblur="notEmpty('title','title','标题不能为空！');"/>
                    <span class="_star ">*</span>
                </li>
                <li><span>平台：</span><input name="platform" id="platform" type="text" value="${item.platform}" />

                <li><span>附件：</span>
                    <div style="float: left;">
                        <input type="hidden" name="enclosure" id="enclosure" value="${item.iconImg}">

                        <img style="margin-bottom: -2px;max-width:200px;max-height:200px" id="iconImg1"
                             src="${item.iconImg}">
                        <span id="fileUrl"></span>
                        <input type="file" name="file_upload" id="file_upload"/>
                    </div>
                </li>
                <li>
                    <span>META关键字：</span>
                    <input type="text" name="metaKeywords"/>
                </li>
                <li>
                    <span>META描述：</span>
                    <input type="text" name="metaDesc"/>
                </li>
                <li>
                    <span>作者：</span>
                    <input type="text" name="author"/>
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
                <li style="overflow: hidden" id="textArea1">
                    <span style="float:left;">摘要：</span>
                    <textarea class="textarea-md" name="summary"></textarea>
                </li>
                <li style="overflow: hidden" id="textArea">
                    <span style="float:left;">页面内容：</span>
                    <textarea class="textarea-lg" name="details" id="myEditor" rows="" cols=""></textarea>
                </li>
            </ul>


            <ul class="adminfo row">
                <li>
                    <span></span>
                    <a onclick="subumintInformation();" target="contentF" class="public_btn bg2">保存</a>
                    <a style="margin-left: 20px" onclick="closeWin()" target="contentF"
                       class="public_btn bg3" id="cancel">取消</a>
                </li>
            </ul>
        </form>
    </section>
</div>
</div>
</body>
<script type="text/javascript">
    UE.getEditor('myEditor');
    UE.getEditor('myEditor1');

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

    function subumintInformation() {
        if (notEmpty('title', 'title', '标题不能为空！')) {
            var data = $("#formId").serialize();
            $.ajax({
                type: "post",
                url: "${ctx}/rest/information/add",
                async: false, // 此处必须同步
                dataType: "json",
                data: data,
                success: function (data) {
                    if (data.state == 0) {
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
                        layer.msg("保存失败！", {icon: 2});
                    }
                }

            });
        }

    }
    function closeWin() {
    parent.layer.closeAll();
    }
    $("#source").change(function () {
        setEmpty();
        $("#cityName").val($("#cityId option:selected").text());
    })
    function setCityName() {
        setEmpty();
        $("#cityName").val($("#cityId option:selected").text())
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
                $("#iconImg1").attr("src", "${ctx}/assets/UEditor/dialogs/attachment/fileTypeImages/" + getFileIcon(data));
                $("#fileUrl").html('<a style="font-size:12px; color:#0066cc;"  title="' + data.substr(data.lastIndexOf('/') + 1) + '">' + data.substr(data.lastIndexOf('/') + 1) + '</a>');
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
            'fileTypeExts': '*.jpg;*.png;*.bmp;*.tiff;*.gif',
            'onUploadSuccess': function (file, data, response) {
                console.log(data);
                $("#imgUrl").attr("value", data);
                $("#iconImg2").attr("src", "${fileUrl}" + data);
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

    function setChildCateName() {
        var cateId = $("#cateParentid option:selected").val();

        $.ajax({
            type: "post",
            url: "${ctx}/rest/category/findChildList?cateId=" + cateId,
            async: false, // 此处必须同步
            dataType: "json",
            data: "",
            success: function (data) {
                console.log(data);
                $("#cateId").html("<option value='' >请选择</option>");
                data.forEach(function (list) {
                    $("#cateId").append(
                        "<option value=" + list.id + " >"
                        + list.cateName + "</option>");
                })
            },
            error: function () {
                layer.msg("新增失败！", {icon: 2});
            }

        });

    }

</script>
</html>