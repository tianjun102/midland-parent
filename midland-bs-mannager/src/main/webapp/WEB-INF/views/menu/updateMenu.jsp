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
        .content ul.userinfo li:not(:last-child) input {
            float: left;
            width: 250px;
            height: 28px;
            line-height: 28px;
            border: 1px solid #dbe2e6;
            border-radius: 4px;
            text-indent: 10px;
            outline-color: #0099e0;
        }
        .content ul.userinfo>li {
            display: flex;
            float: none !important;
            margin-left: 20px;
            padding-top: 20px;
        }

        .dropdown {
            width: 250px!important;
        }
        .vipcate{
            width: 250px!important;;
            height: 28px;
            line-height: 28px;
            border: 1px solid #dbe2e6;
            border-radius: 4px;
            text-indent: 10px;
            outline-color: #0099e0;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $('#file_upload').uploadify({
                'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
                'uploader': '${ctx }/rest/upload/img',
                'multi': false,// 是否支持多个文件上传
                'buttonText': '上传文件',
                'onUploadSuccess': function (file, data, response) {
                    $("#iconImg").attr("value", data);
                    $("#iconImg1").attr("src", data);
                },
                'onQueueComplete': function (queueData) {
                    if (queueData.uploadsSuccessful < 1) {
                        alert('文件上传失败');
                    }
                }
            });
            if (${item.source ==1}){
                $("#menuTypeZtreeId").css('display', 'block');
            }


        })
    </script>
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
        var catProNodes =[${menuTypes}];


        $(document).ready(function(){
            $.fn.zTree.init($("#categoryTree"), setting, catProNodes);
        });

        function beforeClick(treeId, treeNode, clickFlag) {
            $("input[name='menuTypeId']").val(treeNode.id);
            $("input[name='menuTypeName']").val(treeNode.name);
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
    <form action="${ctx}/rest/menu/add" method="post" id="dataForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <%@include file="area_required.jsp" %>

            <li style="display:flex;align-items:center">
                <span>平台：</span>
                <select name="source" id="source" class="dropdown" <c:if test="${empty isSuper}">disabled="disabled"</c:if> onchange="fieldChange()">
                    <c:forEach items="${sources}" var="s">
                        <option value="${s.id}" <c:if test="${s.id == item.source}">selected="selected"</c:if>>
                                ${s.name}
                        </option>
                    </c:forEach>
                </select>
            </li>

            <li style="display: none" id="menuTypeZtreeId"><span>类型：</span>
                <input class="vipcate" id="menuTypeName" name="menuTypeName" onclick="showTree()" value="${item.menuTypeName}" readonly="readonly"/>
                <input name="menuTypeId" type="hidden" value="${item.menuTypeId}"/><label style="color: red" class = "_star " >*</label>
            </li>
            <li  id="showDiv" style="display: none;padding-top: 0px;padding-left: 70px; position:relative;" >
                <div class="zTreeDemoBackground left" style  = "position:absolute;top: -10px;"   onblur="test(event)">
                    <ul id="categoryTree" class="ztree" style  = "width:250px; height: 140px!important;"></ul>
                </div>
                <img  src="${ctx}/assets/img/Closed_16px.png"  alt="关闭" style="vertical-align: top;position:absolute; left: 323px;" onclick="hideTree()">
            </li>
            <li><span>菜单名：</span>
                <input type="text" name="menuName" id="menuName" value="${item.menuName}" onblur="notEmpty('menuName','menuName','')"/>
                <label style="color: red" class = "_star " >*</label>
            </li>
            <li><span>链接：</span>
                <input type="text" name="url" id="url" value="${item.url}" onblur="checkUrl('url','url','')"/>
            </li>
            <li><span>图标：</span>
                <div style="float: left;">
                    <input type="hidden" name="iconImg" id="iconImg" value="${item.iconImg}">

                    <img style="margin-bottom: 10px;max-width:80px;max-height:80px" id="iconImg1"
                         src="${item.iconImg}">
                    <input type="file" name="file_upload" id="file_upload"/>
                </div>
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
    function fieldChange() {
        var j = $("#source option:selected").val()
        if (j == 0) {
            $("#menuTypeId").val("")
            $("#menuTypeName").val("")
            $("#menuTypeZtreeId").css('display', 'none');
        } else if (j == 1) {
            $("#menuTypeId").val("")
            $("#menuTypeName").val("")
            $("#menuTypeZtreeId").css('display', 'block');

        }
    }

    //保存数据
    function updateData() {
        if (!checkSelect('citys','请选择市级')||!notEmpty('menuName','menuName','')||!checkUrl('url','url','')){
            return;
        }
        var data = $("#dataForm").serialize();

        $.ajax({
            type: "post",
            url: "${ctx}/rest/menu/update",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("保存成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        parent.location.reload();
                    }, 1000);

                } else {
                    layer.msg("保存失败！", {icon: 2});
                }
            },
            error: function () {
                layer.msg("保存失败！", {icon: 2});
            }
        });
    }

    //取消
    function closeWin() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }



</script>
</body>
</html>