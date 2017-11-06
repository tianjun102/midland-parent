<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../indexJS.jsp" %>
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
        <div id="headIndex">
            <p class="detail-title">
                <span>详情信息管理>>二手房信息管理</span>
                <a class="setup" target="contentF" onclick="toAddPage()">新增</a>
            </p>
            <form action="${ctx }/rest/quotationSecondHand/list" method="POST" id="searchForm"
                  onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
                <input type="hidden" name="isNew" id="isNew" value="${isNew}"/>
                <ul class="userinfo row">
                    <%@include file="../menu/dist.jsp" %>
                    <li><span>类型：</span>
                        <select name="type" id="type" class="dropdown">
                            <option value="">全部</option>
                            <c:forEach items="${types}" var="type">
                                <option value="${type.id}">${type.name}</option>
                            </c:forEach>
                        </select>

                    </li>
                    <c:if test="${not empty isSuper}">
                        <li><span>是否删除：</span>
                            <select name="isDelete" id="isDelete" class="dropdown">
                                <option value="">全部</option>
                                <c:forEach items="${isDeletes}" var="s1">
                                    <option value="${s1.id}" <c:if test="${s1.id==0}">selected</c:if>>
                                            ${s1.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </li>
                    </c:if>
                    <li>
                        <input class="public_btn bg1" type="submit" name="inquery" id="inquery" value="查询"/>
                    </li>

                </ul>
            </form>
            <input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;"
                   onclick="batchDelete(1)" class="public_btn bg1" type="submit" value="批量删除"/>
            <input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;"
                   onclick="batchDelete(0)" class="public_btn bg1" type="submit" value="批量恢复"/>
            <input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;" onclick="import1()"
                   class="public_btn bg1" type="submit" value="导入"/>
            <input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;" onclick="export1()"
                   class="public_btn bg1" type="submit" value="导出"/>
            <input style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;"
                   onclick="window.open('${ctx}/rest/quotationSecondHand/toolsTip_index','contentF')"
                   class="public_btn bg1" type="submit" value="生成预览"/>
        </div>
        <div id="listDiv"></div>

    </section>

</div>


<script type="text/javascript">
    var allHeight = $(window).height();
    function import1() {
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['500px', '200px'],
            shadeClose: false, //点击遮罩关闭
            title: ['导入'],
            resize: false,
            scrollbar: false,
            content: ['${ctx}/rest/quotationSecondHand/to_import', 'no']
        });
    }

    function export1() {
        var data = $("#searchForm").serialize();

        window.location.href = "${ctx}/rest/quotationSecondHand/export?" + data;
    }

    function toAddPage() {
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['450px','400px'],
            shadeClose: false, //点击遮罩关闭
            title: ['新增'],
            resize: false,
            scrollbar: false,
            content: ['${ctx}/rest/quotationSecondHand/to_add', 'no']
        });
    }

    window.onload = function () {
        $('#searchForm').submit();
    }


</script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>