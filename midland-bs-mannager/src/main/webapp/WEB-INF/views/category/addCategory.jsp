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
    <link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css">
    <style type="text/css">
        /*.content ul.userinfo>li {
            float: none !important;
            margin-left: 20px;
            padding-top: 20px;
        }*/

        .dropdown {
            width: 274px!important;
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
            $("input[name='parentId']").val(treeNode.id);
            $("input[name='parentName']").val(treeNode.name);
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
        <ul class="userinfo row">
            <li>
                <span style = "float:left;">城市：</span>
                <input type="hidden" name="type" value="${type}">
                <input type="hidden" name="cityName" id="cityName" value="${cityName}">
                <c:if test="${empty isSuper}"><input type="hidden" name="cityId"  value="${cityId}"></c:if>
                <select onchange="setCityName()" name="cityId" id="cityId" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" <c:if test="${empty isSuper}">disabled="disabled"</c:if>>
                    <option value="">全部</option>
                    <c:forEach items="${cityList}" var="city">
                        <c:if test="${empty isSuper}"><option selected="selected" value="${cityId}">${cityName}</option></c:if>
                        <option value="${city.id}">${city.name}</option>
                    </c:forEach>
                </select><div style = "font-size:12px; color:#afadad;text-indent: 70px;">(不选择城市则默认全部)</div>
            </li>
            <c:if test="${type!=2}">
            <li>
                <span style = "float:left;">平台：</span>
                <select name="source" id="source" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option value="0">网站</option>
                    <option value="1">微站</option>
                </select>
                <div style = "font-size:12px; color:#afadad;text-indent: 70px;height: 18px;"></div>
            </li>
            </c:if>
            <li><span>父节点：</span><input style="width: 250px!important;" name="parentName" onclick="showTree()" readonly="readonly"/>
                <input name="parentId" type="hidden"/>
                <div style = "font-size:12px; color:#afadad;text-indent: 70px;">(不选父分类则默认一级分类)</div>
            </li>
            <li  id="showDiv" style="display: none;padding-top: 0px;position:relative;" >
                <div class="zTreeDemoBackground left" style  = "position:absolute; left: -268px; top: 29px;"   onblur="test(event)">
                    <ul id="categoryTree" class="ztree" style  = "width:235px; height: 140px!important;"></ul>
                </div>
                <img  src="${ctx}/assets/img/Closed_16px.png"  alt="关闭" style="vertical-align: top;position:absolute; left: -50px; top: 40px;" onclick="hideTree()">
            </li>
            <li>
                <span>分类名称：</span><input style="width:250px;" type="text" name="cateName" id="cateName" onblur="notEmpty('cateName','cateName','分类名称不能为空！');" maxlength="50"/>
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
    function saveData() {
        if(notEmpty('cateName','cateName','分类名称不能为空！')){
        var data = $("#addFrom").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/category/add",
            async: false, // 此处必须同步
            dataType: "json",
            data:data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("新增成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        parent.location.reload();
                    }, 1000);

                } else {
                    layer.msg("新增失败！", {icon: 2});
                }
            },
            error: function () {
                layer.msg("新增失败！", {icon: 2});
            }

        });
        }

    }


    function setCityName(){
        $("#cityName").val($("#cityId option:selected").text())
    }


    //取消
    function closeWin() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }
</script>
</body>
</html>