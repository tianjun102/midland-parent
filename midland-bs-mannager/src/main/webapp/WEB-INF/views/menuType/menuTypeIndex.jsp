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
                <a class="setup" target="contentF" onclick="toAddPage()">新增</a>
            </p>
            <form action="${ctx }/rest/menuType/list" method="POST" id="searchForm"
                  onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
                <ul class="userinfo row">
                    <%@include file="../menu/area.jsp" %>
                    <li><span style="width: 100px">分类名称：</span>
                        <input type="text" name="name" id="name" placeholder="请输入分类名称"/></li>

                    </li>
                    <li><span style="width: 120px">上级分类名称：</span>
                        <input type="text" name="parentName" id="parentName" placeholder="请输入上级分类名称"/></li>
                    </li>
                    <c:if test="${not empty isSuper}">
                        <li><span style="width: 100px">是否删除：</span>
                            <select name="isDelete" id="isDelete" class="dropdown" style="width: 90px">
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
            <c:if test="${not empty isSuper}"><input
                    style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;"
                    onclick="batchDelete(0)"
                    class="public_btn bg1" type="submit" value="批量恢复"/>
            </c:if>
        </div>
        <div id="listDiv"></div>
    </section>
</div>


<script type="text/javascript">
    var allHeight = $(window).height();

    function toAddPage() {
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['500px', '300px'],
            shadeClose: false, //点击遮罩关闭
            title: ['新增'],
            resize: false,
            scrollbar: false,
            content: ['${ctx}/rest/menuType/to_add', 'no']
        });
    }

    window.onload = function () {
        $('#searchForm').submit();
    }
</script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>