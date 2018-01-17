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
<div class="box">
    <section class="content">
        <div id="headIndex">
            <p class="detail-title">
                <span>外销网管理>>楼盘展销会</span>
                <a class="setup" target="contentF" onclick="toAddPage()">新增</a>
            </p>
            <form action="${ctx }/rest/tradeFair/list" method="POST" id="searchForm"
                  onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
                <ul class="userinfo row">
                    <li><span>楼盘id：</span>
                        <input type="text" name="housesId" id="housesId" placeholder="请输入楼盘id"/></li>
                    </li>
                    <li><span>楼盘名称：</span>
                        <input type="text" name="title" id="title" placeholder="请输入楼盘名称"/></li>
                    </li>
                    <li><span>状态：</span>
                        <select name="isShow" id="isShow" class="dropdown">
                            <option value="">全部</option>
                            <option value="0">显示</option>
                            <option value="1">隐藏</option>

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
                    <li><input class="public_btn bg1" type="submit" name="inquery" id="inquery" value="查询"/></li>
                </ul>
            </form>
            <input  onclick="batchDelete(1)" class="public_btn bg1  listButton" type="submit" value="批量删除"/>
            <c:if test="${not empty isSuper}">
                <input  onclick="batchDelete(0)" class="public_btn bg1  listButton" type="submit" value="批量恢复"/>
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
            area: ['100%', '100%'],
            shadeClose: false, //点击遮罩关闭
            title: ['新增楼盘信息'],
            resize: false,
            scrollbar: false,
            content: ['${ctx}/rest/tradeFair/to_add', 'yes']
        });
    }

    window.onload = function () {
        $('#searchForm').submit();
    }
</script>
<!-- 本页私有js -->
</body>
</html>