<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>

</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/appointment/add" method="post" id="dataForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <li><span>appointSn：</span>
               <input type="text" name="appointSn" id="appointSn" ß/>
            </li>
            <li><span>source：</span>
               <input type="text" name="source" id="source" ß/>
            </li>
            <li><span>nickName：</span>
               <input type="text" name="nickName" id="nickName" ß/>
            </li>
            <li><span>phone：</span>
               <input type="text" name="phone" id="phone" onblur="checkPhone('','phone','')"/>
            </li>
            <li><span>houseType：</span>
               <input type="text" name="houseType" id="houseType" ß/>
            </li>
            <li><span>sellRent：</span>
               <input type="text" name="sellRent" id="sellRent" ß/>
            </li>
            <li><span>appointmentTime：</span>
               <input type="text" name="appointmentTime" id="appointmentTime" ß/>
            </li>
            <li><span>areaName：</span>
               <input type="text" name="areaName" id="areaName" ß/>
            </li>
            <li><span>communityName：</span>
               <input type="text" name="communityName" id="communityName" ß/>
            </li>
            <li><span>address：</span>
               <input type="text" name="address" id="address" ß/>
            </li>
            <li><span>layout：</span>
               <input type="text" name="layout" id="layout" ß/>
            </li>
            <li><span>measure：</span>
               <input type="text" name="measure" id="measure" ß/>
            </li>
            <li><span>price：</span>
               <input type="text" name="price" id="price" ß/>
            </li>
            <li><span>entrustTime：</span>
               <input type="text" name="entrustTime" id="entrustTime" ß/>
            </li>
            <li><span>agentId：</span>
               <input type="text" name="agentId" id="agentId" ß/>
            </li>
            <li><span>agentName：</span>
               <input type="text" name="agentName" id="agentName" ß/>
            </li>
            <li><span>status：</span>
               <input type="text" name="status" id="status" ß/>
            </li>
            <li><span>handleTime：</span>
               <input type="text" name="handleTime" id="handleTime" ß/>
            </li>
            <li><span>decoration：</span>
               <input type="text" name="decoration" id="decoration" ß/>
            </li>
            <li><span>isDelete：</span>
               <input type="text" name="isDelete" id="isDelete" ß/>
            </li>
            <li><span>cityId：</span>
               <input type="text" name="cityId" id="cityId" ß/>
            </li>
            <li><span>cityName：</span>
               <input type="text" name="cityName" id="cityName" ß/>
            </li>
            <li><span>areaId：</span>
               <input type="text" name="areaId" id="areaId" ß/>
            </li>
            <li><span>flag：</span>
               <input type="text" name="flag" id="flag" ß/>
            </li>
            <li><span>agentPhone：</span>
               <input type="text" name="agentPhone" id="agentPhone" ß/>
            </li>
            <li><span>viewUrl：</span>
               <input type="text" name="viewUrl" id="viewUrl" ß/>
            </li>
            <li><span>resetFlag：</span>
               <input type="text" name="resetFlag" id="resetFlag" ß/>
            </li>
            <li><span>assignedTime：</span>
               <input type="text" name="assignedTime" id="assignedTime" ß/>
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
        $.ajax({
            type: "post",
            url: "${ctx}/rest/appointment/add",
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
                    layer.msg("保存失败！", {icon: 2});
                }
            }
        });
    }

    //取消
    function closeWin() {
        parent.layer.closeAll();
    }
</script>
</body>
</html>