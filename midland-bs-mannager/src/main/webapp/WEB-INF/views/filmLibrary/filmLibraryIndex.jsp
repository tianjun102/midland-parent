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


<!--委托列表界面-->
<div class="box">
    <section class="content">
        <div id="headIndex">
            <p class="detail-title">
                <span>外销网管理>>片库管理</span>
                <a class="setup" target="contentF" href="${ctx}/rest/filmLibrary/to_add">新增</a>
            </p>
            <form action="${ctx }/rest/filmLibrary/list" method="POST" id="searchForm"
                  onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
                <ul class="userinfo row">
                    <%@include file="../menu/indexArea.jsp" %>
                    <li><span style="width: 80px;">楼盘id：</span>
                        <input type="text" name="housesId" id="housesId" placeholder="请输入楼盘id"/></li>
                    </li>
                    <li><span>楼盘名称：</span>
                        <input type="text" name="title" id="title" placeholder="请输入楼盘名称"/></li>
                    </li>
                    <li>
                        <span style="width: 60px;">分类：</span>
                        <select name="filmType" id="filmType" class="dropdown">
                            <option value="">全部</option>
                            <c:forEach items="${filmTypes}" var="s">
                                <option value="${s.id}">
                                        ${s.name}
                                </option>
                            </c:forEach>
                        </select>
                    </li>
                    <li><span style="width: 60px;">状态：</span>
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
                <input onclick="batchDelete(0)" class="public_btn bg1  listButton"   type="submit" value="批量恢复"/>
            </c:if>
        </div>
        <div id="listDiv"></div>
    </section>
</div>


<script type="text/javascript">
    var allHeight = $(window).height();

    function toAddPage1() {
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['500px', '700px'],
            shadeClose: false, //点击遮罩关闭
            title: ['片库信息'],
            resize: false,
            scrollbar: false,
            content: ['${ctx}/rest/filmLibrary/to_add', 'no']
        });
    }

    window.onload = function () {
        $('#searchForm').submit();
    }
</script>
<!-- 本页私有js -->

</body>
</html>