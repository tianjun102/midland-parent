<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>

    <link rel="stylesheet" href="${ctx }/assets/css/bootstrap.min.css">

    <link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css"/>
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">

</head>
<body>


<!--用户列表界面-->
<div class="box">
    <section class="content">
        <p class="detail-title">
            <span>站点管理>>友情链接管理</span>
            <a class="setup" target="contentF" onclick="toAddPage()">添加</a>
        </p>
        <form action="${ctx}/rest/linkUrlManager/list" method="POST" id="searchForm"
              onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
            <ul class="userinfo row">

                <%@include file="../layout/sherchArea.jsp" %>
                <li>
                    <span style="float:left;">平台：</span>
                    <select name="source" id="source" class="dropdown">
                        <option value="">全部</option>
                        <option value="0">网站</option>
                        <option value="1">微站</option>
                    </select>
                </li>
                <li>
                    <span style="float:left;">模块：</span>
                    <select name="modeId" id="modeId" class="dropdown">
                        <option value="">全部</option>
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
                </li>
                <li id="sellrentLi" style="display:none" >
                    <span style="float:left;">租售：</span>
                    <select name="sellRent" id="sellRentIndexId" class="dropdown">
                        <option value="">全部</option>
                        <option value="0">租</option>
                        <option value="1">售</option>
                    </select>
                </li>

                <c:if test="${not empty isSuper}">
                    <li>
                        <span style="float:left;">是否删除：</span>
                        <select name="isDelete" id="isDelete"
                                style="height: 28px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                            <option value="0">未删除</option>
                            <option value="1">已删除</option>
                        </select>
                    </li>
                </c:if>
                <li><input class="public_btn bg1" type="submit" name="inquery" id="inquery" value="查询"/></li>
            </ul>
        </form>
        <input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;"
               onclick="batchDelete(1)" class="public_btn bg1" type="submit" value="批量删除"/>
        <c:if test="${not empty isSuper}"><input
                style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;"
                onclick="batchDelete(0)" class="public_btn bg1" type="submit" value="批量恢复"/></c:if>
        <div id="listDiv"></div>
    </section>
</div>


<script type="text/javascript">
    $("#modeId").change(function () {
        if ($("#modeId").val() == 5||$("#modeId").val() == 4) {
            $("#sellrentLi").show();
            $(".radioClass").removeAttr("disabled","disabled");
        } else {
            $("#sellrentLi").hide();
            $(".radioClass").attr("disabled","disabled");
        }
    })



    window.onload = function () {
        $('#searchForm').submit();
    }

    function toAddPage() {
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['500px', '650px'],
            shadeClose: false, //点击遮罩关闭
            title: ['新建友情链接'],
            resize: false,
            scrollbar: false,
            content: ['${ctx}/rest/linkUrlManager/to_add', 'yes']
        });
    }
</script>
<!-- 本页私有js -->


<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/common.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/base.js"></script>
<script src="${ctx}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</body>
</html>