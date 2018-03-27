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
        /*.content ul.userinfo>li {
            float: none !important;
            margin-left: 20px;
            padding-top: 20px;
        }*/

        .dropdown {
            width: 274px !important;
        }
    </style>
    <script type="text/javascript">

        var setting = {
            check: {
                enable: true,
                chkboxType: {"Y": "sp", "N": "sp"}


            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick
            }
        };

        $(document).ready(function () {
            var catProNodes = null;
            if ('${type}' == 3) {
                catProNodes = [{id: 0, pId: 0, name: '分类', open: true, nocheck: true, iconSkin: "pIcon01"}
                    , ${categoryData}];
            } else {
                catProNodes = [{
                    id: 0,
                    pId: 0,
                    name: '分类',
                    open: true,
                    nocheck: true,
                    iconSkin: "pIcon01"
                }, ${categoryData}];
            }
            $.fn.zTree.init($("#categoryTree"), setting, catProNodes);
        });

        function beforeClick(treeId, treeNode, clickFlag) {
            $("input[name='parentId']").val(treeNode.id);
            $("input[name='parentName']").val(treeNode.name);
            $("#showDiv").hide();
        }

        function showTree(event) {
            var data = $("#addFrom").serialize();
            $.ajax({
                type: "post",
                url: "${ctx}/rest/siteMap/choose",
                async: false, // 此处必须同步
                dataType: "json",
                data: data,
                success: function (data) {
                    var dfd = {id: 0, pId: 0, name: '分类', open: true, nocheck: true, iconSkin: "pIcon01"};
                    catProNodes = [dfd];
                    $.each(data.list, function (i, listItem) {
                        catProNodes.push(listItem);
                    });
                    $.fn.zTree.init($("#categoryTree"), setting, catProNodes);
                    $("#showDiv").show();
                },
                error: function (data) {
                    if (data.responseText != null) {
                        layer.msg(data.responseText, {icon: 2});
                    } else {
                        layer.msg("保存失败！", {icon: 2});
                    }
                }
            });
            $("#showDiv").show();
        }

        function hideTree(event) {
            $("#showDiv").hide();
        }

    </script>
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
                <select onchange="setCityName()" name="cityId" id="cityId"
                        style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;"
                        <c:if test="${empty isSuper}">disabled="disabled"</c:if>>
                    <c:forEach items="${cityList}" var="city">
                        <c:if test="${empty isSuper}">
                            <option selected="selected" value="${cityId}">${cityName}</option>
                        </c:if>
                        <option value="${city.id}">${city.name}</option>
                    </c:forEach>
                </select>
            </li>
            <li>
                <span style="float:left;">平台：</span>
                <select name="source" id="source"
                        style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option value="0">网站</option>
                    <option value="1">微站</option>
                </select>
                <div style="font-size:12px; color:#afadad;text-indent: 70px;height: 18px;"></div>
            </li>

            <c:if test="${type == 3}">
                <li><span>模块：</span>
                    <input type="hidden" id="modeName" name="modeName" value="">
                    <select onchange="setMenuName()" name="modeId" id="modeId"
                            style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
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

            <c:choose>
                <c:when test="${type == 3 or type ==1 or type == 0 or type ==4}">
                    <li><span>父节点：</span><input style="width: 250px!important;background-color: #dddfe2;" type="text" name="parentName" onclick="showTree()"
                                                readonly="readonly" value="一级分类"/>
                        <input name="parentId" type="hidden" value="0"/>
                        <div style="font-size:12px; color:#afadad;text-indent: 70px;"></div>
                    </li>
                </c:when>
                <c:otherwise>
                    <c:if test="${type != 2}">
                        <li><span>父节点：</span><input style="width: 250px!important;" type="text" name="parentName" onclick="showTree()"
                                                    readonly="readonly"/>
                            <input name="parentId" type="hidden"/>
                            <div style="font-size:12px; color:#afadad;text-indent: 70px;">(不选父分类则默认一级分类)</div>
                        </li>
                        <li id="showDiv"  style="display: none;padding-top: 0px;position:relative;">
                            <div class="zTreeDemoBackground left" style="position:absolute; left: -268px; top: 29px;"
                                 onblur="test(event)">
                                <ul id="categoryTree" class="ztree" style="width:235px; height: 140px!important;"></ul>
                            </div>
                            <img src="${ctx}/assets/img/Closed_16px.png" alt="关闭"
                                 style="vertical-align: top;position:absolute; left: -50px; top: 40px;" onclick="hideTree()">
                        </li>
                    </c:if>
                </c:otherwise>
            </c:choose>
            <li>
                <span>分类名称：</span><input style="width:250px;" type="text" name="cateName" id="cateName"
                                         onblur="notEmpty('cateName','cateName','分类名称不能为空！');" maxlength="50"/>
            </li>
            <c:if test="${type == 4}">
                <li id="isHrefId" ><span>超链接：</span>
                    <label class="checkitem"><input type="radio" name="isHref" onclick="checkRadio()"  class="isHref" value="0" checked="checked"><span>是</span></label>
                    <label class="checkitem"><input type="radio" name="isHref" onclick="checkRadio()" class="isHref" value="1"><span>否</span></label>
                </li>
            </c:if>

            <c:if test="${type != 2 and type !=3}">
                <li style="margin-top: 20px!important;" id="linkUrlId">
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
        if ($("#cityName").val() == "") {
            $("#cityName").val("${cityList[0].name}");
        }
        if ($("#modeName").val()==""){
            $("#modeId").val("0");
            $("#modeName").val("首页");
        }
        if ($("#isHrefId").val()==1){
            $("#linkUrlId").css("display","none");
        }
    })

    function saveData(type) {

        if (notEmpty('cateName', 'cateName', '分类名称不能为空！')) {
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