<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>

    <link rel="stylesheet" href="${ctx }/assets/css/bootstrap.min.css">

    <link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css"/>
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">

</head>
<body>


<!--预约看房重新分配经纪人-->
<div class="box">
    <section class="content">
        <div style="margin-bottom: 10px;">
            <span style="padding-right: 15px;">提问人：${questions.questionName}</span>
            <span style="padding-right: 15px;">手机号码：${questions.questionPhone}</span>
            <span>提问时间：${questions.questionTime}</span>
        </div>
        <div style="margin-bottom: 10px;word-wrap: break-word;">
            <span>提问主题：${questions.questionsTitle}</span>
        </div>
        <div>
            <span style="word-wrap: break-word;">提问内容：${questions.questionsArea}</span>
        </div>

        <form id="formId" action="${ctx}/rest/questions/update" method="post" enctype="multipart/form-data"
              method="post">
            <input type="hidden" name="questionsId" id="questionsId" value="${questions.id}">
            <ul class="adminfo row">

                <li id="textArea" style="display: block;">
                    <span>页面内容：</span>
                    <textarea style="width: 92%;min-height: 350px;resize:none; outline-color: #0099e0;"
                              name="answerArea" id="myEditor" rows="" cols="">${item.disclaimer}</textarea>
                </li>

                <ul class="adminfo row">
                    <li>
                        <span></span>
                        <a onclick="subumintBanner();" target="contentF" class="public_btn bg2">保存</a>
                        <a style="margin-left: 20px" href="${ctx}/rest/banner/bannerindex" target="contentF"
                           class="public_btn bg3" id="cancel">取消</a>
                    </li>
                </ul>
            </ul>
        </form>
        </table>
        <a href="${ctx}/rest/questions/index">返回</a>
    </section>
</div>


<script type="text/javascript">

    HasCheked = true;
    UE.getEditor('myEditor');


    function subumintBanner() {
        var data = $("#formId").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/questions/repeat",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("保存成功！", {icon: 1});
                    setTimeout(function () {
                        setTimeout(function(){window.open("${ctx}/rest/questions/index","contentF");},1000);
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


    window.onload = function () {
        $('#searchForm').submit();
    }


    function deleteAnswer() {
        var data = $("#answerId").val();
        update(data);

    }
    function update(data) {
        $.ajax({
            type: "post",
            url: "${ctx}/rest/questions/answer/delete",
            async: false, // 此处必须同步
            dataType: "json",
            data: "id=" + data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("删除成功！！！", {icon: 1});
                    $('#save').removeAttr("onclick");
                    setTimeout(function () {
                        parent.location.reload();
                    }, 1000);

                } else {
                    layer.msg("删除失败！", {icon: 2});
                }
            },
            error: function () {
                layer.msg("删除失败！", {icon: 2});
            }
        });
    }


</script>
<!-- 本页私有js -->


<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/common.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/base.js"></script>
<script src="${ctx}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</body>
</html>