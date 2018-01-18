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
        .content ul.userinfo li>span{
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
</head>
<body>


<section class="content" style="border:none;">
    <form action="${ctx}/rest/entrust/buy/add" method="post" id="appointInfoForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${entrust.id}">
            <input type="hidden" name="resetFlag" id="resetFlag" value="1">
            <li class="col-md-6"><span>预约编号：</span><input type="text" name="entrustSn" id="entrustSn" onblur="notEmpty('entrustSn','entrustSn','')"
                                                          value="${entrust.entrustSn}"/>
            </li>
            <li class="col-md-6"><span>委托时间：</span><input type="text" name="entrustTime" id="entrustTime" onblur="notEmpty('entrustTime','entrustTime','')"
                                                          value="${entrust.entrustTime}" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" maxlength="50"/>
                <span  class="_star">*</span>
            </li>
            <li class="col-md-6"><span>委托人：</span><input type="text" name="nickName" id="nickName" onblur="notEmpty('nickName','nickName','')"
                                                        value="${entrust.nickName}"/>
            </li>
            <li class="col-md-6"><span>平台：</span>
                <select name="source" id="source" class="dropdown">
                <c:forEach items="${sources}" var="s">
                    <option value="${s.id}">
                            ${s.name}
                    </option>
                </c:forEach>
                </select>
            </li>
            <li class="col-md-6"><span>手机号码：</span><input type="text" name="phone" id="phone"
                                                          value="${entrust.phone}"
                                                          maxlength="50" onblur="checkPhone('','phone','')"/><span class="_star">*</span></li>
            <%@include file="../menu/area.jsp" %>
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

            <li class="col-md-6"><span>区域：</span><input type="text" name="areaName" id="areaName" onblur="notEmpty('areaName','areaName','')"  value="${entrust.areaName}"/>
            </li>
            <li class="col-md-6"><span>小区：</span><input type="text" name="communityName" id="communityName" onblur="notEmpty('communityName','communityName','')"
                                                        value="${entrust.communityName}" maxlength="50"/><span
                    class="_star">*</span></li>
            <li class="col-md-6"><span>地址：</span><input type="text" name="address" id="address" onblur="notEmpty('address','address','')"
                                                        value="${entrust.address}"/>
            </li>
            <li class="col-md-6"><span>户型：</span><input type="text" name="layout" id="layout" onblur="notEmpty('layout','layout','')"
                                                        value="${entrust.layout}"/>

            </li>
            <li class="col-md-6"><span>面积：</span><input type="text" name="measure" id="measure" onblur="notEmpty('measure','measure','');InitInput.setNumber(this,9,2,2)"
                                                        value="${entrust.measure}"
                                                        maxlength="50"/>㎡<span class="_star">*</span></li>
            <li class="col-md-6"><span>装修：</span>
                <select name="renovation" id="renovation" class="dropdown">

                    <c:forEach items="${decorations}" var="s">
                        <option value="${s.id}">
                                ${s.name}
                        </option>
                    </c:forEach>

                </select>
                </select>

            </li>
            <li class="col-md-6"><span>售价/租价：</span><input type="text" name="price" id="price" onblur="notEmpty('price','price','');InitInput.setNumber(this,9,2,2)"
                                                          value="${entrust.price}"/>
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
<c:forEach items="${entrustLogs}" var="s">
    ${s.state}
    ${s.logTime}
    ${s.operatorName}
    ${s.remark}
</c:forEach>
                </textarea></li>
            </li>
            <li class="col-md-6">
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="updateData()">新增</a>
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
        if(!notEmpty('entrustSn','entrustSn','')||!notEmpty('entrustTime','entrustTime','')||!notEmpty('nickName','nickName','')||!notEmpty('areaName','areaName','')
            ||!notEmpty('communityName','communityName','')||!notEmpty('address','address','')||!notEmpty('layout','layout','')||!notEmpty('measure','measure','')
            ||!checkPhone('','phone','')){
            return;
        }
        var data = $("#addEntrustForm").serialize();

        $.ajax({
            type: "post",
            url: "${ctx}/rest/entrust/buy/add",
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
</script>
</body>
</html>