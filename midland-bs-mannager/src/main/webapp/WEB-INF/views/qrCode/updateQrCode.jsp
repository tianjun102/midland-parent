<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
    </script>

</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/qrCode/add" method="post" id="dataForm">
        <input type="hidden" name="cityName" id="cityName" value="${item.cityName}" >
        <input type="hidden" name="id" id="id" value="${item.id}" >
        <ul class="userinfo row">
            <li>
                <span style = "float:left;">城市：</span>
                <select onclick="setCityName();" name="cityId" id="cityId" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" <c:if test="${empty isSuper}">disabled="disabled"</c:if> >
                    <c:forEach items="${cityList}" var="city">
                        <option <c:if test="${city.id == item.cityId}">selected = 'selected' </c:if> value="${city.id}">${city.name}</option>
                    </c:forEach>
                </select>
            </li>
            <li><span>平台：</span>
                <select name="source" id="source" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option <c:if test="${item.source == '0'}">selected = 'selected'</c:if> value="0">网站</option>
                    <option <c:if test="${item.source == '1'}">selected = 'selected'</c:if> value="1">微站</option>
                </select>
            </li>
            <li><span>名称：</span>
                <input type="text" name="name" style="width: 250px!important;" id="name" value="${item.name}" onblur="notEmpty('linkName','linkName','名称不能为空！')"/>
                <span class = "_star ">*</span>
            </li>
            <li><span>图片：</span>
                <div style="float: left;">
                    <input type="hidden" name="imgUrl" id="imgUrl" value="${item.imgUrl}">

                    <img style="margin-bottom: 10px;max-width:100px;max-height:100px" id="iconImg1"
                         src="${item.imgUrl}">
                    <input type="file" name="file_upload" id="file_upload"/>
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
    //保存数据
    function updateData() {
        if(!notEmpty('name','name','名称不能为空！')){
            return;
        }
        var data = $("#dataForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/qrCode/update",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("修改成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        parent.layer.closeAll();
                        parent.$("#inquery").click();
                    }, 1000);

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

    //取消
    function closeWin() {
        parent.layer.closeAll();
    }

    $(function () {
        $('#file_upload').uploadify({
            'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
            'uploader': '${ctx }/rest/upload/img',
            'multi': false,// 是否支持多个文件上传
            'buttonText': '上传图片',
            'onUploadSuccess': function (file, data, response) {
                console.log(data);
                $("#urlImg").attr("value", data);
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

    function setCityName(){
        $("#cityName").val($("#cityId option:selected").text());
    }
</script>
</body>
</html>