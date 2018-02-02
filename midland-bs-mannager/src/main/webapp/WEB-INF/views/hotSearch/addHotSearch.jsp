<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp"%>
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
    <form action="${ctx}/rest/hotSearch/add" method="post" id="dataForm">
        <ul class="userinfo  width-md row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <li><span>搜索词：</span>
               <input type="text" name="keywords" id="keywords"  onblur="notEmpty('keywords','keywords','搜索词不能为空！');" /><label style="color: red" class = "_star " >*</label>
            </li>
            <li>
                <span style = "float:left;">城市：</span>
                <input type="hidden" name="cityName" id="cityName" value="${cityName}">
                <c:if test="${empty isSuper}"><input type="hidden" name="cityId"  value="${cityId}"></c:if>
                <select onchange="setCityName()" name="cityId" id="cityId" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <c:forEach items="${cityList}" var="city">
                        <c:if test="${empty isSuper}"><option selected="selected" value="${cityId}">${cityName}</option></c:if>
                        <option value="${city.id}">${city.name}</option>
                    </c:forEach>
                </select>
            </li>
            <li><span>平台：</span>
                <select name="source" id="source" class="dropdown">
                    <option value="0">网站</option>
                    <option value="1">微站</option>
                </select>
            </li>

            <li><span>模块：</span>
                <input type="hidden" id="menuName" name="menuName" value="" >
                <select onchange="setMenuName()" name="menuId" id="menuId" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option value="0">首页</option>
                    <option value="1">新房</option>
                    <option value="2">二手房</option>
                    <option value="3">租房</option>
                    <option value="4">写字楼</option>
                    <option value="5">商铺</option>
                    <option value="6">小区</option>
                    <option value="7">经纪人</option>
                    <option value="8">外销网</option>
                    <option value="9">市场调究</option>
                    <option value="10">资讯</option>
                    <option value="11">问答</option>
                </select>
                <label style="color: red" class = "_star " >*</label>
            </li>
            <li><span>地址：</span>
                <input type="text" name="url" id="url" value="${item.url}" onblur="checkUrl('url','url','链接格式不正确！')">
            </li>
            <li>
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="updateData()">保存</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>

    </form>
</section>

<script type="text/javascript">
    //保存数据
    function updateData() {
        if(notEmpty('keywords','keywords','搜索词不能为空！')&&checkSelect('menuId','请选择模块！')&&checkUrl('url','url','链接格式不正确！')){
        var data = $("#dataForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/hotSearch/add",
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
                    layer.msg("保存失败！", {icon: 2});
                }
            }
        });
        }
    }

    //取消
    function closeWin() {
        parent.layer.closeAll();
    }

    function setCityName(){
        $("#cityName").val($("#cityId option:selected").text())
    }

    function setMenuName(){
        $("#menuName").val($("#menuId option:selected").text())
    }

</script>
</body>
</html>