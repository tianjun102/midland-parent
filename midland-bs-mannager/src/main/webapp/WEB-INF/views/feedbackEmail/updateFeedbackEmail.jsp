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
    <style type="text/css">
        .dropdown {
            width: 150px!important;
        }
    </style>
</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/feedbackEmail/add" method="post" id="dataForm">
        <ul class="userinfo width-md row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <li>
                <span style = "float:left;">城市：</span>
                <input type="hidden" name="cityName" id="cityName" value="${item.cityName}">
                <select onchange="setCityName()" name="cityId" id="cityId" class="dropdown" <c:if test="${empty isSuper}">disabled="disabled"</c:if>>
                    <option value="">全部</option>
                    <c:forEach items="${cityList}" var="city">
                        <option <c:if test="${city.id==item.cityId}">selected="selected"</c:if> value="${city.id}">${city.name}</option>
                    </c:forEach>
                </select>
            </li>
            <li><span>邮箱：</span>
                <input type="text" value="${item.email}" name="email" id="email" onblur="checkEmail('email','email','邮箱格式错误！')"/>
                <span class = "_star ">*</span>
            </li>
            <li><span>类型：</span>
                <select name="type" id="type" class="dropdown">
                    <option <c:if test="${item.type==0}">selected="selected"</c:if> value="0">总部</option>
                    <option <c:if test="${item.type==1}">selected="selected"</c:if> value="1">分部</option>
                </select>
                <span class = "_star ">*</span>
            </li>
            <li><span>联系人：</span>
                <input type="text" name="contactPerson" id="contactPerson" value="${item.contactPerson}" onblur="notEmpty('contactPerson','contactPerson','联系人不能为空！')"/>
                <span class = "_star ">*</span>
            </li>
            <li><span>联系方式：</span>
                <input type="text" name="phone" id="phone" value="${item.phone}" onblur="checkPhone('phone','phone','手机号码格式不正确！')" />
                <span class = "_star ">*</span>
            </li>
            <li>
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="updateData()">修改</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>

    </form>
</section>

<script type="text/javascript">
    //保存数据
    function updateData() {
        if(!(checkEmail('email','email','邮箱格式错误！')&&checkSelect('type','类型不能为空！')&&notEmpty('contactPerson','contactPerson','联系人不能为空！')&&checkPhone('phone','phone','手机号码格式不正确！'))){
            return;
        }
        var data = $("#dataForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/feedbackEmail/update",
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
                    layer.msg("修改失败！", {icon: 2});
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

    //取消
    function closeWin() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }

    function setCityName(){
        $("#cityName").val($("#cityId option:selected").text())
    }
</script>
</body>
</html>