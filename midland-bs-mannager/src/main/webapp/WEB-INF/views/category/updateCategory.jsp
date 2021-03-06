<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp"%>
<%@include file="../layout/zTree.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加热门关注</title>
    <link rel="stylesheet" href="${ctx}/assets/css/ztree/css/demo.css">
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">
    <link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css"/>
    <style type="text/css">
        .dropdown {
            width: 274px!important;
        }
    </style>

</head>
<body>
<section class="content" style="border:none;">
    <form action="" method="post" id="addFrom">
        <input type="hidden" name="id"  value="${item.id}" >
        <input type="hidden" name="type" id="type" value="${item.type}">
        <ul class="userinfo row">
            <li>
                <span style = "float:left;">城市：</span>
                <input type="hidden" name="cityName" id="cityName" value="${item.cityName}">
                <select onchange="setCityName()" name="cityId" id="cityId" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" <c:if test="${empty isSuper}">disabled="disabled"</c:if>>
                    <c:forEach items="${cityList}" var="city">
                        <option <c:if test="${item.cityId == city.id}"> selected = 'selected'</c:if> value="${city.id}">${city.name}</option>
                    </c:forEach>
                </select>
            </li>
            <li>
                <span style = "float:left;">平台：</span>
                <select name="source" id="source" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option <c:if test="${item.source == '0'}"> selected = 'selected'</c:if> value="0">网站</option>
                    <option <c:if test="${item.source == '1'}"> selected = 'selected'</c:if> value="1">微站</option>
                </select>
            </li>
            <%--<li>
                <span>父分类：</span>
                <select name="parentId" id="parentId" style="height: 28px;width: 274px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option value="">全部</option>
                    <c:forEach items="${cateList}" var="cate">
                    <option <c:if test="${cate.id==item.parentId}">selected="selected"</c:if> value="${cate.id}">${cate.cateName}</option>
                    </c:forEach>
                </select>
            </li>--%>

            <c:if test="${item.type == 3}">
                <li><span>模块：</span>
                    <input type="hidden" id="modeName" name="modeName" value="" >
                    <select onchange="setMenuName()" name="modeId" id="modeId" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="0" <c:if test="${item.modeId ==0}"  >selected="selected"</c:if> >首页</option>
                        <option value="1"  <c:if test="${item.modeId ==1}"  >selected="selected"</c:if>>新房</option>
                        <option value="2"  <c:if test="${item.modeId ==2}"  >selected="selected"</c:if>>二手房</option>
                        <option value="3"  <c:if test="${item.modeId ==3}"  >selected="selected"</c:if>>租房</option>
                        <option value="4"  <c:if test="${item.modeId ==4}"  >selected="selected"</c:if>>写字楼</option>
                        <option value="5"  <c:if test="${item.modeId ==5}"  >selected="selected"</c:if>>商铺</option>
                        <option value="6"  <c:if test="${item.modeId ==6}"  >selected="selected"</c:if>>小区</option>
                        <option value="7"  <c:if test="${item.modeId ==7}"  >selected="selected"</c:if>>经纪人</option>
                        <option value="8"  <c:if test="${item.modeId ==8}"  >selected="selected"</c:if>>外销网</option>
                        <option value="9"  <c:if test="${item.modeId ==9}"  >selected="selected"</c:if>>市场调究</option>
                        <option value="10"  <c:if test="${item.modeId ==10}"  >selected="selected"</c:if>>资讯</option>
                        <option value="11"  <c:if test="${item.modeId ==11}"  >selected="selected"</c:if>>问答</option>
                    </select>
                    <label style="color: red" class = "_star " >*</label>
                </li>
                <li id="sellrentLi" style="display: none"><span>租售：</span>
                    <label class="checkitem"><input class="radioClass" type="radio" name="sellRent" value="0" checked="checked"><span>租房</span></label>
                    <label class="checkitem"><input class="radioClass" type="radio" name="sellRent" value="1" <c:if test="${item.sellRent==1}">checked="checked"</c:if> ><span>售房</span></label>

                </li>
            </c:if>

            <li>
                <span>分类名称：</span><input style="width:250px;" type="text" name="cateName" id="cateName" value="${item.cateName}" onblur="notEmpty('cateName','cateName','分类名称不能为空！');" maxlength="50"/>
            </li>
            <c:if test="${type == 4}">
                <li id="isHrefId" ><span>超链接：</span>
                    <label class="checkitem"><input type="radio" name="isHref" onclick="checkRadio()"  class="isHref" value="0" <c:if test="${item.isHref==0}">checked="checked"</c:if> ><span>是</span></label>
                    <label class="checkitem"><input type="radio" name="isHref" onclick="checkRadio()" class="isHref" value="1" <c:if test="${item.isHref==1}">checked="checked"</c:if> ><span>否</span></label>
                </li>
            </c:if>

            <c:if test="${type != 2 and type !=3}">
                <li id="linkUrlId" <c:if test="${item.isHref==0}">style="display: block" </c:if><c:if test="${item.isHref==1}">style="display: none" </c:if>>
                    <span>网站链接：</span><input style="width:250px;" type="text" name="linkUrl" id="linkUrl" value="${item.linkUrl}" onblur="checkUrl('linkUrl','linkUrl','网站链接格式不正确！');" maxlength="50"/>
                </li>
            </c:if>
            <li style="padding-top:30px;">
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="saveData(${type})">保存</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>
    </form>

</section>
<script type="text/javascript">
    $(function () {
        if (${item.modeId == 5 or item.modeId == 4}) {
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
        if (notEmpty('cateName', 'cateName', '分类名称不能为空！')) {

            if (type != 2 && type !=3 && $('input[name="isHref"]:checked').val()==0 && !checkUrl('linkUrl', 'linkUrl', '网站链接格式不正确！')) {
                return;
            }
            debugger;
        var data = $("#addFrom").serialize();
            if ($("#sellrentLi").css("display")=='none'){
                data+="&sellRent=-1";
            }
        $.ajax({
            type: "post",
            url: "${ctx}/rest/category/update",
            async: false, // 此处必须同步
            dataType: "json",
            data:data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("修改成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        parent.layer.closeAll();
                        parent.$("#inquery").click();
                    }, 1000);

                } else {
                    layer.msg("修改失败！", {icon: 2});
                }
            },
            error: function (data) {
                if (data.responseText != null) {
                    layer.msg(data.responseText, {icon: 2});
                } else {
                    layer.msg("操作失败！", {icon: 2});
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
    function setCityName(){
        setEmpty();
        $("#cityName").val($("#cityId option:selected").text())
    }

    function setMenuName(){
        setEmpty();
        $("#modeName").val($("#modeId option:selected").text())
    }
    function setEmpty() {
        debugger;
        if (${type== 3} || ${type ==1} || ${type== 0 } ){
            $("input[name='showCateName']").val("");
            $("input[name='noteType']").val("");
            $("input[name='modeId']").val("");
            $("input[name='modeName']").val("");
        }else{
            $("input[name='parentId']").val("");
            $("input[name='parentName']").val("");
            $("input[name='showCateName']").val("");
            $("input[name='noteType']").val("");
            $("input[name='modeId']").val("");
            $("input[name='modeName']").val("");
        }
        $("#showDiv").hide();

    }
    //取消
    function closeWin() {
        parent.layer.closeAll();
    }
</script>
</body>
</html>