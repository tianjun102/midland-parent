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
    <form action="${ctx}/rest/hotHand/add" method="post" id="dataForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <li><span>price：</span>
               <input type="text" name="price" id="price" ß/>
            </li>
            <li><span>intoTime：</span>
               <input type="text" name="intoTime" id="intoTime" ß/>
            </li>
            <li><span>managerCosts：</span>
               <input type="text" name="managerCosts" id="managerCosts" ß/>
            </li>
            <li><span>unitTotal：</span>
               <input type="text" name="unitTotal" id="unitTotal" ß/>
            </li>
            <li><span>landArea：</span>
               <input type="text" name="landArea" id="landArea" ß/>
            </li>
            <li><span>parkingNum：</span>
               <input type="text" name="parkingNum" id="parkingNum" ß/>
            </li>
            <li><span>buildingType：</span>
               <input type="text" name="buildingType" id="buildingType" ß/>
            </li>
            <li><span>propertyAddress：</span>
               <input type="text" name="propertyAddress" id="propertyAddress" ß/>
            </li>
            <li><span>developer：</span>
               <input type="text" name="developer" id="developer" ß/>
            </li>
            <li><span>buildingAddress：</span>
               <input type="text" name="buildingAddress" id="buildingAddress" ß/>
            </li>
            <li><span>propertyManagement：</span>
               <input type="text" name="propertyManagement" id="propertyManagement" ß/>
            </li>
            <li><span>propertyDesc：</span>
               <input type="text" name="propertyDesc" id="propertyDesc" ß/>
            </li>
            <li><span>propertyAdvantages：</span>
               <input type="text" name="propertyAdvantages" id="propertyAdvantages" ß/>
            </li>
            <li><span>position：</span>
               <input type="text" name="position" id="position" ß/>
            </li>
            <li><span>supporting：</span>
               <input type="text" name="supporting" id="supporting" ß/>
            </li>
            <li><span>houseName：</span>
               <input type="text" name="houseName" id="houseName" ß/>
            </li>
            <li><span>imgUrl：</span>
               <input type="text" name="imgUrl" id="imgUrl" ß/>
            </li>
            <li><span>agentName：</span>
               <input type="text" name="agentName" id="agentName" ß/>
            </li>
            <li><span>agentId：</span>
               <input type="text" name="agentId" id="agentId" ß/>
            </li>
            <li><span>createTime：</span>
               <input type="text" name="createTime" id="createTime" ß/>
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
            url: "${ctx}/rest/hotHand/add",
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