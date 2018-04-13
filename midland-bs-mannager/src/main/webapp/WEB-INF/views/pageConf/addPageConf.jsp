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

    <script type="text/javascript">
        $(function () {
            $('#file_upload').uploadify({
                'swf': '${ctx }/assets/scripts/uploadify/uploadify.swf',
                'uploader': '${ctx }/rest/upload/img',
                'multi': false,// 是否支持多个文件上传
                'buttonText': '上传图片',
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
        <ul class="adminfo width-lg row">
            <li><span>城市：</span>
                <input type="hidden" name="cityName" id="cityName" value="${cityName}">
                <c:if test="${empty isSuper}"><input type="hidden" name="cityId" value="${cityId}"></c:if>
                <select onchange="setCityName();" name="cityId" id="cityId" <c:if test="${empty isSuper}">disabled="disabled"</c:if>>
                    <option value="">请选择</option>
                    <c:if test="${empty isSuper}"> <option selected="selected" value="${cityId}">${cityName}</option> </c:if>
                    <c:forEach items="${cityList}" var="city">  <option value="${city.id}">${city.name}</option></c:forEach>
                </select>
                <span class="_star ">*</span>
            </li>

            <li><span>平台：</span>
                <select name="source" id="source" onchange="selectSource()">
                    <option value="">请选择</option>
                    <option value="0">网站</option>
                    <option value="1">微站</option>
                </select>
                <span class="_star ">*</span>
            </li>

            <li><span>页面：</span>
                <%--<input type="text" name="model" id="model" value=""/>--%>
                <select name="model" id="model">
                    <option value="">请选择</option>
                    <option value="11">整站</option>
                    <option value="0">首页</option>
                    <option value="1">新房</option>
                    <option value="2">二手房</option>
                    <option value="3">租房</option>
                    <option value="4">写字楼</option>
                    <option value="5">商铺</option>
                    <option value="6">小区</option>
                    <option value="7">经纪人</option>
                    <option value="8">外销网</option>
                    <option value="9">市场研究</option>
                    <option value="10">资讯</option>
                </select>
                <span class="_star ">*</span>
            </li>
            <%--<li><span>META关键词：</span>--%>
            <%--<input type="text" name="metaLable" id="metaLable" value=""/>--%>
            <%--</li>--%>
            <%--<li><span>META描述：</span>--%>
            <%--<input type="text" name="metaDesc" id="metaDesc" value=""/>--%>
            <%--</li>--%>
            <%--<li><span>标题：</span>--%>
            <%--<input type="text" name="title" id="title" value=""/>--%>
            <%--</li>--%>
            <li><span>CNZZ状态：</span>
                <%--<input type="hidden" name="metaShow" id="metaShow" value=""/>--%>
                <span style="width: 50px !important;"> 开启&nbsp; </span><input type="radio" name="metaShow"
                                                                              value="1">
                <span style="width: 50px !important;;"> 关闭&nbsp; </span><input type="radio" name="metaShow"
                                                                               value="0">
            </li>
            <li id="cnzzPc"><span>CNZZ配置：</span><textarea
                    style="width: 87%;min-height: 250px;resize:none; outline-color: #0099e0;float: left"
                    name="cnzzCode" id="myEditor" rows="" cols=""></textarea></li>
            <li id="cnzzWechat" style="display: none"><span>CNZZ微站配置：</span><textarea
                    style="width: 87%;min-height: 250px;resize:none; outline-color: #0099e0;float: left"
                    name="cnzzCodeWechat" id="myEditor2" rows="" cols=""></textarea></li>
            <li><span>百度计量状态：</span>
                <%--<input type="hidden" name="baiduShow" id="baiduShow" value=""/>--%>
                <span style="width: 50px !important;"> 开启&nbsp; </span><input type="radio" name="baiduShow"
                                                                              value="1">
                <span style="width: 50px !important;"> 关闭&nbsp; </span><input type="radio" name="baiduShow"
                                                                              value="0">
            </li>
            <li id="baiduPc"><span>百度计量代码：</span><textarea
                    style="width: 87%;min-height: 250px;resize:none; outline-color: #0099e0;float: left"
                    name="baiduCode" id="myEditor1" rows="" cols=""></textarea></li>
            <li id="baiduWechat" style="display: none"><span>百度计量微站代码：</span><textarea
                    style="width: 87%;min-height: 250px;resize:none; outline-color: #0099e0;float: left ;"
                    name="baiduCodeWechat" id="myEditor3" rows="" cols=""></textarea>
            </li>
            <li>
                <span></span>
                <a onclick="saveData();" target="contentF" class="public_btn bg2">保存</a>
                <a style="margin-left: 20px" onclick="closeWin()" target="contentF"
                   class="public_btn bg3" id="cancel">取消</a>
            </li>
        </ul>
    </form>

</section>
<script type="text/javascript">

    UE.getEditor("myEditor");
    UE.getEditor("myEditor1");
    UE.getEditor("myEditor2");
    UE.getEditor("myEditor3");


    //保存数据
    function saveData() {
        if (checkSelect('cityId|source|model','请选择城市|请选择平台|请选择页面')) {

            var data = $("#dataForm").serialize();
            $.ajax({
                type: "post",
                url: "${ctx}/rest/pageConf/add",
                async: false, // 此处必须同步
                dataType: "json",
                data: data,
                success: function (data) {
                    if (data.state == 0) {
                        layer.msg("保存成功！！！", {icon: 1});
                        $('#save').removeAttr("onclick");
                        setTimeout(function () {
                            setTimeout(function () {
                                parent.layer.closeAll();
                                parent.$("#inquery").click();
                            }, 1000);
                        }, 1000);

                    } else {
                        if(data.state==1){
                            layer.msg("已存在该配置,请勿重复！", {icon: 2});

                        }else{
                            layer.msg("保存失败！", {icon: 2});

                        }
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


    function selectSource() {
        //网站
        if ($("#source option:selected").val() == 0) {
            $("#cnzzPc").show();
            $("#baiduPc").show();
            $("#cnzzWechat").hide();
            $("#baiduWechat").hide();
            //微站
        } else if ($("#source option:selected").val() == 1) {
            $("#cnzzPc").hide();
            $("#baiduPc").hide();
            $("#cnzzWechat").show();
            $("#baiduWechat").show();
        }

    }

    function setCityName() {
        $("#cityName").val($("#cityId option:selected").text())
    }

</script>
</body>
</html>