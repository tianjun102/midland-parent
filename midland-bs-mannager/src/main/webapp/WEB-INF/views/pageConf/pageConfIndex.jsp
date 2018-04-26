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
            <span>站点管理>>页面配置管理</span>
            <a class="setup" target="contentF" onclick="toAddPage()">新增</a>
        </p>
        <form action="${ctx }/rest/pageConf/list" method="POST" id="searchForm"
              onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
            <ul class="userinfo row">
                <%--<li>
                    <span style = "float:left;">城市：</span>
                    <select name="cityId" id="cityId" style="height: 28px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                        <option value="">全部</option>
                        <c:forEach items="${cityList}" var="city">
                            <option value="${city.id}">${city.name}</option>
                        </c:forEach>
                    </select>
                </li>--%>
                <%@include file="../layout/sherchArea.jsp" %>
                <li>
                    <span style="float:left;">平台：</span>
                    <select name="source" id="source" class="dropdown">
                        <option value="">全部</option>
                        <option value="0">网站</option>
                        <option value="1">微站</option>
                    </select>
                </li>
                <li><span>页面：</span>
                    <%--<input type="text" name="model" id="model" value="${item.model}"/>--%>
                    <select name="model" id="model" onchange="selectSource()" class="dropdown">
                        <option value="">全部</option>
                        <option
                                <c:if test="${item.model =='11'}">selected="selected"</c:if> value="11">整站
                        </option>
                        <option
                                <c:if test="${item.model =='0'}">selected="selected"</c:if> value="0">首页
                        </option>
                        <option
                                <c:if test="${item.model =='1'}">selected="selected"</c:if> value="1">新房
                        </option>
                        <option
                                <c:if test="${item.model =='2'}">selected="selected"</c:if> value="2">二手房
                        </option>
                        <option
                                <c:if test="${item.model =='3'}">selected="selected"</c:if> value="3">租房
                        </option>
                        <option
                                <c:if test="${item.model =='4'}">selected="selected"</c:if> value="4">写字楼
                        </option>
                        <option
                                <c:if test="${item.model =='5'}">selected="selected"</c:if> value="5">商铺
                        </option>
                        <option
                                <c:if test="${item.model =='6'}">selected="selected"</c:if> value="6">小区
                        </option>
                        <option
                                <c:if test="${item.model =='7'}">selected="selected"</c:if> value="7">经纪人
                        </option>
                        <option
                                <c:if test="${item.model =='8'}">selected="selected"</c:if> value="8">外销网
                        </option>
                        <option
                                <c:if test="${item.model =='9'}">selected="selected"</c:if> value="9">市场研究
                        </option>
                        <option
                                <c:if test="${item.model =='10'}">selected="selected"</c:if> value="10">资讯
                        </option>
                    </select>
                </li>
                <c:if test="${not empty isSuper}">
                    <li>
                        <span style="float:left;">是否删除：</span>
                        <select name="isDelete" id="isDelete"
                                style="height: 28px;width: 150px; display: inline-table;border-radius: 4px;border: 1px solid #dbe2e6;">
                            <option value="0">未删除</option>
                            <option value="1">已删除</option>
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
                style="margin-left: 20px;width: 70px;height: 28px;line-height: 28px!important;margin-top: 10px;"
                onclick="batchDelete(0)" class="public_btn bg1" type="submit" value="批量恢复"/></c:if>
        <div id="listDiv"></div>
    </section>
</div>


<script type="text/javascript">
    function toAddPage() {
        layer.open({
            type: 2,
            skin: 'layer-style',
            area: ['100%', '100%'],
            shadeClose: false, //点击遮罩关闭
            title: ['新增'],
            resize: false,
            scrollbar: false,
            content: ['${ctx}/rest/pageConf/to_add', 'yes']
        });
    }

    window.onload = function () {
        $('#searchForm').submit();
    }
</script>
<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
</body>
</html>