<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${ctx }/assets/css/common.css">

</head>
<body>
<div class="box">
    <section class="content">
        <div style="margin-bottom: 10px;">
            <span style="padding-right: 15px;">回答人：${answer.answerName}</span>
            <span style="padding-right: 15px;">手机号码：${answer.answerPhone}</span>
            <span>回答时间：${answer.answerTime}</span>
        </div>

        <div>
            <span>内容：</span>
            <textarea style="margin: 0px; width: 555px; height: 142px;" disabled="disabled">${answer.answerArea}</textarea>
        </div>

        <form id="formId" action="${ctx}/rest/questions/update" method="post" enctype="multipart/form-data"
              method="post">
            <input type="hidden" name="id" id="id" value="${answer.id}">

            <span>备注：</span>
            <textarea style="margin: 0px; width: 552px; height: 138px;" name="auditRemark"></textarea>

            <ul class="adminfo row">
                <li>
                    <span></span>
                    <a onclick="audit('${answer.id}','2');" target="contentF" class="public_btn bg2">通过</a>
                    <a onclick="audit('${answer.id}','3');" target="contentF" class="public_btn bg2">拒绝</a>
                    <a style="margin-left: 20px" href="${ctx}/rest/questions/index" target="contentF"
                       class="public_btn bg3" id="cancel">取消</a>
                </li>
            </ul>
        </form>
        </table>
    </section>
</div>

<script type="text/javascript">


    function audit(id,status) {
        var data = $("#formId").serialize();

        $.ajax({
            type: "post",
            url: "${ctx}/rest/questions/updateAnswer?auditStatus="+status,
            cache: false,
            async: false, // 此处必须同步
            dataType: "json",
            data:data,
            success: function (obj) {
                if (obj.state == 0) {
                    layer.msg("成功！", {icon: 5});
                    parent.window.location.reload();
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index);
                }
                if (obj.state == -1) {
                    layer.msg("失败！！", {icon: 7});
                }

            }
        });
    }

    //取消
    function closeWin() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        layer.closeAll();
    }


    function rejust(userId) {
        $.ajax({
            type: "post",
            url: "${ctx}/rest/user/audit?id=" + userId + '&auditStatus=3' + '&auditRemark=' + $('#auditRemark').val(),
            cache: false,
            async: false, // 此处必须同步
            dataType: "json",
            success: function (obj) {
                if (obj.state == 0) {
                    layer.msg("成功！", {icon: 5});
                    parent.window.location.reload();
                    parent.layer.closeAll();
                }
                if (obj.state == -1) {
                    layer.msg("失败！！", {icon: 7});
                }

            }
        });
    }

</script>
<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>