<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../../layout/tablib.jsp" %>
<%@include file="../../layout/source.jsp" %>
<%@include file="../../layout/zTree.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加热门关注</title>
    <link rel="stylesheet" href="${ctx}/assets/css/ztree/css/demo.css">
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">
    <link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css"/>
    <style type="text/css">
        .content ul.userinfo > li {
            float: none !important;
            margin-left: 20px;
            padding-top: 20px;
        }

        .dropdown {
            width: 274px !important;
        }

        .vipcate {
            width: 274px !important;;
            height: 28px;
            line-height: 28px;
            border: 1px solid #dbe2e6;
            border-radius: 4px;
            text-indent: 10px;
            outline-color: #0099e0;
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
        var catProNodes = [${categoryData}];


        $(document).ready(function () {
            $.fn.zTree.init($("#categoryTree"), setting, catProNodes);
        });

        function beforeClick(treeId, treeNode, clickFlag) {
            if (treeNode.id == 0) {
                return;
            }
            $("input[name='cateId']").val(treeNode.id);
            $("input[name='cateName']").val(treeNode.name);
            $("#showDiv").hide();
        }

        function showTree(event) {
            var data = $("#addFrom").serialize() + "&modeId=" + $("#menuId").find("option:selected").val();
            $.ajax({
                type: "post",
                url: "${ctx}/rest/siteMap/choose",
                async: false, // 此处必须同步
                dataType: "json",
                data: data,
                success: function (data) {
                    var dfd = {id: 0, pId: 0, name: '分类', open: true, nocheck: true, iconSkin: "pIcon01"};
                    catProNodes = [];
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
        <input type="hidden" name="id" id="id" value="${item.id}">
        <input name="type" type="hidden" value="${type}" alt="热门关注的type=3"/>
        <ul class="userinfo row">
            <%@include file="../../menu/area_up_required.jsp" %>
            <li>
                <span style="float:left;">平台：</span>
                <select name="source" id="source"
                        style="height: 28px;width: 274px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option
                            <c:if test="${item.source =='0'}">selected='selected'</c:if> value="0">网站
                    </option>
                    <option
                            <c:if test="${item.source =='1'}">selected='selected'</c:if> value="1">微站
                    </option>
                </select>
            </li>
            <li>
                <span style="float:left;">模块：</span>
                <select name="menuId" id="menuId"
                        style="height: 28px;width: 274px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option
                            <c:if test="${item.menuId =='0'}">selected='selected'</c:if> value="0">首页
                    </option>
                    <option
                            <c:if test="${item.menuId =='1'}">selected='selected'</c:if> value="1">新房
                    </option>
                    <option
                            <c:if test="${item.menuId =='2'}">selected='selected'</c:if> value="2">二手房
                    </option>
                    <option
                            <c:if test="${item.menuId =='3'}">selected='selected'</c:if> value="3">租房
                    </option>
                    <option
                            <c:if test="${item.menuId =='4'}">selected='selected'</c:if> value="4">写字楼
                    </option>
                    <option
                            <c:if test="${item.menuId =='5'}">selected='selected'</c:if> value="5">商铺
                    </option>
                    <option
                            <c:if test="${item.menuId =='6'}">selected='selected'</c:if> value="6">小区
                    </option>
                    <option
                            <c:if test="${item.menuId =='7'}">selected='selected'</c:if> value="7">经纪人
                    </option>
                    <option
                            <c:if test="${item.menuId =='8'}">selected='selected'</c:if> value="8">外销网
                    </option>
                    <option
                            <c:if test="${item.menuId =='9'}">selected='selected'</c:if> value="9">市场调究
                    </option>
                    <option
                            <c:if test="${item.menuId =='10'}">selected='selected'</c:if> value="10">资讯
                    </option>
                    <option
                            <c:if test="${item.menuId =='11'}">selected='selected'</c:if> value="11">问答
                    </option>
                </select>
            </li>
            <li id="sellrentLi" style="display: none"><span>租售：</span>
                <label class="checkitem"><input id="radio1" type="radio" name="sellRent"
                                                value="0" checked="checked"><span>租房</span></label>
                <label class="checkitem"><input id="radio2" type="radio" name="sellRent"
                                                value="1"><span>售房</span></label>
            </li>
            <li><span>类型：</span><input class="vipcate" id="cateName" name="cateName" value="${item.cateName}"
                                       onclick="showTree()" readonly="readonly"/>
                <input name="cateId" value="${item.cateId}" type="hidden"/><label style="color: red"
                                                                                  class="_star ">*</label>

            </li>
            <li id="showDiv" style="display: none;padding-top: 0px;padding-left: 70px; position:relative;">
                <div class="zTreeDemoBackground left" style="position:absolute;top: -10px;" onblur="test(event)">
                    <ul id="categoryTree" class="ztree" style="width:270px; height: 140px!important;"></ul>
                </div>
                <img src="${ctx}/assets/img/Closed_16px.png" alt="关闭"
                     style="vertical-align: top;position:absolute; left: 323px;" onclick="hideTree()">
            </li>

            <li><span>链接名：</span><input style="width:274px;" type="text" value="${item.name}"
                                        onblur="notEmpty('name','name','链接名不能为空！');" name="name" id="name"
                                        maxlength="50"/><span class="_star">*</span></li>
            <li><span>链接URL：</span><input style="width:274px;" type="text" name="url" id="url" value="${item.url}"
                                          onblur="checkUrl('url','url','网址格式不正确！')"/><span class="_star">*</span>
            </li>
            <li id="nofollowId"><span>nofollow：</span>
                <label class="checkitem"><input type="radio" name="nofollow" class="nofollow" value="1"
                                                <c:if test="${item.nofollow==1}">checked="checked"</c:if> ><span>是</span></label>
                <label class="checkitem"><input type="radio" name="nofollow" class="nofollow" value="0"
                                                <c:if test="${item.nofollow==0}">checked="checked"</c:if> ><span>否</span></label>
            </li>
            <li style="padding-top:30px;">
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="saveData()">保存</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>
    </form>

</section>
<script type="text/javascript">
    $(function () {
        if (${item.menuId==4}|| ${item.menuId==5}) {
            if (${item.sellRent==0}) {
                $("#radio1").attr('checked', 'true');
            } else if (${item.sellRent==1}) {
                $("#radio2").attr('checked', 'true');
            }
            $("#sellrentLi").show();
        } else {
            $("#sellrentLi").hide();
        }

    })

    function saveData() {
        if (checkSelect("citys", "城市不能为空！") && notEmpty('name', 'name', '链接名不能为空！') && notEmpty('cateName', 'cateName', '分类不能为空！') && checkSelect("source|menuId", "平台不能为空！|类型不能为空！|模块不能为空！") && checkUrl("url", "url", "网址格式不正确！")) {
            var data = $("#addFrom").serialize();
            $.ajax({
                type: "post",
                url: "${ctx}/rest/setting/saveEditPopular",
                async: false, // 此处必须同步
                dataType: "json",
                data: data,
                success: function (data) {
                    if (data.flag == 1) {
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

    //省级赋值
    function initProvince() {
        var addrId = $("#provinces option:selected").val();
        var addName = $("#provinces option:selected").text();
        $("#districts").html("<option  >请选择</option>");
        /*if ("请选择" == addName) {
            //下级改变成请选择
            $("#citys option:selected").text(addName);
            $("#districts option:selected").text(addName);
            $("#citys").html("<option  >请选择</option>");

            $("input[name=provinceId]").val("");
            $("input[name=provinceName]").val("");
            $("input[name='cityId']").val("");
            $("input[name='cityName']").val("");
            $("input[name='distId']").val("");
            $("input[name='distName']").val("");
            return;
        }*/
        $("input[name=provinceId]").val(addrId);
        $("input[name=provinceName]").val(addName);


        $.ajax({
            type: "post",
            url: "${ctx}/rest/setting/seleAddress?flag=city&id=" + addrId,
            async: false, // 此处必须同步
            dataType: "json",
            data: "",
            success: function (data) {
                $("#citys").html("<option  >请选择</option>");

                data.result.forEach(function (list) {
                    $("#citys").append(
                        "<option value=" + list.id + " >" + list.name + "</option>");
                })
            }
        });
    }


    $("#source").change(function () {
        setEmpty();
    })
    $("#provinces").change(function () {
        setEmpty();
    })
    $("#citys").change(function () {
        setEmpty();
    })
    $("#menuId").change(function () {
        if ($("#menuId option:selected").val() == 4 || $("#menuId option:selected").val() == 5) {
            $("#sellrentLi").show();
        } else {
            $("#sellrentLi").hide();
        }
        setEmpty();
    })

    function setEmpty() {
        $("input[name='cateId']").val("");
        $("input[name='cateName']").val("");
        $("input[name='showCateName']").val("");
        $("input[name='noteType']").val("");
        $("input[name='modeId']").val("");
        $("input[name='modeName']").val("");
        $("#showDiv").hide();
    }

</script>
</body>
</html>