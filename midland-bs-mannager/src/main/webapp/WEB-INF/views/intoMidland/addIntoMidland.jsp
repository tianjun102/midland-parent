<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
    </script>

</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/intoMidland/add" method="post" id="dataForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <li><span>companyProfile：</span>
               <input type="text" name="companyProfile" id="companyProfile" ß/>
            </li>
            <li><span>companyProcess：</span>
               <input type="text" name="companyProcess" id="companyProcess" ß/>
            </li>
            <li><span>companyHonor：</span>
               <input type="text" name="companyHonor" id="companyHonor" ß/>
            </li>
            <li><span>companyTraining：</span>
               <input type="text" name="companyTraining" id="companyTraining" ß/>
            </li>
            <li><span>companyCulture：</span>
               <input type="text" name="companyCulture" id="companyCulture" ß/>
            </li>
            <li><span>promotionBenefits：</span>
               <input type="text" name="promotionBenefits" id="promotionBenefits" ß/>
            </li>
            <li><span>contactUs：</span>
               <input type="text" name="contactUs" id="contactUs" ß/>
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
        debugger;
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