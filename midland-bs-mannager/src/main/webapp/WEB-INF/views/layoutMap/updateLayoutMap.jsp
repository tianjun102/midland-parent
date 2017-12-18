<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style type="text/css">


        .content ul.userinfo li input {
            width: 150px;
            height: 28px;
            line-height: 28px;
            border: 1px solid #dbe2e6;
            border-radius: 4px;
            text-indent: 10px;
            outline-color: #0099e0;
        }

        .dropdown .selected, .dropdown li {
            display: block;
            height: 26px;
            width: 150px;
            line-height: 26px;
            font-size: 14px;
            color: #000;
            padding: 0 10px;
            /* overflow: hidden; */
            white-space: nowrap;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $('#file_upload').uploadify({
                'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
                'uploader': '${ctx }/rest/upload/img',
                'multi': false,// 是否支持多个文件上传
                'buttonText': '上传文件',
                'fileTypeExts': '*.bmp;*.jpg;*.png;*.tiff;*.gif;*.pcx;*.tga;*.exif;*.fpx;*.svg;*.psd;*.cdr;*.pcd;*.dxf;*.ufo;*.eps;*.ai;*.raw;*.WMF',
                'onSelectError': uploadify_onSelectError,
                'onUploadSuccess': function (file, data, response) {
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

</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/layoutMap/update" method="post" id="dataForm">
        <ul class="userinfo updInfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <input type="hidden" name="hotHandId" id="hotHandId" value="${item.hotHandId}">

            <li><span>类型：</span>
                <select name="type" id="type" class="dropdown">
                    <option value="0"
                            <c:if test="${item.type == 0}">selected</c:if> >一室
                    </option>
                    <option value="1"
                            <c:if test="${item.type == 1}">selected</c:if> >二室
                    </option>
                    <option value="2"
                            <c:if test="${item.type == 2}">selected</c:if> >三室
                    </option>
                </select>
            </li>
            <li><span>朝向：</span>
                <select name="turned" id="turned" class="dropdown">
                    <c:forEach items="${turneds}" var="s">
                        <option value="${s.id}" <c:if test="${s.id==entrust.turned}">selected</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>
                </select>
            </li>
            <li><span>标题：</span>
                <input type="text" name="title" id="title" value="${item.title}" onblur="notEmpty('title','title','')"/>
            </li>

            <li><span>面积：</span>
                <input type="text" name="acreage" id="acreage" value="${item.acreage}"
                       onblur="InitInput.setNumber(this,9,2,2)"/> ㎡
            </li>
            <li><span>均价：</span>
                <input type="text" name="avgPrice" id="avgPrice" value="${item.avgPrice}"
                       onblur="InitInput.setNumber(this,9,2,2)"/> 元/m²
            </li>
            <li><span>在售套数：</span>
                <input type="text" name="saleingNum" id="saleingNum" value="${item.saleingNum}"
                       onblur="InitInput.setNumber(this,9,0,0)"/> 套
            </li>
            <li><span>价格：</span>
                <input type="text" name="price" id="price" value="${item.price}"
                       onblur="InitInput.setNumber(this,9,2,2)"/> 万元
            </li>
            <li><span style="vertical-align: top;">图片上传：</span>
                <div style="width: 250px;display: inline-block;">
                    <input type="hidden" name="imgUrl" id="imgUrl" value="${item.imgUrl}">
                    <input type="file" name="file_upload" id="file_upload"/>
                    <img style="margin-top: 10px;max-width:80px;max-height:80px" id="iconImg1" src="${item.imgUrl}">
                </div>
            </li>
            <li>
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="updateData()">更新</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>

    </form>
</section>

<script type="text/javascript">

    $(".fileupload").delegate(".xclose", "click", function () {
        var temp = "";
        var $this = $(this);
        var $parent = $this.parent("span");
        var imgsrcs = $("#imgUrl").val();
        var imgsrc = $parent.find("img").attr("src");
        var imgArray = imgsrcs.split("||");
        for (var i = 0; i < imgArray.length; i++) {
            if (imgArray[i].match(imgsrc)) {
                continue;
            }
            if (imgArray[i] != "" && imgArray != null) {
                temp += imgArray[i] + "||";
            }
        }
        $("#imgUrl").val(temp);
        $parent.remove();
    });

    //保存数据
    function updateData() {
        var data = $("#dataForm").serialize();
        if (notEmpty('title', 'title', '')) {
            $.ajax({
                type: "post",
                url: "${ctx}/rest/layoutMap/update",
                async: false, // 此处必须同步
                dataType: "json",
                data: data,
                success: function (data) {
                    if (data.state == 0) {
                        layer.msg("保存成功！！！", {icon: 1});
                        $('#save').removeAttr("onclick");
                        setTimeout(function () {
                            window.open("${ctx}/rest/layoutMap/index" , "contentF");
                        }, 1000);

                    } else {
                        layer.msg("保存失败！", {icon: 2});
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
    }

    var uploadify_onSelectError = function (file, errorCode, errorMsg) {
        var msgText = "上传失败\n";
        switch (errorCode) {
            case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                //this.queueData.errorMsg = "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                break;
            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " )";
                break;
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                msgText += "文件大小为0";
                break;
            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                msgText += "文件格式不正确，仅限 " + this.settings.fileTypeExts;
                break;
            default:
                msgText += "错误代码：" + errorCode + "\n" + errorMsg;
        }
        alert(msgText);
    };

    //取消
    function closeWin() {
//        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
//        parent.layer.close(index);
        window.open("${ctx}/rest/layoutMap/index" , "contentF");
    }
</script>
</body>
</html>