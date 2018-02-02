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
    <form action="${ctx}/rest//entrust/sale/update" method="post" id="appointInfoForm">
        <ul class="userinfo  width-md row">
            <input type="hidden" name="id" id="id" value="${entrust.id}">
            <li class="col-md-6"><span>预约编号：</span>
                <input type="text" name="entrustSn" id="entrustSn" disabled="disabled" value="${entrust.entrustSn}"/>
            </li>
            <li class="col-md-6"><span>委托时间：</span>
                <input type="text" name="entrustTime" id="entrustTime" disabled="disabled" value="${entrust.entrustTime}" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" maxlength="50"/>

            </li>
            <li class="col-md-6"><span>委托人：</span>
                <input type="text" name="nickName" id="nickName"  onblur="notEmpty('nickName','nickName','')" value="${entrust.nickName}"/><span  class="_star">*</span></li>
            </li>
            <li class="col-md-6"><span>手机号码：</span>
                <input type="text" name="phone" id="phone" onblur="checkPhone('','phone','')" value="${entrust.phone}" maxlength="50"/>
                <span class="_star">*</span>
            </li>


            <li class="col-md-6"><span>房屋类型：</span>
                <select name="houseType" id="houseType" class="dropdown" disabled="disabled"  disabled="disabled">

                    <c:forEach items="${houses}" var="s">
                        <option value="${s.id}" <c:if test="${s.id==entrust.houseType}">selected</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>

                </select>
            </li>
            <li class="col-md-6"><span>装修情况：</span>
                <select name="renovation" id="renovation" class="dropdown" disabled="disabled">
                    <c:forEach items="${decorations}" var="s">
                        <option value="${s.id}" <c:if test="${s.id==entrust.renovation}">selected</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>
                </select>
            </li>
            <li class="col-md-6"><span>区域：</span>
                <input type="text" name="areaName"  disabled="disabled" id="areaName"  onblur="notEmpty('areaName','areaName','')" value="${entrust.areaName}"/>
            </li>
            <li class="col-md-6"><span>小区：</span>
                <input type="text" name="communityName"  disabled="disabled" id="communityName"  onblur="notEmpty('communityName','communityName','')" value="${entrust.communityName}" maxlength="50"/>
            </li>


            <li class="col-md-6"><span>户型：</span>
                <input type="text" name="layout" id="layout"  disabled="disabled" onblur="notEmpty('layout','layout','')"  value="${entrust.layout}"/>

            </li>
            <li class="col-md-6"><span>面积：</span>
                <input type="text" name="measure" id="measure"  disabled="disabled" onblur="notEmpty('measure','measure','');InitInput.setNumber(this,9,2,2)"  value="${entrust.measure}" maxlength="50"/>㎡
            </li>

            <li class="col-md-6"><span>出售价位：</span>
                <input type="text" name="price" id="price"  disabled="disabled"  onblur="notEmpty('price','price','');InitInput.setNumber(this,9,2,2)" value="${entrust.price}" maxlength="50"/>
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
            <li class="col-md-11"><span>用户备注：</span>
                <textarea name="remark" id="remark" style="width:calc(100% - 120px);height:50px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;" disabled="disabled"></textarea>
            <li class="col-md-11"><span>处理备注：</span>
                <textarea name="dealRemark" id="dealRemark" style="width:calc(100% - 120px);height:50px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;"></textarea>
            </li>
            </li>
            <li class="col-md-11"><span>处理记录：</span>
                <textarea name="record" id="record" disabled="disabled"
                          style="width:calc(100% - 120px);height:150px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;">
<c:forEach items="${entrustLogs}" var="s">状态    ：<c:forEach items="${statusList}" var="m"><c:if test="${m.id==s.state}">${m.name}</c:if> </c:forEach>
时间    ：${s.logTime}
操作人： ${s.operatorName}
备注    ：${s.remark}


</c:forEach>
                </textarea></li>
            </li>
            <li class="col-md-6">
                <span></span>
                <c:if test="${entrust.status !=3 and entrust.status !=5 }">
                <a target="contentF" class="public_btn bg2" id="save" onclick="updateData()">更新</a>
                </c:if>
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
        if (notEmpty('nickName', 'nickName', '') && checkPhone('phone', 'phone', '')) {
            var data = $("#appointInfoForm").serialize();

            $.ajax({
                type: "post",
                url: "${ctx}/rest/entrust/sale/update",
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
                        layer.msg("操作失败！", {icon: 2});
                    }
                }
            });
        }
    }


    //取消
    function closeWin() {
        parent.layer.closeAll();

    }
</script>
</body>
</html>