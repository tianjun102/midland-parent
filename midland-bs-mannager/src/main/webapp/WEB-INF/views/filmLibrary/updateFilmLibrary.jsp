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
        .content ul.adminfo li>span{
            float: left;
            display: inline-block;
            width: 90px;
            height: 28px;
            line-height: 28px;
            text-align: right;
            font-size: 14px;
            color: rgb( 102, 102, 102 );
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $('#file_upload').uploadify({
                'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
                'uploader': '${ctx }/rest/upload/img',
                'multi': false,// 是否支持多个文件上传
                'buttonText': '上传文件',
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
                'onUploadSuccess': function (file, data, response) {
                    $("#videoUrl").attr("value", data);
                    $("#videoUrl1").attr("src", "${ctx}/assets/UEditor/dialogs/attachment/fileTypeImages/"+getFileIcon(data));
                    $("#fileUrl").html(    '<a style="font-size:12px; color:#0066cc;"  title="' + data.substr(data.lastIndexOf('/')+1) + '">' + data.substr(data.lastIndexOf('/')+1) + '</a>' );
                },
                'onQueueComplete': function (queueData) {
                    if (queueData.uploadsSuccessful < 1) {
                        alert('文件上传失败');
                    }
                }

                // Your options here
            });

            var data = '${item.videoUrl}';
            $("#videoUrl1").attr("src", "${ctx}/assets/UEditor/dialogs/attachment/fileTypeImages/"+getFileIcon(data));
            $("#fileUrl").html(    '<a style="font-size:12px; color:#0066cc;"  title="' + data.substr(data.lastIndexOf('/')+1) + '">' + data.substr(data.lastIndexOf('/')+1) + '</a>' );
        })

    </script>

</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/filmLibrary/add" method="post" id="dataForm">
        <ul class="adminfo row">
            <input type="hidden" id="id" name="id" value="${item.id}">
            <li style="display:flex;align-items:center">
                <span>类型：</span>
                <select name="filmType" id="filmType" class="dropdown">
                    <option value="0">楼盘展销会</option>
                    <option value="1">看楼团</option>
                </select>
            </li>
            <%@include file="../menu/area.jsp" %>
            <li><span>楼盘ID：</span>
                <input type="text" name="housesId" id="housesId" value="${item.housesId}"/>
            </li>

            <li><span>楼盘名称：</span>
                <input type="text" name="housesName" id="housesName" value="${item.housesName}" >
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
                <input type="text" name="duration" id="duration" value="${item.duration}"/>
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
                    setTimeout(function(){window.open("${ctx}/rest/filmLibrary/index","contentF");},1000);

                } else {
                    layer.msg("保存失败！", {icon: 2});
                }
            },
            error: function () {
                layer.msg("保存失败！", {icon: 2});
            }
        });
    }

    //取消
    function closeWin() {
        window.open("${ctx}/rest/filmLibrary/index","contentF")

    }


</script>

</body>
</html>