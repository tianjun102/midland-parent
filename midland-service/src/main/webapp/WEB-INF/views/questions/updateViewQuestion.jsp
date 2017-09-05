<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>

    <link rel="stylesheet" href="${ctx }/assets/css/bootstrap.min.css">

    <link rel="stylesheet" href="${ctx }/assets/css/easydropdown.css" />
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">

</head>
<body>


<!--预约看房重新分配经纪人-->
<div class="box">
    <section class = "content">
        <ul class = "userinfo row">

            <li>
                <span>提问人：</span><span >${questions.questionName}</span>
                <span>手机号码：</span><span >${questions.questionPhone}</span>
            </li>


        </ul>
        <ul>
            <li>
                <span>提问时间：</span><span >${questions.questionTime}</span>
            </li>
            <li>
                <span>提问主题：</span><span >${questions.questionsTitle}</span>
            </li>
            <li>
                <span>提问内容：</span><span >${questions.questionsArea}</span>
            </li>

        </ul>

        <table class="table table-bordered table-add">
            <thead>
            <tr>
                <th>回答内容</th>
                <th>回答时间</th>
                <th>经纪人姓名</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${!empty requestScope.answerList }">
                    <c:forEach items="${requestScope.answerList }" var="item"
                               varStatus="xh">
                        <tr>
                            <input type="hidden" id="answerId" value="${item.id}" />
                            <td>${item.answerArea }</td>
                            <td>${item.answerTime }</td>
                            <td>${item.answerName }</td>
                            <td>

                            <a target="contentF" onclick="deleteAnswer(${item.id })">删除</a>

                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="14">没有找到数据!</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>

    </section>
</div>


<script type="text/javascript">
    window.onload = function(){
        $('#searchForm').submit();
    }


    function deleteAnswer(){
        var data = $("#answerId").val();
        update(data);

    }
    function update(data){
        $.ajax({
            type: "post",
            url: "${ctx}/rest/questions/answer/delete",
            async: false, // 此处必须同步
            dataType: "json",
            data: "id="+data,
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
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/common.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/base.js" ></script>
<script src="${ctx}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</body>
</html>