<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
    </script>

</head>
<body>
<section class="content" style="border:none;">
    <form action="${ctx}/rest/siteMap/add" method="post" id="dataForm">
        <input type="hidden" name="cityName" id="cityName" value="" >
        <ul class="userinfo row">
            <li><span>关键字：</span>
               <input type="text" name="name" id="name"/>
            </li>
            <li><span>平台：</span>
                <select name="source" id="source" style="height: 38px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option value="0">网站</option>
                    <option value="1">微站</option>
                </select>
                <span class = "_star ">*</span>
            </li>
            <li><span>城市：</span>
                <select onchange="setCityName();" name="cityId" id="cityId" style="height: 38px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option value="">全部</option>
                    <c:forEach items="${cityList}" var="city">
                        <option value="${city.id}">${city.name}</option>
                    </c:forEach>
                </select>
                <span class = "_star ">*</span>
            </li>

            <li><span>分类：</span>
                <input type="hidden" name="cateName" id="cateName" value="">
                <select onchange="setCateName();" name="cateId" id="cateId" style="height: 38px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                    <option value="">全部</option>
                        <option value="1">新房</option>
                        <option value="1">二手房</option>
                        <option value="1">租房</option>
                        <option value="1">写字楼</option>
                        <option value="1">商铺</option>
                </select>
                <span class = "_star ">*</span>
            </li>

            <li>
                <span>模块名称：</span><input type="text" name="modeName" >
            </li>

            <li><span>链接：</span>
               <input type="text" name="linkUrl" id="linkUrl" />
            </li>

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
        var data = $("#dataForm").serialize();
        debugger;
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

    function setCityName(){
        $("#cityName").val($("#cityId option:selected").text())
    }

    function setCateName(){
        $("#cateName").val($("#cateId option:selected").text())
    }
</script>
</body>
</html>