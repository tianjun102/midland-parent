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

            <li>
                <span style = "float:left;">城市：</span>
                <input type="hidden" name="cityName" id="cityName" value="${item.cityName}">
                <select onchange="setCityName()" name="cityId" id="cityId" class="dropdown" <c:if test="${empty isSuper}">disabled="disabled"</c:if>>
                    <option value="">请选择</option>

                    <c:forEach items="${cityList}" var="city">
                        <option <c:if test="${item.cityId == city.id}">selected="selected"</c:if> value="${city.id}">${city.name}</option>
                    </c:forEach>
                </select>
            </li>
            <li><span>平台：</span>
                <select name="source" id="source" class="dropdown">
                    <option value="">请选择</option>
                    <option value="0" <c:if test="${item.source == 0}">selected="selected"</c:if>>网站</option>
                    <option value="1" <c:if test="${item.source == 1}">selected="selected"</c:if>>微站</option>
                </select>
            </li>

            <li><span>模块：</span>
                <input type="hidden" id="menuName" name="menuName" value="${item.menuName}" >
                <select onchange="setMenuName()" name="menuId" id="menuId" class="dropdown" style="z-index: 999">
                    <%--<option <c:if test="${item.menuId =='0'}">selected="selected"</c:if> value="0">首页</option>--%>
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
            <li id="sellrentLi" style="display: none"><span>租售：</span>
                <label class="checkitem"><input class="radioClass" type="radio" name="sellRent" value="0" checked="checked"><span>租房</span></label>
                <label class="checkitem"><input class="radioClass" type="radio" name="sellRent" value="1" <c:if test="${item.sellRent==1}">checked="checked"</c:if> ><span>售房</span></label>

            </li>
            <li><span>搜索词：</span>
                <input type="text" name="keywords" id="keywords" value="${item.keywords}" onblur="notEmpty('keywords','keywords','搜索词不能为空！');"/><label style="color: red" class = "_star " >*</label>
            </li>
            <li><span>地址：</span>
                <input type="text" name="url" id="url" value="${item.url}"  onblur="checkUrl('url','url','链接格式不正确！')">
                <label style="color: red" class="_star ">*</label>
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

    $(function () {
        if (${item.menuId == 5 or item.menuId ==4}) {
            $("#sellrentLi").show();
            $(".radioClass").removeAttr("disabled","disabled");
        } else {
            $("#sellrentLi").hide();
            $(".radioClass").attr("disabled","disabled");
        }
    })

    $("#menuId").change(function () {
        if ($("#menuId").val() == 5 || $("#menuId").val()==4) {
            $("#sellrentLi").show();
            $(".radioClass").removeAttr("disabled","disabled");
        } else {
            $("#sellrentLi").hide();
            $(".radioClass").attr("disabled","disabled");
        }
    })


    //保存数据
    function updateData() {
        if(checkSelect('cityId|source|menuId','请选择城市!|请选择平台!|请选择模块！')&&notEmpty('keywords','keywords','搜索词不能为空！')&&checkUrl('url','url','链接格式不正确！')){
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
        $("#cityName").val($("#cityId option:selected").text());
    }

    function setMenuName(){
        $("#menuName").val($("#menuId option:selected").text());
    }

</script>
</body>
</html>