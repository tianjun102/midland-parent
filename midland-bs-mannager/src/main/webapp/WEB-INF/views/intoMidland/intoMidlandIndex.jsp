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
        .layui-layer {
            top: 260px !important;
        }
    </style>
</head>
<body>


<!--列表界面-->
<div class="box">
    <section class="content">
        <p class="detail-title">
				<span>
					<c:if test="${flag==1}">走进美联>>公司介绍</c:if>
					<c:if test="${flag==2}">走进美联>>公司历程</c:if>
					<c:if test="${flag==3}">走进美联>>公司荣誉</c:if>
					<c:if test="${flag==4}">走进美联>>公司培训</c:if>
					<c:if test="${flag==5}">走进美联>>公司文化</c:if>
					<c:if test="${flag==6}">走进美联>>晋升福利</c:if>
					<c:if test="${flag==7}">走进美联>>联系我们</c:if>
					<c:if test="${flag==8}">走进美联>>校园福利</c:if>
					<c:if test="${flag==9}">走进美联>>招聘流程</c:if>
					<c:if test="${flag==10}">走进美联>>走进美联</c:if>
				</span>
        </p>
        <form id="dataForm" action="${ctx}/rest/banner/addBanner" method="post" enctype="multipart/form-data"
              method="post">
            <input type="hidden" name="id" id="id" value="${items.id}">
            <ul class="adminfo row">

                <li id="textArea" style="display: block;">
						<textarea style="width: 92%;min-height: 350px;resize:none; outline-color: #0099e0;"
                                  id="myEditor" rows="" cols=""
                                  <c:if test="${flag==1}">name="companyProfile"</c:if>
                                  <c:if test="${flag==2}">name="companyProcess"</c:if>
                                  <c:if test="${flag==3}">name="companyHonor"</c:if>
                                  <c:if test="${flag==4}">name="companyTraining"</c:if>
                                  <c:if test="${flag==5}">name="companyCulture"</c:if>
                                  <c:if test="${flag==6}">name="promotionBenefits"</c:if>
                                  <c:if test="${flag==7}">name="contactUs"</c:if>
                                  <c:if test="${flag==8}">name="campusWelfare"</c:if>
                                  <c:if test="${flag==9}">name="recruitingProcess"</c:if>
                                  <c:if test="${flag==10}">name="intoMidland"</c:if>>
                                  <c:if test="${flag==1}">${items.companyProfile}</c:if>
                                  <c:if test="${flag==2}">${items.companyProcess}</c:if>
                                  <c:if test="${flag==3}">${items.companyHonor}</c:if>
                                  <c:if test="${flag==4}">${items.companyTraining}</c:if>
                                  <c:if test="${flag==5}">${items.companyCulture}</c:if>
                                  <c:if test="${flag==6}">${items.promotionBenefits}</c:if>
                                  <c:if test="${flag==7}">${items.contactUs}</c:if>
                                  <c:if test="${flag==8}">${items.campusWelfare}</c:if>
                                  <c:if test="${flag==9}">${items.recruitingProcess}</c:if>
                                  <c:if test="${flag==10}">${items.intoMidland}</c:if></textarea>
                </li>
            </ul>
            <ul class="adminfo row">
                <li>
                    <span></span>
                    <a onclick="saveData();" target="contentF" class="public_btn bg2">保存</a>
                    <a style="margin-left: 20px" href="${ctx}/rest/intoMidland/index?flag=${flag}" target="contentF"
                       class="public_btn bg3" id="cancel">取消</a>
                </li>
            </ul>
        </form>
    </section>


</div>


<script type="text/javascript">
    //保存数据
    UE.getEditor('myEditor');

    function saveData() {
        var data = $("#dataForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/intoMidland/add",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("保存成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");

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
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>