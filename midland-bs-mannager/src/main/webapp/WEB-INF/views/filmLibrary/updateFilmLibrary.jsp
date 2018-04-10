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
        .dropdown {
            position: relative;
            width: 250px;
            border: 1px solid #ccc;
            cursor: pointer;
            background: #fff;
            border-radius: 3px;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

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

            $('#file_upload1').uploadify({
                'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
                'uploader': '${ctx }/rest/upload/img',
                'multi': false,// 是否支持多个文件上传
                'buttonText': '上传文件',
                'fileTypeExts': '*.rm;*.rmvb;*.wmv;*.avi;*.mp4;*.3gp;*.mkv',
                'onSelectError': uploadify_onSelectError,
                'onUploadSuccess': function (file, data, response) {
                    $("#videoUrl").attr("value", data);
                    $("#videoUrl1").attr("src", "${ctx}/assets/UEditor/dialogs/attachment/fileTypeImages/" + getFileIcon(data));
                    $("#fileUrl").html('<a style="font-size:12px; color:#0066cc;"  title="' + data.substr(data.lastIndexOf('/') + 1) + '">' + data.substr(data.lastIndexOf('/') + 1) + '</a>');
                },
                'onQueueComplete': function (queueData) {
                    if (queueData.uploadsSuccessful < 1) {
                        alert('文件上传失败');
                    }
                }

                // Your options here
            });

            var data = '${item.videoUrl}';
            $("#videoUrl1").attr("src", "${ctx}/assets/UEditor/dialogs/attachment/fileTypeImages/" + getFileIcon(data));
            $("#fileUrl").html('<a style="font-size:12px; color:#0066cc;"  title="' + data.substr(data.lastIndexOf('/') + 1) + '">' + data.substr(data.lastIndexOf('/') + 1) + '</a>');
        })

    </script>

</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/filmLibrary/add" method="post" id="dataForm">
        <ul class="adminfo  width-md row">
            <input type="hidden" id="id" name="id" value="${item.id}">
            <%@include file="../menu/area_required.jsp" %>
            <li style="display:flex;align-items:center">
                <span>类型：</span>
                <select name="filmType" id="filmType" class="dropdown">
                    <option value="0" <c:if test="${item.filmType ==0}"  >selected="selected"</c:if> >住宅</option>
                    <option value="1" <c:if test="${item.filmType ==1}"  >selected="selected"</c:if>>商铺</option>
                    <option value="2" <c:if test="${item.filmType ==2}"  >selected="selected"</c:if>>写字楼</option>
                </select>
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
            <li><span>楼盘ID：</span>
                <input type="text" name="housesId" id="housesId" value="${item.housesId}"/>
            </li>

            <li><span>楼盘名称：</span>
                <input type="text" name="housesName" id="housesName" value="${item.housesName}">
            </li>
            <li><span>图片描述：</span>
                <textarea rows="" cols="" style="width: 250px;height: 70px;border: 1px solid #dbe2e6;"
                          name="imgDesc" id="imgDesc">${item.imgDesc}</textarea>
            </li>
            <li><span>简介：</span>
                <textarea rows="" cols="" style="width: 250px;height: 70px;border: 1px solid #dbe2e6;"
                          name="introduction" id="introduction">${item.introduction}</textarea>
            </li>

            <li><span>视频上传：</span>
                <div style="float: left;">
                    <input type="file" name="file_upload1" id="file_upload1"/>
                    <img style="margin-bottom: -2px;max-width:200px;max-height:200px" id="videoUrl1"
                         src="${item.videoUrl}">
                    <span id="fileUrl"></span>
                    <input type="hidden" name="videoUrl" id="videoUrl" value="${item.videoUrl}">
                </div>
            </li>
            <li><span>视频时长：</span>
                <input type="text" name="duration" id="duration" value="${item.duration}" placeholder="格式要求00:00:00"
                       onblur="InitInput.setTime('duration','duration','时间格式不正确')"/>
            </li>
            <li><span>图片上传：</span>
                <div style="width: 250px;float: left;">
                    <input type="hidden" name="imgUrl" id="imgUrl" value="${item.imgUrl}">

                    <img style="margin-bottom: 10px;max-width:80px;max-height:80px" id="iconImg1" src="${item.imgUrl}">
                    <input type="file" name="file_upload" id="file_upload"/>
                </div>
            </li>

            <li>
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="saveData()">保存</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>

            </li>
        </ul>

    </form>
</section>

<script type="text/javascript">
    $("#cityId").change(function () {
        $("#cityName").val($("#cityId").find("option:selected").text());
    })


    //保存数据
    function saveData() {
        if (checkSelect('citys','请选择市级')&& notEmpty('housesId', 'housesId', '') && notEmpty('housesName', 'housesName', '')
            && InitInput.setTime('duration', 'duration', '时间格式不正确')) {
            var data = $("#dataForm").serialize();

            $.ajax({
                type: "post",
                url: "${ctx}/rest/filmLibrary/update",
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
        parent.layer.closeAll();
    }


</script>

</body>
</html>