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
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <li><span>搜索词：</span>
                <input type="text" name="keywords" id="keywords" value="${item.keywords}" onblur="notEmpty('keywords','keywords','搜索词不能为空！');"/><label style="color: red" class = "_star " >*</label>
            </li>
            <li>
                <span style = "float:left;">城市：</span>
                <input type="hidden" name="cityName" id="cityName" value="${item.cityName}">
                <select onchange="setCityName()" name="cityId" id="cityId" style="height: 28px;width: 274px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" <c:if test="${empty isSuper}">disabled="disabled"</c:if>>
                    <option value="">全部</option>
                    <c:forEach items="${cityList}" var="city">
                    <option <c:if test="${item.cityId == city.id}">selected="selected"</c:if> value="${city.id}">${city.name}</option>
                    </c:forEach>
                </select>
            </li>
            <li><span>模块：</span>
                <input type="hidden" id="menuName" name="menuName" value="${item.menuName}" >
                <select onchange="setMenuName()" name="menuId" id="menuId" style="height: 28px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option value="">全部</option>
                    <option <c:if test="${item.menuId =='0'}">selected="selected"</c:if> value="0">首页</option>
                    <option <c:if test="${item.menuId =='1'}">selected="selected"</c:if> value="1">新房</option>
                    <option <c:if test="${item.menuId =='2'}">selected="selected"</c:if> value="2">二手房</option>
                    <option <c:if test="${item.menuId =='3'}">selected="selected"</c:if> value="3">租房</option>
                    <option <c:if test="${item.menuId =='4'}">selected="selected"</c:if> value="4">写字楼</option>
                    <option <c:if test="${item.menuId =='5'}">selected="selected"</c:if> value="5">商铺</option>
                    <option <c:if test="${item.menuId =='6'}">selected="selected"</c:if> value="6">小区</option>
                    <option <c:if test="${item.menuId =='7'}">selected="selected"</c:if> value="7">经纪人</option>
                    <option <c:if test="${item.menuId =='8'}">selected="selected"</c:if> value="8">外销网</option>
                    <option <c:if test="${item.menuId =='9'}">selected="selected"</c:if> value="9">市场调究</option>
                    <option <c:if test="${item.menuId =='10'}">selected="selected"</c:if> value="10">资讯</option>
                    <option <c:if test="${item.menuId =='11'}">selected="selected"</c:if> value="11">问答</option>
                </select>
                <label style="color: red" class = "_star " >*</label>
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
        if(notEmpty('keywords','keywords','搜索词不能为空！')&&checkSelect('menuId','请选择模块！')){
        var data = $("#dataForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/hotSearch/update",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("保存成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        $('#searchForm',window.parent.document).submit();
                        closeWin();
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
    }

    //取消
    function closeWin() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }

    function setCityName(){
        $("#cityName").val($("#cityId option:selected").text());
    }

    function setMenuName(){
        $("#menuName").val($("#menuId option:selected").text());
    }

</script>
</body>
</html>