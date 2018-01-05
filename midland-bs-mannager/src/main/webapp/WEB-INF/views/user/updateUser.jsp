<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
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
                    $("#headImg").attr("value", data);
                    $("#iconImg").attr("src", data);
                },
                'onQueueComplete': function (queueData) {
                    if (queueData.uploadsSuccessful < 1) {
                        alert('文件上传失败');
                    }
                }

                // Your options here
            });
        })


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
            return;
        };
    </script>

</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/filmLibrary/add" method="post" id="dataForm">
        <ul class="adminfo row">
            <input style="width:264px;" type="hidden" name="id" id="id" value="${item.id}"/>
            <input style="width:264px;" type="hidden" name="userRoles" id="userRoles" value="${item.userRoles}"/>
            <li class="col-md-6"><span>用户名：</span><input style="width:264px;" type="text" name="username"
                                                         disabled="true" id="username" value="${item.username}"
                                                         onblur="checkUserName();" maxlength="50"/></li>
            <li class="col-md-6"><span>用户名称：</span><input style="width:264px;" type="text" name="userCnName"
                                                          id="userCnName"
                                                          onblur="notEmpty('userCnName','userCnName','用户昵称不能为空')"
                                                          value="${item.userCnName}" maxlength="50"/><span
                    class="_star">*</span></li>
            <li style="display:flex;align-items:center" class="col-md-6">
                <span>平台：</span>
                <select name="source" id="source" class="dropdown" style="width: 264px">
                    <c:forEach items="${sources}" var="s">
                        <option value="${s.id}" <c:if test="${s.id == item.source}">selected="selected"</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>
                </select>
                <span class="_star">*</span>
            </li>
            <%@include file="../menu/area_up_required1.jsp" %>
            <span class="_star">*</span>
            <li class="col-md-6"><span>手机号码：</span><input style="width:264px;" type="text" name="phone" disabled="true"
                                                          id="phone" value="${item.phone}" onblur="checkPhone();"/></li>
            <li class="col-md-6"><span>邮箱：</span><input style="width:264px;" type="text" name="email" id="email"
                                                        value="${item.email}" onblur="checkEmail();"/></li>

            <li><span>图片上传：</span>
                <div style="width: 250px;float: left;">
                    <input type="hidden" name="headImg" id="headImg">

                    <img style="margin-bottom: 10px;max-width:80px;max-height:80px" src="${item.headImg}" id="iconImg">
                    <input type="file" name="file_upload" id="file_upload"/>
                </div>
            </li>

            <li style="padding-top:30px;" class="col-md-6">
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="saveData()">保存</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>

    </form>
</section>

<script type="text/javascript">
    function saveData() {
        if (checkUserName() &&
            notEmpty('userCnName', 'userCnName', '用户昵称不能为空') &&
            checkSelect('citys', '请选择市级') &&
            checkPhone() &&
            checkEmail()
        ) {
            var id = $("#id").val();
            var username = $("#username").val();
            var userCnName = $("input[name='userCnName']").val();
            var userType = $("#userType option:selected").val();
            var source = $("#source option:selected").val();
            var phone = $("input[name='phone']").val();
            var email = $("input[name='email']").val();
            var headImg = $("input[name='headImg']").val();
            var userRoles = $("input[name='userRoles']").val();
            debugger;
//            var userRoles = "";
//            $('input[name="userRoles"]:checked').each(function () {
//                userRoles += $(this).val() + ",";
//            });

            $.ajax({
                type: "post",
                url: "${ctx}/rest/user/edit",
                async: false, // 此处必须同步
                dataType: "json",
                data: {
                    "isFlag": 1,
                    "id": id,
                    "username": username,
                    "userCnName": userCnName,
                    "userType": userType,
                    source: source,
                    "phone": phone,
                    "email": email,
                    "userRoles": userRoles,
                    "headImg": headImg
                },
                success: function (data) {
                    if (data.state == 0) {
                        layer.msg("修改成功！！！", {icon: 1});
                        $('#save').removeAttr("onclick");
                        setTimeout(function () {
                            parent.location.reload();
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
    }


    function checkUserName() {
        var regUserName = /^[a-zA-Z0-9_]{6,20}$/;
        var userName = $("#username").val();
        if (userName == null || userName.trim() == "") {
            //$("#userNameCheck").text("用户名不能为空！");
            layer.tips("用户名不能为空！", "input[name='username']", {tips: 1});
            return false;
        }
        if ("admin" != userName.trim()&&!regUserName.test(userName.trim())) {
            layer.tips("仅支持英文、数字和下划线,长度为6-20个字符！", "input[name='username']", {tips: 1});
            return false;
        }
        var a = true;
        <%--$.ajax({ --%>
        <%--type: "post", --%>
        <%--url: "${ctx }/rest/user/checkUnique",--%>
        <%--async:false, // 此处必须同步--%>
        <%--dataType: "json",--%>
        <%--data:{"userName":userName},--%>
        <%--success: function(xmlobj){ --%>
        <%--if (xmlobj.flag==1){--%>
        <%--layer.tips("该用户已存在！", "input[name='username']",{tips:1});--%>
        <%--a=false;--%>
        <%--}else{--%>
        <%----%>
        <%--a=true;--%>
        <%--}--%>
        <%--} --%>
        <%--});--%>
        return a;
    }

    //检查手机号格式
    function checkPhone() {
        var reg = /^1[3,4,5,7,8]\d{9}$/;
        var phone = $("input[name='phone']").val();
        if (phone.trim() == '') {
            layer.tips("手机号不能为空！", "input[name='phone']", {tips: 3});
            return false;
        }
        if (!reg.test(phone)) {
            layer.tips("手机号格式有误,请核对!", "input[name='phone']", {tips: 3});
            $("input[name='phone']").focus();
            return false;
        }
        var a = true;
        <%--$.ajax({ --%>
        <%--type: "post", --%>
        <%--url: "${ctx }/rest/user/checkPhoneUnique",--%>
        <%--async:false, // 此处必须同步--%>
        <%--dataType: "json",--%>
        <%--data:{"phone":phone},--%>
        <%--success: function(xmlobj){ --%>
        <%--if (xmlobj.flag==1){--%>
        <%--layer.tips("当前手机号码已被使用，请更换手机号码！", "input[name='phone']",{tips:1});--%>
        <%--a=false;--%>
        <%--}else{--%>
        <%--a=true;--%>
        <%--}--%>
        <%--} --%>
        <%--});--%>
        return a;
    }

    //检查邮箱格式
    function checkEmail() {
        var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        var email = $("input[name='email']").val();
        if (email.trim() == '') {
            //layer.tips("邮箱不能为空！", "input[name='email']",{tips:3});
            return true;
        }
        if (!reg.test(email)) {
            layer.tips("邮箱格式有误,请核对!", "input[name='email']", {tips: 3});
            $("input[name='email']").focus();
            return false;
        }
        return true;
    }

    //取消
    function closeWin() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }
</script>

</body>
</html>