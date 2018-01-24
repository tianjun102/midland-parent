<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp"%>
<%@include file="../layout/zTree.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${ctx}/assets/css/ztree/css/demo.css">
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">
    <link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css"/>
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
            if(treeNode.id==0){
                $("input[name='cateId']").val("");
                $("input[name='cateName']").val("");
                $("input[name='noteType']").val("");
                $("input[name='modeId']").val("");
                $("input[name='modeName']").val("");
            }else{
                if (treeNode.type==1){
                    $("input[name='cateId']").val(treeNode.pId);
                    $("input[name='cateName']").val(treeNode.pName);
                    $("input[name='showCateName']").val(treeNode.pName+'('+treeNode.name+')');
                    $("input[name='modeId']").val(treeNode.id);
                    $("input[name='modeName']").val(treeNode.name);
                    $("input[name='noteType']").val(treeNode.type);
                }else{
                    $("input[name='cateId']").val(treeNode.id);
                    $("input[name='cateName']").val(treeNode.name);
                    $("input[name='showCateName']").val(treeNode.name);
                    $("input[name='modeId']").val("");
                    $("input[name='modeName']").val("");
                    $("input[name='noteType']").val(treeNode.type);
                }

            }
            $("#showDiv").hide();
        }

        function showTree(event){
            var data = $("#dataForm").serialize();
            $.ajax({
                type: "post",
                url: "${ctx}/rest/siteMap/choose",
                async: false, // 此处必须同步
                dataType: "json",
                data: data,
                success: function (data) {
                    var dfd={id:0, pId:0,name:'分类',open:true,nocheck:true,iconSkin:"pIcon01"};
                    catProNodes =[dfd];
                    $.each(data.list,function (i,listItem) {
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

        function hideTree(event){
            $("#showDiv").hide();
        }

    </script>
</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/siteMap/add" method="post" id="dataForm">
        <input type="hidden" name="id" id="id" value="${item.id}" >
        <input type="hidden" name="cityName" id="cityName" value="${item.cityName}" >
        <input name="noteType" type="hidden"/>
        <input name="type" type="hidden" value="${type}" alt="网站管理的type=4"/>
        <input name="modeId" type="hidden" value="${item.modeId}"/>
        <input name="modeName" type="hidden"  value="${item.modeName}"/>
        <ul class="userinfo row">
            <li><span>平台：</span>
                <select name="source" id="source" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option <c:if test="${item.source==0}">selected="selected"</c:if> value="0">网站</option>
                    <option <c:if test="${item.source==1}">selected="selected"</c:if> value="1">微站</option>
                </select>
                <span class = "_star ">*</span>
            </li>
            <li><span>城市：</span>
                <select onchange="setCityName();" name="cityId" id="cityId" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" <c:if test="${empty isSuper}">disabled="disabled"</c:if>>
                    <option value="">全部</option>
                    <c:forEach items="${cityList}" var="city">
                        <option <c:if test="${item.cityId==city.id}">selected="selected"</c:if> value="${city.id}">${city.name}</option>
                    </c:forEach>
                </select>
                <span class = "_star ">*</span>
            </li>

            <%--<li><span>分类：</span>
                <input type="hidden" name="cateName" id="cateName" value="">
                <select onchange="setCateName();" name="cateId" id="cateId" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option value="">全部</option>
                    <option <c:if test="${item.cateId==0}">selected="selected"</c:if> value="0">新房</option>
                    <option <c:if test="${item.cateId==1}">selected="selected"</c:if> value="1">二手房</option>
                    <option <c:if test="${item.cateId==2}">selected="selected"</c:if> value="2">租房</option>
                    <option <c:if test="${item.cateId==3}">selected="selected"</c:if> value="3">写字楼</option>
                    <option <c:if test="${item.cateId==4}">selected="selected"</c:if> value="4">商铺</option>
                </select>
                <span class = "_star ">*</span>
            </li>--%>
            <li><span>分类：</span><input style="width: 250px!important;" class="vipcate" id="showCateName" name="showCateName" type="text"
                                       <c:if test="${item.modeId != null}">value="${item.cateName}(${item.modeName})"</c:if>
                                       <c:if test="${item.modeId == null}">value="${item.cateName}"</c:if>
                                       onclick="showTree()" readonly="readonly"/>
                <input name="cateName" id="cateName" type="hidden" value="${item.cateName}"/>
                <input name="cateId" type="hidden" value="${item.cateId}"/><label style="color: red" class = "_star " >*</label>

            </li>
            <li  id="showDiv" style="display: none;padding-top: 0px;padding-left: 70px; position:relative;" >
                <div class="zTreeDemoBackground left" style  = "position:absolute;top: -10px;"   onblur="test(event)">
                    <ul id="categoryTree" class="ztree" style  = "width:250px; height: 140px!important;"></ul>
                </div>
                <img  src="${ctx}/assets/img/Closed_16px.png"  alt="关闭" style="vertical-align: top;position:absolute; left: 305px;" onclick="hideTree()">
            </li>

            <%--<li>
                <span>模块名称：</span><input type="text" name="modeName" id="modeName" value="${item.modeName}" onblur="notEmpty('modeName','modeName','模块名称不能为空！')" >
                <span class = "_star ">*</span>
            </li>--%>
            <li><span>关键字：</span>
                <input style="width: 250px!important;" type="text" name="name" id="name" value="${item.name}" onblur="notEmpty('name','name','关键字不能为空！')"/>
                <span class = "_star ">*</span>
            </li>
            <li><span>链接：</span>
                <input style="width: 250px!important;" type="text" name="linkUrl" id="linkUrl" value="${item.linkUrl}" onblur="checkUrl('linkUrl','linkUrl','链接格式不正确！')" />
                <span class = "_star ">*</span>
            </li>

            <li>
                <span></span>
                <a target="contentF" class="public_btn bg2" id="save" onclick="updateData()">修改</a>
                <a style="margin-left: 20px" class="public_btn bg3" id="cancel" onclick="closeWin();">取消</a>
            </li>
        </ul>

    </form>
</section>

<script type="text/javascript">
    //保存数据
    function updateData() {
        if(!(notEmpty('name','name','关键字不能为空！')&&checkSelect("source|cityId","平台不能为空！城市不能为空！")&&notEmpty('cateName','cateName','模块不能为空！')&&checkUrl('linkUrl','linkUrl','链接格式不正确！'))){
            return;
        }
        var data = $("#dataForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/siteMap/update",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("修改成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        parent.layer.closeAll();
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

    //取消
    function closeWin() {
        parent.layer.closeAll();
    }

    $("#source").change(function () {
        $("#showDiv").hide();
    })
    function setCityName(){
        $("input[name='cateId']").val("");
        $("input[name='cateName']").val("");
        $("input[name='showCateName']").val("");
        $("input[name='noteType']").val("");
        $("input[name='modeId']").val("");
        $("input[name='modeName']").val("");
        $("#showDiv").hide();
        $("#cityName").val($("#cityId option:selected").text())
    }

    function setCateName(){
        $("input[name='cateId']").val("");
        $("input[name='cateName']").val("");
        $("input[name='showCateName']").val("");
        $("input[name='noteType']").val("");
        $("input[name='modeId']").val("");
        $("input[name='modeName']").val("");
        $("#showDiv").hide();
        $("#cateName").val($("#cateId option:selected").text())
    }
</script>
</body>
</html>