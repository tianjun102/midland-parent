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

    <style type="text/css">
        .content ul.userinfo li>span{
            float: left;
            display: inline-block;
            width: 90px;
            height: 38px;
            line-height: 38px;
            text-align: right;
            font-size: 14px;
            color: rgb( 102, 102, 102 );
        }
        .table-add tr td a {
            display: inline-block;
            width: 38px;!important;
            height: 20px;
            margin: 0 5px;
            background-size: contain!important;
        }
        td
        {
            white-space: nowrap;
        }
        th
        {
            white-space: nowrap;
        }
    </style>
</head>
<body>


<!--预约看房重新分配经纪人-->
<div class="box">
    <section class = "content">
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
        <table class="table table-bordered table-add">
            <thead>
            <tr>
                <th>回答内容</th>
                <th>回答时间</th>
                <th>经纪人姓名</th>
                <th>审核状态</th>
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
                            <td><c:forEach items="${auditStatusList}" var="s">
                                <c:if test="${item.auditStatus == s.id}">${s.name}</c:if>
                            </c:forEach></td>
                            <td>
                            <a target="contentF" onclick="deleteAnswer(${item.id })">删除</a>
                                <c:if test="${item.auditStatus==0}">
                                    <a target="contentF" onclick="auditAnswer(${item.id })">审核</a>
                                </c:if>

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
        <a style="margin-left: 20px" href="${ctx}/rest/questions/index" target="contentF"
           class="public_btn bg3" id="cancel">返回</a>
    </section>
</div>


<script type="text/javascript">
    window.onload = function(){
        $('#searchForm').submit();
    }

    function auditAnswer(id){

        layer.open({
            type: 2,
            title: ['查看回答'],
            shade: 0.3,
            area: ['700px', '500px'],
            content: ['${ctx}/rest/questions/audit_answer?id=' + id , 'no']
        });
    }


    function deleteAnswer(data){
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
                        location.reload();
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