<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<%@include file="../layout/source.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>


<!--列表界面-->
<div class="box">
    <section class="content">
        <p class="detail-title">
            <span>新房详情</span>

            <a class="setup" target="contentF" onclick="toAddPage()">新增</a>
        </p>

        <a class="left" target="contentF" href="${ctx}/rest/quotation/toolsTip_index">生成预览</a>

        <form action="${ctx }/rest/quotation/list" method="POST" id="searchForm"
              onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
            <input type="hidden" name="isNew" id="isNew" value="${isNew}"/>
            <ul class="userinfo row">

                <%@include file="../quotationSecondHand/dist.jsp" %>


                <li><span>类型：</span>
                    <select name="type" id="type" class="dropdown">
                        <option value="">全部</option>
                        <c:forEach items="${types}" var="type">
                            <option value="${type.id}">${type.name}</option>
                        </c:forEach>
                    </select>

                </li>
                <li>
                    <input class="public_btn bg1" type="submit" name="inquery" id="inquery" value="查询"/>
                    <a class="left" onclick="import1()" >导入</a> <a class="setup" href="#" onclick="export1()">导出</a>
                </li>
            </ul>
        </form>
        <div id="listDiv"></div>

    </section>
</div>



<script type="text/javascript">

    function import1() {
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['500px', '300px'],
            shadeClose: false, //点击遮罩关闭
            title: ['导入'],
            resize: false,
            scrollbar: false,
            content: ['${ctx}/rest/quotation/to_import', 'no']
        });
    }


    function export1(){
        var data = $("#searchForm").serialize();

        window.location.href="${ctx}/rest/quotation/export?"+data;
    }

    function toAddPage() {
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['500px', '660px'],
            shadeClose: false, //点击遮罩关闭
            title: ['新增'],
            resize: false,
            scrollbar: false,
            content: ['${ctx}/rest/quotation/to_add', 'no']
        });
    }
    window.onload = function () {
        $('#searchForm').submit();
    }

</script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>