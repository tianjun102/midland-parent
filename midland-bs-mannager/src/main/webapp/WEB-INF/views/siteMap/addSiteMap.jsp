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
        var catProNodes =[${categoryData}];
        debugger;
        $(document).ready(function(){
            $.fn.zTree.init($("#categoryTree"), setting, catProNodes);
        });

        function beforeClick(treeId, treeNode, clickFlag) {
            if(treeNode.id==0){
                return;
//                $("input[name='cateId']").val("");
//                $("input[name='cateName']").val("");
//                $("input[name='noteType']").val("");
//                $("input[name='modeId']").val("");
//                $("input[name='modeName']").val("");
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
            var data="cityId="+$("#cityId").val()+"&source="+$("#source").val()+"&type="+$("#type").val();
            $.ajax({
                type: "post",
                url: "${ctx}/rest/siteMap/choose",
                async: false, // 此处必须同步
                dataType: "json",
                data: data,
                success: function (data) {
                    var dfd={id:0, pId:0,name:'分类',open:true,nocheck:true,iconSkin:"pIcon01"};
                        catProNodes =[];
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
        <input type="hidden" name="cityName" id="cityName" value="${cityName}" >
        <input name="noteType" type="hidden"/>
        <input name="type" id="type" type="hidden" value="${type}" alt="网站管理的type=4"/>

        <c:if test="${empty isSuper}">
            <input type="hidden" name="cityId"  value="${cityId}">
        </c:if>
        <ul class="userinfo row">
            <li><span>平台：</span>
                <select name="source" id="source" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option value="0">网站</option>
                    <option value="1">微站</option>
                </select>
                <span class = "_star ">*</span>
            </li>
            <li><span>城市：</span>
                <select onchange="setCityName();" name="cityId" id="cityId" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" <c:if test="${empty isSuper}">disabled="disabled"</c:if>>
                    <c:forEach items="${cityList}" var="city">
                        <c:if test="${empty isSuper}"><option selected="selected" value="${cityId}">${cityName}</option></c:if>
                        <option value="${city.id}">${city.name}</option>
                    </c:forEach>
                </select>
                <span class = "_star ">*</span>
            </li>

            <li><span>分类：</span><input style="width: 250px!important;" type="text" class="vipcate" id="showCateName" name="showCateName" onclick="showTree()" readonly="readonly"/>
                <input name="cateName" id="cateName" type="hidden"/>
                <input name="cateId" id="cateId" type="hidden"/><label style="color: red" class = "_star " >*</label>

            </li>

            <li  id="showDiv" style="display: none;padding-top: 0px;padding-left: 70px; position:relative;" >
                <div class="zTreeDemoBackground left" style  = "position:absolute;top: -10px;"   onblur="test(event)">
                    <ul id="categoryTree" class="ztree" style  = "width:250px; height: 140px!important;"></ul>
                </div>
                <img  src="${ctx}/assets/img/Closed_16px.png"  alt="关闭" style="vertical-align: top;position:absolute; left: 305px;" onclick="hideTree()">
            </li>

            <li><span>关键字：</span>
                <input style="width: 250px!important;" type="text" name="name" id="name" onblur="notEmpty('name','name','关键字不能为空！')"/>
                <span class = "_star ">*</span>
            </li>
            <li><span>链接：</span>
               <input style="width: 250px!important;"  type="text" name="linkUrl" id="linkUrl" onblur="checkUrl('linkUrl','linkUrl','链接格式不正确！')"/>
                <span class = "_star ">*</span>
            </li>
            <input name="modeId" class="modeId" type="hidden"/>
            <input name="modeName" class="modeName" type="hidden"/>
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
        if(!(notEmpty('name','name','关键字不能为空！')&&checkSelect("source|cityId","平台不能为空！|城市不能为空！")&&notEmpty('cateName','cateName','模块不能为空！')&&checkUrl('linkUrl','linkUrl','链接格式不正确！'))){
            return;
        }
        var data = $("#dataForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/siteMap/add",
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
   $("#source").change(function () {
       setEmpty();
   })

    //取消
    function closeWin() {
        parent.layer.closeAll();
    }

    function setCityName(){
        setEmpty();
        $("#cityName").val($("#cityId option:selected").text())
    }

    function setCateName(){
        setEmpty();
        $("#cateName").val($("#cateId option:selected").text())
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
</script>
</body>
</html>