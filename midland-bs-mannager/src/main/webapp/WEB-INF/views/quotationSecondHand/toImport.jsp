<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Insert title here</title>
    <script type="text/javascript">
        $(function () {
            $('#file_upload').uploadify({
                'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
                'uploader': '${ctx }/rest/upload/excel_read',
                'method': 'get',
                'multi': false,// 是否支持多个文件上传
                'onUploadStart': function (file) {
                    $("#file_upload").uploadify("settings", "formData", {
                        'cityId': $("input[name='cityId']").val(),
                        'cityName': unicode($("input[name='cityName']").val().trim()),
                        'provinceId': $("input[name='provinceId']").val(),
                        'provinceName': unicode($("input[name='provinceName']").val().trim()),
                        'readType': '0'
                    });

                },
                'onUploadSuccess': function (fileObj, data, response) {
                    if (data=="成功"){
                        $("#file_upload-queue").html(data);
                    }else{
                        $("#file_upload-queue").html('<span class="_star">'+data+'</span>');
                    }

                }
            });
        })



        function unicode(str) {
            var value = '';
            for (var i = 0; i < str.length; i++) {
                value += '\\u' + left_zero_4(parseInt(str.charCodeAt(i)).toString(16));
            }
            return value;
        }
        function left_zero_4(str) {
            if (str != null && str != '' && str != 'undefined') {
                if (str.length == 2) {
                    return '00' + str;
                }
            }
            return str;
        }
    </script>
</head>
<body>
<section class="content" style="border:none;">
    <%@include file="../menu/area.jsp" %>
    <li>
        <div style="width: 250px;float: left;">
            <img style="margin-bottom: 10px;max-width:80px;max-height:80px" id="iconImg1">
            <input type="file" name="file_upload" id="file_upload"/>
        </div>
    </li>
</section>

</body>
</html>