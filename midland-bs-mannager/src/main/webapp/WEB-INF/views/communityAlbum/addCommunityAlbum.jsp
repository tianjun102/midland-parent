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
        .fileupload .fileupload-item {
            display: inline-block;
            position: relative;
            width: 110px;
            height: 64px;
            margin: 10px 10px 0 0;
            overflow: hidden;
            border: 1px solid #ccc;
        }

        .fileupload-item img {
            max-width: 100%;
            max-height: 100%;
        }

        .fileupload-item .xclose {
            display: block;
            position: absolute;
            right: 0;
            top: 0;
            width: 16px;
            height: 16px;
            line-height: 16px;
            text-align: center;
            background: rgba(0, 0, 0, .7);
            font-size: 14px;
            color: #ddd;
            cursor: pointer;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $('#file_upload').uploadify({
                'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
                'uploader': '${ctx }/rest/upload/img',
                'multi': false,// 是否支持多个文件上传
                'buttonText': '上传文件',
                'fileTypeExts': '*.bmp;*.jpg;*.png;*.tiff;*.gif;*.pcx;*.tga;*.exif;*.fpx;*.svg;*.psd;*.cdr;*.pcd;*.dxf;*.ufo;*.eps;*.ai;*.raw;*.WMF',                'onSelectError': uploadify_onSelectError,
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
    <form action="${ctx}/rest/communityAlbum/add" method="post" id="dataForm">
        <ul class="userinfo updInfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <input type="hidden" name="hotHandId" id="hotHandId" value="${item.hotHandId}">
            <li ><span>类型：</span>
                <select name="type" id="type" class="dropdown">
                    <option value="0" <c:if test="${item.type == 0}">selected</c:if> >实景图</option>
                    <option value="1" <c:if test="${item.type == 1}">selected</c:if> >户型图</option>
                </select>
            </li>
            <li><span style="vertical-align: top;">图片上传：</span>
                <div style="width: 250px;display: inline-block;">
                    <input type="hidden" name="imgUrl" id="imgUrl" value="${item.imgUrl}">

                    <img style="margin-bottom: 10px;max-width:80px;max-height:80px" id="iconImg1" src="${item.imgUrl}">
                    <input type="file" name="file_upload" id="file_upload"/>
                </div>
            </li>

            <li><span>图片描述：</span>
                <input type="text" name="description" id="description" value="${item.description}"/>
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
    //保存数据
    function updateData() {
        var data = $("#dataForm").serialize();
        debugger;
        $.ajax({
            type: "post",
            url: "${ctx}/rest/communityAlbum/add",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("保存成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        parent.location.reload();
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
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }
</script>
</body>
</html>