<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>

    <style type="text/css">
        .content ul.userinfo > li {
            margin-left: 0;
            padding-top: 8px;
        }

        .content ul.userinfo li > span,
        .content ul.userinfo li:not(:last-child) input,
        .content ul.userinfo ._star,
        .content ul.userinfo .dropdown {
            height: 30px !important;
            line-height: 30px !important;
            vertical-align: middle;
        }

        .content ul.userinfo li input[type=checkbox] {
            float: none;
            width: auto !important;
            height: auto !important;
            margin-right: 3px;
        }

        .peitao {
            height: 30px;
            line-height: 30px;
        }

        .peitao > span {
            padding-right: 15px;
        }

        .peitao > span em {
            vertical-align: middle;
        }
    </style>
</head>
<body>


<section class="content" style="border:none;">
    <form action="${ctx}/rest/appoint/update" method="post" id="appointInfoForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${entrust.id}">
            <li class="col-md-6"><span>预约编号：</span><input type="text" name="entrustSn" id="entrustSn"
                                                          value="${entrust.entrustSn}"/>
            </li>
            <li class="col-md-6"><span>委托时间：</span><input type="text" name="entrustTime" id="entrustTime"
                                                          value="${entrust.entrustTime}" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" maxlength="50"/><span
                    class="_star">*</span>
            </li>
            <li class="col-md-6"><span>委托人：</span><input type="text" name="nickName" id="nickName"
                                                        value="${entrust.nickName}"/>
            </li>
            <li class="col-md-6"><span>手机号码：</span><input type="text" name="phone" id="phone"
                                                          value="${entrust.phone}"
                                                          maxlength="50"/><span class="_star">*</span></li>
            <li class="col-md-6"><span>分类：</span>
                <select name="sellRent" id="sellRent" class="dropdown">
                    <c:forEach items="${sellRents}" var="s">
                        <option value="${s.id}" <c:if test="${s.id==entrust.sellRent}">selected</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>
                </select>
                <span class="_star">*</span>
            </li>

            <li class="col-md-6"><span>委托类型：</span>
                <select name="houseType" id="houseType" class="dropdown">

                    <c:forEach items="${houses}" var="s">
                        <option value="${s.id}" <c:if test="${s.id==entrust.houseType}">selected</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>

                </select>
            </li>

            <li class="col-md-6"><span>区域：</span><input type="text" name="areaName" id="areaName" value="${entrust.areaName}"/>
            </li>
            <li class="col-md-6"><span>小区：</span><input type="text" name="communityName" id="communityName"
                                                        value="${entrust.communityName}" maxlength="50"/><span
                    class="_star">*</span></li>
            <li class="col-md-6"><span>地址：</span><input type="text" name="address" id="address"
                                                        value="${entrust.address}"/>
            </li>
            <li class="col-md-6"><span>户型：</span><input type="text" name="layout" id="layout"
                                                        value="${entrust.layout}"/>

            </li>
            <li class="col-md-6"><span>面积：</span><input type="text" name="measure" id="measure"
                                                        value="${entrust.measure}"
                                                        maxlength="50"/>㎡<span class="_star">*</span></li>
            <li class="col-md-6"><span>装修：</span>
                <select name="renovation" id="renovation" class="dropdown">

                    <c:forEach items="${decorations}" var="s">
                        <option value="${s.id}" <c:if test="${s.id==entrust.renovation}">selected</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>

                </select>

            </li>
            <li class="col-md-6"><span>配套：</span>
                <div class="peitao">
                    <span><input onchange="lick(this,'household')"  type="checkbox"
                                 <c:if test="${entrust.household == 1}">checked</c:if> ><em>带家电</em>
                        <input type="hidden" name="household" id="household" value="${entrust.household}">
                    </span>
                    <span><input onchange="lick(this,'broadband')"  type="checkbox"
                                 <c:if test="${entrust.broadband == 1}">checked</c:if> ><em>宽带</em>
                    <input type="hidden" name="broadband" id="broadband" value="${entrust.broadband}">
                    </span>
                    <span><input onchange="lick(this,'fridge')"  type="checkbox"
                                 <c:if test="${entrust.fridge == 1}">checked</c:if> ><em>冰箱</em>

                    <input type="hidden" name="fridge" id="fridge" value="${entrust.fridge}">
                    </span>
                    <span><input onchange="lick(this,'washingMachine')"  type="checkbox"
                                 <c:if test="${entrust.washingMachine == 1}">checked</c:if> ><em>洗衣机</em>
                    <input type="hidden" name="washingMachine" id="washingMachine" value="${entrust.washingMachine}">
                    </span>
                </div>
            </li>
            <li class="col-md-6"><span>状态：</span>
                <select name="status" id="status" class="dropdown">

                    <c:forEach items="${statusList}" var="s">
                        <option value="${s.id}" <c:if test="${s.id==entrust.status}">selected</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>

                </select>
            </li>
            <li class="col-md-11"><span>备注：</span>
                <textarea name="remark" id="remark"
                          style="width:calc(100% - 120px);height:50px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;"></textarea>
            </li>
            </li>
            <li class="col-md-11"><span>处理记录：</span>
                <textarea name="record" id="record" disabled="disabled"
                          style="width:calc(100% - 120px);height:150px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;">
<c:forEach items="${entrustLogs}" var="s">    状态    ： <c:forEach items="${statusList}" var="m"><c:if test="${m.id==s.state}">${m.name}</c:if> </c:forEach>
    时间    ：${s.logTime}
    操作人：${s.operatorName}
    备注    ：${s.remark}


</c:forEach>
                </textarea></li>
            </li>
            <li class="col-md-6">
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="updateData()">更新</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>

    </form>
</section>


<script type="text/javascript">

    function lick(be,id) {
        if ($(be).is(':checked')) {
            $('#'+id).val(1);
        } else {
            $('#'+id).val(0);
        }
    }


    //保存数据
    function updateData() {
        var data = $("#appointInfoForm").serialize();

        $.ajax({
            type: "post",
            url: "${ctx}/rest/entrust/update",
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
    //检查手机号格式
    function checkPhone() {

        var phone0 = $("#ph").val();
        var reg = /^1[3,4,5,7,8]\d{9}$/;
        var phone = $("input[name='phone']").val();
        if (phone.trim() == '') {
            layer.tips("手机号不能为空！", "input[name='phone']", {tips: 1});
            return false;
        }
        if (!reg.test(phone)) {
            layer.tips("手机号格式有误,请核对!", "input[name='phone']", {tips: 3});
            return false;
        }
        if (phone0 == phone) {
            return true;
        }

        var a = true;
        $.ajax({
            type: "post",
            url: "${ctx }/rest/user/checkPhoneUnique",
            async: false, // 此处必须同步
            dataType: "json",
            data: {"phone": phone},
            success: function (xmlobj) {
                if (xmlobj.flag == 1) {
                    layer.tips("当前手机号码已被使用，请更换手机号码！", "input[name='phone']", {tips: 1});
                    a = false;
                } else {
                    a = true;
                }
            }
        });
        return a;
    }

    //检查邮箱格式
    function checkEmail() {
        var reg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
        var email = $("input[name='email']").val();
        if (email.trim() == '') {
            //layer.tips("邮箱不能为空！", "input[name='email']",{tips:3});
            return true;
        }
        if (!reg.test(email)) {
            layer.tips("邮箱格式有误,请核对!", "input[name='email']", {tips: 3});
            //$("input[name='email']").focus();
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
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>