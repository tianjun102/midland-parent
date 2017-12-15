<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="${ctx }/assets/scripts/uploadify/uploadify.css">
    <script type="text/javascript" src="${ctx }/assets/scripts/uploadify/jquery.uploadify.min.js"></script>
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
                'multi': true,// 是否支持多个文件上传
                'buttonText': '上传文件',
                'onUploadSuccess': function (file, data, response) {
                    $(".fileupload").append("<span class='fileupload-item'><img src='" + data + "'><i class='xclose'>×</i></span>")
                    $("#imgUrl").attr("value", data + "||" + $("#imgUrl").val());



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
    <form action="${ctx}/rest/tradeFair/update" method="post" id="dataForm">
        <ul class="userinfo updInfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <li>
                <span>类型：</span>
                <select name="tradeType" id="tradeType" class="dropdown">
                    <option value="0"
                            <c:if test="${item.tradeType==0}">selected</c:if> >楼盘展销会
                    </option>
                    <option value="1"
                            <c:if test="${item.tradeType==1}">selected</c:if> >看楼团
                    </option>
                </select>
            </li>
            <li><span style="vertical-align: top;">图片上传：</span>
                <div class="fileupload">
                    <input type="hidden" name="imgUrl" id="imgUrl" value="${item.imgUrl}">
                    <input type="file" name="file_upload" id="file_upload"/>
                    <c:forEach items="${item.imgUrlList }" var="s">
                        <span class='fileupload-item'><img src='${s}'><i class='xclose'>×</i></span>
                    </c:forEach>
                </div>
            </li>
            <li><span>楼盘ID：</span>
                <input type="text" name="housesId" id="housesId" value="${item.housesId}"/>
            </li>

            <li><span>楼盘名称：</span>
                <input type="text" name="title" id="title" value="${item.title}"/>
            </li>
            <li><span>简介：</span>
                <textarea class="textarea-sm" rows="" cols="" name="introduction"
                          id="introduction">${item.introduction}</textarea>
            </li>

            <li>
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="updateData()">保存</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>


            </li>
        </ul>

    </form>
</section>

<script type="text/javascript">
    //保存数据



    $(".fileupload").delegate(".xclose","click", function () {
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
    function updateData() {
        var data = $("#dataForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/tradeFair/update",
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
             error: function (data) {                        if (data.responseText!= null){                            layer.msg(data.responseText, {icon: 2});                        }else {                            layer.msg("保存失败！", {icon: 2});                        }                    }
        });
    }

    //取消
    function closeWin() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }


</script>

</body>
</html>