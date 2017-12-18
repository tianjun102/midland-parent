<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Insert title here</title>
    <style type="text/css">
        .content ul.userinfo>li {margin-left: 0!important;}
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
    <form action="${ctx}/rest/quotation/update" method="post" id="dataForm">
        <ul class="userinfo updinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <li class=""><span>数据时间：</span>
                <input class="Wdate half" id="time1" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd'})"
                       name="dataTime" value="${item.dataTime}" onblur="notEmpty('time1','dataTime','日期不能为空')" style="
    width: 250px;
"/>
            </li>
            <li class=""><span>类型：</span>
                <select name="type" id="type" class="dropdown">
                    <c:forEach items="${types}" var="type">
                        <option value="${type.id}" <c:if test="${type.id==item.type}">selected</c:if> >${type.name}</option>
                    </c:forEach>
                </select>
            </li>
            <%@include file="../menu/sheet_required.jsp" %>
            <li class=""><span>成交套数：</span>
                <input type="text" name="dealNum" id="dealNum" value="${item.dealNum}"/>
            </li>
            <li class=""><span>成交面积：</span>
                <input type="text" name="dealAcreage" id="dealAcreage" value="${item.dealAcreage}"/>
            </li>
            <li class=""><span>成交均价：</span>
                <input type="text" name="price" id="price" value="${item.price}"/>
            </li>
            <li class=""><span>可售套数：</span>
                <input type="text" name="soldNum" id="soldNum" value="${item.soldNum}"/>
            </li>
            <li class=""><span>可售面积：</span>
                <input type="text" name="soldArea" id="soldArea" value="${item.soldArea}"/>
            </li>
            <li class="col-sm-12 col-md-12 col-lg-12">
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="addData()">更新</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>

    </form>
</section>

<script type="text/javascript">
    //保存数据
    function addData() {
        if (notEmpty('time1','dataTime','日期不能为空')&&checkSelect('districts','请选择区级')){
        var data = $("#dataForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/quotation/add",
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
    }

    //取消
    function closeWin() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }
</script>
</body>
</html>