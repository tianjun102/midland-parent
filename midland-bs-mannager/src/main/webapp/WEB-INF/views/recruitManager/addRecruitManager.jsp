<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
    </script>

</head>
<body>
<style>
    .layui-layer{
        top:260px!important;
    }
</style>

<div class="box">
    <section class = "content">
        <p class = "detail-title">
            <span>添加招聘信息</span>
        </p>
        <form id="dataForm" action="${ctx}/rest/recruitManager/add" method="post" enctype="multipart/form-data" method="post">
            <input type="hidden" name="cityName" id="cityName" value="" >
            <ul class = "adminfo row">
                <li><span>招聘类型：</span>
                    <select name="type" id="type" class="dropdown" >
                        <option value="" class="label">请选择</option>
                        <option value="1">校招</option>
                        <option value="2">社招</option>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li><span>城市：</span>
                    <select onchange="setCityName();" name="cityId" id="cityId" style="height: 38px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="">全部</option>
                        <c:forEach items="${cityList}" var="city">
                            <option value="${city.id}">${city.name}</option>
                        </c:forEach>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li>
                    <span>上线时间：</span>
                    <input type="text" name="startTime" class="Wdate half" id="time3" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time4\')}'})" />
                    <em class = "gang">-</em>
                    <input type="text" name="endTime" class="Wdate half" id="time4" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time3\')}'})"/>
                </li>
                <li><span>招聘岗位：</span>
                    <input id="post" name="post" maxlength="255" type="text" value="">
                </li>
                <li><span>招聘人数：</span>
                    <input id="recruitersNum" name="recruitersNum" maxlength="255" type="text" value="">
                </li>
                <li><span>学历要求：</span>
                    <select name="education" id="education" style="height: 38px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="0">不限</option>
                        <option value="1">高中</option>
                        <option value="2">大专</option>
                        <option value="3">本科</option>
                        <option value="4">硕士及硕士以上</option>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li><span>工作年限：</span>
                    <select name="workLift" id="workLift" style="height: 38px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="0">不限</option>
                        <option value="1">应届毕业生</option>
                        <option value="2">1-3年</option>
                        <option value="3">3-5年</option>
                        <option value="4">5-10年</option>
                        <option value="5">10年以上</option>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li><span>发布状态：</span>
                    <select name="releaseStatus" id="releaseStatus" style="height: 38px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="0">上线</option>
                        <option value="1">下线</option>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li id="textArea" ><span>职位描述：</span><textarea style="width: 93%;min-height: 250px;resize:none; outline-color: #0099e0;float: right" name="postDesc" id="myEditor" rows="" cols=""></textarea></li>
                <li id="textArea1" ><span>任职要求：</span><textarea style="width: 93%;min-height: 250px;resize:none; outline-color: #0099e0;float: right" name="jobClaim" id="myEditor1" rows="" cols=""></textarea></li>

            </ul>




            <ul class = "adminfo row">
                <li>
                    <span></span>
                    <a onclick="updateData();" target="contentF" class = "public_btn bg2">保存</a>
                    <a style="margin-left: 20px" href="${ctx}/rest/banner/bannerindex" target="contentF" class="public_btn bg3" id="cancel">取消</a>
                </li>
            </ul>
        </form>
    </section>
</div>

<script type="text/javascript">
    UE.getEditor('myEditor');
    UE.getEditor('myEditor1');
    //保存数据
    function updateData() {
        var data = $("#dataForm").serialize();
        debugger;
        $.ajax({
            type: "post",
            url: "${ctx}/rest/recruitManager/add",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("保存成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function(){window.open("${ctx}/rest/recruitManager/index","contentF");},2000);
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
</script>
</body>
</html>