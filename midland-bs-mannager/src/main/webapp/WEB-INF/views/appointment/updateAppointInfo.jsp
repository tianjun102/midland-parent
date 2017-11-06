<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/appoint/update" method="post" id="appointInfoForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${appointment.id}">
            <li class="col-md-6"><span>预约编号：</span><input type="text" name="appointSn" id="appointSn" disabled="disabled"
                                                          value="${appointment.appointSn}"/>
            </li>
            <li class="col-md-6"><span>预约时间：</span>
                <input type="text" name="appointmentTime" id="appointmentTime"  disabled="disabled"
                                                          value="${appointment.appointmentTime}" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                                           maxlength="50"/>

            </li>
            <li class="col-md-6"><span>预约人：</span><input type="text" name="nickName" id="nickName" onblur="notEmpty('nickName','nickName','')"
                                                        value="${appointment.nickName}"/>
                <span class="_star">*</span>
            </li>
            <li class="col-md-6"><span>手机号码：</span><input type="text" name="phone" id="phone" onblur="checkPhone('','phone','')"
                                                          value="${appointment.phone}"
                                                          maxlength="50"/><span class="_star">*</span></li>
            <li class="col-md-6"><span>分类：</span>
                <select name="sellRent" id="sellRent"  disabled="disabled" class="dropdown">
                    <c:forEach items="${sellRents}" var="s">
                        <option value="${s.id}" <c:if test="${s.id==appointment.sellRent}">selected</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>
                </select>
            </li>
            <li class="col-md-6"><span>预约类型：</span>
                <select name="houseType"  disabled="disabled" id="houseType" class="dropdown">

                    <c:forEach items="${houses}" var="s">
                        <option value="${s.id}" <c:if test="${s.id==appointment.houseType}">selected</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>

                </select>
            </li>

            <li class="col-md-6"><span>区域：</span><input type="text"  disabled="disabled" name="areaName" id="areaName" value="${appointment.areaName}"/>
            </li>
            <li class="col-md-6"><span>小区：</span><input type="text" name="communityName"  disabled="disabled" id="communityName"
                                                        value="${appointment.communityName}" maxlength="50"/>
            <li class="col-md-6"><span>地址：</span><input type="text"  disabled="disabled" name="address" id="address"
                                                        value="${appointment.address}"/>
            </li>
            <li class="col-md-6"><span>户型：</span><input type="text" name="layout"  disabled="disabled" id="layout"
                                                        value="${appointment.layout}"/>

            </li>
            <li class="col-md-6"><span>面积：</span><input type="text"  disabled="disabled" name="measure" id="measure"
                                                        value="${appointment.measure}"
                                                        maxlength="50"/>㎡</li>
            <li class="col-md-6"><span>装修：</span>
                <select name="decoration" id="decoration"  disabled="disabled" class="dropdown">

                    <c:forEach items="${decorations}" var="s">
                        <option value="${s.id}" <c:if test="${s.id==appointment.decoration}">selected</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>

                </select>

            </li>
            <li class="col-md-6"><span>状态：</span>
                <select name="status" id="status" class="dropdown">

                    <c:forEach items="${statusList}" var="s">
                        <option value="${s.id}" <c:if test="${s.id==appointment.status}">selected</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>

                </select>
            </li>

            <li class="col-md-11"><span>备注：</span>
                <textarea name="remark" id="remark" style="width:calc(100% - 120px);height:50px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;"></textarea>
            </li>
            </li>
            <li class="col-md-11"><span>处理记录：</span>
                <textarea name="record" id="record" disabled="disabled"
                          style="width:calc(100% - 120px);height:150px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;">
<c:forEach items="${appointLogs}" var="s">状态    ：<c:forEach items="${statusList}" var="m"><c:if test="${m.id==s.state}">${m.name}</c:if> </c:forEach>
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
    //保存数据
    function updateData() {
        if (!notEmpty('nickName','nickName','')||!checkPhone('','phone','')){
            return;
        }
        var data = $("#appointInfoForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/appoint/update",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                debugger;
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
</body>
</html>