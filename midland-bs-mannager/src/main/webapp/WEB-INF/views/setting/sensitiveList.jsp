<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        .public_text {
            font-size: 14px;
            display: inline-block;
            color: #463d3d;
            background: #e8e9f3;
            padding-left: 5px;
            padding-right: 5px;
            padding-top: 5px;
            margin: 5px;
            line-height: 28px;
            vertical-align: middle;
            text-align: center;
            border: none;
            border-radius: 4px;
            text-indent: 0;
            position: relative;
        }
        .xclose {
            display: block;
            position: absolute;
            right: 0;
            top: 0;
            width: 10px;
            height: 10px;
            line-height: 10px;
            text-align: center;
            background: rgba(0, 0, 0, .7);
            font-size: 14px;
            color: #ddd;
            cursor: pointer;
        }
    </style>
</head>
<body>


<div class="table-responsive m40" style="min-height:400px;">
    <table class="table table-bordered table-add" >
        <c:forEach items="${items}" var="item" >
            <div class="public_text">${item}<i class='xclose' onclick="delNote('${item}',this)">×</i></div>
        </c:forEach>
    </table>
</div>
<!-- 分页 -->
<c:if test="${!empty paginator}">
    <c:set var="paginator" value="${paginator}"/>
    <c:set var="target" value="listDiv"/>
    <%@include file="../layout/pagination.jsp" %>
</c:if>

<script type="text/javascript">
    $(function () {
        $(".table-responsive").css("max-height",result);
    })
    function delNote(i,ths) {
        var thisObj=$(ths);

        var data="V1="+i;
        $.ajax({
            type: "post",
            url: "${ctx}/rest/sensitive/del",
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    layer.msg("删除成功！！！", {icon: 1});
                    thisObj.parent().remove();

                } else {
                    layer.msg("删除失败！", {icon: 2});
                }
            },
            error: function (data) {
                layer.msg("删除失败！", {icon: 2});
            }
        });

    }
    $(".fileupload").delegate(".xclose", "click", function () {
        var temp = "";
        var $this = $(this);
        var $parent = $this.parent("span");
        var imgsrcs = $("#imgUrl").val();
        var imgsrc = $parent.find("img").attr("src");
        var imgArray = imgsrcs.split("||");
        for (var i = 0; i < imgArray.length; i++) {
            if (imgArray[i].match(imgsrc)) {
                continue;
            }
            if (imgArray[i] != "" && imgArray != null) {
                temp += imgArray[i] + "||";
            }
        }
        $("#imgUrl").val(temp);
        $parent.remove();
    });

</script>
</body>
</html>