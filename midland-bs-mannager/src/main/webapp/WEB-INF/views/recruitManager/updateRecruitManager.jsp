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
<section class="content" style="border:none;">
    <form action="${ctx}/rest/recruitManager/update" method="post" id="dataForm">
        <ul class="userinfo row">
            <input type="hidden" name="id" id="id" value="${item.id}">
            <li><span>type：</span>
               <input type="text" name="type" id="type" value="${item.type}"/>
            </li>
            <li><span>post：</span>
               <input type="text" name="post" id="post" value="${item.post}"/>
            </li>
            <li><span>recruitersNum：</span>
               <input type="text" name="recruitersNum" id="recruitersNum" value="${item.recruitersNum}"/>
            </li>
            <li><span>education：</span>
               <input type="text" name="education" id="education" value="${item.education}"/>
            </li>
            <li><span>workLift：</span>
               <input type="text" name="workLift" id="workLift" value="${item.workLift}"/>
            </li>
            <li><span>startTime：</span>
               <input type="text" name="startTime" id="startTime" value="${item.startTime}"/>
            </li>
            <li><span>endTime：</span>
               <input type="text" name="endTime" id="endTime" value="${item.endTime}"/>
            </li>
            <li><span>releaseStatus：</span>
               <input type="text" name="releaseStatus" id="releaseStatus" value="${item.releaseStatus}"/>
            </li>
            <li><span>postDesc：</span>
               <input type="text" name="postDesc" id="postDesc" value="${item.postDesc}"/>
            </li>
            <li><span>jobClaim：</span>
               <input type="text" name="jobClaim" id="jobClaim" value="${item.jobClaim}"/>
            </li>
            <li><span>cityId：</span>
               <input type="text" name="cityId" id="cityId" value="${item.cityId}"/>
            </li>
            <li><span>cityName：</span>
               <input type="text" name="cityName" id="cityName" value="${item.cityName}"/>
            </li>
            <li><span>releaseTime：</span>
               <input type="text" name="releaseTime" id="releaseTime" value="${item.releaseTime}"/>
            </li>
            <li><span>email：</span>
               <input type="text" name="email" id="email" value="${item.email}"/>
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
    //保存数据
    function updateData() {
        var data = $("#dataForm").serialize();
        debugger;
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