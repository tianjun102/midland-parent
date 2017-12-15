<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp"%>

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

        <form id="formId" action="${ctx}/rest/record/add" method="post" enctype="multipart/form-data"
              method="post">
            <input type="hidden" name="questionsId" id="questionsId" value="${item.id}">
            <ul class="adminfo row">
                <%@include file="../menu/area_required.jsp" %>
                <li id="textArea" style="display: block;">
                    <textarea style="width: 92%;min-height: 350px;resize:none; outline-color: #0099e0;"
                              name="record" id="myEditor" rows="" cols="">${item.record}</textarea>
                </li>

                <ul class="adminfo row">
                    <li>
                        <span></span>
                        <a onclick="subumintBanner();" target="contentF" class="public_btn bg2">保存</a>
                        <a style="margin-left: 20px" href="${ctx}/rest/record/index" target="contentF"
                           class="public_btn bg3" id="cancel">取消</a>
                    </li>
                </ul>
            </ul>
        </form>
        </table>
    </section>
</div>


<script type="text/javascript">

    HasCheked = true;
    UE.getEditor('myEditor');


    function subumintBanner() {

        if (!checkSelect('citys','请选择市级')){
            return;
        }
        var data = $("#formId").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/record/add",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("保存成功！", {icon: 1});
                    setTimeout(function () {
                        setTimeout(function(){window.open("${ctx}/rest/record/index","contentF");},1000);
                    }, 1000);
                }else if (data.state == 1){
                    layer.msg("该城市已经有备案", {icon: 2});
                }

                else {
                    layer.msg("保存失败！", {icon: 2});
                }
            },
             error: function (data) {                        if (data.responseText!= null){                            layer.msg(data.responseText, {icon: 2});                        }else {                            layer.msg("保存失败！", {icon: 2});                        }                    }

        });

    }


    window.onload = function () {
        $('#searchForm').submit();
    }

</script>
<!-- 本页私有js -->
</body>
</html>