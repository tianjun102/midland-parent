<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
<%@include file="../layout/zTree.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加热门关注</title>
    <link rel="stylesheet" href="${ctx}/assets/css/ztree/css/demo.css">
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">
    <link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css">
    <style type="text/css">

        .dropdown {
            width: 274px !important;
        }
    </style>

</head>
<body>
<section class="content" style="border:none;">
    <form action="" method="post" id="addFrom">
        <ul class="userinfo row">

            <li>
                <span style="float:left;">城市：</span>
                <input type="hidden" name="type" value="${type}">
                <input type="hidden" name="cityName" id="cityName" value="${cityName}">
                <c:if test="${empty isSuper}"><input type="hidden" name="cityId" value="${cityId}"></c:if>
                <select onchange="setCityName()" name="cityId" id="cityId" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" <c:if test="${empty isSuper}">disabled="disabled"</c:if>>
                    <option value="">请选择</option>
                    <c:forEach items="${cityList}" var="city">
                        <c:if test="${empty isSuper}">
                            <option selected="selected" value="${cityId}">${cityName}</option>
                        </c:if>
                        <option value="${city.id}">${city.name}</option>
                    </c:forEach>
                </select>
                <label style="color: red" class="_star ">*</label>
            </li>
            <li>
                <span style="float:left;">平台：</span>
                <select name="source" id="source" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option value="">请选择</option>
                    <option value="0">网站</option>
                    <option value="1">微站</option>
                </select>
                <label style="color: red" class="_star ">*</label>
            </li>

            <c:if test="${type == 3}">
                <li><span>模块：</span>
                    <input type="hidden" id="modeName" name="modeName" value="">
                    <select onchange="setMenuName()" name="modeId" id="modeId"
                            style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="">请选择</option>
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
                    <label style="color: red" class="_star ">*</label>
                </li>
            </c:if>
            <li id="sellrentLi" style="display: none"><span>租售：</span>
                <label class="checkitem"><input class="radioClass" type="radio" name="sellRent" value="0" checked="checked"><span>租房</span></label>
                <label class="checkitem"><input class="radioClass" type="radio" name="sellRent" value="1" <c:if test="${item.sellRent==1}">checked="checked"</c:if> ><span>售房</span></label>

            </li>

            <li>
                <span>分类名称：</span><input style="width:250px;" type="text" name="cateName" id="cateName"
                                         onblur="notEmpty('cateName','cateName','分类名称不能为空！');" maxlength="50"/>
                <label style="color: red" class="_star ">*</label>
            </li>
            <c:if test="${type == 4}">
                <li id="isHrefId" ><span>超链接：</span>
                    <label class="checkitem"><input type="radio" name="isHref" onclick="checkRadio()"  class="isHref" value="0" checked="checked"><span>是</span></label>
                    <label class="checkitem"><input type="radio" name="isHref" onclick="checkRadio()" class="isHref" value="1"><span>否</span></label>
                </li>
            </c:if>

            <c:if test="${type != 2 and type !=3}">
                <li style="" id="linkUrlId">
                    <span>网站链接：</span><input style="width:250px;" type="text" name="linkUrl" id="linkUrl"
                                             onblur="checkUrl('linkUrl','linkUrl','网站链接格式不正确！');" maxlength="50"/>
                </li>
            </c:if>
            <li style="padding-top:20px;">
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="saveData(${type})">保存</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>
    </form>

</section>
<script type="text/javascript">

    $(function () {
        if ($("#isHrefId").val()==1){
            $("#linkUrlId").css("display","none");
        }
    })

    $(function () {
        if (${item.menuId == 5 and item.menuId == 4}) {
            $("#sellrentLi").show();
            $(".radioClass").removeAttr("disabled","disabled");
        } else {
            $("#sellrentLi").hide();
            $(".radioClass").attr("disabled","disabled");
        }
    })

    $("#modeId").change(function () {
        if ($("#modeId").val() == 4 || $("#modeId").val() == 5) {
            $("#sellrentLi").show();
            $(".radioClass").removeAttr("disabled","disabled");
        } else {
            $("#sellrentLi").hide();
            $(".radioClass").attr("disabled","disabled");
        }
    })

    function saveData(type) {

        if (checkSelect('cityId|source', '城市不能为空|平台不能为空!|', '城市不能为空！')&&notEmpty('cateName', 'cateName', '分类名称不能为空！')) {
            if (type==3&&!checkSelect('modeId', '模块不能为空!|', '模块不能为空！')){
                return;
            }
            if (type != 2 && type !=3 && $('input[name="isHref"]:checked').val()==0 && !checkUrl('linkUrl', 'linkUrl', '网站链接格式不正确！')) {
                return;
            }
            var data = $("#addFrom").serialize();
            $.ajax({
                type: "post",
                url: "${ctx}/rest/category/add",
                async: false, // 此处必须同步
                dataType: "json",
                data: data,
                success: function (data) {
                    if (data.state == 0) {
                        layer.msg("新增成功！！！", {icon: 1});
                        $('#save').removeAttr("onclick");
                        setTimeout(function () {
                            parent.layer.closeAll();
                            parent.$("#inquery").click();
                        }, 1000);

                    } else {
                        layer.msg("新增失败！", {icon: 2});
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

    $("#source").change(function () {
        setEmpty();
    })

   function checkRadio() {
        if($('input[name="isHref"]:checked').val()==1){
            $("#linkUrlId").hide();
        }else{
            $("#linkUrlId").show();
        }
    }

    function setCityName() {
        setEmpty();
        $("#cityName").val($("#cityId option:selected").text())
    }

    function setMenuName() {
        setEmpty();
        $("#modeName").val($("#modeId option:selected").text())
    }

    function setEmpty() {
        $("input[name='cateId']").val("");
        $("input[name='cateName']").val("");
        $("input[name='showCateName']").val("");
        $("input[name='noteType']").val("");
        $("input[name='modeId']").val("");
        $("input[name='modeName']").val("");
        $("#showDiv").hide();
    }

    //取消
    function closeWin() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }
</script>
</body>
</html>