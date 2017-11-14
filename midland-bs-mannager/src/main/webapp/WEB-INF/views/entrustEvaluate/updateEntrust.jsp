<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>

    <style type="text/css">
        section.content{
            padding: 0 13px 0 30px;
        }
        .content ul.userinfo{
            height: 606px;
            overflow: hidden;
            overflow-y: scroll;
            padding: 15px 0 25px;
        }
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

        .content ul.userinfo li > span {
            float: left;
            display: inline-block;
            width: 90px;
            height: 28px;
            line-height: 28px;
            text-align: right;
            font-size: 14px;
            color: rgb(102, 102, 102);
        }
    </style>

</head>
<body>


<section class="content" style="border:none;">
    <form action="${ctx}/rest/entrust/evaluate/update" method="post" id="appointInfoForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${entrust.id}">
            <li class="col-md-6"><span>委托编号：</span>
                <input type="text" name="entrustSn" id="entrustSn" disabled="disabled" value="${entrust.entrustSn}"/>
            </li>
            <li class="col-md-6"><span>委托时间：</span>
                <input type="text" name="entrustTime" id="entrustTime" disabled="disabled" value="${entrust.entrustTime}"
                                                          onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                                          maxlength="50"/>
            </li>
            <li class="col-md-6"><span>联系人：</span>
                <input type="text" name="nickName" id="nickName"  value="${entrust.nickName}" onblur="notEmpty('nickName','nickName','')" />
                <span class="_star">*</span>
            </li>
            <li class="col-md-6"><span>手机号码：</span>
                <input type="text" name="phone" id="phone"  onblur="checkPhone('','phone','')" value="${entrust.phone}" maxlength="50"/>
                <span class="_star">*</span>
            </li>
            <li class="col-md-6"><span>房屋朝向：</span>
                <select name="turned" id="turned" class="dropdown" disabled="disabled">
                    <c:forEach items="${turneds}" var="s">
                        <option value="${s.id}" <c:if test="${s.id==entrust.turned}">selected</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>
                </select>
            </li>
            <li class="col-md-6"><span>平台：</span>
                <select name="source" id="source" class="dropdown" disabled="disabled">
                    <c:forEach items="${sources}" var="s1">
                        <option value="${s1.id}" <c:if test="${entrust.source==s1.id}">selected</c:if>>
                                ${s1.name}
                        </option>
                    </c:forEach>
                </select>
            </li>
            <li class="col-md-6"><span>建筑年代：</span>
                <input type="text" name="buildYear" id="buildYear" disabled="disabled" value="${entrust.buildYear}" maxlength="50"/>
            </li>
            <li class="col-md-6"><span>所属楼层：</span>
                <input type="text" name="theLayer" id="theLayer" disabled="disabled"  value="${entrust.theLayer}" maxlength="50"/>
            </li>
            <li class="col-md-6"><span>总楼层数：</span>
                <input type="text" name="totalLayer" id="totalLayer"  disabled="disabled"  value="${entrust.totalLayer}"  maxlength="50"/>
            </li>
             <li class="col-md-6"><span>其他特色：</span>
                 <input type="text" name="otherItem" id="otherItem"  disabled="disabled"  value="${entrust.otherItem}"  maxlength="50"/>
             </li>

            <li class="col-md-6"><span>意向区域：</span>
                <input type="text" name="areaName" id="areaName" disabled="disabled" value="${entrust.areaName}"/>
            </li>
            <li class="col-md-6"><span>意向小区：</span>
                <input type="text" name="communityName" id="communityName" disabled="disabled"  value="${entrust.communityName}" maxlength="50"/>
             </li>

            <li class="col-md-6"><span>意向户型：</span>
                <input type="text" name="layout" id="layout" disabled="disabled"  value="${entrust.layout}"/>
            </li>
            <li class="col-md-6"><span>意向面积：</span><input type="text" name="measure" id="measure" disabled="disabled"
                                                          onblur="notEmpty('measure','measure','');InitInput.setNumber(this,9,2,2)"
                                                          value="${entrust.measure}"
                                                          maxlength="50"/>㎡</li>


            <li class="col-md-6"><span>经纪人：</span>
                <input type="text" name="measure" id="agentName" disabled="disabled"  value="${entrust.agentName}" maxlength="50"/>
            </li>
            <li class="col-md-6"><span>处理时间：</span>
                <input type="text" name="communityName" id="handleTime" disabled="disabled" onblur="notEmpty('communityName','communityName','')" value="${entrust.handleTime}" maxlength="50"/>
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
            </li>

            <li class="col-md-11"><span>处理备注：</span>
                <textarea name="dealRemark" id="dealRemark"  style="width:calc(100% - 120px);height:50px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;"></textarea>
            </li>
            <li class="col-md-11"><span>处理记录：</span>
                <textarea name="record" id="record" disabled="disabled"  style="width:calc(100% - 120px);height:150px;resize:none; border: 1px solid #dbe2e6; border-radius: 4px; outline-color: #0099e0;">
<c:forEach items="${entrustLogs}" var="s">状态   ：<c:forEach items="${statusList}" var="m"><c:if  test="${m.id==s.state}">${m.name}</c:if></c:forEach>
时间   ：${s.logTime}
操作人：${s.operatorName}
备注   ：${s.remark}

</c:forEach>
                </textarea>
            </li>
            <li class="col-md-6">
                <span></span>
                <c:if test="${entrust.status !=5}">
                <a target="contentF" class="public_btn bg2" id="save" onclick="updateData()">更新</a>
                </c:if>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>

    </form>
</section>


<script type="text/javascript">


    //保存数据
    function updateData() {

        var data = $("#appointInfoForm").serialize();
        var pageNo = ${pageNo};
        var pageSize = ${pageSize};
        var param = "?pageNo="+pageNo+"&pageSize="+pageSize;
        $.ajax({
            type: "post",
            url: "${ctx}/rest/entrust/evaluate/update",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("保存成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        window.open('${ctx}/rest/entrust/evaluate/index'+param,'contentF');
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
        var pageNo = ${pageNo};
        var pageSize = ${pageSize};
        var param = "?pageNo="+pageNo+"&pageSize="+pageSize;
        window.open('${ctx}/rest/entrust/evaluate/index'+param,'contentF');
//        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
//        parent.layer.close(index);
    }
</script>
</body>
</html>