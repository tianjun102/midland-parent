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
        .content ul.userinfo>li {
            float: none !important;
            margin-left: 20px;
            padding-top: 20px;
        }

        .dropdown {
            width: 274px!important;
        }
        .vipcate{
            width: 274px!important;;
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
                chkboxType: { "Y": "sp", "N": "sp" }


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
        var catProNodes =[{id:0, pId:0,name:'分类',open:true,nocheck:true,iconSkin:"pIcon01"},${categoryData}];


        $(document).ready(function(){
            $.fn.zTree.init($("#categoryTree"), setting, catProNodes);
        });

        function beforeClick(treeId, treeNode, clickFlag) {
            $("input[name='cateId']").val(treeNode.id);
            $("input[name='cateName']").val(treeNode.name);
            $("#showDiv").hide();
        }

        function showTree(event){
            $("#showDiv").show();
        }

        function hideTree(event){
            $("#showDiv").hide();
        }

    </script>
</head>
<body>
<section class="content" style="border:none;">
    <form action="" method="post" id="addFrom">
        <input type="hidden" name="id" id="id" value="${popular.id}">
        <ul class="userinfo row">
            <li><span>链接名：</span><input style="width:274px;" type="text" value="${popular.name}" onblur="notEmpty('name','name','链接名不能为空！');" name="name" id="name" maxlength="50"/><span class="_star">*</span></li>
            <li>
                <span style = "float:left;">平台：</span>
                <select name="source" id="source" style="height: 28px;width: 274px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option value="">全部</option>
                    <option <c:if test="${popular.source =='1'}">selected = 'selected'</c:if> value="1">网站</option>
                    <option <c:if test="${popular.source =='2'}">selected = 'selected'</c:if> value="2">微站</option>
                </select>
            </li>
            <%--<li style="display:flex;align-items:center">
                <span style = "float:left;">类型：</span>
                <select name="cateId" id="cateId" class="dropdown">
                    <option <c:if test="${popular.source =='1'}">selected = 'selected'</c:if> value="1">精选深圳二手房</option>
                    <option <c:if test="${popular.source =='1'}">selected = 'selected'</c:if> value="2">精选深圳租房</option>
                </select>
            </li>--%>
            <li><span>类型：</span><input class="vipcate" id="cateName" name="cateName" value="${popular.cateName}" onclick="showTree()" readonly="readonly"/>
                <input name="cateId" value="${popular.cateId}" type="hidden"/><label style="color: red" class = "_star " >*</label>

            </li>
            <li  id="showDiv" style="display: none;padding-top: 0px;padding-left: 70px; position:relative;" >
                <div class="zTreeDemoBackground left" style  = "position:absolute;top: -10px;"   onblur="test(event)">
                    <ul id="categoryTree" class="ztree" style  = "width:270px; height: 140px!important;"></ul>
                </div>
                <img  src="${ctx}/assets/img/Closed_16px.png"  alt="关闭" style="vertical-align: top;position:absolute; left: 323px;" onclick="hideTree()">
            </li>
            <li>
                <span style = "float:left;">模块：</span>
                <select name="menuId" id="menuId" style="height: 28px;width: 274px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option <c:if test="${popular.source =='1'}">selected = 'selected'</c:if> value="1">首页</option>
                    <option <c:if test="${popular.source =='2'}">selected = 'selected'</c:if> value="2">新房</option>
                    <option <c:if test="${popular.source =='3'}">selected = 'selected'</c:if> value="3">新二手房</option>
                    <option <c:if test="${popular.source =='4'}">selected = 'selected'</c:if> value="4">租房</option>
                    <option <c:if test="${popular.source =='5'}">selected = 'selected'</c:if> value="5">写字楼</option>
                    <option <c:if test="${popular.source =='6'}">selected = 'selected'</c:if> value="6">商铺</option>
                    <option <c:if test="${popular.source =='7'}">selected = 'selected'</c:if> value="7">小区</option>
                    <option <c:if test="${popular.source =='8'}">selected = 'selected'</c:if> value="8">经纪人</option>
                    <option <c:if test="${popular.source =='9'}">selected = 'selected'</c:if> value="9">外销网</option>
                    <option <c:if test="${popular.source =='10'}">selected = 'selected'</c:if> value="10">市场调究</option>
                    <option <c:if test="${popular.source =='11'}">selected = 'selected'</c:if> value="11">资讯</option>
                    <option <c:if test="${popular.source =='12'}">selected = 'selected'</c:if> value="12">问答</option>
                </select>
            </li>

            <li><span>省：</span> <!-- 省 -->
                <p id="province" style="display: inline-block;height: 28px;">
                    <label for="pop-14"></label> <input type="hidden" value="${popular.provinceId}" name="provinceId">
                    <input type="hidden" name="regionSn"> <input type="hidden" value="${popular.provinceName}"  name="provinceName">
                    <!-- 第一次进页面加载省 -->
                    <select id="provinces" onchange="initProvince()"
                            style="height: 100%;width: 65px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" <c:if test="${empty isSuper}">disabled="disabled"</c:if>>
                        <option>请选择</option>
                        <c:forEach items="${provinceList}" var="province">
                            <option <c:if test="${popular.provinceId == province.parentId}">selected = 'selected'</c:if>  value="${province.parentId}">
                                    ${province.parentName}
                            </option>
                        </c:forEach>
                    </select>
                </p>
                <!-- 市 -->
                <p id="city" style="display: inline-block;height: 28px;">
                    <span>市：</span>
                    <label for="pop-15"></label> <input value="${popular.cityId}" type="hidden" name="cityId">
                    <input type="hidden" value="${popular.cityName}" name="cityName">
                    <select id="citys" onchange="initCity()" style="height: 100%;width: 65px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" <c:if test="${empty isSuper}">disabled="disabled"</c:if>>
                        <option value="">请选择</option>
                        <c:forEach items="${cityList}" var="city">
                            <option <c:if test="${city.id == popular.cityId}">selected = 'selected'</c:if>  value="${city.id}">
                                    ${city.name}
                            </option>
                        </c:forEach>
                    </select>
                </p>

                <p id="district" style="display: inline-block;height: 28px;">
                    <span>地区：</span>
                    <label for="pop-16"></label> <input type="hidden" value="${popular.areaId}" name="distId"> <input type="hidden" value="${popular.areaName}" name="distName">
                        <select id="districts" onchange="initDistrict()" style="height: 100%;width: 65px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" <c:if test="${empty isSuper}">disabled="disabled"</c:if>>
                            <option value="">请选择</option>
                            <c:forEach items="${areaList}" var="area">
                            <option <c:if test="${area.id == popular.areaId}"> selected = 'selected'</c:if> value="${area.id}">${area.name}</option>
                            </c:forEach>
                        </select>
                </p>
            </li>

            <li>
                <!-- 片区 -->
                <span>片区：</span>
                <p id="sheet" style="display: inline-block;height: 28px;">
                    <label for="pop-16"></label> <input type="hidden" value="${popular.sheetId}" name="sheetId"> <input type="hidden" value="${popular.sheetName}" name="sheetName">
                    <select id="sheets" onchange="initSheet()" style="height: 100%;width: 274px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="">请选择</option>
                        <c:forEach items="${sheetList}" var="sheet">
                            <option <c:if test="${sheet.id==popular.sheetId}"> selected = 'selected' </c:if> value="${sheet.id}">${sheet.name}</option>
                        </c:forEach>
                    </select>
                </p>
            </li>
            <li><span>链接URL：</span><input style="width:274px;" type="text" name="url" id="url" value="${popular.url}"
                                          onblur="checkUrl('url','url','网址格式不正确！');"/><span class="_star">*</span>
            </li>
            <%-- <li>
                <span>角色类型：</span>
                <div style = "width:252px; display:flex;align-items:center; flex-wrap: wrap;">
                    <c:if test="${!empty roles }">
                    <c:forEach items="${roles }" var="role">
                        <span style = "width: 82px;">
                            <input style="width:24px;height: 14px" type="checkbox" name="userRoles" value="${role.id }"/>
                            <label>${role.roleName }</label>
                        </span>
                    </c:forEach>
                </c:if>
                </div>
            </li> --%>
            <li style="padding-top:30px;">
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="saveData()">保存</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>
    </form>

</section>
<script type="text/javascript">
    function saveData() {
        if(notEmpty('name','name','链接名不能为空！')&&notEmpty('cateName','cateName','链接名不能为空！')&&checkSelect("source|menuId","平台不能为空！|类型不能为空！|模块不能为空！")&&checkUrl("url","url","网址格式不正确！")){
            var id =  $("#id").val();
            var name = $("#name").val();
            var source = $("#source option:selected").val();
            var provinceId = $("input[name='provinceId']").val();
            var cityId = $("input[name='cityId']").val();
            var distId = $("input[name='distId']").val();
            var sheetId = $("input[name='sheetId']").val();
            var url = $("input[name='url']").val();
            var cateId = $("input[name='cateId']").val();
            var menuId = $("input[name='menuId']").val();
            var provinceName = $("input[name='provinceName']").val();
            var cityName = $("input[name='cityName']").val();
            var areaName = $("input[name='distName']").val();
            var sheetName = $("input[name='sheetName']").val();
            var cateId = $("#cateId option:selected").val();
            var menuId = $("#menuId option:selected").val();
            var cateName = $("input[name='cateName']").val();
            $.ajax({
                type: "post",
                url: "${ctx}/rest/setting/saveEditPopular",
                async: false, // 此处必须同步
                dataType: "json",
                data: {
                    "name": name, "source": source, "provinceId": provinceId, "cityId": cityId,
                    "areaId": distId, "sheetId": sheetId, "url": url,"cateId":cateId,"menuId":menuId,"provinceName":provinceName,"cityName":cityName,"areaName":areaName,"sheetName":sheetName,"id":id,"cateName":cateName
                },
                success: function (data) {
                    if (data.flag == 1) {
                        layer.msg("修改成功！！！", {icon: 1});
                        $('#save').removeAttr("onclick");
                        setTimeout(function () {
                            $('#searchForm',window.parent.document).submit();
                            closeWin();
                        }, 1000);

                    } else {
                        layer.msg("修改失败！", {icon: 2});
                    }
                },
                error: function () {
                    layer.msg("修改失败！", {icon: 2});
                }

            });
        }
    }


    function checkUserName() {
        var regUserName = /^[a-zA-Z0-9_]{6,20}$/;
        var userName = $("#username").val();
        if (userName == null || userName.trim() == "") {
            //$("#userNameCheck").text("用户名不能为空！");
            layer.tips("用户名不能为空！", "input[name='username']", {tips: 1});
            return false;
        }
        if (!regUserName.test(userName.trim())) {
            layer.tips("仅支持英文、数字和下划线,长度为6-20个字符！", "input[name='username']", {tips: 1});
            return false;
        }
        var a = true;
        $.ajax({
            type: "post",
            url: "${ctx }/rest/user/checkUnique",
            async: false, // 此处必须同步
            dataType: "json",
            data: {"userName": userName},
            success: function (xmlobj) {
                if (xmlobj.flag == 1) {
                    layer.tips("该用户已存在！", "input[name='username']", {tips: 1});
                    a = false;
                } else {

                    a = true;
                }
            }
        });
        return a;
    }

    //检查手机号格式
    function checkPhone() {
        var reg = /^1[3,4,5,7,8]\d{9}$/;
        var phone = $("input[name='phone']").val();
        if (phone.trim() == '') {
            layer.tips("手机号不能为空！", "input[name='phone']", {tips: 3});
            return false;
        }
        if (!reg.test(phone)) {
            layer.tips("手机号格式有误,请核对!", "input[name='phone']", {tips: 3});
            $("input[name='phone']").focus();
            return false;
        }
        var a = true;
        $.ajax({
            type: "post",
            url: "${ctx }/rest/user/checkPhoneUnique",
            async: false, // 此处必须同步
            dataType: "json",
            data: {"phone": phone},
            success: function (xmlobj) {
                if (xmlobj.flag == 1) {
                    layer.tips("当前手机号码已被使用，请更换手机号码！", "input[name='phone']", {tips: 1});
                    a = false;
                } else {
                    a = true;
                }
            }
        });
        return a;
    }

    //检查邮箱格式
    function checkEmail() {
        var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        var email = $("input[name='email']").val();
        if (email.trim() == '') {
            //layer.tips("邮箱不能为空！", "input[name='email']",{tips:3});
            return true;
        }
        if (!reg.test(email)) {
            layer.tips("邮箱格式有误,请核对!", "input[name='email']", {tips: 3});
            $("input[name='email']").focus();
            return false;
        }
        return true;
    }

    //取消
    function closeWin() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
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
            type : "post",
            url : "${ctx}/rest/setting/seleAddress?flag=city&id=" + addrId,
            async : false, // 此处必须同步
            dataType : "json",
            data : "",
            success : function(data) {
                $("#citys").html("<option  >请选择</option>");

                data.result.forEach(function(list) {
                    $("#citys").append(
                        "<option value="+list.id+" >" + list.name + "</option>");
                })
            }
        });
    }


    //市级赋值
    function initCity() {
        var addrId = $("#citys option:selected").val();
        var addName = $("#citys option:selected").text();
        if ("请选择" == addName) {
            //下级改变成请选择
            $("#districts option:selected").text(addName);
            $("#districts").html("<option  >请选择</option>");
            //其值及其下级值变成空
            $("input[name='cityId']").val("");
            $("input[name='cityName']").val("");
            $("input[name='distId']").val("");
            $("input[name='distName']").val("");
            return;
        }
        $("input[name='cityId']").val(addrId);
        $("input[name='cityName']").val(addName);
        $.ajax({
            type : "post",
            url : "${ctx}/rest/setting/seleAddress?flag=area&id=" + addrId,
            async : false, // 此处必须同步
            dataType : "json",
            data : "",
            success : function(data) {
                $("#districts").html("<option value='' >请选择</option>");
                data.result.forEach(function(list) {
                    $("#districts").append(
                        "<option value="+list.id+" >"
                        + list.name + "</option>");
                })
            }
        });
    }


    //区级赋值
    function initDistrict() {
        var cityId =$("input[name='cityId']").val();
        var addrId = $("#districts option:selected").val();
        var addName = $("#districts option:selected").text();
        if ("请选择" == addName) {
            //下级改变成请选择
            $("#districts option:selected").text(addName);
            //其值及其下级值变成空
            $("input[name='distId']").val("");
            $("input[name='distName']").val("");
            return;
        }
        $("input[name='distId']").val(addrId);
        $("input[name='distName']").val(addName);

        $.ajax({
            type : "post",
            url : "${ctx}/rest/setting/seleAddress?flag=sheet&id=" + cityId+"&parentId=" + addrId,
            async : false, // 此处必须同步
            dataType : "json",
            data : "",
            success : function(data) {
                $("#sheets").html("<option value='' >请选择</option>");
                data.result.forEach(function(list) {
                    $("#sheets").append(
                        "<option value="+list.id+" >"
                        + list.name + "</option>");
                })
            }
        });

    }


    //区级赋值
    function initSheet() {
        var cityId =$("input[name='cityId']").val();
        var addrId = $("#sheets option:selected").val();
        var addName = $("#sheets option:selected").text();
        if ("请选择" == addName) {
            //下级改变成请选择
            $("#sheets option:selected").text(addName);
            //其值及其下级值变成空
            $("input[name='sheetId']").val("");
            $("input[name='sheetName']").val("");
            return;
        }
        $("input[name='sheetId']").val(addrId);
        $("input[name='sheetName']").val(addName);

    }



</script>
</body>
</html>