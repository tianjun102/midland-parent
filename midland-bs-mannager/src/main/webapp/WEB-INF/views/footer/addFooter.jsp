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
        .layui-layer{
            top:260px!important;
        }
    </style>
    <script type="text/javascript">
    </script>

</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/footer/add" method="post" id="dataForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <li><span>registrationProtocol：</span>
               <input type="text" name="registrationProtocol" id="registrationProtocol" value="${item.menuName}"/>
            </li>
            <li><span>disclaimer：</span>
               <input type="text" name="disclaimer" id="disclaimer" value="${item.menuName}"/>
            </li>
            <li><span>recordNumber：</span>
               <input type="text" name="recordNumber" id="recordNumber" value="${item.menuName}"/>
            </li>
            <li><span>recruitment：</span>
               <input type="text" name="recruitment" id="recruitment" value="${item.menuName}"/>
            </li>
            <li><span>aboutUs：</span>
               <input type="text" name="aboutUs" id="aboutUs" value="${item.menuName}"/>
            </li>
            <li><span>privacy：</span>
               <input type="text" name="privacy" id="privacy" value="${item.menuName}"/>
            </li>
            <li><span>serviceArea：</span>
               <input type="text" name="serviceArea" id="serviceArea" value="${item.menuName}"/>
            </li>
            <li><span>tradingProcess：</span>
               <input type="text" name="tradingProcess" id="tradingProcess" value="${item.menuName}"/>
            </li>
            <li><span>isexport：</span>
               <input type="text" name="isexport" id="isexport" value="${item.menuName}"/>
            </li>
            <li><span>purpose：</span>
               <input type="text" name="purpose" id="purpose" value="${item.menuName}"/>
            </li>
            <li><span>eliteDesc：</span>
               <input type="text" name="eliteDesc" id="eliteDesc" value="${item.menuName}"/>
            </li>
            <li><span>memberShip：</span>
               <input type="text" name="memberShip" id="memberShip" value="${item.menuName}"/>
            </li>
            <li><span>development：</span>
               <input type="text" name="development" id="development" value="${item.menuName}"/>
            </li>
            <li><span>isDelete：</span>
               <input type="text" name="isDelete" id="isDelete" value="${item.menuName}"/>
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
            url: "${ctx}/rest/footer/add",
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