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
            type: 1,
            skin: 'layer-style',
            area: ['350px','250px'],
            shadeClose: false, //点击遮罩关闭
            title:['审核'],
            resize: false,
            scrollbar:false,
            content:
            '<form action="${ctx}/rest/answer/update" method="post" id="answerAuditForm">'+
                '<input type="hidden" name="id" value=\"'+id+'\"/>'
            +'<li><span>备注：</span><textarea style="width:80%;height:100px" name="auditRemark" id="blackRemark"></textarea></li>'
            +'</ul></form>',
            btn:["通过",'拒绝'],
            yes: function(index){
                var data = $("#answerAuditForm").serialize();
                alert(data);
                $.ajax({
                    type: "post",
                    url: "${ctx}/rest/questions/updateAnswer",
                    cache:false,
                    async:false, // 此处必须同步
                    data:data+"&auditStatus=1",
                    dataType: "json",
                    success: function(xmlobj){
                        if(xmlobj.flag==1){
                            layer.msg("审核成功！",{icon:1});
                            setTimeout(function(){$("#searchForm").submit();},1000);
                        }
                        if(xmlobj.flag==0){
                            layer.msg("审核失败！！",{icon:7});
                        }
                        layer.close(index);
                    }
                });
            },
            btn2: function(index){
                var data = $("#answerAuditForm").serialize();
                alert(data);
                $.ajax({
                    type: "post",
                    url: "${ctx}/rest/questions/updateAnswer",
                    cache:false,
                    async:false, // 此处必须同步
                    data:data+"&auditStatus=2",
                    dataType: "json",
                    success: function(xmlobj){
                        if(xmlobj.flag==1){
                            layer.msg("审核成功！",{icon:1});
                            setTimeout(function(){$("#searchForm").submit();},1000);
                        }
                        if(xmlobj.flag==0){
                            layer.msg("审核失败！！",{icon:7});
                        }
                        layer.close(index);
                    }
                });
            }
            ,success: function (layero) {
                var btn = layero.find('.layui-layer-btn');
                btn.css('text-align', 'center');
            }
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