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
        <form id="dataForm" action="${ctx}/rest/banner/addBanner" method="post" enctype="multipart/form-data" method="post">
            <input type="hidden" name="id" value="${item.id}">
            <input type="hidden" name="cityName" id="cityName" value="${item.cityName}" >
            <ul class = "adminfo row">
                <li><span>招聘类型：</span>
                    <select name="type" id="type" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;" >
                        <option value="" class="label">请选择</option>
                        <option <c:if test="${item.type==1}">selected="selected"</c:if> value="1">校招</option>
                        <option <c:if test="${item.type==2}">selected="selected"</c:if> value="2">社招</option>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li><span>城市：</span>
                    <select onchange="setCityName();" name="cityId" id="cityId" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="">全部</option>
                        <c:forEach items="${cityList}" var="city">
                            <option <c:if test="${item.cityId==city.id}">selected="selected"</c:if> value="${city.id}">${city.name}</option>
                        </c:forEach>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li>
                    <span>上线时间：</span>
                    <input type="text" value="${item.startTime}" name="startTime" class="Wdate half" id="time3" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time4\')}'})" />
                    <em class = "gang">-</em>
                    <input type="text" value="${item.endTime}" name="endTime" class="Wdate half" id="time4" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time3\')}'})"/>
                </li>
                <li><span>招聘岗位：</span>
                    <input id="post" name="post" maxlength="255" type="text" value="${item.post}">
                </li>
                <li><span>招聘人数：</span>
                    <input id="recruitersNum" name="recruitersNum" maxlength="255" type="text" value="${item.recruitersNum}">
                </li>
                <li><span>学历要求：</span>
                    <select name="education" id="education" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option <c:if test="${item.education==0}">selected="selected"</c:if> value="0">不限</option>
                        <option <c:if test="${item.education==1}">selected="selected"</c:if> value="1">高中</option>
                        <option <c:if test="${item.education==2}">selected="selected"</c:if> value="2">大专</option>
                        <option <c:if test="${item.education==3}">selected="selected"</c:if> value="3">本科</option>
                        <option <c:if test="${item.education==4}">selected="selected"</c:if> value="4">硕士及硕士以上</option>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li><span>工作年限：</span>
                    <select name="workLift" id="workLift" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option <c:if test="${item.workLift==0}">selected="selected"</c:if> value="0">不限</option>
                        <option <c:if test="${item.workLift==1}">selected="selected"</c:if> value="1">应届毕业生</option>
                        <option <c:if test="${item.workLift==2}">selected="selected"</c:if> value="2">1-3年</option>
                        <option <c:if test="${item.workLift==3}">selected="selected"</c:if> value="3">3-5年</option>
                        <option <c:if test="${item.workLift==4}">selected="selected"</c:if> value="4">5-10年</option>
                        <option <c:if test="${item.workLift==5}">selected="selected"</c:if> value="5">10年以上</option>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li><span>上线状态：</span>
                    <select name="releaseStatus" id="releaseStatus" style="height: 28px;width: 250px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option <c:if test="${item.releaseStatus==0}">selected="selected"</c:if> value="0">上线</option>
                        <option <c:if test="${item.releaseStatus==1}">selected="selected"</c:if> value="1">下线</option>
                    </select>
                    <span class = "_star ">*</span>
                </li>
                <li id="textArea" ><span>职位描述：</span><textarea style="width: 93%;min-height: 250px;resize:none; outline-color: #0099e0;float: right" name="postDesc" id="myEditor" rows="" cols="">${item.postDesc}</textarea></li>
                <li id="textArea1" ><span>任职要求：</span><textarea style="width: 93%;min-height: 250px;resize:none; outline-color: #0099e0;float: right" name="jobClaim" id="myEditor1" rows="" cols="">${item.jobClaim}</textarea></li>

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
        $.ajax({
            type: "post",
            url: "${ctx}/rest/recruitManager/update",
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