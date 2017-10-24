<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">
    <link rel="stylesheet" type="text/css" href="${ctx }/assets/scripts/uploadify/uploadify.css">
    <script type="text/javascript" src="${ctx }/assets/scripts/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx }/assets/scripts/uploadify/jquery.uploadify.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#file_upload').uploadify({
                'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
                'uploader': '${ctx }/rest/upload/img',
                'multi': false,// 是否支持多个文件上传
                'onUploadSuccess': function (file, data, response) {
                    $("#iconImg").attr("value", data);
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
    <form action="${ctx}/rest/menu/add" method="post" id="dataForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <%@include file="area.jsp" %>

            <li style="display:flex;align-items:center">
                <span>平台：</span>
                <select name="source" id="source" class="dropdown" onchange="fieldChange()">
                    <c:forEach items="${sources}" var="s">
                        <option value="${s.id}" <c:if test="${s.id == item.source}">selected="selected"</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>
                </select>
            </li>
            <li style="display: none" id="acreageShow">
                <span style="width:90px">菜单分类：</span>
                <select name="" id="parentIdTemp" class="dropdown" onchange="chooseParent()">
                    <c:forEach items="${menuTypes}" var="s">
                        <option value="${s.id}">${s.name}</option>
                    </c:forEach>
                </select>
                <input type="text" id="parentId" name="menuTypeId" value="0">
            </li>
            <li><span>菜单名：</span>
                <input type="text" name="menuName" id="menuName" value="${item.menuName}"/>
            </li>
            <li><span>链接：</span>
                <input type="text" name="url" id="url" value="${item.url}"/>
            </li>
            <li><span>图标：</span>
                <div style="float: left;">
                    <input type="hidden" name="iconImg" id="iconImg" value="${item.iconImg}">

                    <img style="margin-bottom: 10px;max-width:80px;max-height:80px" id="iconImg1"
                         src="${item.iconImg}">
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

    function fieldChange() {
        var j = $("#source option:selected").val()
        if (j==0){
            $("#acreageShow").css('display','none');

        }else if (j==1){
            $("#acreageShow").css('display','block');
        }
    }

    function chooseParent() {
        $("#parentId").val($("#parentIdTemp option:selected").val())
        if ($("#parentIdTemp option:selected").text()=="根节点"){
            $("#parentName").val()
        }else{
            $("#parentName").val($("#parentIdTemp option:selected").text())
        }
    }



    //保存数据
    function updateData() {
        var data = $("#dataForm").serialize();

        $.ajax({
            type: "post",
            url: "${ctx}/rest/menu/add",
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

    //取消
    function closeWin() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }


</script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>


</body>
</html>